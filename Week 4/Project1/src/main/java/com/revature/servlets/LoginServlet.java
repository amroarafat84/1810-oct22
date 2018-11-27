package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.Reimbursement;
import com.revature.pojos.User;
import com.revature.service.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	static UserService uService = new UserService();

	private static Logger log = Logger.getLogger(LoginServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// functionality to go back to login.html
		// REQUEST DISPATCHER

		req.getRequestDispatcher("login.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// login functionality here:
		String username = null;
		String password = null;
		
		ObjectMapper usrmap = new ObjectMapper();
		User usr = usrmap.readValue(req.getInputStream(), User.class);
		
		User u = uService.validateUser(username, password);
		
		//convert to JSON
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(u);
		log.trace("RETURNING USER. JSON: " + json);
		
		//send response
		PrintWriter writer = resp.getWriter();
		resp.setContentType("/application/json");
		writer.write(json);

		if (u == null) {
			req.getRequestDispatcher("partials/error-login.html").forward(req, resp);
		} else {
			// successful login
			// Add user to session
			HttpSession session = req.getSession();

			// will return current session if one exists
			// creates new session and returns it if none exists
			log.trace("ADDING USER TO SESSION: " + session.getId());

			session.setAttribute("user", u);
			
			log.trace(session.getAttribute("role"));

		}

	}

}
