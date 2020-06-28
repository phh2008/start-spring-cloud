package org.example.core.tool.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * java，android，ios通用AES加解密
 * Created by phh on 2017/7/20.
 *
 * @author phh
 */
public class AesUtils {

    private static final Logger logger = LoggerFactory.getLogger(AesUtils.class);

    /**
     * 密钥长度:128
     */
    private static final int KEY_SIZE = 128;
    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "AES";
    /**
     * "算法/模式/补码方式
     */
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    /**
     * 向量值，可修改，需要与app端相同
     */
    private static final String IV_PARAMETER = "vx9sNtLGhR5McqUC";

    /**
     * 加密
     *
     * @param src         待加密的文本
     * @param key         密钥
     * @param ivParameter 向量
     * @return string
     * @throws RuntimeException if error
     * @author phh
     * @date 2020/6/24
     * @version V1.0
     */
    public static String encrypt(String src, String key, String ivParameter) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            byte[] raw = key.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
            // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(src.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            logger.error("加密失败", e);
            throw new RuntimeException("加密异常");
        }
    }

    public static String encrypt(String src, String key) {
        return encrypt(src, key, IV_PARAMETER);
    }

    /**
     * 解密
     *
     * @param src         待解密文本
     * @param key         密钥
     * @param ivParameter 向量
     * @return string
     * @throws RuntimeException if error
     * @author phh
     * @date 2020/6/24
     * @version V1.0
     */
    public static String decrypt(String src, String key, String ivParameter) {
        try {
            byte[] raw = key.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.getDecoder().decode(src);
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("解密失败", e);
            throw new RuntimeException("密文无效");
        }
    }

    public static String decrypt(String src, String key) {
        return decrypt(src, key, IV_PARAMETER);
    }


}
