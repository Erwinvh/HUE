
package com.example.hue.database.table;

import androidx.room.Entity;

@Entity(tableName = "config_table")
public class Config {

    private String archetype;
    private String function;
    private String direction;

    public String getArchetype() {
        return archetype;
    }
    public void setArchetype(String archetype) {
        this.archetype = archetype;
    }
    public String getFunction() {
        return function;
    }
    public void setFunction(String function) {
        this.function = function;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
}
