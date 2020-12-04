package com.example.hue.model;

import android.os.Parcel;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LightTest {


    @Test
    public void getName() {
        Light TestLight = new Light("TestLight", 0,0,0,false);
        assertEquals(TestLight.getName(), "TestLight");
    }

    @Test
    public void getState() {
        Light TestLight = new Light("TestLight", 0,1,2,false);
        assertEquals(TestLight.getState().getHue(), 2);
        assertEquals(TestLight.getState().getBri(), 1);
        assertEquals(TestLight.getState().getSat(), 0);
        assertEquals(TestLight.getState().isOn(), false);
    }

    @Test
    public void setName() {
        Light TestLight = new Light("TestLight", 0,0,0,false);
        TestLight.setName("NewName");
        assertEquals(TestLight.getName(), "NewName");
    }

    @Test
    public void describeContents() {
        Light TestLight = new Light("TestLight", 0,0,0,false);
        assertEquals(TestLight.describeContents(), 0);
    }

    @Test
    public void TestRegularConstructor() {
        Light ToTestConstructor = new Light("Name", 12,22,56,true);
        assertEquals(ToTestConstructor.getName(), "Name");
        assertEquals(ToTestConstructor.getState().getSat(), 12);
        assertEquals(ToTestConstructor.getState().getBri(), 22);
        assertEquals(ToTestConstructor.getState().getHue(), 56);
        assertEquals(ToTestConstructor.getState().isOn(), true);
    }

}