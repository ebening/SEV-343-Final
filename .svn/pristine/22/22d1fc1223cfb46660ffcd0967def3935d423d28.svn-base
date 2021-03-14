package com.adinfi.seven.business.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Clase que tiene los métodos que usan los servlets del proyecto AdManager
 *	 
 * @author ELIAS AKE UC
 *
 */
public abstract class BaseServlet extends HttpServlet {

	//	 Initializes the servlet.
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	// Destroys the servlet.
	public void destroy() { }
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
				    throws ServletException,IOException {
		doAction(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
				    throws ServletException,IOException {
		doAction(request,response);
	}
	
	public abstract void doAction(HttpServletRequest request,HttpServletResponse response)
				    throws ServletException,IOException;
	
	/**
	 * Manda la respuesta al jsp.
	 * @param request
	 * @param response
	 * @param page
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void dispatch(HttpServletRequest request, HttpServletResponse response, String page)
					throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		dispatcher.forward(request, response);	
	}
	
	
}
