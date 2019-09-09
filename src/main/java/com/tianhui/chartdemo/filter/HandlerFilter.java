package com.tianhui.chartdemo.filter;

import com.google.gson.Gson;
import com.tianhui.chartdemo.common.ResponseData;
import com.tianhui.chartdemo.dao.IUserMapper;
import com.tianhui.chartdemo.localEnum.ResponseEnum;
import com.tianhui.chartdemo.utils.ContextUtil;
import com.tianhui.chartdemo.utils.EncryptUtil;
import com.tianhui.chartdemo.utils.ThreadLocalUtil;
import com.tianhui.chartdemo.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class HandlerFilter implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(HandlerFilter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if (StringUtils.isEmpty(request.getHeader("x-auth-token"))){
                PrintWriter printWriter = response.getWriter();
                response.setStatus(401);
                printWriter.write(new Gson().toJson(ResponseData.build(ResponseEnum.NO_AUTHORIZATION,null)));
                return  false;
            }
            IUserMapper userMapper = (IUserMapper) ContextUtil.getBean(IUserMapper.class);
            UserVO user = userMapper.queryUserById(EncryptUtil.cipherDecrypt(request.getHeader("x-auth-token")),null,null);
            if(null == user){
                PrintWriter printWriter = response.getWriter();
                response.setStatus(401);
                printWriter.write(new Gson().toJson(ResponseData.build(ResponseEnum.NO_AUTHORIZATION,null)));
                return  false;
            }

            ThreadLocalUtil.setUser(user);
            ThreadLocalUtil.setUer_token(request.getHeader("x-auth-token"));

        } catch (Exception e) {
            log.error("HandlerInterceptor Something Error : {}",e.getMessage());
            PrintWriter printWriter = response.getWriter();
            response.setStatus(401);
            printWriter.write(new Gson().toJson(ResponseData.build(ResponseEnum.NO_AUTHORIZATION,null)));
            return false;
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
            ThreadLocalUtil.remove();
    }
}
