package com.wenjuan.dao;

import com.wenjuan.model.Article;
import com.wenjuan.model.ArticleComment;
import com.wenjuan.vo.ArticleCommentView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleComment record);

    int updateByPrimaryKey(ArticleComment record);

    List<ArticleCommentView> selectByArticleId2(int id);

    List<ArticleCommentView> selectByArticleId3(Map map);

    ArticleCommentView selectByPrimaryKey(Integer id);

    List<ArticleCommentView> selectByArticleId(@Param("id") Integer id, @Param("topLevel") boolean topLevel);

    List<ArticleCommentView> selectNewComment(Integer userId);

    /**
     * 更新用户阅读时间
     *
     * @param id
     * @return
     */
    int updateCommentViewTime(Integer id);

    List<ArticleCommentView> selectCommentAfter(@Param("articleId") Integer articleId, @Param("lastCommentId") Integer lastCommentId);

    List<ArticleComment> getCommentFY(Map map);

    int articleCommentCount();

    int UserComment(int id);

    //本月
    int getUserComment(int id);

    //上月
    int getUserComment2(int id);

    List<ArticleCommentView> selectTopComment(@Param("articleId") Integer articleId, @Param("count") int count);

    /**
     * 获取某评论下的评论
     *
     * @param commentId
     * @return
     */
    List<ArticleCommentView> selectCommentUnderComment(Integer commentId);

    List<ArticleCommentView> selectCommentBaseComment(Integer commentId);

    /**
     * 我评论的
     *
     * @param config
     * @return
     */
    List<Article> selectMySendMsg(Map config);

    List<Article> selectMyReceiveMsg(Map config);


    Integer selectMyReceiveMsgCount(Map config);

    List<ArticleCommentView> selectLastNewCommentBaseUser(Map<String, Object> config);

    //帖子回复数据
    List<ArticleComment> CommentData(Map map);

    int CommentDataCount();

    //用户对一条帖子的所有评论
    List<ArticleComment> commentLengthList(Map map);

    //查询字数
    int commentLength(String id);

    //查询评论数
    int comment(Map map);

    //最近一个月
    List<ArticleComment> commentMM(int uid);

    //回帖数据最终
    List<ArticleComment> CommentListFY(Map map);
    int CommentListFYCount(int uid);
    void updateComment(Map map);

    int getCountNum(Map map);

    //根据type所有评论
    List<ArticleComment> getAllCommentListByType(Map map);
    int getAllCommentListByTypeCount(Map map);
}