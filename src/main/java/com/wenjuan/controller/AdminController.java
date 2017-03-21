package com.wenjuan.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wenjuan.bean.AvailBean;
import com.wenjuan.bean.MessageBean;
import com.wenjuan.dao.AdminRecordMapper;
import com.wenjuan.dao.AppVersionMapper;
import com.wenjuan.dao.UserMapper;
import com.wenjuan.model.*;
import com.wenjuan.service.*;
import com.wenjuan.utils.*;
import com.wowtour.jersey.api.EasemobChatGroups;
import com.wowtour.jersey.api.EasemobMessages;
import com.wowtour.jersey.comm.Constants;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/admin")
public class AdminController {
  private static final String APPKEY = Constants.APPKEY;
  public static String SESSION_KEY_ADMIN_USER = "adminUser";
  public static String SESSION_KEY_OPERATOR_RECORD = "admin_operation";

  private static JsonNodeFactory factory = new JsonNodeFactory(false);
  @Autowired
  UserMapper userMapper;
  @Autowired
  @Qualifier("SystemInfoService")
  private SystemInfoService systemInfoService;
  @Autowired
  AdminRecordMapper adminRecordMapper;
  @Autowired
  @Qualifier("articleCommentService")
  private ArticleCommentService articleCommentService;

  @Autowired
  @Qualifier("articleService")
  private ArticleService articleService;

  @Autowired
  @Qualifier("diaryCommentService")
  private DiaryCommentService diaryCommentService;

  @Autowired
  @Qualifier("articleEnableService")
  private ArticleEnableService articleEnableService;

  @Autowired
  @Qualifier("diaryService")
  private DiaryService diaryService;

  @Autowired
  @Qualifier("feedbackService")
  private FeedbackService feedbackService;

  @Autowired
  @Qualifier("goodService")
  private GoodService goodService;

  @Autowired
  @Qualifier("helpService")
  private HelpService helpService;


  @Autowired
  @Qualifier("userService")
  private UserService userService;

  @Autowired
  @Qualifier("groupService")
  private GroupService groupService;

  @Autowired
  @Qualifier("userGroupService")
  private UserGroupService userGroupService;

  @Resource
  private AppVersionMapper appVersionMapper;

  @Resource
  private HttpSession session;
  @Autowired
  @Qualifier("organizationUserService")
  private OrganizationUserService organizationUserService;
  @Autowired
  @Qualifier("organizationGroupService")
  private OrganizationGroupService organizationGroupService;
  /**
   * 验证管理员用户登录
   *
   * @param username
   * @param password
   * @return extra中User对象
   */
  @RequestMapping(value = "/login")
  public MessageBean login(@RequestParam String username, @RequestParam String password, HttpSession httpSession) {
    User adminUsers = userService.selectByUsernamePassword(username, password);
    MessageBean messageBean;
//        String description = "";
    if (adminUsers == null) {
      messageBean = MessageBean.ERROR_USERNAME_PASSWORD;
//            description = String.format("用户%s登录失败！", username);
    } else if (adminUsers.isAdmin()) {
      messageBean = MessageBean.SUCCESS.clone();
      messageBean.setExtra(adminUsers);
      httpSession.setAttribute(SESSION_KEY_ADMIN_USER, adminUsers);
//            description = String.format("用户%s登录成功！", username);
    } else {
      messageBean = MessageBean.PERMISSION_DENIED;
//            description = String.format("普通用户%s登录成功！权限不足", username);
    }
    //session.setAttribute(SESSION_KEY_OPERATOR_RECORD, description);
    return messageBean;
  }

  @RequestMapping(value = "/login2")
  public ModelAndView login2(@RequestParam String username, @RequestParam String password, HttpSession httpSession) {
    User adminUsers = userService.selectByUsernamePassword(username, password);
    ModelAndView mav=new ModelAndView();
    if (adminUsers == null) {
      mav.setViewName("/Login");
    } else if (adminUsers.isAdmin()) {
      httpSession.setAttribute(SESSION_KEY_ADMIN_USER, adminUsers);
      mav.setViewName("redirect:Sensitive.do");
    }


    return mav;
  }




  @RequestMapping("/logout")
  public MessageBean logout(HttpSession httpSession) {
    httpSession.setAttribute(SESSION_KEY_ADMIN_USER, null);
    return MessageBean.SUCCESS;
  }

  @RequestMapping("/logoutWeb")
  public ModelAndView logoutWeb(HttpSession httpSession) {
    httpSession.setAttribute(SESSION_KEY_ADMIN_USER, null);
    ModelAndView mav=new ModelAndView();
    mav.setViewName("/Login");
    return mav;
  }

