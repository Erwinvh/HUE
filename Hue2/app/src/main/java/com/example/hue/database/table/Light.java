
package com.example.hue.database.table;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "light_table")
public class Light {

    @NonNull // To show current state of light in detail view.
    @Embedded
    private State state;
    @Embedded
    private Swupdate swupdate;
    private String type;
    private String name;
    private String modelid;
    private String manufacturername;
    @NonNull // To order the lights when displayed.
    private String productname;
    @Embedded
    private Capabilities capabilities;
    @Embedded
    private Config config;
    @PrimaryKey
    @NonNull // To identify all lights
    private String uniqueid;
    private String swversion;

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
    public Swupdate getSwupdate() {
        return swupdate;
    }
    public void setSwupdate(Swupdate swupdate) {
        this.swupdate = swupdate;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getModelid() {
        return modelid;
    }
    public void setModelid(String modelid) {
        this.modelid = modelid;
    }
    public String getManufacturername() {
        return manufacturername;
    }
    public void setManufacturername(String manufacturername) { this.manufacturername = manufacturername; }
    public String getProductname() {
        return productname;
    }
    public void setProductname(String productname) {
        this.productname = productname;
    }
    public Capabilities getCapabilities() {
        return capabilities;
    }
    public void setCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
    }
    public Config getConfig() {
        return config;
    }
    public void setConfig(Config config) {
        this.config = config;
    }
    public String getUniqueid() {
        return uniqueid;
    }
    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }
    public String getSwversion() {
        return swversion;
    }
    public void setSwversion(String swversion) {
        this.swversion = swversion;
    }
}
