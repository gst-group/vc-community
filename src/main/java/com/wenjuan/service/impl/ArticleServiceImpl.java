package com.wenjuan.service.impl;

import com.wenjuan.dao.ArticleMapper;
import com.wenjuan.dao.UserGroupMapper;
import com.wenjuan.model.Article;
import com.wenjuan.model.User;
import com.wenjuan.model.UserGroup;
import com.wenjuan.service.ArticleService;
import com.wenjuan.utils.ApplicationContextUtil;
import com.wenjuan.utils.CommonUtil;
import com.wenjuan.utils.ConfigUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private UserGroupMapper userGroupMapper;

    public int deleteByPrimaryKey(Integer id) {
        return articleMapper.deleteByPrimaryKey(id);
    }

    public int insert(Article record) {
        return articleMapper.insert(record);
    }

    @Override
    public Article selectByPrimaryKey(Integer id, User user) {
        return articleMapper.selectByPrimaryKey(id, user == null ? null : user.getId());
    }

    /**
     * @param type         0：自己发布的，1：全部可见的，2:登陆用户可见的，3:未登录用户可见的
     * @param user         不验证用户是否存在
     * @param order        time,follow_count,reply_count，author等与desc,asc的组合
     * @param page         页数，0时不分页
     * @param countPerPage 每页项数，null时为默认值
     * @param loadId       字符A或B开头，后面跟上id，分别表示After和Before,例如A0表示id为0之后的文章
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Article> selectArticles(Integer grpId, int type, User user, String order, int page, Integer countPerPage, String loadId,int ogid) {
        countPerPage = countPerPage == null ? ConfigUtil.NUMBER_OF_ARTICLE_ITEM_PER_PAGE : countPerPage;
        HashMap config = new HashMap();
        config.put("order", order);
        config.put("startIndex", page == 0 ? null : (page - 1) * countPerPage);
        config.put("pageLimited", countPerPage);
        config.put("id", CommonUtil.splitInteger(loadId));
        config.put("comparator", CommonUtil.splitComparator(loadId));
        config.put("userId", user == null ? null : user.getId());
        config.put("grpId", grpId);
        config.put("uid",user.getId());
        config.put("ogid",ogid);
        List<Article> articles = null;
        switch (type) {
            case 0:
                articles = articleMapper.selectMyArticles(config);
                break;
            case 1:
                articles = articleMapper.selectArticles(config);
                break;
            case 2:
                articles = articleMapper.selectVisibleArticleByUser(config);
                break;
            case 3:
                articles = articleMapper.selectVisibleArticle(config);
                break;
        }
        /*
        if (articles != null) {
            for (Article article : articles) {
                article.setComments(articleCommentMapper.selectTopComment(article.getId(), 3));
                article.setLikeUsers(userMapper.selectFollowUserByArticleId(article.getId()));
            }
        }*/
        if (user != null && articles != null && articles.size() > 0) {
            int lastId = articles.get(0).getId();
            if (grpId != null) {
                UserGroup userGroup = new UserGroup();
                userGroup.setGroupId(grpId);
                userGroup.setUserId(user.getId());
                userGroup.setLastQueryArticleId(lastId);
                userGroupMapper.updateUserReadArticleIdIfGreater(userGroup);
            } else if (user.getQueryLastArticleId() < lastId) {
                User newUserInfo = new User();
                newUserInfo.setId(user.getId());
                newUserInfo.setQueryLastArticleId(lastId);
                ApplicationContextUtil.getUserService().updateByPrimaryKey(newUserInfo);
            }
        }
        return articles;
    }

    @Override
    public int selectArticlesCount(Integer grpId, int type, User user, String loadId, boolean containMine,int ogid) {
        HashMap<String, Object> config = new HashMap<>();
        config.put("id", CommonUtil.splitInteger(loadId));
        config.put("comparator", CommonUtil.splitComparator(loadId));
        config.put("userId", user == null ? null : user.getId());
        config.put("grpId", grpId);
        config.put("containMine", containMine);
        config.put("ogid",ogid);
        switch (type) {
            case 0:
                return articleMapper.selectMyArticlesCount(config);
            case 1:
                return articleMapper.selectArticlesCount(config);
            case 2:
                return articleMapper.selectVisibleArticleByUserCount(config);
            case 3:
                return articleMapper.selectVisibleArticleCount(config);
            default:
                return 0;
        }
    }

    @Override
    public List<Map> selectArticlesCountByGroup(String loadId) {
        HashMap<String, Object> config = new HashMap<>();
        config.put("id", CommonUtil.splitInteger(loadId));
        config.put("comparator", CommonUtil.splitComparator(loadId));
        return articleMapper.selectArticlesCountByGroup(config);
    }

    public Article[] getFavorites(Integer id, String order, String loadId,int ogid) {
        Map<String, Object> config = new HashMap<>();
        config.put("id", id);
        config.put("order", order);
        config.put("ogid",ogid);
        config.put("comparator", CommonUtil.splitComparator(loadId));
        config.put("articleid", CommonUtil.splitInteger(loadId));
        return articleMapper.getFavorites(config);
    }

    @Override
    public List<Article> articleSearch(String content, String order) {
        //TODO
        return articleMapper.articleSearch(content, order);
    }

    public int updateByPrimaryKey(Article record) {
        return articleMapper.updateByPrimaryKey(record);
    }

    @Override
    public int toggleVisible(Integer id) {
        return articleMapper.toggleVisible(id);
    }

    @Override
    public int toggleTop(int id) {
        return articleMapper.toggleTop(id);
    }

    @Override
    public List<Article> all() {
        // TODO Auto-generated method stub
        return articleMapper.all();
    }

    @Override
    public int topCount(String gid) {
        return articleMapper.topCount(gid);
    }

    @Override
    public List<Article> getPushList(Map map) {
        return articleMapper.getPushList(map);
    }

    @Override
    public List<User> getLikeArticleList(int aid) {
        return articleMapper.getLikeArticleList(aid);
    }


}
