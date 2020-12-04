package com.example.hue.model;

import java.util.HashMap;

public class Lighting {

    private static Lighting INSTANCE = null;
    private HashMap<String, Light> lights;
    private int numberOfLights;

    private Lighting() {
        this.lights = new HashMap<>();
        this.numberOfLights = 0;
    }

    public static Lighting getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Lighting();
        }
        return INSTANCE;
    }

    public Light getLight(String number) {
        return lights.get(number);
    }

    public void setLights(HashMap<String, Light> lights) {
        this.lights = lights;
        this.numberOfLights = lights.size();
    }

    public HashMap<String, Light> getLights() {
        return lights;
    }
}
