package com.tianhui.chartdemo.service;

import com.tianhui.chartdemo.bean.EnumerationEntity;

import java.util.List;
import java.util.Map;

public interface IEnumerationService {
    boolean createEnum(EnumerationEntity enumerationEntity);

    boolean updateEnum(EnumerationEntity enumerationEntity);

    List<EnumerationEntity> queryEnum(Map<String,Object> params);

    boolean deleteEnum(Integer id);
}
