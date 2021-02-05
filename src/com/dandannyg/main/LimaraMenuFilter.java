package com.dandannyg.main;
//Daniel Guo
import java.io.IOException;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ca.senecacollege.prg556.limara.bean.AccountOwner;
import ca.senecacollege.prg556.limara.bean.Material;
import ca.senecacollege.prg556.limara.dao.AccountOwnerDAO;
import ca.senecacollege.prg556.limara.dao.MaterialReservationDAO;

import com.kingmushroom.main.data.*;
import com.dandannyg.main.data.*;

/**
 * Servlet Filter implementation class LibraryLoginFilter
 */
public class LimaraMenuFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LimaraMenuFilter() {
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
		HttpServletRequest req = (HttpServletRequest)request; 
		HttpSession session = req.getSession();
		AccountOwner owner = (AccountOwner) session.getAttribute("owner");
        MaterialReservationDAO mrDAO = MaterialReservationDAOFactory.getMaterialReservationDAO();
		try {
			req.setAttribute("noMat", mrDAO.getMaterialReservations(owner.getId()).size());
		} 
		catch (Exception e) {
			throw new ServletException(e);
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
