package com.wenjuan.controller;

import com.wenjuan.bean.MessageBean;
import com.wenjuan.bean.PageBean;
import com.wenjuan.dao.SensitiveMapper;
import com.wenjuan.model.*;
import com.wenjuan.service.*;
import com.wenjuan.utils.CheckUtil;
import com.wenjuan.utils.CommonUtil;
import com.wenjuan.utils.ScoreUtil;
import com.wenjuan.utils.ZipUtil;
import com.wenjuan.vo.DiaryCommentView;
import com.wenjuan.vo.UserBriefInfo;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping(value = "/diary")
public class DiaryController {
    private static byte DIARY_DEFAULT_STATUS = 0;
    @Autowired
    @Qualifier("diaryService")
    private DiaryService diaryService;
    @Autowired
    @Qualifier("diaryCommentService")
    private DiaryCommentService diaryCommentService;
    @Autowired
    @Qualifier("userFollowDiaryService")
    private UserFollowDiaryService userFollowDiaryService;
    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @Autowired
    @Qualifier("userViewDiaryLogService")
    private UserViewDiaryLogService userViewDiaryLogService;
    @Resource
    private HttpServletRequest request;

    @Resource
    private SensitiveMapper sensitiveMapper;
    @Autowired
    @Qualifier("organizationUserService")
    private OrganizationUserService organizationUserService;
    @Autowired
    @Qualifier("organizationGroupService")
    private OrganizationGroupService organizationGroupService;

    /**
     * @param content 日记内容 必须
     * @param pics    pics中传参格式为图片连接1,图片连接2,，英文逗号诶分割
     * @param video   视频链接
     * @param status  状态，0为正常，1为隐藏，默认0
     * @return extra中为map对象，key为"value"的为插入后的Diary详情
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/add")
    public MessageBean add(@RequestParam String content,
                           @RequestParam(defaultValue = "") String pics,
                           @RequestParam(defaultValue = "") String video,
                           @RequestParam(defaultValue = "0") byte status,
                           HttpSession session) throws UnsupportedEncodingException {
        //替换敏感字
        String con=sensitiveMapper.selectSensitive(Integer.parseInt(session.getAttribute("ogid").toString()));
        String[] str = con.split("\\,");
        for(int i=0;i<str.length;i++){
            String rep=str[i];
            if(rep.trim().length()==0){
                continue;
            }
            String replen="";
            for(int f=0;f<rep.length();f++){
                replen+="*";
            }
            while (content.indexOf(rep)!=-1) {
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
        Diary diary = new Diary();
        diary.setContent(content);
        diary.setPics(String.format("v:%s;i:%s", video, pics));
        diary.setAuthor(user.getId());
        diary.setStatus(status);
        diary.setOgid(Integer.parseInt(session.getAttribute("ogid").toString()));
        diaryService.insert(diary);
        Map<String, Object> map = new HashMap<>();
        map.put("value", diaryService.selectByPrimaryKey(diary.getId()));
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtra(map);
      //  ScoreUtil.addScore(user.getId(), ScoreUtil.PUBLISH_DIARY);//发布日记加分

        return messageBean;
    }

    /**
     * 发表日记的同时上传图片
     * 限制：POST body里面是多张图片数据，编码为base64字符串，每张图片以英文封号分割,不要传其他参数，例如pics=base64字符串。
     * 注意：注意上传图片大小限制：4MB
     *
     * @param content 内容，GET传递
     * @param request 不需要客户端传
     * @param video   视频URI,GET
     * @param status  状态，0为正常，1为隐藏，默认0，GET
     * @return extra中为map对象，key为"value"的为插入后的Diary详情
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/addByBase64")
    public MessageBean add(@RequestParam String content,
                           @RequestParam(required = false) @RequestBody String pics,
                           HttpServletRequest request,
                           @RequestParam(required = false) String video,
                           @RequestParam(defaultValue = "0") byte status,
                           HttpSession session) throws UnsupportedEncodingException {
        //替换敏感字
        String con=sensitiveMapper.selectSensitive(Integer.parseInt(session.getAttribute("ogid").toString()));
        String[] str = con.split("\\,");
        for(int i=0;i<str.length;i++){
            String rep=str[i];
            if(rep.trim().length()==0){
                continue;
            }
            String replen="";
            for(int f=0;f<rep.length();f++){
                replen+="*";
            }
            while (content.indexOf(rep)!=-1){
                content=content.replace(rep,replen);
            }
        }

        List<String> picList = CommonUtil.SplitBase64ArrayToPathArray(pics, request, userService);
        return add(content, StringUtils.arrayToDelimitedString(picList.toArray(), ","), video, status,session);
    }

    /**
     * 删除日记
     *
     * @param id 日记id
     * @return
     */
    @RequestMapping(value = "/delete")
    public MessageBean delete(@RequestParam int id) {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        Diary diary = diaryService.selectByPrimaryKey(id);
        if (diary == null) {
            return MessageBean.DIARY_NOT_EXIST;
        }
        if (!diary.getAuthor().equals(user.getId())) {
            return MessageBean.INVALID_AUTHOR;
        }
        return MessageBean.getMessageBean(diaryService.deleteByPrimaryKey(id), MessageBean.ErrorMessageType.SYSTEM_ERROR);
    }

