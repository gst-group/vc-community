package com.wenjuan.dao;

import com.wenjuan.model.ScoreHistory;

import java.util.List;
import java.util.Map;

public interface ScoreHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScoreHistory record);

    ScoreHistory selectByPrimaryKey(Integer id);

    List<ScoreHistory> selectScoreHistories();

    /**
     * @param id 用户id
     * @return
     */
    List<ScoreHistory> selectMyScoreHistories(Map<String, Object> config);

    String getScoreInfo();

}