package com.github.wetest.controller;

import com.github.wetest.utils.GetTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JZWen
 * @date 2020/10/26
 */
@RestController("")
public class LoginController {

    @Autowired
    private GetTokenUtil getTokenUtil;

    @RequestMapping("/getToken")
    public String getToken() throws Exception {
        System.out.println("test");
        return getTokenUtil.getToken();
    }

}
