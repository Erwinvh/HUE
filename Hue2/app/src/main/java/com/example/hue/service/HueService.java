package com.example.hue.service;

import android.util.Log;

import com.example.hue.model.Light;
import com.example.hue.model.Lighting;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HueService implements IHueService {

    private final String TAG = getClass().getSimpleName();

    private OkHttpClient client = new OkHttpClient();
    volatile boolean hasCallbackEnded = false;
    volatile boolean isPutServiceSuccess = false;

    public void getLights() {
        getService("http://10.0.2.2:8000/api/newdeveloper/lights/");
    }

    // TODO Create functions for other variables if necessary:
    // - Modelid
    // - swversion
    // - state/reachable
    // - type
    // - pointsymbol
    // - uniqueid

    public boolean updateName(String lightNumber, String name) {
        if (putServiceHelper(lightNumber, "name", name)) {
            Lighting.getINSTANCE().getLight(lightNumber).setName(name);
            return true;
        }
        return false;
    }

    @Deprecated
    public boolean updateXY(String lightNumber, List<Double> xy) {
        String body = "{\"xy\":[" + xy.get(0) + "," + xy.get(1) + "]}";
        if (putServiceHelper(lightNumber, "xy", RequestBody.create(body.getBytes()))) {
            Lighting.getINSTANCE().getLight(lightNumber).getState().setXy(xy);
            return true;
        }
        return false;
    }

    public boolean updateCt(String lightNumber, int ct) {
        if (putServiceHelper(lightNumber, "ct", Integer.toString(ct))) {
            Lighting.getINSTANCE().getLight(lightNumber).getState().setCt(ct);
            return true;
        }
        return false;
    }

    public boolean updateAlert(String lightNumber, String alert) {
        if (putServiceHelper(lightNumber, "alert", alert)) {
            Lighting.getINSTANCE().getLight(lightNumber).getState().setAlert(alert);
            return true;
        }
        return false;
    }

    public boolean updateSat(String lightNumber, int sat) {
        if (putServiceHelper(lightNumber, "sat", Integer.toString(sat))) {
            Lighting.getINSTANCE().getLight(lightNumber).getState().setSat(sat);
            return true;
        }
        return false;
    }

    public boolean updateEffect(String lightNumber, String effect) {
        if (putServiceHelper(lightNumber, "effect", effect)) {
            Lighting.getINSTANCE().getLight(lightNumber).getState().setEffect(effect);
            return true;
        }
        return false;
    }

    public boolean updateBri(String lightNumber, int bri) {
        if (putServiceHelper(lightNumber, "bri", Integer.toString(bri))) {
            Lighting.getINSTANCE().getLight(lightNumber).getState().setBri(bri);
            return true;
        }
        return false;
    }

    public boolean updateHue(String lightNumber, int hue) {
        if (putServiceHelper(lightNumber, "hue", Integer.toString(hue))) {
            Lighting.getINSTANCE().getLight(lightNumber).getState().setCt(hue);
            return true;
        }
        return false;
    }

    public boolean updateColorMode(String lightNumber, String colormode) {
        if (putServiceHelper(lightNumber, "colormode", colormode)) {
            Lighting.getINSTANCE().getLight(lightNumber).getState().setColormode(colormode);
            return true;
        }
        return false;
    }

    public boolean updateOn(String lightNumber, boolean on) {
        if (putServiceHelper(lightNumber, "on", Boolean.toString(on))) {
            Lighting.getINSTANCE().getLight(lightNumber).getState().setOn(on);
            return true;
        }
        return false;
    }

    private boolean putServiceHelper(String lightNumber, String variable, RequestBody requestBody) {
        String service = "http://10.0.2.2:8000/api/newdeveloper/lights/" + lightNumber;
        if (!variable.contains("name")) service += "/state";

        if (putService(service, requestBody)) {
            Log.d(TAG, "Test putService successful.");
            return true;
        } else {
            Log.d(TAG, "Test putService failed.");
            return false;
        }
    }

    private boolean putServiceHelper(String lightNumber, String variable, String value) {
        String body = "{\"" + variable + "\":\"" + value + "\"}";
        return putServiceHelper(lightNumber, variable, RequestBody.create(body.getBytes()));
    }

    @Override
    public void getService(String service) {
        hasCallbackEnded = false;
        Request request = new Request.Builder()
                .url(service)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                hasCallbackEnded = true;
                Log.d(TAG, "Failed to receive response: " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                Log.d(TAG, "Received: " + json);
                try {
                    deserialize(new JSONObject(json));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                hasCallbackEnded = true;
            }
        });
        // Wait for Callback to finish
        while (!hasCallbackEnded) {
        }
    }

    @Override
    public boolean putService(String service, RequestBody body) {
        hasCallbackEnded = false;
        Request request = new Request.Builder()
                .url(service)
                .put(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                isPutServiceSuccess = false;
                hasCallbackEnded = true;
                Log.d(TAG, "Failed to receive response: " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                Log.d(TAG, "Received response  " + json);
                isPutServiceSuccess = json.length() > 20 && json.contains("\"success\":");
                hasCallbackEnded = true;
            }
        });

        // Wait for Callback to finish
        while (!hasCallbackEnded) {
        }
        return isPutServiceSuccess;
    }

    private void deserialize(JSONObject json) {
        HashMap<String, Light> newLighting = new HashMap<>();
        Iterator<String> keys = json.keys();

        // Create Light for each nested JSONObject.
        while (keys.hasNext()) {
            String key = keys.next();
            try {
                Light light = new Light(json.getJSONObject(key));
                newLighting.put(key, light);

            } catch (JSONException e) {
                Log.d("HueService.java", e.getLocalizedMessage());
            }
        }

        // Replace current Lighting (if any) with received lighting
        Lighting.getINSTANCE().setLights(newLighting);
    }
}
