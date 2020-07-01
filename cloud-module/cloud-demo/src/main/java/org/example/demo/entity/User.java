package org.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.core.common.mp.base.BaseColumnEntity;

/**
 * <p>
 *
 * </p>
 *
 * @author phh
 * @since 2020-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("demo_user")
public class User extends BaseColumnEntity {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 密码
     */
    private String password;


}
