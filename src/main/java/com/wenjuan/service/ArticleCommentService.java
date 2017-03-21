package com.wenjuan.service;

import com.wenjuan.model.Article;
import com.wenjuan.model.ArticleComment;
import com.wenjuan.model.User;
import com.wenjuan.vo.ArticleCommentView;

import java.util.List;
import java.util.Map;

/**
 * Created by Gao Xun on 2016/6/3.
 */
public interface ArticleCommentService {

    int deleteByPrimaryKey(Integer id);

    int insert(ArticleComment record);

    ArticleCommentView selectByPrimaryKey(Integer id);

    List<ArticleCommentView> selectByArticleId(Integer id, boolean topLevel);

    List<ArticleCommentView> selectNewComment(Integer userId);

    int updateCommentViewTime(int userId);

    /**
     * 获取指定话题id的指定时间之后的评论
     *
     * @param articleId
     * @param lastCommentId
     * @return
     */
    List<ArticleCommentView> selectCommentAfter(Integer articleId, int lastCommentId);

    /**
     * 获取某评论下的评论
     *
     * @param commentId
     * @return
     */
    List<ArticleCommentView> selectCommentUnderComment(Integer commentId);

    List<Article> selectCommentMsg(boolean isReceive, Integer grpId, String loadTime, int page, User user, String order,int ogid);

    List<ArticleCommentView> selectByArticleId2(int id);


    int selectMyReceiveMsgCount(String loadTime, User user);

    //修改评论type为1
    void updateComment(Map map);

    int getCountNum(Map map);

}
