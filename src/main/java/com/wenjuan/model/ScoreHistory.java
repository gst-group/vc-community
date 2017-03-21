package com.wenjuan.model;

import java.io.Serializable;
import java.util.Date;

public class ScoreHistory implements Serializable {

    /**
     * 商品兑换历史id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 兑换事件id，详情参考
     * ScoreUtil类
     */
    private Integer eventId;

    /**
     * 兑换时间
     */
    private Date time;

    /**
     * 兑换时的积分
     */
    private Integer score;

    /**
     * 兑换事件名
     */
    private String eventName;

    /**
     * 兑换商品现在所需的积分，可能为-1
     */
    private Integer eventValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getEventValue() {
        return eventValue;
    }

    public void setEventValue(Integer eventValue) {
        this.eventValue = eventValue;
    }
}