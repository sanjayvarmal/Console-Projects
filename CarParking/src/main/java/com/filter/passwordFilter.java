package com.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebFilter( urlPatterns= {"/Home"})
public class passwordFilter implements Filter{
	Pattern p;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String val=req.getParameter("val");
		if(val.equals("2")) {
		String password = req.getParameter("pass");
		System.out.println("password is:" +password+" homepassword");
		Matcher m = p.matcher(password);
		if(m.matches()) {
			System.out.println("password valid");
			chain.doFilter(request, response);
		}
		else {
			System.out.println("password invalid-filter");
			JSONObject obj=new JSONObject();
			obj.put("val", "0");
			res.getWriter().println(obj);
		}
		}
		else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		p=Pattern.compile("^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,15}$");
	}

}
