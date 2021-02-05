package com.kingmushroom.main;

//Author: Ali Umar
import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import ca.senecacollege.prg556.limara.bean.AccountOwner;
import ca.senecacollege.prg556.limara.bean.Material;
import ca.senecacollege.prg556.limara.dao.MaterialReservationDAO;
import com.dandannyg.main.data.*;

/**
 * Servlet Filter implementation class ShowMaterialReservationsFilter
 */
public class ShowMaterialReservationsFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public ShowMaterialReservationsFilter() {
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
		HttpServletRequest _request = (HttpServletRequest) request;
		final String matId = (String) _request.getParameter("idINPUT");
		MaterialReservationDAO mrdDAO = MaterialReservationDAOFactory
				.getMaterialReservationDAO();
		int ownerId = ((AccountOwner) _request.getSession().getAttribute(
				"owner")).getId();

		try {
			if ("POST".equals(_request.getMethod())) {
				Material obj = mrdDAO.getMaterialReservation(ownerId, matId);
				if (obj != null) {
					mrdDAO.cancelMaterialReservation(ownerId, matId);
				} else {
					throw new BadRequestException();
				}
			}
			_request.setAttribute("mList",
					mrdDAO.getMaterialReservations(ownerId));
			chain.doFilter(request, response);
		} catch (SQLException ex) {
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
