package com.wenjuan.service.impl;

import com.wenjuan.dao.ChatHistoryMapper;
import com.wenjuan.model.ChatHistory;
import com.wenjuan.service.ChatHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("chatHistoryService")
public class ChatHistoryServiceImpl implements ChatHistoryService {

    @Resource
    ChatHistoryMapper chatHistoryMapper;

    public int insert(ChatHistory record) {
        return chatHistoryMapper.insert(record);
    }

    public ChatHistory selectByPrimaryKey(Integer id) {
        return chatHistoryMapper.selectByPrimaryKey(id);
    }

    public List<ChatHistory> selectHistories() {
        return chatHistoryMapper.selectHistories();
    }

    public int updateByPrimaryKey(ChatHistory record) {
        return chatHistoryMapper.updateByPrimaryKey(record);
    }
}
