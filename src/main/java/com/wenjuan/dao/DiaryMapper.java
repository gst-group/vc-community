package com.wenjuan.dao;

import com.wenjuan.model.Diary;
import com.wenjuan.vo.DiaryView;

import java.util.List;
import java.util.Map;

public interface DiaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Diary record);

    DiaryView selectByPrimaryKey(Integer id);

    /**
     * @param config pageLimited:每页页数
     *               startIndex:开始索引
     *               order : time,follow_count,reply_count，author等与desc,asc的组合
     *               comparator 比较符号 > < 或 = 等
     *               id 该id之前或之后的日记
     * @return
     */
    List<DiaryView> selectDiaries(Map config);

    int selectDiariesCount(Map config);

    /**
     * @param config userId:用户id
     *               pageLimited:每页页数
     *               startIndex:开始索引
     *               order : time,follow_count,reply_count，author等与desc,asc的组合
     *               comparator 比较符号 > < 或 = 等
     *               id 该id之前或之后的日记
     * @return
     */
    List<DiaryView> selectMyDiaries(Map config);

    int selectMyDiariesCount(Map config);

    /**
     * 获取赞过的日记列表
     *
     * @param id 用户id
     * @return
     */
    List<Diary> selectMyFavor(Map map);

    /**
     * 注意！ 调用该方法之后会将用户设置的一些特殊的话题查看权限清空！！
     *
     * @param id
     * @return
     */
    int toggleVisible(Integer id);

    int updateByPrimaryKey(Diary record);

    /**
     * @param config key 搜索内容，必选
     *               startIndex 分页开始索引，可选
     *               order
     *               pageLimited 每页项数
     * @return
     */
    List<Diary> diarySearch(Map config);

    List<Diary> diaryFy(Map map);

    List<Diary> diaryFyName(Map map);

    int diaryCount(int ogid);

    List<Diary> diarySearch2(Map map);

    int diarySearch2count(Map map);


    void top(Map map);

}