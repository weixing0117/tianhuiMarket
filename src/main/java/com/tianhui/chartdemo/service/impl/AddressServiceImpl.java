package com.tianhui.chartdemo.service.impl;


import com.tianhui.chartdemo.bean.AddressEntity;
import com.tianhui.chartdemo.dao.IAddressMapper;
import com.tianhui.chartdemo.service.IAddressService;
import com.tianhui.chartdemo.service.IUserService;
import com.tianhui.chartdemo.utils.ThreadLocalUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements IAddressService {
    @Resource
    IAddressMapper addressMapper;
    @Resource
    IUserService userService;
    @Override
    public boolean insertAddress(AddressEntity addressEntity) {
        addressEntity.setUser_id(ThreadLocalUtil.getUser().getId());
        Integer result = addressMapper.insertOrUpdateAddress(addressEntity);
        if (result>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAddress(AddressEntity addressEntity) {
        Integer result = addressMapper.insertOrUpdateAddress(addressEntity);
        if (result>0){
            return true;
        }
        return false;
    }

    @Override
    public List<AddressEntity> queryAddressByUserId(String user_id) {
        return addressMapper.queryAddressByUserId(user_id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteAddress(List<String> addressIds) {
        Map<String,Object> deleteMap = new HashMap<>();
        deleteMap.put("ids",addressIds);
        deleteMap.put("user_id",ThreadLocalUtil.getUser().getId());
        Integer result = addressMapper.deleteAddress(deleteMap);
        Integer count = addressMapper.queryUsersCount(ThreadLocalUtil.getUser().getId());
        if(count==0){
            userService.updateCommonAddress(ThreadLocalUtil.getUser().getId(),null);
        }
        if(result>0){
            return true;
        }
        return false;
    }

    @Override
    public Integer queryUsersCount() {
        return addressMapper.queryUsersCount(ThreadLocalUtil.getUser().getId());
    }

    @Override
    public AddressEntity queryById(String id) {
        return addressMapper.queryById(id);
    }
}
