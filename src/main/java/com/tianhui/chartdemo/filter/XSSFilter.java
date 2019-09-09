package com.tianhui.chartdemo.filter;

import com.tianhui.chartdemo.common.XSSRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter(filterName = "xssFilter", urlPatterns = "/*", asyncSupported = true)
public class XSSFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        XSSRequestWrapper xssRequestWrapper = new XSSRequestWrapper((HttpServletRequest)servletRequest);
        filterChain.doFilter(xssRequestWrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
