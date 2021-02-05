package com.dandannyg.main.data;
//Daniel Guo
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ca.senecacollege.prg556.limara.bean.Material;
import ca.senecacollege.prg556.limara.dao.MaterialReservationDAO;

class MaterialReservationData implements MaterialReservationDAO {

	@Override
	public Material reserveMaterial(int accountOwnerId, String materialId)
			throws SQLException {
		// TODO Auto-generated method stub
		String update = String
				.format("UPDATE Limara.dbo.material SET account_owner_id=%d WHERE material_id =\'%s\' AND account_owner_id IS NULL",
						accountOwnerId, materialId);
		try (Connection conn = DataSourceFactory.getDataSource()
				.getConnection()) // Create connection from data source (ds)
		{
			try (PreparedStatement smt = conn.prepareStatement(update)) {
				smt.executeUpdate();
			}
		}
		return this.getMaterialReservation(accountOwnerId, materialId);
	}

	public MaterialReservationData() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cancelMaterialReservation(int accountOwnerId, String materialId)
			throws SQLException {
		// TODO Auto-generated method stub
		String update = String
				.format("UPDATE  Limara.dbo.material SET account_owner_id=NULL WHERE material_id =\'%s\' AND material_id IS NOT NULL",
						materialId);
		try (Connection conn = DataSourceFactory.getDataSource()
				.getConnection()) // Create connection from data source (ds)
		{
			try (PreparedStatement smt = conn.prepareStatement(update)) {
				smt.executeUpdate();
				return;
			}
		}
	}

	@Override
	public Material getMaterialReservation(int accountOwnerId, String materialId)
			throws SQLException {
		String query = String
				.format("SELECT * FROM  Limara.dbo.material WHERE material_id = \'%s\' AND account_owner_id=%d",
						materialId, accountOwnerId);
		try (Connection conn = DataSourceFactory.getDataSource()
				.getConnection()) // Create connection from data source (ds)
		{
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				try (ResultSet rslt = statement.executeQuery()) {
					if (!rslt.next())
						return null;
					String dbMatId = rslt.getString("material_id");
					String dbTitle = rslt.getString("title");
					String dbType = rslt.getString("type");
					return new Material(dbMatId, dbTitle, dbType);
				}
			}
		}
	}

	@Override
	public List<Material> getMaterialReservations(int accountOwnerId)
			throws SQLException {
		// TODO Auto-generated method stub
		List<Material> reservedMaterials = new ArrayList<>();
		String query = String.format(
				"SELECT * FROM  Limara.dbo.material WHERE account_owner_id = %d ORDER BY type, title", accountOwnerId);
		try (Connection conn = DataSourceFactory.getDataSource()
				.getConnection()) {
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				try (ResultSet rslt = statement.executeQuery()) {
					while (rslt.next()) {
						String dbMatId = rslt.getString("material_id");
						String dbTitle = rslt.getString("title");
						String dbType = rslt.getString("type");
						reservedMaterials.add(new Material(dbMatId, dbTitle, dbType));
					}
				}
			}
		}
		return reservedMaterials;
	}
}
