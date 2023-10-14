package com.saurabh.cloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class CloudGatewayApplication {

    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayApplication.class, args);
    }
//
//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
//        return factory -> factory.configureDefault(
//                id -> new Resilience4JConfigBuilder(id)
//                        .timeLimiterConfig(TimeLimiterConfig.custom()
//                                .timeoutDuration(Duration.ofSeconds(4))
//                                .build()
//                        ).circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
//                        .build());
//    }
}
