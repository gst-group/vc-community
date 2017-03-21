package com.wenjuan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wenjuan.utils.CommonUtil;

public class ArticleComment extends CommentBase {

    /**
     * 评论的文章id
     */
    private Integer article;

    /**
     * 图片，多张图片以逗号分隔
     */
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

    public Integer type;

    @Override
    public Integer getType() {
        return type;
    }

    @Override
    public void setType(Integer type) {
        this.type = type;
    }

    @JsonProperty("video")
    public String getVideo() {
        return CommonUtil.getVideo(pics);
    }

    @JsonProperty("pics")
    public String[] getPictures() {
        return CommonUtil.getPictures(pics);
    }
}