package com.wenjuan.service;

import com.wenjuan.model.ScoreHistory;
import com.wenjuan.model.User;

import java.util.List;

public interface ScoreHistoryService {
    int deleteByPrimaryKey(Integer id);

    int insert(ScoreHistory record);

    ScoreHistory selectByPrimaryKey(Integer id);

    List<ScoreHistory> selectScoreHistories();

    /**
     * @param id    用户id
     * @param order
     * @return
     */
    List<ScoreHistory> selectMyScoreHistories(String order, String loadTime, int page, User user);

    String getScoreInfo();
}
