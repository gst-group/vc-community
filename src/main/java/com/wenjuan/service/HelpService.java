package com.wenjuan.service;

import com.wenjuan.model.Help;

import java.util.List;

/**
 * Created by Gao Xun on 2016/6/2.
 */
public interface HelpService {

    int deleteByPrimaryKey(Integer id);

    int insert(Help record);

    Help selectByPrimaryKey(Integer id);

    List<Help> selectAllHelps();

    int toggleVisible(int id);

    int updateByPrimaryKey(Help record);
}
