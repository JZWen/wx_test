package com.github.wetest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.wetest.constants.WxConstant;
import com.github.wetest.domain.info.AccessTokenInfo;
import com.github.wetest.domain.info.WxTemplateMsgInfo;
import com.github.wetest.service.WxTemplateMsgService;
import com.github.wetest.utils.GetTokenUtil;
import com.github.wetest.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JZWen
 * @date 2020/10/27
 */
@Service
public class WxTemplateMsgServiceImpl implements WxTemplateMsgService {

    @Autowired
    private GetTokenUtil getTokenUtil;

    @Override
    public String send(WxTemplateMsgInfo wxTemplateMsgInfo) throws Exception {
        String token = getTokenUtil.getToken();
        //发送服务号消息
        System.out.println(JSON.toJSONString(wxTemplateMsgInfo));
        JSONObject jsonObject = HttpUtil.sendPost(WxConstant.WX_SEND_TEMPLATE_MSG_API + "?access_token=" + token, JSON.toJSONString(wxTemplateMsgInfo));
        return jsonObject.toJSONString();
    }

    public static void main(String[] args) {
        AccessTokenInfo accessTokenInfo = new AccessTokenInfo();
        AccessTokenInfo accessTokenInfo1 = new AccessTokenInfo();
        accessTokenInfo.setAccess_token("11");
        accessTokenInfo.setExpires_in("111");
        accessTokenInfo1.setAccess_token("22");
        accessTokenInfo1.setExpires_in("222");
        List list = new ArrayList();
        list.add(accessTokenInfo);
        list.add(accessTokenInfo1);
        String str = JSON.toJSONString(list);
        System.out.println(str);
        List<AccessTokenInfo> list1 = JSONObject.parseArray(str, AccessTokenInfo.class);
        for (AccessTokenInfo s : list1) {
            System.out.println(s.getExpires_in());
            System.out.println(s.getAccess_token());
        }

    }

}
