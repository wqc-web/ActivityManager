package com.zhongzhou.common.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JwtUtil
 * @Description JWT
 * @Date 2020/4/17 17:19
 * @Author wj
 */
@Slf4j
public class JwtUtil {
    public static final long EXPIRATION_TIME = 2592_000_000L; // 有效期30天
    public static final String SECRET = "zz@jszzkj.cn";
    public static final String HEADER = "Authorization";
    public static final String USER_ID = "userId";

    /**
     * 根据userId生成token
     * @param userId
     * @return
     */
    public static String generateToken(String userId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(USER_ID, userId);
        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return jwt;
    }

    /**
     * 验证token
     * @param request
     * @return 验证通过返回userId
     */
    public static Long verifyToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER);
        if (token != null) {
            try {
                Map<String, Object> body = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token)
                        .getBody();

                for (Map.Entry entry : body.entrySet()) {
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    if (key.toString().equals(USER_ID)) {
                        return Long.parseLong(value.toString());// userId
                    }
                }
                return null;
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException("unauthorized");
            }
        } else {
            throw new RuntimeException("missing token");
        }
    }
}
