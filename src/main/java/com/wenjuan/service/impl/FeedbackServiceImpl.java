package com.wenjuan.service.impl;

import com.wenjuan.dao.FeedbackMapper;
import com.wenjuan.model.Feedback;
import com.wenjuan.model.User;
import com.wenjuan.service.FeedbackService;
import com.wenjuan.utils.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    FeedbackMapper feedbackMapper;

    public int deleteByPrimaryKey(Integer id) {
        return feedbackMapper.deleteByPrimaryKey(id);
    }

    public int insert(Feedback record) {
        return feedbackMapper.insert(record);
    }

    public Feedback selectByPrimaryKey(Integer id) {
        return feedbackMapper.selectByPrimaryKey(id);
    }

    public List<Feedback> selectMyFeedbacks(Integer id) {
        return feedbackMapper.selectMyFeedbacks(id);
    }

    public List<Feedback> selectFeedbacks(Integer visible) {
        return feedbackMapper.selectFeedbacks(visible);
    }

    @Override
    public List<Feedback> selectNewReplyFeedback(String loadFeedbackReplayTime, User user) {
        if (user == null) return new ArrayList<>();
        return feedbackMapper.selectNewReplyFeedback(getParameterMap(loadFeedbackReplayTime, user));
    }

    @Override
    public int selectNewReplyFeedbackCount(User user) {
        if (user == null) return 0;
        return feedbackMapper.selectNewReplyFeedbackCount(user.getId());
    }

    public List<Feedback> selectUnrepliedFeedbacks() {
        return feedbackMapper.selectUnrepliedFeedbacks();
    }

    @Override
    public int toggleVisible(Integer id) {
        return feedbackMapper.toggleVisible(id);
    }

    public int updateByPrimaryKey(Feedback record) {
        return feedbackMapper.updateByPrimaryKey(record);
    }

    private Map<String, Object> getParameterMap(String loadFeedbackReplayTime, User user) {
        Map<String, Object> config = new HashMap<>();
        config.put("time", CommonUtil.splitString(loadFeedbackReplayTime));
        config.put("comparator", CommonUtil.splitComparator(loadFeedbackReplayTime));
        config.put("userId", user.getId());
        return config;
    }
}
