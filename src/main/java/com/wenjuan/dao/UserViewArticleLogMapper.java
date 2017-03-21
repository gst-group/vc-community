package com.wenjuan.dao;

import com.wenjuan.model.UserViewArticleLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserViewArticleLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserViewArticleLog record);

    int updateByPrimaryKey(UserViewArticleLog record);

    UserViewArticleLog selectByPrimaryKey(Integer id);

    UserViewArticleLog selectTimeEndNullByArticleUser(@Param("aid") int articleId, @Param("uid") int userId);

    int setUnreadMessageRead(UserViewArticleLog record);

    int ViewCount(int id);

    //查询本月
    int getViewCount(int id);

    //查询上月
    int getViewCount2(int id);

    //帖子数据
    List<UserViewArticleLog> getViewData(Map map);

    int getViewDataCount();

    //浏览时长
    int GetViewTime(String id);


    List<UserViewArticleLog> ViewBeginEnd(int uid);

    //最近一月
    int ViewMM(int uid);

    UserViewArticleLog VBE(Map map);
}