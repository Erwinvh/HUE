package com.example.hue.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.hue.Lamp;
import com.example.hue.R;

public class masterfragment extends Fragment {

    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.detailfragment, container, false);

        Button SettingsButton = (Button) RootView.findViewById(R.id.SettingsButton);
        SettingsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final Intent intent;
                    Fragment LampDetailFragment = new settingsfragment();
//                    ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.Container, LampDetailFragment).commit();

            }
        });

        return RootView;
    }



}
