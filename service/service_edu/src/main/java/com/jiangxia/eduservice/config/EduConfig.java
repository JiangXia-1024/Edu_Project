package com.jiangxia.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.mapstruct.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiangxia
 * @date 2022年02月04日 22:35
 */
@Configuration
@MapperScan("com.jiangxia.eduservice.mapper")
public class EduConfig {

    //逻辑删除组件
    @Bean
    public ISqlInjector iSqlInjector(){
        return new LogicSqlInjector();
    }
}
