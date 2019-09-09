package com.tianhui.chartdemo.base;

import com.tianhui.chartdemo.utils.UIdGenerator;

public abstract class BasicEntity {
    public String id;

    public BasicEntity() {
        if (null == id) {
            id = UIdGenerator.getInstance().getUId()+"";
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
