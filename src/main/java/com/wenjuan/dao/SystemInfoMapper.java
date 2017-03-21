package com.wenjuan.dao;

import com.wenjuan.model.SystemInfo;

import java.util.Map;

/**
 * Created by admin on 2016/10/29.
 */
public interface SystemInfoMapper {
    /**
     * 根据id查询配置
     *
     */
    SystemInfo getSystemInfoByid(Map map);
    /**
     * 修改配置信息
     */
    void update(SystemInfo S);

    /**
     * 插入配置组织信息
     */
    void insertSystemInfo(Map map);

    /**
     * 组织开启日记模块
     */
    int getDiaryVisible(int ogid);
    void UpdateDiaryVisible(Map map);
    void isnertDiaryInfo(Map map);
}
