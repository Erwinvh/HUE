
package com.example.hue.database.table;

import androidx.room.Embedded;
import androidx.room.Entity;

import java.util.List;

@Entity(tableName = "control_table")
public class Control {

    private Integer mindimlevel;
    private Integer maxlumen;
    private String colorgamuttype;
    private List<List<Double>> colorgamut = null;
    @Embedded
    private Ct ct;

    public Integer getMindimlevel() {
        return mindimlevel;
    }
    public void setMindimlevel(Integer mindimlevel) {
        this.mindimlevel = mindimlevel;
    }
    public Integer getMaxlumen() {
        return maxlumen;
    }
    public void setMaxlumen(Integer maxlumen) {
        this.maxlumen = maxlumen;
    }
    public String getColorgamuttype() {
        return colorgamuttype;
    }
    public void setColorgamuttype(String colorgamuttype) {
        this.colorgamuttype = colorgamuttype;
    }
    public List<List<Double>> getColorgamut() {
        return colorgamut;
    }
    public void setColorgamut(List<List<Double>> colorgamut) {
        this.colorgamut = colorgamut;
    }
    public Ct getCt() {
        return ct;
    }
    public void setCt(Ct ct) {
        this.ct = ct;
    }
}
