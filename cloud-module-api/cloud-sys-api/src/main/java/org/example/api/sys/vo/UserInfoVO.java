package org.example.api.sys.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/30
 */
@Data
@ToString
public class UserInfoVO implements Serializable {

    private static final long serialVersionUID = 5623845587472002053L;

    private Long userId;

    private String username;

    private String realName;

    private String token;

}
