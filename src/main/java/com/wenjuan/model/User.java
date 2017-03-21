package com.wenjuan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wenjuan.utils.ApplicationContextUtil;
import com.wenjuan.vo.UserBriefInfo;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class User extends UserBriefInfo {

    /**
     * 用户角色，1用户，2管理员3两边都是
     */
    public Integer role;
    @JsonIgnore
    private String password;
    /**
     * 0为男，1为女
     */
    private Byte sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 学历，未定
     */
    private String degree;
    /**
     * 是否已婚
     */
    private String marriage;

    /**
     * 毕业院校
     */
    private String graduatedFrom;

    /**
     * 专业
     */
    private String major;

    /**
     * 居住地字符串
     */
    private String location;

    /**
     * 兴趣爱好，以英文封号分割
     */
    private String interest;

    /**
     * 个性签名
     */
    private String personalizedSignature;

    /**
     * 剩余积分数
     */
    private Integer score;

    /**
     * 电话号码
     */
    private String realname;

    /**
     * 是否订阅新商品
     */
    private Boolean subscribe;

    /**
     * 是否允许发布话题
     */
    private Boolean allowArticle;

    private Date viewReplyTimeArticle;

    private Date viewReplyTimeDiary;

    private Date queryLastDiaryComment;

    private Date queryLastArticleComment;

    private Date queryLastFeedback;

    private Date queryLastDiaryLike;

    private Integer queryLastDiaryId;

    private Integer queryLastArticleId;

    private Integer queryLastPushId;

    private Date registerTime;

    /**
     * 暂时无用
     */
    private String extra;

    private Integer loginCount;

    private Integer questionnaireTime;

    private Boolean addBackground;

    //是否只展示话题0话题管理1全部
    private int rootType;

    public int getRootType() {
        return rootType;
    }

    public void setRootType(int rootType) {
        this.rootType = rootType;
    }

    @JsonIgnore
    private List<ColumnInfoView> columnInfoViews;

    public static User getCurrentUser() {
        return ApplicationContextUtil.getUserUtil().getUser();
    }

    public static String getSexInfo(Byte sex) {
        if (sex == null) {
            return "保密";
        } else if (sex == 0) {
            return "男";
        } else {
            return "女";
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getGraduatedFrom() {
        return graduatedFrom;
    }

    public void setGraduatedFrom(String graduatedFrom) {
        this.graduatedFrom = graduatedFrom == null ? null : graduatedFrom.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest == null ? null : interest.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public Boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public Boolean getAllowArticle() {
        return allowArticle;
    }

    public void setAllowArticle(Boolean allowArticle) {
        this.allowArticle = allowArticle;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }

    /**
     * 是否为管理员
     *
     * @return
     */
    public boolean isAdmin() {
        return getRole() == 2 || getRole() == 3;
    }

    /**
     * 是否为超级管理员
     *
     * @return
     */
    public boolean isSuperAdmin() {
        return getRole() == 3;
    }

    /**
     * 是否为普通用户
     */
    public boolean isTypicalUser() {
        return true;
    }

    public String getPersonalizedSignature() {
        return personalizedSignature;
    }

    public void setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Date getQueryLastDiaryComment() {
        return queryLastDiaryComment;
    }

    public void setQueryLastDiaryComment(Date queryLastDiaryComment) {
        this.queryLastDiaryComment = queryLastDiaryComment;
    }

    public Date getQueryLastArticleComment() {
        return queryLastArticleComment;
    }

    public void setQueryLastArticleComment(Date queryLastArticleComment) {
        this.queryLastArticleComment = queryLastArticleComment;
    }

    public Date getQueryLastFeedback() {
        return queryLastFeedback;
    }

    public void setQueryLastFeedback(Date queryLastFeedback) {
        this.queryLastFeedback = queryLastFeedback;
    }

    public Date getQueryLastDiaryLike() {
        return queryLastDiaryLike;
    }

    public void setQueryLastDiaryLike(Date queryLastDiaryLike) {
        this.queryLastDiaryLike = queryLastDiaryLike;
    }

    public Integer getQueryLastDiaryId() {
        return queryLastDiaryId;
    }

    public void setQueryLastDiaryId(Integer queryLastDiaryId) {
        this.queryLastDiaryId = queryLastDiaryId;
    }

    public Date getViewReplyTimeArticle() {
        return viewReplyTimeArticle;
    }

    public void setViewReplyTimeArticle(Date viewReplyTimeArticle) {
        this.viewReplyTimeArticle = viewReplyTimeArticle;
    }

    public Date getViewReplyTimeDiary() {
        return viewReplyTimeDiary;
    }

    public void setViewReplyTimeDiary(Date viewReplyTimeDiary) {
        this.viewReplyTimeDiary = viewReplyTimeDiary;
    }

    public Integer getQueryLastArticleId() {
        return queryLastArticleId;
    }

    public void setQueryLastArticleId(Integer queryLastArticleId) {
        this.queryLastArticleId = queryLastArticleId;
    }

    public Integer getQueryLastPushId() {
        return queryLastPushId;
    }

    public void setQueryLastPushId(Integer queryLastPushId) {
        this.queryLastPushId = queryLastPushId;
    }

    public Integer getQuestionnaireTime() {
        return questionnaireTime;
    }

    public void setQuestionnaireTime(Integer questionnaireTime) {
        this.questionnaireTime = questionnaireTime;
    }

    public List<ColumnInfoView> getColumnInfoViews() {
        return columnInfoViews;
    }

    public void setColumnInfoViews(List<ColumnInfoView> columnInfoViews) {
        this.columnInfoViews = columnInfoViews;
    }

    public Boolean getAddBackground() {
        return addBackground;
    }

    public void setAddBackground(Boolean addBackground) {
        this.addBackground = addBackground;
    }
}