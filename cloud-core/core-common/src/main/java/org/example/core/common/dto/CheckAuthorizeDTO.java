package org.example.core.common.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 校验权限
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/29
 */
@Data
@ToString
public class CheckAuthorizeDTO implements Serializable {

    private static final long serialVersionUID = -8123984944900394428L;

    private Long userId;

    private List<String> roles;

    private List<String> permits;

}
