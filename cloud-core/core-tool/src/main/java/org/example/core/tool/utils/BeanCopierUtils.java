package org.example.core.tool.utils;


import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanMap;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 高性能bean属性复制与map之间的转换工具
 * 如需使用与扩展，请阅读注意事项.
 * <p>
 * 作用：beanCopy，beanToMap，mapToBean.
 * 实现：采用cglib工具包实现高性的bean属性复制、bean与map之间的转换.
 * <p>
 * 《注意事项》：
 * 1):bean属性复制必需是同名同类型才会复制.
 * 2):map转换为bean必需要求map与bean属性数据类型一致，bean转换为map无要求.
 * 3):因为使用Objenesis，目标Class可不提供构造.
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/24
 */
public class BeanCopierUtils {

    /**
     * BeanCopier 对象缓存
     */
    private static final ConcurrentHashMap<String, BeanCopier> CACHE = new ConcurrentHashMap<>();

    /**
     * 可绕过构造创建对象
     */
    private static final Objenesis OBJENESIS = new ObjenesisStd(true);

    /**
     * 复制对象属性（类型、名称一致才会复制）
     *
     * @param src 源对象
     * @param des 目标对象
     * @param <S>
     * @param <D>
     * @return null if src==null or des==null
     */
    public static <S, D> D copy(S src, D des) {
        if (src == null || des == null) {
            return null;
        }
        BeanCopier copier = getCopier(src.getClass(), des.getClass());
        copier.copy(src, des, null);
        return des;
    }

    /**
     * 复制对象（类型、名称一致才会复制）
     *
     * @param src 源对象
     * @param des 目标Class
     * @param <S>
     * @param <D>
     * @return null if src==null or des==null
     */
    public static <S, D> D copy(S src, Class<D> des) {
        if (src == null || des == null) {
            return null;
        }
        BeanCopier copier = getCopier(src.getClass(), des);
        D target = OBJENESIS.newInstance(des);
        copier.copy(src, target, null);
        return target;
    }

    /**
     * 复制集合对象
     *
     * @param srcList 源对象集合
     * @param des     目标Class
     * @param <S>
     * @param <D>
     * @return emptyList if srcList is null or size=0
     */
    public static <S, D> List<D> copyList(List<S> srcList, Class<D> des) {
        if (srcList == null || srcList.size() == 0) {
            return new ArrayList<>(0);
        }
        BeanCopier copier = getCopier(srcList.get(0).getClass(), des);
        List<D> desList = new ArrayList<>(srcList.size());
        for (S s : srcList) {
            D target = OBJENESIS.newInstance(des);
            copier.copy(s, target, null);
            desList.add(target);
        }
        return desList;
    }

    private static BeanCopier getCopier(Class<?> src, Class<?> des) {
        String key = getCacheKey(src, des);
        return CACHE.computeIfAbsent(key, k -> BeanCopier.create(src, des, false));
    }

    private static String getCacheKey(Class<?> src, Class<?> des) {
        return src.toString() + des.toString();
    }


    /**
     * bean -> map
     *
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        if (bean == null) {
            return new HashMap<>(0);
        }
        BeanMap beanMap = BeanMap.create(bean);
        Set keys = beanMap.keySet();
        Map<String, Object> map = new HashMap<>(keys.size());
        for (Object key : keys) {
            map.put(key.toString(), beanMap.get(key));
        }
        return map;
    }

    /**
     * bean list -> map list
     *
     * @param beanList
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, Object>> beansToMap(List<T> beanList) {
        if (beanList == null || beanList.size() == 0) {
            return new ArrayList<>(0);
        }
        List<Map<String, Object>> mapList = new ArrayList<>(beanList.size());
        for (T bean : beanList) {
            mapList.add(beanToMap(bean));
        }
        return mapList;
    }

    /**
     * map转换为bean
     * 要求map与bean数据类型一致
     *
     * @param map
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Map<String, ?> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * map转换为bean
     * 要求map与bean数据类型一致
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        T bean = OBJENESIS.newInstance(clazz);
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * map list转换为bean list
     * 要求map与bean数据类型一致
     *
     * @param mapList
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> mapToBeans(List<Map<String, Object>> mapList, Class<T> clazz) {
        if (mapList == null || mapList.size() == 0) {
            return new ArrayList<>(0);
        }
        List<T> beanList = new ArrayList<>(mapList.size());
        for (Map<String, Object> map : mapList) {
            beanList.add(mapToBean(map, clazz));
        }
        return beanList;
    }


}
