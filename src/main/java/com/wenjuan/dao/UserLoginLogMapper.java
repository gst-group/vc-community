package com.wenjuan.dao;

import com.wenjuan.model.UserLoginLog;

import java.util.List;

public interface UserLoginLogMapper {
    int insert(UserLoginLog record);

    UserLoginLog selectLastLoginLog(Integer userid);

    int updateByPrimaryKey(UserLoginLog record);

    int selectCountLogin(int id);

    //本月
    int getLoginCount(int id);

    //上月
    int getLoginCount2(int id);

    List<UserLoginLog> UserLoginInfo(int uid);

}