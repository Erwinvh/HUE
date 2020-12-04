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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hue.R;
import com.example.hue.model.Light;
import com.example.hue.service.HueService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import top.defaults.colorpicker.ColorPickerPopup;

public class detailfragment extends Fragment {

    private Light subjectLamp;
    private HueService hueService;

    private int mDefaultColor = 0;
    private View mColorPreview;
    private EditText LampName;
    private boolean editable = false;
    private Switch LampSwitch;
    private String subjectKey;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            subjectLamp = bundle.getParcelable("LampInfo"); // Value
            subjectKey = bundle.getString("LampKey"); // Light number
        }
        View RootView = inflater.inflate(R.layout.detailfragment, container, false);

        // Initalise HueService
        hueService = new HueService();

        //NAME
        LampName = (EditText) RootView.findViewById(R.id.LampNameDetail);
        LampName.setText(subjectLamp.getName());
        toggleEditableName(editable);

        //ON/OFF
        LampSwitch = (Switch) RootView.findViewById(R.id.switchDetail);
        LampSwitch.setChecked(subjectLamp.getState().isOn());
        LampSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                subjectLamp.getState().setOn(isChecked);
                if (!LampSwitch.isChecked()){
                    mColorPreview.setBackgroundColor(Color.BLACK);
                }
                else{
                    mColorPreview.setBackgroundColor(mDefaultColor);
                }
        hueService.updateOn(subjectKey, LampSwitch.isChecked());
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
                    hueService.updateName(subjectKey, NewName);
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
        float[] hsvfloat = new float[3];
        hsvfloat[0] = (float) (subjectLamp.getState().getHue() / 182.0444);
        hsvfloat[1] = (float) (subjectLamp.getState().getSat() / 2.55);
        hsvfloat[2] = (float) (subjectLamp.getState().getBri() / 2.55);
        mDefaultColor = Color.HSVToColor(hsvfloat);
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
                        if (LampSwitch.isChecked()) {
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
                                                    float[] hue = new float[3];
                                                    Color.RGBToHSV(Color.red(color), Color.green(color), Color.blue(color), hue);

                                                    hueService.updateSat(subjectKey, (int) (hue[1] * 255));
                                                    hueService.updateHue(subjectKey, (int) (hue[0] * 182));
                                                    hueService.updateBri(subjectKey, (int) (hue[2] * 255));
                                                    Log.d("detailfragment.java", "Color code sent: [" + hue[0] + ", " + hue[1] + ", " + hue[2] + "]"); // Source: https://github.com/square/okhttp/issues/2408

                                                    subjectLamp.getState().setHue((int) hue[0]);
                                                    subjectLamp.getState().setSat((int) hue[1]);
                                                    subjectLamp.getState().setBri((int) hue[2]);
                                                    if (LampSwitch.isChecked()) {
                                                        mColorPreview.setBackgroundColor(mDefaultColor);
                                                    }
                                                }
                                            });
                        } else{
                            Toast.makeText(getContext().getApplicationContext(),getResources().getString(R.string.turn_on_light),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return RootView;
    }

    public void toggleEditableName(boolean bool){
        LampName.setEnabled(bool);
        LampName.setFocusable(bool);
    }
}
