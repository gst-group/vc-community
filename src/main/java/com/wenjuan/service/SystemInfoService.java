package com.wenjuan.service;

import com.wenjuan.model.SystemInfo;
import  java.util.Map;
/**
 * Created by admin on 2016/10/29.
 */
public interface SystemInfoService {

    SystemInfo getSystemInfoByid(Map map);
    void update(SystemInfo S);
    void insertSystemInfo(Map map);

    /**
     * 组织开启日记模块
     */
    int getDiaryVisible(int ogid);
    void UpdateDiaryVisible(Map map);
    void isnertDiaryInfo(Map map);

}
