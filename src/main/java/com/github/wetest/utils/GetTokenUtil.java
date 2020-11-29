package com.github.wetest.utils;

import com.alibaba.fastjson.JSONObject;
import com.github.wetest.constants.WxConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author JZWen
 * @date 2020/10/26
 */
@Component
public class GetTokenUtil {

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;
    @Autowired
    private RedisTemplate redisTemplate;

    public String getToken() throws Exception {
        String token = (String) redisTemplate.opsForValue().get(WxConstant.STR_WX_ACCESS_TOKEN);
        if (token == null || token.equals("")) {
            //发起http请求获取accessToken
            JSONObject jsonObject = HttpUtil.sendGet(WxConstant.WX_ACCESS_TOKEN_API + "?grant_type=client_credential&appid=" + appid + "&secret=" + secret);
            token = jsonObject.getString("access_token");
            if (token == null || token.equals("")) {
                redisTemplate.opsForValue().set(WxConstant.STR_WX_ACCESS_TOKEN, token, 1L, TimeUnit.HOURS);
            }
        }
        return token;
    }
}
