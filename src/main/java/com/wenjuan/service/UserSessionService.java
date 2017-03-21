package com.wenjuan.service;

import com.wenjuan.model.UserSession;

public interface UserSessionService {
    int insert(UserSession record);

    int deleteByUserId(Integer id);

    UserSession selectByPrimaryKey(Integer userId);
}
