package com.tianhui.chartdemo.annotation;



import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Operation {
    @NotNull
    String type();
    @NotNull
    String info();
}
