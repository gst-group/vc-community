package com.wenjuan.dao;

import com.wenjuan.model.Group;

import java.util.List;
import java.util.Map;

public interface GroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Group record);

    Group selectByPrimaryKey(Integer id);

    Group selectByName(String grpName);

    int updateByPrimaryKey(Group record);

    List<String> getExistsGroup();

    int deleteGroup(String grp);

    int isGroupExists(String grp);

    List<Group> getExistsGroupInfo(int aid);

    Group[] getMyGroup(int userId);

    Group[] selectForegroundGroup(String ogid);

    Integer selectForegroundGroupCount(String ogid);
}