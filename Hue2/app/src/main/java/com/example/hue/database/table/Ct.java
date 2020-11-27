
package com.example.hue.database.table;

import androidx.room.Entity;

@Entity(tableName = "ct_table")
public class Ct {

    private Integer min;
    private Integer max;

    public Integer getMin() {
        return min;
    }
    public void setMin(Integer min) { this.min = min; }
    public Integer getMax() {
        return max;
    }
    public void setMax(Integer max) {
        this.max = max;
    }
}
