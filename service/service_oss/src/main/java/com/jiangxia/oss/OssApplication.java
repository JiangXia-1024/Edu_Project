package com.jiangxia.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author jiangxia
 * @date 2022年02月19日 20:11
 * desc:spring boot 会默认加载org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration这个类，
 * 而DataSourceAutoConfiguration类使用了@Configuration注解向spring注入了dataSource bean，又因为项目（oss模块）
 * 中并没有关于dataSource相关的配置信息，所以当spring创建dataSource bean时因缺少相关的信息就会报错。
 * 在@SpringBootApplication注解上加上exclude，解除自动加载DataSourceAutoConfiguration
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.jiangxia"})
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class,args);
    }
}
