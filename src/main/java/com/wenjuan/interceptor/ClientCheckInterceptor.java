package com.wenjuan.interceptor;

import com.wenjuan.controller.AdminController;
import com.wenjuan.dao.AdminRecordMapper;
import com.wenjuan.model.AdminRecord;
import com.wenjuan.model.User;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ClientCheckInterceptor implements HandlerInterceptor {
    private List<String> validOrder = new ArrayList<String>() {{
        add("desc");
        add("asc");
    }};

    @Resource
    private AdminRecordMapper adminRecordMapper;

    /**
     * 检测客户端权限,每次访问均有一个名为token的请求参数
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String order = (String) request.getAttribute("order");
        if (!StringUtils.isEmpty(order)) {
            if (order.contains(" ")) {
                String[] splitOrder = order.split(" ");
                if (splitOrder.length > 2 || !validOrder.contains(splitOrder[1])) {
                    return false;
                }
            } else {
                return false;
            }
        }
        response.setHeader("Access-Control-Allow-Origin","*");
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        Object description = request.getSession().getAttribute(AdminController.SESSION_KEY_OPERATOR_RECORD);
        request.getSession().removeAttribute(AdminController.SESSION_KEY_OPERATOR_RECORD);
        if (!StringUtils.isEmpty(description)) {
            User user = User.getCurrentUser();
            AdminRecord adminRecord = new AdminRecord();
            adminRecord.setUserId(user.getId());
            adminRecord.setDescription(description.toString());
            adminRecordMapper.insert(adminRecord);
        }

    }
}
