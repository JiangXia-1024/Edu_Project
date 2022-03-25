package com.jiangxia.security.security;

import io.jsonwebtoken.CompressionCodec;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author jiangxia
 * @date 2022年03月25日 16:40
 * desc:token管理
 */
@Component
public class TokenManager {
    private long tokenExpiration = 24*60*60*1000; //token有效期
    private String tokenSignKey = "123456";

    /**
     * @description: 生成token
     * @param: userName
     * @return:  token
     * @author:江夏
     * @date: 2022/3/25 16:50
     */
    public String createToken(String userName){
        String token = Jwts.builder().setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis()+tokenExpiration))
                .signWith(SignatureAlgorithm.HS512,tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    /** 
     * @description: token中获取用户信息
     * @param: null 
     * @return:  
     * @author:江夏
     * @date: 2022/3/25 16:51
     */ 
    public String getUserFromToken(String token){
        String user = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
        return user;
    }

    /**
     * @author:江夏
     * @date 2022/3/25 17:11
     * @version 1.0
     * @description: 这里应该直接关闭浏览器 就清空了
     */
    public void removeToken(String token) {
    }
}
