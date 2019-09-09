package com.tianhui.chartdemo.localEnum;

public enum OperationEnum {
    CREATE("create","创建"),
    UPDATE("update","修改"),
    DELETE("delete","删除"),
    RESUME("resume","恢复");
    private String operation;

    private String msg;

    OperationEnum (String operation,String msg){
        this.operation = operation;
        this.msg = msg;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
