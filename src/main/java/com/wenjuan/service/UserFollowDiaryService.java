package com.wenjuan.service;

import com.wenjuan.model.UserFollowDiary;

import java.util.List;
import java.util.Map;


public interface UserFollowDiaryService {

    int deleteByPrimaryKey(Integer userid, Integer diaryid);

    int insert(UserFollowDiary record);

    UserFollowDiary selectByPrimaryKey(Integer userid, Integer diaryid);

    /**
     * @param followed
     * @param userId
     * @param page     页码，0不分页
     * @param order
     * @param loadId
     * @return
     */
    List<UserFollowDiary> selectFollowInfo(boolean followed, int userId, int page, String order, String loadId,int ogid);


    List<UserFollowDiary> diaryPariseFY(Map map);

    int diaryPariseCount(int id);

    int selectFollowCount(String loadTime, int userId);

    List<UserFollowDiary> selectAll(int id);
}
