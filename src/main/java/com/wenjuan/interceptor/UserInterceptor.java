package com.wenjuan.interceptor;

import com.wenjuan.controller.AdminController;
import com.wenjuan.model.User;
import com.wenjuan.service.UserService;
import com.wenjuan.utils.ApplicationContextUtil;
import com.wenjuan.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    @Qualifier("userService")
    UserService userService;

    /**
     * 验证客户端端用户身份
     * 并将其注入到Bean名为userUtil的工具类中
     * 所有请求除注册、登录等外均需要传递一token，登录过后会由服务器传给客户端
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        UserUtil userUtil = ApplicationContextUtil.getUserUtil();
        HttpSession session = request.getSession();
        Object userObj = session.getAttribute(AdminController.SESSION_KEY_ADMIN_USER);
        if (userObj == null) {
            String token = request.getParameter("token");
            if (token != null) {
                userUtil.setUser(userService.selectByToken(token));
            }
        } else {
            userUtil.setUser((User) userObj);
        }

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
