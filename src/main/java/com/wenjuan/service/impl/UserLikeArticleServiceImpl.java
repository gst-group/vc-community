package com.wenjuan.service.impl;

import com.wenjuan.dao.UserLikeArticleMapper;
import com.wenjuan.model.User;
import com.wenjuan.model.UserFollowArticle;
import com.wenjuan.model.UserLikeArticle;
import com.wenjuan.service.UserLikeArticleService;
import com.wenjuan.utils.ApplicationContextUtil;
import com.wenjuan.utils.ConfigUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.wenjuan.service.impl.UserFollowDiaryServiceImpl.getParameterMap;

@Service("userLikeArticleService")
public class UserLikeArticleServiceImpl implements UserLikeArticleService {
  @Resource
  private UserLikeArticleMapper userLikeArticleMapper;

  @Override
  public UserLikeArticle selectByPrimaryKey(UserLikeArticle record) {
    return userLikeArticleMapper.selectByPrimaryKey(record);
  }

  @Override
  public int insert(UserLikeArticle record) {
    return userLikeArticleMapper.insert(record);
  }

  @Override
  public int deleteByPrimaryKey(UserLikeArticle record) {
    return userLikeArticleMapper.deleteByPrimaryKey(record);
  }

  @Override
  public List<UserLikeArticle> getLikeListByArticle(Map<String, Integer> map) {
    return userLikeArticleMapper.getListCountByArticle(map);
  }

  @Override
  public int getLikeCountByArticle(int article) {
    Integer count = userLikeArticleMapper.getLikeCountByArticle(article);
    return count == null ? 0 : count;
  }

  @Override
  public List<UserFollowArticle> selectFollowInfo(boolean followed, int userId, int page, String order, String loadTime,int ogid) {
    int countPerPage = ConfigUtil.NUMBER_OF_ARTICLE_ITEM_PER_PAGE;
    Map<String, Object> config = getParameterMap(loadTime, userId);
    config.put("order", order);
    config.put("startIndex", page == 0 ? null : (page - 1) * countPerPage);
    config.put("pageLimited", countPerPage);
    config.put("followed", followed);
    config.put("ogid",ogid);
    return userLikeArticleMapper.selectFollowInfo(config);
  }
}
