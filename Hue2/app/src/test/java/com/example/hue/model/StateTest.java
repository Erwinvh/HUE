package com.example.hue.model;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import static org.junit.Assert.*;

public class StateTest {

    @Test
    public void getSat() throws JSONException {
        State state = new State(0,0,0,false);
        state.setSat(50);
        Assert.assertEquals(state.getSat(), 50);
    }

    @Test
    public void getHue() throws JSONException {
        State state = new State(0,0,0,false);
        state.setHue(50);
        Assert.assertEquals(state.getHue(), 50);
    }

    @Test
    public void isOn() throws JSONException {
        State state = new State(0,0,0,false);
        state.setOn(true);
        Assert.assertTrue(state.isOn());
    }

    @Test
    public void setSat() throws JSONException {
        State state = new State(0,0,0,false);
        state.setSat(50);
        Assert.assertEquals(state.getSat(), 50);
    }

    @Test
    public void setBri() throws JSONException {
        State state = new State(0,0,0,false);
        state.setBri(50);
        Assert.assertEquals(state.getBri(), 50);
    }

    @Test
    public void getBri() throws JSONException {
        State state = new State(0,0,0,false);
        state.setBri(50);
        Assert.assertEquals(state.getBri(), 50);
    }

    @Test
    public void setHue() throws JSONException {
        State state = new State(0,0,0,false);
        state.setHue(50);
        Assert.assertEquals(state.getHue(), 50);
    }

    @Test
    public void setOn() throws JSONException {
        State state = new State(0,0,0,false);
        state.setOn(true);
        Assert.assertTrue(state.isOn());
    }
}