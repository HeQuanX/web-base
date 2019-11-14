package cn.crabapples.utils;

import cn.crabapples.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES加密字符串
 * @author wishforyou.xia@gmail.com
 * @date 2019/7/3 23:24
 */
public class AesStringUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(AesStringUtils.class);

    /**
     * 用于将密钥种子转换为KEY
     * @param seed 密钥种子
     * @return 密钥
     * @throws Exception 生成密钥可能出现的异常
     */
    private static Key createKey(String seed) throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128,new SecureRandom(seed.getBytes()));
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }

    /**
     *
     * @param keyString 密钥
     * @param string 需要加解密的字符串
     * @param type 需要执行的操作(加/解密)
     * @return 输出的字符串
     * @throws Exception 运行过程中可能出现的异常
     */
    public static String doFinal(String keyString, String string, int type) throws Exception {
        try {
            /**
             * 初始化加密方式
             */
            Cipher cipher = Cipher.getInstance("AES");
            /**
             * 当操作类型为加密时
             */
            if (type == Cipher.ENCRYPT_MODE) {
                /**
                 * 初始化Cipher为加密
                 */
                cipher.init(Cipher.ENCRYPT_MODE, createKey(keyString));
                byte[] dataByte = cipher.doFinal(string.getBytes());
                byte[] encodeByte = Base64.getEncoder().encode(dataByte);
                String data = new String(encodeByte);
                return data;
            } else if (type == Cipher.DECRYPT_MODE) {
                /**
                 * 初始化Cipher为解密
                 */
                cipher.init(Cipher.DECRYPT_MODE, createKey(keyString));
                byte[] decodeByte = Base64.getDecoder().decode(string.getBytes());
                byte[] dataByte = cipher.doFinal(decodeByte);
                String data = new String(dataByte);
                return data;
            }else{
                /**
                 * 当输入的类型不匹配加/解密时抛出异常
                 */
                throw new ApplicationException("please input type");
            }
        }catch (Exception e){
            LOGGER.error("出现错误:[{}]",e.getMessage());
            throw e;
        }
    }
}