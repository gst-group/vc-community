package com.wenjuan.service.impl;

import com.wenjuan.dao.UserFollowArticleMapper;
import com.wenjuan.model.UserFollowArticle;
import com.wenjuan.service.UserFollowArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.wenjuan.service.impl.UserFollowDiaryServiceImpl.getParameterMap;

@Service("userFollowArticleService")
public class UserFollowArticleServiceImpl implements UserFollowArticleService {

    @Resource
    UserFollowArticleMapper userFollowArticleMapper;

    @Override
    public int deleteByPrimaryKey(Integer userid, Integer articleid) {
        return userFollowArticleMapper.deleteByPrimaryKey(userid, articleid);
    }

    @Override
    public int insert(UserFollowArticle record) {
        return userFollowArticleMapper.insert(record);
    }


    @Override
    public UserFollowArticle selectByPrimaryKey(Integer userid, Integer articleid) {
        return userFollowArticleMapper.selectByPrimaryKey(userid, articleid);
    }

    @Override
    public int selectFollowCount(String loadTime, int userId) {
        return userFollowArticleMapper.selectFollowCount(getParameterMap(loadTime, userId));
    }
}
