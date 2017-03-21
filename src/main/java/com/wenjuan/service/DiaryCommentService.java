package com.wenjuan.service;

import com.wenjuan.model.Diary;
import com.wenjuan.model.DiaryComment;
import com.wenjuan.model.User;
import com.wenjuan.vo.DiaryCommentView;

import java.util.List;

/**
 * Created by Gao Xun on 2016/6/2.
 */
public interface DiaryCommentService {

    int deleteByPrimaryKey(Integer id);

    int insert(DiaryComment record);

    DiaryCommentView selectByPrimaryKey(Integer id);

    List<DiaryCommentView> selectByDiaryId(Integer id, boolean topLevel);

    /**
     * @param userId
     * @return
     */
    List<DiaryCommentView> selectNewComment(Integer userId);

    int updateCommentViewTime(int userId);

    List<DiaryCommentView> selectCommentAfter(Integer articleId, int lastCommentId);

    /**
     * 获取某评论下的评论
     *
     * @param commentId
     * @return
     */
    List<DiaryCommentView> selectCommentUnderComment(Integer commentId);

    List<Diary> selectCommentMsg(boolean isReceive, String loadTime, int page, User user, String order,int ogid);

    int selectMyReceiveMsgCount(String loadTime, User user);
}
