package com.zoho;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.repository.repository;

import com.dto.subadmin;
import com.mysql.cj.xdevapi.JsonParser;
import com.dto.Parkerdetails;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out1=response.getWriter();
		String location=request.getParameter("val");
		List<String> loc=repository.getInstance().checkLocation(location);
		JSONArray arr=new JSONArray();
		for(int i=0;i<loc.size();i++) {
			JSONObject obj=new JSONObject();
			obj.put("loc", loc.get(i).split("@")[0]);
			obj.put("slot", loc.get(i).split("@")[1]);
			obj.put("price", loc.get(i).split("@")[2]);
			arr.add(obj);
		}
		out1.println(arr);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String name=request.getParameter("username");
		String pass=request.getParameter("password");
		
		int num=repository.getInstance().checkuser(name, pass);
		if(num==2) {
			response.sendRedirect("failed.html");
			return;
		}
		HttpSession session = request.getSession();
		session.setAttribute("name", name);
		session.setAttribute("orgpass", pass);
		if(num==1) {
			response.sendRedirect("admin.html");
		}
		else {
			response.sendRedirect("home.html");
		}
		//response.sendRedirect("home.html");
	}
}
