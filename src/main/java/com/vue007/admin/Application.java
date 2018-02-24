package com.vue007.admin;

import com.vue007.admin.util.OssStsUtil;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author akai
 * @since 2017-06-27 16:42:33
 */
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@Configuration
@RestController
@MapperScan(basePackages = "com.zed.bbs.admin.mapper")
@ComponentScan(basePackages = "com.zed.bbs.admin")
@SpringBootApplication
@PropertySource(value = "classpath:application.properties")
@PropertySource(value = "classpath:aws.properties")
public class Application extends WebMvcConfigurerAdapter {

    @Value("classpath:/ui/index.html")
    private Resource indexHtml;

    @Value(value="classpath:/oss.json")
    private Resource ossJson;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Object ossJson() throws Exception {
        OssStsUtil.setOssContent(ossJson);
        System.out.println(OssStsUtil.OSS_CONTENT);
        return new Object();
    }
//    @Bean
//    public ServletRegistrationBean apiV1ServletBean(WebApplicationContext wac) {
//        DispatcherServlet servlet = new DispatcherServlet(wac);
//        ServletRegistrationBean bean = new ServletRegistrationBean(servlet, "/api/*");
//        bean.setName("api");
//        return bean;
//    }

    @RequestMapping("/")
    public Object index() {
        return ResponseEntity.ok().body(indexHtml);
    }


}
