package com.github.wetest.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @author JZWen
 * @date 2020/10/26
 *
 * TODO 1、将请求改成 RestTemplate  2、增加重试
 *
 */
public class HttpUtil {

//    /**
//     * <p>方法说明: HTTP POST 请求
//     * <p>编码格式: UTF8
//     * <p>参数说明: String urL 请求的路径
//     * <p>参数说明: String parAms 请求的参数
//     * <p>返回说明: JSONObject
//     * */
//    public static JSONObject doPost(String url, String params) throws Exception {
//        Request request = Request.Post(url);
//        request.bodyByteArray(params.getBytes("UTF8"));
//        Response response = request.execute();
//        String jsonData = response.returnContent().asString();
//
//        /* 转化为 JSONObject 数据 */
//        JSONObject json = JSONObject.fromObject(jsonData);
//        return json;
//    }
//
//    /**
//     * <p>方法说明: HTTP GET 请求
//     * <p>编码格式: UTF8
//     * <p>参数说明: String urL 请求的路径
//     * <p>返回说明: JSONObject
//     * */
//    public static JSONObject doGet(String url) throws Exception{
//        Request request = Request.Get(url);
//        request.setHeader("Content-type", "application/json;charset=UTF8");
//        Response response = request.execute();
//        String jsonData = response.returnContent().asString();
//        JSONObject json = JSONObject.fromObject(jsonData);
//        return json;
//    }
//
//    /**
//     * <p>方法说明: HTTP GET 请求
//     * <p>编码格式: UTF8 , 微信编码转为UTF-8
//     * <p>参数说明: String urL 请求的路径
//     * <p>返回说明: JSONObject
//     * */
//    public static JSONObject doGetUTF8(String url) throws Exception {
//        Request request = Request.Get(url);
//        request.setHeader("Content-type", "application/json;charset=UTF8");
//        Response response = request.execute();
//        String jsonData = response.returnContent().asString();
//        String string = new String(jsonData.getBytes("ISO-8859-1"),"UTF-8");
//        JSONObject json = JSONObject.fromObject(string);
//        return json;
//    }
//
//
//    /**
//     * <p>方法说明: HTTP POST 请求
//     * <p>编码格式: UTF8
//     * <p>参数说明: String urL 请求的路径
//     * <p>参数说明: String parAms 请求的参数
//     * <p>返回说明: String
//     * */
//    public static String doPostToStr(String url, String params) throws Exception {
//        Request request = Request.Post(url);
//        request.bodyByteArray(params.getBytes("UTF8"));
//        Response response = request.execute();
//        return response.returnContent().asString();
//    }
//
//    /**
//     * <p>方法说明: HTTP GET 请求
//     * <p>编码格式: UTF8
//     * <p>参数说明: String urL 请求的路径
//     * <p>返回说明: String
//     * */
//    public static String doGetToStr(String url) throws Exception {
//        Request request = Request.Get(url);
//        request.setHeader("Content-type", "application/json;charset=UTF8");
//        Response response = request.execute();
//        return response.returnContent().asString();
//    }

    public static JSONObject getRequest(String url) throws URISyntaxException, IOException {
        System.out.println(url);
        URI uri = new URI(url);
        SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
        ClientHttpRequest chr = schr.createRequest(uri, HttpMethod.POST);
        //chr.getBody().write(param.getBytes());//body中设置请求参数
        ClientHttpResponse res = chr.execute();
        InputStream is = res.getBody(); //获得返回数据,注意这里是个流
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String str = "";
        while((str = br.readLine())!=null) {
            System.out.println(str);//获得页面内容或返回内容
            return JSONObject.parseObject(str);
        }
        System.out.println("return null");
        return null;
    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url：发送请求的 URL
     * @param param：请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return String[result]：所代表远程资源的响应结果
     */
    public static JSONObject sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);

            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();

            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());

            // 发送请求参数
            out.print(param);

            // flush输出流的缓冲
            out.flush();

            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }

        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return JSONObject.parseObject(result);
    }

    /**
     * 向指定URL发送GET方法的请求
     * @param url：发送请求的URL
     * @return String[result]：所代表远程资源的响应结果
     */
    public static JSONObject sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();

            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            // 建立实际的连接
            connection.connect();

            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();

            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }

        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return JSONObject.parseObject(result);
    }
}