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

@WebServlet("/update")
public class UpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Setting MIME response to a response object
		response.setContentType("text/html");
		
		//Collecting Request parameters from the RequestObject
		String sid=request.getParameter("sid");
		String sname = request.getParameter("sname");
		String saddress = request.getParameter("saddress");

		// setting the request object data to Student
		Student student = new Student();
		student.setSid(Integer.parseInt(sid));
		student.setSname(sname);
		student.setSaddress(saddress);

		// Get the service of StudentService for updation
		EditService editService=new EditService();
		boolean status=editService.updateStudent(student);
		
		//Getting PrintWriter stream to write a response object
		PrintWriter out=response.getWriter();
				
		out.println("<html><head><title>Response</title></head>");
		out.println("<body>");
		if (status) {
			out.println("<h1 style='color:green; text-align:center;'>STUDENT UPDATED SUCCESFULLY</h1>");
		} else {
			out.println("<h1 style='color:red; text-align:center;'>STUDENT UPDATION FAILED</h1>");
			out.println("<h2 style='color:blue; text-align:center;'><a href='index.html'>|INDEXPAGE|</a></h2>");
		}

		out.println("</body>");
		out.println("</head>");
		//Closing Stream
		out.close();
	}

}
