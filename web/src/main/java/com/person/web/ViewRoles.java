package com.person.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import com.person.service.*;
import com.person.dto.*;
import com.person.model.Roles;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class SessionServlet
 */
public class ViewRoles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewRoles() {
        System.out.println("ViewRoles constructor called");
    }

    /**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("ViewRoles \"Init\" method called");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("ViewRoles \"Destroy\" method called");
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
		try{
			List<Roles> roles = new Service().getAllRoles();
			roles.forEach(System.out::println);
			PrintWriter out = response.getWriter();
			
			out.write("<html><body><h2>Roles</h2></body></html>");
			
			roles.forEach(r->{
				out.write("<html><body>"+r+"<br/></body></html>");
			});
			out.write("<html><body><p>&copy 2016 nosyajnir</p></body></html>");
			out.write("<html><body><a href='"+request.getContextPath()+"/MainPage'>Back to MainPage</a></body></html>");

		}
		catch(Exception ex){
			
		}
	}
	
}
