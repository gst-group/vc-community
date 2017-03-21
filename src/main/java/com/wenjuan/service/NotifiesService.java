package com.wenjuan.service;

import com.wenjuan.model.Notifies;
import com.wenjuan.model.NotifiesUser;
import com.wenjuan.model.NotifiesUserView;
import com.wenjuan.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Gao Xun on 2016/6/2.
 */
public interface NotifiesService {

    int deleteByPrimaryKey(Integer id);

    int insert(Notifies record);

    Notifies selectByPrimaryKey(Integer id);

    List<Notifies> selectNotifys(Map map);

    int count();

    void inNU(Map map);


    int selectBytitle(String content);

    NotifiesUserView[] selectNotifyViews(String loadId, String order, int page, User user);

    int insertNotifyUserByList(List<NotifiesUser> notifiesUsers);

    List<Notifies> selectNotifysSeach(Map map);

    int countSeach(String key);

    int selectPushCount(String loadId,User user);
}
