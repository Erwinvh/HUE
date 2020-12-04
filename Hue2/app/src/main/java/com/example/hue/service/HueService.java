package com.example.hue.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.hue.MainActivity;
import com.example.hue.R;
import com.example.hue.model.Light;
import com.example.hue.model.Lighting;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HueService implements IHueService {

    private final String TAG = getClass().getSimpleName();

    private OkHttpClient client;
    volatile boolean hasCallbackEnded;
    volatile boolean isPutServiceSuccess;

    public HueService() {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(200, TimeUnit.MILLISECONDS)
                .readTimeout(200, TimeUnit.MILLISECONDS)
                .writeTimeout(200, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .build();
        this.hasCallbackEnded = false;
        this.isPutServiceSuccess = false;
    }

    public void getLights(boolean forceConnect) {
        String loadedJson = MainActivity.loadLights();
        if (loadedJson.equals("") || forceConnect) {
            getService("http://10.0.2.2:8000/api/newdeveloper/lights/");
        } else {
            try {
                deserialize(new JSONObject(loadedJson));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    public boolean updateName(String lightNumber, String name) {
        if (putServiceHelper(lightNumber, "name", name)) {
            Lighting.getINSTANCE().getLight(lightNumber).setName(name);
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

    public boolean updateBri(String lightNumber, int bri) {
        if (putServiceHelper(lightNumber, "bri", Integer.toString(bri))) {
            Lighting.getINSTANCE().getLight(lightNumber).getState().setBri(bri);
            return true;
        }
        return false;
    }

    public boolean updateHue(String lightNumber, int hue) {
        if (putServiceHelper(lightNumber, "hue", Integer.toString(hue))) {
            Lighting.getINSTANCE().getLight(lightNumber).getState().setHue(hue);
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

    private boolean putServiceHelper(String lightNumber, String variable, String value) {
        String service = "http://10.0.2.2:8000/api/newdeveloper/lights/" + lightNumber;
        if (!variable.contains("name")) service += "/state";

        String body = "{\"" + variable + "\":\"" + value + "\"}";
        RequestBody requestBody = RequestBody.create(body.getBytes());

        if (putService(service, requestBody)) {
            Log.d(TAG, "Test putService successful.");
            return true;
        } else {
            Log.d(TAG, "Test putService failed.");
            return false;
        }
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
                    MainActivity.saveLights(json);
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

                JSONObject jsonLight = json.getJSONObject(key);
                String name = jsonLight.getString("name");
                JSONObject jsonState = jsonLight.getJSONObject("state");
                int sat = jsonState.getInt("sat");
                int bri = jsonState.getInt("bri");
                int hue = jsonState.getInt("hue");
                boolean on = jsonState.getBoolean("on");

                Light light = new Light(name, sat, bri, hue, on);
                newLighting.put(key, light);

            } catch (JSONException e) {
                Log.d("HueService.java", e.getLocalizedMessage());
            }
        }

        // Replace current Lighting (if any) with received lighting
        Lighting.getINSTANCE().setLights(newLighting);
        Log.d("LIGHTS", "Lights are successfully received.");
        test();
    }

    private static void test() {
        System.out.println("test");
    }
}
