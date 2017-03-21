package com.wenjuan.controller;

import com.wenjuan.bean.AvailBean;
import com.wenjuan.bean.MessageBean;
import com.wenjuan.dao.ArticleCommentMapper;
import com.wenjuan.dao.SensitiveMapper;
import com.wenjuan.model.*;
import com.wenjuan.service.*;
import com.wenjuan.utils.*;
import com.wenjuan.vo.ArticleCommentView;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "/article")
public class ArticleController {
  @Autowired
  ArticleCommentMapper articleCommentMapper;
  @Resource
  HttpServletRequest request;
  @Autowired
  @Qualifier("articleService")
  private ArticleService articleService;
  @Autowired
  @Qualifier("articleEnableService")
  private ArticleEnableService articleEnableService;
  @Autowired
  @Qualifier("articleCommentService")
  private ArticleCommentService articleCommentService;
  @Autowired
  @Qualifier("userFollowArticleService")
  private UserFollowArticleService userFollowArticleService;
  @Autowired
  @Qualifier("userService")
  private UserService userService;
  @Autowired
  @Qualifier("userViewArticleLogService")
  private UserViewArticleLogService userViewArticleLogService;
  @Autowired
  @Qualifier("userGroupService")
  private UserGroupService userGroupService;
  @Autowired
  private UserLikeArticleService userLikeArticleService;
  @Autowired
  @Qualifier("groupService")
  private GroupService groupService;
  @Resource
  private SensitiveMapper sensitiveMapper;

  @Autowired
  @Qualifier("SystemInfoService")
  private SystemInfoService systemInfoService;
  @Autowired
  @Qualifier("organizationUserService")
  private OrganizationUserService organizationUserService;
  @Autowired
  @Qualifier("organizationGroupService")
  private OrganizationGroupService organizationGroupService;
  Logger logger = Logger.getLogger(ArticleController.class);

  @RequestMapping(value = "/add")
  public MessageBean add(@RequestParam String content,
                         @RequestParam String title,
                         @RequestParam int grpId,
                         @RequestParam(defaultValue = "") String pics,
                         @RequestParam(defaultValue = "") String video,
                         @RequestParam(defaultValue = "true") boolean avai,
                         @RequestParam(defaultValue = "") String availUserOrGrp,
                         HttpSession session) throws UnsupportedEncodingException {







                           //替换敏感字
    int sensitive=0;
//ogid 当前所在组织的编号
    logger.info("articleGroup");
logger.info("-----------------------------"+session.getAttribute("ogid"));
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
      while (content.indexOf(rep) != -1) {
        content = content.replace(rep, replen);
        sensitive=1;
      }
      while (title.indexOf(rep) != -1) {
        title = title.replace(rep, replen);
        sensitive=1;
      }
    }


    Article article = new Article();
  if(sensitive==1){
    article.setSensitive(1);
  }

    //是否满足人工审核条件

    Map map=new HashMap();
    map.put("id",3);
    map.put("ogid",session.getAttribute("ogid"));
    SystemInfo s1=systemInfoService.getSystemInfoByid(map);
    //条件2包含敏感词
    map.put("id",2);
    SystemInfo s2=systemInfoService.getSystemInfoByid(map);
    map.put("id",1);
    //状态
    SystemInfo s3=systemInfoService.getSystemInfoByid(map);

//审核条件
    int type=0;
    String[] str2 = s2.getName().split("\\,");
    for (int i = 0; i < str2.length; i++) {
      String rep2 = str2[i];
      if (rep2.trim().length() == 0) {
        continue;
      }
     if(content.indexOf(rep2) != -1) {
     type=1;
      }
      if(title.indexOf(rep2) != -1) {
        type=1;
      }
    }
if(s3.getValue().equals("0")){
  //满足其一
  //条件一开启并满足
if(s1.getValue().equals("0")){
  if(content.length()>=Integer.parseInt(s1.getName())){
    article.setType(1);
  }
}
//条件二开启并满足
  if(s2.getValue().equals("0")){
    if(type==1){
      article.setType(1);
    }
  }

}else{
  //同时满足
  if(s1.getValue().equals("0")) {
    if(s2.getValue().equals("0")) {
      //条件12开启
      if(content.length()>=Integer.parseInt(s1.getName())) {
      if(type==1){
        article.setType(1);
      }
      }
      }
  }


}








