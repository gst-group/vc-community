package com.wenjuan.dao;

import com.wenjuan.model.Draft;

import java.util.Map;

/**
 * Created by YG on 2016/9/22.
 */
public interface DraftMapper {

    Draft selectDraft(Map map);

     void insert(Draft draft);

}
