package com.wenjuan.dao;

import com.wenjuan.model.Feedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Feedback record);

    Feedback selectByPrimaryKey(Integer id);

    /**
     * 我的反馈
     *
     * @param id
     * @return
     */
    List<Feedback> selectMyFeedbacks(Integer id);

    /**
     * 所有反馈
     *
     * @param status null为所有，0为正常，1为隐藏
     * @return
     */
    List<Feedback> selectFeedbacks(@Param("status") Integer status);

    /**
     * @param id 用户id
     * @return
     */
    List<Feedback> selectNewReplyFeedback(Map<String, Object> config);

    int selectNewReplyFeedbackCount(int userId);

    /**
     * 未回复的反馈
     *
     * @return
     */
    List<Feedback> selectUnrepliedFeedbacks();

    int updateByPrimaryKey(Feedback record);

    int toggleVisible(Integer id);

    int feedBackCount();

    List<Feedback> feedBackListFy(Map map);

    int UserfeedBackCount(int id);

    //查询本月
    int getFeedBackCount(int id);

    //查询上月
    int getFeedBackCount2(int id);

    int feedbackSeachCount(Map map);

    List<Feedback> feedbackSeach(Map map);

    List<Feedback> FeedBackMM(int uid);
}