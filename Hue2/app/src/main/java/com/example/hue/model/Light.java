package com.example.hue.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Light implements Parcelable {
    private String name;
    private State state;

    public Light(JSONObject json) throws JSONException {
        this.name = json.getString("name");
        this.state = new State(json.getJSONObject("state"));
    }

    protected Light(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Light> CREATOR = new Creator<Light>() {
        @Override
        public Light createFromParcel(Parcel in) {
            return new Light(in);
        }

        @Override
        public Light[] newArray(int size) {
            return new Light[size];
        }
    };

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
