package com.system.common.utils;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

public class SHA256Utils {

    protected static final Logger LOG = LoggerFactory.getLogger(SHA256Utils.class);

    /***
     * 利用Apache的工具类实现SHA-256加密
     *
     * @param str 加密后的报文
     * @return
     */
    public static String sha256(String str) {
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (Exception e) {
            LOG.error("getSHA256Str has error:", e);
            return str;
        }
        return encdeStr;
    }

    /**
     * 密码加盐加密
     *
     * @param password
     * @param salt
     * @return
     * @author guanliangyou515
     */
    public static String encryptPassword(String password, String salt) {

        return sha256(sha256(password) + salt);
    }
}