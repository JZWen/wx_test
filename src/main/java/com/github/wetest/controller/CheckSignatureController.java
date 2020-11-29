package com.github.wetest.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.wetest.constants.WxConstant;
import com.github.wetest.domain.info.WxTemplateMsgInfo;
import com.github.wetest.service.WxTemplateMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 验证微信服务器
 *
 * @author JZWen
 * @date 2020/8/21
 */
@RestController
@RequestMapping("/")
public class CheckSignatureController {

    private int count = 0;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private WxTemplateMsgService wxTemplateMsgService;

    @Value("${wx.appid}")
    private String appId;
    @Value("${wx.template.id}")
    private String templateId;


    @GetMapping("test")
    public String checkSignature(HttpServletRequest request) {
        System.out.println("signature" + request.getParameter("signature"));
        System.out.println("timestamp" + request.getParameter("timestamp"));
        System.out.println("nonce" + request.getParameter("nonce"));
        System.out.println("echostr" + request.getParameter("echostr"));

        System.out.println(JSON.toJSONString(request.getParameter("echostr")));
        return request.getParameter("echostr");
    }

    @PostMapping("test")
    public String handler(HttpServletRequest request) {
        System.out.println("事件");
        return "";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }


    @RequestMapping("reporting")
    public String reporting(HttpServletRequest request) {
        String openid = request.getParameter("openid");
        String pin = request.getParameter("pin");
        System.out.println("reporting, openid: " + openid + "  pin:" + pin);
        redisTemplate.opsForValue().set(WxConstant.STR_WX_PIN_OPENID_PREFIX + pin, openid);

        return "";
    }

    @RequestMapping("sendTemplateMsg")
    public String sendTemplateMsg(HttpServletRequest request) throws Exception {
        String pin = request.getParameter("pin");
        String content = request.getParameter("content");

        String openId = (String) redisTemplate.opsForValue().get(WxConstant.STR_WX_PIN_OPENID_PREFIX + pin);
        WxTemplateMsgInfo wxTemplateMsgInfo = new WxTemplateMsgInfo();
//        MiniProgram miniProgram = new MiniProgram();
//        miniProgram.setAppid(appId);
//        wxTemplateMsgInfo.setMiniprogram(miniProgram);
        wxTemplateMsgInfo.setTouser(openId);
        wxTemplateMsgInfo.setTemplate_id(templateId);
        JSONObject nameObject = new JSONObject();
        nameObject.put("value", pin);
        nameObject.put("color", "#173177");
        JSONObject contentObject = new JSONObject();
        contentObject.put("value", content);
        contentObject.put("coloe", "#173177");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", nameObject);
        jsonObject.put("content", contentObject);
        wxTemplateMsgInfo.setData(jsonObject);
        return wxTemplateMsgService.send(wxTemplateMsgInfo);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> sleep(finalI), WxTemplateMsgService.executors);
            int a = future.get();

        }
    }

    private static int sleep(int s) {
        int a = 0;
        for (int i = 0; i < 1000; i++) {
            a++;
        }
        return s;
    }
}
