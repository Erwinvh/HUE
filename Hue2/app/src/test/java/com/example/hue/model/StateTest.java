package com.example.hue.model;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.runner.RunWith;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.util.Objects;

import static org.junit.Assert.*;

public class StateTest {

    @Test
    public void getSat(){
        State testState = new State(0,0,0,false);
        Assert.assertEquals(testState.getSat(), 0);
    }

    @Test
    public void getHue(){
        State testState = new State(0,0,0,false);
        Assert.assertEquals(testState.getHue(), 0);
    }

    @Test
    public void isOn(){
        State testState = new State(0,0,0,false);
        Assert.assertFalse(testState.isOn());
    }

    @Test
    public void getBri(){
        State testState = new State(0,0,0,false);
        Assert.assertEquals(testState.getBri(), 0);
    }

    @Test
    public void setSat(){
        State testState = new State(0,0,0,false);
        testState.setSat(50);
        Assert.assertEquals(testState.getSat(), 50);
    }

    @Test
    public void setHue(){
        State testState = new State(0,0,0,false);
        testState.setHue(50);
        Assert.assertEquals(testState.getHue(), 50);
    }

    @Test
    public void setBri(){
        State testState = new State(0,0,0,false);
        testState.setBri(50);
        Assert.assertEquals(testState.getBri(), 50);
    }

    @Test
    public void setOn(){
        State testState = new State(0,0,0,false);
        testState.setOn(true);
        Assert.assertTrue(testState.isOn());
    }
    @Test
    public void StateConstructor(){
        State toCheckState = new State(12,22,56,true);
        assertEquals(toCheckState.getBri(), 22);
        assertEquals(toCheckState.getHue(), 56);
        assertEquals(toCheckState.getSat(), 12);
        assertEquals(toCheckState.isOn(), true);
    }
}