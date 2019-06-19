/**
 * 
 */
package com.soprasteria.devopsassesmenttool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author dbkumar
 *
 */

@Component
public class DevOpsInterceptorAppConfig extends WebMvcConfigurerAdapter {
	@Autowired
	DevOpsServiceInterceptor devOpsServiceInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(devOpsServiceInterceptor).excludePathPatterns("/devops/login/**", "/devops/logout/**");
	}
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/devops/**");
    }
}