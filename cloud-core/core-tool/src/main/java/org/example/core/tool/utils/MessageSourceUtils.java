package org.example.core.tool.utils;

import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;

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
    public static String getMsg(String code, @Nullable Object[] args, @Nullable String defaultMessage, Locale locale) {
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
    public static String getMsg(String code, @Nullable Object[] args, @Nullable String defaultMessage) {
        return getMsg(code, args, defaultMessage, Locale.getDefault());
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
    public static String getMsg(String code, @Nullable Object[] args, Locale locale) {
        return messageSource.getMessage(code, args, null, locale);
    }


    public static String getMsg(String code, @Nullable String defaultMessage) {
        return getMsg(code, null, defaultMessage, Locale.getDefault());
    }


    public static String getMsg(String code) {
        return getMsg(code, null, Locale.getDefault());
    }


}
