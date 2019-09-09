package com.tianhui.chartdemo.common;

import com.tianhui.chartdemo.localEnum.ResponseEnum;

public class ResponseData {

    private Integer code;

    private String status;

    private String msg;

    private Object result;

    private ResponseData(){};

    private ResponseData(ResponseEnum responseEnum, Object data){
        this.code = responseEnum.getCode();
        if(200==this.code){
            this.status = "success";
        }else{
            this.status = "failure";
        }
        this.msg = responseEnum.getMsg();
        this.result = data;
    }

    private ResponseData(Integer code,String msg,Object data){
        this.code = code;
        if(200==this.code){
            this.status = "success";
        }else{
            this.status = "failure";
        }
        this.msg = msg;
        this.result = data;
    }

    public static ResponseData build(ResponseEnum responseEnum,Object data){
        return new ResponseData(responseEnum,data);
    }

    public static ResponseData build(Integer code,String msg,Object data){
        return new ResponseData(code,msg,data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "code=" + code +
                ", status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
