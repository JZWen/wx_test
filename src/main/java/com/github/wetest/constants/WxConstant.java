package com.github.wetest.constants;

/**
 * @author JZWen
 * @date 2020/10/26
 */
public class WxConstant {

    /**
     * redis key 相关
     */
    public static final String STR_WX_ACCESS_TOKEN = "str_wx_access_token";
    public static final String STR_WX_PIN_OPENID_PREFIX = "str_wx_pin_openid_";

    /**
     * api 相关
     */
    public static final String WX_CREATE_MENU_API = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token="; //可不用
    public static final String WX_ACCESS_TOKEN_API = "https://api.weixin.qq.com/cgi-bin/token";
    public static final String WX_SEND_TEMPLATE_MSG_API = "https://api.weixin.qq.com/cgi-bin/message/template/send";
    public static final String WX_GET_OPEN_ID_API = "https://api.weixin.qq.com/sns/oauth2/access_token";
    public static final String WX_AUTHORIZE_CODE_API = "https://open.weixin.qq.com/connect/oauth2/authorize";

}
