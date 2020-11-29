package com.github.wetest.domain.info;

import com.alibaba.fastjson.JSONObject;

/**
 * @author JZWen
 * @date 2020/10/27
 */
public class WxTemplateMsgInfo {
    private String touser;
    private String template_id;
    private String url;
    private MiniProgram miniprogram;
    private JSONObject data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MiniProgram getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(MiniProgram miniprogram) {
        this.miniprogram = miniprogram;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
