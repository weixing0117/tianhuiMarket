package com.tianhui.chartdemo.po;

import com.tianhui.chartdemo.base.BasicGoodsEntity;

public class GoodsPO extends BasicGoodsEntity {
    private Integer type;

    private Integer status;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  super.toString()+"GoodsPO{" +
                "type=" + type +
                ", status=" + status +
                '}';
    }
}
