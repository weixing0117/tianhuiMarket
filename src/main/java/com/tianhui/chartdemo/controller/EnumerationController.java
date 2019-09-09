package com.tianhui.chartdemo.controller;

import com.tianhui.chartdemo.base.BasicController;
import com.tianhui.chartdemo.bean.EnumerationEntity;
import com.tianhui.chartdemo.common.ResponseData;
import com.tianhui.chartdemo.service.IEnumerationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/enum")
@Api(tags="枚举列表管理")
public class EnumerationController extends BasicController {
    private static final Logger log = LoggerFactory.getLogger(EnumerationController.class);
    @Resource
    IEnumerationService enumerationService;


    @PostMapping("/create")
    @ApiOperation(value = "创建",notes = "根据不同类型，加入子集")
    public ResponseData createEnum(@RequestBody EnumerationEntity enumerationEntity){
        if(!isAdmin()){
            return noAuthConvert("没有权限");
        }
        boolean result = enumerationService.createEnum(enumerationEntity);
        if(result){
            return successConvert("创建成功");
        }
        return customizeConvert(400,"failed to update","枚举创建失败");
    }

    @GetMapping("/query")
    @ApiOperation(value = "查询枚举",notes = "")
    public ResponseData queryEnum(
            @ApiParam(name = "id",value = "枚举id")@RequestParam(value = "id",required = false) Integer id,
            @ApiParam(name = "type",value = "枚举类型")@RequestParam(value = "type",required = false) String type){
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        params.put("type",type);
        List<EnumerationEntity> enumerationEntityList = enumerationService.queryEnum(params);
        return successConvert(enumerationEntityList);
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改",notes = "根据不同类型，修改子集")
    public ResponseData updateEnum(@RequestBody EnumerationEntity enumerationEntity){
        if(!isAdmin()){
            return noAuthConvert("没有权限");
        }
        boolean result = enumerationService.updateEnum(enumerationEntity);
        if(result){
            return successConvert("修改成功");
        }
        return customizeConvert(400,"failed to update","枚举修改失败");
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除",notes = "若有关联，则不能删除")
    public ResponseData deleteEnum(
            @ApiParam(name = "id",value = "枚举id")@RequestParam(value = "id",required = false) Integer id){
        if(!isAdmin()){
            return noAuthConvert("没有权限");
        }
        boolean result = enumerationService.deleteEnum(id);
        if (result){
            return successConvert("删除成功");
        }
        return customizeConvert(400,"failed to update","枚举删除失败");
    }
}

