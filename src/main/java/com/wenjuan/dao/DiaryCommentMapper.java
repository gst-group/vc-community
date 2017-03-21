package com.wenjuan.dao;

import com.wenjuan.model.Diary;
import com.wenjuan.model.DiaryComment;
import com.wenjuan.vo.DiaryCommentView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DiaryCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiaryComment record);

    int updateByPrimaryKey(DiaryComment record);

    DiaryCommentView selectByPrimaryKey(Integer id);

    List<DiaryCommentView> selectByDiaryId(@Param("id") Integer id, @Param("topLevel") boolean topLevel);

    List<DiaryCommentView> getCommentFY(Map map);

    List<DiaryCommentView> selectNewComment(Integer userId);

    /**
     * 更新用户阅读时间
     *
     * @param id
     * @return
     */
    int updateCommentViewTime(Integer id);

    List<DiaryCommentView> selectCommentAfter(@Param("diaryId") Integer diaryId, @Param("lastCommentId") int lastCommentId);

    int diaryCommentCount();

    int Usercomment(int id);

    //本月
    int getUsercomment(int id);

    //上月
    int getUsercomment2(int id);

    List<DiaryCommentView> selectTopComment(@Param("diaryId") Integer diaryId, @Param("count") int count);

    /**
     * 获取某评论下的评论
     *
     * @param commentId
     * @return
     */
    List<DiaryCommentView> selectCommentUnderComment(Integer commentId);

    List<DiaryCommentView> selectCommentBaseComment(Integer commentId);

    /**
     * 我评论的
     *
     * @param config
     * @return
     */
    List<Diary> selectMySendMsg(Map config);

    List<Diary> selectMyReceiveMsg(Map config);

    Integer selectMyReceiveMsgCount(Map config);

    List<DiaryCommentView> selectLastNewCommentBaseUser(Map<String, Object> config);
}