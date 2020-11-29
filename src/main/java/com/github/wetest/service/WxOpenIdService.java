package com.github.wetest.service;

/**
 * @author JZWen
 * @date 2020/10/27
 */
public interface WxOpenIdService {

    String getOpenIdByCode(String code) throws Exception;
}
