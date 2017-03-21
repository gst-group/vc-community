package com.wenjuan.dao;

import com.wenjuan.model.ArticleEnable;

import java.util.List;

public interface ArticleEnableMapper {
    int deleteByPrimaryKey(ArticleEnable articleEnable);

    int insert(ArticleEnable record);

    ArticleEnable selectByPrimaryKey(ArticleEnable articleEnable);

    /**
     * 获取话题id的访问权限
     *
     * @param id 话题id
     * @return
     */
    List<ArticleEnable> getAvail(Integer id);
}