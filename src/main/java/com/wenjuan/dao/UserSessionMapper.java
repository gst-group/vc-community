package com.wenjuan.dao;

import com.wenjuan.model.UserSession;

public interface UserSessionMapper {
    int deleteOutOfDate();

    int deleteByUserId(Integer id);

    int insert(UserSession record);

    UserSession selectByPrimaryKey(Integer userId);

    int update(Integer userId);
}