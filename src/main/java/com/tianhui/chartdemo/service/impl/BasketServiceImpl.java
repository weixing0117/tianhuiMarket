package com.tianhui.chartdemo.service.impl;

import com.tianhui.chartdemo.bean.UserBean;
import com.tianhui.chartdemo.dao.IBasketMapper;
import com.tianhui.chartdemo.service.IBasketService;
import com.tianhui.chartdemo.bean.BasketEntity;
import com.tianhui.chartdemo.utils.ThreadLocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BasketServiceImpl implements IBasketService {
    private static final Logger log = LoggerFactory.getLogger(BasketServiceImpl.class);
    @Resource
    IBasketMapper basketMapper;


    @Override
    public boolean insertIntoBasket(Integer goodsId) {
        try {
            UserBean user = ThreadLocalUtil.getUser();
            BasketEntity basketEntity = new BasketEntity();
            basketEntity.setGoods_id(goodsId);
            basketEntity.setUser_id(user.getId());
            basketMapper.InsertIntoBasket(basketEntity);
        } catch (Exception e) {
            log.error("Insert Into Basket Error :{}",e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBasket(List<BasketEntity> basketEntityList) {
        try {
            for (BasketEntity basketEntity: basketEntityList) {
                basketEntity.setUser_id(ThreadLocalUtil.getUser().getId());
            }
            basketMapper.clearBasket(ThreadLocalUtil.getUser().getId());
            basketMapper.createBasketOrUpdate(basketEntityList);
        } catch (Exception e) {
            log.error("Update Basket Error :{}",e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<BasketEntity> queryBasket(String user_id) {
        return basketMapper.queryBasket(user_id);
    }

    @Override
    public boolean clearBasket(String user_id) {
        try {
            basketMapper.clearBasket(user_id);
        } catch (Exception e) {
            log.error("Clear Basket Error :{}",e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Integer basketCount(String user_id) {
        return basketMapper.basketCount(user_id);
    }

    @Override
    public boolean deleteFromBasket(String goodsIds) {
        Map<String,Object> params = new HashMap<>();
        params.put("list",Arrays.asList(goodsIds.split(",")));
        params.put("user_id",ThreadLocalUtil.getUser().getId());
        basketMapper.deleteFromBasket(params);
        return true;
    }
}
