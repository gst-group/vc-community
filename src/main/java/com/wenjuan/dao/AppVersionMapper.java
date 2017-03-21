package com.wenjuan.dao;

import com.wenjuan.model.AppVersion;

public interface AppVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppVersion record);

    AppVersion selectByPrimaryKey(Integer id);

    /**
     * 获取指定平台最新版本信息
     *
     * @param platCode 台号，apple为2，android为1
     * @return
     */
    AppVersion selectLastVersion(Integer platCode);

    int updateByPrimaryKey(AppVersion record);


}