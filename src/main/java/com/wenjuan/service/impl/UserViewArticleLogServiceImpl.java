package com.wenjuan.service.impl;

import com.wenjuan.dao.UserViewArticleLogMapper;
import com.wenjuan.model.UserViewArticleLog;
import com.wenjuan.service.UserViewArticleLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userViewArticleLogService")
public class UserViewArticleLogServiceImpl implements UserViewArticleLogService {

    @Resource
    UserViewArticleLogMapper userViewArticleLogMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userViewArticleLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserViewArticleLog record) {
        return userViewArticleLogMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKey(UserViewArticleLog record) {
        return userViewArticleLogMapper.updateByPrimaryKey(record);
    }

    @Override
    public int setUnreadMessageRead(UserViewArticleLog record) {
        return userViewArticleLogMapper.setUnreadMessageRead(record);
    }

    @Override
    public UserViewArticleLog selectByPrimaryKey(Integer id) {
        return userViewArticleLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserViewArticleLog selectTimeEndNullByArticleUser(int articleId, int userId) {
        return userViewArticleLogMapper.selectTimeEndNullByArticleUser(articleId, userId);
    }

    @Override
    public int ViewCount(int id) {
        return userViewArticleLogMapper.ViewCount(id);
    }

    @Override
    public int getViewCount(int id) {
        return userViewArticleLogMapper.getViewCount(id);
    }

    @Override
    public int getViewCount2(int id) {
        return userViewArticleLogMapper.getViewCount2(id);
    }
}
