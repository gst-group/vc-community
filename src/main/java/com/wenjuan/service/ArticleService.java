package com.wenjuan.service;


import com.wenjuan.model.Article;
import com.wenjuan.model.User;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    Article selectByPrimaryKey(Integer id, User user);

    /**
     * @param type         0：自己发布的，1：全部可见的，2:登陆用户可见的，3:未登录用户可见的
     * @param user         不验证用户是否存在
     * @param order        time,follow_count,reply_count，author等与desc,asc的组合
     * @param page         页数，0时不分页
     * @param countPerPage 每页项数，null时为默认值
     * @param loadId       字符A或B开头，后面跟上id，分别表示After和Before,例如A0表示id为0之后的文章
     * @return
     */
    List<Article> selectArticles(Integer grpId, int type, User user, String order, int page, Integer countPerPage, String loadId,int ogid);

    int selectArticlesCount(Integer grpId, int type, User user, String loadId, boolean containMine,int ogid);

    List<Map> selectArticlesCountByGroup(String loadId);

    Article[] getFavorites(Integer id, String order, String loadId,int ogid);

    List<Article> articleSearch(String content, String order);

    int updateByPrimaryKey(Article record);

    /**
     * 注意！ 调用该方法之后会将用户设置的一些特殊的话题查看权限清空！！
     *
     * @param id
     * @return
     */
    int toggleVisible(Integer id);

    /**
     * 切换设置置顶，
     *
     * @param id 文章id
     * @return
     */
    int toggleTop(int id);

    List<Article> all();

    //置顶数量
    int topCount(String gid);

    List<Article> getPushList(Map map);

    List<User> getLikeArticleList(int aid);
}
