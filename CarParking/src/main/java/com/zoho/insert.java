package com.zoho;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.Parkerdetails;
import com.repository.repository;

/**
 * Servlet implementation class insert
 */
@WebServlet("/insert")
public class insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insert() {
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

		Parkerdetails userdetails=new Parkerdetails();
		userdetails.setLocation(request.getParameter("loc"));
        userdetails.nameSet(request.getParameter("name"));
        userdetails.numberSet(request.getParameter("num"));
        userdetails.carSet(request.getParameter("carnum"));
        userdetails.hourSet(Integer.parseInt(request.getParameter("hours")));
        userdetails.setDate(String.valueOf(LocalDate.now()));
        userdetails.setParkTime(LocalDateTime.now());
        repository.getInstance().quickBook(userdetails);
        System.out.println("insert");
        response.sendRedirect("samhome.html");
	}
}