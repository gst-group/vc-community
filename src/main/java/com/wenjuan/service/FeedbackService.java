package com.wenjuan.service;

import com.wenjuan.model.Feedback;
import com.wenjuan.model.User;

import java.util.List;

/**
 * Created by Gao Xun on 2016/6/2.
 */
public interface FeedbackService {


    int deleteByPrimaryKey(Integer id);

    int insert(Feedback record);

    Feedback selectByPrimaryKey(Integer id);

    List<Feedback> selectMyFeedbacks(Integer id);

    /**
     * 是否选择可见的
     *
     * @param visible null为全部，0为可见，1为不可见
     * @return
     */
    List<Feedback> selectFeedbacks(Integer visible);

    /**
     * @param id 用户id
     * @return
     */
    List<Feedback> selectNewReplyFeedback(String loadFeedbackReplayTime, User user);

    int selectNewReplyFeedbackCount(User user);

    List<Feedback> selectUnrepliedFeedbacks();

    int toggleVisible(Integer id);

    int updateByPrimaryKey(Feedback record);
}
