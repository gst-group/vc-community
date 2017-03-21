package com.wenjuan.model;

import java.io.Serializable;
import java.util.Date;

public class GoodDuihuan implements Serializable {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 兑换的商品id
     */
    private Integer goodId;

    /**
     * 兑换时间
     */
    private Date time = new Date(System.currentTimeMillis());

    /**
     * 是否已处理，0为未处理，1为已处理
     */
    private Byte handled;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Byte getHandled() {
        return handled;
    }

    public void setHandled(Byte handled) {
        this.handled = handled;
    }
}