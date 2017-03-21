package com.wenjuan.dao;

import com.wenjuan.model.UserGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserGroupMapper {
    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("groupId") Integer groupId);

    int insert(UserGroup record);

    int deleteUserGroup(Integer userId);

    int insertUsersIntoGroup(@Param("groupId") int grpId, @Param("ids") List<Integer> users);

    UserGroup selectByPrimaryKey(@Param("userId") Integer userId, @Param("groupId") Integer groupId);

    int updateByPrimaryKey(UserGroup record);

    List<Map> selectArticleGroupMsgCount(int userId);

    void del(int gid);

    int updateUserReadArticleIdIfGreater(UserGroup record);

    int addUserToAllGroup(Map map);
}