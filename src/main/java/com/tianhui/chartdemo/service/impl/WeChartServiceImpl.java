package com.tianhui.chartdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tianhui.chartdemo.dao.IUserMapper;
import com.tianhui.chartdemo.localEnum.GenderEnum;
import com.tianhui.chartdemo.po.UserPO;
import com.tianhui.chartdemo.service.IWeChartService;
import com.tianhui.chartdemo.utils.EncryptUtil;
import com.tianhui.chartdemo.utils.RestTemplateUtil;
import com.tianhui.chartdemo.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class WeChartServiceImpl implements IWeChartService {

    private static final Logger log = LoggerFactory.getLogger(WeChartServiceImpl.class);

    @Value("${weChartParam.appID}")
    String appID;

    @Value("${weChartParam.appSecret}")
    String appSecret;

    @Value("${weChartParam.tokenUrl}")
    String tokenUrl;

    @Value("${weChartParam.refreshUrl}")
    String refreshUrl;

    @Value("${weChartParam.userInfoUrl}")
    String userInfoUrl;

    @Value("${weChartParam.checkUrl}")
    String checkUrl;

    @Resource
    RestTemplateUtil restTemplateUtil;

    @Resource
    IUserMapper userMapper;

    @Override
    public String getToken(String userCode) {
        UserPO userPO = null;
        try {
            String token_url = tokenUrl + "appid="+ EncryptUtil.cipherDecrypt(appID)+"&secret="+EncryptUtil.cipherDecrypt(appSecret)+"&code="+userCode+"&grant_type="+"authorization_code";

            JSONObject tokenBody = restTemplateUtil.doGet(token_url,null);
            String access_token = tokenBody.getString("access_token");
            String openId = tokenBody.getString("openid");
            JSONObject userBody = getWeChartUserInfo(access_token,openId);
            if(userBody.isEmpty() || userBody.containsKey("errcode")){
                log.info("获取用户信息失败 : {}",userBody);
                return null;
            }
            userPO = new UserPO();
            userPO.setOpen_id(openId);
            userPO.setNickname(userBody.getString("nickname"));
            userPO.setIcon_url(userBody.getString("headimgurl"));
            userPO.setAccess_token(access_token);
            userPO.setRefresh_token(tokenBody.getString("refresh_token"));
            String gender = "";
            switch (userBody.getInteger("sex")){
                case 1 :
                    gender = GenderEnum.MAN.getValue();
                    break;
                case 2 :
                    gender = GenderEnum.FEMALE.getValue();
                    break;
                default:
                    gender = GenderEnum.UNKNOWN.getValue();
                    break;
            }
            userPO.setGender(gender);

            UserVO  userVO = userMapper.queryUserById(null,openId,null);
            if(null == userVO){
                userMapper.createUser(userPO);
            }else{
                userPO.setId(userVO.getId());
                userMapper.updateUser(JSONObject.parseObject(JSONObject.toJSONString(userPO), Map.class));
            }
        } catch (Exception e) {
            log.error("微信验证失败 : {}",e.getMessage());
            return null;
        }
        return EncryptUtil.cipherEncrypt(userPO.getId());
    }

    @Override
    public String checkToken() {
        return null;
    }

    @Override
    public String refreshToken() {
        return null;
    }

    @Override
    public JSONObject getWeChartUserInfo(String accessToken, String openId) {
        String user_url = userInfoUrl+"access_token="+accessToken+"&openid="+openId+"&lang="+"zh_CN";

        return restTemplateUtil.doGet(user_url,null);
    }

}
