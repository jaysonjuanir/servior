package com.person.web;

import java.io.IOException;
import java.io.PrintWriter;


import com.person.service.*;
import com.person.dto.*;

import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddContact
 */
/**
 * @author preetham
 *
 */
public class AddContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddContact() {
        super();
        System.out.println("Logout servlet constructor called");
    }

    /**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("AddContact \"Init\" method called");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("AddContact \"Destroy\" method called");
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AddContact doGet called");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		out.write("<html><body><h4>Check console to understand the flow</h4></body></html>");
		if(session!=null)
		{
			//invalidates the session
			session.invalidate();
			System.out.println("Session is invalidated/logged out");
			out.write("<html><body><h1>Session is invalidated/logged out</h1></body></html>");
			
			
		}
		else{
			out.write("<html><body><h1>Session not present</h1></body></html>");
			try{
		List<PersonDto> personDtos = new Service().getPeople();
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		//request.setAttribute("persons",personDtos);
		request.setAttribute("thisShit","awdawdawdawdawdaw");
		
		System.out.println(personDtos);
		System.out.println("fuck this "+request.getAttribute("thisShit"));
		request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
		//response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/TestSessionServlet"));
		}catch(Exception ex){ex.printStackTrace();}
		}
		out.write("<html><body><p>&copy 2016 Preetham</p></body></html>");
	}


}
