package com.kingmushroom.main;

// Author: Ali Umar
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kingmushroom.main.data.AccountOwnerDAOFactory;

import ca.on.senecac.prg556.common.StringHelper;
import ca.senecacollege.prg556.limara.bean.AccountOwner;
import ca.senecacollege.prg556.limara.dao.AccountOwnerDAO;

/**
 * Servlet Filter implementation class LibraryLoginFilter
 */

public class LibraryLoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LibraryLoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest _request = (HttpServletRequest)request; 
		HttpServletResponse _response = (HttpServletResponse)response; 
		HttpSession session = _request.getSession();
		final String username = (String) _request.getParameter("usernameInput");
		final String password = (String) _request.getParameter("passwordInput");
		boolean loginCheck = StringHelper.isNotNullOrEmpty(username) && StringHelper.isNotNullOrEmpty(password);

		if (session.getAttribute("owner") != null && loginCheck)
		{
			_response.sendRedirect(((HttpServletRequest)request).getContextPath() + "/");
			return;
		} 
		
		try {
			
			AccountOwnerDAO OwnerDAO = AccountOwnerDAOFactory.getAccountOwnerDAO();
			AccountOwner obj = null;
			
			if ("POST".equals(_request.getMethod())) {
				if(loginCheck)
					obj = OwnerDAO.validateAccountOwner(username, password);
				if (obj != null)
				{
					session.setAttribute("owner", obj);
					_response.sendRedirect(((HttpServletRequest)request).getContextPath() + "/");
				} else if(StringHelper.isNotNullOrEmpty(username)) {
					_request.setAttribute("validated", false);
					_request.setAttribute("username", StringHelper.xmlEscape(username));
				} else {
					_request.setAttribute("validated", null);
				}
			}
			chain.doFilter(request, response);
			
		} catch(SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
