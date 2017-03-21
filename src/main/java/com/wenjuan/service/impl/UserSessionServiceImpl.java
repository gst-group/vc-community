package com.wenjuan.service.impl;

import com.wenjuan.dao.UserMapper;
import com.wenjuan.dao.UserSessionMapper;
import com.wenjuan.model.UserSession;
import com.wenjuan.service.UserSessionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userSessionService")
public class UserSessionServiceImpl implements UserSessionService {
    @Resource
    UserSessionMapper userSessionMapper;

    @Resource
    UserMapper userMapper;


    @Override
    public int insert(UserSession record) {
        return userSessionMapper.insert(record);
    }

    @Override
    public int deleteByUserId(Integer id) {
        return userSessionMapper.deleteByUserId(id);
    }


    @Override
    public UserSession selectByPrimaryKey(Integer userId) {
        return userSessionMapper.selectByPrimaryKey(userId);
    }
}
