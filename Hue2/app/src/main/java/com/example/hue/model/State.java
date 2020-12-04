package com.example.hue.model;

import org.json.JSONException;
import org.json.JSONObject;

public class State {
    private int sat;
    private int bri;
    private int hue;
    private boolean on;

    public State(JSONObject json) throws JSONException {
        this.sat = json.getInt("sat");
        this.bri = json.getInt("bri");
        this.hue = json.getInt("hue");
        this.on = json.getBoolean("on");
    }

    public State(int sat, int bri, int hue, boolean on) {
        this.sat = sat;
        this.bri = bri;
        this.hue = hue;
        this.on = on;
    }

    public int getSat() {
        return sat;
    }

    public int getHue() {
        return hue;
    }

    public boolean isOn() {
        return on;
    }

    public void setSat(int sat) {
        this.sat = sat;
    }

    public void setBri(int bri) {
        this.bri = bri;
    }

    public int getBri() {
        return bri;
    }

    public void setHue(int hue) {
        this.hue = hue;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
}
