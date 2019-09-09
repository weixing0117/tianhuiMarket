package com.tianhui.chartdemo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.tianhui.chartdemo.bean.InsertOrder;
import com.tianhui.chartdemo.bean.InsertOrderBean;
import com.tianhui.chartdemo.bean.OrderGoodsBean;
import com.tianhui.chartdemo.bean.OrderInfoBean;
import com.tianhui.chartdemo.common.GoodsStore;
import com.tianhui.chartdemo.dao.IOrderMapper;
import com.tianhui.chartdemo.exception.GoodsException;
import com.tianhui.chartdemo.po.OrderPO;
import com.tianhui.chartdemo.service.IBasicGoodsService;
import com.tianhui.chartdemo.service.IBasketService;
import com.tianhui.chartdemo.service.IOrderService;
import com.tianhui.chartdemo.utils.ThreadLocalUtil;
import com.tianhui.chartdemo.vo.OrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements IOrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
        IOrderMapper orderMapper;
    @Resource
    IBasicGoodsService goodsService;
    @Resource
    IBasketService basketService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer createOrder(InsertOrder insertOrder) throws Exception{
        StringBuffer ids = new StringBuffer("");
        OrderPO orderPO = new OrderPO();
        // 生成订单与商品关系
        List<OrderGoodsBean> orderGoodsBeanList = new ArrayList<>(insertOrder.getInsertOrderBeanList().size());

        for (int i = 0 ; i<insertOrder.getInsertOrderBeanList().size();i++) {
            // 先修改本地缓存
            if(!GoodsStore.getInstance().buyGoods(insertOrder.getInsertOrderBeanList().get(i).getGoods_id(),insertOrder.getInsertOrderBeanList().get(i).getQuantity())){
                for(int j = 0 ; j < i ; j++){
                    // 回滚操作
                    GoodsStore.getInstance().resumeGoods(insertOrder.getInsertOrderBeanList().get(j).getGoods_id(),insertOrder.getInsertOrderBeanList().get(j).getQuantity());
                }
                throw new GoodsException("库存不足");
            }
            ids.append(insertOrder.getInsertOrderBeanList().get(i).getGoods_id()+",");
            OrderGoodsBean goodsBean = new OrderGoodsBean();
            goodsBean.setGoods_id(insertOrder.getInsertOrderBeanList().get(i).getGoods_id());
            goodsBean.setOrder_id(orderPO.getId());
            goodsBean.setQuantity(insertOrder.getInsertOrderBeanList().get(i).getQuantity());
            goodsBean.setUnit_price(insertOrder.getInsertOrderBeanList().get(i).getUnit_price());
            orderGoodsBeanList.add(goodsBean);
        }
        Integer result = 0;
            // 减库存
        Integer effected = goodsService.updateGoodsStock(insertOrder.getInsertOrderBeanList());
        if(effected < insertOrder.getInsertOrderBeanList().size()){
            throw new GoodsException("库存不足");
        }
        try {
            // 删除购物车中对应商品
            basketService.deleteFromBasket(ids.substring(0,ids.length()-1));
            // 生成订单
            orderPO.setUser_id(ThreadLocalUtil.getUser().getId());
            orderPO.setComment(insertOrder.getComment());
            orderPO.setContact(insertOrder.getContact());
            orderPO.setContact_number(insertOrder.getContact_number());
            orderPO.setTotal_cost(insertOrder.getTotal_cost());
            orderPO.setUser_id(ThreadLocalUtil.getUser().getId());
            orderPO.setContact_address(insertOrder.getContact_address());
            orderPO.setTotal_number(insertOrder.getTotal_number());
            // 插入关系表
            orderMapper.createConnection(orderGoodsBeanList);
            // 生成订单
            result = orderMapper.createOrder(orderPO);

        } catch (Exception e) {
            for (int i = 0 ; i<insertOrder.getInsertOrderBeanList().size();i++) {
                // 回滚操作
                GoodsStore.getInstance().resumeGoods(insertOrder.getInsertOrderBeanList().get(i).getGoods_id(),insertOrder.getInsertOrderBeanList().get(i).getQuantity());
            }
            log.error("订单提交失败 :{}",e.getMessage());
            throw new Exception("订单提交失败");
        }
        return result;
    }

    @Override
    public List<OrderVO> queryOrder(Map<String, Object> params) {
        return orderMapper.queryOrder(params);
    }

    @Override
    public Integer queryOrderCount(Map<String, Object> params) {
        return orderMapper.queryOrderCount(params);
    }

    @Override
    public boolean updateOrderInfoForUser(OrderPO orderPO) {
        orderMapper.updateOrderInfoForUser(orderPO);
        return true;
    }

    @Override
    public OrderPO queryBaseOrder(String id) {
        return orderMapper.queryBaseOrder(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelOrder(OrderPO orderPO) throws Exception{
        Map<String,Object> params = new HashMap<>();
        params.put("id",orderPO.getId());
        List<OrderInfoBean> orderInfoBeanList = orderMapper.queryOrder(params).get(0).getOrderInfoList();
        List<InsertOrderBean> insertOrderBeanList = JSONArray.parseArray(JSONArray.toJSONString(orderInfoBeanList),InsertOrderBean.class);
        for (InsertOrderBean insertOrderBean:
        insertOrderBeanList) {
            insertOrderBean.setQuantity(-insertOrderBean.getQuantity());
        }
        // 归还库存
        try {
            Integer effected = goodsService.updateGoodsStock(insertOrderBeanList);
            if(effected < insertOrderBeanList.size()){
                throw new GoodsException("库存异常");
            }
          orderMapper.cancelOrder(orderPO);
        } catch (GoodsException e) {
            log.error("撤销订单失败 : {}",e.getMessage());
            throw new GoodsException("库存修改失败");
        }
        for (int i = 0 ; i<orderInfoBeanList.size();i++) {
            // 本地缓存归还操作
            GoodsStore.getInstance().resumeGoods(orderInfoBeanList.get(i).getGoods_id(),orderInfoBeanList.get(i).getQuantity());
        }
        return true;
    }

    @Override
    public boolean updateOrderStatus(OrderPO orderPO) {
        return orderMapper.updateOrderStatus(orderPO);
    }
}
