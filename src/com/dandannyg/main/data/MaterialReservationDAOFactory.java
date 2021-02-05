	package com.dandannyg.main.data;
	//Daniel Guo
	import ca.senecacollege.prg556.limara.dao.MaterialReservationDAO;
	
	public class MaterialReservationDAOFactory {
	
		public MaterialReservationDAOFactory() {
			// TODO Auto-generated constructor stub
		}
		public static MaterialReservationDAO getMaterialReservationDAO(){
			return new MaterialReservationData();
			
		}
	
	}
