package com.wenjuan.dao;

import com.wenjuan.model.UserViewDiaryLog;
import org.apache.ibatis.annotations.Param;

public interface UserViewDiaryLogMapper {
    int deleteByPrimaryKey(@Param("diaryId") Integer diaryId, @Param("userId") Integer userId);

    int insert(UserViewDiaryLog record);

    UserViewDiaryLog selectByPrimaryKey(@Param("diaryId") Integer diaryId, @Param("userId") Integer userId);

    int ViewCount(int id);

    //获取上月
    int getViewCount(int id);

    //获取本月
    int getViewCount2(int id);

}