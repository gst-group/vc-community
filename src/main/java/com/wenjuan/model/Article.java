package com.wenjuan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wenjuan.utils.CommonUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Article implements Serializable {
    /**
     * 文章id
     */
    private Integer id;

    /**
     * 作者id
     */
    private Integer author;

    private String authorNickname;

    private String authorName;

    /**
     * 话题标题
     */
    private String title;

    /**
     * 话题发布时间
     */
    private Date time;

    /**
     * 默认全部可见还是全部不可见
     */
    private Boolean avai;

    /**
     * 状态，0为正常，1为已删除，显示文章时请注意检查
     */
    private Byte status;

    /***
     * 话题内容，html字符串，图文混排
     */
    private String content;

    /**
     * 置顶时间，不置顶时为null
     */
    private Date top;

    private Integer followCount;

    private Integer replyCount;

    @JsonIgnore
    private String pics;

    /**
     * 是否为后台发布
     */
    private Boolean publishBackground;

    /**
     * 评论
     */
    private List<?> comments;

    /**
     * 赞的用户
     */
    private List<?> likeUsers;
    /**
     * 赞的数量
     */
    private Integer loveCount;

    public Integer getSensitive() {
        return sensitive;
    }

    public void setSensitive(Integer sensitive) {
        this.sensitive = sensitive;
    }

    /**
     * 是否含有敏感字
     */

    private Integer sensitive;
    /**
     * 赞的用户
     */
     private List<User> praiseUser;

    public List<User> getPraiseUser() {
        return praiseUser;
    }

    public void setPraiseUser(List<User> praiseUser) {
        this.praiseUser = praiseUser;
    }

    private boolean favor = false;

    private boolean viewed = false;

    public Integer getLoveCount() {
        return loveCount;
    }

    public void setLoveCount(Integer loveCount) {
        this.loveCount = loveCount;
    }

    private boolean like=false;

    private String extra;

    private Integer groupId;

    private Integer readCount;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics == null ? null : pics.trim();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Boolean getAvai() {
        return avai;
    }

    public void setAvai(Boolean avai) {
        this.avai = avai;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getTop() {
        return top;
    }

    public void setTop(Date top) {
        this.top = top;
    }

    public Integer getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
    }

    public Boolean getPublishBackground() {
        return publishBackground;
    }

    public void setPublishBackground(Boolean publishBackground) {
        this.publishBackground = publishBackground;
    }

    public List<?> getComments() {
        return comments;
    }

    public void setComments(List<?> comments) {
        this.comments = comments;
    }

    public List<?> getLikeUsers() {
        return likeUsers;
    }

    public void setLikeUsers(List<?> likeUsers) {
        this.likeUsers = likeUsers;
    }

    @JsonProperty("video")
    public String getVideo() {
        return CommonUtil.getVideo(pics);
    }

    @JsonProperty("pics")
    public String[] getPictures() {
        return CommonUtil.getPictures(pics);
    }

    public String getAuthorNickname() {
        return authorNickname;
    }

    public void setAuthorNickname(String authorNickname) {
        this.authorNickname = authorNickname;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public boolean isFavor() {
        return favor;
    }

    public void setFavor(boolean favor) {
        this.favor = favor;
    }

    public boolean isLike() {
      return like;
    }

    public void setLike(boolean like) {
      this.like = like;
    }

  public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public String groupTitle;
}