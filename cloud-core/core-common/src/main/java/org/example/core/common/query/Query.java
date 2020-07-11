package org.example.core.common.query;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询对象基类
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/11
 */
@Data
public class Query implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页数
     */
    private Long pageNo;

    /**
     * 每页数量
     */
    private Long pageSize;

}
