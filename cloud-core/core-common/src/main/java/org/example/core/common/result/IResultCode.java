package org.example.core.common.result;

import java.io.Serializable;

/**
 * 返回码接口
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/24
 */
public interface IResultCode extends Serializable {

    /**
     * 消息
     *
     * @return String
     */
    String getMsg();

    /**
     * 状态码
     *
     * @return int
     */
    String getCode();

}
