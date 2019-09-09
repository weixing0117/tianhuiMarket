package com.tianhui.chartdemo.bean;

import java.math.BigDecimal;

public class OrderInfoBean {
    private Integer goods_id;
    private String goods_name;
    private String goods_unit;
    private String goods_subject;
    private Integer quantity;
    private BigDecimal unit_price;
    private String goods_icon;
    private BigDecimal count_price;

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_unit() {
        return goods_unit;
    }

    public void setGoods_unit(String goods_unit) {
        this.goods_unit = goods_unit;
    }

    public String getGoods_subject() {
        return goods_subject;
    }

    public void setGoods_subject(String goods_subject) {
        this.goods_subject = goods_subject;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public String getGoods_icon() {
        return goods_icon;
    }

    public void setGoods_icon(String goods_icon) {
        this.goods_icon = goods_icon;
    }

    public BigDecimal getCount_price() {
        return count_price;
    }

    public void setCount_price(BigDecimal count_price) {
        this.count_price = count_price;
    }
}
