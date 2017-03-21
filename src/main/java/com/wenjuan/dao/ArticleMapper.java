package com.wenjuan.dao;

import com.wenjuan.model.Article;
import com.wenjuan.model.User;
import com.wenjuan.model.UserFollowArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleMapper {
  int deleteByPrimaryKey(Integer id);

  int insert(Article record);

  Article selectByPrimaryKey(@Param("id") Integer id, @Param("userId") Integer userId);

  /**
   * @param config pageLimited:每页页数
   *               startIndex:开始索引
   *               order : time,follow_count,reply_count，author等与desc,asc的组合
   *               comparator 比较符号 > < 或 = 等
   *               id 该id之前或之后的日记
   * @return
   */
  List<Article> selectArticles(Map config);

  int selectArticlesCount(Map config);

  /**
   * @param config userId:用户id
   *               pageLimited:每页页数
   *               startIndex:开始索引
   *               order : time,follow_count,reply_count，author等与desc,asc的组合
   *               comparator 比较符号 > < 或 = 等
   *               id 该id之前或之后的日记
   * @return
   */
  List<Article> selectMyArticles(Map config);

  int selectMyArticlesCount(Map config);

  /**
   * 获取默认可见文章
   *
   * @param config userId:用户id
   *               pageLimited:每页页数
   *               startIndex:开始索引
   *               order : time,follow_count,reply_count，author等与desc,asc的组合
   * @return
   */
  List<Article> selectVisibleArticle(Map config);

  int selectVisibleArticleCount(Map config);

  /**
   * 获取某用户可见文章
   *
   * @param config userId:用户id
   *               pageLimited:每页页数
   *               startIndex:开始索引
   *               order : time,follow_count,reply_count，author等与desc,asc的组合
   * @return
   */
  List<Article> selectVisibleArticleByUser(Map config);

  int selectVisibleArticleByUserCount(Map config);

  /**
   * 获取赞过的话题列表
   *
   * @param id    用户id
   * @param order time,follow_count,reply_count，author等与desc,asc的组合
   * @return
   */
  Article[] getFavorites(Map<String, Object> config);

  int updateByPrimaryKey(Article record);

  /**
   * 注意！ 调用该方法之后会将用户设置的一些特殊的话题查看权限清空！！
   *
   * @param id
   * @return
   */
  int toggleVisible(Integer id);

  List<Article> articleSearch(@Param("content") String content, @Param("order") String order);

  List<Article> articleFY(Map map);

  int articleCount(int type);

  List<Article> articleSearch2(Map map);

  int articleSearch2Count(Map map);

  /**
   * 切换设置置顶，
   *
   * @param id 文章id
   * @return
   */
  int toggleTop(int id);

  List<UserFollowArticle> getLike(Map map);

  int getLikeCount(Map map);

  List<Article> all();

  List<Article> getArticleTitle(String content);

  Article selectByid(String id);

  List<Map> selectArticlesCountByGroup(Map config);

  //最近一个月的发布数
  List<Article> selectMMCount(int uid);

  //本月发帖
  int ArticleCountBen(int uid);

  //上月发帖
  int ArticleCountShang(int uid);

  //置顶数量
  int topCount(String gid);

  List<Article> getPushList(Map map);

  //赞的用户
  List<User> getLikeArticleList(int aid);
}