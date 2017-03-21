package com.wenjuan.utils;

import com.wenjuan.model.User;

/**
 * 记录每次请求的用户
 */
public class UserUtil {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
