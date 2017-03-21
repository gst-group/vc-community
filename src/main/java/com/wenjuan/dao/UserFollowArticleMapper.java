package com.wenjuan.dao;

import com.wenjuan.model.UserFollowArticle;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserFollowArticleMapper {
    int deleteByPrimaryKey(@Param("userid") Integer userid, @Param("articleid") Integer articleid);

    int insert(UserFollowArticle record);

    UserFollowArticle selectByPrimaryKey(@Param("userid") Integer userid, @Param("articleid") Integer articleid);

    int selectFollowCount(Map<String, Object> map);

    int FollowArticle(Map map);
}