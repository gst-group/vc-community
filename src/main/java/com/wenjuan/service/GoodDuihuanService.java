package com.wenjuan.service;

import com.wenjuan.model.GoodDuihuan;
import com.wenjuan.model.GoodDuihuanView;
import com.wenjuan.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Gao Xun on 2016/6/2.
 */
public interface GoodDuihuanService {

    int deleteByPrimaryKey(GoodDuihuan key);

    int insert(GoodDuihuan record);

    GoodDuihuan selectByPrimaryKey(GoodDuihuan key);

    int updateByPrimaryKey(GoodDuihuan record);

    List<GoodDuihuanView> getAllRecord(String order, String loadTime, int page, User user);

    List<GoodDuihuanView> getAll(Map map);

    int getAllCount();

    List<GoodDuihuanView> getUserAll(Map map);

    int getUsercount(int uid);
}
