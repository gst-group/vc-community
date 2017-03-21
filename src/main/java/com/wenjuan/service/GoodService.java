package com.wenjuan.service;

import com.wenjuan.model.Good;

import java.util.List;
import java.util.Map;

/**
 * Created by Gao Xun on 2016/6/2.
 */
public interface GoodService {

    int deleteByPrimaryKey(Integer id);

    int insert(Good record);

    Good selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Good record);

    List<Good> getAllGood(int page, Integer countPerPage, String lastLoadId, String order);

    List<Good> GoodFy(Map map);
}
