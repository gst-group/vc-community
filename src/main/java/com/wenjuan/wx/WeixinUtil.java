package com.wenjuan.wx;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.wenjuan.controller.WeiXinController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 公众平台通用接口工具类
 * 
 * @author DiGua
 */
public class WeixinUtil {
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

	// 服务号（客户）
//	public static final String APP_ID = "wxeb39f2a90068fc1c";
//	public static final String APP_SECRET = "4603234b18bc96e61ad5555b518287e7";
	
	// 测试公众号
//	public static final String APP_ID = "wxaf8eb79a12e1b3cd";
//	public static final String APP_SECRET = "82b3e7805b97f407589dc092c60868a6";
	// 订阅号（靓靓的）
//	public static final String APP_ID = "wxc79084142f4abec8";
//	public static final String APP_SECRET = "7ddba922b58e289366041879c5ea65e6";
	// 服务号（光宇）
//	public static final String APP_ID = "wxee929a1a1cf2bbcc";
//	public static final String APP_SECRET = "9549e512e696a6e548deca713ea81889";
	//关山
//wxeb39f2a90068fc1c
	//1c3898ec9f24b0ee52be8fd0cedbc203
	public static final String APP_ID = "wxf02d93b9479c90c7";
	public static final String APP_SECRET ="1bfc45f675b03621f37cabd66bd2c9f0";


	private static String ACCESS_TOKEN = "";
	private static Date LAST_GET_TIME = null;
	
	// 获取基础支持的access_token的接口地址（GET）   
	private final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// 获取jsapi_ticket凭证（GET） 有效期7200s
	private final static String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	// 通过code获取网页授权access_token（有效期7200s）
	private final static String user_access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	// 通过refresh_token（有效期30天）刷新用户access_token
	private final static String refresh_user_access_token_url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	// 验证授权access_token是否有效
	private final static String auth_user_access_token_url = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";
	// 抓取用户信息
	private final static String get_user_info_url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	/**
	 * 获取基础支持的accessToken
	 * @return
	 */
	public static String getClientAccessToken() 
	{
		if(ACCESS_TOKEN != null && !"".equals(ACCESS_TOKEN) && LAST_GET_TIME != null)
		{
			// 十分钟内无需刷新
			long diffTime = new Date().getTime() - LAST_GET_TIME.getTime();
			if(diffTime < 10 * 60 * 1000)
			{
				return ACCESS_TOKEN;
			}
		}
		
		AccessToken at = getAccessToken();
		if(at != null && at.getToken() != null && !"".equals(at.getToken()))
		{
			ACCESS_TOKEN = at.getToken();
			LAST_GET_TIME = new Date();
		}
		else
		{
			log.error("获取微信clientAccessToken失败！");
		}
		
		return ACCESS_TOKEN;
	}

	/** 
	 * 获取access_token 
	 *  
	 * @return 
	 */  
	private static AccessToken getAccessToken(){
		return getAccessToken(APP_ID, APP_SECRET);
	}
	
	
	/** 
	 * 获取access_token 
	 *  
	 * @param appid 凭证 
	 * @param appsecret 密钥 
	 * @return 
	 */  
	private static AccessToken getAccessToken(String appid, String appsecret) {  
	    AccessToken accessToken = null;  
	  
	    String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
	    JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
	    // 如果请求成功   
	    if (null != jsonObject) {  
	        try {  
	            accessToken = new AccessToken();  
	            accessToken.setToken(jsonObject.getString("access_token"));  
	            accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
	        } catch (JSONException e) {  
	            accessToken = null;  
	            // 获取token失败   
	            log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
	        }  
	    }  
	    return accessToken;  
	} 

	
	public static String getJsapiTicket() 
	{
		return getJsapiTicket(getClientAccessToken());
	}
	
	
	/**
	 * 获取jsapi_ticket
	 * @param token
	 * @return
	 */
	public static String getJsapiTicket(String token)
	{
		String ticket = "";
	    String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", token);  
		
		JSONObject jo = httpRequest(requestUrl, "GET", null);
		 
	    if (null != jo) {  
	        try {
	        	log.info(jo.toString());
	        	if(jo.getInt("errcode") == 0)
	        	{
	        		ticket = jo.getString("ticket");
	        	}
	        } catch (JSONException e) {  
	            e.printStackTrace();
	        	ticket = "";
	        }  
	    }
	    
	    return ticket;
	}
	
