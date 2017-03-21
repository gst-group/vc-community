package com.wenjuan.utils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.annotation.PreDestroy;
import java.io.*;
import java.util.Map;

public class ConfigUtil {
  /**
   * 自增长序列化ID
   */
  //private static final long serialVersionUID = -5297381375690088745L;
  public static final String path = "config.json";
  public static final int NUMBER_OF_ARTICLE_ITEM_PER_PAGE = 12;

  public static final String WEB = "WEB";
  public static final String ANDROID = "ANDROID";
  public static final String IOS = "IOS";

  private Map<String, Object> map;

  private static ConfigUtil _instance = new ConfigUtil();

  private ConfigUtil() {
    Reader inSm = null;
    try {
      inSm = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(path), "UTF-8");
      this.map = new Gson().fromJson(inSm, new TypeToken<Map<String, String>>() {
      }.getType());
    } catch (Exception ex) {
      System.out.println("配置文件加载异常");
    } finally {
      if (inSm != null) {
        try {
          inSm.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static ConfigUtil getDefaultInstance() {
    return _instance;
  }

  public static Boolean getBoolean(Object key) {
    Object value = _instance.map.get (key);
    if (value == null) {
      return null;
    }
    try {
      return Boolean.parseBoolean(value.toString());
    } catch (RuntimeException e) {
      return null;
    }
  }

  public static void put(String key, Object value) {
    _instance.map.put(key, value);
  }

  @PreDestroy
  public static void saveParas() {
    Writer writer = null;
    try {
      writer = new FileWriter(new File(path));
      new Gson().toJson(_instance.map, writer);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("文件未找到");
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("修改文件异常");
    } finally {
      try {
        if (writer != null)
          writer.close();
      } catch (IOException e) {

      }
    }
  }
}