package com.jiangxia.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author jiangxia
 * @date 2022年02月04日 22:32
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.jiangxia"}) //组件扫描，扫描前缀是com.jiangxia的包
public class EduServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduServiceApplication.class,args);
    }
}
