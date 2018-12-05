package com.app.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.app.pojo.Reimbursement;
import com.app.pojo.User;
import com.app.service.ReimbService;
import com.app.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/reimbursement")
public class ReimbursementServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(ReimbursementServlet.class);
	static ReimbService reimbService = new ReimbService();
	static UserService userService = new UserService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		ObjectMapper mapper = new ObjectMapper();
		Reimbursement reimb = mapper.readValue(req.getInputStream(), Reimbursement.class);
		int id = (int) session.getAttribute("userId");
		reimb.setAuthor(id);
		reimb = reimbService.addReimbursement(reimb.getAmount(), reimb.getDescription(),
				reimb.getAuthor(), reimb.getTypeId());
		logger.trace("ADDED NEW REIMBURSEMENTS: " + reimb);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}