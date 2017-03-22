package com.wenjuan.controller;

import com.wenjuan.bean.MessageBean;
import com.wenjuan.dao.UserLoginLogMapper;
import com.wenjuan.dao.UserMapper;
import com.wenjuan.dao.WeiXinMapper;
import com.wenjuan.model.*;
import com.wenjuan.service.OrganizationUserService;
import com.wenjuan.service.UserGroupService;
import com.wenjuan.service.UserService;
import com.wenjuan.service.UserSessionService;
import com.wenjuan.utils.Base64;
import com.wenjuan.wx.WechatSign;
import com.wenjuan.wx.WeixinUtil;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/wx"})
public class WeiXinController
{
    public static String domainUrl = "vc-community.insight365.ai";
    Logger logger = Logger.getLogger(WeiXinController.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    WeiXinMapper weixinMapper;

    @Autowired
    @Qualifier("userService")
    private UserService userService;


    @Autowired
    @Qualifier("organizationUserService")
    private OrganizationUserService organizationUserService;

    @Autowired
    @Qualifier("userGroupService")
    private UserGroupService userGroupService;

    @Autowired
    @Qualifier("userSessionService")
    private UserSessionService userSessionService;

    @Resource
    private UserLoginLogMapper userLoginLogMapper;

    @RequestMapping({"wxLogin"})
    public void login(HttpServletRequest req,HttpSession session, HttpServletResponse res, String openid, String ACCESS_TOKEN, @RequestParam(defaultValue="true") boolean addToAllGroup) throws Exception { JSONObject jo = new JSONObject();
      /*  WeiXin wx = this.weixinMapper.selectAll(openid);
        if (wx != null)
        {
            if (wx.getUserid() != null)
            {
                User user = this.userService.selectByPrimaryKey(Integer.valueOf(Integer.parseInt(wx.getUserid())));
                jo.put("msg", "success");
                jo.put("userName", user.getName());
                jo.put("passWord", user.getPassword());
                res.getWriter().write(jo.toString());
            }
            else {
                jo.put("msg", "success");
                jo.put("userName", wx.getOpenId());
                jo.put("passWord", "123456789");
                res.getWriter().write(jo.toString());
            }

        }
        else
        {
            Map obj = WeixinUtil.getUserInfo(openid, ACCESS_TOKEN);
            WeiXin wxUser = new WeiXin();
            wxUser.setNickname(String.valueOf(obj.get("nickname")));
            wxUser.setOpenId(String.valueOf(obj.get("openid")));
            wxUser.setCity(String.valueOf(obj.get("city")));
            wxUser.setCountry(String.valueOf(obj.get("country")));
            wxUser.setPrivilege(String.valueOf(obj.get("privilege")));
            wxUser.setProvince(String.valueOf(obj.get("province")));
            wxUser.setSex(String.valueOf(obj.get("sex")));
            wxUser.setHeadImgURL(String.valueOf(obj.get("headimgurl")));
            wxUser.setUnionId(String.valueOf(obj.get("unionid")));
            wxUser.setAccessToken(ACCESS_TOKEN);
try {

    this.weixinMapper.insert(wx);
}catch (Exception e){

}

            User user = new User();
            user.setId(null);
            user.setScore(null);
            user.setRegisterTime(null);
            user.setAllowArticle(null);
            user.setPassword("123456789");
            user.setName(wx.getOpenId());
            user.setNickname(wx.getNickname());
            user.setAddBackground(Boolean.valueOf(false));
            user.setAvatar(wxUser.getHeadImgURL());
            user.setRole(Integer.valueOf(1));
            user.setLocation(wx.getCity());
            int effectRow=0;
            try{
                effectRow = this.userService.insert(user);
            }catch (Exception e){
                effectRow=1;
            }


            logger.info("--------添加讨论区------------");
                try{
                    Map mapConfig=new HashMap();
                    logger.info("-------userid-------"+user.getId().intValue());
                    mapConfig.put("userId",user.getId().intValue());
                    logger.info("---------ogid-----"+session.getAttribute("ogid"));
                    mapConfig.put("ogid",session.getAttribute("ogid"));
                    this.userGroupService.addUserToAllGroup(mapConfig);
                }catch (Exception e){

                }


            if (effectRow > 0)
            {
                propertyController ec = new propertyController();
                ec.insertUser(user.getId().toString());
            }

            jo.put("msg", "success");
            jo.put("userName", wx.getOpenId());
            jo.put("passWord", "123456789");
            res.getWriter().write(jo.toString());
        }*/
    }

    @RequestMapping({"/wxRgister"})
    public void UserOrWeiXin(String tel, String name, @RequestParam(required=false) String password, HttpServletResponse res, HttpServletRequest req)
            throws Exception
    {
        User user = this.userService.selectByName(tel);

        WeiXin wx = this.weixinMapper.selectAll(name);
        if (user != null)
        {
            wx.setUserid(user.getId().toString());
            this.weixinMapper.UpdateUserid(wx);
        } else {
            user = this.userService.selectByName(name);

            user.setName(tel);
            user.setPassword(password);
            user.setScore(null);
            user.setRegisterTime(null);
            user.setAllowArticle(null);
            user.setAddBackground(Boolean.valueOf(false));

            this.userService.updateByPrimaryKey(user);

            wx.setUserid(user.getId().toString());
            this.weixinMapper.UpdateUserid(wx);
        }

        UserSession userSession = this.userSessionService.selectByPrimaryKey(user.getId());
        if (userSession == null) {
            String token = RandomStringUtils.random(31, true, true);
            userSession = new UserSession();
            userSession.setToken(token);
            userSession.setUserId(user.getId());
            try {

                this.userSessionService.insert(userSession);
            }catch (Exception e){

            }
        }
        try {
            UserLoginLog userLoginLog = new UserLoginLog();
            userLoginLog.setUserid(user.getId());
            userLoginLog.setIp(req.getRemoteAddr());
            try {
                this.userLoginLogMapper.insert(userLoginLog);

            }catch (Exception e){

            }
            }
        catch (RuntimeException localRuntimeException)
        {
        }
        Cookie loginCookie = new Cookie("_wx_token", userSession.getToken());
        loginCookie.setDomain(domainUrl);
        loginCookie.setPath("/");
        loginCookie.setMaxAge(86400000);
        res.addCookie(loginCookie);
        res.getWriter().write("success");
    }

    @RequestMapping({"/User"})
    public void User(String tel, HttpServletResponse res) throws Exception
    {
        User user = this.userService.selectByName(tel);
        if (user != null)
            res.getWriter().write("1");
        else
            res.getWriter().write("2");
    }

    @RequestMapping({"/wx"})
    public void w(HttpServletResponse res,HttpServletRequest request,HttpSession session) throws Exception
    {
        //存入用户所在组织编号  即aid
        session.setAttribute("ogid", Base64.urlsplit(request.getParameter("from")));
        String project_name = "lk-vc";
        String redirectURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf02d93b9479c90c7&redirect_uri=http%3a%2f%2f" + domainUrl+ "%2f" + project_name + "%2fwx%2fauthdeny2.do&response_type=code&scope=snsapi_userinfo&state=test#wechat_redirect";
        res.sendRedirect(redirectURL);
    }

    @RequestMapping({"/configdata"})
    public MessageBean getWXConfigData(HttpServletRequest request, String urls)
    {
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        try
        {
            String url = urls;
            String ticket = WeixinUtil.getJsapiTicket();

            Map res = WechatSign.sign(ticket, url);
            this.logger.info(res);
            messageBean.setExtra(res);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return messageBean;
    }

  /*  @RequestMapping({"/authdeny2"})
    public void authdeny2(HttpServletRequest request, HttpServletResponse response,HttpSession session)
            throws Exception
    {

        String redirectURL = "http://" + domainUrl + "/lk-vc-h5/page/article.html";
        String code = request.getParameter("code");
        String state = request.getParameter("state");

        if (!"authdeny".equals(code)) {
            Map atMap = WeixinUtil.getUserAccessToken(code);

            String accessToken = String.valueOf(atMap.get("access_token"));
            String refreshToken = String.valueOf(atMap.get("refresh_token"));
            String openId = String.valueOf(atMap.get("openid"));

            this.logger.info(atMap);

            if ((openId != null) && (!"".equals(openId)))
            {
                WeiXin wx = this.weixinMapper.selectAll(openId);

                Map uiMap = WeixinUtil.getUserInfo(openId, accessToken);
                WeiXin wxUser = new WeiXin();
                wxUser.setNickname(String.valueOf(uiMap.get("nickname")));
                wxUser.setOpenId(String.valueOf(uiMap.get("openid")));
                wxUser.setCity(String.valueOf(uiMap.get("city")));
                wxUser.setCountry(String.valueOf(uiMap.get("country")));
                wxUser.setPrivilege(String.valueOf(uiMap.get("privilege")));
                wxUser.setProvince(String.valueOf(uiMap.get("province")));
                wxUser.setSex(String.valueOf(uiMap.get("sex")));
                wxUser.setHeadImgURL(String.valueOf(uiMap.get("headimgurl")));
                wxUser.setUnionId(String.valueOf(uiMap.get("unionid")));
                wxUser.setAccessToken(accessToken);
                wxUser.setRefreshToken(refreshToken);

                User user = null;
                boolean isnew = false;

                if (wx != null)
                {
                    if ((wx.getUserid() != null) && (!"".equals(wx.getUserid())))
                    {
                        user = this.userService.selectByPrimaryKey(Integer.valueOf(Integer.parseInt(wx.getUserid())));
                    }
                }
                else
                {
                    wx = wxUser;
                    isnew = true;
                }
                if (user == null)
                {
                    user = new User();
                    user.setId(null);
                    user.setScore(null);
                    user.setRegisterTime(null);
                    user.setAllowArticle(null);
                    user.setPassword("123456789");
                    user.setName(wx.getOpenId());
                    user.setNickname(wx.getNickname());
                    user.setAddBackground(Boolean.valueOf(false));
                    user.setRole(Integer.valueOf(1));
                    user.setLocation(wx.getCity());
                    int effectRow=0;
                    try{

    effectRow = this.userService.insert(user);
}catch (Exception e){
 effectRow = 1;
}                   Map mapConfig=new HashMap();
                    mapConfig.put("userId",user.getId().intValue());
                    mapConfig.put("ogid",session.getAttribute("ogid"));
                    this.userGroupService.addUserToAllGroup(mapConfig);
                    if (effectRow > 0)
                    {
                        propertyController ec = new propertyController();
                        ec.insertUser(user.getName());
                    }

                }
                wx.setUserid(String.valueOf(user.getId()));
                wx.setLastRefreshTime(new Date());
                if (isnew)
                {
                    try{
                        this.weixinMapper.insert(wx);
                    }catch (Exception e){

                    }

                }
                else
                {
                    this.weixinMapper.update(wx);
                }

                UserSession userSession = this.userSessionService.selectByPrimaryKey(user.getId());
                if (userSession == null) {
                    String token = RandomStringUtils.random(31, true, true);
                    userSession = new UserSession();
                    userSession.setToken(token);
                    userSession.setUserId(user.getId());
                    try {
                        this.userSessionService.insert(userSession);
                    }
                    catch (Exception e)
                    {
                    }
                }
                //// TODO: 2017/3/9  新用户插入组织
                Map map=new HashMap();
                map.put("ogid",Integer.parseInt(session.getAttribute("ogid").toString()));
                map.put("uid",user.getId());
                if(organizationUserService.getCountByUidAndOgid(map)==0){
                    OrganizationUser ou=new OrganizationUser();
                    ou.setOgid(Integer.parseInt(session.getAttribute("ogid").toString()));
                    ou.setUid(user.getId());
                    organizationUserService.insert(ou);
                };


                try {
                    UserLoginLog userLoginLog = new UserLoginLog();
                    userLoginLog.setUserid(user.getId());
                    userLoginLog.setIp(request.getRemoteAddr());
                    //为用户分组
                    OrganizationGroup o=new OrganizationGroup();
                    String str =session.getAttribute("Organization").toString();
                    int b=0;
                    try {
                        b = Integer.valueOf(str).intValue();
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    try
                    {
                        this.userLoginLogMapper.insert(userLoginLog);
                    }
                    catch (Exception e)
                    {
                    }
                }
                catch (RuntimeException localRuntimeException) {
                }
                Cookie loginCookie = new Cookie("_wx_token", userSession.getToken());
                loginCookie.setDomain(domainUrl);
                loginCookie.setPath("/");
                loginCookie.setMaxAge(86400000);
                response.addCookie(loginCookie);
            }
            else
            {
                redirectURL = "";
            }


        }
        else
        {
            redirectURL = "http://www.baidu.com";
        }

        response.sendRedirect(redirectURL);
    }*/



   /* @RequestMapping({"/authdeny2"})
    public void authdeny2(HttpServletRequest request, HttpServletResponse response,HttpSession session)
            throws Exception
    {
        session.setAttribute("ogid",Base64.urlsplit(request.getParameter("from")));
        String redirectURL = "http://" + domainUrl + ":8080/lk-vc-h5/page/article.html?ogid="+Base64.urlsplit(request.getParameter("from"));
             String accessToken = String.valueOf(request.getParameter("access_token"));
            String refreshToken = String.valueOf(request.getParameter("refresh_token"));
            String openId = String.valueOf(request.getParameter("openid"));
        logger.info("--------accessToken---------------------------------------"+accessToken);
            logger.info("--------refreshToken---------------------------------------"+refreshToken);
           logger.info("--------openId---------------------------------------"+openId);
            if ((openId != null) && (!"".equals(openId)))
            {
                WeiXin wx = this.weixinMapper.selectAll(openId);
                Map uiMap=new HashMap();
                try{
                    uiMap = WeixinUtil.getUserInfo(openId, accessToken,refreshToken);

                    WeiXin wxUser = new WeiXin();
                    logger.info("----------------------------wx------------------------------"+uiMap.get("nickname"));
                    wxUser.setNickname(String.valueOf(uiMap.get("nickname")));
                    wxUser.setOpenId(openId);
                    wxUser.setCity(String.valueOf(uiMap.get("city")));
                    wxUser.setCountry(String.valueOf(uiMap.get("country")));
                    wxUser.setPrivilege(String.valueOf(uiMap.get("privilege")));
                    wxUser.setProvince(String.valueOf(uiMap.get("province")));
                    wxUser.setSex(String.valueOf(uiMap.get("sex")));
                    wxUser.setHeadImgURL(String.valueOf(uiMap.get("headimgurl")));
                    wxUser.setUnionId(String.valueOf(uiMap.get("unionid")));
                    wxUser.setAccessToken(accessToken);
                    wxUser.setRefreshToken(refreshToken);
                    User user = null;
                    boolean isnew = false;
                    if (wx != null)
                    {
                        if ((wx.getUserid() != null) && (!"".equals(wx.getUserid())))
                        {
                            user = this.userService.selectByPrimaryKey(Integer.valueOf(Integer.parseInt(wx.getUserid())));
                        }
                    }
                    else
                    {
                        wx = wxUser;
                        isnew = true;
                    }
                    if (user == null)
                    {
                        user = new User();
                        user.setPassword("123456789");
                        user.setName(wx.getOpenId());
                        user.setNickname(String.valueOf(uiMap.get("nickname")));
                        user.setAddBackground(Boolean.valueOf(false));
                        user.setRole(Integer.valueOf(1));
                        user.setLocation(String.valueOf(uiMap.get("city")));
                        int effectRow=0;
                        try{

                            effectRow = this.userService.insert(user);
                            logger.info("---User添加成功---------------------------------------"+user.getId());

                        }catch (Exception e){
                            effectRow = 1;
                        }

                        if (effectRow > 0)
                        {
                            propertyController ec = new propertyController();
                            ec.insertUser(user.getId().toString());
                        }
                        Map mapConfig=new HashMap();
                        logger.info("---userId-------添加讨论区----------------------------"+user.getId());
                        mapConfig.put("userId",user.getId().intValue());
                        mapConfig.put("ogid",session.getAttribute("ogid"));
                        this.userGroupService.addUserToAllGroup(mapConfig);
                    }

                    wx.setUserid(String.valueOf(user.getId()));
                    wx.setLastRefreshTime(new Date());
                    if (isnew)
                    {
                        try{
                            this.weixinMapper.insert(wx);
                        }catch (Exception e){

                        }

                    }
                    else
                    {
                        this.weixinMapper.update(wx);
                    }

                    UserSession userSession = this.userSessionService.selectByPrimaryKey(user.getId());
                    if (userSession == null) {
                        String token = RandomStringUtils.random(31, true, true);
                        userSession = new UserSession();
                        userSession.setToken(token);
                        userSession.setUserId(user.getId());
                        try {
                            this.userSessionService.insert(userSession);
                        }
                        catch (Exception e)
                        {
                        }
                    }
                    //用户插入组织
                    Map map=new HashMap();
                    map.put("ogid",Integer.parseInt(session.getAttribute("ogid").toString()));
                    map.put("uid",user.getId());
                    try{
                        Map mapConfig=new HashMap();
                        logger.info("-------userid-------"+user.getId().intValue());
                        mapConfig.put("userId",user.getId().intValue());
                        logger.info("---------ogid-----"+session.getAttribute("ogid"));
                        mapConfig.put("ogid",session.getAttribute("ogid"));
                        this.userGroupService.addUserToAllGroup(mapConfig);
                    }catch (Exception e){

                    }
                    if(organizationUserService.getCountByUidAndOgid(map)==0){
                        OrganizationUser ou=new OrganizationUser();
                        ou.setOgid(Integer.parseInt(session.getAttribute("ogid").toString()));
                        ou.setUid(user.getId());
                        organizationUserService.insert(ou);
                    };


                    try {
                        UserLoginLog userLoginLog = new UserLoginLog();
                        userLoginLog.setUserid(user.getId());
                        userLoginLog.setIp(request.getRemoteAddr());
                        try
                        {
                            this.userLoginLogMapper.insert(userLoginLog);
                        }
                        catch (Exception e)
                        {
                        }
                    }
                    catch (RuntimeException localRuntimeException) {
                    }
                    Cookie loginCookie = new Cookie("_wx_token", userSession.getToken());
                    loginCookie.setDomain(domainUrl);
                    loginCookie.setPath("/");
                    loginCookie.setMaxAge(86400000);
                    response.addCookie(loginCookie);
                }catch (Exception e){

                    redirectURL = "http://" + domainUrl + ":8080/lk-vc-h5/page/errorPage.html";

                }

            }
        response.sendRedirect(redirectURL);
    }*/


    @RequestMapping({"/authdeny2"})
    public void authdeny2(HttpServletRequest request, HttpServletResponse response,HttpSession session)
            throws Exception
    {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        Map atMap = WeixinUtil.getUserAccessToken(code);
        String accessToken = String.valueOf(atMap.get("access_token"));
        String refreshToken = String.valueOf(atMap.get("refresh_token"));
        String openId = String.valueOf(atMap.get("openid"));
       // session.setAttribute("ogid",Base64.urlsplit(request.getParameter("from")));
        String redirectURL = "http://" + domainUrl + "/lk-vc-h5/page/article.html?ogid="+session.getAttribute("ogid");

        if ((openId != null) && (!"".equals(openId))) {
            //查询本地微信表是否存在
            WeiXin wx = this.weixinMapper.selectAll(openId);
            Map uiMap=new HashMap();
            User user=new User();
            if(wx==null){
                //本地不存在
                uiMap = WeixinUtil.getUserInfo(openId, accessToken,refreshToken);
                //插入用户表
                user.setPassword("123456789");
                user.setName(openId);
                user.setNickname(String.valueOf(uiMap.get("nickname")));
                user.setAddBackground(Boolean.valueOf(false));
                user.setRole(Integer.valueOf(1));
                user.setLocation(String.valueOf(uiMap.get("city")));
                int effectRow=0;
                try{
                    effectRow = this.userService.insert(user);
                }catch (Exception e){
                  //  effectRow = 1;
                }
                WeiXin wxUser = new WeiXin();
                wxUser.setNickname(String.valueOf(uiMap.get("nickname")));
                wxUser.setOpenId(openId);
                wxUser.setUserid(String.valueOf(user.getId()));
                wxUser.setCity(String.valueOf(uiMap.get("city")));
                wxUser.setCountry(String.valueOf(uiMap.get("country")));
                wxUser.setPrivilege(String.valueOf(uiMap.get("privilege")));
                wxUser.setProvince(String.valueOf(uiMap.get("province")));
                wxUser.setSex(String.valueOf(uiMap.get("sex")));
                wxUser.setHeadImgURL(String.valueOf(uiMap.get("headimgurl")));
                wxUser.setUnionId(String.valueOf(uiMap.get("unionid")));
                wxUser.setAccessToken(accessToken);
                wxUser.setRefreshToken(refreshToken);
                this.weixinMapper.insert(wxUser);



                //添加扩展属性
                if (effectRow > 0)
                {
                    propertyController ec = new propertyController();
                    ec.insertUser(user.getId().toString(),Integer.parseInt(String
                            .valueOf(session.getAttribute("ogid"))));
                }
                Map mapConfig=new HashMap();
                mapConfig.put("userId",user.getId());
                mapConfig.put("ogid",session.getAttribute("ogid"));
                this.userGroupService.addUserToAllGroup(mapConfig);
                //添加组织
                Map map=new HashMap();
                map.put("ogid",Integer.parseInt(session.getAttribute("ogid").toString()));
                map.put("uid",user.getId());

                if(organizationUserService.getCountByUidAndOgid(map)==0){
                    OrganizationUser ou=new OrganizationUser();
                    ou.setOgid(Integer.parseInt(session.getAttribute("ogid").toString()));
                    ou.setUid(user.getId());
                    organizationUserService.insert(ou);
                };

                //添加登录信息
                try {
                    UserLoginLog userLoginLog = new UserLoginLog();
                    userLoginLog.setUserid(user.getId());
                    userLoginLog.setIp(request.getRemoteAddr());
                    this.userLoginLogMapper.insert(userLoginLog);
                }
                catch (Exception e) {
                }
                //添加登录用户session表信息
                UserSession userSession = this.userSessionService.selectByPrimaryKey(user.getId());
                if (userSession == null) {
                    userSession = new UserSession();
                    userSession.setToken(openId);
                    userSession.setUserId(user.getId());
                    try {
                        this.userSessionService.insert(userSession);
                    }
                    catch (Exception e)
                    {
                    }
                }
                //添加cookie到
                Cookie loginCookie = new Cookie("_wx_token",openId);
                loginCookie.setDomain(domainUrl);
                loginCookie.setPath("/");
                loginCookie.setMaxAge(86400000);
                response.addCookie(loginCookie);

            }else{
             //本地存在 修改已存入信息
                WeiXin wxUser = new WeiXin();
                wxUser.setNickname(String.valueOf(uiMap.get("nickname")));
                wxUser.setOpenId(openId);
                wxUser.setCity(String.valueOf(uiMap.get("city")));
                wxUser.setCountry(String.valueOf(uiMap.get("country")));
                wxUser.setPrivilege(String.valueOf(uiMap.get("privilege")));
                wxUser.setProvince(String.valueOf(uiMap.get("province")));
                wxUser.setSex(String.valueOf(uiMap.get("sex")));
                wxUser.setHeadImgURL(String.valueOf(uiMap.get("headimgurl")));
                wxUser.setUnionId(String.valueOf(uiMap.get("unionid")));
                wxUser.setAccessToken(accessToken);
                wxUser.setRefreshToken(refreshToken);
                logger.info("----------------------begin--update-----------------------------------------");
                this.weixinMapper.update(wxUser);
                logger.info("----------------------end--update-----------------------------------------");
                User users=userMapper.selectByName(openId);
                Map map=new HashMap();
                map.put("ogid",Integer.parseInt(session.getAttribute("ogid").toString()));
                map.put("uid",users.getId());
                if(organizationUserService.getCountByUidAndOgid(map)==0){
                    OrganizationUser ou=new OrganizationUser();
                    ou.setOgid(Integer.parseInt(session.getAttribute("ogid").toString()));
                    ou.setUid(users.getId());
                    organizationUserService.insert(ou);
                };
                map.put("userId",users.getId());
                this.userGroupService.addUserToAllGroup(map);
                //添加登录信息
                try {
                    UserLoginLog userLoginLog = new UserLoginLog();
                    userLoginLog.setUserid(users.getId());
                    userLoginLog.setIp(request.getRemoteAddr());
                    this.userLoginLogMapper.insert(userLoginLog);
                }catch (Exception e){}
                //添加登录用户session表信息
                UserSession userSession = this.userSessionService.selectByPrimaryKey(users.getId());
                if (userSession == null) {

                    userSession = new UserSession();
                    userSession.setToken(openId);
                    userSession.setUserId(users.getId());
                    try {
                        this.userSessionService.insert(userSession);
                    }
                    catch (Exception e)
                    {
                    }
                }


                //添加Cookie
                Cookie loginCookie = new Cookie("_wx_token",openId);
                loginCookie.setDomain(domainUrl);
                loginCookie.setPath("/");
                loginCookie.setMaxAge(86400000);
                response.addCookie(loginCookie);
            }


        }

        response.sendRedirect(redirectURL);
    }





    @RequestMapping({"/downloadMedia"})
    public void downloadMedia(String mediaId, HttpServletResponse res, HttpServletRequest req)
            throws Exception
    {
        String accessToken = WeixinUtil.getClientAccessToken();
        String picName = mediaId;
        String path = req.getSession().getServletContext().getRealPath("/") + "image/";
        saveImageToDisk(accessToken, mediaId, picName, path);

        res.getWriter().write("success");
    }

    public static InputStream getInputStream(String accessToken, String mediaId)
    {
        InputStream is = null;
        String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + mediaId;
        try
        {
            URL urlGet = new URL(url);

            HttpURLConnection http = (HttpURLConnection)urlGet.openConnection();

            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");
            http.connect();

            is = http.getInputStream();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }

    public static void saveImageToDisk(String accessToken, String mediaId, String picName, String picPath)
            throws Exception
    {
        InputStream inputStream = getInputStream(accessToken, mediaId);
        byte[] data = new byte[10240];
        int len = 0;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(picPath + picName + ".jpg");
            while ((len = inputStream.read(data)) != -1)
                fileOutputStream.write(data, 0, len);
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null)
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static void getPic(String accessToken, String mediaId)
    {
        String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);

        JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
        System.out.println(jsonObject);
    }
}