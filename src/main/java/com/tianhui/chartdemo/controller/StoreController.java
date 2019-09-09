package com.tianhui.chartdemo.controller;

import com.tianhui.chartdemo.base.BasicController;
import com.tianhui.chartdemo.localEnum.GoodsEnum;
import com.tianhui.chartdemo.po.GoodsPO;
import com.tianhui.chartdemo.common.Constants;
import com.tianhui.chartdemo.common.ResponseData;
import com.tianhui.chartdemo.exception.GoodsException;
import com.tianhui.chartdemo.service.IBasicGoodsService;
import com.tianhui.chartdemo.utils.PageUtil;
import com.tianhui.chartdemo.utils.PatternUtil;
import com.tianhui.chartdemo.vo.GoodsVO;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/v1/store")
@Api(tags="仓库管理")
public class StoreController extends BasicController {
    private static final Logger log = LoggerFactory.getLogger(StoreController.class);
    @Resource
    IBasicGoodsService goodsService;


    @GetMapping("/base/query")
    @ApiOperation(value = "查询商品详情",notes = "支持多个时以英文,隔开")
    public ResponseData queryGoods(
            @ApiParam(name = "id",value = "商品id，若传入则查询单个")@RequestParam(value = "id",required = false) Integer id,
            @ApiParam(name = "name",value = "商品名称,模糊查询")@RequestParam(value = "name",required = false) String name,
            @ApiParam(name = "type",value = "商品类型，支持多个")@RequestParam(value = "type",required = false) String type,
            @ApiParam(name = "status",value = "商品状态，支持多个")@RequestParam(value = "status",required = false) String status,
            @ApiParam(name = "queryAll",value = "是否查询被删除的商品")@RequestParam(value = "queryAll",required = false) boolean queryAll,
            @ApiParam(name = "sortKey",value = "排序字段")@RequestParam(value = "sortKey",required = false) String sortKey,
            @ApiParam(name = "sort",value = "排序规则(asc/desc)")@RequestParam(value = "sort",required = false) String sort,
            @ApiParam(name = "startTime",value = "起售时间")@RequestParam(value = "startTime",required = false) String startTime,
            @ApiParam(name = "endTime",value = "截止时间")@RequestParam(value = "endTime",required = false) String endTime,
            @RequestParam(value = "pageNo",required = false)Integer pageNo,@RequestParam(value = "pageSize",required = false)Integer pageSize){

        if (id !=null && id >0){
            return successConvert(constructResult(pageNo,pageSize,null,goodsService.queryGoodsInfo(id,queryAll)));
        }
        if(pageNo !=null && pageSize != null){
            if(pageNo <1 || pageSize<1){
                return undefinedConvert("分页参数错误");
            }
        }

        Integer count = goodsService.queryGoodsCount(id,name,type,status,queryAll,startTime,endTime);
        List<GoodsVO> goodsVOList = goodsService.queryGoods(name,type,status, PageUtil.getStartPosition(pageSize,pageNo,count),pageSize,queryAll,sortKey,sort,startTime,endTime);
        return successConvert(constructResult(pageNo,pageSize,count, goodsVOList));
    }
    @PostMapping("/base/create")
    @ApiOperation(value = "添加商品基础信息",notes = "无法定义售价、库存和状态")
    public ResponseData createGoods(@RequestBody GoodsPO goodsPO){
        if(!isAdmin()){
            return noAuthConvert("没有权限");
        }
        if(!PatternUtil.isMatch(Constants.GOODS_NAME_REG, goodsPO.getName())){
            return undefinedConvert("商品名称不合法");
        }
        boolean result = false;
        try {
            result = goodsService.createGoods(goodsPO);
        } catch (GoodsException e) {
            return undefinedConvert(e.getMessage());
        }
        if (result){
            return successConvert(goodsPO);
        }
        return customizeConvert(400,"failed to create","商品创建失败");
    }

    @PutMapping("/base/update")
    @ApiOperation(value = "修改商品基础信息",notes = "无法修改售价、库存和状态")
    public ResponseData updateGoods(@RequestBody GoodsPO goodsPO){
        if(!isAdmin()){
            return noAuthConvert("没有权限");
        }
        if(!StringUtils.isEmpty(goodsPO.getName())){
            if(!PatternUtil.isMatch(Constants.GOODS_NAME_REG, goodsPO.getName())){
                return undefinedConvert("商品名称不合法");
            }
        }
        boolean result;
        try {
         result = goodsService.updateGoods(goodsPO);
        } catch (GoodsException e) {
            return undefinedConvert(e.getMessage());
        }
        if (result){
            return successConvert(goodsPO);
        }
        return customizeConvert(400,"failed to update","商品修改失败");
    }