    /**
     * 设置日记的可见的访问权限
     *
     * @param id 日记id
     * @return 其中extra为1表示可见，2表示不可见
     */
    @RequestMapping(value = "/visible")
    public MessageBean setVisible(@RequestParam int id) {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        Diary diary = diaryService.selectByPrimaryKey(id);
        if (diary == null) {
            return MessageBean.DIARY_NOT_EXIST;
        }
        if (!diary.getAuthor().equals(user.getId())) {
            return MessageBean.INVALID_AUTHOR;
        }

        MessageBean messageBean = MessageBean.getMessageBean(diaryService.toggleVisible(id), MessageBean.ErrorMessageType.DIARY_NOT_EXIST);
        messageBean.setExtra(2 - diary.getStatus());
        return messageBean;
    }

    /**
     * @param content
     * @param diary
     * @param commentTo
     * @param lastCommentId 最后一次获取评论的时间，为0表示不获取，默认为0
     * @return 其中extraList包含最新的评论信息，类型为DiaryComment
     */
    @RequestMapping(value = "/comment")
    public MessageBean comment(@RequestParam String content,
                               @RequestParam int diary,
                               @RequestParam(required = false) Integer commentTo,
                               @RequestParam(defaultValue = "0") int lastCommentId,
                               HttpSession session) {
        //替换敏感字
        String con=sensitiveMapper.selectSensitive(Integer.parseInt(session.getAttribute("ogid").toString()));

        String[] str = con.split("\\,");
        for(int i=0;i<str.length;i++){
            String rep=str[i];
            if(rep.trim().length()==0){
                continue;
            }
            String replen="";
            for(int f=0;f<rep.length();f++){
                replen+="*";
            }
            while (content.indexOf(rep)!=-1){
                content=content.replace(rep,replen);
            }
        }

        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        if (content == null || content.isEmpty()) {
            return MessageBean.getInvalidField("评论内容");
        }
        DiaryComment diaryComment = new DiaryComment();
        diaryComment.setCommenter(user.getId());
        diaryComment.setContent(content);
        diaryComment.setDiary(diary);
        diaryComment.setCommentTo(commentTo);
        MessageBean messageBean = MessageBean.getMessageBean(diaryCommentService.insert(diaryComment), MessageBean.ErrorMessageType.SYSTEM_ERROR);
        if (messageBean.getCode() == MessageBean.SUCCESS.getCode()) {
            if (lastCommentId != 0) {
                messageBean.setExtraList(diaryCommentService.selectCommentAfter(diaryComment.getDiary(), lastCommentId).toArray());
            }
            messageBean.setExtra(diaryCommentService.selectByPrimaryKey(diaryComment.getId()));
        }
        return messageBean;
    }

