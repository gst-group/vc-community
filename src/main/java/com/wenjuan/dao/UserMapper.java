package com.wenjuan.dao;

import com.wenjuan.model.User;
import com.wenjuan.model.UserView;
import com.wenjuan.vo.UserBriefInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    User selectByUsernamePassword(@Param("username") String username, @Param("password") String password);

    User selectByName(String name);

    User[] selectByNames(@Param("names") String[] names);

    User selectByToken(String token);

    int updateByPrimaryKey(User record);

    List<User> getAllUserInfo();

    int UserCount(int uid);

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

    /**
     * 获取该组下的所有用户成员
     *
     * @param grp 组名
     * @return
     */
    List<User> getUserListUnderGrp(Map<String, Object> config);

    /**
     * 关注收藏日记的用户
     *
     * @param id
     * @return
     */
    List<UserBriefInfo> selectFollowUserByDiaryId(Integer id);

    List<UserBriefInfo> selectFollowUserByArticleId(Integer id);

    List<UserBriefInfo> selectUserInfoByList(@Param("ids") List<String> ids);

    List<UserBriefInfo> selectUserBriefInfoByNameList(@Param("names") List<String> names);

    /**
     * 切换用户订阅商品状态
     *
     * @param userid
     * @return
     */
    int toggleNotifyNewGood(int userid);

    List<User> getUserFY(Map map);

    //登录次数
    int getLoginCount();

    /**
     * 获取新消息数量
     *
     * @param id 用户id
     * @return
     */
    int getNewMsgCount(@Param("userId") Integer id, @Param("type") int type);

    List<User> adminUserList(Map map);

    int adminCount();

    List<User> filter(Map<String, Object> config);

    List<User> unGroupedUser(Map config);

    List<User> selectSubscribeGoodUser();

    List<UserView> selectUserViewByGroups(@Param("grps") List<String> grps);

    List<User> selectUserByGroups(@Param("grps") List<String> grps);

    User selectAdmin(String name);

    List<User> seach(Map map);

    int seachCount(Map map);

    List<String> getExistDegree();

    List<String> selectUserNameUnderGroup(String grpName);

    List<User> userSearchAlert(Map map);

    //权限判断
    int getRootType(int uid);
}