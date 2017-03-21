package com.wenjuan.utils;


import com.wenjuan.bean.MessageBean;
import com.wenjuan.controller.UploadController;
import com.wenjuan.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommonUtil {

    private static Logger logger = Logger.getLogger(CommonUtil.class);
    public static String getVideo(String pics) {
        if (pics == null) {
            return null;
        }
        int indexOfSeparator = pics.indexOf(';');
        if (indexOfSeparator == -1 || indexOfSeparator < 2) return null;
        return pics.substring(2, indexOfSeparator);
    }

    public static String[] getPictures(String pics) {
        String[] value = new String[0];
        if (pics == null) {
            return value;
        }
        int indexOfSeparator = pics.indexOf(';');
        String picsStr = "";
        if (indexOfSeparator == -1 || indexOfSeparator < 2) picsStr = pics;
        else picsStr = pics.substring(pics.indexOf(":", indexOfSeparator) + 1);
        if (picsStr.isEmpty()) return value;
        return picsStr.split(",");
    }

    public static String formatPics(String video, Object[] pics) {
        return formatPics(video, StringUtils.arrayToDelimitedString(pics, ","));
    }

    public static String formatPics(String video, String pics) {
        return String.format("v:%s;i:%s", video, pics);
    }

    /**
     * 将由英文封号分隔开的Base64字符串转换为路径数组
     *
     * @param base64 base64字符串，多张图片以封号分割全部是英文封号隔开的base64字符串
     * @return 两个键值分别为pics的图片List和warn的序号List
     */
    public static List<String> SplitBase64ArrayToPathArray(String base64, HttpServletRequest request, UserService userService) {
        List<String> pics = new ArrayList<>();
        if (!StringUtils.isEmpty(base64)) {
            String[] picStrings = base64.replaceAll(" ", "+").split(";");
            for (String picString : picStrings) {
                MessageBean currMessageBean = null;
                try {
                    currMessageBean = UploadController.uploadImage(new BASE64Decoder().decodeBuffer(picString), request, userService, 0);
                    if (currMessageBean.getCode() == MessageBean.SUCCESS.getCode()) {
                        pics.add(currMessageBean.getExtra().toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return pics;
    }

    public static String splitComparator(String load) {
        if (StringUtils.isEmpty(load)) {
            return null;
        }
        if ((load.startsWith("A") || load.startsWith("B")) && load.length() > 1) {
            return load.startsWith("A") ? ">" : "<";
        } else {
            return null;
        }
    }

    public static Integer splitInteger(String load) {
        String value = splitString(load);
        if (!StringUtils.isEmpty(value)) {
            try {
                return Integer.parseInt(value);
            } catch (RuntimeException ignored) {
            }
        }
        return null;
    }

    public static String splitString(String load) {
        String value = null;
        if (!StringUtils.isEmpty(load) && (load.startsWith("A") || load.startsWith("B")) && load.length() > 1) {
            value = load.substring(1);
        }
        return value;
    }

    public static String processLikeParameter(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return "%" + key + "%";
    }
}
