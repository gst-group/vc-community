package com.wenjuan.dao;

import com.wenjuan.model.ScoreConfig;

import java.util.List;
import java.util.Map;

public interface ScoreConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScoreConfig record);

    ScoreConfig selectByPrimaryKey(Integer id);

    List<ScoreConfig> selectAllConfig();

    int updateByPrimaryKey(ScoreConfig record);

    Map selectCotent();

    void updateContent(Map map);
}