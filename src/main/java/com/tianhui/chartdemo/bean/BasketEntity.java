package com.tianhui.chartdemo.bean;

import java.math.BigDecimal;

public class BasketEntity {
    private Integer goods_id;
    private Integer total_number;
    private String goods_name;
    private String goods_subject;
    private String goods_icon;
    private String goods_type;
    private String goods_unit;
    private BigDecimal goods_current_price;
    private String user_id;

    public Integer getTotal_number() {
        return total_number;
    }

    public void setTotal_number(Integer total_number) {
        this.total_number = total_number;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_subject() {
        return goods_subject;
    }

    public void setGoods_subject(String goods_subject) {
        this.goods_subject = goods_subject;
    }

    public String getGoods_icon() {
        return goods_icon;
    }

    public void setGoods_icon(String goods_icon) {
        this.goods_icon = goods_icon;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getGoods_unit() {
        return goods_unit;
    }

    public void setGoods_unit(String goods_unit) {
        this.goods_unit = goods_unit;
    }

    public BigDecimal getGoods_current_price() {
        return goods_current_price;
    }

    public void setGoods_current_price(BigDecimal goods_current_price) {
        this.goods_current_price = goods_current_price;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }
}
