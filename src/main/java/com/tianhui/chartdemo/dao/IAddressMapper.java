package com.tianhui.chartdemo.dao;

import com.tianhui.chartdemo.bean.AddressEntity;

import java.util.List;
import java.util.Map;

public interface IAddressMapper {
    Integer insertOrUpdateAddress(AddressEntity addressEntity);

    Integer deleteAddress(Map<String,Object> deleteMap);

    List<AddressEntity> queryAddressByUserId(String userId);

    AddressEntity queryById(String id);

    Integer queryUsersCount(String user_id);
}
