package com.euvatrates.euvrservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
@EnableAspectJAutoProxy
public class EuvrserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EuvrserviceApplication.class, args);
	}



}
