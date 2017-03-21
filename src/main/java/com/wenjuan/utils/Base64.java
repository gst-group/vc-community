package com.wenjuan.utils;


import java.io.UnsupportedEncodingException;

import com.wenjuan.controller.WeiXinController;
import com.wenjuan.service.OrganizationGroupService;
import com.wenjuan.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import sun.misc.*;

import java.io.UnsupportedEncodingException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
public class Base64{

    //url分割
    public static int urlsplit(String url){
        Logger logger = Logger.getLogger(Base64.class);
        String res = getFromBase64(url);
        //根据组织代号查询ogid
        int ogid=ApplicationContextUtil.getBean("organizationGroupService",OrganizationGroupService.class).selectOgid(res);

        return ogid;
    }
    //加密
    public static String getBase64(String str){
        byte[] b=null;
        String s=null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(b!=null){
            s=new BASE64Encoder().encode(b);
        }
        return s;
    }
    // 解密
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
