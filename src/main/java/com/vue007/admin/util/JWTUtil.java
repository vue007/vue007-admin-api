package com.vue007.admin.util;


import com.vue007.admin.model.system.Admin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Strings;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;
import java.util.Map;

/**
 * JTW 工具类
 *
 * @author Xiangyang
 * @date 2017年11月3日
 */
public class JWTUtil {

    public static final String SALT = "0f35e545ba4fd904f9d14d967db46ee7";

    public static final char SEPARATOR_CHAR = '.';

    public static String creatCompact(String secretKey, Map<String, Object> claims) {
        if (StringUtils.isBlank(secretKey)) {
            throw new IllegalArgumentException("SecretKey is NULL");
        }

        return Jwts.builder()
                .setClaims(claims)
                //令牌有效期
                .setExpiration(DateUtils.addDays(new Date(), 1))
                //密钥
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public static String parseBase64UrlEncodedPayload(String accessToken) {
        String base64UrlEncodedHeader = null;
        String base64UrlEncodedPayload = null;
        int delimiterCount = 0;
        StringBuilder sb = new StringBuilder(128);
        for (char c : accessToken.toCharArray()) {
            if (c == SEPARATOR_CHAR) {
                CharSequence tokenSeq = Strings.clean(sb);
                String token = tokenSeq != null ? tokenSeq.toString() : null;
                if (delimiterCount == 0) {
                    base64UrlEncodedHeader = token;
                } else if (delimiterCount == 1) {
                    base64UrlEncodedPayload = token;
                }
                delimiterCount++;
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }

        //base64解码，获取payload
        org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
        byte[] decode = base64.decode(base64UrlEncodedPayload);
        return new String(decode);
    }

    public static String getKey(Admin admin) {
        // TODO: 密钥算法优化
        return admin.getPassword() + SALT + admin.getUsername();
    }

}
