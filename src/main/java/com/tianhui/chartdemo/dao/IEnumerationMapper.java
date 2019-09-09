package com.tianhui.chartdemo.dao;

import com.tianhui.chartdemo.bean.EnumerationEntity;

import java.util.List;
import java.util.Map;

public interface IEnumerationMapper {
     boolean createOrUpdateEnum(EnumerationEntity enumerationEntity);

     List<EnumerationEntity> queryEnum(Map<String,Object> params);

     boolean deleteEnum(Integer id);

}
