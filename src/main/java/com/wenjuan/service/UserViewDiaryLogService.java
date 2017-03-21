package com.wenjuan.service;

import com.wenjuan.model.UserViewDiaryLog;

public interface UserViewDiaryLogService {

    int deleteByPrimaryKey(Integer diaryId, Integer userId);

    int insert(UserViewDiaryLog record);

    UserViewDiaryLog selectByPrimaryKey(Integer diaryId, Integer userId);
}
