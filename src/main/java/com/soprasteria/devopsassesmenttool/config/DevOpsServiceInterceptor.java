/**
 * 
 */
package com.soprasteria.devopsassesmenttool.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.soprasteria.devopsassesmenttool.model.UserToken;
import com.soprasteria.devopsassesmenttool.repository.UserTokenRepository;
import com.soprasteria.devopsassesmenttool.util.ApiResponse;
import com.soprasteria.devopsassesmenttool.util.CustomErrorType;
import com.soprasteria.devopsassesmenttool.util.ResourceNotFoundException;

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

		
/*		if (request.getHeader("token") != null) {
			UserToken ut = userTokenRepository.findByToken(request.getHeader("token"));
			if (ut != null) {
				return true;
			}

			else {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("UNAUTHORIZED");
			}
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("UNAUTHORIZED");

		}*/

		return true;
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