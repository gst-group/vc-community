package com.wenjuan.utils;

/**
 * 常用检测工具类
 */
public class CheckUtil {
    public static boolean checkPassword(String password) {
        String reg = "^[a-zA-Z0-9_ ]\\w{5,17}$";
        return password.matches(reg);
    }

    public static boolean checkName(String name) {
        return !name.isEmpty();
    }

    /***
     * 校验排序字段合法性
     *
     * @return
     */
    public static boolean checkOrderField(String order) {
        if (order == null) {
            return false;
        }
        order = order.trim();
        if (order.isEmpty() || !order.contains(" ") || order.matches("[0-9]")) {
            return false;
        }
        String[] fields = order.split(" ");
        if (fields.length != 2) {
            return false;
        }
        if (fields[1].equals("asc") || fields[1].equals("desc")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param loadId 字符A或B开头，后面跟上id，分别表示After和Before
     * @return
     */
    public static boolean checkLoadId(String loadId) {
        if (loadId == null) {
            return false;
        }
        loadId = loadId.trim();
        if (loadId.isEmpty()) {
            return false;
        }
        if (loadId.startsWith("A") || loadId.startsWith("B")) {
            try {
                Integer.parseInt(loadId.substring(1));
            } catch (Exception e) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否为合法的电话号码
     *
     * @param tel
     * @return
     */
    public static boolean isLegalTel(String tel) {
        return tel != null && tel.matches("^((\\+86)|(86))?1\\d{10}$");
    }
}

