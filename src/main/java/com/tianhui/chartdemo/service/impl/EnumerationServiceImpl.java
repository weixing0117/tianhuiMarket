package com.tianhui.chartdemo.service.impl;

import com.tianhui.chartdemo.bean.EnumerationEntity;
import com.tianhui.chartdemo.dao.IEnumerationMapper;
import com.tianhui.chartdemo.service.IBasicGoodsService;
import com.tianhui.chartdemo.service.IEnumerationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class EnumerationServiceImpl implements IEnumerationService {

    @Resource
    IEnumerationMapper enumerationMapper;
    @Resource
    IBasicGoodsService basicGoodsService;

    @Override
    public boolean createEnum(EnumerationEntity enumerationEntity) {
        return enumerationMapper.createOrUpdateEnum(enumerationEntity);
    }

    @Override
    public boolean updateEnum(EnumerationEntity enumerationEntity) {
        return enumerationMapper.createOrUpdateEnum(enumerationEntity);
    }

    @Override
    public List<EnumerationEntity> queryEnum(Map<String, Object> params) {
        return enumerationMapper.queryEnum(params);
    }

    @Override
    public boolean deleteEnum(Integer id) {
        Integer related_type = basicGoodsService.queryGoodsCount(null,null,String.valueOf(id),null,true,null,null);

        Integer related_status = basicGoodsService.queryGoodsCount(null,null,null,String.valueOf(id),true,null,null);

        if(related_type>0 || related_status>0){
            return false;
        }

        return enumerationMapper.deleteEnum(id);
    }
}
