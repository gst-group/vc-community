package com.wenjuan.dao;

import com.wenjuan.model.Notifies;
import com.wenjuan.model.NotifiesUser;
import com.wenjuan.model.NotifiesUserView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NotifiesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notifies record);

    Notifies selectByPrimaryKey(Integer id);

    List<Notifies> selectNotifys(Map map);

    int count();

    void inNU(Map map);

    int selectBytitle(String content);

    NotifiesUserView[] selectNotifyViews(Map<String, Object> config);

    int insertNotifyUserByList(@Param("notifiesUsers") List<NotifiesUser> notifiesUsers);

    List<Notifies> selectNotifysSeach(Map map);

    int countSeach(String key);

    int selectPushCount(Map<String, Object> config);
}