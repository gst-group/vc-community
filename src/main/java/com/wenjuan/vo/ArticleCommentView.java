package com.wenjuan.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wenjuan.utils.CommonUtil;

public class ArticleCommentView extends CommentViewBase {
    private Integer article;

    @JsonIgnore
    private String pics;

    public Integer getArticle() {
        return article;
    }

    public void setArticle(Integer article) {
        this.article = article;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics == null ? null : pics.trim();
    }

    @JsonProperty("video")
    public String getVideo() {
        return CommonUtil.getVideo(pics);
    }

    @JsonProperty("pics")
    public String[] getPictures() {
        return CommonUtil.getPictures(pics);
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer type;
}