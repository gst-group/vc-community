package com.wenjuan.dao;

import java.util.Map;

/**
 * Created by YG on 2016/9/22.
 */
public interface SensitiveMapper {
    void updateSensitive(Map map);
    String selectSensitive(int ogid);
    void insert(Map map);
}
