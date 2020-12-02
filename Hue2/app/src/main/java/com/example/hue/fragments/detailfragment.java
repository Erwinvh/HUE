package com.example.hue.fragments;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hue.Lamp;
import com.example.hue.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import top.defaults.colorpicker.ColorPickerPopup;

public class detailfragment extends Fragment {

    private Lamp subjectLamp;

    private int mDefaultColor = 0;
    private Button mPickColorButton;
    private View mColorPreview;

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
        Button mPickColorButton = (Button) RootView.findViewById(R.id.pick_color_button);;
 mDefaultColor = 0;
       // mSetColorButton = RootView.findViewById(R.id.set_color_button);
        mColorPreview = RootView.findViewById(R.id.preview_selected_color);
        mPickColorButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        new ColorPickerPopup.Builder(getContext()).initialColor(Color.RED)
                                .enableBrightness(true)
                                .enableAlpha(true)
                                .okTitle("Choose")
                                .cancelTitle("Cancel")
                                .showIndicator(true)
                                .showValue(true)
                                .build()
                                .show(
                                        v,
                                        new ColorPickerPopup.ColorPickerObserver() {
                                            @Override
                                            public void
                                            onColorPicked(int color) {
                                                mDefaultColor = color;
                                                mColorPreview.setBackgroundColor(mDefaultColor);
                                            }
                                        });
                    }
                });


        return RootView;
    }
}
