package com.dt.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.dt"})
@EnableHystrix
@EnableSwagger2
@Controller
@ComponentScan(value = {"com.dt"})
public class CrmWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmWebApplication.class, args);
    }

}
