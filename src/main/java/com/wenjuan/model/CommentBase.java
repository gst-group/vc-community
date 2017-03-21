package com.wenjuan.model;


import java.io.Serializable;
import java.util.Date;

public abstract class CommentBase implements Serializable {
    /**
     * 评论id
     */
    private Integer id;

    /**
     * 评论的评论id
     */
    private Integer commentTo;

    /**
     * 评论的评论用户名
     */
    private String commentToName;

    /**
     * 评论人id
     */
    private Integer commenter;

    /**
     * 评论用户名
     */
    private String commenterName;

    /**
     * 评论时间
     */
    private Date time;

    /**
     * 1表示删除该评论，0表示正常，请注意检查
     */
    private Byte status;

    /**
     * 评论内容
     */
    private String content;

    private Integer commentBase;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommentTo() {
        return commentTo;
    }

    public void setCommentTo(Integer commentTo) {
        this.commentTo = commentTo;
    }

    public String getCommentToName() {
        return commentToName;
    }

    public void setCommentToName(String commentToName) {
        this.commentToName = commentToName;
    }

    public Integer getCommenter() {
        return commenter;
    }

    public void setCommenter(Integer commenter) {
        this.commenter = commenter;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCommentBase() {
        return commentBase;
    }

    public void setCommentBase(Integer commentBase) {
        this.commentBase = commentBase;
    }

    public Integer type;
}
