package com.devteam.digital.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;
    private static final List<CallBack> CALL_BACKS = new ArrayList<>();
    private static boolean addCallback = true;

    /**
     * For some initialization methods, submit the callback method when the SpringContextHolder is not initialized.
     * After the SpringContextHolder is initialized, callback is used
     *
     * @param callBack Callback
     */
    public synchronized static void addCallBacks(CallBack callBack) {
        if (addCallback) {
            SpringContextHolder.CALL_BACKS.add(callBack);
        } else {
            log.warn("CallBack：{} Can't add! Execute immediately", callBack.getCallBackName());
            callBack.executor();
        }
    }

    /**
     * Obtain the Bean from the static variable applicationContext and automatically convert it to the type of the assigned object.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * Obtain the Bean from the static variable applicationContext and automatically convert it to the type of the assigned object.
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     * Get SpringBoot configuration information
     *
     * @param property
     * @param defaultValue
     * @param requiredType
     * @return /
     */
    public static <T> T getProperties(String property, T defaultValue, Class<T> requiredType) {
        T result = defaultValue;
        try {
            result = getBean(Environment.class).getProperty(property, requiredType);
        } catch (Exception ignored) {}
        return result;
    }

    /**
     * Get SpringBoot configuration information
     *
     * @param property
     * @return /
     */
    public static String getProperties(String property) {
        return getProperties(property, null, String.class);
    }

    /**
     *Get SpringBoot configuration information
     *
     * @param property
     * @param requiredType
     * @return /
     */
    public static <T> T getProperties(String property, Class<T> requiredType) {
        return getProperties(property, null, requiredType);
    }

    /**
     * Check that the ApplicationContext is not empty.
     */
    private static void assertContextInjected() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext Property is not injected, please applicationContext" +
                    ".xml Define SpringContextHolder in or register in SpringBoot startup classSpringContextHolder.");
        }
    }

    /**
     * SpringContextHolder ApplicationContext Null.
     */
    private static void clearHolder() {
        log.debug("SpringContextHolder ApplicationContext:"
                + applicationContext);
        applicationContext = null;
    }

    @Override
    public void destroy() {
        SpringContextHolder.clearHolder();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextHolder.applicationContext != null) {
            log.warn("SpringContextHolder The ApplicationContext in is overwritten, and the original ApplicationContext is:" + SpringContextHolder.applicationContext);
        }
        SpringContextHolder.applicationContext = applicationContext;
        if (addCallback) {
            for (CallBack callBack : SpringContextHolder.CALL_BACKS) {
                callBack.executor();
            }
            CALL_BACKS.clear();
        }
        SpringContextHolder.addCallback = false;
    }
}
