package com.tianhui.chartdemo.service;

import com.tianhui.chartdemo.bean.BasketEntity;

import java.util.List;

public interface IBasketService {
        boolean insertIntoBasket(Integer goodsId);

        boolean updateBasket(List<BasketEntity> basketEntityList);

        List<BasketEntity> queryBasket(String user_id);

        boolean clearBasket(String user_id);

        Integer basketCount(String user_id);

        boolean deleteFromBasket(String goodsIds);
}
