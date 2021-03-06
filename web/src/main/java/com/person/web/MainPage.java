package com.person.web;

import com.person.service.*;
import com.person.dto.*;
import com.person.model.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.*;
import java.lang.NumberFormatException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


/**
 * Servlet implementation class MainPage
 */
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainPage() {
        System.out.println("MainPage Constructor called!");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("MainPage \"Init\" method called");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("MainPage \"Destroy\" method called");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sort = request.getParameter("sort");
		if(sort==null)
			sort="";
		String cancel = request.getParameter("cancel");
		System.out.println("Action! : "+sort);
		
		System.out.println("MainPage \"Service\" method(inherited) called");
		System.out.println("MainPage \"DoGet\" method called");
		
		try{
			List<PersonDto> personDtos = new ArrayList<>();
			
			if(sort.equals("byDateHired")){
				personDtos = new Service().getPersonByDateHired();
			}
			else if(sort.equals("byLastName")){
				personDtos = new Service().getPersonByLastName();
			}
			else if(sort.equals("byGWA")){
				personDtos = new Service().getPersonByGWA();
			}
			else{
				personDtos = new Service().getPeople();	
			}
			personDtos.forEach(System.out::print);
			response.setContentType("text/html");
			response.setStatus(HttpServletResponse.SC_OK);
			request.setAttribute("persons",personDtos);
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
			//response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/TestSessionServlet"));
		}catch(Exception ex){ex.printStackTrace();}
		//storeInSessionAndRespond(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */ 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{
				
			System.out.println("POST METHOD!");
			String personId = request.getParameter("personId");
			String firstName = request.getParameter("firstName");
			String middleName = request.getParameter("middleName");
			String lastName = request.getParameter("lastName");
			String suffix = request.getParameter("suffix");
			String title = request.getParameter("title");
			String gender = request.getParameter("gender");
			String birthday = request.getParameter("birthday");
			String employed = request.getParameter("employed");
			String dateHired = request.getParameter("dateHired");
			String gwa = request.getParameter("gwa");
			String [] roles = request.getParameterValues("role");
			
			String streetNumber = request.getParameter("streetNumber");
			String barangay = request.getParameter("barangay");
			String city = request.getParameter("city");
			String zipcode = request.getParameter("zipcode");
			
			String [] contactType = request.getParameterValues("contacts");
			String emailValue = request.getParameter("email");
			String mobileValue = request.getParameter("mobile");
			String landlineValue = request.getParameter("landline");
			String emailId = request.getParameter("emailId");
			String mobileId = request.getParameter("mobileId");
			String landlineId = request.getParameter("landlineId");
			
			// for(String s:contactType){
				// System.out.println(s);
			// }
			System.out.println(emailId.isEmpty()?"empty":"not empty");
			System.out.println(mobileId);
			System.out.println(landlineId);
			personId=personId.isEmpty()?"0":personId;
			emailId=emailId.isEmpty()?"0":emailId;
			mobileId=mobileId.isEmpty()?"0":mobileId;
			landlineId=landlineId.isEmpty()?"0":landlineId;
			// NameDto names = new NameDto(firstName, middleName, lastName, suffix, title);
			// AddressDto address = new AddressDto(streetNumber, barangay, city, zipcode);
			// System.out.println(names);
			// System.out.println(address);
			
			PersonDto person = new Service().createPerson(personId, firstName, middleName, lastName, suffix, title, gender, birthday, employed, dateHired, gwa,roles, streetNumber, barangay, city, zipcode, contactType, emailValue, mobileValue, landlineValue, emailId, mobileId, landlineId);
			
			boolean indicatorFields = new Service().checkRequired(firstName, middleName, lastName, streetNumber, barangay,city,zipcode);
			boolean indicatorDate = new Service().checkDate(birthday);
			boolean indicatorDecimal = new Service().checkDecimal(gwa);
			boolean indicatorEmployed =true;
			if(employed.equals("yes")){
				indicatorEmployed = new Service().checkEmployed(dateHired, roles);
			}
			System.out.println(gender);
			if(gender.equals("male")){
				person.setGender(Gender.MALE);
			}
			else{
				person.setGender(Gender.FEMALE);
			}
			
			System.out.println("fields "+indicatorFields +" date "+ indicatorDate +" decimal "+ !indicatorDecimal +" employed "+ indicatorEmployed);
			System.out.println("PERSON OUTPUT!!!!:" + person);
			person.getPerson_contact().forEach(System.out::println);
			if(indicatorFields && indicatorDate && !indicatorDecimal && indicatorEmployed){
				if(personId.isEmpty() || personId.equals("0")){
					new Service().executeCreatePerson(person);
					System.out.println(person.getPerson_contact());
				}
				else{	
					//PersonDto existingPerson = new Service().getPersonById(Integer.parseInt(personId));
					System.out.println(person.getPerson_contact());
					//person.setPerson_contact(existingPerson.getPerson_contact());
					new Service().executeUpdatedPerson(person);
				}
				
				response.sendRedirect(request.getContextPath()+"/MainPage?message=SUCCESS");
			}else{
				//error
				
				List<String> errors = new ArrayList<>();
				if(!indicatorFields){
					errors.add("Missing required fields");
				}
				if(!indicatorDate){
					errors.add("Invalid date format");
				}
				if(indicatorDecimal){
					errors.add("Invalid gwa");
				}
				if(!indicatorEmployed){
					errors.add("Missing fields on employed/invalid format");
				}
				request.setAttribute("errors",errors);
				request.setAttribute("person",person);
				request.getRequestDispatcher("/WEB-INF/person.jsp").forward(request, response);
				
				//System.out.println("PERSON OUTPUT!!!!:" + person);
				//System.out.println("NULL PERSON OUTPUT" + new PersonDto());
			}
			System.out.println(personId);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
