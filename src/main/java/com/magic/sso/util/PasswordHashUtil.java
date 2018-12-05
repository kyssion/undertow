package com.magic.sso.util;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;

public class PasswordHashUtil {

    /**
     * 将用户密码使用MD5进行加密
     * @param password
     * @return
     */
    public static String passwordToHash(String password){
        return DigestUtils.sha256Hex(password);
    }

}
