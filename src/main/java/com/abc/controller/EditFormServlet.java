package com.abc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.dao.EditService;
import com.abc.dto.Student;

@WebServlet("/edit")
public class EditFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Setting a MIME response Object
		response.setContentType("text/html");
		
		//Getting PrintWriter Stream to write a ResponseObject
		PrintWriter out= response.getWriter();
		
		out.println("<html><head><title>Response</title></head>");
		out.println("<body>");
		
		//Collecting Request parameters from the RequestObject
		String sid=request.getParameter("sid");
		
		//Pass sid to service layer
		EditService editService= new EditService();
		Student student=editService.getSutdent(Integer.parseInt(sid));
		
		if(student != null) {
			out.println("<form action='update' method='post'>");
			out.println("<table style='margin-left:auto; margin-right:auto'>");
			out.println("<tr><td>SID</td><td>" + student.getSid() + "</td></tr>");
			out.println("<tr><td><input type='hidden' name='sid' value="+student.getSid()+"></td></tr>");
			out.println("<tr><td>SNAME</td><td><input type='text' name='sname' value="+student.getSname()+"></td></tr>");
			out.println("<tr><td>SADDRESS</td><td><input type='text' name='saddress' value="+student.getSaddress()+"></td></tr>");
			out.println("<tr><td><input type='submit' value='update'/></td></tr>");
			out.println("</table>");
			out.println("</form>");
		}else {
			out.println(
					"<h1 style='color:red; text-align:center;'>RECORD NOT AVAILABLE FOR GIVEN ID :: " + sid + "</h1>");
			out.println("<h1 style='color:red; text-align:center;'><a href='index.html'>|INDEXPAGE|</a></h1>");
		}
		out.println("</body>");
		out.println("</head>");
		//Closing Stream
		out.close();
	}

}
