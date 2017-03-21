package com.wenjuan.controller;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.wenjuan.bean.MessageBean;
import com.wenjuan.dao.DraftMapper;
import com.wenjuan.dao.SensitiveMapper;
import com.wenjuan.dao.UserLoginLogMapper;
import com.wenjuan.model.*;
import com.wenjuan.service.*;
import com.wenjuan.service.impl.UserLikeArticleServiceImpl;
import com.wenjuan.utils.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    public static final String FLAG_FIRST_REQUST_MSG = "0";
    Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("diaryCommentService")
    private DiaryCommentService diaryCommentService;

    @Autowired
    @Qualifier("articleCommentService")
    private ArticleCommentService articleCommentService;

    @Autowired
    @Qualifier("userSessionService")
    private UserSessionService userSessionService;

       @Resource
    private UserLoginLogMapper userLoginLogMapper;

    @Resource
    private SensitiveMapper sensitiveMapper;



    @Resource
    private DraftMapper draftMapper;

    @Autowired
    @Qualifier("userFollowDiaryService")
    private UserFollowDiaryService userFollowDiaryService;

    @Autowired
    private UserLikeArticleService userLikeArticleService;

    @Autowired
    @Qualifier("articleService")
    private ArticleService articleService;

    @Autowired
    @Qualifier("diaryService")
    private DiaryService diaryService;

    @Autowired
    @Qualifier("feedbackService")
    private FeedbackService feedbackService;

    @Autowired
    @Qualifier("userGroupService")
    private UserGroupService userGroupService;

    @Autowired
    @Qualifier("groupService")
    private GroupService groupService;

    @Autowired
    @Qualifier("notifiesService")
    private NotifiesService notifiesService;
    @Autowired
    @Qualifier("SystemInfoService")
    private SystemInfoService systemInfoService;
    @Autowired
    @Qualifier("organizationUserService")
    private OrganizationUserService organizationUserService;
    @Autowired
    @Qualifier("organizationGroupService")
    private OrganizationGroupService organizationGroupService;
    /**
     * 修改用户信息
     *
     * @param user
     * @return extra为修改后的User对象
     */
    @RequestMapping(value = "/modify")
    public MessageBean modify(@ModelAttribute User user, HttpSession session) {
        User currUser = User.getCurrentUser();
        if (currUser == null) {
            return MessageBean.UNLOGIN;
        }
        if (user.getPassword() != null && !CheckUtil.checkPassword(user.getPassword())) {
            return MessageBean.getInvalidField("密码");
        }

        //替换敏感字

        String con = sensitiveMapper.selectSensitive(Integer.parseInt(session.getAttribute("ogid").toString()));
        String[] str = con.split("\\,");
        int type=0;
        for (int i = 0; i < str.length; i++) {
            String rep = str[i];
            if (rep.trim().length() == 0) {
                continue;
            }
            String replen = "";
            for (int f = 0; f < rep.length(); f++) {
                replen += "*";
            }
            if(!"".equals(user.getRealname())){
                while (user.getRealname().indexOf(rep) != -1) {
                    type=1;
                    user.setRealname(user.getRealname().replace(rep, replen));
                }
            }
            if(!"".equals(user.getNickname())){
                while (user.getNickname().indexOf(rep) != -1) {
                    type=1;
                    user.setNickname(user.getNickname().replace(rep, replen));
                }
            }

        }



        user.setName(null);//不可修改用户名
        user.setQueryLastFeedback(null);
        user.setQueryLastDiaryLike(null);
        user.setQueryLastDiaryComment(null);
        user.setQueryLastArticleComment(null);
        user.setId(currUser.getId());
        user.setScore(null);
        int effectRow = userService.updateByPrimaryKey(user);
        MessageBean messageBean;
        if (effectRow > 0) {
            messageBean = MessageBean.SUCCESS.clone();
            messageBean.setExtra(userService.selectByPrimaryKey(currUser.getId()));
        } else {
            messageBean = MessageBean.SYSTEM_ERROR;
        }
        return messageBean;
    }

    /**
     * 获取用户信息
     *
     * @return extra中User实例对象
     */
    @RequestMapping(value = "/getUserInfo")
    public MessageBean getUserInfo() {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        } else {
            MessageBean messageBean = MessageBean.SUCCESS.clone();
            messageBean.setExtra(userService.selectByPrimaryKey(user.getId()));
            return messageBean;
        }
    }

    /**
     * 用户注册
     *
     * @param user
     * @return extra中最新的用户信息。User实例对象
     * @throws SQLException
     */
    @RequestMapping(value = {"/register"})
    public MessageBean register(String url,@ModelAttribute User user, @RequestParam(defaultValue = "true") boolean addToAllGroup,HttpSession session) throws SQLException {
        session.setAttribute("ogid",organizationGroupService.selectOgid(Base64.getFromBase64(url)));



        if (user.getPassword() == null || !CheckUtil.checkPassword(user.getPassword())) {
            return MessageBean.getInvalidField("密码");
        }
        if (user.getName() == null || !CheckUtil.checkName(user.getName())) {
            return MessageBean.getInvalidField("用户名");
        }
        user.setId(null);
        user.setScore(null);
        user.setRegisterTime(null);
        user.setAllowArticle(null);


        //替换敏感字
        String con = sensitiveMapper.selectSensitive(Integer.parseInt(session.getAttribute("ogid").toString()));
        String[] str = con.split("\\,");
        int type=0;
        for (int i = 0; i < str.length; i++) {
            String rep = str[i];
            if (rep.trim().length() == 0) {
                continue;
            }
            String replen = "";
            for (int f = 0; f < rep.length(); f++) {
                replen += "*";
            }
            try{
                if(!"".equals(user.getName())){
                    while (user.getName().indexOf(rep) != -1) {
                        type=1;
                        user.setName(user.getName().replace(rep, replen));
                    }
                }
            }catch (Exception e){

            }  try{

                if(!"".equals(user.getNickname())){
                    while (user.getNickname().indexOf(rep) != -1) {
                        type=1;
                        user.setNickname(user.getNickname().replace(rep, replen));
                    }
                }
            }catch (Exception e){

            }


        }


        User existUser = userService.selectByName(user.getName());
        if (existUser != null) {
            return MessageBean.USER_ALREADY_EXIST;
        }
        int effectRow = userService.insert(user);
        if (addToAllGroup) {
            Map mapConfig=new HashMap();
            mapConfig.put("userId",user.getId());
            mapConfig.put("ogid",session.getAttribute("ogid"));
            userGroupService.addUserToAllGroup(mapConfig);
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
        if (effectRow > 0) {
            //添加扩展列默认值
            propertyController ec = new propertyController();
            ec.insertUser(user.getName(),Integer.parseInt(String.valueOf(map.get("ogid"))));

            MessageBean messageBean = MessageBean.SUCCESS.clone();
            messageBean.setExtra(userService.selectByName(user.getName()));
            return messageBean;
        } else {
            return MessageBean.SYSTEM_ERROR;
        }
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回SUCCESS，extra中为tokenextraList中为User实例对象
     * 失败返回 ERROR_USERNAME_PASSWORD
     */
    @RequestMapping(value = "/login")
    public MessageBean login(@RequestParam String username, @RequestParam String password, HttpServletRequest request,@RequestParam String url,HttpSession session) {
      //获取用户当前?url
        session.setAttribute("ogid",organizationGroupService.selectOgid(Base64.getFromBase64(url)));
        final User user = userService.selectByUsernamePassword(username, password);
        if (user == null) {
            return MessageBean.ERROR_USERNAME_PASSWORD;
        } else if (!user.isTypicalUser()) {
            return MessageBean.ADMIN_CANNOT_LOGIN;
        } else {
            MessageBean messageBean = MessageBean.SUCCESS.clone();
            UserSession userSession = userSessionService.selectByPrimaryKey(user.getId());
            if (userSession == null) {
                String token = org.apache.commons.lang3.RandomStringUtils.random(31, true, true);
                userSession = new UserSession();
                userSession.setToken(token);
                userSession.setUserId(user.getId());
                userSessionService.insert(userSession);
            }
            try {
                UserLoginLog userLoginLog = new UserLoginLog();
                userLoginLog.setUserid(user.getId());
                userLoginLog.setIp(request.getRemoteAddr());
                userLoginLogMapper.insert(userLoginLog);
            } catch (RuntimeException r) {
                //r.printStackTrace();
            }
            Map<String, Object> map = new HashMap<>();
            map.put("token", userSession.getToken());
            map.put("user", user);
            messageBean.setExtra(map);
            return messageBean;
        }
    }

    /**
     * 更新用户信息
     *
     * @param newUserInfo
     * @return
     */
    @RequestMapping("/update")
    public MessageBean updateUserInfo(@ModelAttribute User newUserInfo,HttpSession session) {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }


        //替换敏感字
        String con = sensitiveMapper.selectSensitive(Integer.parseInt(session.getAttribute("ogid").toString()));
        String[] str = con.split("\\,");
        for (int i = 0; i < str.length; i++) {
            String rep = str[i];
            if (rep.trim().length() == 0) {
                continue;
            }
            String replen = "";
            for (int f = 0; f < rep.length(); f++) {
                replen += "*";
            }
            try {
                if(!"".equals(newUserInfo.getRealname())){
                    while (newUserInfo.getRealname().indexOf(rep) != -1) {
                        newUserInfo.setRealname(newUserInfo.getRealname().replace(rep, replen));
                    }
                }
            }catch (Exception e){

            }

            try {
                if(!"".equals(newUserInfo.getNickname())){
                    while (newUserInfo.getNickname().indexOf(rep) != -1) {
                        newUserInfo.setNickname(newUserInfo.getNickname().replace(rep, replen));
                    }
                }

            }catch (Exception e){

        }

        }




        newUserInfo.setId(user.getId());
        newUserInfo.setRegisterTime(null);
        newUserInfo.setQueryLastArticleComment(null);
        newUserInfo.setQueryLastDiaryLike(null);
        newUserInfo.setQueryLastFeedback(null);
        newUserInfo.setQueryLastDiaryComment(null);
        newUserInfo.setRole(null);
        newUserInfo.setScore(null);
        userService.updateByPrimaryKey(newUserInfo);
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtra(userService.selectByPrimaryKey(user.getId()));
        return messageBean;
    }

    /**
     * @return
     */
    @RequestMapping(value = "/logout")
    public MessageBean logout() {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        UserSession userSession = userSessionService.selectByPrimaryKey(user.getId());
        if (userSession != null) {
            userSessionService.deleteByUserId(user.getId());
        }
        UserLoginLog userLoginLog = userLoginLogMapper.selectLastLoginLog(user.getId());
        if (userLoginLog != null) {
            userLoginLog.setLogoutTime(new Date());
            userLoginLogMapper.updateByPrimaryKey(userLoginLog);
            long timeDiff = (userLoginLog.getLogoutTime().getTime() - userLoginLog.getLoginTime().getTime()) / (1000 * 60);//单位 : min
            int login_time_level = 0;
            if (timeDiff < 30) {
                login_time_level = ScoreUtil.LOG_IN_BELOW_30;
            } else if (timeDiff < 60) {
                login_time_level = ScoreUtil.LOG_IN_BELOW_60;
            } else if (timeDiff < 80) {
                login_time_level = ScoreUtil.LOG_IN_BELOW_80;
            } else {
                login_time_level = ScoreUtil.LOG_IN_UPON_80;
            }
            ScoreUtil.addScore(user.getId(), login_time_level);
        }
        return MessageBean.SUCCESS;
    }

    /**
     * 查看赞过我的用户列表
     *
     * @return extraList中的User实例数组
     */
    @RequestMapping(value = "/getFavorUser")
    public MessageBean getFavorUser() {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtraList(userService.getFavorMe(user.getId()).toArray());
        return messageBean;
    }

    /**
     * 获取所有已存在的用户组
     *
     * @return extraList中String对象
     */
    @RequestMapping(value = "/allExistGroup")
    public MessageBean getAllExistGroup() {
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtraList(groupService.getExistsGroupInfo(true).toArray());
        return messageBean;
    }

    /**
     * 获取我的讨论区信息
     *
     * @return
     */
    @RequestMapping("/myGroup")
    public MessageBean getMyGroup() {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        Group[] myGroup = groupService.getMyGroup(user.getId());
        messageBean.setExtraList(myGroup);
        return messageBean;
    }

    /**
     * 显示在前台的组信息
     * 附带推送文章信息
     *
     * @return
     */
    @RequestMapping("/groupInfo")
    public MessageBean getGroupInfo(HttpSession session){
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtraList(groupService.selectForegroundGroup(session.getAttribute("ogid").toString()));
        Map map=new HashMap();
        map.put("uid",User.getCurrentUser().getId());
        map.put("ogid",session.getAttribute("ogid"));
        List<Article> la=articleService.getPushList(map);


        messageBean.setExtra(la);
        return messageBean;
    }

    /**
     * 获取该组下的所有用户成员
     *
     * @param grp 组名
     * @return extraList中User对象
     */
    @RequestMapping(value = "/underGrp")
    public MessageBean getUserListUnderGrp(@RequestParam String grp,
                                           @RequestParam(required = false) Integer sex,
                                           @RequestParam(required = false) Integer ageS,
                                           @RequestParam(required = false) Integer ageE,
                                           @RequestParam(required = false) String city,
                                           @RequestParam(required = false) String degree,
                                           @RequestParam(required = false) String marriage) {
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        Map<String, Object> config = new HashMap<>();
        config.put("grp", grp);
        config.put("sex", sex);
        config.put("ageS", ageS);
        config.put("ageE", ageE);
        config.put("city", CommonUtil.processLikeParameter(city));
        config.put("degree", degree);
        config.put("marriage", marriage);
        List<User> users = userService.getUserListUnderGrp(config);
        messageBean.setExtraList(users.toArray());
        return messageBean;
    }

    /**
     * 通过用户名（电话号码）查询用户信息
     * 多个用户名用逗号分隔
     *
     * @param name
     * @return
     */
    @RequestMapping("/userInfoByName")
    public MessageBean getUserInfoByName(@RequestParam String name) {
        if (name == null) {
            return MessageBean.getInvalidField("用户名");
        }
        String[] names = name.split(",");
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtraList(userService.selectByNames(names));
        return messageBean;
    }

    /**
     * 查看回复我的内容
     *
     * @param type       回复类型，0为话题消息数量，1为日记消息数量，2为话题回复消息，3为日记回复消息，默认为0
     * @param clearCount 是否清空读取消息
     * @return extraList中的ArticleComment实例数组
     */
    @RequestMapping(value = "/getReplyInfo")
    public MessageBean getReplyInfo(@RequestParam(defaultValue = "0") Integer type, @RequestParam(defaultValue = "false") boolean clearCount) {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        switch (type) {
            case 0:
            case 1:
                messageBean.setExtra(userService.getNewMsgCount(user.getId(), type));
                break;
            case 2:
                messageBean.setExtraList(articleCommentService.selectNewComment(user.getId()).toArray());
                break;
            case 3:
                messageBean.setExtraList(diaryCommentService.selectNewComment(user.getId()).toArray());
                break;
            default:
                messageBean = MessageBean.getInvalidField("类型字段");
        }
        if (messageBean.getCode() == MessageBean.SUCCESS.getCode() && clearCount) {
            if (type % 2 == 0) {
                articleCommentService.updateCommentViewTime(user.getId());
            } else {
                diaryCommentService.updateCommentViewTime(user.getId());
            }
        }
        return messageBean;
    }

    /**
     * @param isFollowed true为被赞，false为赞他人
     * @param loadTime   A/b后面接的是评论时间，时间戳，单位为秒
     * @param page       0不分页
     * @param order      默认time desc
     * @return
     */
    @RequestMapping("/likeInfo")
    public MessageBean getLikeInfo(@RequestParam boolean isFollowed,
                                   @RequestParam String loadTime,
                                   @RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "time desc") String order,
                                   @RequestParam(defaultValue = "false") Boolean isArticle,HttpSession session) {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        int ogid=Integer.parseInt(session.getAttribute("ogid").toString());
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        if(!isArticle) {
            messageBean.setExtraList(userFollowDiaryService.selectFollowInfo(isFollowed, user.getId(), page, order, loadTime,ogid).toArray());
        }else{
            messageBean.setExtraList(userLikeArticleService.selectFollowInfo(isFollowed,user.getId(),page,order,loadTime,ogid).toArray());
        }
        return messageBean;
    }

    /**
     * 获取环信组里面的用户的用户基本信息
     *
     * @param grpName
     * @return
     */
    @RequestMapping("/underHxGrp")
    public MessageBean getUserListUnderHxGrp(@RequestParam String grpName) {
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtra(HxUtil.getUserListUnderHxGrp(grpName));
        return messageBean;
    }

    /**
     * 获取用户的环信群组信息
     *
     * @return
     */
    @RequestMapping("/hxGroup")
    public MessageBean getUserHxGroup() {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtraList(HxUtil.getAll(user.getName()).toArray());
        return messageBean;
    }

    @RequestMapping("/friendInfo")
    public MessageBean getUserFriendInfo() {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtraList(HxUtil.getfriend(user).toArray());
        return messageBean;
    }

    /**
     * @param loadDiary    A/B+id
     * @param loadArticle  A/B+id
     * @param loadACTime   A/B+Time ,Article Comment
     * @param loadDCTime   A/B+Time ,DiaryComment
     * @param loadFeedback A/B+ReplyTime New Reply Feedback
     * @param loadLike
     * @return
     */
    @RequestMapping("/newMsgCount")
    public MessageBean unreadMessageCount(@RequestParam(required = false) String loadDiary,
                                          @RequestParam(required = false) String loadArticle,
                                          @RequestParam(required = false) Integer grpId,
                                          @RequestParam(required = false) String loadACTime,
                                          @RequestParam(required = false) String loadDCTime,
                                          @RequestParam(required = false) String loadFeedback,
                                          @RequestParam(required = false) String loadLike,
                                          @RequestParam(required = false) String loadArticleGroup,
                                          @RequestParam(required = false) String loadPush,
                                          HttpSession session) {
        User user = User.getCurrentUser();
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        HashMap<String, Object> newMsgCount = new HashMap<>();
        messageBean.setExtra(newMsgCount);
        if (!StringUtils.isEmpty(loadArticle)) {
            newMsgCount.put("article", articleService.selectArticlesCount(grpId, 1, user, loadArticle, false,Integer.parseInt(session.getAttribute("ogid").toString())));
        }
        if (!StringUtils.isEmpty(loadDiary)) {
            newMsgCount.put("diary", diaryService.selectDiariesCount(1, user, loadDiary,Integer.parseInt(session.getAttribute("ogid").toString())));
        }
        if (!StringUtils.isEmpty(loadACTime)) {
            newMsgCount.put("articleReply", articleCommentService.selectMyReceiveMsgCount(loadACTime, user));
        }
        if (!StringUtils.isEmpty(loadDCTime)) {
            newMsgCount.put("diaryReply", diaryCommentService.selectMyReceiveMsgCount(loadDCTime, user));
        }
        if (!StringUtils.isEmpty(loadFeedback)) {
            newMsgCount.put("feedback", feedbackService.selectNewReplyFeedbackCount(user));
        }
        if (!StringUtils.isEmpty(loadLike) && user != null) {
            newMsgCount.put("likeDiary", userFollowDiaryService.selectFollowCount(loadLike, user.getId()));
        }
        if (user != null && !StringUtils.isEmpty(loadArticleGroup)) {
            newMsgCount.put("groupMsg", userGroupService.selectArticleGroupMsgCount(user));
        }
        if (!StringUtils.isEmpty(loadPush)) {
            newMsgCount.put("push", notifiesService.selectPushCount(loadPush, user));
        }
        return messageBean;
    }

    /**
     * @param loadACTime
     * @param loadDCTime
     * @param loadLike
     * @return
     */
    @RequestMapping(value = "/clearMessageCount")
    public MessageBean clearMessageCount(@RequestParam(required = false) Integer loadDiary,
                                         @RequestParam(required = false) Integer loadArticle,
                                         @RequestParam(required = false) String loadACTime,
                                         @RequestParam(required = false) String loadDCTime,
                                         @RequestParam(required = false) String loadLike) {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        Date now = new Date();
        User newUserInfo = new User();
        newUserInfo.setId(user.getId());
        boolean isQuery = false;
        if (!StringUtils.isEmpty(loadDiary)) {
            newUserInfo.setQueryLastDiaryId(loadDiary);
            isQuery = true;
        }
        if (!StringUtils.isEmpty(loadArticle)) {
            newUserInfo.setQueryLastArticleId(loadArticle);
        }
        if (!StringUtils.isEmpty(loadACTime)) {
            newUserInfo.setQueryLastArticleComment(now);
            isQuery = true;
        }
        if (!StringUtils.isEmpty(loadDCTime)) {
            newUserInfo.setQueryLastDiaryComment(now);
            isQuery = true;
        }
        if (!StringUtils.isEmpty(loadLike)) {
            newUserInfo.setQueryLastDiaryLike(now);
            isQuery = true;
        }
        if (isQuery) {
            userService.updateByPrimaryKey(newUserInfo);
        }
        return MessageBean.SUCCESS;
    }
    @RequestMapping("/findPassWord")
    public MessageBean findPassWord(String account,String passWord){
       User user=userService.selectByName(account);
        user.setPassword(passWord);
        userService.updateByPrimaryKey(user);
        return MessageBean.SUCCESS;
    }

    /**
     * 查看草稿
     * 1帖子2日记
     * 3.帖子评论草稿
     */
    @RequestMapping("/selectDraft")
    public MessageBean selectDraft(String userId,String type){
        Map map=new HashMap();
        map.put("uid",userId);
        map.put("type",type);
        Draft draft=draftMapper.selectDraft(map);
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtra(draft);
        return  messageBean;
    }

    /**
     * 添加草稿
     * @param draft
     */
    @RequestMapping("/insertDraft")
    public  MessageBean insertDraft(Draft draft){
    draftMapper.insert(draft);
        return MessageBean.SUCCESS;
    }

    /**
     * 修改敏感词
     * @param content
     * @return
     */
    @RequestMapping("/updateSensitive")
    public MessageBean updateSensitive(String content){
        User user=User.getCurrentUser();

        Map map=new HashMap();
        map.put("ogid",organizationGroupService.selectByOAdmin(user.getId()));
        map.put("content",content);
        sensitiveMapper.updateSensitive(map);
        return MessageBean.SUCCESS;
    }

    /**
     * 查询敏感词
     * @param type 1后台0用户端
     * @return
     */
    @RequestMapping("/selectSensitive")
    public MessageBean selectSensitive(@RequestParam(defaultValue ="0") String type, HttpSession session){
      String content;
        if(type.equals("1")){
            User userAdmin=User.getCurrentUser();
             content=sensitiveMapper.selectSensitive(organizationGroupService.selectByOAdmin(userAdmin.getId()));

        }else{
             content=sensitiveMapper.selectSensitive(Integer.parseInt(session.getAttribute("ogid").toString()));

        }
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        if(!type.equals("1")){
            String[] str = content.split("\\,");
            messageBean.setExtraList(str);
        }else{
            messageBean.setExtra(content);
        }

        return messageBean;
    }

    /**
     *配置信息获取
     * @param
     * @param res
     * @throws Exception
     */
    @RequestMapping("/getSystem")
    public void getSystem(HttpServletResponse res,HttpSession session)throws  Exception{

        Map map=new HashMap();
        map.put("id",1);
        map.put("ogid",session.getAttribute("ogid"));
        SystemInfo s= systemInfoService.getSystemInfoByid(map);
        res.getWriter().write(s.getValue());
    }
}
