package com.tianhui.chartdemo.controller;

import com.tianhui.chartdemo.base.BasicController;
import com.tianhui.chartdemo.bean.BasketEntity;
import com.tianhui.chartdemo.common.ResponseData;
import com.tianhui.chartdemo.service.IBasketService;
import com.tianhui.chartdemo.utils.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/basket")
@Api(tags="购物车管理")
public class ShopTrolleyController extends BasicController {
    @Resource
    IBasketService basketService;

    @PostMapping("/goods/insert")
    @ApiOperation(value = "商品放入购物车",notes = "支持多个时以英文,隔开")
    public ResponseData insertGoods(@RequestBody Map<String,Object> goodsInfo){
        if(!goodsInfo.containsKey("goodsId")|| StringUtils.isEmpty(goodsInfo.get("goodsId").toString())){
            return undefinedConvert("未选中商品");
        }
        boolean result;
        try {
            result = basketService.insertIntoBasket(Integer.valueOf(goodsInfo.get("goodsId").toString()));
        } catch (NumberFormatException e) {
            return undefinedConvert("参数错误");
        }
        if(result){
            return successConvert("成功加入购物车");
        }
        return customizeConvert(400,"failed to insert","加入购物车失败");
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新购物车")
    public ResponseData updateBasket(@RequestBody List<BasketEntity> basketEntityList){

        boolean result = basketService.updateBasket(basketEntityList);
        if(result){
            return successConvert("成功更新购物车");
        }
        return customizeConvert(400,"failed to insert","更新购物车失败");
    }

    @GetMapping("/query")
    @ApiOperation(value = "查询我的购物车",notes = "支持多个时以英文,隔开")
    public ResponseData queryBasket(@RequestParam(value = "pageNo",required = false)Integer pageNo,@RequestParam(value = "pageSize",required = false)Integer pageSize){
        if(pageNo !=null && pageSize != null){
            if(pageNo <1 || pageSize<1){
                return undefinedConvert("分页参数错误");
            }
        }
        List<BasketEntity> result = basketService.queryBasket(ThreadLocalUtil.getUser().getId());
        Integer count = basketService.basketCount(ThreadLocalUtil.getUser().getId());
        return successConvert(constructResult(pageNo,pageSize,count, result));
    }

}
