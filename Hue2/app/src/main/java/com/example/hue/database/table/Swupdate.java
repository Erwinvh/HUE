
package com.example.hue.database.table;

import androidx.room.Entity;

@Entity(tableName = "swupdate_table")
public class Swupdate {

    private String state;
    private String lastinstall;

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getLastinstall() {
        return lastinstall;
    }
    public void setLastinstall(String lastinstall) {
        this.lastinstall = lastinstall;
    }
}
