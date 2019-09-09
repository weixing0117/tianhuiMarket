package com.tianhui.chartdemo.dao;

import com.tianhui.chartdemo.bean.InsertOrderBean;
import com.tianhui.chartdemo.po.GoodsPO;
import com.tianhui.chartdemo.vo.GoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IBasicGoodsMapper {
    boolean createOrUpdateBasicGoods(GoodsPO goodsPO);

    List<GoodsVO> queryBasicGoods(Map<String,Object> queryParam);

    Integer queryBasicGoodsCount(Map<String,Object> queryParam);

    GoodsVO queryBasicInfo(Map<String,Object> queryParam);

    Integer changeBasicGoodsFlag(String ids,Integer flag);

    boolean updateGoods(GoodsPO goodsPO);

    Integer updateGoodsStock(List<InsertOrderBean> orderBeans);
}
