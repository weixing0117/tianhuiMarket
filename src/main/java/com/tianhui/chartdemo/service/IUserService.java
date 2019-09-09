package com.tianhui.chartdemo.service;

import com.tianhui.chartdemo.po.UserPO;
import com.tianhui.chartdemo.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface IUserService {
    boolean createUser(UserPO userPO);

    boolean updateUser(UserPO userPO);

    List<UserVO> queryUser(Long id,String name,Integer telephone,String openId,Integer start,Integer pageSize);

    Integer queryUserCount(Long id,String name,Integer telephone,String openId);

    String login(String username,String password);

    boolean updateCommonAddress(String id,String addressId);
}