    /**
     * 赞日记
     *
     * @param id 日记id
     * @return 其中extra为1表示已赞，0表示取消赞
     */
    @RequestMapping(value = "/favor")
    public MessageBean favor(@RequestParam int id) {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        Diary diary = diaryService.selectByPrimaryKey(id);
        if (diary == null) {
            return MessageBean.DIARY_NOT_EXIST;
        }
        UserFollowDiary userFollowDiary = userFollowDiaryService.selectByPrimaryKey(user.getId(), id);
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        if (userFollowDiary == null) {
            userFollowDiary = new UserFollowDiary();
            userFollowDiary.setUserId(user.getId());
            userFollowDiary.setDiaryId(id);
            userFollowDiaryService.insert(userFollowDiary);
            messageBean.setExtra(1);
        } else {
            userFollowDiaryService.deleteByPrimaryKey(user.getId(), id);
            messageBean.setExtra(0);
        }
        return messageBean;
    }

    /**
     * 获取赞指定日记的用户
     *
     * @param id 日记id
     * @return 其中extraList为User对象数组
     */
    @RequestMapping(value = "/getFavors")
    public MessageBean getFavors(@RequestParam int id) {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtraList(userService.getDiaryFavors(id).toArray());
        return messageBean;
    }

    /**
     * 获取赞过的日记列表
     *
     * @param page 分页页数
     * @return 其中extraList为Diary类型的数组
     */
    @RequestMapping(value = "/getFavorites")
    public MessageBean getFavorites(@RequestParam(defaultValue = "1") int page,HttpSession session) {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        Map map=new HashMap();
        map.put("id",user.getId());
        map.put("ogid",Integer.parseInt(session.getAttribute("ogid").toString()));
        messageBean.setExtra(new PageBean(page, diaryService.selectMyFavor(map)));
        return messageBean;
    }