    @DeleteMapping("/base/delete")
    @ApiOperation(value = "删除商品")
    public ResponseData deleteGoods(@ApiParam(name = "id",value = "商品id,支持多个时以英文,隔开")@RequestParam(value = "id",required = false) String ids){
        if(!isAdmin()){
            return noAuthConvert("没有权限");
        }
        Integer result = 0;
        try {
            result = goodsService.deleteGoods(ids);
        } catch (GoodsException e) {
            return undefinedConvert(e.getMessage());
        }
        if (result>0){
            return successConvert("成功删除"+result+"件商品");
        }
        return customizeConvert(400,"no such goods","商品删除失败");
    }

    @PutMapping("/base/resume")
    @ApiOperation(value = "恢复商品")
    public ResponseData resumeGoods(@ApiParam(name = "id",value = "商品id,支持多个时以英文,隔开")@RequestParam(value = "id",required = false) String ids){
        if(!isAdmin()){
            return noAuthConvert("没有权限");
        }
        Integer result = 0;
        try {
            result = goodsService.resumeGoods(ids);
        } catch (GoodsException e) {
            return undefinedConvert(e.getMessage());
        }
        if (result>0){
            return successConvert("成功恢复"+result+"件商品");
        }
        return customizeConvert(400,"no such goods","商品还原失败");
    }

    @PutMapping("/goods/update")
    @ApiOperation(value = "修改商品销售信息",notes = "只能修改售价，库存，时间，状态")
    public ResponseData resumeGoods(@RequestBody GoodsPO goodsPO){
        if(!isAdmin()){
            return noAuthConvert("没有权限");
        }
        boolean result;
        try {
            result = goodsService.updateSaleGoods(goodsPO);
        } catch (GoodsException e) {
            log.error("修改商品失败:{}",e.getMessage());
            return undefinedConvert(e.getMessage());
        }
        if (result){
            return successConvert("修改成功");
        }
        return customizeConvert(400,"no such goods","修改失败");
    }

    @PutMapping("/goods/upperShelf")
    @ApiOperation(value = "商品上架",notes = "不设置截至时间，默认一个月")
    public ResponseData upperShelf(@RequestBody GoodsPO goodsPO){
        if(!isAdmin()){
            return noAuthConvert("没有权限");
        }
        if(null == goodsPO.getStock() || goodsPO.getStock()<1){
            return customizeConvert(400,"stock can't be 0","商品数量不能为0");
        }
        if(goodsPO.getStatus() == GoodsEnum.STATUS_ONSALE.getStatus()){
            return customizeConvert(400,"Goods Upper Shelf Already","商品已上架");
        }
        goodsPO.setStatus(GoodsEnum.STATUS_ONSALE.getStatus());
        boolean result;
        try {
            result = goodsService.updateSaleGoods(goodsPO);
        } catch (GoodsException e) {
            return undefinedConvert(e.getMessage());
        }
        if (result){
            return successConvert("上架成功");
        }
        return customizeConvert(400,"no such goods","上架失败");
    }

    @PutMapping("/goods/advanceSale")
    @ApiOperation(value = "商品预售",notes = "不设置截至时间，默认一个月")
    public ResponseData advanceSale(@RequestBody GoodsPO goodsPO){
        if(!isAdmin()){
            return noAuthConvert("没有权限");
        }
        if(goodsPO.getStatus() == GoodsEnum.STATUS_PRESALE.getStatus()){
            return customizeConvert(400,"Goods Advance Sale Already","商品已开始预售");
        }
        if(goodsPO.getStatus() == GoodsEnum.STATUS_ONSALE.getStatus()){
            return customizeConvert(400,"Goods Advance Sale Already","商品已上架" );
        }
        if(null == goodsPO.getStock() || goodsPO.getStock()<1){
            return customizeConvert(400,"stock can't be 0","商品数量不能为0");
        }
        goodsPO.setStatus(GoodsEnum.STATUS_PRESALE.getStatus());
        boolean result;
        try {
            result = goodsService.updateSaleGoods(goodsPO);
        } catch (GoodsException e) {
            return undefinedConvert(e.getMessage());
        }
        if (result){
            return successConvert("预售成功");
        }
        return customizeConvert(400,"no such goods","预售失败");
    }


    @PutMapping("/goods/downShelf")
    @ApiOperation(value = "商品下架")
    public ResponseData downShelf(@RequestBody GoodsPO goodsPO){
        if(!isAdmin()){
            return noAuthConvert("没有权限");
        }
        if(goodsPO.getStatus() == GoodsEnum.STATUS_OFFSALE.getStatus()){
            return customizeConvert(400,"Goods Down Shelf Already","商品已下架");
        }
        boolean result;
        goodsPO.setStatus(GoodsEnum.STATUS_OFFSALE.getStatus());
        try {
            result = goodsService.updateSaleGoods(goodsPO);
        } catch (GoodsException e) {
            return undefinedConvert(e.getMessage());
        }
        if (result){
            return successConvert("下架成功");
        }
        return customizeConvert(400,"no such goods","下架失败");
    }

}
