package com.zoho;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dto.Parkerdetails;
import com.repository.repository;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int num=Integer.parseInt(request.getParameter("val"));
		HttpSession session=request.getSession(false);
		String name=(String) session.getAttribute("name");
		PrintWriter out=response.getWriter();
		if(num==0) {
		String a[][][]=repository.getInstance().viewlots(name);
		JSONArray arr=new JSONArray();
		for(int i=0;i<a.length;i++) {
			JSONObject obj=new JSONObject();
			Integer sam=0;
			for(int j=0;j<a[i].length;j++) {
				for(int k=0;k<a[i][j].length;k++) {
					obj.put(sam,a[i][j][k]);
					sam++;
				}
			}
			arr.add(obj);
		}
		out.println(arr);
		}
		else if(num==1) {
			String date=request.getParameter("date");
			List<Parkerdetails> det=repository.getInstance().viewDetails(date,name);
			JSONArray arr=new JSONArray();
			for(int i=0;i<det.size();i++) {
				Parkerdetails sam=det.get(i);
				JSONObject obj=new JSONObject();
				obj.put("name", sam.getName());
				obj.put("location", sam.getLocation());
				obj.put("date", sam.getDate());
				obj.put("phnumber", sam.getNumber());
				obj.put("vehnum", sam.getCar());
				obj.put("parktime", sam.getParkTime()+"");
				obj.put("endtime", sam.getEndTime()+"");
				arr.add(new JSONObject(obj));
			}
			out.println(arr);
		}
		else if(num==3) {
			String number=request.getParameter("num");
			System.out.println(number);
			int check=repository.getInstance().payment(number,name);
			System.out.println(check);
			out.println(check);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session =request.getSession(false);
			String name=(String) session.getAttribute("name");
			String orgpass=(String) session.getAttribute("orgpass");
			String pass=request.getParameter("pass");
			String lot=request.getParameter("lot");
			repository.getInstance().modify("", pass, Integer.parseInt(lot),name,orgpass);
			if(!orgpass.equals(""))
			session.setAttribute("orgpass",pass);
			PrintWriter out=response.getWriter();
			JSONObject obj=new JSONObject();
			obj.put("val", "1");
			out.println(obj);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
}
