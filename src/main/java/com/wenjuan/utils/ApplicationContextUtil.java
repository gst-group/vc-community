package com.wenjuan.utils;

import com.wenjuan.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 操作ApplicationContext的工作类
 */
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName, Class<T> cls) {
        return (T) applicationContext.getBean(beanName);
    }

    public static UserUtil getUserUtil() {
        return getBean("userUtil", UserUtil.class);
    }

    public static UserService getUserService() {
        return getBean("userService", UserService.class);
    }
}
