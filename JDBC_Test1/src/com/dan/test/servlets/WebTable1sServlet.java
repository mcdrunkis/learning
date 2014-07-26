package com.dan.test.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dan.test.jdbc.WebDB;
import com.dan.test.jdbc.WebDBException;
import com.dan.test.webobjects.WebTable1;

@WebServlet(value="/webtable1s")
public class WebTable1sServlet extends HttpServlet {
	public static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<WebTable1> webTable1s = null;
		
		try {
			WebDB webDB = new WebDB();
			webTable1s = webDB.getWebTable1s();
		}
		catch (WebDBException ex) {
			request.setAttribute("MESSAGE",ex.getMessage());
		}
		
		request.setAttribute("WEBTABLE1S",webTable1s);
		request.getRequestDispatcher("/webtable1s.jsp").forward(request,response);
	}	
}
