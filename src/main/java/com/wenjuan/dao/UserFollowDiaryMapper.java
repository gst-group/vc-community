package com.wenjuan.dao;

import com.wenjuan.model.UserFollowDiary;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserFollowDiaryMapper {
    int deleteByPrimaryKey(@Param("userid") Integer userid, @Param("diaryid") Integer diaryid);

    int insert(UserFollowDiary record);

    UserFollowDiary selectByPrimaryKey(@Param("userid") Integer userid, @Param("diaryid") Integer diaryid);

    /**
     * @param config followed 是赞我的还是赞的，未设为我赞的(false),true为赞我的，boolean
     *               userId 用户id
     *               pageLimited:每页页数
     *               startIndex:开始索引
     *               order : time,follow_count,reply_count，author等与desc,asc的组合
     *               comparator 比较符号 > <
     *               time 评论时间
     * @return
     */
    List<UserFollowDiary> selectFollowInfo(Map<String, Object> config);


    List<UserFollowDiary> diaryPariseFY(Map map);

    int diaryPariseCount(int id);

    int selectFollowCount(Map<String, Object> config);

    List<UserFollowDiary> selectAll(int id);
}