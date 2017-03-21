package com.wenjuan.service.impl;

import com.wenjuan.dao.UserViewDiaryLogMapper;
import com.wenjuan.model.UserViewDiaryLog;
import com.wenjuan.service.UserViewDiaryLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userViewDiaryLogService")
public class UserViewDiaryLogServiceImpl implements UserViewDiaryLogService {
    @Resource
    private UserViewDiaryLogMapper userViewDiaryLogMapper;

    @Override
    public int deleteByPrimaryKey(Integer diaryId, Integer userId) {
        return userViewDiaryLogMapper.deleteByPrimaryKey(diaryId, userId);
    }

    @Override
    public int insert(UserViewDiaryLog record) {
        return userViewDiaryLogMapper.insert(record);
    }

    @Override
    public UserViewDiaryLog selectByPrimaryKey(Integer diaryId, Integer userId) {
        return userViewDiaryLogMapper.selectByPrimaryKey(diaryId, userId);
    }
}
