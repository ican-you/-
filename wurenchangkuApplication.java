package com.A14;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan(value = "com.A14.ServletContextListener")
@MapperScan(basePackages = {"com.A14.dao"})
public class wurenchangkuApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(wurenchangkuApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
        return applicationBuilder.sources(wurenchangkuApplication.class);
    }
}
