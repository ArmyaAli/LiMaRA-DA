package com.dandannyg.main;

//Daniel Guo
import java.io.IOException;
import java.sql.SQLException;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.on.senecac.prg556.common.StringHelper;
import ca.senecacollege.prg556.limara.bean.AccountOwner;
import ca.senecacollege.prg556.limara.bean.Material;
import ca.senecacollege.prg556.limara.dao.MaterialDAO;
import ca.senecacollege.prg556.limara.dao.MaterialReservationDAO;

import com.dandannyg.main.data.MaterialReservationDAOFactory;
import com.kingmushroom.main.BadRequestException;
import com.kingmushroom.main.data.*;

/**
 * Servlet Filter implementation class LibraryLoginFilter
 */
public class ReserveMaterialsFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public ReserveMaterialsFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse _response = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String title = req.getParameter("titleINPUT");
		boolean valid = true;
		final String type = req.getParameter("typeINPUT");
		String matId = null;
		MaterialDAO mDAO = MaterialDAOFactory.getMaterialDAO();
		MaterialReservationDAO mrdDAO = MaterialReservationDAOFactory.getMaterialReservationDAO();
		try {
			if("POST".equals(req.getMethod())) {
				// CHECKS
				if(req.getParameter("idINPUT") == null) {
					if(StringHelper.isNotNullOrEmpty(type)) {
						req.setAttribute("type", StringHelper.xmlEscape(type));
						if(StringHelper.isNullOrEmpty(title) && type.equals("All")) {
							req.setAttribute("invalidTitle", true);
							valid = false;
						} 
					} else 
						valid = false;
	
					if(StringHelper.isNotNullOrEmpty(title)) {
						req.setAttribute("title", StringHelper.xmlEscape(title));
					}
					
					if(valid) {
						if(type.equals("All")) {
							req.setAttribute("availableMaterials", mDAO.getAvailableMaterials(title, null));
						} else if(StringHelper.isNotNullOrEmpty(title)){
							req.setAttribute("availableMaterials", mDAO.getAvailableMaterials(title, type));
						} else {
							req.setAttribute("availableMaterials", mDAO.getAvailableMaterials(null, type));
						}
					}
				} else {
					matId = req.getParameter("idINPUT");
					if(StringHelper.isNullOrEmpty(matId)) {
						throw new BadRequestException();
					}
					if(mrdDAO.reserveMaterial(((AccountOwner)session.getAttribute("owner")).getId(), matId) != null) {
						_response.sendRedirect(((HttpServletRequest)request).getContextPath() + "/");
					}
				}
				
			}
		} catch(SQLException ex) {
			throw new ServletException(ex);
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
