package com.vue007.admin;

import com.vue007.admin.util.OssStsUtil;
import org.apache.catalina.connector.Response;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.OutputStream;

/**
 * @author akai
 * @since 2017-06-27 16:42:33
 */
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@Configuration
@RestController
@MapperScan(basePackages = "com.vue007.admin.mapper")
@ComponentScan(basePackages = "com.vue007.admin")
@SpringBootApplication
@PropertySource(value = "classpath:application.properties")
public class Application extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
