package com.wenjuan.vo;

import java.io.Serializable;
import java.util.Date;

public class CommentViewBase implements Serializable {
    private Integer id;

    private Integer commenterId;

    private String commenterName;

    private String commenterAvatar;

    /**
     * 评论的评论id
     */
    private Integer commentToId;

    private Date time;

    private Boolean status;

    private Byte commenterRole;

    private String commenterNickname;

    private String commentToNickname;

    /**
     * 被评论的用户id
     */
    private Integer commentToUserId;

    private String content;

    private String commentToName;

    private Boolean viewed;

    private Object commentUnderThis;

    private Integer commentBase;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(Integer commenterId) {
        this.commenterId = commenterId;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getCommenterAvatar() {
        return commenterAvatar;
    }

    public void setCommenterAvatar(String commenterAvatar) {
        this.commenterAvatar = commenterAvatar;
    }

    public Integer getCommentToId() {
        return commentToId;
    }

    public void setCommentToId(Integer commentToId) {
        this.commentToId = commentToId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Byte getCommenterRole() {
        return commenterRole;
    }

    public void setCommenterRole(Byte commenterRole) {
        this.commenterRole = commenterRole;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getCommentUnderThis() {
        return commentUnderThis;
    }

    public void setCommentUnderThis(Object commentUnderThis) {
        this.commentUnderThis = commentUnderThis;
    }

    public String getCommentToName() {
        return commentToName;
    }

    public void setCommentToName(String commentToName) {
        this.commentToName = commentToName;
    }

    public String getCommenterNickname() {
        return commenterNickname;
    }

    public void setCommenterNickname(String commenterNickname) {
        this.commenterNickname = commenterNickname;
    }

    public String getCommentToNickname() {
        return commentToNickname;
    }

    public void setCommentToNickname(String commentToNickname) {
        this.commentToNickname = commentToNickname;
    }

    public Boolean getViewed() {
        return viewed;
    }

    public void setViewed(Boolean viewed) {
        this.viewed = viewed;
    }

    public Integer getCommentToUserId() {
        return commentToUserId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setCommentToUserId(Integer commentToUserId) {
        this.commentToUserId = commentToUserId;
    }

    public Integer getCommentBase() {
        return commentBase;
    }

    public void setCommentBase(Integer commentBase) {
        this.commentBase = commentBase;
    }

    public Integer type;
}
