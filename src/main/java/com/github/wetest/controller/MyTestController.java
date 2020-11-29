package com.github.wetest.controller;

import com.alibaba.fastjson.JSON;
import com.github.wetest.constants.WxConstant;
import com.github.wetest.domain.info.WxMsgSettingInfo;
import com.github.wetest.service.WxOpenIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JZWen
 * @date 2020/10/26
 */
@Controller("/")
public class MyTestController {

    private int count = 0;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private WxOpenIdService wxOpenIdService;

    @Value("${local.domain.name}")
    private String domainName;
    @Value("${wx.appid}")
    private String appid;

    @RequestMapping("/a")
    public String test() {
        redisTemplate.opsForValue().set("a", "123");
        return (String) redisTemplate.opsForValue().get("a");
    }

    @RequestMapping("/login")
    public void login(HttpServletResponse response) throws IOException {
        //页面重定向到微信服务器 页面携带code
        String localNameUrl = domainName + "/bindInfo";
        System.out.println(localNameUrl);
        String redirectUri = URLEncoder.encode(localNameUrl, "utf-8");
        String requestUrl = WxConstant.WX_AUTHORIZE_CODE_API +
                "?appid=" +
                appid +
                "&redirect_uri=" +
                redirectUri +
                "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        System.out.println(requestUrl);
        response.sendRedirect(requestUrl);
    }

    @RequestMapping("/bindInfo")
    public void bindInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code = request.getParameter("code");
        System.out.println("code: " + code);
        String openId = wxOpenIdService.getOpenIdByCode(code);
        System.out.println("openId = " + openId);
        response.sendRedirect("/home?openId=" + openId);
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("setting")
    public String setting() {
        System.out.println("消息设置页面");
        return "setting";
    }

    @RequestMapping("zhanan")
    public String zhanan() {
        System.out.println("zhanan");
        System.out.println(++count);
        return "zhanan";
    }

        public static void main(String[] args) {

            WxMsgSettingInfo wxMsgSettingInfo = new WxMsgSettingInfo();
            wxMsgSettingInfo.setIsReceive("0");
            wxMsgSettingInfo.setMessageName("咚咚消息");
            wxMsgSettingInfo.setMsgType("15001");
            wxMsgSettingInfo.setDesc("咚咚离线消息触达，点击关注即可");
            wxMsgSettingInfo.setPin("pin");
            wxMsgSettingInfo.setAppId("appId");
            WxMsgSettingInfo wxMsgSettingInfo1 = new WxMsgSettingInfo();
            wxMsgSettingInfo1.setIsReceive("1");
            wxMsgSettingInfo1.setMessageName("交易消息");
            wxMsgSettingInfo1.setMsgType("12");
            wxMsgSettingInfo1.setDesc("交易消息，点击关注即可");
            wxMsgSettingInfo1.setPin("pin");
            wxMsgSettingInfo1.setAppId("appId");
            List<WxMsgSettingInfo> list = new ArrayList<>();
            list.add(wxMsgSettingInfo);
            list.add(wxMsgSettingInfo1);
            System.out.println(JSON.toJSONString(list));
        }

}
