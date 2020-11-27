
package com.example.hue.database.table;


import androidx.room.Entity;

@Entity(tableName = "streaming_table")
public class Streaming {

    private Boolean renderer;
    private Boolean proxy;

    public Boolean getRenderer() {
        return renderer;
    }
    public void setRenderer(Boolean renderer) {
        this.renderer = renderer;
    }
    public Boolean getProxy() {
        return proxy;
    }
    public void setProxy(Boolean proxy) {
        this.proxy = proxy;
    }
}
