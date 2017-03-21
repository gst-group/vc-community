package com.wenjuan.interceptor;

import com.wenjuan.controller.AdminController;
import com.wenjuan.controller.WeiXinController;
import com.wenjuan.dao.AdminRecordMapper;
import com.wenjuan.model.AdminRecord;
import com.wenjuan.model.User;
import com.wenjuan.service.OrganizationGroupService;
import com.wenjuan.utils.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Properties;

public class AuthorityAdminInterceptor implements HandlerInterceptor {
    @Autowired
    @Qualifier("organizationGroupService")
    private OrganizationGroupService organizationGroupService;
    /**
     * 检测用户登录访问权限
     *
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        User user = (User) request.getSession().getAttribute(AdminController.SESSION_KEY_ADMIN_USER);
        // User user = User.getCurrentUser();
        logger.info("--------user-------"+user);
        response.setHeader("Access-Control-Allow-Origin","*");
        if (user == null || !user.isAdmin()) {
            String redirectURL = request.getRequestURI();
            String queryString = request.getQueryString();
            if (!StringUtils.isEmpty(queryString)) {
                redirectURL += "?" + queryString;
            }

            Properties prop =  new  Properties();
            InputStream ins = this.getClass().getResourceAsStream("../properties/MysqlDB.properties");

            String MainName="";
            try  {
                prop.load(ins);
                MainName = prop.getProperty( "mainPath" ).trim();
            }  catch  (IOException e) {
                e.printStackTrace();
            }
            response.sendRedirect(MainName+"/page/Login.jsp?redirectURL=" + URLEncoder.encode(redirectURL));
            return false;
        }
        return true;
    }
    Logger logger = Logger.getLogger(AuthorityAdminInterceptor.class);

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        try{
            User user = (User) request.getSession().getAttribute(AdminController.SESSION_KEY_ADMIN_USER);
            if (user.isAdmin()) {

                //管理员 权限4 仅保留话题模块  不包括发帖等
                if(user.getName().equals("@lkxy")||user.getName().equals("@lkxy2")){
                    modelAndView.addObject("rootNumber",4);
                }else{
                    modelAndView.addObject("UserURL",Base64.getBase64(organizationGroupService.getGroup(User.getCurrentUser().getId())));

                    //超级管理员1 普通管理员0
                    if(user.getRootType()==1)
                    modelAndView.addObject("rootNumber",1);
                    else
                    modelAndView.addObject("rootNumber",0);
                    //部分模块隐藏开启1隐藏 0不隐藏
                    modelAndView.addObject("visible",1);
                }
            }
        }catch (Exception e){

        }

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {


    }
}
