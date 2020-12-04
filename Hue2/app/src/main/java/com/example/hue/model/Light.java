package com.example.hue.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class Light {
    private String modelid;
    private String name;
    private String swversion;
    private State state;
    private String type;
    private HashMap<Integer, String> pointsymbol;
    private String uniqueid;

    public Light(JSONObject json) throws JSONException {
        this.modelid = json.getString("modelid");
        this.name = json.getString("name");
        this.swversion = json.getString("swversion");
        this.state = new State(json.getJSONObject("state"));
        this.type = json.getString("type");
        this.uniqueid = json.getString("uniqueid");

        JSONObject jsonPointsymbol = json.getJSONObject("pointsymbol");
        this.pointsymbol = new HashMap<>();
        for (int i = 0; i < jsonPointsymbol.length() ; i++) {
            Integer key = i + 1;
            this.pointsymbol.put(key, (jsonPointsymbol.getString(Integer.toString(key))));
        }
    }

    public String getModelid() {
        return modelid;
    }

    public String getName() {
        return name;
    }

    public String getSwversion() {
        return swversion;
    }

    public State getState() {
        return state;
    }

    public String getType() {
        return type;
    }

    public HashMap<Integer, String> getPointsymbol() {
        return pointsymbol;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setName(String name) {
        this.name = name;
    }
}
