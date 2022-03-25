package com.jiangxia.security.security;

import com.jiangxia.commonutils.ResponseUtil;
import com.jiangxia.commonutils.ResultData;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jiangxia
 * @date 2022年03月25日 17:03
 * desc:未授权的统一处理方式
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseUtil.out(httpServletResponse, ResultData.error());
    }
}
