package com.wenjuan.dao;

import com.wenjuan.model.Help;

import java.util.List;
import java.util.Map;

public interface HelpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Help record);

    Help selectByPrimaryKey(Integer id);

    List<Help> selectAllHelps();

    int updateByPrimaryKey(Help record);

    int toggleVisible(int id);

    List<Help> helpListFy(Map map);

    int helpCount();
}