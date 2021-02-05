package com.kingmushroom.main.data;
// Author: Ali Umar
import ca.senecacollege.prg556.limara.dao.AccountOwnerDAO;


public class AccountOwnerDAOFactory {

	public AccountOwnerDAOFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static AccountOwnerDAO getAccountOwnerDAO() {
		return new AccountOwnerData();
	}
	
	
}
