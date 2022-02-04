package com.jiangxia.eduservice.config;

import org.mapstruct.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiangxia
 * @date 2022年02月04日 22:35
 */
@Configuration
@MapperScan("com.jiangxia.eduservice.mapper")
public class EduConfig {

}
