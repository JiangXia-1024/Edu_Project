package com.jiangxia.security.security;

import com.jiangxia.commonutils.ResponseUtil;
import com.jiangxia.commonutils.ResultData;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jiangxia
 * @date 2022年03月25日 17:03
 * desc:登出业务逻辑类
 */
public class TokenLogoutHandler implements LogoutHandler {
    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenLogoutHandler(TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    /** 
     * @description: 实现方法
     * @param: httpServletRequest
httpServletResponse
authentication 
     * @return:  
     * @author:江夏
     * @date: 2022/3/25 17:10
     */ 
    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        String token = httpServletRequest.getHeader("token");
        if(token!=null){
            tokenManager.removeToken(token);
            //清空redis中当前用户的权限数据
            String userName = tokenManager.getUserFromToken(token);
            redisTemplate.delete(userName);
        }
        ResponseUtil.out(httpServletResponse, ResultData.ok());
    }
}
