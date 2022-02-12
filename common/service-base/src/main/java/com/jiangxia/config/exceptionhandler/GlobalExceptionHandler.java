package com.jiangxia.config.exceptionhandler;

import com.jiangxia.commonutils.ExceptionUtil;
import com.jiangxia.commonutils.ResultData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangxia
 * @date 2022年02月12日 17:42
 */
//@ControllerAdvice，是Spring3.2提供的新注解,它是一个Controller增强器,
//可对controller中被 @RequestMapping注解的方法加一些逻辑处理。最常用的就
//是异常处理,需要配合@ExceptionHandler使用。
@ControllerAdvice
@Slf4j // 日志注解
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public ResultData error(Exception e){
        e.printStackTrace();
        return ResultData.error().message("执行了全局异常处理......");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public ResultData error(ArithmeticException e) {
        e.printStackTrace();
        return ResultData.error().message("执行了ArithmeticException异常处理..");
    }

    //自定义异常
    @ExceptionHandler(CustomizeException.class)
    @ResponseBody //为了返回数据
    public ResultData error(CustomizeException e) {
        //记录日志到日志文件中
//        log.error(e.getMessage());
////        e.printStackTrace();
//        =======================
        //以上信息只记录简单信息，这里对异常信息进行处理，写入到日志文件中
        log.error(ExceptionUtil.getMessage(e));
        return ResultData.error().code(e.getCode()).message(e.getMsg());
    }
}
