package com.example.javaitmo2;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = @Info(
		title = "Itmo Java project API",
		version = "1.0"
))
public class JavaItmo2Application {

	public static void main(String[] args) {
		SpringApplication.run(JavaItmo2Application.class, args);
	}
}