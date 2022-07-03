package com.banking.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.banking.consumer.authfilter.AuthenticationFilter;


@SpringBootApplication
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthenticationFilter> filterRegistrationBean()
	{
		FilterRegistrationBean<AuthenticationFilter> filterBean = new FilterRegistrationBean<AuthenticationFilter>();
		AuthenticationFilter authFilter = new AuthenticationFilter();
		filterBean.setFilter(authFilter);
		filterBean.addUrlPatterns("/result/*");		
		return filterBean;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> registerCorsFilter()
	{
		FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<CorsFilter>();
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config  = new CorsConfiguration();
		config.addAllowedHeader("*");
		config.addAllowedOrigin("*");
		source.registerCorsConfiguration("/**", config);
		CorsFilter filter = new CorsFilter(source);
		corsBean.setFilter(filter);
		corsBean.setOrder(0);
		return corsBean;
	}
}
