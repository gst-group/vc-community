package com.wenjuan.utils;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.wenjuan.dao.UserMapper;
import com.wenjuan.model.Notifies;
import com.wenjuan.service.NotifiesService;
import com.wowtour.jersey.api.EasemobMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * TODO Y 发送消息与通知的类
 */
@Component
public class MessageUtil {
    private static String lettermodel = "【VC社区】测试短信";
    private static Logger LOGGER = LoggerFactory
            .getLogger(EasemobMessages.class);
    @Resource(name = "notifiesService")
    NotifiesService notifiesService;
    @Autowired
    UserMapper userMapper;

    /**
     * 获取四位数字的验证码
     *
     * @return
     */
    private static String getNumberCode() {
        StringBuffer code = new StringBuffer("");
        for (int i = 0; i < 4; i++) {
            code.append((int) (Math.random() * 10));
        }

        return new String(code);
    }

    public static void main(String[] args) throws IOException, APIConnectionException, APIRequestException {
    	MessageUtil jp=new MessageUtil();
    	//jp.JPush("123","YES","","13701747745","");
    	System.out.println(jp.sendNotification(0,"123","","",""));
//        String content = lettermodel;
//        String s = JavaSmsApi.sendSms(content, "15696564260");
//        System.out.println(s);
    }

    public static PushResult buildPushObject_ios_audienceMore_messageWithExtras(String articleid,String user, String title, String content) throws APIConnectionException, APIRequestException {
//        return PushPayload.newBuilder()
//                .setPlatform(Platform.android_ios())
//                .setAudience(/*Audience.newBuilder()
////                    .addAudienceTarget(AudienceTarget.tag("10086", "10086"))
//                        .addAudienceTarget(AudienceTarget.alias(user, user))
//                        .build()*/
//                		Audience.alias(user)
//                		)
//                .setMessage(Message.newBuilder()
//                        .setMsgContent(content)
//                        .addExtra("from", "VC社区")
//                        .setTitle(title)
//                        .build())
//                .build();
    	//YES
    	
    	  String appKey = "3977359b2d0bfe4c9b773658";
          String masterSecret = "9fdca29412f36a27e5742fea";
          JPushClient jpush = new JPushClient(masterSecret, appKey);

//          PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.all())
//                  .setAudience(Audience.alias(user))
//                  .setOptions(Options.newBuilder()
//                          .setApnsProduction(false)
//                          .build())
//                .setMessage(Message.newBuilder()
//                        .setMsgContent(content)
//                        .addExtra("from", "VC社区")
//                        .setTitle(title)
//                        .build())
//                  .build();
          PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.all())
                  .setAudience(Audience.alias(user))
                  .setOptions(Options.newBuilder()
                          .setApnsProduction(false)
                          .build())
                   .setMessage(Message.newBuilder()
                        .setMsgContent(content)
                        .addExtra("from", "龙卡心语")
                        .setTitle(title)
                        .addExtra("id",articleid)
                        .build())
                .build();
          PushResult pushResult = null;
          try{
              pushResult = jpush.sendPush(payload);
          }catch(Exception e){

          }
         
          return pushResult;
    }

    public NotifiesService getNotifiesService() {
        return notifiesService;
    }

    public void setNotifiesService(NotifiesService notifiesService) {
        this.notifiesService = notifiesService;
    }

    /**
     * 发送短消息
     * 发送完成后记录在数据库中
     *
     * @param receiver 电话号码
     * @param msg      消息
     * @return
     * @throws IOException
     */

    public String sendMessage(String receiver, String goodname) throws IOException {
//    	String code=getNumberCode();
//		String content=lettermodel.replace("#code#", code);
        //【VC社区】有新的商品:#good#可以兑换了,赶快来吧
        String content ="【龙卡心语】有新的商品:"+goodname+"可以兑换了,赶快来吧";
        String s = JavaSmsApi.sendSms(content, receiver);
        return "success";
    }

    /**
     * 推送消息
     * 推送完成后记录在数据库中
     *
     * @param receiverId 给推送的用户的id，是用户id，不是电话号码
     * @param alert      通知内容, 这里必须指定，则会覆盖上级统一指定的 alert 信息；内容可以为空字符串，则表示不展示到通知栏。
     * @param title      通知标题，如果指定了，则通知里原来展示 App名称的地方，将展示成这个字段。
     * @param extras     JSON Object,这里自定义 JSON 格式的 Key/Value 信息，以供业务使用。
     * @return
     * @throws APIRequestException
     * @throws APIConnectionException
     */
    public PushResult sendNotification(int receiverId, String alert, String title, String extras, String img) throws APIConnectionException, APIRequestException {
        String appKey = "29ca2ad9cd12a50324154f64";
        String masterSecret = "35213cebef55ce3db513c002";
        JPushClient jpush = new JPushClient(masterSecret, appKey);

        PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.all())
                .setAudience(Audience.alias("13701747745"))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .build())
                .setNotification(Notification.newBuilder().setAlert(alert)
                        .addPlatformNotification(
                                IosNotification.newBuilder().setSound("default").setBadge(1).build())
                        .build())
                .build();
        PushResult pushResult = jpush.sendPush(payload);
        return pushResult;
//        if (pushResult.isResultOK()) {
//            Notifies n = new Notifies();
//            n.setPlatform("极光推送");
//            n.setContent(alert);
//            n.setSendStatus(0);
//            ApplicationContextUtil.getBean("notifiesService", NotifiesService.class).insert(n);
//            return "success";
//        }
//        return "error";
    }

    public void JPush(String title, String content, String articleid, String user, String img) throws APIConnectionException, APIRequestException {
        String appKey = "3977359b2d0bfe4c9b773658";
        String masterSecret = "9fdca29412f36a27e5742fea";
       // JPushClient jpush = new JPushClient(masterSecret, appKey);
        PushResult pushPayload = buildPushObject_ios_audienceMore_messageWithExtras(articleid,user,title, content);
    }
}
