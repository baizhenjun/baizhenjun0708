package com.example.java_springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan("com.example.java_springboot.mapper")
public class JavaSpringbootApplication {
	public static void main(String[] args) {
		SpringApplication.run(JavaSpringbootApplication.class, args);
	}

}
