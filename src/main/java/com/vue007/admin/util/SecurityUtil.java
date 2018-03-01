package com.vue007.admin.util;

import com.vue007.admin.domain.shiro.ShiroConfiguration;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created by akai on 2017/7/4.
 */
public class SecurityUtil {
    public static String encryptPassword(String password){
        String str = null;
        str = new SimpleHash(ShiroConfiguration.PASSWORD_ENCRYPT, password, null, ShiroConfiguration.PASSWORD_ENCRYPT_TIMES).toString();
        return str;
    }

    /**
     * 来源于 上个版本
     * @param passwordStr
     * @return
     */
    public static String encryptStationPassword(String passwordStr){
        String str = null;
        try {
            str = MD5.getInstance().getHexMD5(passwordStr);
        } catch (Exception e){
            throw new RuntimeException("密码MD5加密出错..."+e.getMessage());
        }
        return str;
    }
}