  /**
   * 删除管理员
   *
   * @param id 管理员id
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "/admin/delete")
  public void adminDelete(@RequestParam int id, HttpSession session, HttpServletResponse res) throws IOException {
    User ad = User.getCurrentUser();
    //是否为特级管理员
    if (ad.role != 3) {
      res.getWriter().write("role");
    } else {

      if (ad.getId() == id) {
        res.getWriter().write("NO");
      } else {
        //查询是否为同时为用户
        User user = userService.selectByPrimaryKey(id);
        int returnCode = 0;
        if (user.isTypicalUser()) {
          user.setRole(1);
          returnCode = userService.updateByPrimaryKey(user);
        } else {
          returnCode = userService.deleteByPrimaryKey(id);
        }
        session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("删除管理员'%s'", user.getName()));
        res.getWriter().write("success");
      }
    }


  }

  /**
   * 修改或增加管理员
   * 以设置用户id为准
   *
   * @param username
   * @param password
   * @param id       若设置id，为更新管理员信息
   * @return
   */
  @RequestMapping(value = "/admin/new")
  public MessageBean adminNew(@RequestParam String username, @RequestParam String password, @RequestParam(required = false) Integer id) {
    User adminUsers = new User();
    MessageBean messageBean;
    if (!CheckUtil.checkName(username)) {
      messageBean = MessageBean.getInvalidField("用户名");
    } else if (!CheckUtil.checkPassword(password)) {
      messageBean = MessageBean.getInvalidField("密码");
    } else {
      session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("添加管理员'%s'成功", username));
      adminUsers.setName(username);
      adminUsers.setPassword(password);
      adminUsers.setId(id);
      if (id == null) {
        return MessageBean.getMessageBean(userService.insertAdminUser(adminUsers), MessageBean.ErrorMessageType.USER_ALREADY_EXIST);
      } else {
        return MessageBean.getMessageBean(userService.updateByPrimaryKey(adminUsers), MessageBean.ErrorMessageType.USER_NOT_EXIST);
      }
    }
    return messageBean;
  }

  /**
   * 删除日记
   *
   * @param id 日记id
   * @return
   */
  @RequestMapping(value = "/diary/delete")
  public MessageBean diaryDelete(@RequestParam int id) {
    int returnCode = diaryService.deleteByPrimaryKey(id);
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("删除id为'%d'的日记", id));
    return MessageBean.getMessageBean(returnCode, MessageBean.ErrorMessageType.ERROR_OPERATION);
  }

  /**
   * 删除用户
   *
   * @param id 用户id
   * @return
   */
  @RequestMapping(value = "/user/delete")
  public MessageBean userDelete(@RequestParam int id) {
    User user = userService.selectByPrimaryKey(id);
    int returnCode = 0;
    if (user.getRole() == 3) {
      user.setRole(2);
      returnCode = userService.updateByPrimaryKey(user);
    } else {
      returnCode = userService.deleteByPrimaryKey(id);
      HxUtil hxUtil = new HxUtil();
      hxUtil.deleteUser(user);
    }
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("删除用户'%s'", user.getName()));
    return MessageBean.getMessageBean(returnCode, MessageBean.ErrorMessageType.ERROR_OPERATION);
  }

  /**
   * 修改或增加用户
   * 以用户id为标识
   *
   * @param user 例如name="test"等等
   * @return
   * @throws SQLException
   * @throws IOException
   */
  @RequestMapping(value = "/user/new")
  public MessageBean userNew(@ModelAttribute User user) throws SQLException, IOException {
    MessageBean messageBean = null;
    if (user.getPassword() == null || !CheckUtil.checkPassword(user.getPassword())) {
      messageBean = MessageBean.getInvalidField("密码");
    } else if (user.getName() == null || !CheckUtil.checkName(user.getName())) {
      messageBean = MessageBean.getInvalidField("用户名");
    } else {
      User userByName = userService.selectByName(user.getName());
      if (user.getId() == null && userByName == null) {//新建
        user.setAddBackground(true);
        userService.insert(user);
        HxUtil hu = new HxUtil();
        hu.insertUser(user);
        User u = userService.selectByName(user.getName());
        propertyController ec = new propertyController();

        //给用户插入指定组织
        OrganizationUser o=new OrganizationUser();
        o.setUid(u.getId());
        User userAdmin=User.getCurrentUser();
        o.setOgid(organizationGroupService.selectByOAdmin(userAdmin.getId()));
        organizationUserService.insert(o);
        ec.insertUser(u.getId().toString(),o.getOgid());
      } else if (user.getId() != null) { //修改数据
        userService.updateByPrimaryKey(user);
      } else {
        messageBean = MessageBean.USER_ALREADY_EXIST.clone();
      }
    }
    if (messageBean == null) messageBean = MessageBean.SUCCESS;
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("添加用户'%s'" + messageBean.getMessage(), user.getName()));
    return messageBean;
  }

  /**
   * 设置用户是否可以发布话题
   *
   * @param id     用户id
   * @param enable 0为不允许，1为允许
   * @return
   */
  @RequestMapping(value = "/user/allowArticle")
  public MessageBean userAllowArticle(@RequestParam int id, @RequestParam int enable) {
    User user = new User();
    user.setId(id);
    user.setAllowArticle(enable != 0);
    int effectRow = userService.updateByPrimaryKey(user);
    MessageBean messageBean;
    if (effectRow == 0) {
      messageBean = MessageBean.USER_NOT_EXIST.clone();
    } else {
      messageBean = MessageBean.SUCCESS.clone();
    }
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD,
        String.format("设置用户'%s'发布话题状况" + user.getAllowArticle() + "," + messageBean.getMessage(), user.getName()));
    return messageBean;
  }

  /**
   * 删除话题
   *
   * @param id 话题id
   * @return
   */
  @RequestMapping(value = "/article/delete")
  public MessageBean articleDelete(@RequestParam int id) {
    Article article = articleService.selectByPrimaryKey(id, null);
    MessageBean messageBean;
    if (article == null) {
      messageBean = MessageBean.ARTICLE_NOT_EXIST.clone();
    } else {
      article.setType(3);
      articleService.updateByPrimaryKey(article);
      messageBean = MessageBean.SUCCESS.clone();
    }

    session.setAttribute(SESSION_KEY_OPERATOR_RECORD,
        String.format("删除话题'%s'", article.getTitle()));
    return messageBean;
  }

  //存入话题标题
  @RequestMapping("/article/new/title")
  public void title(String title, HttpSession session, HttpServletResponse res, String group, String user, HttpServletResponse req) throws IOException {
    String c = title;
    String availUserOrGrp = "{group : [" + group + "],user:[" + user + "] }";
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("group", group == "" ? null : group.split(","));
    map.put("user", user == null ? null : user.split(","));
    JSON json = JSONSerializer.toJSON(map);
    session.setAttribute("availUserOrGrp", json);
    session.setAttribute("article", c);
    res.getWriter().write("success");
  }

  @RequestMapping("/article/new")
  public MessageBean articleNew(@RequestParam(required = false) int gid, @RequestParam String title, @RequestParam String content, @RequestParam(required = false) String availUserOrGrp, HttpSession session) throws UnsupportedEncodingException {
    Article article = new Article();
    article.setGroupId(gid);
    article.setTitle(title);
    article.setContent(content);
    User ad = (User) session.getAttribute("adminUser");
    article.setAuthor(ad.getId());
    MessageBean messageBean;
    if (StringUtils.isEmpty(article.getContent())) {
      messageBean = MessageBean.getInvalidField("内容");
    } else if (StringUtils.isEmpty(article.getTitle())) {
      messageBean = MessageBean.getInvalidField("标题");
    } else {
      article.setPublishBackground(true);
      articleService.insert(article);
      messageBean = MessageBean.SUCCESS.clone();
      ArticleUtil.setAvail(articleEnableService, article.getId(), availUserOrGrp);
    }
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("添加话题'%s'", article.getTitle()));
    return messageBean;
  }

  /**
   * 切换置顶话题状态
   *
   * @param id 话题id
   * @return
   */
  @RequestMapping(value = "/article/top")
  public MessageBean articleTop(@RequestParam int id) {
    int effectRow = articleService.toggleTop(id);
    MessageBean messageBean;
    if (effectRow == 0) {
      messageBean = MessageBean.ARTICLE_NOT_EXIST.clone();
    } else {
      messageBean = MessageBean.SUCCESS.clone();
    }
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("置顶话题'%s'", id));
    return messageBean;
  }

  /**
   * 设置文章的用户或组的可见权限
   *
   * @param articleId 话题id
   * @param type      类型
   *                  0为增用户
   *                  1为增组
   *                  2为减用户
   *                  3为减组
   * @param name      名,多个名字用英文封号分割
   * @return
   */
  @RequestMapping("/article/avail")
  public MessageBean articleAvail(@RequestParam int articleId, @RequestParam int type, @RequestParam String name) {
    Article article = articleService.selectByPrimaryKey(articleId, null);
    if (article == null) {
      return MessageBean.ARTICLE_NOT_EXIST;
    }
    boolean add = (type & 0x2) == 0;
    boolean isUser = (type % 2) == 0;
    String[] names = name.split(";");
    ArticleEnable articleEnable = new ArticleEnable();
    articleEnable.setArticleId(articleId);
    articleEnable.setEnable(isUser ? 2 : 1);
    int effectRow = 0;
    for (String grpOrUserName : names) {
      articleEnable.setName(grpOrUserName);
      if (add) {
        effectRow += articleEnableService.insert(articleEnable);
      } else {
        effectRow += articleEnableService.deleteByPrimaryKey(articleEnable);
      }
    }
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("设置话题'%s'可见权限", article.getTitle()));
    return MessageBean.getMessageBean(effectRow, MessageBean.ErrorMessageType.SYSTEM_ERROR);
  }

  @RequestMapping("/article/setGroupId")
  public MessageBean articleSetGroupId(@RequestParam int articleId, @RequestParam int grpId) {
    Article article = new Article();
    article.setId(articleId);
    article.setGroupId(grpId);
    articleService.updateByPrimaryKey(article);
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("设置话题'%d'组id为'%d'", articleId, grpId));
    return MessageBean.SUCCESS;
  }

  /**
   * 删除话题评论
   *
   * @param id 评论id
   * @return
   */
  @RequestMapping(value = "/article/comment/delete")
  public MessageBean articleCommentDelete(@RequestParam int id,@RequestParam(defaultValue = "3")int type) {

//    int effectRow = articleCommentService.deleteByPrimaryKey(id);
    Map map =new HashMap();
    map.put("id",id);
    map.put("type",type);
    articleCommentService.updateComment(map);
    MessageBean messageBean = MessageBean.getMessageBean(1, MessageBean.ErrorMessageType.SYSTEM_ERROR);
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("删除话题评论'%s'", id));
    return messageBean;
  }

  /**
   * 添加或修改商品信息商品
   * 判别标准：是否传入商品id
   *
   * @param good 例如  description=商品1 等
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "/good/add")
  public MessageBean goodAdd(@ModelAttribute Good good) throws IOException {
    int effectRow = goodService.insert(good);
    MessageBean messageBean = MessageBean.getMessageBean(effectRow, MessageBean.ErrorMessageType.ERROR_OPERATION);
    if (messageBean.getCode() == MessageBean.SUCCESS.getCode()) {
      JavaSmsApi.newGoodNotification(goodService.selectByPrimaryKey(good.getId()));
    }
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("添加商品'%s'", good.getName()));
    return messageBean;
  }

  /**
   * 删除商品
   *
   * @param id 商品id
   * @return
   */
  @RequestMapping(value = "/good/delete")
  public MessageBean goodDelete(@RequestParam int id) {
    Good good = goodService.selectByPrimaryKey(id);
    MessageBean messageBean = MessageBean.getMessageBean(goodService.deleteByPrimaryKey(id), MessageBean.ErrorMessageType.ERROR_OPERATION);
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("删除商品'%s'", good.getName()));
    return messageBean;
  }

  /**
   * 切换用户订阅状态
   * 当有新的兑换信息时以短信形式提示，手机号为用户信息中的手机号
   *
   * @param id 需要订阅提醒的用户id
   * @return
   */
  @RequestMapping(value = "/good/notify")
  public MessageBean goodNewNotify(@RequestParam int id) {
    return MessageBean.getMessageBean(userService.toggleNotifyNewGood(id), MessageBean.ErrorMessageType.SYSTEM_ERROR);
  }


  /**
   * TODO Y
   * 后台分配群组
   * 参数未定
   *
   * @return
   */
  @RequestMapping(value = "/group/add")
  public MessageBean groupAdd(String groupname, String desc, String owner, String groupUsers) {
    //groupUsers 为123&1234165&类型的字符串
    ObjectNode dataObjectNode = JsonNodeFactory.instance.objectNode();
    //组名，描述，*，是否公开，最大人数 ，群主，成员
    dataObjectNode.put("groupname", groupname);
    dataObjectNode.put("desc", desc);
    dataObjectNode.put("approval", true);
    dataObjectNode.put("public", true);
    dataObjectNode.put("maxusers", 200);
    dataObjectNode.put("owner", owner);
    ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
    String address = new String(groupUsers);
    String[] str = address.split("\\&");
    for (String s : str) {
      arrayNode.add(s);
    }

    dataObjectNode.put("members", arrayNode);
    ObjectNode creatChatGroupNode = EasemobChatGroups.creatChatGroups(dataObjectNode);
    //返回群id
    JsonNode groupid = creatChatGroupNode.findValue("groupid");
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("后台分配聊天群组'%s'", groupname));
    return MessageBean.SUCCESS;
  }

  /**
   * TODO Y
   * 发送消息
   * 参数未定
   *
   * @return
   */
  @RequestMapping(value = "/group/sendMessage")
  public MessageBean groupSendMessage(String usertel, String text, String groupid) {
    // 给一个群组发文本消息
    String from = "hddl_gax";
    //String targetTypeus = "users";
    ObjectNode ext = factory.objectNode();
    ArrayNode targetusers = factory.arrayNode();
    targetusers.add("1");
    targetusers.add(usertel);
    ObjectNode txtmsg = factory.objectNode();
    txtmsg.put("msg", text);
    txtmsg.put("type", "txt");

    String targetTypegr = "chatgroups";
    ArrayNode chatgroupidsNode = (ArrayNode) EasemobChatGroups.getAllChatgroupids().path("data");
    ArrayNode targetgroup = factory.arrayNode();
    targetgroup.add(chatgroupidsNode.get(0).path(groupid).asText());
    ObjectNode sendTxtMessagegroupnode = EasemobMessages.sendMessages(targetTypegr, targetgroup, txtmsg, from, ext);
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("后台发送群组消息'%s'到'%s'", text, usertel));
    return MessageBean.SUCCESS;
  }


  /**
   * 添加或修改帮助
   * 判别标准：是否传入帮助id
   *
   * @return
   * @throws UnsupportedEncodingException
   */
  @Deprecated
  @RequestMapping(value = "/help/add")
  public MessageBean helpAdd(@ModelAttribute Feedback fb, @RequestParam(defaultValue = " ") String content, @RequestParam(defaultValue = " ") String title, HttpServletRequest res, HttpSession session) throws UnsupportedEncodingException {
//        String t = new String(fb.getContent().getBytes("iso-8859-1"), "utf-8");
    Date time = new Date();
    fb.setTime(time);
    fb.setContent(title);
    fb.setReply(content);
    User ad = (User) session.getAttribute("adminUser");
    fb.setUser(ad.getId());
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("添加帮助信息'%s'", content));
    return MessageBean.getMessageBean(feedbackService.insert(fb), MessageBean.ErrorMessageType.ERROR_OPERATION);
  }

  /**
   * 删除帮助
   *
   * @param id 帮助id
   * @return
   */
  @Deprecated
  @RequestMapping(value = "/help/delete")
  public MessageBean helpDelete(@RequestParam int id) {
    return MessageBean.getMessageBean(helpService.deleteByPrimaryKey(id), MessageBean.ErrorMessageType.ERROR_OPERATION);
  }

  /**
   * toggle帮助的隐藏状态
   *
   * @param id 帮助id
   * @return
   */
  @Deprecated
  @RequestMapping(value = "/help/visible")
  public MessageBean helpVisible(@RequestParam int id) {
    return MessageBean.getMessageBean(helpService.toggleVisible(id), MessageBean.ErrorMessageType.ERROR_OPERATION);
  }

  /**
   * 日记评论删除
   *
   * @param id 评论id
   * @return
   */
  @RequestMapping(value = "/diary/comment/delete")
  public MessageBean diaryCommentDelete(@RequestParam int id) {
    int effectRow = diaryCommentService.deleteByPrimaryKey(id);
    MessageBean messageBean = MessageBean.getMessageBean(effectRow, MessageBean.ErrorMessageType.COMMENT_NOT_EXIST);
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("删除日记的评论'%d'", id));
    return messageBean;
  }

  /**
   * 删除回馈
   *
   * @param id 回馈id
   * @return
   */
  @RequestMapping(value = "/feedback/delete")
  public MessageBean feedbackDelete(@RequestParam int id) {
    MessageBean messageBean = MessageBean.getMessageBean(feedbackService.deleteByPrimaryKey(id), MessageBean.ErrorMessageType.FEEDBACK_NOT_EXIST);
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("删除反馈'%d'", id));
    return messageBean;
  }

  /**
   * toggle回馈的隐藏状态
   *
   * @param id 回馈id
   * @return
   */
  @RequestMapping(value = "/feedback/visible")
  public MessageBean feedbackVisible(@RequestParam int id) {
    MessageBean messageBean = MessageBean.getMessageBean(feedbackService.toggleVisible(id), MessageBean.ErrorMessageType.FEEDBACK_NOT_EXIST);
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("设置反馈'%d'可见", id));
    return messageBean;
  }

  /**
   * 回复回馈
   *
   * @param feedback 需传入参数id和reply
   * @return
   * @throws UnsupportedEncodingException
   */
  @RequestMapping(value = "/feedback/reply")
  public MessageBean feedbackReply(@ModelAttribute Feedback feedback) throws UnsupportedEncodingException {
    if (feedback.getId() == null) {
      return MessageBean.getInvalidField("反馈id");
    }
    if (feedback.getReply() == null || feedback.getReply().isEmpty()) {
      return MessageBean.getInvalidField("反馈回复内容");
    }
//        String c = new String(feedback.getReply().getBytes("iso-8859-1"), "utf-8");
    Feedback updateFeedback = new Feedback();
    updateFeedback.setId(feedback.getId());
    updateFeedback.setReply(feedback.getReply());
    MessageBean messageBean = MessageBean.getMessageBean(feedbackService.updateByPrimaryKey(updateFeedback), MessageBean.ErrorMessageType.FEEDBACK_NOT_EXIST);

    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("回复反馈'%d'，内容'%s','%s'", feedback.getId(), feedback.getReply(), messageBean.getMessage()));
    return messageBean;
  }

  /**
   * 设置用户的积分
   *
   * @param id    用户id
   * @param score 积分数
   * @return
   */
  @RequestMapping("/score/set")
  public MessageBean setScore(@RequestParam int id, @RequestParam int score) {
    MessageBean messageBean = ScoreUtil.addScore(id, score, ScoreUtil.SYSTEM);
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("添加用户'%d'积分'%d'", id, score));
    return messageBean;
  }

  @RequestMapping("/app/version/new")
  public MessageBean addNewAppVersion(@ModelAttribute AppVersion appVersion) {
    appVersionMapper.insert(appVersion);
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("添加app版本'%s','%s','%s'", appVersion.getPlatform(), appVersion.getVersionCode(), appVersion.getUpdateLog()));
    return MessageBean.SUCCESS;
  }

  /**
   * 添加APP版本到本地
   *
   * @param log   日志
   * @param code  版本号
   * @param files apk文件
   * @return ModelAndView 跳转回原页面
   * @throws Exception
   */
  @RequestMapping("/app/new")
  public ModelAndView appNew(
      String log, String code
      , HttpServletResponse res, HttpServletRequest req, MultipartFile files, String type) throws Exception {
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("添加app版本'%s','%s','%s'", 1, code, log));
    String root = req.getSession().getServletContext().getRealPath("/");
    InputStream in = req.getInputStream();
    String dir = "upload/good/";
    String imgPath = root + dir;
    String imgName = "";
    if (type.equals("1")) {
      imgName = getRandomFileName() + ".apk";
    } else if (type.equals("2")) {
      imgName = getRandomFileName() + ".ipa";
    }
    File file = new File(imgPath, imgName);
    files.transferTo(file);


