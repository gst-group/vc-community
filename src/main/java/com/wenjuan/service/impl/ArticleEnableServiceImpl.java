package com.wenjuan.service.impl;

import com.wenjuan.bean.AvailBean;
import com.wenjuan.dao.ArticleEnableMapper;
import com.wenjuan.model.ArticleEnable;
import com.wenjuan.service.ArticleEnableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service("articleEnableService")
public class ArticleEnableServiceImpl implements ArticleEnableService {
    @Resource
    ArticleEnableMapper articleEnableMapper;

    @Override
    public int deleteByPrimaryKey(ArticleEnable articleEnable) {
        return articleEnableMapper.deleteByPrimaryKey(articleEnable);
    }

    @Override
    public int insert(ArticleEnable record) {
        return articleEnableMapper.insert(record);
    }

    @Override
    public ArticleEnable selectByPrimaryKey(ArticleEnable articleEnable) {
        return articleEnableMapper.selectByPrimaryKey(articleEnable);
    }


    @Override
    public AvailBean getAvail(Integer id) {
        List<ArticleEnable> articleEnableList = articleEnableMapper.getAvail(id);
        List<String> users = new ArrayList<>();
        List<String> groups = new ArrayList<>();
        for (ArticleEnable articleEnable : articleEnableList) {
            switch (articleEnable.getEnable()) {
                case 1://组
                    groups.add(articleEnable.getName());
                    break;
                case 2://用户
                    users.add(articleEnable.getName());
                    break;
            }
        }
        AvailBean availBean = new AvailBean();
        availBean.setUser(users);
        availBean.setGroup(groups);
        return availBean;
    }
}
