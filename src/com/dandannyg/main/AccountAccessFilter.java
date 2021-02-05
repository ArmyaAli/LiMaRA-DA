package com.dandannyg.main;
//Daniel Guo
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet Filter implementation class LibraryLoginFilter
 */
public class AccountAccessFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AccountAccessFilter() {
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
		
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest _request = (HttpServletRequest)request; 
        HttpServletResponse _response = (HttpServletResponse)response;
        HttpSession session = _request.getSession();
        final String loginPage = ((HttpServletRequest)request).getContextPath() + "/librarylogin.jspx"; 
    	if (session.getAttribute("owner") == null && !(_request.getRequestURI()).equals(loginPage))
		{
			_response.sendRedirect(((HttpServletRequest)request).getContextPath() + "/librarylogin.jspx");
			return;
		} 
    	
	    chain.doFilter(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
