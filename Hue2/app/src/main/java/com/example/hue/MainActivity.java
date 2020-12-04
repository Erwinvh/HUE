package com.example.hue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.hue.fragments.detailfragment;
import com.example.hue.fragments.masterfragment;
import com.example.hue.service.HueService;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_LIGHTS_JSON = "key_lights_json";
    private static Context context;

    public HueService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        service = new HueService();
        service.getLights(false);

        getSupportFragmentManager().beginTransaction().replace(R.id.Container, new masterfragment()).commit();
    }

    @Override
    public void onBackPressed() {
        Fragment current = (Fragment) this.getSupportFragmentManager().findFragmentById(R.id.Container);
        if (current instanceof detailfragment){
            getSupportFragmentManager().beginTransaction().replace(R.id.Container, new masterfragment()).commit();
        }
    }

    public static void saveLights(String json) {
        SharedPreferences pref = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(KEY_LIGHTS_JSON, json);
        editor.apply();
    }

    public static String loadLights() {
        SharedPreferences pref = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        return pref.getString(KEY_LIGHTS_JSON, "");

    }

}
