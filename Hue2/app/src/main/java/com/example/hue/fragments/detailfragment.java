package com.example.hue.fragments;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

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
    private View mColorPreview;
    private EditText LampName;
    private boolean editable = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            subjectLamp = bundle.getParcelable("LampInfo"); // Key
        }
        View RootView = inflater.inflate(R.layout.detailfragment, container, false);

        //NAME
        LampName = (EditText) RootView.findViewById(R.id.LampNameDetail);
        LampName.setText(subjectLamp.getLampName());
        toggleEditableName(editable);


        //ON/OFF
        final Switch LampSwitch = (Switch) RootView.findViewById(R.id.switchDetail);
        LampSwitch.setChecked(subjectLamp.getLampState());
        LampSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                subjectLamp.toggleLamp(!LampSwitch.isChecked());
                if (!LampSwitch.isChecked()){
                    mColorPreview.setBackgroundColor(Color.BLACK);
                }
                else{
                    mColorPreview.setBackgroundColor(mDefaultColor);
                }

            }
        });

        //EDIT NAME BUTTON
        final FloatingActionButton EditNameButton = (FloatingActionButton)  RootView.findViewById(R.id.EditNameButton);
        EditNameButton.setBackgroundTintList(null);
        EditNameButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (editable){
                    String NewName = LampName.getText().toString();
                    subjectLamp.setName(NewName);
                    editable = false;
                    EditNameButton.setBackgroundTintList(null);
                    //InputMethodManager imm = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
                    //imm.hideSoftInputFromWindow(LampName.getWindowToken(), 0);
                }else{
                    editable = true;
                    EditNameButton.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    LampName.requestFocus();
                    //InputMethodManager imm = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
                    //imm.showSoftInput(LampName, InputMethodManager.SHOW_IMPLICIT);
                    Log.d("Color back to green", "Yes");
                }
                toggleEditableName(editable);
            }
        });

        //Color
        Button mPickColorButton = (Button) RootView.findViewById(R.id.pick_color_button);;
 mDefaultColor = subjectLamp.getLampColor();
       // mSetColorButton = RootView.findViewById(R.id.set_color_button);
        mColorPreview = RootView.findViewById(R.id.preview_selected_color);
        if (LampSwitch.isChecked()){
            mColorPreview.setBackgroundColor(mDefaultColor);
        }else{
            mColorPreview.setBackgroundColor(Color.BLACK);
        }
        mPickColorButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        new ColorPickerPopup.Builder(getContext()).initialColor(mDefaultColor)
                                .enableBrightness(true)
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
                                                if (LampSwitch.isChecked()){
                                                mColorPreview.setBackgroundColor(mDefaultColor);
                                                }

                                            }
                                        });
                    }
                });


        return RootView;
    }

    public void toggleEditableName(boolean bool){
        LampName.setEnabled(bool);
        LampName.setFocusable(bool);

    }
}
