package com.wenjuan.dao;

import com.wenjuan.model.GoodDuihuan;
import com.wenjuan.model.GoodDuihuanView;

import java.util.List;
import java.util.Map;


public interface GoodDuihuanMapper {
    int deleteByPrimaryKey(GoodDuihuan key);

    int insert(GoodDuihuan record);

    GoodDuihuanView selectByPrimaryKey(GoodDuihuan key);

    int updateByPrimaryKey(GoodDuihuan record);

    /**
     * @param userId
     * @param order  排序顺序，默认时间降序，event_value,event_name,score,time,event_id与asc,desc的组合
     * @return
     */
    List<GoodDuihuanView> getAllRecord(Map config);

    List<GoodDuihuanView> getAll(Map map);

    int getAllcount();

    List<GoodDuihuanView> getUserAll(Map map);

    int getUsercount(int uid);
}