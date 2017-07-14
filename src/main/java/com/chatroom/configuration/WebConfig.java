package com.chatroom.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"com.chatroom"})
public class WebConfig extends WebMvcConfigurerAdapter{

	WebConfig()
	{
		System.out.println("Into Configuration File");
	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/resources/**")
		.addResourceLocations("/resources/");
		}
	
	@Bean
	public CommonsMultipartResolver getCommonsMultiPartResolver()
	{
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		return new CommonsMultipartResolver();
	}
	
}
