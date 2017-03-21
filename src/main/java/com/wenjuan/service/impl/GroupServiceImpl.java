package com.wenjuan.service.impl;

import com.wenjuan.dao.GroupMapper;
import com.wenjuan.model.Group;
import com.wenjuan.model.User;
import com.wenjuan.service.GroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("groupService")
public class GroupServiceImpl implements GroupService {
  @Resource
  GroupMapper groupMapper;

  @Override
  public int deleteByPrimaryKey(Integer id) {
    return groupMapper.deleteByPrimaryKey(id);
  }

  @Override
  public int insert(Group record) {
    return groupMapper.insert(record);
  }

  @Override
  public Group selectByPrimaryKey(Integer id) {
    return groupMapper.selectByPrimaryKey(id);
  }

  @Override
  public Group selectByName(String grpName) {
    return groupMapper.selectByName(grpName);
  }

  @Override
  public int updateByPrimaryKey(Group record) {
    return groupMapper.updateByPrimaryKey(record);
  }

  @Override
  public List<String> getExistsGroup() {
    return groupMapper.getExistsGroup();
  }

  @Override
  public int deleteGroup(String grp) {
    return groupMapper.deleteGroup(grp);
  }

  @Override
  public int isGroupExists(String grp) {
    return groupMapper.isGroupExists(grp);
  }

  @Override
  public List<Group> getExistsGroupInfo(boolean containUngroup) {
    User userAdmin =User.getCurrentUser();
    List<Group> existsGroupInfo = groupMapper.getExistsGroupInfo(userAdmin.getId());
    if (containUngroup && existsGroupInfo != null) {
      existsGroupInfo.add(0, Group.getUngroup());
    }

    return existsGroupInfo;
  }

  @Override
  public Group[] getMyGroup(int userId) {
    return groupMapper.getMyGroup(userId);
  }

  @Override
  public Group[] selectForegroundGroup(String ogid) {
    return groupMapper.selectForegroundGroup(ogid);
  }

  @Override
  public int selectForegroundGroupCount(String ogid) {
    Integer count = groupMapper.selectForegroundGroupCount(ogid);
    return count == null ? 0 : count;
  }
}
