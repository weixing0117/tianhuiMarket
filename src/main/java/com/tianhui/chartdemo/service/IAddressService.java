package com.tianhui.chartdemo.service;

import com.tianhui.chartdemo.bean.AddressEntity;

import java.util.List;

public interface IAddressService {
    boolean insertAddress(AddressEntity addressEntity);

    boolean updateAddress(AddressEntity addressEntity);

    List<AddressEntity> queryAddressByUserId(String user_id);

    boolean deleteAddress(List<String> addressIds);

    Integer queryUsersCount();

    AddressEntity queryById(String id);
}
