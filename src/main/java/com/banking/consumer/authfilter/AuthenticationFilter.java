package com.banking.consumer.authfilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import com.banking.consumer.constants.ConsumerConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class AuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String authToken = req.getHeader("AuthToken");
		if(authToken != null) {
			String[] tokenList = authToken.split("token ");
			if(tokenList.length == 2 && tokenList[1] != null) { // 0 - "Auth ", 1 - tokenstring
				String token = tokenList[1].trim();
				try {
					Claims claims =  Jwts.parser().setSigningKey(ConsumerConstants.ENCRYPT_KEY).parseClaimsJws(token).getBody();
					if(!req.getSession().toString().equals(claims.get("session").toString())) {
						res.sendError(HttpStatus.FORBIDDEN.value(), "invalid/expired session");
						return;
					}
					req.setAttribute("accountno", Integer.parseInt(claims.get("accountno").toString()));
				}
				catch (Exception e) {
					res.sendError(HttpStatus.FORBIDDEN.value(), "invalid/expired token");
					return;
				}
			}
			else {
				res.sendError(HttpStatus.FORBIDDEN.value(), "No token info found");
				return;
			}
		}
		else {
			res.sendError(HttpStatus.FORBIDDEN.value(), "No header info found");
			return;
		}
		chain.doFilter(request, response);
	}
}
