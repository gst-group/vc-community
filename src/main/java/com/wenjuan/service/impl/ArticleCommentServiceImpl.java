package com.wenjuan.service.impl;

import com.wenjuan.dao.ArticleCommentMapper;
import com.wenjuan.model.Article;
import com.wenjuan.model.ArticleComment;
import com.wenjuan.model.User;
import com.wenjuan.service.ArticleCommentService;
import com.wenjuan.utils.ApplicationContextUtil;
import com.wenjuan.utils.CommonUtil;
import com.wenjuan.utils.ConfigUtil;
import com.wenjuan.vo.ArticleCommentView;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("articleCommentService")
public class ArticleCommentServiceImpl implements ArticleCommentService {
    @Resource
    ArticleCommentMapper articleCommentMapper;

    public int deleteByPrimaryKey(Integer id) {
        return articleCommentMapper.deleteByPrimaryKey(id);
    }

    public int insert(ArticleComment record) {
        return articleCommentMapper.insert(record);
    }

    public ArticleCommentView selectByPrimaryKey(Integer id) {
        return articleCommentMapper.selectByPrimaryKey(id);
    }

    public List<ArticleCommentView> selectByArticleId(Integer id, boolean topLevel) {
        return articleCommentMapper.selectByArticleId(id, topLevel);
    }

    public List<ArticleCommentView> selectNewComment(Integer userId) {
        return articleCommentMapper.selectNewComment(userId);
    }

    public int updateCommentViewTime(int userId) {
        return articleCommentMapper.updateCommentViewTime(userId);
    }

    public List<ArticleCommentView> selectCommentAfter(Integer articleId, int lastCommentId) {
        return articleCommentMapper.selectCommentAfter(articleId, lastCommentId);
    }

    public List<ArticleCommentView> selectCommentUnderComment(Integer commentId) {
        return articleCommentMapper.selectCommentUnderComment(commentId);
    }

    public List<Article> selectCommentMsg(boolean isReceive, Integer grpId, String loadTime, int page, User user, String order,int ogid) {
        int countPerPage = ConfigUtil.NUMBER_OF_ARTICLE_ITEM_PER_PAGE;
        Map<String, Object> config = getParameterConfig(loadTime, user);
        config.put("order", order);
        config.put("startIndex", page == 0 ? null : (page - 1) * countPerPage);
        config.put("pageLimited", countPerPage);
        config.put("grpId", grpId);
        config.put("uid",user.getId());
        config.put("ogid",ogid);
        List<Article> articles;
        if (isReceive) {
            articles = articleCommentMapper.selectMyReceiveMsg(config);
            for (Article article : articles) {
                if (article.getExtra() != null) {
                    article.setComments(articleCommentMapper.selectCommentBaseComment(Integer.parseInt(article.getExtra())));
                } else if (article.getExtra() == null && article.getAuthor().equals(user.getId())) {
                    article.setComments(articleCommentMapper.selectByArticleId(article.getId(), false));
                }
            }
            User newUserInfo = new User();
            newUserInfo.setId(user.getId());
            newUserInfo.setQueryLastArticleComment(new Date());
            ApplicationContextUtil.getUserService().updateByPrimaryKey(newUserInfo);
        } else {
            articles = articleCommentMapper.selectMySendMsg(config);
            config = getParameterConfig(loadTime, user);
            config.put("commentBase", 0);
            config.put("isReceive", isReceive);
            for (Article article : articles) {
                config.put("commentBase", article.getExtra());
                article.setComments(articleCommentMapper.selectLastNewCommentBaseUser(config));
            }
        }
        return articles;
    }

    @Override
    public List<ArticleCommentView> selectByArticleId2(int id) {
        // TODO Auto-generated method stub
        return articleCommentMapper.selectByArticleId2(id);
    }

    @Override
    public int selectMyReceiveMsgCount(String loadTime, User user) {
        Integer num = articleCommentMapper.selectMyReceiveMsgCount(getParameterConfig(loadTime, user));
        return num == null ? 0 : num;
    }

    private Map<String, Object> getParameterConfig(String loadTime, User user) {
        Map<String, Object> config = new HashMap<>();
        config.put("userId", user == null ? null : user.getId());
        config.put("comparator", CommonUtil.splitComparator(loadTime));
        config.put("time", StringUtils.isEmpty(loadTime) ? null : loadTime.substring(1));
        return config;
    }
    public void updateComment(Map map){
        articleCommentMapper.updateComment(map);
    }


    public int getCountNum(Map map){
        return articleCommentMapper.getCountNum(map);
    };


}
