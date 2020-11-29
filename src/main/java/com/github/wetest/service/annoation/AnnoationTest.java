package com.github.wetest.service.annoation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JZWen
 * @date 2020/11/29
 */
@Controller
public class AnnoationTest {

    @Autowired
    private ComponentLoad componentLoad;
    @Autowired
    private ServiceLoad serviceLoad;

//    @RequestMapping("/annoation")
//    @ResponseBody
//    public String test() {
//        serviceLoad.test();
//        //componentLoad.test();
//        return "ok";
//
//    }

}
