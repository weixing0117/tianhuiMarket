package com.tianhui.chartdemo.base;

import java.math.BigDecimal;

public class BasicGoodsEntity {
    private Integer id;
    private String name;
    private String subject;
    private String icon;
    private String detail_desc;
    private String unit;
    private Integer stock;
    private BigDecimal default_price;
    private BigDecimal current_price;
    private String start_time;
    private String end_time;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDetail_desc() {
        return detail_desc;
    }

    public void setDetail_desc(String detail_desc) {
        this.detail_desc = detail_desc;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getDefault_price() {
        return default_price;
    }

    public void setDefault_price(BigDecimal default_price) {
        this.default_price = default_price;
    }

    public BigDecimal getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(BigDecimal current_price) {
        this.current_price = current_price;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "BasicGoodsEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", icon='" + icon + '\'' +
                ", detail_desc='" + detail_desc + '\'' +
                ", unit='" + unit + '\'' +
                ", stock=" + stock +
                ", default_price=" + default_price +
                ", current_price=" + current_price +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                '}';
    }
}