    /**
     * 获取所有日记
     *
     * @param type     0：自己发布的，1：全部可见的
     * @param order    time,follow_count,reply_count，author等与desc,asc的组合
     * @param loadId   字符A或B开头，后面跟上id，分别表示After和Before,例如A0表示id为0之后的日记
     * @param page     分页页数
     * @param getCount 是否只获取数量
     * @return 在extraList中，类型为ArticleDiaryDetailBean的数组，实例为Diary
     */
    @RequestMapping(value = "/getList")
    public MessageBean getDiaryList(
            @RequestParam(defaultValue = "1") int type,
            @RequestParam(defaultValue = "time desc") String order,
            @RequestParam(defaultValue = "A0") String loadId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "false") boolean getCount,
            HttpSession session) {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        if (type != 0 && type != 1) {
            return MessageBean.getInvalidField("类型type");
        }
        if (!CheckUtil.checkOrderField(order)) {
            return MessageBean.getInvalidField("排序字段order");
        }
        if (!CheckUtil.checkLoadId(loadId)) {
            return MessageBean.getInvalidField("loadId");
        }
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        if (getCount) {
            messageBean.setExtra(diaryService.selectDiariesCount(type, user, loadId,Integer.parseInt(session.getAttribute("ogid").toString())));
        } else {
            messageBean.setExtraList(diaryService.selectDiaries(type, user, order, page, null, loadId,Integer.parseInt(session.getAttribute("ogid").toString())).toArray());
        }
        return messageBean;
    }

    /**
     * 获取日记的赞的用户和评论
     *
     * @param id 日记id
     * @return
     */
    @RequestMapping("/getDetail")
    public MessageBean getFollowAndComment(@RequestParam int id) {
        Diary diary = diaryService.selectByPrimaryKey(id);
        if (diary == null) {
            return MessageBean.DIARY_NOT_EXIST;
        }
        User user = User.getCurrentUser();
        if (user != null) {
            UserViewDiaryLog userViewDiaryLog = new UserViewDiaryLog();
            userViewDiaryLog.setUserId(user.getId());
            userViewDiaryLog.setDiaryId(id);
            userViewDiaryLogService.insert(userViewDiaryLog);
        }
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        List<DiaryCommentView> diaryCommentList = diaryCommentService.selectByDiaryId(id, false);
        /*
        for (DiaryCommentView diaryCommentView : diaryCommentList) {
            diaryCommentView.setCommentUnderThis(diaryCommentService.selectCommentUnderComment(diaryCommentView.getId()));
        }*/
        List<UserBriefInfo> userList = userService.selectFollowUserByDiaryId(id);
        diary.setComments(diaryCommentList);
        diary.setLikeUsers(userList);
        messageBean.setExtra(diary);
        return messageBean;
    }

    /**
     * 内容关键字全局搜索（包括日记和回复）
     *
     * @param key  搜索关键字
     * @param page 分页页数
     * @return extraList中的Diary对象数组
     */
    @RequestMapping(value = "/search")
    public MessageBean search(@RequestParam String key, @RequestParam(defaultValue = "1") int page) {
        MessageBean messageBean = MessageBean.SUCCESS.clone();

        messageBean.setExtraList(diaryService.diarySearch(key, page,organizationGroupService.selectByOAdmin(User.getCurrentUser().getId())).toArray());
        return messageBean;
    }

    /**
     * 显示评论消息
     *
     * @param isReceive true为收到的评论，false为发出的评论
     * @param loadTime  A或B后接评论时间戳
     * @param page      0为不分页
     * @param order     默认时间降序
     * @return
     */
    @SuppressWarnings("Duplicates")
    @RequestMapping("/commentList")
    public MessageBean selectCommentMsg(@RequestParam boolean isReceive,
                                        @RequestParam String loadTime,
                                        @RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "time desc") String order,HttpSession session) {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtraList(diaryCommentService.selectCommentMsg(isReceive, loadTime, page, user, order,Integer.parseInt(session.getAttribute("ogid").toString())).toArray());
        return messageBean;
    }

    @RequestMapping("/count")
    public MessageBean diaryCount(HttpSession session) {
        MessageBean messageBean = MessageBean.SUCCESS.clone();

        messageBean.setExtra(diaryService.diaryCount(Integer.parseInt(session.getAttribute("ogid").toString())));
        return messageBean;
    }

    /**
     * 导出原日记及附带评论
     *
     * @param id 日记id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/export")
    public void export(@RequestParam int id, HttpServletRequest req, HttpServletResponse res) throws IOException {
        String title = "";
        //获取日记信息
        Diary d = diaryService.selectByPrimaryKey(id);
        //获取作者信息
        User user = userService.selectByPrimaryKey(d.getAuthor());
        //获取所有评论
        List<DiaryCommentView> dc = diaryCommentService.selectByDiaryId(id, false);
        //创建excel设置标题
//        String path = req.getServletContext().getRealPath(File.separator);
        //String path ="C://Users//Administrator//Desktop";
        //下载
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("用户信息");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("编号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 1);
        cell.setCellValue("作者");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("内容");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("图片");
        cell.setCellStyle(style);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

        row = sheet.createRow((int) 1);
        // 第四步，创建单元格，并设置值
        row.createCell((short) 0).setCellValue(d.getId());
        try {
            row.createCell((short) 1).setCellValue(user.getNickname());
            title = user.getNickname();
        } catch (NullPointerException e) {
            row.createCell((short) 1).setCellValue(user.getName());
            title = user.getName();
        }
        row.createCell((short) 2).setCellValue(sdf.format(d.getTime()));

        row.createCell((short) 3).setCellValue(d.getContent());
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        List<String> imgli = new ArrayList<>();
        imgli.addAll(Arrays.asList(d.getPictures()));
//        String serverBase = req.getRequestURL().substring(0, req.getRequestURL().indexOf("article/export.do"));
        String serverBase = req.getSession().getServletContext().getRealPath("");
//        String absoluteDir = req.getSession().getServletContext().getRealPath("");
        String prefix = "lk3-h/";
        for (int i = 0; i < imgli.size(); ++i) {
            String imgPath = imgli.get(i);
           // imgPath = imgPath.substring(imgPath.indexOf(prefix) + prefix.length() - 1);
            imgli.set(i, serverBase +"/"+imgPath);
        }
        for (int j = 0; j < imgli.size(); j++) {
            row.createCell((short)4 + j).setCellValue("=HYPERLINK(\"" + new File(imgli.get(j)).getName() + "\",\"日记图片\")");
        }
        //5行开始

        HSSFRow row3 = sheet.createRow((int) 6);
        HSSFCell cell3 = row3.createCell((short) 0);
        cell3.setCellValue("用户昵称");
        cell3.setCellStyle(style);
        HSSFCell cell22 = row3.createCell((short) 1);
        cell22.setCellValue("手机号");
        cell22.setCellStyle(style);
        HSSFCell cell33 = row3.createCell((short) 2);
        cell33.setCellValue("时间");
        cell33.setCellStyle(style);
        HSSFCell cell4 = row3.createCell((short) 3);
        cell4.setCellValue("内容");
        cell4.setCellStyle(style);
        int rows=0;
        for (int i = 0; i < dc.size(); i++) {
            String name1 = dc.get(i).getCommenterNickname();
            String name2 = dc.get(i).getCommentToNickname();
            String comment = dc.get(i).getContent();
            HSSFRow row2 = sheet.createRow((int) i + 7);
            rows=i+7+5;
            if (name2 == null) {
                row2.createCell((short) 0).setCellValue(name1);
            } else {
                row2.createCell((short) 0).setCellValue(name1 + "@" + name2);
            }
            User user1=new User();
            User user2=new User();
            try{
                user1=userService.selectByPrimaryKey(diaryCommentService.selectByPrimaryKey(dc.get(i).getId()).getCommenterId());

            }catch (Exception e){

            }
            DiaryCommentView dcss=diaryCommentService.selectByPrimaryKey(dc.get(i).getId());
            if(dcss.getCommentToId()!=null){
                try{
                    DiaryCommentView dcs=diaryCommentService.selectByPrimaryKey(dcss.getCommentToId());

                    user2=userService.selectByPrimaryKey(dcs.getCommenterId());

                }catch (Exception e){

                }
            }
          if(user2!=null){
              try{
                  if(user2.getName().length()!=0){

                      row2.createCell((short) 1).setCellValue(user1.getName() + ";" + user2.getName());

                  }else{
                      row2.createCell((short) 1).setCellValue(user1.getName() + ";");

                  }
              }catch (NullPointerException e){
                  row2.createCell((short) 1).setCellValue(user1.getName() + ";");

              }

          }else{
              try {
                  row2.createCell((short) 1).setCellValue(user1.getName() + ";");

              }catch (Exception e){

              }

          }




            row2.createCell((short) 2).setCellValue(sdf.format(dc.get(i).getTime()));




            row2.createCell((short) 3).setCellValue(comment);

        }
        //点赞列表
        HSSFRow row5 = sheet.createRow((int) rows);
        HSSFCell ce = row5.createCell((short) 0);
        ce.setCellValue("点赞昵称");
        ce.setCellStyle(style);
        HSSFCell ce2 = row5.createCell((short) 1);
        ce2.setCellValue("手机号");
        ce2.setCellStyle(style);
        HSSFCell ce3 = row5.createCell((short) 2);
        ce3.setCellValue("时间");
        ce3.setCellStyle(style);
        List<UserFollowDiary> ludf=userFollowDiaryService.selectAll(id);
        for(int i=0;i<ludf.size();i++){
            HSSFRow row6 = sheet.createRow((int) rows+1+i);

            User userss=userService.selectByPrimaryKey(ludf.get(i).getUserId());
            String time=sdf.format(ludf.get(i).getTime());
            try{
                row6.createCell((short) 0).setCellValue(userss.getNickname());

            }catch (Exception e){
                row6.createCell((short) 0).setCellValue("");

            }
            row6.createCell((short) 1).setCellValue(userss.getName());
            row6.createCell((short) 2).setCellValue(time);


        }


        String outputFilePath = System.getProperty("java.io.tmpdir") + "/excel";
        String xlsPath = outputFilePath + user.getNickname() + "的日记.xls";
        try {
            FileOutputStream fout = new FileOutputStream(xlsPath);
            wb.write(fout);
            fout.flush();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        imgli.add(xlsPath);
        for(int i=0;i<imgli.size();i++){
            System.out.println(imgli.get(i));
        }
        ZipUtil.writeZip(imgli, title + "的日记.zip", res);

    }

    /**
     * 日记置顶
     *
     * @throws IOException
     */
    @RequestMapping("/top")
    public void diaryTop(int id, HttpServletResponse res) throws IOException {
        Diary diary = new Diary();
        diary.setId(id);
        Date time = new Date();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("date", time);
        diaryService.top(map);
        res.getWriter().write("success");
    }

}
