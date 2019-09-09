package com.tianhui.chartdemo.service;

import com.tianhui.chartdemo.bean.InsertOrder;
import com.tianhui.chartdemo.po.OrderPO;
import com.tianhui.chartdemo.vo.OrderVO;

import java.util.List;
import java.util.Map;

public interface IOrderService {
    Integer createOrder(InsertOrder insertOrder) throws Exception;

    List<OrderVO> queryOrder(Map<String,Object> params);

    Integer queryOrderCount(Map<String,Object> params);

    boolean updateOrderInfoForUser(OrderPO orderPO);

    OrderPO queryBaseOrder(String id);

    boolean cancelOrder(OrderPO orderPO) throws Exception;

    boolean updateOrderStatus(OrderPO orderPO);
}
