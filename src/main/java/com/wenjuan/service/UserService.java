package com.wenjuan.service;

import com.wenjuan.model.User;
import com.wenjuan.model.UserView;
import com.wenjuan.vo.UserBriefInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Gao Xun on 2016/6/2.
 */
public interface UserService {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    User selectByUsernamePassword(String username, String password);

    User selectByName(String name);

    User[] selectByNames(String[] names);

    User selectByToken(String token);

    int updateByPrimaryKey(User record);

    /**
     * 切换用户订阅商品状态
     *
     * @param userid
     * @return
     */
    int toggleNotifyNewGood(int userid);

    List<User> getAllUserInfo();

    /**
     * 获取赞过指定日记的用户
     *
     * @param id 日记id
     * @return
     */
    List<User> getDiaryFavors(Integer id);

    /**
     * 获取赞过指定话题的用户
     *
     * @param id 话题id
     * @return
     */
    List<User> getArticleFavors(Integer id);

    /**
     * 获取赞过指定用户的用户列表
     *
     * @param id 用户id
     * @return
     */
    List<User> getFavorMe(Integer id);

    List<User> getUserListUnderGrp(Map<String, Object> config);

    List<UserBriefInfo> selectFollowUserByDiaryId(Integer id);

    List<UserBriefInfo> selectFollowUserByArticleId(Integer id);

    List<UserBriefInfo> selectUserInfoByList(List<String> ids);

    /**
     * 新消息数量
     *
     * @param id 用户id
     * @return
     */
    int getNewMsgCount(Integer id, int type);

    int insertAdminUser(User user);

    List<User> adminUserList(Map map);

    int adminCount();

    List<User> filter(Map<String, Object> config);

    List<User> unGroupedUser(Map config);

    List<User> selectSubscribeGoodUser();

    List<User> selectUserByGroups(List<String> grps);

    List<UserView> selectUserViewByGroups(List<String> grps);

    List<UserBriefInfo> selectUserBriefInfoByNameList(List<String> names);


    List<String> getExistDegree();

    List<String> selectUserNameUnderGroup(String grpName);

    int getRootType(int id);
}
