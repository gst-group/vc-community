package com.wenjuan.model;

import java.io.Serializable;
import java.util.Date;

public class Feedback implements Serializable {
    /**
     * 反馈id
     */
    private Integer id;

    /**
     * 发送反馈的用户
     */
    private Integer user;

    /**
     * 范松反馈时间
     */
    private Date time;

    /**
     * 0为正常，1为以阅读，2为隐藏，3为已读后隐藏
     */
    private Byte status;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 对反馈的回复
     */
    private String reply;

    private Date replyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }
}