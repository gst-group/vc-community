package com.wenjuan.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 记录已发送的所有消息
 */
public class Notifies implements Serializable {
    /**
     * img
     *
     * @return
     */
    public String img;
    /**
     * 消息id
     */
    private Integer id;
    /**
     * 发送平台
     */
    private String platform;
    /**
     * 发送内容
     */
    private String content;
    /**
     * 发送时间
     */
    private Date createTime;
    /**
     * 发送状态
     */
    private Integer sendStatus;
    private String title;

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}