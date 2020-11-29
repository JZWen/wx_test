package com.github.wetest.controller;

import com.github.wetest.service.limiter.LimiterOverflowValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JZWen
 * @date 2020/11/26
 */
@Controller
public class LimiterController {

    @Autowired
    private LimiterOverflowValidate limiterOverflowValidate;

    @RequestMapping("/limit")
    @ResponseBody
    public String limiter() {
        limiterOverflowValidate.tryAcquire();
        return "ok";
    }

}
