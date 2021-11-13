package com.saurabh.departmentweb;

import com.saurabh.departmentweb.services.DepartmentService;
import com.saurabh.departmentweb.services.DepartmentServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
@EntityScan("com.department.api")
public class DepartmentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DepartmentServiceApplication.class, args);
    }

    @Bean
    DepartmentService getDepatment(){
        return new DepartmentServiceImpl(null);
    }

}
