package com.tianhui.chartdemo.vo;


import com.tianhui.chartdemo.base.BasicGoodsEntity;

public class GoodsVO extends BasicGoodsEntity {

    private String type;

    private String created_on;

    private String status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  super.toString()+"GoodsVO{" +
                "type='" + type + '\'' +
                ", created_on='" + created_on + '\'' +
                ", status=" + status +
                '}';
    }
}
