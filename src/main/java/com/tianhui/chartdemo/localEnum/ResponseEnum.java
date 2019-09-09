package com.tianhui.chartdemo.localEnum;

public enum ResponseEnum {

    SUCCESS(200, "ok"),
    UNDEFINED(400, "Request param undefined"),
    NO_AUTHORIZATION(401, "No Authorization");

    private Integer code;

    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
