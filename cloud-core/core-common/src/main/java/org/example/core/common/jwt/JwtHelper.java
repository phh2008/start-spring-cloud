package org.example.core.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.core.common.constant.TokenConst;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

/**
 * Jwt 工具类
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/24
 */
public class JwtHelper {

    private static RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();

    private static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 密钥加密token
     *
     * @param jwtInfo
     * @param priKeyPath
     * @param expireSecond 过期时间（单位秒）
     * @return
     * @throws Exception if error
     */
    public static String createToken(IJwtInfo jwtInfo, String priKeyPath, int expireSecond) throws Exception {

        return Jwts.builder()
                .setSubject(jwtInfo.getId())
                .claim(TokenConst.JWT_KEY_USERNAME, jwtInfo.getUserName())
                .claim(TokenConst.JWT_KEY_REALNAME, jwtInfo.getRealName())
                .setExpiration(toDate(LocalDateTime.now().plusSeconds(expireSecond)))
                .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKeyPath))
                .compact();
    }

    /**
     * 密钥加密token
     *
     * @param jwtInfo
     * @param priKey
     * @param expireSecond 过期时间（单位秒）
     * @return
     * @throws Exception if error
     */
    public static String createToken(IJwtInfo jwtInfo, byte priKey[], int expireSecond) {
        return Jwts.builder()
                .setSubject(jwtInfo.getId())
                .claim(TokenConst.JWT_KEY_USERNAME, jwtInfo.getUserName())
                .claim(TokenConst.JWT_KEY_REALNAME, jwtInfo.getRealName())
                .setExpiration(toDate(LocalDateTime.now().plusSeconds(expireSecond)))
                .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKey))
                .compact();
    }

    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception if error
     */
    public static Jws<Claims> parserToken(String token, String pubKeyPath) throws Exception {
        return Jwts.parser()
                .setSigningKey(rsaKeyHelper.getPublicKey(pubKeyPath))
                .parseClaimsJws(token);
    }

    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token, byte[] pubKey) throws Exception {
        return Jwts.parser()
                .setSigningKey(rsaKeyHelper.getPublicKey(pubKey))
                .parseClaimsJws(token);
    }

    /**
     * 获取token中的用户信息
     *
     * @param token
     * @param pubKeyPath
     * @return
     * @throws Exception if error
     */
    public static IJwtInfo getInfoByToken(String token, String pubKeyPath) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, pubKeyPath);
        Claims body = claimsJws.getBody();
        return new JwtInfo(body.getSubject(),
                Objects.toString(body.get(TokenConst.JWT_KEY_USERNAME)),
                Objects.toString(body.get(TokenConst.JWT_KEY_REALNAME)),
                body.getExpiration());
    }

    /**
     * 获取token中的用户信息
     *
     * @param token
     * @param pubKey
     * @return
     * @throws Exception if error
     */
    public static IJwtInfo getInfoByToken(String token, byte[] pubKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, pubKey);
        Claims body = claimsJws.getBody();
        return new JwtInfo(body.getSubject(),
                Objects.toString(body.get(TokenConst.JWT_KEY_USERNAME)),
                Objects.toString(body.get(TokenConst.JWT_KEY_REALNAME)),
                body.getExpiration());
    }

}