	/**
	 * 获取用户网页授权访问token
	 * @param code
	 * @return
	 */
	public static Map<String, Object> getUserAccessToken(String code)
	{
		Map<String, Object> ret = new HashMap<String, Object>();

		String reqUrl = user_access_token_url.replace("APPID", APP_ID).replace("SECRET", APP_SECRET).replace("CODE", code);

		JSONObject jo = httpRequest(reqUrl, "GET", null);
		 
	    if (null != jo) {  
	        try {
	        	ret.put("access_token", jo.getString("access_token"));	// 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	        	ret.put("expires_in", jo.getInt("expires_in"));			// access_token接口调用凭证超时时间，单位（秒）
	        	ret.put("refresh_token", jo.getString("refresh_token"));// 用户刷新access_token
	        	ret.put("openid", jo.getString("openid"));				// 用户唯一标识
	        	ret.put("scope", jo.getString("scope"));				// 用户授权的作用域，使用逗号（,）分隔
	        } catch (JSONException e) {  
	            e.printStackTrace();
	        }  
	    }
		
		return ret;
	}
	
	/**
	 * 刷新用户accessToken信息（accessToken两小时失效）
	 * @param refreshToken
	 * @return
	 */
	public static Map<String, Object> refreshUserAccessToken(String refreshToken)
	{
		Map<String, Object> ret = new HashMap<String, Object>();

		String reqUrl = refresh_user_access_token_url.replace("APPID", APP_ID).replace("REFRESH_TOKEN", refreshToken);

		JSONObject jo = httpRequest(reqUrl, "GET", null);
		 
	    if (null != jo) {  
	        try {
	        	ret.put("access_token", jo.getString("access_token"));	// 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	        	ret.put("expires_in", jo.getInt("expires_in"));			// access_token接口调用凭证超时时间，单位（秒）
	        	ret.put("refresh_token", jo.getString("refresh_token"));// 用户刷新access_token
	        	ret.put("openid", jo.getString("openid"));				// 用户唯一标识
	        	ret.put("scope", jo.getString("scope"));				// 用户授权的作用域，使用逗号（,）分隔
	        } catch (JSONException e) {  
	            e.printStackTrace();
	        }  
	    }
		
		return ret;
	}
	
	/**
	 * 验证accessToken是否有效
	 * @param openId
	 * @param userAccessToken
	 * @return
	 */
	public static boolean isValidUserAccessToken(String openId, String userAccessToken)
	{
		boolean isValid = false;

		String reqUrl = auth_user_access_token_url.replace("OPENID", openId).replace("ACCESS_TOKEN", userAccessToken);

		JSONObject jo = httpRequest(reqUrl, "GET", null);
		 
	    if (null != jo) {  
	        try {
	        	isValid = jo.getInt("errcode") == 0;
	        } catch (JSONException e) {  
	            e.printStackTrace();
	        }  
	    }
		
		return isValid;
	}
	
	/**
	 * 获取微信用户信息
	 * @param openId
	 * @param userAccessToken
	 * @return
	 */
	public static Map<String, Object> getUserInfo(String openId, String userAccessToken,String refreshToken)
	{   		Map<String, Object> retref = new HashMap<String, Object>();

		if(!isValidUserAccessToken(openId,userAccessToken)){
			retref=refreshUserAccessToken(refreshToken);
			openId=retref.get("openid").toString();
			userAccessToken=retref.get("access_token").toString();
			log.info("----------------openId------------------"+openId);
			log.info("---------------userAccessToken-------------------"+userAccessToken);
		};


		Map<String, Object> ret = new HashMap<String, Object>();

		String reqUrl = get_user_info_url.replace("OPENID", openId).replace("ACCESS_TOKEN", userAccessToken);

		JSONObject jo = httpRequest(reqUrl, "GET", null);
		 
	    if (null != jo) {  
	        try {
	        	log.info(jo.toString());
	        	ret.put("openid", jo.getString("openid"));		// 用户的唯一标识
	        	ret.put("nickname", jo.getString("nickname"));	// 用户昵称
	        	ret.put("sex", jo.getString("sex"));			// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	        	ret.put("province", jo.getString("province"));	// 用户个人资料填写的省份
	        	ret.put("city", jo.getString("city"));			// 普通用户个人资料填写的城市
	        	ret.put("country", jo.getString("country"));	// 国家，如中国为CN
	        	ret.put("headimgurl", jo.getString("headimgurl"));	// 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	        	try {
		        	ret.put("unionid", jo.getString("unionid"));	// 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。					
				} catch (Exception e) {
				}

	        	
	        	JSONArray pvgs = jo.getJSONArray("privilege");	// 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
	        	if(pvgs != null && pvgs.size() > 0)
	        	{
	        		List l = new ArrayList();
	        		for(int i = 0; i < pvgs.size(); i++)
	        		{
	        			l.add(pvgs.getString(i));
	        		}
		        	ret.put("privilege", l);
	        	}
	        	else
	        	{
		        	ret.put("privilege", new ArrayList());
	        	}
	        } catch (JSONException e) {

	        }  
	    }
		
		return ret;
	}
	
	
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			log.info("url:"+requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		log.info("return:"+jsonObject.toString());
		return jsonObject;
	}
}
    