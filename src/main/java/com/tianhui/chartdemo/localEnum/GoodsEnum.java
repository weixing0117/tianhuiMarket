package com.tianhui.chartdemo.localEnum;

public enum GoodsEnum {

    STATUS_ONSALE(11,"在售"),
    STATUS_PRESALE(12,"预售"),
    STATUS_OFFSALE(13,"停售");

    private Integer status;

    private String status_cn;

    GoodsEnum (Integer status,String status_cn){
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