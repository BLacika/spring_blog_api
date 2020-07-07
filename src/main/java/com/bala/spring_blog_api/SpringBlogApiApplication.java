package com.bala.spring_blog_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
public class SpringBlogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBlogApiApplication.class, args);
	}

}
