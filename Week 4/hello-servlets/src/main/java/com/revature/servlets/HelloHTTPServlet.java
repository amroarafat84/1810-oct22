package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class HelloHTTPServlet extends HttpServlet {
	//HttpServlet extends GenericServlet
	//Here, we do NOT have to override the service() method
	//we must simply override AT LEAST ONE doXXX method
	//such as doGet() or doPost()
	
	private static Logger log = Logger.getLogger(HelloHTTPServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Use SERVLETS to manipulate form data
		String name = req.getParameter("username");
		String password = req.getParameter("password");
		//req.getParameter(x); where x is input field's name OR parameter
		//appended to URL
		PrintWriter writer = resp.getWriter();
		String text = "Getting Data from form:"
				+ "<br>Username: "+name
				+ "<br>Password: "+password;
		
		Enumeration<String> paramNames = req.getParameterNames();
		while (paramNames.hasMoreElements()) {
			text += "<br>PARAM: " + paramNames.nextElement();
		}
		
		writer.write(text);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		PrintWriter writer = resp.getWriter();
		String text = "POSTING DATA: "
				+ "<br>Username: "+username
				+ "<br>Password: "+password;
		
		writer.write(text);
		
	}

}
