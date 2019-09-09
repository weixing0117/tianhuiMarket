package com.tianhui.chartdemo.controller;

import com.tianhui.chartdemo.base.BasicController;
import com.tianhui.chartdemo.common.Constants;
import com.tianhui.chartdemo.common.ResponseData;
import com.tianhui.chartdemo.service.IUserService;
import com.tianhui.chartdemo.service.IWeChartService;
import com.tianhui.chartdemo.utils.PatternUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/v1/user")
@Api(tags="登录")
public class PlatformUserController extends BasicController {
    private static final Logger log = LoggerFactory.getLogger(PlatformUserController.class);

    @Resource
    IUserService userService;

    @Resource
    IWeChartService weChartService;


    @GetMapping("/weChart/authenticate")
    @ApiOperation(value = "微信用户验证",notes = "依据code创建或更新用户")
    public ResponseData createUser(@ApiParam(name = "userCode",value = "用户微信code") @RequestParam(value = "userCode",required = true)String userCode){

        String tokenStr = weChartService.getToken(userCode);
        if(StringUtils.isEmpty(tokenStr)){
            return customizeConvert(400,"failed to authenticate","验证失败");
        }
        return successConvert(tokenStr);
    }

    @PostMapping("/login/authenticate")
    @ApiOperation(value = "用户登录",notes = "依据账号密码登录获取token")
    public ResponseData queryUser(@RequestBody Map<String,Object> loginParam){
        if(null == loginParam || !loginParam.containsKey("th_username") || !loginParam.containsKey("th_password")
                || StringUtils.isEmpty(loginParam.get("th_username").toString()) || StringUtils.isEmpty(loginParam.get("th_password").toString())){
            return undefinedConvert("参数错误");
        }
        if(!PatternUtil.isMatch(Constants.USER_NAME_REG,loginParam.get("th_username").toString())){
            return undefinedConvert("用户名不合法");
        }
        if(!PatternUtil.isMatch(Constants.USER_PASSWORD_REG,loginParam.get("th_password").toString())){
            return undefinedConvert("密码错误");
        }

        String result = userService.login(loginParam.get("th_username").toString(),loginParam.get("th_password").toString());

        if(StringUtils.isEmpty(result)){
            return noAuthConvert("用户名密码错误");
        }

        return successConvert(result);

    }




}
