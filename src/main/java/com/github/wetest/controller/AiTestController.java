package com.github.wetest.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.wetest.domain.info.ImageInfo;
import com.github.wetest.utils.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JZWen
 * @date 2020/11/24
 */
@Controller("ai")
public class AiTestController {

    @RequestMapping("/up")
    public String up(HttpServletRequest request) {

        String url = request.getParameter("imageUrl");
        String spu = request.getParameter("spu");
        String id = request.getParameter("id");
        String aiUrl = "http://ai-image-price.jd.local/product_image/add?user_id=parity_system&token=8130b2f8-f590-4f2c-a641-1235d8d8e757";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("collection_name", "jzfp");
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setProduct_id(spu);
        imageInfo.setImage_content(url);
        imageInfo.setImage_id(id);
        imageInfo.setImage_info("spu_image");
        List<ImageInfo> imageInfoList = new ArrayList<>();
        jsonObject.put("image_list", imageInfoList);

        JSONObject jsonObject1 = HttpUtil.sendPost(aiUrl, jsonObject.toString());
        System.out.println(jsonObject1.toString());
        return "ok";
    }

    @RequestMapping("/down")
    public String down(HttpServletRequest request) {
//
//        String url = request.getParameter("imageUrl");
//        String spu = request.getParameter("spu");
//        String id = request.getParameter("id");
//        String aiUrl = "http://ai-image-price.jd.local/product_image/add?user_id=parity_system&token=8130b2f8-f590-4f2c-a641-1235d8d8e757";
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("collection_name", "jzfp");
//        ImageInfo imageInfo = new ImageInfo();
//        imageInfo.setProduct_id(spu);
//        imageInfo.setImage_content(url);
//        imageInfo.setImage_id(id);
//        imageInfo.setImage_info("spu_image");
//        List<ImageInfo> imageInfoList = new ArrayList<>();
//        jsonObject.put("image_list", imageInfoList);

//        JSONObject jsonObject1 = HttpUtil.sendPost(aiUrl, jsonObject.toString());
//        System.out.println(jsonObject1.toString());
        return "ok";
    }


}
