package com.tianhui.chartdemo.localEnum;

public enum OrderEnum {

    CANCELED(26,"已取消"),
    SUCCEED(21,"已下单"),
    RECEIVED(22,"已接单"),
    ONROAD(23,"配送中"),
    REACHED(24,"已送达"),
    DONE(25,"已完成");

    private Integer status;

    private String status_cn;

    OrderEnum(Integer status, String status_cn){
        this.status = status;
        this.status_cn = status_cn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatus_cn() {
        return status_cn;
    }

    public void setStatus_cn(String status_cn) {
        this.status_cn = status_cn;
    }
}