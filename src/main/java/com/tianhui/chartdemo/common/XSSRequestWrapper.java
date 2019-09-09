package com.tianhui.chartdemo.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Pattern;


public class XSSRequestWrapper extends HttpServletRequestWrapper {


    public XSSRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public String[] getParameterValues(String parameter) {

        String[] values = super.getParameterValues(parameter);

        if (values == null) {

            return null;

        }

        int count = values.length;

        String[] encodedValues = new String[count];

        for (int i = 0; i < count; i++) {

            encodedValues[i] = cleanXSS(values[i]);

        }

        return encodedValues;

    }

    public String getParameter(String parameter) {

        String value = super.getParameter(parameter);

        if (value == null) {

            return null;

        }

        return cleanXSS(value);

    }

    public String getHeader(String name) {

        String value = super.getHeader(name);

        if (value == null)

            return null;

        return cleanXSS(value);

    }



    private String cleanXSS(String value) {
        if (value != null) {


            // 避免空字符串
            value = value.replaceAll("", "");

            // 阻止script 标签
            Pattern scriptPattern = Pattern.compile(Constants.XSS_SCRIPT, Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // 阻止src表达式
            scriptPattern = Pattern.compile(Constants.XSS_SRC, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // 阻止单个的 </script> 标签
            scriptPattern = Pattern.compile(Constants.XSS_END_SCRIPT, Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // 阻止单个的<script ...> 标签
            scriptPattern = Pattern.compile(Constants.XSS_BEGIN_SCRIPT, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // 阻止 eval(...) 形式表达式
            scriptPattern = Pattern.compile(Constants.XSS_EVAL, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // 阻止 e­xpression(...) 表达式
            scriptPattern = Pattern.compile(Constants.XSS_EXPR, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // 阻止 javascript: 表达式
            scriptPattern = Pattern.compile(Constants.XSS_JAVASCRIPT, Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // 阻止 vbscript: 表达式
            scriptPattern = Pattern.compile(Constants.XSS_VBS, Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // 阻止 οnlοad= 表达式
            scriptPattern = Pattern.compile(Constants.XSS_ONLOAD, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // 阻止 onXX= 表达式
            scriptPattern = Pattern.compile(Constants.XSS_ON, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");


        }
        return value;
    }
}
