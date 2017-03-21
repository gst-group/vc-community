package com.wenjuan.model;

import java.util.Date;

public class UserLikeArticle {
  public Integer articleId;

  public Integer userId;

  public Date time;

  public UserLikeArticle(){

  }

  public UserLikeArticle(Integer articleId,Integer userId){
    this.articleId = articleId;
    this.userId = userId;
  }

  public Integer getArticleId() {
    return articleId;
  }

  public void setArticleId(Integer articleId) {
    this.articleId = articleId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }
}
