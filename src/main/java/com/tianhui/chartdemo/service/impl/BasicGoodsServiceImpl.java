package com.tianhui.chartdemo.service.impl;

import com.tianhui.chartdemo.annotation.Operation;
import com.tianhui.chartdemo.bean.InsertOrderBean;
import com.tianhui.chartdemo.common.GoodsStore;
import com.tianhui.chartdemo.po.GoodsPO;
import com.tianhui.chartdemo.common.Constants;
import com.tianhui.chartdemo.dao.IBasicGoodsMapper;
import com.tianhui.chartdemo.exception.GoodsException;
import com.tianhui.chartdemo.service.IBasicGoodsService;
import com.tianhui.chartdemo.utils.PatternUtil;
import com.tianhui.chartdemo.vo.GoodsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BasicGoodsServiceImpl implements IBasicGoodsService {
    private static final Logger log = LoggerFactory.getLogger(BasicGoodsServiceImpl.class);
    @Resource
    IBasicGoodsMapper goodsMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createGoods(GoodsPO goodsPO) throws GoodsException{
        try {
            boolean result = goodsMapper.createOrUpdateBasicGoods(goodsPO);
            GoodsStore.getInstance().reMakeGoods();
            return result;
        } catch (Exception e) {
            log.error("创建商品失败: for {{}}",e.getMessage());
            throw new GoodsException("商品创建失败");
        }
    }

    @Override
    @Operation(type = "update",info = "修改商品")
    public boolean updateGoods(GoodsPO goodsPO) {
        try {
            boolean result = goodsMapper.createOrUpdateBasicGoods(goodsPO);
            return result;
        } catch (Exception e) {
            log.error("更新商品失败: for {{}}",e.getMessage());
            throw new GoodsException("商品更新失败");
        }
    }

    @Override
    public List<GoodsVO> queryGoods(String name, String type, String status, Integer start, Integer pageSize, boolean queryAll, String sortKey,
                                    String sort, String startTime, String endTime) {
        Map<String,Object> queryParam = new HashMap<>();
        queryParam.put("name",name);
        queryParam.put("type",StringUtils.isEmpty(type)?null:Arrays.asList(type.split(",")));
        queryParam.put("start",start);
        queryParam.put("pageSize",pageSize);
        queryParam.put("flag",getFlag(queryAll));
        queryParam.put("startTime",startTime);
        queryParam.put("endTime",endTime);
        queryParam.put("status",StringUtils.isEmpty(status)?null:Arrays.asList(status.split(",")));
        if(!StringUtils.isEmpty(sortKey) && !StringUtils.isEmpty(sort)){
            if(!PatternUtil.isMatch(Constants.GOODS_SORT_REG,sort)){
                sort = "desc";
            }
            if(!PatternUtil.isMatch(Constants.GOODS_SORTKEY_REG,sortKey)){
                sortKey = "id";
            }else{
                switch (sortKey){
                    case "name":
                        sortKey = "CONVERT(name USING 'gbk')";
                        break;
                    case "status":
                    case "type":
                        sortKey = sortKey+" "+sort+",CONVERT(name USING 'gbk')";
                        break;
                    default:
                        break;
                }
            }
            sortKey = sortKey+" "+sort;
            queryParam.put("sort",sortKey);
        }
        return goodsMapper.queryBasicGoods(queryParam);
    }

    @Override
    public Integer queryGoodsCount(Integer id, String name, String type, String status,boolean queryAll,String startTime,String endTime) {
        Map<String,Object> queryParam = new HashMap<>();
        queryParam.put("name",name);
        queryParam.put("type",StringUtils.isEmpty(type)?null: Arrays.asList(type.split(",")));
        queryParam.put("status",StringUtils.isEmpty(status)?null:Arrays.asList(status.split(",")));
        queryParam.put("flag",getFlag(queryAll));
        return goodsMapper.queryBasicGoodsCount(queryParam);
    }

    @Override
    public GoodsVO queryGoodsInfo(Integer id, boolean queryAll) {
        Map<String,Object> queryParam = new HashMap<>();
        queryParam.put("id",id);
        queryParam.put("flag",getFlag(queryAll));
        return goodsMapper.queryBasicInfo(queryParam);
    }

    @Override
    public Integer deleteGoods(String ids) {
        return goodsMapper.changeBasicGoodsFlag(ids,0);
    }

    @Override
    public Integer resumeGoods(String ids) {
        return goodsMapper.changeBasicGoodsFlag(ids,1);
    }

    @Override
    public boolean updateSaleGoods(GoodsPO goodsPO) throws GoodsException{
        boolean result ;
        try {
            result = goodsMapper.updateGoods(goodsPO);
            GoodsStore.getInstance().reMakeGoods();
        } catch (Exception e) {
            log.error("修改商品失败: for {{}}",e.getMessage());
            throw new GoodsException("商品创建失败");
        }
        return result;
    }

    @Override
    public Integer updateGoodsStock(List<InsertOrderBean> orderBeans) {
        return goodsMapper.updateGoodsStock(orderBeans);
    }

    private List<Integer> getFlag(boolean queryAll){
        if(queryAll){
            return Arrays.asList( new Integer[]{0,1});
        }
        return Arrays.asList(new Integer[]{1});
    }
}
