package com.wenjuan.utils;


import com.wenjuan.model.Good;
import com.wenjuan.model.User;
import com.wenjuan.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 短信http接口的java代码调用示例
 * 基于Apache HttpClient 4.3
 *
 * @author songchao
 * @since 2015-04-03
 */

public class JavaSmsApi {
    private final static Logger logger = LoggerFactory.getLogger(JavaSmsApi.class);

    //查账户信息的http地址
    private static String URI_GET_USER_INFO = "http://sms.yunpian.com/v2/user/get.json";

    //智能匹配模版发送接口的http地址
    private static String URI_SEND_SMS = "http://sms.yunpian.com/v2/sms/single_send.json";

    //模板发送接口的http地址
    private static String URI_TPL_SEND_SMS = "http://sms.yunpian.com/v2/sms/tpl_single_send.json";

    //发送语音验证码接口的http地址
    private static String URI_SEND_VOICE = "http://voice.yunpian.com/v2/voice/send.json";

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";

    //修改为您的apikey.apikey可在官网（http://www.yuanpian.com)登录后获取
    private static String apikey = "8f7b4b96ba06c1991c6ba874e126af3d";

    public static void main(String[] args) throws IOException, URISyntaxException {

//        //修改为您的apikey.apikey可在官网（http://www.yuanpian.com)登录后获取
        String apikey = "8f7b4b96ba06c1991c6ba874e126af3d";
        String tel = "15800449307";
        String text = "【龙卡心语】您好！您的验证码为9900，三分钟内有效";
//
//        //修改为您要发送的手机号
//        String mobile = URLEncoder.encode("13020179822",ENCODING);
//
//        /**************** 查账户信息调用示例 *****************/
//        System.out.println(JavaSmsApi.getUserInfo(apikey));
//
//        /**************** 使用智能匹配模版接口发短信(推荐) *****************/
//        //设置您要发送的内容(内容必须和某个模板匹配。以下例子匹配的是系统提供的1号模板）
//        String text = "【赞橙社区】验证码是：1234（请勿泄露），赞橙社区官方网站izancheng.com";
//        //发短信调用示例
        System.out.println(JavaSmsApi.sendSms(text, tel));
//
//        /**************** 使用指定模板接口发短信(不推荐，建议使用智能匹配模版接口) *****************/
//        //设置模板ID，如使用1号模板:【#company#】您的验证码是#code#
//        long tpl_id = 1;
//        //设置对应的模板变量值
//
//        String tpl_value = URLEncoder.encode("#code#",ENCODING) +"="
//            + URLEncoder.encode("1234", ENCODING) + "&"
//            + URLEncoder.encode("#company#",ENCODING) + "="
//            + URLEncoder.encode("云片网",ENCODING);
//        //模板发送的调用示例
//        System.out.println(tpl_value);
//        System.out.println(JavaSmsApi.tplSendSms(apikey, tpl_id, tpl_value, mobile));
//
//        /**************** 使用接口发语音验证码 *****************/
//        String code = "1234";
        //System.out.println(JavaSmsApi.sendVoice(apikey, mobile ,code));
    }

    /**
     * 取账户信息
     *
     * @return json格式字符串
     * @throws java.io.IOException
     */

    public static String getUserInfo(String apikey) throws IOException, URISyntaxException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        return post(URI_GET_USER_INFO, params);
    }

    /**
     * 智能匹配模版接口发短信
     * @param text   　短信内容
     * @param mobile 　接受的手机号
     * @return json格式字符串
     * @throws IOException
     */

    public static String sendSms(String text, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("text", text);
        params.put("mobile", mobile);
        return post(URI_SEND_SMS, params);
    }

    /**
     * 通过模板发送短信(不推荐)
     *
     * @param apikey    apikey
     * @param tpl_id    　模板id
     * @param tpl_value 　模板变量值
     * @param mobile    　接受的手机号
     * @return json格式字符串
     * @throws IOException
     */

    public static String tplSendSms(String apikey, long tpl_id, String tpl_value, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("tpl_id", String.valueOf(tpl_id));
        params.put("tpl_value", tpl_value);
        params.put("mobile", mobile);
        return post(URI_TPL_SEND_SMS, params);
    }

    /**
     * 通过接口发送语音验证码
     *
     * @param apikey apikey
     * @param mobile 接收的手机号
     * @param code   验证码
     * @return
     */

    public static String sendVoice(String apikey, String mobile, String code) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("mobile", mobile);
        params.put("code", code);
        return post(URI_SEND_VOICE, params);
    }

    /**
     * 基于HttpClient 4.3的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return 提交响应
     */

    public static String post(String url, Map<String, String> paramsMap) {
        logger.info("start sendcode,method post,url:[{}]", url);
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }

    /**
     * 对订阅了新商品的用户发送新商品提醒
     *
     * @param good 新商品信息，不需要校验空
     * @throws IOException
     */
    public static void newGoodNotification(Good good) throws IOException {
        UserService userService = ApplicationContextUtil.getBean("userService", UserService.class);
        //所有订阅了新商品的用户
        List<User> users = userService.selectSubscribeGoodUser();
        for (User user : users) {
            String tel = user.getName();
            //电话号码不合法不发送短信提醒
            if (CheckUtil.isLegalTel(tel)) {
                MessageUtil mu = new MessageUtil();
                mu.sendMessage(tel, good.getName());
            }
        }
    }

    public static boolean SendRegisterCode(String tel, String code) {
        if (code == null) {
            code = RandomStringUtils.random(4, true, true);
        }
        try {
            System.out.println("****"+code+"***"+tel);
            sendSms(String.format("【龙卡心语】您好！您的验证码为%s，三分钟内有效", code), tel);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
