package com.wenjuan.utils;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wenjuan.dao.UserMapper;
import com.wenjuan.model.User;
import com.wenjuan.service.UserService;
import com.wenjuan.vo.UserBriefInfo;
import com.wenjuan.vo.UserHx;
import com.wowtour.jersey.api.EasemobChatGroups;
import com.wowtour.jersey.api.EasemobChatMessage;
import com.wowtour.jersey.api.EasemobIMUsers;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 环信工具类 TODO Y
 */

public class HxUtil {
    @Autowired
    UserMapper userMapper;

    private static Logger LOGGER = LoggerFactory
            .getLogger(EasemobIMUsers.class);
    @Autowired
    @Qualifier("userService")
    UserService userServiceImpl;

    /**
     * 获取环信组里面的用户的用户基本信息
     *
     * @param grpName 组id
     * @return
     */
    public static UserHx getUserListUnderHxGrp(String grpName) {
        String chatgroupid = grpName;
        ObjectNode getAllMemberssByGroupIdNode = EasemobChatGroups.getAllMemberssByGroupId(chatgroupid);
        ArrayNode memberArray = (ArrayNode) getAllMemberssByGroupIdNode.get("data");
        int memberSize = memberArray.size();
        ArrayList<String> al = new ArrayList<>();
        for (int i = 0; i < memberSize; ++i) {
            JsonNode idJsonNode = memberArray.get(i);
            JsonNode idNode = idJsonNode.get("member");
            if (idNode == null) {
                idNode = idJsonNode.get("owner");
            }
            al.add(idNode.textValue());
        }
        UserHx userHx = new UserHx();
        userHx.setUser(ApplicationContextUtil.getBean("userService", UserService.class).selectUserInfoByList(al));
        userHx.setGroupid(grpName);
        userHx.setCount(memberSize);
        return userHx;
    }

