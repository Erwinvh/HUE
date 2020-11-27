package com.example.hue.fragments;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hue.Lamp;
import com.example.hue.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        //NAME
        final EditText LampNaam = (EditText) RootView.findViewById(R.id.LampNameDetail);
        LampNaam.setText(subjectLamp.getLampName());

        //ON/OFF
        final Switch LampSwitch = (Switch) RootView.findViewById(R.id.switchDetail);
        LampSwitch.setChecked(subjectLamp.getLampState());
        LampSwitch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                subjectLamp.toggleLamp(!LampSwitch.isChecked());
                LampSwitch.setChecked(!LampSwitch.isChecked());
            }
        });

        //EDIT NAME BUTTON
        final FloatingActionButton EditNameButton = (FloatingActionButton)  RootView.findViewById(R.id.EditNameButton);
        EditNameButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (LampNaam.isEnabled()){
                    String NewName = LampNaam.getText().toString();
                    subjectLamp.setName(NewName);
                    LampNaam.setEnabled(false);
                    EditNameButton.setBackgroundTintList(null);
                }else{
                    LampNaam.setEnabled(true);
                    EditNameButton.setBackgroundTintList(ColorStateList.valueOf(R.color.EditGreen));
                }
            }
        });

        //TODO: add colorwheel picker






        return RootView;
    }
}
