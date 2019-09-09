package com.tianhui.chartdemo.aop;

import com.tianhui.chartdemo.annotation.Operation;
import com.tianhui.chartdemo.utils.ThreadLocalUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;

@Component
@Aspect
public class CommonAspect {
    private static final Logger log = LoggerFactory.getLogger(CommonAspect.class);

    @AfterReturning(value = "@annotation(com.tianhui.chartdemo.annotation.Operation)",returning = "result")
    private void getOperation(JoinPoint joinPoint, Object result){
        Operation operation = getOperation(joinPoint.getSignature());
        System.out.println(operation);
        //log.info(ThreadLocalUtil.getUser().getNickname(),operation.info());
    }

    private Operation getOperation(Signature signature){
        Method method = ((MethodSignature)signature).getMethod();
        return method.getAnnotation(Operation.class);
    }
}
