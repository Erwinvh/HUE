package com.example.hue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;

import com.example.hue.fragments.detailfragment;
import com.example.hue.fragments.masterfragment;
import com.example.hue.service.HueService;

public class MainActivity extends AppCompatActivity {

    public HueService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = new HueService();
        service.getLights();

        getSupportFragmentManager().beginTransaction().replace(R.id.Container, new masterfragment()).commit();
    }

    @Override
    public void onBackPressed() {
        Fragment current = (Fragment) this.getSupportFragmentManager().findFragmentById(R.id.Container);
        if (current instanceof detailfragment){
            getSupportFragmentManager().beginTransaction().replace(R.id.Container, new masterfragment()).commit();
        }

    }

}
