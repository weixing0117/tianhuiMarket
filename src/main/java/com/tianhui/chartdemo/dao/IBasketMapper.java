package com.tianhui.chartdemo.dao;

import com.tianhui.chartdemo.bean.BasketEntity;

import java.util.List;
import java.util.Map;

public interface IBasketMapper {

    Integer createBasketOrUpdate(List<BasketEntity> basket);

    List<BasketEntity> queryBasket(String user_id);

    Integer clearBasket(String user_id);

    Integer InsertIntoBasket(BasketEntity basketEntity);

    Integer basketCount(String user_id);

    Integer deleteFromBasket(Map<String,Object> params);

}
