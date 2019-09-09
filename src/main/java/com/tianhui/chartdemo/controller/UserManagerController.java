package com.tianhui.chartdemo.controller;

import com.tianhui.chartdemo.base.BasicController;
import com.tianhui.chartdemo.common.Constants;
import com.tianhui.chartdemo.common.ResponseData;
import com.tianhui.chartdemo.po.UserPO;
import com.tianhui.chartdemo.service.IUserService;
import com.tianhui.chartdemo.utils.PageUtil;
import com.tianhui.chartdemo.utils.PatternUtil;
import com.tianhui.chartdemo.utils.ThreadLocalUtil;
import com.tianhui.chartdemo.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/v1/userManager")
@Api(tags="用户管理")
public class UserManagerController extends BasicController {
    private static final Logger log = LoggerFactory.getLogger(UserManagerController.class);

    @Resource
    IUserService userService;


    @PostMapping("/create")
    @ApiOperation(value = "创建用户",notes = "创建用户")
    public ResponseData createUser( @ApiParam(name = "用户实体",value = "用户实体")@RequestBody UserPO userPO){
        if(!PatternUtil.isMatch(Constants.USER_NAME_REG,userPO.getName())){
            return undefinedConvert("用户名不合法");
        }
        if(!PatternUtil.isMatch(Constants.USER_PASSWORD_REG,userPO.getPassword())){
            return undefinedConvert("密码不合法");
        }

        boolean result = userService.createUser(userPO);
        if(result){
            return successConvert("创建成功");
        }
        return customizeConvert(400,"failed to create","用户创建失败");
    }

    @GetMapping("/query")
    @ApiOperation(value = "查询用户信息",notes = "依据id，name,telephone")
    public ResponseData queryUser( @ApiParam(name = "id",value = "用户id")@RequestParam(value = "id",required = false) Long id,
                                   @ApiParam(name = "name",value = "用户名称")@RequestParam(value = "name",required = false) String name,
                                   @ApiParam(name = "telephone",value = "用户电话")@RequestParam(value = "telephone",required = false) Integer telephone,
                                   @ApiParam(name = "openId",value = "微信ID")@RequestParam(value = "openId",required = false) String openId,
                                   @RequestParam(value = "pageNo",required = false)Integer pageNo,@RequestParam(value = "pageSize",required = false)Integer pageSize
                                   ){
        if(id == null && telephone==null && StringUtils.isEmpty(openId)){
            if(!isAdmin()){
                return successConvert(ThreadLocalUtil.getUser());
            }
        }

        if(pageNo !=null && pageSize != null){
            if(pageNo <1 || pageSize<1){
                return undefinedConvert("分页参数错误");
            }
        }

        Integer count = userService.queryUserCount(id,name,telephone,openId);

        List<UserVO> userVOList = userService.queryUser(id,name,telephone, openId,PageUtil.getStartPosition(pageSize,pageNo,count),pageSize);

        return successConvert(constructResult(pageNo,pageSize,count, userVOList));

    }

    @PutMapping("/update")
    @ApiOperation(value = "修改用户信息",notes = "依据id，name,telephone")
    public ResponseData updateUser( @ApiParam(name = "用户实体",value = "用户实体")@RequestBody UserPO userPO){
        boolean result = userService.updateUser(userPO);
        if(result){
            return successConvert("修改成功");
        }
        return customizeConvert(400,"failed to update","用户修改失败");
    }
}
