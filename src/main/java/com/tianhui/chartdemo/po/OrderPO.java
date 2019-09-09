package com.tianhui.chartdemo.po;


import com.tianhui.chartdemo.base.BasicOrderEntity;

public class OrderPO extends BasicOrderEntity {
    private String user_id;
    private Integer status;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
