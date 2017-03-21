package com.wenjuan.service;

import com.wenjuan.bean.AvailBean;
import com.wenjuan.model.ArticleEnable;

/**
 * Created by Gao Xun on 2016/6/14.
 */
public interface ArticleEnableService {

    int deleteByPrimaryKey(ArticleEnable articleEnable);

    int insert(ArticleEnable record);

    ArticleEnable selectByPrimaryKey(ArticleEnable articleEnable);

    AvailBean getAvail(Integer id);
}
