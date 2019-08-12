/**
 * 
 */
package com.soprasteria.devopsassesmenttool.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.soprasteria.devopsassesmenttool.model.UserToken;
import com.soprasteria.devopsassesmenttool.repository.UserTokenRepository;

/**
 * @author dbkumar
 *
 */

@Component
public class DevOpsServiceInterceptor implements HandlerInterceptor {

	@Autowired
	UserTokenRepository userTokenRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.getHeader("Authorization");

		UserToken ut = userTokenRepository.findByToken(request.getHeader("token"));
		if (ut != null ) {
			return true;
		} else {
			//response.getWriter().write("UNAUTHORIZED");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		
			return true;

		}

		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}