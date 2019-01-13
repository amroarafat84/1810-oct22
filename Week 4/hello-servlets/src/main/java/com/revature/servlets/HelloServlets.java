package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;


//Generic servlet
//endpoint is ; http://localhost:8088/hello-servlets/test
public class HelloServlets extends GenericServlet{
	
	private static Logger log = Logger.getLogger(HelloServlets.class);
	
	static int counter = 0;

	/*
	 * LIFECYCLE OF A SERVLET! init() service() destroy()
	 * 
	 * init is called by the container when the servlet is initialized.
	 * Here you can configure things like parameters, loggers, etc. 
	 * Basically anything
	 * 
	 */
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		log.trace("Initializing Hello Servlet");
	}
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.trace("Servicing Hello Servlet, Count: " + ++counter);
		
		//PrintWriter allows you to write responses
		
		String context = getServletContext().getInitParameter("AppInfo");
		String config = getServletConfig().getInitParameter("secret");
		
		PrintWriter writer = res.getWriter();
		res.setContentType("text/html");
		String text = "<h1>Hello Servlets!</h1>"
				+ "<br>"
				+ "This is a response from the HelloServlet"
				+ "<br>"
				+ "<i> Request #" + counter + "</i>"
				+ "<br>ServletContext: " +context
				+ "<br>ServletConfig: " + config;
		writer.write(text); //sends parameter as response body;
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		log.trace("Destroying Hello Servlet");

	}

	
	
}