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
 * Servlet implementation class admin
 */
@WebServlet("/admin")
public class admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num=Integer.parseInt(request.getParameter("val"));
		System.out.println(1);
		HttpSession session=request.getSession(false);
		String name=(String) session.getAttribute("name");
		
		PrintWriter out=response.getWriter();
		if(num==0) {
			List<String> view=repository.getInstance().view();
			JSONObject obj=new JSONObject();
			for(int i=0;i<view.size();i++) {
				obj.put(i, view.get(i));
			}
			System.out.println(obj);
			out.println(obj);
		}
		else if(num==1) {
			String name1=request.getParameter("select");
			String a[][][]=repository.getInstance().viewlots(name1.split("-")[0]);
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
		else if(num==2) {
			String number=request.getParameter("number");
			List<Parkerdetails> det=repository.getInstance().search(number);
			JSONArray arr=new JSONArray();
			if(det.size()==0) {
				JSONObject obj1=new JSONObject();
				obj1.put(0, "null");
				arr.add(obj1);
				out.println(arr);
			}
			else {
				for(int i=0;i<det.size();i++) {
					JSONObject obj=new JSONObject();
					obj.put("name", det.get(i).getName());
					obj.put("location",det.get(i).getLocation() );
					obj.put("date", det.get(i).getDate());
					obj.put("phnum", det.get(i).getNumber());
					obj.put("vhnum", det.get(i).getCar());
					arr.add(obj);
				}
				out.println(arr);
			}
		}
		else if(num==3) {
			int number=repository.getInstance().deleteLot(request.getParameter("names"));
			JSONObject obj=new JSONObject();
			obj.put("val",number);
			out.println(obj);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
