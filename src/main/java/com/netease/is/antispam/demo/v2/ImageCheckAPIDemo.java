/*
 * @(#) ImageCheckAPIDemo.java 2016年3月15日
 * 
 * Copyright 2010 NetEase.com, Inc. All rights reserved.
 */
package com.netease.is.antispam.demo.v2;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netease.is.antispam.demo.utils.HttpClient4Utils;
import com.netease.is.antispam.demo.utils.SignatureUtils;
import org.apache.http.Consts;
import org.apache.http.client.HttpClient;
import org.apache.log4j.Logger;
import sun.misc.BASE64Encoder;
import sun.misc.IOUtils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 调用易盾反垃圾云服务图片在线检测接口API示例，该示例依赖以下jar包：
 * 1. httpclient，用于发送http请求
 * 2. commons-codec，使用md5算法生成签名信息，详细见SignatureUtils.java
 * 3. gson，用于做json解析
 *
 * @author hzgaomin
 * @version 2016年2月3日
 */
public class ImageCheckAPIDemo {
  /**
   * 产品密钥ID，产品标识
   */
  private final static String SECRETID = "3783a45dc03c7789205e51a149a956aa";
  /**
   * 产品私有密钥，服务端生成签名信息使用，请严格保管，避免泄露
   */
  private final static String SECRETKEY = "34f13c8becda59112d6ad65f5ce163c7";
  /**
   * 业务ID，易盾根据产品业务特点分配
   */
  private final static String BUSINESSID = "152430c7b35ac27626ff428176304fbf";
  /**
   * 易盾反垃圾云服务图片在线检测接口地址
   */
  private final static String API_URL = "https://api.aq.163.com/v2/image/check";
  /**
   * 实例化HttpClient，发送http请求使用，可根据需要自行调参
   */
  private static HttpClient httpClient = HttpClient4Utils.createHttpClient(100, 20, 10000, 1000, 1000);
  private static Logger logger = Logger.getLogger(ImageCheckAPIDemo.class);

  /**
   * @param absolutePath
   * @throws Exception
   */
  public static boolean checkImage(String absolutePath) throws Exception {
    logger.info("checkImg/////////////////");
    Map<String, String> params = new HashMap<String, String>();
    // 1.设置公共参数
    params.put("secretId", SECRETID);
    params.put("businessId", BUSINESSID);
    params.put("version", "v2");
    params.put("timestamp", String.valueOf(System.currentTimeMillis()));
    params.put("nonce", String.valueOf(new Random().nextInt()));

//        String filePath = String.format("http://%s%s/%s",request.getServerName(),request.getContextPath(),relativePath);
    // 2.设置私有参数
    JsonArray jsonArray = new JsonArray();
//        // 传图片url进行检测，name结构产品自行设计，用于唯一定位该图片数据
//        JsonObject image1 = new JsonObject();
//        image1.addProperty("name", filePath);
//        image1.addProperty("type", 1);
//        image1.addProperty("data", filePath);
//        jsonArray.add(image1);

    // 传图片base64编码进行检测，name结构产品自行设计，用于唯一定位该图片数据
    logger.info("IN"+absolutePath);
    String base64 = new BASE64Encoder().encode(IOUtils.readFully(new FileInputStream(absolutePath), -1, false));
    logger.info("YES"+base64);
    JsonObject image2 = new JsonObject();
    image2.addProperty("name", "{\"imageId\": 33451123, \"contentId\": 78978}");
    image2.addProperty("type", 2);
    image2.addProperty("data", base64);
    jsonArray.add(image2);

    params.put("images", jsonArray.toString());
//    params.put("account", "java@163.com");
//    params.put("ip", "123.115.77.137");

    // 3.生成签名信息
    String signature = SignatureUtils.genSignature(SECRETKEY, params);
    params.put("signature", signature);

    // 4.发送HTTP请求，这里使用的是HttpClient工具包，产品可自行选择自己熟悉的工具包发送请求
    String response = HttpClient4Utils.sendPost(httpClient, API_URL, params, Consts.UTF_8);

    // 5.解析接口返回值
    JsonObject resultObject = new JsonParser().parse(response).getAsJsonObject();
    int code = resultObject.get("code").getAsInt();
    String msg = resultObject.get("msg").getAsString();
    if (code == 200) {
      JsonArray resultArray = resultObject.getAsJsonArray("result");
      for (JsonElement jsonElement : resultArray) {
        JsonObject jObject = jsonElement.getAsJsonObject();
        String name = jObject.get("name").getAsString();
        System.out.println(name);
        JsonArray labelArray = jObject.get("labels").getAsJsonArray();
        for (JsonElement labelElement : labelArray) {
          JsonObject lObject = labelElement.getAsJsonObject();
          int label = lObject.get("label").getAsInt();
          int level = lObject.get("level").getAsInt();
          double rate = lObject.get("rate").getAsDouble();
          System.out.println(String.format("label:%s, level=%s, rate=%s", label, level, rate));
          return false;
        }
        return true;
      }
    } else {
      System.out.println(String.format("ERROR: code=%s, msg=%s", code, msg));
      return false;
    }
    System.out.println("系统错误");
    return false;
  }
}
