package com.example.demo;

import
		org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


import javax.activation.DataSource;


@RestController
@Configuration
@SpringBootApplication
@EnableTransactionManagement   //开启事务管理
@ComponentScan("com.example.demo")
@MapperScan("com.example.demo.dao")

//@EnableScheduling

//extends WebMvcConfigurationSupport

public class DemoApplication extends SpringBootServletInitializer{

	public static void main(String[] args)
	{
		SpringApplication.run(DemoApplication.class, args);
	}



	@Override//为了打包springboot项目
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return builder.sources(this.getClass());
	}

/*
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//这里res下存在的资源不拦截，但是随便输一个不存在的/res/asdasd，按理说应该也不会被拦截，只是报404，然而却被拦截了
		//registry.addInterceptor((HandlerInterceptor) loginInterceptor).excludePathPatterns("/resources/static/**");

		System.out.println("启动拦截器------------------------------------------");

		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/resources/static/tokenrequest.html");


	}

*/



	@RequestMapping("/")
    public ModelAndView hello(){

		return  new ModelAndView ("index");
	}

	@RequestMapping("/test")
	public String test(){

		return "test springboot";
	}
}
