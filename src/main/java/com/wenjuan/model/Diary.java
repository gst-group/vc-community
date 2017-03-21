package com.wenjuan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wenjuan.utils.CommonUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Diary implements Serializable, Cloneable {
    /**
     * 日记id
     */
    private Integer id;

    /**
     * 日记发布时间
     */
    private Date time;

    private String title;

    /**
     * 作者id
     */
    private Integer author;

    /**
     * 赞的数量
     */
    private Integer followCount;

    /**
     * 状态，0为正常，1为已删除，请注意检查
     */
    private Byte status;

    /**
     * 日记内容
     */
    private String content;

    /**
     * 日记附加图片和视频连接
     * 采用的格式是：v:视频连接;i:图片连接1,图片连接2,图片连接3
     * 若视频为空，则为 v:;i:img1.jpg,img2.jpg
     */
    @JsonIgnore
    private String pics;

    /**
     * 回复数
     */
    private Integer replyCount;

    /**
     * 评论
     */
    private List<?> comments;

    /**
     * 赞的用户
     */
    private List<?> likeUsers;

    @JsonIgnore
    private String extra;

    private Date top;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Integer getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
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

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics == null ? null : pics.trim();
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Date getTop() {
        return top;
    }

    public void setTop(Date top) {
        this.top = top;
    }

    public int getOgid() {
        return ogid;
    }

    public void setOgid(int ogid) {
        this.ogid = ogid;
    }

    @Override
    public Diary clone() {
        try {
            return (Diary) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return this;

    }

    public String getType() {
        return "image";
    }
    private int ogid;
}