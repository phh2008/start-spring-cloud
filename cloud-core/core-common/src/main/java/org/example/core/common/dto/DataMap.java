package org.example.core.common.dto;

import java.util.HashMap;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/9
 */
public class DataMap<K, V> extends HashMap<K, V> {

    /**
     * new DataMap<?,?>
     *
     * @return
     */
    public static DataMap<Object, Object> build() {
        return new DataMap<>();
    }

    /**
     * put(key,value)
     *
     * @param key   键
     * @param value 值
     * @return self
     */
    public DataMap<K, V> set(K key, V value) {
        super.put(key, value);
        return this;
    }


}
