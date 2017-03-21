package com.wenjuan.service;

import com.wenjuan.model.UserFollowArticle;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Gao Xun on 2016/6/15.
 */
public interface UserFollowArticleService {

    int deleteByPrimaryKey(@Param("userid") Integer userid, @Param("articleid") Integer articleid);

    int insert(UserFollowArticle record);

    UserFollowArticle selectByPrimaryKey(@Param("userid") Integer userid, @Param("articleid") Integer articleid);

    int selectFollowCount(String loadTime, int userId);
}
