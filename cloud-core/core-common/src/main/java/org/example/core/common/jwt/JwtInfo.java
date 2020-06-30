package org.example.core.common.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * jwt 信息实现类
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/24
 */
public class JwtInfo implements Serializable, IJwtInfo {

    private String id;
    private String userName;
    private String realName;
    private Date expireTime;

    public JwtInfo() {
    }

    public JwtInfo(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public JwtInfo(String id, String userName, String realName) {
        this.id = id;
        this.userName = userName;
        this.realName = realName;
    }

    public JwtInfo(String id, String userName, String realName, Date expireTime) {
        this.id = id;
        this.userName = userName;
        this.realName = realName;
        this.expireTime = expireTime;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public Date getExpireTime() {
        return expireTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        JwtInfo jwtInfo = (JwtInfo) o;
        if (!Objects.equals(this.id, jwtInfo.id)) {
            return false;
        }
        return Objects.equals(userName, jwtInfo.userName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }

}
