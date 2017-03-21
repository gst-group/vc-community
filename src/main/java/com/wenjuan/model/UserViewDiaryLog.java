package com.wenjuan.model;

import java.io.Serializable;
import java.util.Date;

public class UserViewDiaryLog implements Serializable {
    private Integer diaryId;

    private Integer userId;

    private Date time;

    public Integer getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}