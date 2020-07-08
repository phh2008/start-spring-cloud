package org.example.core.authc.component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.example.core.authc.config.UserAuthConfig;
import org.example.core.common.exception.CloudException;
import org.example.core.common.jwt.IJwtInfo;
import org.example.core.common.jwt.JwtHelper;
import org.example.core.common.result.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/28
 */
@Slf4j
@Component
public class UserAuthHelper {

    @Autowired
    private UserAuthConfig userAuthConfig;

    public IJwtInfo getInfoFromToken(String token) {
        try {
            return JwtHelper.getInfoByToken(token, userAuthConfig.getPubKeyByte());
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            log.info("令牌无效：" + ex.getMessage(), ex);
            throw new CloudException(ResultCodeEnum.UNAUTHENTICATED);
        } catch (ExpiredJwtException ex) {
            log.info("令牌已过期：" + ex.getMessage());
            throw new CloudException(ResultCodeEnum.AUTH_EXPIRED);
        } catch (Exception ex) {
            log.info("令牌验证失败：" + ex.getMessage());
            throw new CloudException(ResultCodeEnum.BIZ_ERROR.getCode(), "令牌验证失败");
        }
    }

}
