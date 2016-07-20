package com.person.web;

import java.io.IOException;
import java.io.PrintWriter;

import com.person.service.*;
import com.person.dto.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class SessionServlet
 */
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonServlet() {
        super();
        System.out.println("PersonServlet constructor called");
    }

    /**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("PersonServlet \"Init\" method called");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("PersonServlet \"Destroy\" method called");
		new Service().endProgram();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
		String id = request.getParameter("id");
		if(id==null){
			id="";
		}
		else{
			PersonDto person= new Service().getPersonById(Integer.parseInt(id));
			request.setAttribute("person", person);
			request.setAttribute("id", id);
		}
		//Get the exisiting session, if session doesn't exist it will return null
		HttpSession session = request.getSession(false);
		if(session!=null)
		{
			//get the attributes from session
			String uname=(String) session.getAttribute("uname");
			String emailId=(String) session.getAttribute("emailId");
		}
		else{
		}
		
		request.getRequestDispatcher("/WEB-INF/person.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
		String id = request.getParameter("id");
		PersonDto person = new Service().getPersonById(Integer.parseInt(id));
		new Service().deletePerson(person);
		response.sendRedirect(request.getContextPath()+"/MainPage?delete=SUCCESS");
		// //Get the exisiting session, if session doesn't exist it will return null
		// HttpSession session = request.getSession(false);
		// if(session!=null)
		// {
			// //get the attributes from session
			// String uname=(String) session.getAttribute("uname");
			// String emailId=(String) session.getAttribute("emailId");
		// }
		// else{
		// }
		
		// request.getRequestDispatcher("/WEB-INF/person.jsp").forward(request, response);
	}
}
