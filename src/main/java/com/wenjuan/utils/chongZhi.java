package com.wenjuan.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class chongZhi {
    public static final String key = "c34dhqAi6XGsamD8a0mawdHmLgFlGruIjnLqDazxY6QYhiVhi9iEaPRLVmXqDgYM";
    public static final String selectKey = "tz4WArjom1ct9XAgVIKVnrw9Dio8ocJICBt4TDg6sinq7OwQm8BznYxR7MOkPmSg";
    public static final String url = "http://api.huafeiduo.com/gateway.cgi?mod=order.phone.submit";

//    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
//        chong("15800449307", 10, "123");
//    }

    public static void chong(String tel, int money, String orderid) throws NoSuchAlgorithmException, IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("card_worth", money);
        params.put("phone_number", tel);
        params.put("sp_order_id", orderid);
        params.put("api_key", key);
        params.put("sign", sign(params));
        readContentFromPost(url, params);
    }

    public static void readContentFromPost(String urls, Map<String, Object> params) throws IOException {
        String result = "";
        BufferedReader in = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(urls);
            if (params != null) {
                for (String key : params.keySet()) {
                    sb.append("&").append(key).append("=").append(params.get(key));
                }
            }
            URL realUrl = new URL(sb.toString());
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        System.out.println(result);
    }

    public static String sign(Map<String, Object> params) throws NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder();
        Collection<String> keySet = params.keySet();
        List<String> keys = new ArrayList<>(keySet);
        Collections.sort(keys);
        for (String key : keys) {
            sb.append(key).append(params.get(key));
        }
        sb.append(selectKey);
        return chongZhi.md5(sb.toString());
    }

    public static String md5(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(data.getBytes());
        StringBuffer buf = new StringBuffer();
        byte[] bits = md.digest();
        for (int i = 0; i < bits.length; i++) {
            int a = bits[i];
            if (a < 0) a += 256;
            if (a < 16) buf.append("0");
            buf.append(Integer.toHexString(a));
        }
        return buf.toString();
    }
}
