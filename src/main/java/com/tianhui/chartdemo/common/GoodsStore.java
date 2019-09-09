package com.tianhui.chartdemo.common;

import com.tianhui.chartdemo.dao.IBasicGoodsMapper;
import com.tianhui.chartdemo.utils.ContextUtil;
import com.tianhui.chartdemo.vo.GoodsVO;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *  本地缓存，可拓展为redis
 */
public class GoodsStore {
    private volatile static GoodsStore goodsStore = new GoodsStore();

    private static ReentrantReadWriteLock.WriteLock rl;

    private Map<Integer, GoodsVO> goodsMap;

    private GoodsStore(){
        makeGoodsMap();
        rl = new ReentrantReadWriteLock().writeLock();
    }

    public static GoodsStore  getInstance(){
        return goodsStore;
    }
    public GoodsVO getGoods(Integer id){
        return goodsMap.get(id);
    }

    public boolean buyGoods(Integer id,Integer number){
        GoodsVO goodsVO = goodsMap.get(id);
        if (null == goodsVO || goodsVO.getStock()==0){
            return false;
        }
        rl.lock();
        try {
            Integer rest = goodsVO.getStock()-number;
            if (rest < 0){
                return false;
            }
            goodsVO.setStock(rest);
            goodsMap.put(id, goodsVO);
        } catch (Exception e){
            return false;
        } finally {
            rl.unlock();
        }
        return true;
    }

    public boolean resumeGoods(Integer id,Integer number){
        GoodsVO goodsVO = goodsMap.get(id);
        rl.lock();
        try {
            Integer rest = goodsVO.getStock()+number;
            goodsVO.setStock(rest);
            goodsMap.put(id, goodsVO);
        } catch (Exception e){
            return false;
        } finally {
            rl.unlock();
        }
        return true;
    }

    public boolean addGoods(GoodsVO goodsVO){
        rl.lock();
        try {
            goodsMap.put(goodsVO.getId(), goodsVO);
        } catch (Exception e){
            return false;
        } finally {
            rl.unlock();
        }
        return true;
    }

    public boolean deleteGoods(Integer id){
        rl.lock();
        try {
            goodsMap.remove(id);
        } catch (Exception e){
            return false;
        } finally {
            rl.unlock();
        }
        return true;
    }

    public boolean reMakeGoods(){
        rl.lock();
        try {
            makeGoodsMap();
        } catch (Exception e){
            return false;
        } finally {
            rl.unlock();
        }
        return true;
    }

    private void makeGoodsMap(){
        Map<String,Object> param = new HashMap<>();
        param.put("flag",Arrays.asList(new Integer[]{0,1}));
        IBasicGoodsMapper  iBasicGoodsMapper = (IBasicGoodsMapper) ContextUtil.getBean(IBasicGoodsMapper.class);
        List<GoodsVO> goodsEntities = iBasicGoodsMapper.queryBasicGoods(param);
        goodsMap = new HashMap<>();
        for (GoodsVO goodsVO : goodsEntities) {
            goodsMap.put(goodsVO.getId(), goodsVO);
        }
    }
}
