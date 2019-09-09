package com.tianhui.chartdemo.controller;

import com.tianhui.chartdemo.base.BasicController;
import com.tianhui.chartdemo.bean.AddressEntity;
import com.tianhui.chartdemo.common.Constants;
import com.tianhui.chartdemo.common.ResponseData;
import com.tianhui.chartdemo.service.IUserService;
import com.tianhui.chartdemo.service.IAddressService;
import com.tianhui.chartdemo.utils.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

@RestController
@RequestMapping("/v1/address")
@Api(tags="用户地址管理")
public class AddressController extends BasicController {
    private static final Logger log = LoggerFactory.getLogger(AddressController.class);
    @Resource
    IAddressService addressService;
    @Resource
    IUserService userService;

    @PostMapping("/insert")
    @ApiOperation(value = "新增地址")
    public ResponseData insertAddress(@RequestBody AddressEntity addressEntity){
        try {
            if(addressService.queryUsersCount() >= Constants.USER_ADDRESS_COUNT){
                return customizeConvert(400,"your address was packed","新增失败，已达上限");
            }
            if(addressService.insertAddress(addressEntity)){
                return successConvert("新增地址成功");
            }
        } catch (Exception e) {
            log.error("新增地址失败: {}",e.getMessage());
        }
        return customizeConvert(400,"failed to insert","新增地址失败");
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改地址")
    public ResponseData updateAddress( @RequestBody AddressEntity addressEntity){
        try {
            AddressEntity getById = addressService.queryById(addressEntity.getId());
            if(null == getById){
                return undefinedConvert("地址不存在");
            }
            if(addressService.updateAddress(addressEntity)){
                return successConvert("修改地址成功");
            }
        } catch (Exception e) {
            log.error("修改地址失败: {}",e.getMessage());
        }
        return customizeConvert(400,"failed to update","修改地址失败");
    }

    @GetMapping("/query")
    @ApiOperation(value = "查询地址")
    public ResponseData queryUserAddress(){
        return successConvert(addressService.queryAddressByUserId(ThreadLocalUtil.getUser().getId()));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    public ResponseData deleteAddress(@ApiParam(name = "addressIds",value = "地址id(字符串)；以英文,隔开")@RequestBody String addressIds){
        try {
            if(addressService.deleteAddress(Arrays.asList(addressIds.split(",")))){
                return successConvert("删除地址成功");
            }
        } catch (Exception e) {
            log.error("删除地址失败: {}",e.getMessage());
        }
        return customizeConvert(400,"failed to delete","地址删除失败");
    }

    @PutMapping("/update/userCommon")
    @ApiOperation(value = "设为常用地址")
    public ResponseData commonAddress(@ApiParam(name = "addressId",value = "地址id")@RequestBody String addressId){
        try {

            if(userService.updateCommonAddress(ThreadLocalUtil.getUser().getId(),addressId)){
                return successConvert("常用地址修改成功");
            }
        } catch (Exception e) {
            log.error("常用地址修改失败: {}",e.getMessage());
        }
        return customizeConvert(400,"failed to update","常用地址修改失败");
    }

}
