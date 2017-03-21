package com.wenjuan.dao;

import com.wenjuan.model.ChatHistory;

import java.util.List;

public interface ChatHistoryMapper {
    int insert(ChatHistory record);

    ChatHistory selectByPrimaryKey(Integer id);

    List<ChatHistory> selectHistories();

    int updateByPrimaryKey(ChatHistory record);
}