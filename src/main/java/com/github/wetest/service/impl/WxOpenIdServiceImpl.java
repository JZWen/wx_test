package com.github.wetest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.wetest.constants.WxConstant;
import com.github.wetest.domain.info.AccessTokenInfo;
import com.github.wetest.service.WxOpenIdService;
import com.github.wetest.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author JZWen
 * @date 2020/10/27
 */
@Service
public class WxOpenIdServiceImpl implements WxOpenIdService {

    @Value("${wx.appid}")
    private String appId;
    @Value("${wx.secret}")
    private String secret;

    @Override
    public String getOpenIdByCode(String code) {

        String requestUrl = WxConstant.WX_GET_OPEN_ID_API +
                "?appid=" +
                appId +
                "&secret=" +
                secret +
                "&code=" +
                code +
                "&grant_type=authorization_code";
        JSONObject resultJson = HttpUtil.sendGet(requestUrl);
        String openId = resultJson.getString("openid");
        return openId;
    }

    public static void main(String[] args) {
        System.out.println(new Date().getTime());
    }

}
