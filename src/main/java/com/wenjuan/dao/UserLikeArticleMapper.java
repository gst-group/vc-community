package com.wenjuan.dao;

import com.wenjuan.model.UserFollowArticle;
import com.wenjuan.model.UserLikeArticle;

import java.util.List;
import java.util.Map;

public interface UserLikeArticleMapper {
  UserLikeArticle selectByPrimaryKey(UserLikeArticle record);

  int insert(UserLikeArticle record);

  int deleteByPrimaryKey(UserLikeArticle record);

  List<UserLikeArticle> getListCountByArticle(Map<String, Integer> map);

  Integer getLikeCountByArticle(int article);

  /**
   * @param config followed 是赞我的还是赞的，未设为我赞的(false),true为赞我的，boolean
   *               userId 用户id
   *               pageLimited:每页页数
   *               startIndex:开始索引
   *               order : time,follow_count,reply_count，author等与desc,asc的组合
   *               comparator 比较符号 > <
   *               time 评论时间
   * @return
   */
  List<UserFollowArticle> selectFollowInfo(Map<String, Object> config);
}
