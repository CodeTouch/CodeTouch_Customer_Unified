package com.tagmaster.codetouch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.tagmaster.codetouch.mapper")
public class CodetouchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodetouchApplication.class, args);
	}

}
