package com.wenjuan.service;

import com.wenjuan.model.User;
import com.wenjuan.model.UserGroup;

import java.util.List;
import java.util.Map;

public interface UserGroupService {

    int deleteByPrimaryKey(Integer userId, Integer groupId);

    int insert(UserGroup record);

    int deleteUserGroup(Integer userId);

    int insertUsersIntoGroup(int grpId, List<Integer> users);

    UserGroup selectByPrimaryKey(Integer userId, Integer groupId);

    boolean isUserInGroup(Integer userId, Integer groupId);

    int updateByPrimaryKey(UserGroup record);

    List<Map> selectArticleGroupMsgCount(User user);
    
    void del(int gid);

    int updateUserReadArticleIdIfGreater(UserGroup record);

    int addUserToAllGroup(Map map);
}
