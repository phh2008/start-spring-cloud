package org.example.core.tool.utils;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * 资源国际化
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/10
 */
public class MessageSourceUtils {

    private static MessageSource messageSource;

    public MessageSourceUtils(MessageSource messageSource) {
        MessageSourceUtils.messageSource = messageSource;
    }

    /**
     * 获取资源消息
     *
     * @param code           编码
     * @param args           格式化参数
     * @param defaultMessage 默认值
     * @param locale         地区
     * @return string or null
     * @author phh
     * @date 2020/7/10
     */
    public static String getMsg(String code, String defaultMessage, Locale locale, Object[] args) {
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }

    /**
     * 获取资源消息
     * Locale：Locale.getDefault()
     *
     * @param code           编码
     * @param args           格式化参数
     * @param defaultMessage 默认值
     * @return string
     * @author phh
     * @date 2020/7/10
     */
    public static String getMsg(String code, String defaultMessage, Object[] args) {
        return messageSource.getMessage(code, args, defaultMessage, Locale.getDefault());
    }

    /**
     * 获取资源消息
     *
     * @param code   编码
     * @param args   格式化参数
     * @param locale 地区
     * @return string or null
     * @author phh
     * @date 2020/7/10
     */
    public static String getMsg(String code, Locale locale, Object[] args) {
        return messageSource.getMessage(code, args, null, locale);
    }

    public static String getMsg(String code, Object[] args) {
        return messageSource.getMessage(code, args, null, Locale.getDefault());
    }


    public static String getMsg(String code, Locale locale, String defaultMessage) {
        return messageSource.getMessage(code, null, defaultMessage, locale);
    }


    public static String getMsg(String code, String defaultMessage) {
        return messageSource.getMessage(code, null, defaultMessage, Locale.getDefault());
    }

    public static String getMsg(String code, Locale locale) {
        return messageSource.getMessage(code, null, null, locale);
    }


    public static String getMsg(String code) {
        return messageSource.getMessage(code, null, null, Locale.getDefault());
    }


}