    if (availUserOrGrp.isEmpty()) {
      Object availUserOrGrpObj = request.getSession().getAttribute("availUserOrGrp");
      if (availUserOrGrpObj != null) {
        availUserOrGrp = availUserOrGrpObj.toString();
        title = request.getSession().getAttribute("article").toString();
        request.getSession().removeAttribute("article");
        article.setPublishBackground(true);
      }
    }
    User user = User.getCurrentUser();
    MessageBean messageBean;
    if (user == null) {
      messageBean = MessageBean.UNLOGIN;
    }  else if (StringUtils.isEmpty(content)) {
      messageBean = MessageBean.getInvalidField("内容");
    } else if (StringUtils.isEmpty(title)) {
      messageBean = MessageBean.getInvalidField("标题");
    } else if (userGroupService.isUserInGroup(user.getId(), grpId)) {
      messageBean = MessageBean.USER_NOT_IN_TARGET_GROUP;
    } else {
      if (StringUtils.isEmpty(video)) {
        video = "";
      }
      article.setContent(content);
      article.setTitle(title);
      article.setPics(String.format("v:%s;i:%s", video, pics));
      article.setAvai(avai);
      article.setAuthor(user.getId());
        if(!user.getAllowArticle()) {
            article.setType(1);
        }

      article.setGroupId(grpId);
      articleService.insert(article);
      messageBean = MessageBean.SUCCESS.clone();
      messageBean.setExtra(articleService.selectByPrimaryKey(article.getId(), null));
      ArticleUtil.setAvail(articleEnableService, article.getId(), availUserOrGrp);
    }
    return messageBean;
  }

  /**
   * 发表话题的同时上传图片
   * 限制：POST body里面是多张图片数据，编码为base64字符串，每张图片以英文封号分割,不要传其他参数,例如pics=base64字符串。
   * 注意：注意上传图片大小限制：4MB
   *
   * @param content        内容，GET传递
   * @param title          标题,GET
   * @param pics           图片。base64字符串，多个以封号连接
   * @param video          视频URI,GET
   * @param avai           是否默认全部可见，默认true,GET
   * @param availUserOrGrp 可以浏览帖子的用户或群组（avai为true），反之亦然，GET
   *                       Json字符串，格式为：{"group" : ["group1","group2"],"user" : ["user1", "user2" ] }，由AvailBean序列化而来
   * @return extra中为map对象，key为"value"的为插入后的Diary详情
   * @throws UnsupportedEncodingException
   */
  @RequestMapping(value="/addByBase64")
  public MessageBean addByBase64(@RequestParam String content,
                                 @RequestParam String title,
                                 @RequestParam int grpId,
                                 @RequestParam(required = false)  @RequestBody  String pics,
                                 @RequestParam(required = false) String video,
                                 @RequestParam(defaultValue = "true") boolean avai,
                                 @RequestParam(defaultValue = "") String availUserOrGrp,
                                 HttpSession session) throws UnsupportedEncodingException {



/*

                                   //替换敏感字
    String con = sensitiveMapper.selectSensitive();
    String[] str = con.split("\\,");
    for (int i = 0; i < str.length; i++) {
      String rep = str[i];
      String replen = "";

      if (rep.trim().length() == 0) {
        continue;
      }
      for (int f = 0; f < rep.length(); f++) {
        replen += "*";
      }
      while (content.indexOf(rep) != -1) {
        content = content.replace(rep, replen);
      }
      while (title.indexOf(rep) != -1) {
        title = title.replace(rep, replen);
      }
    }
*/


    User user = User.getCurrentUser();
    if (user == null) {
      return MessageBean.UNLOGIN;
    }
    List<String> picList = CommonUtil.SplitBase64ArrayToPathArray(pics, request, userService);
    return add(content, title, grpId, StringUtils.arrayToDelimitedString(picList.toArray(), ","), video, avai, availUserOrGrp,session);
  }

  /**
   * 删除话题，只能删除自己的
   *
   * @param id 话题id
   * @return
   */
  @RequestMapping(value = "/delete")
  public MessageBean delete(@RequestParam int id) {
    User user = User.getCurrentUser();
    MessageBean messageBean;
    if (user == null) {
      messageBean = MessageBean.UNLOGIN;
    } else {
      Article article = articleService.selectByPrimaryKey(id, null);
      if (article == null) {
        messageBean = MessageBean.ARTICLE_NOT_EXIST;
      } else if (user.getId().equals(article.getAuthor())) {
        int effectRow = articleService.deleteByPrimaryKey(id);
        messageBean = MessageBean.getMessageBean(effectRow, MessageBean.ErrorMessageType.INVALID_AUTHOR);
      } else {
        messageBean = MessageBean.INVALID_AUTHOR;
      }
    }
    return messageBean;
  }

  /**
   * 切换帖子的可见的访问权限
   *
   * @param id 话题id
   * @return 其中extra为1表示可见，2表示不可见
   */
  @RequestMapping(value = "/visible")
  public MessageBean setVisible(@RequestParam int id) {
    User user = User.getCurrentUser();
    if (user == null) {
      return MessageBean.UNLOGIN;
    }
    Article article = articleService.selectByPrimaryKey(id, null);
    if (article == null) {
      return MessageBean.ARTICLE_NOT_EXIST;
    }
    if (article.getAuthor().equals(user.getId())) {
      int effectRow = articleService.toggleVisible(id);
      MessageBean messageBean = MessageBean.getMessageBean(effectRow, MessageBean.ErrorMessageType.SYSTEM_ERROR);
      if (effectRow == 1) {
        messageBean.setExtra(article.getStatus() == 1 ? 1 : 2);
      }
      return messageBean;
    } else {
      return MessageBean.INVALID_AUTHOR;
    }
  }

  /**
   * 评论
   *
   * @param lastCommentId 最后一个评论的id，为0表示不获取，默认为0
   * @return 其中extraList包含最新的评论信息，类型为ArticleComment
   * @throws UnsupportedEncodingException
   */
  @RequestMapping(value = "/comment")
  public MessageBean comment(@RequestParam String content,
                             @RequestParam int article,
                             @RequestParam(required = false) @RequestBody String pics,
                             @RequestParam(required = false) Integer commentTo,
                             @RequestParam(defaultValue = "0") int lastCommentId,
                             @RequestParam(defaultValue = "true") boolean byBase64,
                             HttpSession session) throws UnsupportedEncodingException {

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
      while (content.indexOf(rep) != -1) {
        content = content.replace(rep, replen);
      }
    }

    User user = User.getCurrentUser();
    if (user == null) {
      return MessageBean.UNLOGIN;
    }
    if (StringUtils.isEmpty(content)) {
      return MessageBean.getInvalidField("内容");
    }
