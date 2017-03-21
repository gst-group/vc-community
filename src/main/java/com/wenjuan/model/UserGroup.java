package com.wenjuan.model;

import java.util.Date;

public class UserGroup {
    private Integer userId;

    private Integer groupId;

    private Date time;

    private Integer lastQueryArticleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getLastQueryArticleId() {
        return lastQueryArticleId;
    }

    public void setLastQueryArticleId(Integer lastQueryArticleId) {
        this.lastQueryArticleId = lastQueryArticleId;
    }
}