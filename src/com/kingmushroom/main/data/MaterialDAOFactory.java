package com.kingmushroom.main.data;
// Author: Ali Umar
import ca.senecacollege.prg556.limara.dao.MaterialDAO;


public class MaterialDAOFactory {

	public MaterialDAOFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static MaterialDAO getMaterialDAO() {
		return new MaterialData();
	}

}
