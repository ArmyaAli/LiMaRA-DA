package com.kingmushroom.main.data;

// Author: Ali Umar
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dandannyg.main.data.DataSourceFactory;
import ca.senecacollege.prg556.limara.bean.AccountOwner;
import ca.senecacollege.prg556.limara.dao.AccountOwnerDAO;

class AccountOwnerData implements AccountOwnerDAO {

	public AccountOwnerData() {
		// TODO Auto-generated constructor stub
	}

	private AccountOwner accountOwnerHelper(String query) throws SQLException {
		try (Connection conn = DataSourceFactory.getDataSource()
				.getConnection()) {
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				try (ResultSet rslt = statement.executeQuery()) {
					if (!rslt.next())
						return null;
					int dbId = rslt.getInt("id");
					String dbfName = rslt.getString("first_name");
					String dblName = rslt.getString("last_name");
					return new AccountOwner(dbId, dbfName, dblName);
				}
			}
		}
	}

	@Override
	public AccountOwner getAccountOwner(int id) throws SQLException {
		final String tableName = "accountowner";
		final String query = String.format(
				"SELECT * FROM %s WHERE id = \'%s\'", tableName,
				Integer.toString(id));
		return accountOwnerHelper(query);
	}

	@Override
	public AccountOwner validateAccountOwner(String username, String password)
			throws SQLException {
		final String tableName = "accountowner";

		final String query = String
				.format("SELECT * FROM Limara.dbo.%s WHERE username = \'%s\' and password = \'%s\'",
						tableName, username, password);

		return accountOwnerHelper(query);
	}

}
