package com.example.hue.model;

public class State {
    private int sat;
    private int bri;
    private int hue;
    private boolean on;

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
