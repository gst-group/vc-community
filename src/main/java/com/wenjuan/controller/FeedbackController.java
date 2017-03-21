package com.wenjuan.controller;

import com.wenjuan.bean.MessageBean;
import com.wenjuan.model.Feedback;
import com.wenjuan.model.User;
import com.wenjuan.service.FeedbackService;
import com.wenjuan.utils.ScoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/feedback")
public class FeedbackController {

    @Autowired
    @Qualifier("feedbackService")
    private FeedbackService feedbackService;

    /**
     * 添加反馈
     *
     * @param content 反馈内容
     * @return
     */
    @RequestMapping(value = "/add")
    public MessageBean add(@RequestParam String content) {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        Feedback feedback = new Feedback();
        feedback.setContent(content);
        feedback.setUser(user.getId());
        feedbackService.insert(feedback);
        ScoreUtil.addScore(user.getId(), ScoreUtil.SUBMIT_FEEDBACK);
        return MessageBean.SUCCESS;
    }

    /**
     * 获取我的所有反馈内容
     *
     * @return extraList中的Feedback数组
     */
    @RequestMapping(value = "/getMyAll")
    public MessageBean getMyAll() {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtraList(feedbackService.selectMyFeedbacks(user.getId()).toArray());
        return messageBean;
    }

    /**
     * 获取所有可见反馈内容
     *
     * @return extraList中的Feedback数组
     */
    @RequestMapping(value = "/getAll")
    public MessageBean getAll() {
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtraList(feedbackService.selectFeedbacks(0).toArray());
        return messageBean;
    }

    @RequestMapping(value = "/setRead")
    public MessageBean setRead(@RequestParam int id) {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        Feedback feedback = feedbackService.selectByPrimaryKey(id);
        if (feedback == null) {
            return MessageBean.FEEDBACK_NOT_EXIST;
        }
        if (!user.getId().equals(feedback.getUser())) {
            return MessageBean.INVALID_AUTHOR;
        }
        feedback = new Feedback();
        feedback.setId(id);
        feedback.setStatus((byte) 1);
        feedbackService.updateByPrimaryKey(feedback);
        return MessageBean.SUCCESS;
    }

    /**
     * 获取我的反馈的回复
     *
     * @param isCount 是否为获取数量
     * @return
     */
    @RequestMapping(value = "/newMsg")
    public MessageBean getNewFeedbackMsg(@RequestParam boolean isCount,
                                         @RequestParam(required = false) String loadFeedbackReplayTime) {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        if (isCount) {
            messageBean.setExtra(feedbackService.selectNewReplyFeedbackCount(user));
        } else {
            messageBean.setExtraList(feedbackService.selectNewReplyFeedback(loadFeedbackReplayTime, user).toArray());
        }
        return messageBean;
    }
}