//        File file = new File(appPath, fileName);
//        files.transferTo(file);
    AppVersion app = new AppVersion();
    app.setUrl("/" + dir + imgName);
    app.setPlatform(Byte.valueOf(type));
    app.setVersionCode(code);
    app.setUpdateLog(log);
    appVersionMapper.insert(app);
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/index");
    mav.addObject("type", "2");
    mav.addObject("msg", "success");
    return mav;
  }

  /**
   * 版本显示
   *
   * @return ModelAndView版本信息
   */
  @RequestMapping("/app/Version")
  public ModelAndView app() {
    AppVersion ap = appVersionMapper.selectLastVersion(1);
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/index");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    mav.addObject("type", "1");


    AppVersion ap2 = appVersionMapper.selectLastVersion(2);
    try {
      mav.addObject("code", ap.getVersionCode());
    } catch (NullPointerException e) {
      mav.addObject("code", "");
    }
    try {
      mav.addObject("time", sdf.format(ap.getUpdateTime()));
    } catch (NullPointerException e) {
      mav.addObject("time", "");
    }
    try {
      mav.addObject("log", ap.getUpdateLog());
    } catch (NullPointerException e) {
      mav.addObject("log", "");
    }
    try {
      mav.addObject("url", ap.getUrl());

    } catch (NullPointerException e) {
      mav.addObject("url", "");
    }


    try {
      mav.addObject("code2", ap2.getVersionCode());
    } catch (NullPointerException e) {
      mav.addObject("code2", "");
    }
    try {
      mav.addObject("time2", sdf.format(ap2.getUpdateTime()));
    } catch (NullPointerException e) {
      mav.addObject("time2", "");
    }
    try {
      mav.addObject("log2", ap2.getUpdateLog());
    } catch (NullPointerException e) {
      mav.addObject("log2", "");
    }
    try {
      mav.addObject("url2", ap2.getUrl());

    } catch (NullPointerException e) {
      mav.addObject("url2", "");
    }
    return mav;
  }

  /**
   * @param grp
   * @param sex
   * @param ageS          最低年龄
   * @param ageE          最高年龄
   * @param city
   * @param degree
   * @param marriage
   * @param selectionType 只选左边，只选右边，左右求交，左右求并，0,1,2,3
   * @return
   */

  @RequestMapping("/user/filter")
  public MessageBean filter(@RequestParam(required = false) Integer grp,
                            @RequestParam(required = false) Integer sex,
                            @RequestParam(required = false) String ageS,
                            @RequestParam(required = false) String ageE,
                            @RequestParam(required = false) String city,
                            @RequestParam(required = false) String degree,
                            @RequestParam(required = false) Boolean marriage,
                            @RequestParam(defaultValue = "") String extendsInfo,
                            @RequestParam(defaultValue = "0") int selectionType) {
    MessageBean messageBean = MessageBean.SUCCESS.clone();
    boolean emptyLeft = grp == null;
    boolean emptyRight = (sex == null && ageS == null && ageE == null && city == null && degree == null && (StringUtils.isEmpty(extendsInfo) || extendsInfo.equals("{}")));
    if ((selectionType == 0 && emptyLeft) || (selectionType == 1 && emptyRight) || ((selectionType == 2 || selectionType == 3) && (emptyLeft || emptyRight))) {
      return MessageBean.getInvalidField("参数");
    }
    Map<String, Object> config = new HashMap<>();
    config.put("grp", grp);
    config.put("sex", sex);
    config.put("ageS", ageS);
    config.put("ageE", ageE);
    config.put("city", CommonUtil.processLikeParameter(city));
    config.put("degree", degree);
    config.put("marriage", marriage);
    config.put("selectionType", selectionType);
    if (!StringUtils.isEmpty(extendsInfo)) {
      JSONObject jsonObject = JSONObject.fromObject(extendsInfo);
      StringBuilder sqlSb = new StringBuilder();
      sqlSb.append(" 1 = 1 ");
      ArrayList<String> queriedName = new ArrayList<>();
      for (Map.Entry<String, Object> entry : (Set<Map.Entry<String, Object>>) jsonObject.entrySet()) {
        String key = entry.getKey();
        Object valueObject = entry.getValue();
        try {
          if (!StringUtils.isEmpty(valueObject)) {
            String value = valueObject.toString();
            int keyLen = key.length();
            if (key.endsWith("S")) {
              String columnName = key.substring(0, keyLen - 1);
              String extraComparator = "";
              if (jsonObject.containsKey(columnName + "E")) {
                int endValue = jsonObject.getInt(columnName + "E");
                extraComparator = String.format(" AND column_name = '%s' and cvalue < %d", columnName, endValue);
              }
              int startValue = jsonObject.getInt(key);
              sqlSb.append(String.format(" AND exists (select * from wj_view_column_info where uid = user_id and (column_name = '%s' and cvalue > %d) %s) ", columnName, startValue, extraComparator));
              queriedName.add(columnName);
            } else if (key.endsWith("E")) {
              String columnName = key.substring(0, keyLen - 1);
              if (!queriedName.contains(columnName)) {
                int endValue = jsonObject.getInt(key);
                sqlSb.append(String.format(" AND exists (select * from wj_view_column_info where uid = user_id and column_name = '%s' and cvalue < %d) ", columnName, endValue));
              }
            } else {
              sqlSb.append(String.format(" AND exists (select * from wj_view_column_info where uid = user_id and column_name = '%s' and cvalue = '%s') ", key, value));
            }
          }
        } catch (RuntimeException e) {
          e.printStackTrace();
        }
      }
      config.put("extendsInfo", sqlSb.toString());
    }
    messageBean.setExtraList(userService.filter(config).toArray());
    return messageBean;
  }

  /**
   * 导出用户数据
   *
   * @throws SQLException
   * @throws IOException
   */
  @RequestMapping(value = "/export")
  public void export(HttpServletRequest req, HttpServletResponse res, int page, int type,HttpSession session) throws SQLException, IOException {
    int ogid=organizationGroupService.selectByOAdmin(User.getCurrentUser().getId());
    propertyController pc = new propertyController();
    HSSFWorkbook wb = pc.export(page, type,ogid);
    OutputStream out = null;//创建一个输出流对象
    out = res.getOutputStream();
    String headerStr = "用户列表";
    headerStr = new String(headerStr.getBytes("gb2312"), "ISO8859-1");
    res.setHeader("Content-disposition", "attachment; filename=" + headerStr + ".xls");//filename是下载的xls的名，建议最好用英文
    res.setContentType("application/msexcel;charset=UTF-8");//设置类型
    res.setHeader("Pragma", "No-cache");//设置头
    res.setHeader("Cache-Control", "no-cache");//设置头
    res.setDateHeader("Expires", 0);//设置日期头
    wb.write(out);
    out.flush();
    if (out != null) {
      out.close();
    }

    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, "导出用户数据");
  }

  @RequestMapping("exportlu")
  public ModelAndView exportlu(HttpServletRequest req, HttpServletResponse res, @RequestParam("file") MultipartFile file, @RequestParam(value = "wantpage", defaultValue = "1") String wantpage) throws IOException, SQLException {
   int ogid=organizationGroupService.selectByOAdmin(User.getCurrentUser().getId());
    if (!file.isEmpty()) {
      String filePath = "";
      try {
        // 文件保存路径
        filePath = req.getSession().getServletContext().getRealPath("/") + "upload/"
            + file.getOriginalFilename();
        // 转存文件
        file.transferTo(new File(filePath));
      } catch (Exception e) {
        e.printStackTrace();
      }
      propertyController pc = new propertyController();
      JSONObject json = pc.insert(filePath, res, req,ogid);
      File file2 = new File(filePath);
      if (file2.exists()) {
        file2.delete();
      }
    }
    ModelAndView modelAndView = new ModelAndView();
    propertyController pc = new propertyController();
    int wp = Integer.parseInt(wantpage);
    int begin = (wp - 1) * 10;
    JSONObject js = pc.info(begin,ogid);
    List clist = (List) js.get("cinfo");
    List ilist = (List) js.get("info");
    List cx = (List) js.get("cx");
    int x = 0;
    List allList = new ArrayList();
    int h = 0;
    int l = 0;
    JSONObject jsonObj = new JSONObject();
    for (int i = clist.size() * h; i < ilist.size(); i++) {
      if (l == clist.size()) {
        l = 0;
      }
      String regEx = "date|day|hours|minutes|month|nanos|seconds|time|timezoneOffset|year";
      Pattern p = Pattern.compile(regEx);
      Matcher m = p.matcher(ilist.get(i).toString());
      boolean rs = m.find();
      if (rs == true) {
        JSONObject createDate = (JSONObject) ilist.get(i);
        Date tempDate = (Date) JSONObject.toBean(createDate, Date.class);
        String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tempDate);
        try {
          jsonObj.put(clist.get(l), createTime);
        } catch (Exception e) {
          jsonObj.put(clist.get(l), ilist.get(i));
        }
      } else {
        jsonObj.put(clist.get(l), ilist.get(i));
      }
      if ((i + 1) % clist.size() == 0) {

        allList.add(x, jsonObj);
        x++;
        jsonObj = new JSONObject();
      }
      h++;
      l++;
    }
    JSONArray ja = new JSONArray();
    ja.add(allList);
    modelAndView.addObject("all", allList);
    modelAndView.addObject("clist", cx);
    //用户总数
    JSONObject result = new JSONObject();
    int pagesize = 10;

    User userAdmin=User.getCurrentUser();
    int UserCount = userMapper.UserCount(userAdmin.getId());

    int pageCount = 0;
    if (UserCount % 10 != 0) {
      pageCount = (UserCount - (UserCount % pagesize)) / pagesize + 1;
    } else if (UserCount % 10 == 0) {
      pageCount = UserCount / 10;
    } else if (UserCount < 10 && UserCount > 0) {
      pageCount = 1;
    } else {
      pageCount = 0;
    }
    List li = this.quanxian();
    modelAndView.addObject("group", li);
    List idList = new ArrayList();
    for (int i = 1; i <= 10; i++) {
      idList.add((wp - 1) * 10 + i);
    }
    modelAndView.addObject("idlist", idList);

    modelAndView.addObject("nowpage", wantpage);
    modelAndView.addObject("pagecount", pageCount);
    modelAndView.setViewName("/UserList");
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, "导出用户数据");
    return modelAndView;
  }

  /**
   * 所有分组的显示
   */

  public List quanxian() {
    //获取所有群组
    HxUtil hu = new HxUtil();
    List li = hu.getGroupInfo();
    return li;
  }

  /**
   * 删除群组，多个用逗号隔开
   * 返回success成功
   *
   * @param grps
   */
  @RequestMapping("/group/delete")
  public MessageBean deleteGroup(@RequestParam String grps) {
    String[] grpArray = grps.split(",");
    for (String grp : grpArray) {
      groupService.deleteGroup(grp);
    }
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("删除群组'%s'", grps));
    return MessageBean.SUCCESS;
  }

  /**
   * 删除指定用户的所属群组，多个用逗号隔开
   *
   * @param ids
   * @return success成功
   */
  @RequestMapping("/user/group/delete")
  public MessageBean deleteUserGroup(@RequestParam String ids) {
    String[] idStrs = ids.split(",");
    for (String idStr : idStrs) {
      try {
        userGroupService.deleteUserGroup(Integer.parseInt(idStr));
      } catch (RuntimeException e) {
        //NOTHING
      }
    }
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("删除用户群组'%s'", ids));
    return MessageBean.SUCCESS;
  }

  /**
   * 将指定id的用户插入指定的讨论组中
   *
   * @param gid  组id
   * @param usrs 多个用户id用逗号隔开
   * @return
   * @throws JsonMappingException
   * @throws JsonParseException
   * @throws IOException
   * @throws IllegalStateException
   */
  @RequestMapping("/user/group/chang")
  public ModelAndView UserGroupChang(int gid, String usrs) throws Exception {
    AvailBean availBean = new ObjectMapper().readValue(usrs, AvailBean.class);
    List<User> lu = userService.selectUserByGroups(availBean.getGroup());
    Set<User> userSet = new HashSet<>(lu);
    userSet.addAll(Arrays.asList(userService.selectByNames(availBean.getUser().toArray(new String[0]))));
    List<Integer> luser = new ArrayList<>();
    Iterator<User> iterator = userSet.iterator();
    while (iterator.hasNext()) {
      User us = iterator.next();
      luser.add(us.getId());
    }
    userGroupService.del(gid);
    userGroupService.insertUsersIntoGroup(gid, luser);
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("添加用户'%s'到组中", usrs, gid));
    return new ModelAndView("redirect:/admin/userGroup.do?msg=1");
  }

  /**
   * 添加讨论区
   *
   * @param req
   * @param type
   * @param img  log图片
   * @param usrs 用户
   * @param grp  讨论区名
   * @return
   * @throws IllegalStateException
   * @throws IOException
   */
  @RequestMapping("/user/group/add")
  public ModelAndView addUserToGroup(HttpServletRequest req, int type, @RequestParam(value = "img", required = false) MultipartFile img, String usrs, @RequestParam String grp) throws IllegalStateException, IOException {
    Group group = groupService.selectByName(grp);
    if (group != null) {
      return new ModelAndView("redirect:/admin/userGroupNew.do?msg=2");

    }
    String imgs = "";


    AvailBean availBean = new ObjectMapper().readValue(usrs, AvailBean.class);
    List<User> lu = userService.selectUserByGroups(availBean.getGroup());
    Set<User> userSet = new HashSet<>(lu);
    userSet.addAll(Arrays.asList(userService.selectByNames(availBean.getUser().toArray(new String[0]))));
    List<Integer> luser = new ArrayList<>();
    Iterator<User> iterator = userSet.iterator();
    while (iterator.hasNext()) {
      User us = iterator.next();
      luser.add(us.getId());
    }
    for (int i = 0; i < lu.size(); i++) {
      luser.add(lu.get(i).getId());
    }


    if (img.getSize() != 0) {
      String root = req.getSession().getServletContext().getRealPath("/");
//            InputStream in = req.getInputStream();
      String dir = "/upload/good/";
      String imgPath = root + dir;
      String imgName = getRandomFileName() + ".jpg";
      File directory = new File(dir);
      if (!directory.exists()) {
        directory.mkdirs();
      }
      File file = new File(imgPath, imgName);
      img.transferTo(file);
      imgs = dir + imgName;
    }

    group = new Group();
    group.setName(grp);
    group.setLogo(imgs);
    if (type == 1) {
      group.setAddBackground(false);

    } else {
      group.setAddBackground(true);

    }
    User user=User.getCurrentUser();

    group.setOgid(organizationGroupService.selectByOAdmin(user.getId()));
    groupService.insert(group);

    int success = userGroupService.insertUsersIntoGroup(group.getId(), luser);
    session.setAttribute(SESSION_KEY_OPERATOR_RECORD, String.format("新建讨论组'%s'", group.getName()));
//        int totalSize = idStrs.length;
//        MessageBean messageBean = MessageBean.SUCCESS.clone();
//        messageBean.setExtra(String.format("共操作%d个，成功插入%d个，失败%d个", totalSize, success, totalSize - success));
    //return messageBean;
    return new ModelAndView("redirect:/admin/userGroupNew.do?msg=1");
  }


  @RequestMapping("/user/group/change")
  public ModelAndView changeUserInfo(HttpServletRequest req, @RequestParam(value = "img", required = false) MultipartFile img, @RequestParam Integer grpId,  @RequestParam String grpName) throws IllegalStateException, IOException {

    Group group=groupService.selectByPrimaryKey(grpId);
    String imgs = "";
    if (img != null & img.getSize()!=0) {
      String root = req.getSession().getServletContext().getRealPath("/");
//            InputStream in = req.getInputStream();
      String dir = "/upload/good/";
      String imgPath = root + dir;
      String imgName = getRandomFileName() + ".jpg";
      File directory = new File(dir);
      if (!directory.exists()) {
        directory.mkdirs();
      }
      try {
        File file = new File(imgPath, imgName);
        img.transferTo(file);
        imgs = dir + imgName;
        group.setLogo(imgs);
      }catch (Exception e){
        e.printStackTrace();
          return new ModelAndView("redirect:/admin/userGroup.do?changeInfoMsg=1");
      }
    }
    group.setName(grpName);
    int count=groupService.updateByPrimaryKey(group);
    MessageBean messageBean = MessageBean.SUCCESS.clone();
//    messageBean.setExtra(String.format("共操作%d个，成功插入%d个，失败%d个", totalSize, success, totalSize - success));

    if(count>0){
        return new ModelAndView("redirect:/admin/userGroup.do?changeInfoMsg=1");
    }else{
        return new ModelAndView("redirect:/admin/userGroup.do?changeInfoMsg=0");
    }
  }

  //生成不重复随机数
  public static String getRandomFileName() {
    SimpleDateFormat simpleDateFormat;
    simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    Date date = new Date();
    String str = simpleDateFormat.format(date);
    Random random = new Random();
    int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
    return rannum + str;// 当前时间
  }


  /**
   * 积分修改权限操作
   *
   * @param id   管理员id
   * @param res
   * @param type 操作类型 0不可修改，1可修改，2请求修改
   *             返回success成功
   * @throws IOException
   */
  @RequestMapping("/ScoreRole")
  public void scoreRole(int id, HttpServletResponse res, @RequestParam(required = false, defaultValue = "0") int type) throws IOException {
    User user = userService.selectByPrimaryKey(id);

    if (type == 1 && user.getExtra().equals("2")) {

    } else {

      if (user.getExtra().equals("0")) {
        user.setExtra("1");
      } else if (user.getExtra().equals("2")) {
        user.setExtra("0");
      } else if (user.getExtra().equals("1")) {
        user.setExtra("2");
      }
      userService.updateByPrimaryKey(user);
    }

    res.getWriter().write("success");
  }

  /**
   * 日记开启隐藏
   * @param setShown
   * @param isShow
     * @return
     */
  @RequestMapping("/updateDiaryShow")
  public MessageBean updateDiaryShow(@RequestParam String setShown, @RequestParam boolean isShow) {
    //ConfigUtil.put(setShown, isShow);
    Map map=new HashMap();
    if(isShow)    map.put("diaryVisible",1);
    else map.put("diaryVisible",0);

    map.put("ogid",organizationGroupService.selectByOAdmin(User.getCurrentUser().getId()));
    systemInfoService.UpdateDiaryVisible(map);
    return MessageBean.SUCCESS;
  }

  /**
   * 设置用户组显示在前台
   *
   * @param id
   * @return
   */
  @RequestMapping("/group/setForeground")
  public MessageBean setForeground(@RequestParam int id) {
    //不做限制
//    if (groupService.selectForegroundGroupCount() >= 3) {
//      MessageBean messageBean = new MessageBean();
//      messageBean.setCode(100);
//      messageBean.setExtra("前台组数已有三个");
//      return messageBean;
//    }
    Group group = groupService.selectByPrimaryKey(id);
    if (group == null) {
      return MessageBean.GROUP_NOT_EXISTS;
    }
    if(group.getShowForeground()){

      group.setShowForeground(false);
    }else{

      group.setShowForeground(true);
    }

    groupService.updateByPrimaryKey(group);
    return MessageBean.SUCCESS;
  }

  /**
   * 查看修改权限
   *
   * @param id  管理员
   * @param res
   * @throws Exception
   */
  @RequestMapping("/ScoreRoleInfo")
  public void ScoreRoleInfo(int id, HttpServletResponse res) throws Exception {
    User user = userService.selectByPrimaryKey(id);
    if (user.getExtra().equals("0")) {
      res.getWriter().write("0");
    } else {
      res.getWriter().write("1");

    }
  }

  //导出管理员操作记录
  @RequestMapping("/exportAdmin")
  public void exportAdmin(String id, HttpServletResponse res) throws Exception {
    int count = adminRecordMapper.count(id);
    List<AdminRecord> ar = new ArrayList<AdminRecord>();
    if (count == 0) {
    } else {
      ar = adminRecordMapper.selectAll(id);
    }

    HSSFWorkbook wb = new HSSFWorkbook();
    HSSFSheet sheet = wb.createSheet("用户信息");
    HSSFRow row = sheet.createRow((int) 0);
    HSSFCellStyle style = wb.createCellStyle();
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
    HSSFCell cell = row.createCell((short) 0);
    cell.setCellValue("管理员");
    cell.setCellStyle(style);
    cell = row.createCell((short) 1);
    cell.setCellValue("操作记录");
    cell.setCellStyle(style);
    cell = row.createCell((short) 2);
    cell.setCellValue("操作时间");
    cell.setCellStyle(style);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

    User u = userMapper.selectByPrimaryKey(Integer.parseInt(id));
    for (int i = 0; i < ar.size(); i++) {
      row = sheet.createRow((int) i + 1);
      row.createCell((short) 0).setCellValue(u.getName());
      row.createCell((short) 1).setCellValue(ar.get(i).getDescription().replace("'", ""));
      row.createCell((short) 2).setCellValue(sdf.format(ar.get(i).getTime()));

    }
    OutputStream out = null;//创建一个输出流对象
    out = res.getOutputStream();
    String headerStr = u.getName() + "操作记录";
    headerStr = new String(headerStr.getBytes("gb2312"), "ISO8859-1");
    res.setHeader("Content-disposition", "attachment; filename=" + headerStr + ".xls");//filename是下载的xls的名，建议最好用英文
    res.setContentType("application/msexcel;charset=UTF-8");//设置类型
    res.setHeader("Pragma", "No-cache");//设置头
    res.setHeader("Cache-Control", "no-cache");//设置头
    res.setDateHeader("Expires", 0);//设置日期头
    wb.write(out);
    out.flush();
    if (out != null) out.close();

  }
}
