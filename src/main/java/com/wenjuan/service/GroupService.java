package com.wenjuan.service;

import com.wenjuan.model.Group;

import java.util.List;

public interface GroupService {
  int deleteByPrimaryKey(Integer id);

  int insert(Group record);

  Group selectByPrimaryKey(Integer id);

  Group selectByName(String grpName);

  int updateByPrimaryKey(Group record);

  List<String> getExistsGroup();

  int deleteGroup(String grp);

  int isGroupExists(String grp);

  List<Group> getExistsGroupInfo(boolean containUngroup);

  Group[] getMyGroup(int userId);

  Group[] selectForegroundGroup(String ogid);

  int selectForegroundGroupCount(String ogid);
}
