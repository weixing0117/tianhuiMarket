package com.tianhui.chartdemo.dao;

import com.tianhui.chartdemo.bean.OrderGoodsBean;
import com.tianhui.chartdemo.po.OrderPO;
import com.tianhui.chartdemo.vo.OrderVO;

import java.util.List;
import java.util.Map;

public interface IOrderMapper {

    List<OrderVO> queryOrder(Map<String,Object> params);

    Integer queryOrderCount(Map<String,Object> params);

    Integer createOrder(OrderPO orderPO);

    Integer createConnection(List<OrderGoodsBean> orderGoodsBeanList);

    Integer updateOrderInfoForUser(OrderPO orderPO);

    OrderPO queryBaseOrder(String id);

    Integer cancelOrder(OrderPO orderPO);

    boolean updateOrderStatus(OrderPO orderPO);
}