//        String t = new String(content.getBytes("iso-8859-1"), "utf8");
//        content = t;
    Article articleObj = articleService.selectByPrimaryKey(article, null);
    if (articleObj == null) {
      return MessageBean.ARTICLE_NOT_EXIST;
    }
    ArticleComment articleComment = new ArticleComment();

    //是否包含关键词----------------------------------todoYG
//是否满足人工审核条件
    //条件1字数
    Map map=new HashMap();
    map.put("id",3);
    map.put("ogid",session.getAttribute("ogid"));
    SystemInfo s1=systemInfoService.getSystemInfoByid(map);
    //条件2包含敏感词
    map.put("id",2);
    SystemInfo s2=systemInfoService.getSystemInfoByid(map);
    map.put("id",1);
    //状态
    SystemInfo s3=systemInfoService.getSystemInfoByid(map);

//审核条件
    int type=0;
    String[] str2 = s2.getName().split("\\,");
    for (int i = 0; i < str2.length; i++) {
      String rep2 = str2[i];
      if (rep2.trim().length() == 0) {
        continue;
      }
      if(content.indexOf(rep2) != -1) {
        type = 1;
      }
    }
    if(s3.getValue().equals("0")){
      //满足其一
      //条件一开启并满足
      if(s1.getValue().equals("0")){
        if(content.length()>=Integer.parseInt(s1.getName())){
          articleComment.setType(2);
        }
      }
//条件二开启并满足
      if(s2.getValue().equals("0")){
        if(type==1){
          articleComment.setType(2);
        }
      }

    }else{
      //同时满足
      if(s1.getValue().equals("0")) {
        if (s2.getValue().equals("0")) {
          //条件12开启
          if (content.length() >= Integer.parseInt(s1.getName())) {
            if (type == 1) {
              articleComment.setType(2);
            }
          }
        }
      }
    }


    articleComment.setStatus(new Byte(0 + ""));
    articleComment.setCommenter(user.getId());
    articleComment.setCommentTo(commentTo);
    articleComment.setContent(content);
    articleComment.setArticle(article);
    if (!StringUtils.isEmpty(pics)) {
      if (byBase64) {
        articleComment.setPics(CommonUtil.formatPics("", CommonUtil.SplitBase64ArrayToPathArray(pics, request, userService).toArray()));
      } else {
        articleComment.setPics(CommonUtil.formatPics("", pics));
      }
    }
    int effectRow = articleCommentService.insert(articleComment);
    MessageBean messageBean = MessageBean.getMessageBean(effectRow, MessageBean.ErrorMessageType.SYSTEM_ERROR);
    messageBean.setExtra(articleCommentService.selectByPrimaryKey(articleComment.getId()));
    if (messageBean.getCode() == MessageBean.SUCCESS.getCode()) {
      if (lastCommentId != 0) {
        List<ArticleCommentView> articleCommentViews = articleCommentService.selectCommentAfter(articleComment.getArticle(), lastCommentId);
        messageBean.setExtraList(articleCommentViews.toArray());
      }
      /*//添加积分
      long hourDiff = (new Date().getTime() - articleObj.getTime().getTime()) / (1000 * 60 * 60);//单位：时
      int comment_time_level = 0;
      if (hourDiff < 6) {
        comment_time_level = ScoreUtil.COMMENT_BELOW_6;
      } else if (hourDiff < 24) {
        comment_time_level = ScoreUtil.COMMENT_BELOW_24;
      } else if (hourDiff < 48) {
        comment_time_level = ScoreUtil.COMMENT_BELOW_48;
      } else {
        comment_time_level = ScoreUtil.COMMENT_UPON_48;
      }
      //End 添加积分
      //推送消息
      if (commentTo != null) {
        //TODO
      }
      //End 推送消息
      ScoreUtil.addScore(user.getId(), comment_time_level);*/
    }
    return messageBean;
  }

  /***
   * 切换收藏状态
   *
   * @param id 话题id
   * @return 其中extra为1表示已收藏，0表示取消收藏
   */
  @RequestMapping(value = "/favor")
  public MessageBean favor(@RequestParam int id) {
    User user = User.getCurrentUser();
    if (user == null) {
      return MessageBean.UNLOGIN;
    }
    Article article = articleService.selectByPrimaryKey(id, user);
    if (article == null) {
      return MessageBean.ARTICLE_NOT_EXIST;
    }
    MessageBean messageBean = MessageBean.SUCCESS.clone();
    if (!article.isFavor()) {
      UserFollowArticle userFollowArticle = new UserFollowArticle();
      userFollowArticle.setArticleId(id);
      userFollowArticle.setUserid(user.getId());
      userFollowArticleService.insert(userFollowArticle);
      messageBean.setExtra(1);
    } else {
      userFollowArticleService.deleteByPrimaryKey(user.getId(), id);
      messageBean.setExtra(0);
    }
    return messageBean;
  }

  /**
   * 获取收藏的话题列表
   *
   * @param order time,follow_count,reply_count，author等与desc,asc的组合
   * @return 其中extraList为Article类型的数组
   */
  @RequestMapping(value = "/getFavorites")
  public MessageBean getFavorites(@RequestParam(defaultValue = "time asc") String order,
                                  @RequestParam(defaultValue = "A0") String loadId,HttpSession session) {
    User user = User.getCurrentUser();
    if (user == null) {
      return MessageBean.UNLOGIN;
    }
    if (!CheckUtil.checkOrderField(order)) {
      return MessageBean.getInvalidField("order");
    }
    MessageBean messageBean = MessageBean.SUCCESS.clone();
    messageBean.setExtraList(articleService.getFavorites(user.getId(), order, loadId,Integer.parseInt(session.getAttribute("ogid").toString())));
    return messageBean;
  }

  /**
   * 可以浏览该帖子的用户或用户组
   *
   * @param id 话题id
   * @return extra为AvailBean对象
   */
  @RequestMapping(value = "/avail")
  public MessageBean getAvail(@RequestParam int id) {
    AvailBean availBean = articleEnableService.getAvail(id);
    MessageBean messageBean = MessageBean.SUCCESS.clone();
    messageBean.setExtra(availBean);
    return messageBean;
  }

  /**
   * 设置文章访问权限
   *
   * @param id             文章id
   * @param availUserOrGrp 可以浏览帖子的用户或群组（avai为true），反之亦然，
   *                       Json字符串，格式为：{"group" : ["group1","group2"],"user" : ["user1", "user2" ] }，由AvailBean序列化而来
   * @return
   */
  @RequestMapping("/avail/set")
  public MessageBean setAvail(@RequestParam int id, @RequestParam(defaultValue = "") String availUserOrGrp) {
    User user = User.getCurrentUser();
    if (user == null) {
      return MessageBean.UNLOGIN;
    }
    Article article = articleService.selectByPrimaryKey(id, null);
    if (article == null) {
      return MessageBean.ARTICLE_NOT_EXIST;
    }
    if (article.getAuthor().equals(user.getId()) || user.isAdmin()) {
      return ArticleUtil.setAvail(articleEnableService, id, availUserOrGrp);
    } else {
      return MessageBean.MISMATCH;
    }
  }

  /**
   * 获取文章列表
   *
   * @param type     0：自己发布的，1：全部可见的，2:登陆用户可见的，3:未登录用户可见的，默认1
   * @param order    time,follow_count,reply_count，author等与desc,asc的组合
   * @param loadId   字符A或B开头，后面跟上id，分别表示After和Before,例如A0表示id为0之后的文章
   * @param page     分页页数,默认1, 0为不分页
   * @param getCount 是否只获取数量
   * @return 在extraList中，Article实例数组
   */
  @RequestMapping(value = "/getList")
  public MessageBean getArticleList(
      @RequestParam(required = false) Integer grpId,
      @RequestParam(defaultValue = "1") int type,
      @RequestParam(defaultValue = "time desc") String order,
      @RequestParam(defaultValue = "A0") String loadId,
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "false") boolean getCount,
      HttpSession session) {
    if (!CheckUtil.checkOrderField(order)) {
      return MessageBean.getInvalidField("order");
    }
    User user = User.getCurrentUser();
    if (user == null && (type == 0 || type == 2 || grpId != null)) {
      return MessageBean.UNLOGIN;
    }
    MessageBean messageBean = MessageBean.SUCCESS.clone();
    int ogid=Integer.parseInt(session.getAttribute("ogid").toString());
    if (getCount) {
      messageBean.setExtra(articleService.selectArticlesCount(grpId, type, user, loadId, false,ogid));
    } else {
      messageBean.setExtraList(articleService.selectArticles(grpId, type, user, order, page, null, loadId,ogid).toArray());
    }
      JSONArray ja=JSONArray.fromObject(messageBean.getExtraList());
      JSONArray jat=new JSONArray();
      for(int i=0;i<ja.size();i++){
        JSONObject jo=JSONObject.fromObject(ja.get(i));
        if(jo.get("top").equals(null)){
          jo.put("top"," ");
        }
        try {

          String a[] = jo.get("pics").toString().split("i:");
          List li =new ArrayList();
          String b[]=a[1].toString().split(",");
          for(int f=0;f<b.length;f++){
            li.add(b[f]);
          }
          jo.put("pics",li);
        }catch (Exception e){
          jo.put("pics","");

        }




        Map ma=new HashMap();
        ma.put("aid",jo.get("id"));
        ma.put("uid",user.getId());
      int num=articleCommentService.getCountNum(ma);
        jo.put("replyCount",num);
        jat.add(jo);
      }
      messageBean.setExtraList(jat.toArray());

    return messageBean;
  }

  /**
   * 获取推荐列表
   *
   * @return
   */
  @RequestMapping("/getPushList")
  public MessageBean getPushList(HttpSession session) {
    MessageBean messageBean = MessageBean.SUCCESS.clone();
    //tod
    Map map=new HashMap();
    map.put("uid",User.getCurrentUser().getId());
    map.put("ogid",session.getAttribute("ogid"));
    messageBean.setExtraList(articleService.getPushList(map).toArray());
    return messageBean;
  }

  @RequestMapping("/like")
  public MessageBean like(@RequestParam int id) {
    MessageBean messageBean = MessageBean.SUCCESS.clone();
    User user = User.getCurrentUser();
    if (user == null) {
      return MessageBean.UNLOGIN;
    }
    UserLikeArticle userLikeArticle = new UserLikeArticle(id, user.getId());
    UserLikeArticle isExist = userLikeArticleService.selectByPrimaryKey(userLikeArticle);
    boolean isDelete = true;
    if (isExist == null) {
      userLikeArticleService.insert(userLikeArticle);
      isDelete = false;
    } else {
      userLikeArticleService.deleteByPrimaryKey(userLikeArticle);
    }
    messageBean.setExtra(isDelete);
    return messageBean;
  }

  /**
   * 显示回复消息
   *
   * @param isReceive true为收到的评论，false为发出的评论
   * @param loadTime  A或B后接评论时间戳
   * @param page      0为不分页
   * @param order     默认时间降序
   * @return---to
   */
  @SuppressWarnings("Duplicates")
  @RequestMapping(value = "/commentList")
  public MessageBean commentList(@RequestParam boolean isReceive,
                                 @RequestParam String loadTime,
                                 @RequestParam(required = false) Integer grpId,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "time desc") String order,
                                 HttpSession session) {
    User user = User.getCurrentUser();
    if (user == null) {
      return MessageBean.UNLOGIN;
    }
    MessageBean messageBean = MessageBean.SUCCESS.clone();
    messageBean.setExtraList(articleCommentService.selectCommentMsg(isReceive, grpId, loadTime, page, user, order,Integer.parseInt(session.getAttribute("ogid").toString())).toArray());
    //剔除他人type为1的回复（不剔除自己的）
    List<Article> la=new ArrayList<>();
for(int i=0;i<messageBean.getExtraList().length;i++){
  Article a=(Article) messageBean.getExtraList()[i];
  JSONObject js=JSONObject.fromObject(a);
  List<ArticleCommentView> acv=(List)js.get("comments");
  List<Object> acv2=new ArrayList();
  for(int j=0;j<acv.size();j++){
    JSONObject jo=JSONObject.fromObject(acv.get(j));
//    //判断是否type为1
    if(jo.get("type")==1){
      if(jo.get("commenterId").equals(user.getId())){
        //被删除但是是自己发的依旧可见
        if(jo.get("commentUnderThis").equals(null)){
          jo.put("commentUnderThis"," ");
        }else{
          try {
            JSONArray ja=JSONArray.fromObject(jo.get("commentUnderThis"));
            JSONArray janew=new JSONArray();
            for (int k=0;k<ja.size();k++){
              JSONObject joj=JSONObject.fromObject(ja.get(k));
              if(joj.get("commentUnderThis").equals(null)){
                joj.put("commentUnderThis"," ");
              }
              janew.add(joj);
            }
            jo.put("commentUnderThis",janew);
          }catch (Exception e){

          }
        }
        acv2.add(jo);




      }
    }else{
//      //未被删除的
      if(jo.get("commentUnderThis").equals(null)){
      jo.put("commentUnderThis"," ");
      }else{
        try {
          JSONArray ja=JSONArray.fromObject(jo.get("commentUnderThis"));
          JSONArray janew=new JSONArray();
          for (int k=0;k<ja.size();k++){
            JSONObject joj=JSONObject.fromObject(ja.get(k));
            if(joj.get("commentUnderThis").equals(null)){
              joj.put("commentUnderThis"," ");
            }
            janew.add(joj);
          }
          jo.put("commentUnderThis",janew);
        }catch (Exception e){

        }
      }


      acv2.add(jo);
    }
  }
 a.setReplyCount(acv2.size());
    a.setComments(acv2);

  la.add(a);
}


messageBean.setExtraList(la.toArray());



    return messageBean;
  }

  /**
   * 内容关键字全局搜索（包括话题和回复）
   *
   * @param key   搜索关键字
   * @param order time,follow_count,reply_count，author等与desc,asc的组合
   * @return extraList中的Article对象数组
   */
  @RequestMapping(value = "/search")
  public MessageBean search(@RequestParam String key, @RequestParam(defaultValue = "time asc") String order) {
    if (!CheckUtil.checkOrderField(order)) {
      return MessageBean.getInvalidField("order");
    }
    MessageBean messageBean;
    if (key.isEmpty()) {
      messageBean = MessageBean.getInvalidField("关键字");
    } else {
      System.out.println("search");
      messageBean = MessageBean.SUCCESS.clone();
      String searchContent = "%" + key + "%";
      messageBean.setExtra(key);
      messageBean.setExtraList(articleService.articleSearch(searchContent, order).toArray());
    }
    return messageBean;
  }

  /**
   * 获取话题的赞用户和评论
   *
   * @param id     话题id
   * @param source 进入渠道，0为讨论区，1为提醒
   * @return comment：话题评论数组ArticleComment
   * user：赞过该话题用户列表-to
   */
  @RequestMapping("/getDetail")
  public MessageBean getFollowAndComment(@RequestParam int id, @RequestParam(defaultValue = "0") byte source) {
    User user = User.getCurrentUser();
    Article article = articleService.selectByPrimaryKey(id, user);
    if (article == null) {
      return MessageBean.ARTICLE_NOT_EXIST;
    }
    UserViewArticleLog userViewArticleLog = null;
    if (user != null) {
      userViewArticleLog = userViewArticleLogService.selectTimeEndNullByArticleUser(id, user.getId());
      Date now = new Date();
      if (userViewArticleLog == null) {
        userViewArticleLog = new UserViewArticleLog();
        userViewArticleLog.setArticleId(id);
        userViewArticleLog.setUserId(user.getId());
        userViewArticleLog.setSource(source);
        userViewArticleLogService.insert(userViewArticleLog);
      } else if (now.getTime() - userViewArticleLog.getTime().getTime() > 1000 * 60 * 20) {
        userViewArticleLog.setTime(now);
        userViewArticleLogService.updateByPrimaryKey(userViewArticleLog);
      }
    }
    MessageBean messageBean = MessageBean.SUCCESS.clone();
    List<ArticleCommentView> articleCommentList = articleCommentService.selectByArticleId(id, true);
    for (ArticleCommentView articleCommentView : articleCommentList) {
      List<ArticleCommentView> acs= articleCommentService.selectCommentUnderComment(articleCommentView.getId());





      List<ArticleCommentView> acs2=new ArrayList();
      for (int i=0;i<acs.size();i++){
        if(acs.get(i).getType()!=0){
          if(!acs.get(i).getCommenterId().equals(User.getCurrentUser().getId())||acs.get(i).getType()==3){
          continue;
          }
        }



        ArticleCommentView acv=articleCommentService.selectByPrimaryKey(acs.get(i).getCommentToId());
        if(acv.getType()!=0){
            if(acv.getCommenterId().equals(User.getCurrentUser().getId())){
              acs2.add(acs.get(i));
            }
        }else{
          acs2.add(acs.get(i));
        }
      }

      articleCommentView.setCommentUnderThis(acs2);
    }
        /*
        List<UserBriefInfo> userList = userService.selectFollowUserByArticleId(id);
        article.setLikeUsers(userList);*/
        article.setPraiseUser(articleService.getLikeArticleList(id));
    article.setComments(articleCommentList);
    if (userViewArticleLog != null) {
      article.setExtra(userViewArticleLog.getId().toString());
    }
    messageBean.setExtra(article);


    //过滤
    JSONObject js=JSONObject.fromObject(messageBean.getExtra());
   List<ArticleCommentView> acv=(List)js.get("comments");
    List<Object> acv2=new ArrayList();
    for(int i=0;i<acv.size();i++){
      JSONObject jo=JSONObject.fromObject(acv.get(i));
      if(jo.get("type")==3){
        continue;
      }


  //判断是否type为1
      if(jo.get("type")==1||jo.get("type")==2){
        if(user.getId().equals(jo.get("commenterId"))){
        //被删除但是是自己发的依旧可见

          if(jo.get("commentUnderThis").equals(null)){
            jo.put("commentUnderThis"," ");
          }else{
            if(jo.get("commentUnderThis").equals(null)){
              jo.put("commentUnderThis"," ");
            }else{
              try {
                JSONArray ja=JSONArray.fromObject(jo.get("commentUnderThis"));
                JSONArray janew=new JSONArray();
                for (int k=0;k<ja.size();k++){
                  JSONObject joj=JSONObject.fromObject(ja.get(k));
                  if(joj.get("commentUnderThis").equals(null)){
                    joj.put("commentUnderThis"," ");
                  }
                  janew.add(joj);
                }
                jo.put("commentUnderThis",janew);
              }catch (Exception e){

              }
            }
try{
  String a[] = jo.get("pics").toString().split("i:");
  List li =new ArrayList();
  String b[]=a[1].toString().split(",");
  for(int f=0;f<b.length;f++){
    li.add(b[f]);
  }

  jo.put("pics",li);

}catch (Exception e){
jo.put("pics","");
}

          }

          acv2.add(jo);
        }
      }else{
        //未被删除的
        if(jo.get("commentUnderThis").equals(null)){
          jo.put("commentUnderThis"," ");
        }else{
          if(jo.get("commentUnderThis").equals(null)){
            jo.put("commentUnderThis"," ");
          }else{
            try {
              JSONArray ja=JSONArray.fromObject(jo.get("commentUnderThis"));
              JSONArray janew=new JSONArray();
              for (int k=0;k<ja.size();k++){
                JSONObject joj=JSONObject.fromObject(ja.get(k));
                if(joj.get("commentUnderThis").equals(null)){
                  joj.put("commentUnderThis"," ");
                }
                janew.add(joj);
              }
              jo.put("commentUnderThis",janew);
            }catch (Exception e){

            }
          }
        }
        try{
          String a[] = jo.get("pics").toString().split("i:");
          List li =new ArrayList();
          String b[]=a[1].toString().split(",");
          for(int f=0;f<b.length;f++){
            li.add(b[f]);
          }
          jo.put("pics",li);

        }catch (Exception e){
      jo.put("pics","");
        }
        acv2.add(jo);
      }
    }
    Article art=(Article) messageBean.getExtra();
    Map ma=new HashMap();
    ma.put("aid",art.getId());
    ma.put("uid",user.getId());
    int num=articleCommentService.getCountNum(ma);
    art.setReplyCount(num);
    art.setComments(acv2);
    Group g=groupService.selectByPrimaryKey(art.getGroupId());
    art.setGroupTitle(g.getName());
    messageBean.setExtra(art);
    return messageBean;
  }

  /**
   * 退出阅读
   *
   * @param id
   * @return
   */
  @RequestMapping(value = "/quitRead")
  public MessageBean setQuit(@RequestParam int id) {
    User user = User.getCurrentUser();
    if (user == null) {
      return MessageBean.UNLOGIN;
    }
    UserViewArticleLog userViewArticleLog = userViewArticleLogService.selectByPrimaryKey(id);
    if (userViewArticleLog == null) {
      return MessageBean.ID_NOT_EXIST;
    }
    if (!userViewArticleLog.getUserId().equals(user.getId())) {
      return MessageBean.getMismatch("传入Id", "其对应的用户Id");
    }
    UserViewArticleLog newLog = new UserViewArticleLog();
    newLog.setId(userViewArticleLog.getId());
    newLog.setTimeEnd(new Date());
    userViewArticleLogService.updateByPrimaryKey(newLog);
    return MessageBean.SUCCESS;
  }

  //图片下载
  @RequestMapping(value = "/getpic")
  public void downPhotoByStudentId(final HttpServletResponse response) throws IOException {
//String path="http://112.124.52.129/lk3-h/image/14667324411415999354277786539855.jpg";
//    	String path="http://115.159.46.66/zancheng/upload/shop/zancheng_mall_0384.jpg";
//    	String path2="http://115.159.46.66/zancheng/upload/shop/zancheng_mall_0381.jpg";
//    	List li=new ArrayList();
//    	li.add(path);
//    	li.add(path2);


  }


  /**
   * 话题导出
   *
   * @param id 话题编号
   * @return
   * @throws IOException
   */
  @RequestMapping("/export")
  public void export(Integer id, HttpServletRequest req, HttpServletResponse res) throws IOException {
    Article article = articleService.selectByPrimaryKey(id, null);
    if (article == null) {
      return;
    }
    List<String> imgli = new ArrayList<>();
    List contentValue = new ArrayList();
    if (article.getPublishBackground()) {
      String content = article.getContent();
      //<img src="/lk3-h/image/image/20160728/20160728164334_508.jpg" width="300px" height="300px" alt="" />
      Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
      Matcher m = p.matcher(content);

      while (m.find()) {
        // System.out.println(m.group());
//                 System.out.println(m.group(1));
        System.out.print("YES" + new File(m.group(1)).getName());
        content = content.replace(m.group(), "");
        contentValue.add("=HYPERLINK(\"" + new File(m.group(1)).getName() + "\",\"话题图片\")");
        imgli.add(m.group(1));
      }

      article.setContent(content);
    }

    List<String> li = new ArrayList();
    HSSFWorkbook wb = new HSSFWorkbook();
    HSSFSheet sheet = wb.createSheet("用户信息");
    HSSFRow row = sheet.createRow((int) 0);
    HSSFCellStyle style = wb.createCellStyle();
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
    HSSFCell cell = row.createCell((short) 0);
    cell.setCellValue("发布者昵称");
    cell.setCellStyle(style);
    cell = row.createCell((short) 1);
    cell.setCellValue("手机号");
    cell.setCellStyle(style);
    cell = row.createCell((short) 2);
    cell.setCellValue("标题");
    cell.setCellStyle(style);
    cell = row.createCell((short) 3);
    cell.setCellValue("话题内容");
    cell.setCellStyle(style);
    cell = row.createCell((short) 4);
    cell.setCellValue("图片");
    cell.setCellStyle(style);
    //发布者
    row = sheet.createRow((int) 1);
    row.createCell((short) 0).setCellValue(article.getAuthorNickname());
    row.createCell((short) 1).setCellValue(article.getAuthorName());
    row.createCell((short) 2).setCellValue(article.getTitle());
    row.createCell((short) 3).setCellValue(article.getContent());
    for (int i = 0; i < contentValue.size(); i++) {
      row.createCell((short) 4 + i).setCellValue(contentValue.get(i).toString());
    }

    if (!article.getPublishBackground()) {
      imgli.addAll(Arrays.asList(article.getPictures()));
    }
//        String serverBase = req.getRequestURL().substring(0, req.getRequestURL().indexOf("article/export.do"));
    String serverBase = req.getSession().getServletContext().getRealPath("");
//        String absoluteDir = req.getSession().getServletContext().getRealPath("");
    String prefix = "lk3-h/";
    for (int i = 0; i < imgli.size(); ++i) {
      String imgPath = imgli.get(i);
      if (article.getPublishBackground()) {
        imgPath = imgPath.substring(imgPath.indexOf(prefix) + prefix.length() - 1);
      }
      imgPath = serverBase + imgPath;
      imgli.set(i, imgPath);
    }
    if (!article.getPublishBackground()) {
      for (int j = 0; j < imgli.size(); j++) {
        row.createCell((short) 4 + j).setCellValue("=HYPERLINK(\"" + new File(imgli.get(j)).getName() + "\",\"话题图片\")");
      }
    }

    row = sheet.createRow((int) 5);
//        row.createCell((short) 0).setCellValue("评论回复");
    row.createCell((short) 0).setCellValue("评论者昵称");
    row.createCell((short) 1).setCellValue("评论者电话");
    row.createCell((short) 2).setCellValue("评论时间");
    row.createCell((short) 3).setCellValue("内容");
    row.createCell((short) 4).setCellValue("图片");

    List<ArticleCommentView> acli = articleCommentService.selectByArticleId2(id);
    for (int i = 0; i < acli.size(); i++) {
      row = sheet.createRow((int) 6 + i);
      String name1 = "";
      String name2 = "";
      User user1 = new User();
      User user2 = new User();
      try {
        user1 = userService.selectByPrimaryKey(acli.get(i).getCommenterId());

      } catch (Exception e) {

      }
      try {
        ArticleCommentView ac = articleCommentMapper.selectByPrimaryKey(acli.get(i).getCommentToId());
        user2 = userService.selectByPrimaryKey(ac.getCommenterId());

      } catch (Exception e) {

      }
      if (acli.get(i).getCommenterNickname() == null || acli.get(i).getCommenterNickname() == "") {
        name1 = acli.get(i).getCommenterName();


      } else {
        name1 = acli.get(i).getCommenterNickname();


      }
      if (acli.get(i).getCommentToNickname() == null || acli.get(i).getCommentToNickname() == "") {
        name2 = acli.get(i).getCommentToName();
      } else {

        name2 = acli.get(i).getCommenterNickname();
      }
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");


      if (name2 == "" || name2 == null) {
        row.createCell((short) 0).setCellValue(name1);

      } else {
        row.createCell((short) 0).setCellValue(name1 + "@" + name2);
      }
      try {
        if (user2 != null) {
          if (user2.getName().length() != 0) {
            row.createCell((short) 1).setCellValue(user1.getName() + ";" + user2.getName());
          } else {
            row.createCell((short) 1).setCellValue(user1.getName() + ";");
          }
        } else {
          row.createCell((short) 1).setCellValue(user1.getName() + ";");
        }
      } catch (Exception e) {
        try {
          row.createCell((short) 1).setCellValue(user1.getName() + ";");

        } catch (Exception f) {


        }

      }
      try {
        row.createCell((short) 2).setCellValue(sdf.format(acli.get(i).getTime()));

      } catch (Exception e) {
        row.createCell((short) 2).setCellValue("");
      }

      row.createCell((short) 3).setCellValue(acli.get(i).getContent());


      List<String> acimgli = Arrays.asList(acli.get(i).getPictures());
      if (acimgli != null) {
        for (int j = 0; j < acimgli.size(); ++j) {
          acimgli.set(j, serverBase + acimgli.get(j));

          imgli.add(acimgli.get(j));
        }
      }
      for (int j = 0; j < acimgli.size(); j++) {

        row.createCell((short) 4 + j).setCellValue("=HYPERLINK(\"" + new File(acimgli.get(j)).getName() + "\",\"话题评论图片\")");
      }
    }
    String outputFilePath = System.getProperty("java.io.tmpdir") + "/excel";
    String xlsPath = outputFilePath + article.getTitle() + ".xls";
    try {
      FileOutputStream fout = new FileOutputStream(xlsPath);
      wb.write(fout);
      fout.flush();
      fout.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    imgli.add(xlsPath);
    for (int i = 0; i < imgli.size(); i++) {
      System.out.println(imgli.get(i));
    }

    ZipUtil.writeZip(imgli, article.getTitle() + ".zip", res);

  }

  /**
   * 置顶数量
   *
   * @param
   */
  @RequestMapping("/topArticleCount")
  public void topCount(Integer articleid, HttpServletResponse res) throws Exception {
    User user = new User();
    Article article = articleService.selectByPrimaryKey(articleid, user);

    int count = articleService.topCount(article.getGroupId().toString());
    String msg = "";
    if (count >= 10) {
      msg = "error";
    } else {
      msg = "success";
    }
    res.getWriter().write(msg);
  }
/**
 * 是否只展示帖子管理
 * true 所有false展示部分
 */
@RequestMapping("/ArticleRool")
public void ArticleRool(HttpServletResponse res)throws Exception{
  int type=userService.getRootType( User.getCurrentUser().getId());
  String result="";
  if(type==0){
result="false";
  }else if(type==1){
result="true";
  }
  res.getWriter().write(result);
}
}
