package com.wenjuan.service;

import com.wenjuan.model.Diary;
import com.wenjuan.model.User;
import com.wenjuan.vo.DiaryView;

import java.util.List;
import java.util.Map;

/**
 * Created by Gao Xun on 2016/6/2.
 */
public interface DiaryService {

    int deleteByPrimaryKey(Integer id);

    int insert(Diary record);

    DiaryView selectByPrimaryKey(Integer id);

    /**
     * @param type         0：自己发布的，1：全部可见的
     * @param user         不验证用户是否存在
     * @param order        time,follow_count,reply_count，author等与desc,asc的组合
     * @param page         页数，0时不分页
     * @param countPerPage 每页项数，null时为默认值
     * @param loadId       字符A或B开头，后面跟上id，分别表示After和Before,例如A0表示id为0之后的文章
     * @return
     */
    List<?> selectDiaries(int type, User user, String order, int page, Integer countPerPage, String loadId,int ogid);

    int selectDiariesCount(int type, User user, String loadId,int ogid);

    List<?> selectMyFavor(Map map);

    int diaryCount(int ogid);

    int toggleVisible(Integer id);

    int updateByPrimaryKey(Diary record);

    List<Diary> diarySearch(String content, int page,int ogid);

    void top(Map map);
}
