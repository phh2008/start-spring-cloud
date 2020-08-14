package com.phh.entity;

import lombok.Data;

import java.util.Date;

/**
 * 描述
 *
 * @author huihui.peng
 * @version V1.0
 * @date 2020/8/14
 */
@Data
public class TableEntity {

    private String tableName;
    private String tableComment;
    private Date createTime;

}
