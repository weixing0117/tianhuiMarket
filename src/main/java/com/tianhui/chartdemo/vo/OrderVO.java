package com.tianhui.chartdemo.vo;

import com.tianhui.chartdemo.base.BasicOrderEntity;
import com.tianhui.chartdemo.bean.OrderInfoBean;

import java.util.List;

public class OrderVO extends BasicOrderEntity {
    private String status;

    private String user_name;

    private String created_on;

    private String end_time;

    List<OrderInfoBean> orderInfoList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public List<OrderInfoBean> getOrderInfoList() {
        return orderInfoList;
    }

    public void setOrderInfoList(List<OrderInfoBean> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "status='" + status + '\'' +
                ", created_on='" + created_on + '\'' +
                ", end_time='" + end_time + '\'' +
                ", orderInfoList=" + orderInfoList +
                ", id='" + id + '\'' +
                '}';
    }
}