    /**
     * 根据用户号码获取群组及群成员信息
     * li中结构
     * li-----------JSONObject
     * JSONObject -------groupid,groupName,count,User
     * count用户数量
     * ObjectNode类型的User
     * User--------------member成员，owner群主
     *
     * @param tel
     * @return
     */
    public static List<UserHx> getAll(String tel) {
        String username = tel;
        ObjectNode getJoinedChatgroupsForIMUserNode = EasemobChatGroups.getJoinedChatgroupsForIMUser(username);
        JsonNode a = getJoinedChatgroupsForIMUserNode.get("data");
        UserService userService = ApplicationContextUtil.getBean("userService", UserService.class);
        List<UserHx> li = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            //获取组id
            String groupid = a.get(i).get("groupid").textValue();
            //获取组成员
            ObjectNode getAllMemberssByGroupIdNode = EasemobChatGroups.getAllMemberssByGroupId(a.get(i).get("groupid").textValue());
            //组名字
            String groupName = a.get(i).get("groupname").textValue();
            UserHx userHx = new UserHx();
            userHx.setGroupid(groupid);
            userHx.setGroupName(groupName);
            userHx.setCount(getAllMemberssByGroupIdNode.get("count").intValue());
            List<String> names = new ArrayList<>();
            ArrayNode namesNode = (ArrayNode) getAllMemberssByGroupIdNode.get("data");
            for (JsonNode jsonNode : namesNode) {
                JsonNode nameNode = jsonNode.get("member");
                if (nameNode == null) {
                    nameNode = jsonNode.get("owner");
                }
                names.add(nameNode.textValue());
            }
            userHx.setUser(userService.selectUserBriefInfoByNameList(names));
            li.add(userHx);
        }
        return li;
    }

    /**
     * 查看好友
     */
    public static List<UserBriefInfo> getfriend(User user) {
        JsonNode friendsNode = EasemobIMUsers.getFriends(user.getName()).get("data");
        List<String> userHxes = new ArrayList<>();
        if (friendsNode == null) {
            return null;
        }
        for (int i = 0; i < friendsNode.size(); ++i) {
            userHxes.add(friendsNode.get(i).textValue());
        }
        return ApplicationContextUtil.getUserService().selectUserInfoByList(userHxes);
    }

    /**
     * 插入用户 其登陆用户名和密码分别为电话号码和登陆密码
     *
     * @param user
     * @throws IOException
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody

    public String insertUser(User user) throws IOException {
        //注册用户
        String tel = user.getName();
        String pwd = user.getPassword();
        // 环信注册
        ObjectNode datanode = JsonNodeFactory.instance.objectNode();
        datanode.put("username", tel);
        datanode.put("password", pwd);
        ObjectNode node = EasemobIMUsers.createNewIMUserSingle(datanode);
        if (node.get("statusCode").toString().equals("200")) {
            return "success";
        } else {

            //若插入失败尝试更新
            String username =tel;
            ObjectNode json2 = JsonNodeFactory.instance.objectNode();
            json2.put("newpassword",pwd);
            ObjectNode modifyIMUserPasswordWithAdminTokenNode = EasemobIMUsers.modifyIMUserPasswordWithAdminToken(username, json2);
            if (modifyIMUserPasswordWithAdminTokenNode.get("statusCode").toString().equals("200")) {
                return "success";
            }else{
                return "error";
            }
        }
    }
/*
    public List getGroupInfo() {
        User u;
        ObjectNode chatgroupidsNode = EasemobChatGroups.getAllChatgroupids();
        String jString = chatgroupidsNode.toString();
        JSONObject obj = JSONObject.fromObject(jString);
        String group = obj.get("data").toString();
        JSONArray groupinfo = new JSONArray();
        JSONArray json = JSONArray.fromObject(group);
        for (int i = 0; i < json.size(); i++) {
            JSONObject result = new JSONObject();
            String groupid = (String) json.getJSONObject(i).get("groupid");
            String ownerid = (String) json.getJSONObject(i).get("owner");
            result.put("groupid", groupid);
            String username = new String(ownerid);
            String[] str = username.split("\\_");
            for (String s : str) {
                // u=userServiceImpl.selectByName(s);
                // result.put("name",u.getNickname());
                result.put("name", s);
            }
            String groupname = (String) json.getJSONObject(i).get("groupname");
            result.put("groupname", groupname);
            String date = (String) json.getJSONObject(i).get("last_modified");
            result.put("time", date);
            groupinfo.add(result);
        }
        return groupinfo;
    }
*/

    /**
     * 更新用户信息
     * 密码、电话号码等
     *
     * @param user
     */
    @RequestMapping(value = "/register/update", method = RequestMethod.GET)
    public void updateUserInfo(User user) {
       /* userServiceImpl.updateByPrimaryKey(user);*/
        String username = user.getName();
        ObjectNode json2 = JsonNodeFactory.instance.objectNode();
        json2.put("newpassword", user.getPassword());
        ObjectNode modifyIMUserPasswordWithAdminTokenNode = EasemobIMUsers
                .modifyIMUserPasswordWithAdminToken(username, json2);
    }

    /**
     * 删除用户信息
     *
     * @param user
     */

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void deleteUser(User user) {
        // 环信删除
        ObjectNode deleteIMUserByUserPrimaryKeyNode = EasemobIMUsers
                .deleteIMUserByUserPrimaryKey(user.getName());
    }

    public List getGroupInfo() {
        User u;
        ObjectNode chatgroupidsNode = EasemobChatGroups.getAllChatgroupids();
        String jString = chatgroupidsNode.toString();
        JSONObject obj = JSONObject.fromObject(jString);
        String group = obj.get("data").toString();
        JSONArray groupinfo = new JSONArray();
        JSONArray json = JSONArray.fromObject(group);
        for (int i = 0; i < json.size(); i++) {
            JSONObject result = new JSONObject();
            String groupid = (String) json.getJSONObject(i).get("groupid");
            String ownerid = (String) json.getJSONObject(i).get("owner");
            result.put("groupid", groupid);
            String username = new String(ownerid);
            String[] str = username.split("\\_");
            for (String s : str) {
                result.put("name", s);
            }
            String groupname = (String) json.getJSONObject(i).get("groupname");
            result.put("groupname", groupname);
            String date = (String) json.getJSONObject(i).get("last_modified");
            result.put("count", obj.get("count"));
            result.put("time", date);
            groupinfo.add(result);
        }
        return groupinfo;
    }
    //循环插入数据库

    /**
     * 获取聊天记录
     *
     * @return
     */

    public ObjectNode messageHistory(Date time) {
        ObjectNode ja = EasemobChatMessage.messageHistory(time);
        return ja;

    }

    /**
     * 添加群组
     */
    public String groupAdd(String gname, String desc, String owner, int max, List users) {
        ObjectNode dataObjectNode = JsonNodeFactory.instance.objectNode();
        dataObjectNode.put("groupname", gname);
        dataObjectNode.put("desc", desc);
        dataObjectNode.put("approval", true);
        dataObjectNode.put("public", true);
        dataObjectNode.put("maxusers", max);
        dataObjectNode.put("owner", owner);
        ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
        for (int i = 0; i < users.size(); i++) {
            arrayNode.add(users.get(i).toString().trim());
        }
        dataObjectNode.put("members", arrayNode);
        ObjectNode creatChatGroupNode = EasemobChatGroups.creatChatGroups(dataObjectNode);
        return "success";
    }

    /**
     * 获取用户组详情
     */
    public String group(String gid) {
//    	String[] chatgroupIDs ={gid};
//    	System.out.println(gid);
        ObjectNode groupDetailNode = EasemobChatGroups.getGroupDetailsByChatgroupid(gid);
        JsonNode jn = groupDetailNode.get("data");
        String newStr1 = "";
        for (int i = 0; i < jn.size(); i++) {
            String name = jn.get(i).get("name").textValue();
            newStr1 = name.replaceAll("\"", "");
        }
        return newStr1;
    }

    /**
     * 获取用户组详情
     */
    public JSONObject groupGetInfo(String gid) {
        ObjectNode groupDetailNode = EasemobChatGroups.getGroupDetailsByChatgroupid(gid);
        JsonNode jn = groupDetailNode.get("data");
        JSONObject json = new JSONObject();
        if(jn!=null){
            for (int i = 0; i < jn.size(); i++) {
                try{
                    String name = jn.get(i).get("name").textValue();
                    String desc = jn.get(i).get("description").textValue();
                    int max = Integer.parseInt(jn.get(i).get("maxusers").toString());
                    json.put("name", name);
                    json.put("desc", desc);
                    json.put("max", max);
                }catch (Exception e){
                    json.put("name", " ");
                    json.put("desc", " ");
                    json.put("max", 0);
                }


            }
        }else{
            json.put("name", " ");
            json.put("desc", " ");
            json.put("max", 0);
        }

        return json;
    }

    /**
     * 添加组成员
     */
    public String groupAddUser(String user, String gid) {
        String addToChatgroupid = gid;
        String toAddUsername = user;
        ObjectNode addUserToGroupNode = EasemobChatGroups.addUserToGroup(addToChatgroupid, toAddUsername);
        return "success";
    }

    /**
     * 修改环信组信息
     */
    public String ChangGroup(String gid, String grname, String desc, int max) {

        EasemobChatGroups.Chang(gid, grname, desc, max);
        return "success";
    }

    //删除组成员
    public void deleteGroupUser(String tel, String gid) {
        EasemobChatGroups.deleteGroupUser(tel, gid);
    }

    //删除组
    public void deleteGroup(String gid) {
        EasemobChatGroups.deleteGroup(gid);
    }

    //获取群主
    public String getOwner(String grpName) {
        String chatgroupid = grpName.replaceAll("\"", "");
        ObjectNode getAllMemberssByGroupIdNode = EasemobChatGroups.getAllMemberssByGroupId(chatgroupid);
        ArrayNode memberArray = (ArrayNode) getAllMemberssByGroupIdNode.get("data");
        String owner = "";
        for (JsonNode jsonNode : memberArray) {
            Object str = jsonNode.get("owner");
            if (str != null && !str.equals(""))
                owner = str.toString().replaceAll("\"", "");
        }
        return owner;
    }

public static void main(String [] args){
    String tel="";
    ObjectNode deleteIMUserByUserPrimaryKeyNode = EasemobIMUsers
            .deleteIMUserByUserPrimaryKey(tel);
}
}

