/**
 * 
 */
package com.soprasteria.devopsassesmenttool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author dbkumar
 *
 */

@Configuration
@EnableWebMvc
public class DevOpsInterceptorAppConfig extends WebMvcConfigurerAdapter {
	@Autowired
	DevOpsServiceInterceptor devOpsServiceInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(devOpsServiceInterceptor).excludePathPatterns("/devops/login/**", "/devops/logout/**");
	}
}

