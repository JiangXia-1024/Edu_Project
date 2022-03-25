package com.jiangxia.commonutils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jiangxia
 * @date 2022年03月24日 17:35
 * desc:请求结果处理工具类
 */
public class ResponseUtil {
    public static void out(HttpServletResponse response,ResultData resultData){
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            mapper.writeValue(response.getWriter(),resultData);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
