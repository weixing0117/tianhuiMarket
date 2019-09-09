package com.tianhui.chartdemo.controller;

import com.tianhui.chartdemo.base.BasicController;
import com.tianhui.chartdemo.bean.InsertOrder;
import com.tianhui.chartdemo.bean.InsertOrderBean;
import com.tianhui.chartdemo.common.ResponseData;
import com.tianhui.chartdemo.localEnum.GoodsEnum;
import com.tianhui.chartdemo.localEnum.OrderEnum;
import com.tianhui.chartdemo.po.OrderPO;
import com.tianhui.chartdemo.service.IOrderService;
import com.tianhui.chartdemo.utils.PageUtil;
import com.tianhui.chartdemo.utils.ThreadLocalUtil;
import com.tianhui.chartdemo.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/order")
@Api(tags="订单管理")
public class OrderController extends BasicController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    @Resource
    IOrderService orderService;

    @PostMapping("/create")
    @ApiOperation(value = "提交订单",notes = "依据购物车生成用户订单")
    public ResponseData createOrder(@RequestBody InsertOrder insertOrder){
        if(null == insertOrder || null ==insertOrder.getInsertOrderBeanList() || insertOrder.getInsertOrderBeanList().size()<1){
            return undefinedConvert("订单不能为空");
        }
        try {
            if(orderService.createOrder(insertOrder)>0){
                return successConvert("成功生成订单");
            }
        } catch (Exception e) {
            return customizeConvert(400,"failed to create",e.getMessage());
        }
        return customizeConvert(400,"failed to create","订单提交失败");
    }

    @GetMapping("/update/info")
    @ApiOperation(value = "用户修改订单",notes = "修改订单备注信息/收货信息")
    public ResponseData updateOrderInfo(@RequestBody  OrderPO orderPO){
        OrderPO curPO = orderService.queryBaseOrder(orderPO.getId());
        if(null == curPO){
            return undefinedConvert("订单不存在");
        }
        if(!isAdmin() && curPO.getUser_id() != ThreadLocalUtil.getUser().getId()){
            return noAuthConvert("非本人不能操作");
        }
        if(OrderEnum.SUCCEED.getStatus() == curPO.getStatus() || OrderEnum.RECEIVED.getStatus() == curPO.getStatus()){

            orderService.updateOrderInfoForUser(orderPO);
            return successConvert("订单修改成功");
        }
        return customizeConvert(400,"failed to update","订单修改失败");
    }

    @GetMapping("/query/myOrders")
    @ApiOperation(value = "查询我的订单",notes = "查询我的订单")
    public ResponseData queryMyOrders(@ApiParam(name = "id",value = "订单id")@RequestParam(value = "id",required = false)String id,
                                      @ApiParam(name = "status",value = "订单状态")@RequestParam(value = "status",required = false) Integer status,
                                      @RequestParam(value = "pageNo",required = false)Integer pageNo,@RequestParam(value = "pageSize",required = false)Integer pageSize){

        if(pageNo !=null && pageSize != null){
            if(pageNo <1 || pageSize<1){
                return undefinedConvert("分页参数错误");
            }
        }
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        params.put("status",status);
        params.put("user_id", ThreadLocalUtil.getUser().getId());
        Integer count = orderService.queryOrderCount(params);
        params.put("start",PageUtil.getStartPosition(pageSize,pageNo,count));
        params.put("pageSize",pageSize);
        return successConvert(constructResult(pageNo,pageSize,count,orderService.queryOrder(params)));

    }


    @GetMapping("/query")
    @ApiOperation(value = "查询订单",notes = "分页查询订s单")
    public ResponseData queryOrder(@ApiParam(name = "id",value = "订单id")@RequestParam(value = "id",required = false)String id,
                                   @ApiParam(name = "userName",value = "用户昵称(模糊查询)")@RequestParam(value = "userName",required = false) String userName,
                                   @ApiParam(name = "contact",value = "联系人(模糊查询)")@RequestParam(value = "contact",required = false) String contact,
                                   @ApiParam(name = "telephone",value = "联系电话(配送时电话非用户电话，精确匹配)")@RequestParam(value = "telephone",required = false) String telephone,
                                   @ApiParam(name = "status",value = "订单状态")@RequestParam(value = "status",required = false) Integer status,
                                   @ApiParam(name = "sortKey",value = "排序字段")@RequestParam(value = "sortKey",required = false) String sortKey,
                                   @ApiParam(name = "sort",value = "排序规则(asc/desc)")@RequestParam(value = "sort",required = false) String sort,
                                   @RequestParam(value = "pageNo",required = false)Integer pageNo,@RequestParam(value = "pageSize",required = false)Integer pageSize){

        if(!isAdmin()){
            return noAuthConvert("没有权限");
        }
        if(pageNo !=null && pageSize != null){
            if(pageNo <1 || pageSize<1){
                return undefinedConvert("分页参数错误");
            }
        }
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        params.put("status",status);
        params.put("user_name",userName);
        params.put("contact",contact);
        params.put("telephone",telephone);
        Integer count = orderService.queryOrderCount(params);
        params.put("start",PageUtil.getStartPosition(pageSize,pageNo,count));
        params.put("pageSize",pageSize);
        return successConvert(constructResult(pageNo,pageSize,count,orderService.queryOrder(params)));

    }


    @PutMapping("/update/status")
    @ApiOperation(value = "修改订单状态",notes = "仅管理员可修改")
    public ResponseData updateOrderStatus(@RequestBody OrderPO orderPO){
        if(!isAdmin()){
            return noAuthConvert("没有权限");
        }
        OrderPO curPO = orderService.queryBaseOrder(orderPO.getId());
        if(null == curPO || curPO.getStatus() == OrderEnum.DONE.getStatus() || curPO.getStatus() == OrderEnum.CANCELED.getStatus() || orderPO.getStatus() == OrderEnum.CANCELED.getStatus()){
            return  undefinedConvert("此状态不能修改");
        }
        boolean result = orderService.updateOrderStatus(orderPO);
        if(result){
            return  successConvert("修改成功");
        }
        return  customizeConvert(400,"failed to update","订单修改失败");
    }

    @PutMapping("/cancel")
    @ApiOperation(value = "撤销订单",notes = "撤销订单")
    public ResponseData cancelOrder(@RequestBody OrderPO orderPO){
        OrderPO curPO = orderService.queryBaseOrder(orderPO.getId());
        if(null == curPO || curPO.getStatus() == OrderEnum.DONE.getStatus() || curPO.getStatus() == OrderEnum.CANCELED.getStatus()){
            return  undefinedConvert("此状态不能撤销");
        }
        if(!isAdmin() && curPO.getUser_id() != ThreadLocalUtil.getUser().getId()){
                return noAuthConvert("非本人不能操作");
        }
        try {
            if(orderService.cancelOrder(orderPO)){
                return  successConvert("撤销成功");
            }
        } catch (Exception e) {
            return  customizeConvert(400,"failed to update","撤销订单失败");
        }

        return  customizeConvert(400,"failed to update","撤销订单失败");
    }
}
