package com.example.hue.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class State {
    private List<Double> xy;
    private int ct;
    private String alert;
    private int sat;
    private String effect;
    private int bri;
    private int hue;
    private String colormode;
    private boolean reachable;
    private boolean on;

    public State(JSONObject json) throws JSONException {
        this.ct = json.getInt("ct");
        this.alert = json.getString("alert");
        this.sat = json.getInt("sat");
        this.effect = json.getString("effect");
        this.bri = json.getInt("bri");
        this.hue = json.getInt("hue");
        this.colormode = json.getString("colormode");
        this.reachable = json.getBoolean("reachable");
        this.on = json.getBoolean("on");

        JSONArray jsonXy = json.getJSONArray("xy");
        this.xy = new ArrayList<>();
        for (int i = 0; i < jsonXy.length() ; i++) {
            this.xy.add(jsonXy.getDouble(i));
        }
    }

    public List<Double> getXy() {
        return xy;
    }

    public int getCt() {
        return ct;
    }

    public String getAlert() {
        return alert;
    }

    public int getSat() {
        return sat;
    }

    public String getEffect() {
        return effect;
    }

    public int getBri() {
        return bri;
    }

    public int getHue() {
        return hue;
    }

    public String getColormode() {
        return colormode;
    }

    public boolean isReachable() {
        return reachable;
    }

    public boolean isOn() {
        return on;
    }

    public void setXy(List<Double> xy) {
        this.xy = xy;
    }

    public void setCt(int ct) {
        this.ct = ct;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public void setSat(int sat) {
        this.sat = sat;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public void setBri(int bri) {
        this.bri = bri;
    }

    public void setHue(int hue) {
        this.hue = hue;
    }

    public void setColormode(String colormode) {
        this.colormode = colormode;
    }

    public void setReachable(boolean reachable) {
        this.reachable = reachable;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
}
