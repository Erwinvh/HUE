package com.example.hue.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hue.Lamp;
import com.example.hue.R;

public class detailfragment extends Fragment {

    private Lamp subjectLamp;


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            subjectLamp = bundle.getParcelable("LampInfo"); // Key
        }
        View RootView = inflater.inflate(R.layout.detailfragment, container, false);
        TextView LampNaam = (TextView) RootView.findViewById(R.id.LampNameDetail);
        LampNaam.setText(subjectLamp.getLampName());
        Switch LampSwitch = (Switch) RootView.findViewById(R.id.switchDetail);
        LampSwitch.setChecked(subjectLamp.getLampState());

        return RootView;
    }
}
