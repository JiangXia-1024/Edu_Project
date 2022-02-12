package com.jiangxia.config.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jiangxia
 * @date 2022/2/12 19:35
 * @param:No such property: code for class: Script1
 * @return: No such property: code for class: Script1
 * @description：自定义异常类
 */
@Data
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor   //生成无参数构造
public class CustomizeException extends RuntimeException {
    private Integer code;//状态码
    private String msg;//异常信息
}
