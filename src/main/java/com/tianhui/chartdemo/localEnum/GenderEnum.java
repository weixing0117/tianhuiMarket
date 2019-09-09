package com.tianhui.chartdemo.localEnum;

public enum GenderEnum {
    FEMALE(2,"女性"),
    MAN(1,"男性"),
    UNKNOWN(0,"未知");

    private Integer key;

    private String value;

    GenderEnum (Integer key,String value){
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
