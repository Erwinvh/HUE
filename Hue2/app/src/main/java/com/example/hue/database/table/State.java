
package com.example.hue.database.table;

import androidx.room.Entity;

import java.util.List;

@Entity(tableName = "state_table")
public class State {

    private Boolean on;
    private Integer bri;
    private Integer hue;
    private Integer sat;
    private String effect;
    private List<Double> xy = null;
    private Integer ct;
    private String alert;
    private String colormode;
    private String mode;
    private Boolean reachable;

    public Boolean getOn() {
        return on;
    }
    public void setOn(Boolean on) {
        this.on = on;
    }
    public Integer getBri() {
        return bri;
    }
    public void setBri(Integer bri) {
        this.bri = bri;
    }
    public Integer getHue() {
        return hue;
    }
    public void setHue(Integer hue) {
        this.hue = hue;
    }
    public Integer getSat() {
        return sat;
    }
    public void setSat(Integer sat) {
        this.sat = sat;
    }
    public String getEffect() {
        return effect;
    }
    public void setEffect(String effect) {
        this.effect = effect;
    }
    public List<Double> getXy() {
        return xy;
    }
    public void setXy(List<Double> xy) {
        this.xy = xy;
    }
    public Integer getCt() {
        return ct;
    }
    public void setCt(Integer ct) {
        this.ct = ct;
    }
    public String getAlert() {
        return alert;
    }
    public void setAlert(String alert) {
        this.alert = alert;
    }
    public String getColormode() {
        return colormode;
    }
    public void setColormode(String colormode) {
        this.colormode = colormode;
    }
    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }
    public Boolean getReachable() {
        return reachable;
    }
    public void setReachable(Boolean reachable) {
        this.reachable = reachable;
    }
}
