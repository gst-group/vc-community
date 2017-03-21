package com.wenjuan.dao;

import com.wenjuan.model.History;

import java.util.List;
import java.util.Map;

public interface HistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(History record);

    History selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(History record);

    History max();

    List<History> getAll(Map map);

    int Count();

    //获取群组及所有聊天信息
    List<History> getGroup(String groupid);

    List<History> getMessage();
}