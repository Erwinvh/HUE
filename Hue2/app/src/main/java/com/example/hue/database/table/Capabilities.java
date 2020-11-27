
package com.example.hue.database.table;

import androidx.room.Embedded;
import androidx.room.Entity;

@Entity(tableName = "capabilities_table")
public class Capabilities {

    private Boolean certified;
    @Embedded
    private Control control;
    @Embedded
    private Streaming streaming;

    public Boolean getCertified() {
        return certified;
    }
    public void setCertified(Boolean certified) {
        this.certified = certified;
    }
    public Control getControl() {
        return control;
    }
    public void setControl(Control control) {
        this.control = control;
    }
    public Streaming getStreaming() {
        return streaming;
    }
    public void setStreaming(Streaming streaming) {
        this.streaming = streaming;
    }
}
