package com.zoho;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.subadmin;
import com.repository.repository;

/**
 * Servlet implementation class addUser
 */
@WebServlet("/addUser")
public class addUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		subadmin client=new subadmin();
        client.setLocation(request.getParameter("lotlocation"));
        client.setSlots(Integer.parseInt(request.getParameter("floors")));
        client.setPrice(Integer.parseInt(request.getParameter("price")));
        client.setUserid(request.getParameter("name"));
        client.setPassword(request.getParameter("password"));
        boolean bool=repository.getInstance().addUser(client);
        if(bool)
        	response.sendRedirect("load.html");
        else
        	response.sendRedirect("loadfail.html");
	}

}