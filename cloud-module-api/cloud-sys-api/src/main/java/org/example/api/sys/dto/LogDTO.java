package org.example.api.sys.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/6
 */
@Data
public class LogDTO implements Serializable {

    private static final long serialVersionUID = 8133245282382470495L;

    private Long id;

    private Boolean logType;

    private String logContent;

    private Boolean operateType;

    private Long userId;

    private String username;

    private String ip;

    private String method;

    private String requestUrl;

    private String requestParam;

    private String requestType;

    private Long costTime;

    private Long createBy;

    private LocalDateTime createdAt;

    private Long updateBy;

    private LocalDateTime updatedAt;

    private Integer delFlag;

}
