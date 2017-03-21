package com.wenjuan.utils;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wowtour.jersey.api.EasemobChatMessage;
import com.wowtour.jersey.comm.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO Y 历史记录同步
 */
public class SyncHistoryUtil {
    private static final String APPKEY = Constants.APPKEY;
    /**
     * 获取十天的历史信息
     */
    private static Logger LOGGER = LoggerFactory
            .getLogger(EasemobChatMessage.class);
    private static JsonNodeFactory factory = new JsonNodeFactory(false);

    public static ObjectNode sync() {
        String currentTimestamp = String.valueOf(System.currentTimeMillis());
        String senvenDayAgo = String.valueOf(System.currentTimeMillis() - 10
                * 24 * 60 * 60 * 1000);
        ObjectNode queryStrNode1 = factory.objectNode();
        queryStrNode1.put("ql", "select * where  timestamp > " + senvenDayAgo
                + " and timestamp < " + currentTimestamp);
        ObjectNode messages1 = EasemobChatMessage
                .getChatMessages(queryStrNode1);
        return messages1;
    }

}
