package com.wenjuan.service.impl;

import com.wenjuan.dao.UserGroupMapper;
import com.wenjuan.model.User;
import com.wenjuan.model.UserGroup;
import com.wenjuan.service.UserGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userGroupService")
public class UserGroupServiceImpl implements UserGroupService {
    @Resource
    UserGroupMapper userGroupMapper;

    @Override
    public int deleteByPrimaryKey(Integer userId, Integer groupId) {
        return userGroupMapper.deleteByPrimaryKey(userId, groupId);
    }

    @Override
    public int insert(UserGroup record) {
        return userGroupMapper.insert(record);
    }

    @Override
    public int deleteUserGroup(Integer userId) {
        return userGroupMapper.deleteUserGroup(userId);
    }

    @Override
    public int insertUsersIntoGroup(int grpId, List<Integer> users) {
        if (users == null || users.size() == 0) {
            return 0;
        }
        return userGroupMapper.insertUsersIntoGroup(grpId, users);
    }

    @Override
    public UserGroup selectByPrimaryKey(Integer userId, Integer groupId) {
        return userGroupMapper.selectByPrimaryKey(userId, groupId);
    }

    @Override
    public boolean isUserInGroup(Integer userId, Integer groupId) {
        return selectByPrimaryKey(userId, groupId) == null;
    }

    @Override
    public int updateByPrimaryKey(UserGroup record) {
        return userGroupMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Map> selectArticleGroupMsgCount(User user) {
        if (user == null) {
            return null;
        }
        return userGroupMapper.selectArticleGroupMsgCount(user.getId());
    }

    @Override
    public void del(int gid) {
        // TODO Auto-generated method stub
        userGroupMapper.del(gid);
    }

    @Override
    public int updateUserReadArticleIdIfGreater(UserGroup record) {
        return userGroupMapper.updateUserReadArticleIdIfGreater(record);
    }

    @Override
    public int addUserToAllGroup(Map map) {

        return userGroupMapper.addUserToAllGroup(map);
    }
}
