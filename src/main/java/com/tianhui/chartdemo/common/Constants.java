package com.tianhui.chartdemo.common;

public class Constants {
    public static final String GOODS_NAME_REG = "[a-zA-Z0-9_\u4e00-\u9fa5]{1,30}";

    public static final String USER_NAME_REG = "[a-zA-Z0-9_\u4e00-\u9fa5]{5,20}";

    public static final String USER_PASSWORD_REG = "[a-zA-Z0-9_!@#$%^&*(),.?~`{}:<>;\\s]{6,20}";

    public static final String GOODS_SORT_REG = "desc|asc|DESC|ASC";

    public static final String GOODS_SORTKEY_REG = "name|status|stock|start_time|end_time|type|created_on";

    public static final Integer USER_ADDRESS_COUNT = 6;

    public static final String XSS_SCRIPT = "<script>(.*?)</script>";

    public static final String XSS_SRC = "src[\r\n]*=[\r\n]*\\\'(.*?)\\\'";

    public static final String XSS_END_SCRIPT = "</script>";

    public static final String XSS_BEGIN_SCRIPT = "<script(.*?)>";

    public static final String XSS_EVAL = "eval\\((.*?)\\)";

    public static final String XSS_EXPR = "expression\\((.*?)\\)";

    public static final String XSS_JAVASCRIPT = "javascript:";

    public static final String XSS_VBS = "vbscript:";

    public static final String XSS_ONLOAD = "onload(.*?)=";

    public static final String XSS_ON = "on.*(.*?)=";
}
