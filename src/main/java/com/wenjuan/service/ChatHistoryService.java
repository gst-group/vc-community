package com.wenjuan.service;

import com.wenjuan.model.ChatHistory;

import java.util.List;

/**
 * Created by Gao Xun on 2016/6/2.
 */
public interface ChatHistoryService {

    int insert(ChatHistory record);

    ChatHistory selectByPrimaryKey(Integer id);

    List<ChatHistory> selectHistories();

    int updateByPrimaryKey(ChatHistory record);
}
