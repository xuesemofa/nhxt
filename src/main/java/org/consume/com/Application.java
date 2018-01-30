package org.consume.com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring Boot 应用启动类
 * <p>
 * Created by bysocket on 16/4/26.
 */
@SpringBootApplication
@MapperScan("org.consume.com.*.mapper")
//定时器
@EnableScheduling
//多数据源
//@Import({DynamicDataSourceRegister.class}) // 注册动态多数据源
public class Application {
//        extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    //设置ajax跨域请求
//    @Bean
//    public WebMvcConfigurer corsConfigurer(){
//        return new WebMvcConfigurerAdapter(){
//
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("*");
//            }
//        };
//    }

    // war
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder
//                                                         builder) {
//         TODO Auto-generated method stub
//        return builder.sources(Application.class);
//    }
}
