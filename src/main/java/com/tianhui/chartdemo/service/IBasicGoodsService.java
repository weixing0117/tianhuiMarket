package com.tianhui.chartdemo.service;

import com.tianhui.chartdemo.bean.InsertOrderBean;
import com.tianhui.chartdemo.po.GoodsPO;
import com.tianhui.chartdemo.exception.GoodsException;
import com.tianhui.chartdemo.vo.GoodsVO;

import java.util.List;

public interface IBasicGoodsService {
    boolean createGoods(GoodsPO goodsPO) throws GoodsException;

    boolean updateGoods(GoodsPO goodsPO);

    List<GoodsVO> queryGoods(String name, String type, String status, Integer start, Integer pageSize, boolean queryAll, String sortKey, String sort, String startTime, String endTime);

    Integer queryGoodsCount(Integer id,String name,String type,String status,boolean queryAll,String startTime,String endTime);

    GoodsVO queryGoodsInfo(Integer id, boolean queryAll);

    Integer deleteGoods(String ids);

    Integer resumeGoods(String ids);

    boolean updateSaleGoods(GoodsPO goodsPO)  throws GoodsException;

    Integer updateGoodsStock(List<InsertOrderBean> orderBeans);
}
