package com.wenjuan.service;

import com.wenjuan.model.UserViewArticleLog;

public interface UserViewArticleLogService {
    int deleteByPrimaryKey(Integer id);

    int insert(UserViewArticleLog record);

    int updateByPrimaryKey(UserViewArticleLog record);

    int setUnreadMessageRead(UserViewArticleLog record);

    UserViewArticleLog selectByPrimaryKey(Integer id);

    UserViewArticleLog selectTimeEndNullByArticleUser(int articleId, int userId);

    int ViewCount(int id);

    //查询本月
    int getViewCount(int id);

    //查询上月
    int getViewCount2(int id);
}
