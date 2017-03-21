package com.wowtour.jersey.api;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wowtour.jersey.comm.Constants;
import com.wowtour.jersey.comm.HTTPMethod;
import com.wowtour.jersey.comm.Roles;
import com.wowtour.jersey.utils.JerseyUtils;
import com.wowtour.jersey.vo.ClientSecretCredential;
import com.wowtour.jersey.vo.Credential;
import com.wowtour.jersey.vo.EndPoints;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * REST API Demo : 群组管理 Jersey2.9实现
 * <p>
 * Doc URL: http://www.easemob.com/docs/rest/groups/
 *
 * @author Lynch 2014-09-12
 */
public class EasemobChatGroups {

    private static final String APPKEY = Constants.APPKEY;
    private static Logger LOGGER = LoggerFactory.getLogger(EasemobChatGroups.class);
    // 通过app的client_id和client_secret来获取app管理员token
    private static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
            Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);
    private static JsonNodeFactory factory = new JsonNodeFactory(false);

    public static void main(String[] args) {
//    	ObjectNode dataObjectNode = JsonNodeFactory.instance.objectNode();
//    	String groupid="200468952965448108";
//    	dataObjectNode.put("groupname", "testtesttest");
//    	dataObjectNode.put("description","错误的描述");
//    	dataObjectNode.put("maxusers",20);
//    	ObjectNode chatgroupidsNode =changGourp(dataObjectNode,groupid);
//    	System.out.println(chatgroupidsNode);
//		/** 获取APP中所有的群组ID
//		 * curl示例:
//		 * curl -X GET -i "https://a1.easemob.com/easemob-playground/test1/chatgroups" -H "Authorization: Bearer {token}"
//		 */
//		ObjectNode chatgroupidsNode = getAllChatgroupids();
//		System.out.println(chatgroupidsNode.toString());
//
//		/**
//		 * 获取一个或者多个群组的详情
//		 * curl示例
//		 * curl -X GET -i "https://a1.easemob.com/easemob-playground/test1/chatgroups/1414379474926191,1405735927133519"
//         * -H "Authorization: Bearer {token}"
//		 */
//		String[] chatgroupIDs = {"200468946804015528"};
//		ObjectNode groupDetailNode = getGroupDetailsByChatgroupid("200468946804015528");
//		System.out.println(groupDetailNode.get("data").get(0).get("name"));
//
        /** 创建群组
         * curl示例
         * curl -X POST 'https://a1.easemob.com/easemob-playground/test1/chatgroups' -H 'Authorization: Bearer {token}' -d '{"groupname":"测试群组","desc":"测试群组","public":true,"approval":true,"owner":"xiaojianguo001","maxusers":333,"members":["xiaojianguo002","xiaojianguo003"]}'
         */
//		ObjectNode dataObjectNode = JsonNodeFactory.instance.objectNode();
//		dataObjectNode.put("groupname", "测试群组");
//		dataObjectNode.put("desc", "测试群组");
//		dataObjectNode.put("approval", true);
//		dataObjectNode.put("public", true);
//		dataObjectNode.put("maxusers",10);
//		dataObjectNode.put("owner", "18818216390");
//		ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
//		arrayNode.add("18818216390");
//		dataObjectNode.put("members", arrayNode);
//		ObjectNode creatChatGroupNode = creatChatGroups(dataObjectNode);
//		System.out.println(creatChatGroupNode.toString());
//		System.out.println(creatChatGroupNode.findValue("groupid"));

//
//		/**
//		 * 删除群组
//		 * curl示例
//		 * curl -X DELETE 'https://a1.easemob.com/easemob-playground/test1/chatgroups/1405735927133519' -H 'Authorization: Bearer {token}'
//		 */
//		String toDelChatgroupid = "1405735927133519";
//		ObjectNode deleteChatGroupNode =  deleteChatGroups(toDelChatgroupid) ;
//		System.out.println(deleteChatGroupNode.toString());
//
//		/**
//		 * 获取群组中的所有成员
//		 * curl示例
//		 * curl -X GET 'https://a1.easemob.com/easemob-playground/test1/chatgroups/1405735927133519/users' -H 'Authorization: Bearer {token}'
//		 */
//		String chatgroupid = "200468946804015528";
//		ObjectNode getAllMemberssByGroupIdNode = getAllMemberssByGroupId(chatgroupid);
//		System.out.println(getAllMemberssByGroupIdNode.toString());
//
//		/**
//		 * 在群组中添加一个人
//		 * curl示例
//		 * curl -X POST 'https://a1.easemob.com/easemob-playground/test1/chatgroups/1405735927133519/users/xiaojianguo002' -H 'Authorization: Bearer {token}'
//		 */
//		String addToChatgroupid = "1405735927133519";
//		String toAddUsername = "xiaojianguo002";
//		ObjectNode addUserToGroupNode = addUserToGroup(addToChatgroupid, toAddUsername);
//		System.out.println(addUserToGroupNode.toString());
//
//		/**
//		 * 在群组中减少一个人
//		 * curl示例
//		 * curl -X DELETE 'https://a1.easemob.com/easemob-playground/test1/chatgroups/1405735927133519/users/xiaojianguo002' -H 'Authorization: Bearer {token}'
//		 */
//		String delFromChatgroupid = "1405735927133519";
//		String toRemoveUsername = "xiaojianguo002";
//		ObjectNode deleteUserFromGroupNode = deleteUserFromGroup(delFromChatgroupid, toRemoveUsername);
//		System.out.println(deleteUserFromGroupNode.asText());
//
//		/**
//		 * 获取一个用户参与的所有群组
//		 * curl示例
//		 * curl -X GET 'https://a1.easemob.com/easemob-playground/test1/users/xiaojianguo002/joined_chatgroups' -H 'Authorization: Bearer {token}'
//		 */
//		String username = "18818216390";
//		ObjectNode getJoinedChatgroupsForIMUserNode =getJoinedChatgroupsForIMUser(username);
//		System.out.println(getJoinedChatgroupsForIMUserNode.toString());

//		/**
//		 * 群组批量添加成员
//		 * curl示例
//		 * curl -X POST -i 'https://a1.easemob.com/easemob-playground/test1/chatgroups/1405735927133519/users' -H 'Authorization: Bearer {token}' -d '{"usernames":["xiaojianguo002","xiaojianguo003"]}'
//		 */
//		String toAddBacthChatgroupid = "1405735927133519";
//		ArrayNode usernames = JsonNodeFactory.instance.arrayNode();
//		usernames.add("xiaojianguo002");
//		usernames.add("xiaojianguo003");
//		ObjectNode usernamesNode = JsonNodeFactory.instance.objectNode();
//		usernamesNode.put("usernames", usernames);
//		ObjectNode addUserToGroupBatchNode = addUsersToGroupBatch(toAddBacthChatgroupid, usernamesNode);
//		System.out.println(addUserToGroupBatchNode.toString());
    }

    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 获取APP中所有的群组ID
     *
     * @return
     */
    public static ObjectNode getAllChatgroupids() {

        ObjectNode objectNode = factory.objectNode();

        // check appKey format
        if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + APPKEY);

            objectNode.put("message", "Bad format of Appkey");

            return objectNode;
        }

        try {

            JerseyWebTarget webTarget = null;
            webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
                    .resolveTemplate("app_name", APPKEY.split("#")[1]);

            objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 获取一个或者多个群组的详情
     *
     * @return
     */
//	public static ObjectNode getGroupDetailsByChatgroupid(String[] chatgroupIDs) {
    public static ObjectNode getGroupDetailsByChatgroupid(String gid) {

        ObjectNode objectNode = factory.objectNode();

        // check appKey format
        if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + APPKEY);

            objectNode.put("message", "Bad format of Appkey");

            return objectNode;
        }

        try {
            JerseyWebTarget webTarget = null;
            webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
                    .resolveTemplate("app_name", APPKEY.split("#")[1]).path(gid);

            objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 创建群组
     */
    public static ObjectNode creatChatGroups(ObjectNode dataObjectNode) {

        ObjectNode objectNode = factory.objectNode();

        // check appKey format
        if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + APPKEY);

            objectNode.put("message", "Bad format of Appkey");

            return objectNode;
        }

        // check properties that must be provided
        if (!dataObjectNode.has("groupname")) {
            LOGGER.error("Property that named groupname must be provided .");

            objectNode.put("message", "Property that named groupname must be provided .");

            return objectNode;
        }
        if (!dataObjectNode.has("desc")) {
            LOGGER.error("Property that named desc must be provided .");

            objectNode.put("message", "Property that named desc must be provided .");

            return objectNode;
        }
        if (!dataObjectNode.has("public")) {
            LOGGER.error("Property that named public must be provided .");

            objectNode.put("message", "Property that named public must be provided .");

            return objectNode;
        }
        if (!dataObjectNode.has("approval")) {
            LOGGER.error("Property that named approval must be provided .");

            objectNode.put("message", "Property that named approval must be provided .");

            return objectNode;
        }
        if (!dataObjectNode.has("owner")) {
            LOGGER.error("Property that named owner must be provided .");

            objectNode.put("message", "Property that named owner must be provided .");

            return objectNode;
        }
        if (!dataObjectNode.has("members") || !dataObjectNode.path("members").isArray()) {
            LOGGER.error("Property that named members must be provided .");

            objectNode.put("message", "Property that named members must be provided .");

            return objectNode;
        }

        try {

            JerseyWebTarget webTarget = null;
            webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
                    .resolveTemplate("app_name", APPKEY.split("#")[1]);

            objectNode = JerseyUtils.sendRequest(webTarget, dataObjectNode, credential, HTTPMethod.METHOD_POST, null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 修改群组
     */
    public static ObjectNode changGourp(ObjectNode dataObjectNode, String gid) {
        ObjectNode objectNode = factory.objectNode();

        try {

            JerseyWebTarget webTarget = null;
            webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
                    .resolveTemplate("app_name", APPKEY.split("#")[1]);

            objectNode = JerseyUtils.sendRequest2(webTarget, dataObjectNode, credential, HTTPMethod.METHOD_PUT, null, gid);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }


    /**
     * 删除群组
     */
    public static ObjectNode deleteChatGroups(String chatgroupid) {
        ObjectNode objectNode = factory.objectNode();

        // check appKey format
        if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + APPKEY);

            objectNode.put("message", "Bad format of Appkey");

            return objectNode;
        }

        try {

            JerseyWebTarget webTarget = null;
            webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
                    .resolveTemplate("app_name", APPKEY.split("#")[1]).path(chatgroupid);

            objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 获取群组中的所有成员
     */
    public static ObjectNode getAllMemberssByGroupId(String chatgroupid) {
        ObjectNode objectNode = factory.objectNode();

        // check appKey format
        if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + APPKEY);

            objectNode.put("message", "Bad format of Appkey");

            return objectNode;
        }

        try {

            JerseyWebTarget webTarget = null;
            webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
                    .resolveTemplate("app_name", APPKEY.split("#")[1]).path(chatgroupid).path("users");

            objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 在群组中添加一个人
     */
    public static ObjectNode addUserToGroup(String chatgroupid, String userprimarykey) {

        ObjectNode objectNode = factory.objectNode();

        // check appKey format
        if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + APPKEY);

            objectNode.put("message", "Bad format of Appkey");

            return objectNode;
        }

        try {

            JerseyWebTarget webTarget = null;
            webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
                    .resolveTemplate("app_name", APPKEY.split("#")[1]).path(chatgroupid).path("users")
                    .path(userprimarykey);

            objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_POST, null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 在群组中减少一个人
     */
    public static ObjectNode deleteUserFromGroup(String chatgroupid, String userprimarykey) {
        ObjectNode objectNode = factory.objectNode();

        // check appKey format
        if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + APPKEY);

            objectNode.put("message", "Bad format of Appkey");

            return objectNode;
        }

        try {
            JerseyWebTarget webTarget = null;
            webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
                    .resolveTemplate("app_name", APPKEY.split("#")[1]).path(chatgroupid).path("users")
                    .path(userprimarykey);

            objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 获取一个用户参与的所有群组
     *
     * @param username
     * @return
     */
    public static ObjectNode getJoinedChatgroupsForIMUser(String username) {
        ObjectNode objectNode = factory.objectNode();
        // check appKey format
        if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + APPKEY);
            objectNode.put("message", "Bad format of Appkey");
            return objectNode;
        }
        if (StringUtils.isBlank(username.trim())) {
            LOGGER.error("Property that named username must be provided .");
            objectNode.put("message", "Property that named username must be provided .");
            return objectNode;
        }

        try {
            objectNode = JerseyUtils.sendRequest(EndPoints.USERS_TARGET.path(username).path("joined_chatgroups"), null,
                    credential, HTTPMethod.METHOD_GET, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 群组批量添加成员
     *
     * @param toAddBacthChatgroupid
     * @param usernames
     * @return
     */
    private static ObjectNode addUsersToGroupBatch(String toAddBacthChatgroupid, ObjectNode usernames) {
        ObjectNode objectNode = factory.objectNode();
        // check appKey format
        if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + APPKEY);
            objectNode.put("message", "Bad format of Appkey");
            return objectNode;
        }
        if (StringUtils.isBlank(toAddBacthChatgroupid.trim())) {
            LOGGER.error("Property that named toAddBacthChatgroupid must be provided .");
            objectNode.put("message", "Property that named toAddBacthChatgroupid must be provided .");
            return objectNode;
        }
        // check properties that must be provided
        if (null != usernames && !usernames.has("usernames")) {
            LOGGER.error("Property that named usernames must be provided .");
            objectNode.put("message", "Property that named usernames must be provided .");
            return objectNode;
        }

        try {
            objectNode = JerseyUtils.sendRequest(EndPoints.CHATGROUPS_TARGET.path(toAddBacthChatgroupid).path("users"), usernames,
                    credential, HTTPMethod.METHOD_POST, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 修改群组
     */
    public static void Chang(String gid, String grname, String desc, int max) {
        ObjectNode dataObjectNode = JsonNodeFactory.instance.objectNode();
        String groupid = gid;
        dataObjectNode.put("groupname", grname);
        dataObjectNode.put("description", desc);
        dataObjectNode.put("maxusers", max);
        ObjectNode chatgroupidsNode = changGourp(dataObjectNode, groupid);
    }

    //删除组成员
    public static void deleteGroupUser(String tel, String gid) {
        String delFromChatgroupid = gid;
        String toRemoveUsername = tel;
        ObjectNode deleteUserFromGroupNode = deleteUserFromGroup(delFromChatgroupid, toRemoveUsername);
    }

    /**
     * 删除群组
     */
    public static void deleteGroup(String gid) {
        String toDelChatgroupid = gid;
        ObjectNode deleteChatGroupNode = deleteChatGroups(toDelChatgroupid);
    }
}
