package com.example.hue;

import android.os.Parcel;
import android.os.Parcelable;

public class Lamp implements Parcelable {

    //teswtcode
    private String helloworld;

    public Lamp() {


    }


    public static final Creator<Lamp> CREATOR = new Creator<Lamp>() {
        @Override
        public Lamp createFromParcel(Parcel in) {
            return new Lamp();
        }

        @Override
        public Lamp[] newArray(int size) {
            return new Lamp[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(helloworld);
    }

    public String getLampName() {
        //TODO:
        return "";
    }

    public int getLampImageResource() {
        //TODO:
        return 0;
    }

    public boolean getLampState() {
        //TODO:
        return false;
    }

    public void setName(String newName) {
        //TODO:
    }

    public void toggleLamp(boolean b) {
        //TODO:
    }
}
