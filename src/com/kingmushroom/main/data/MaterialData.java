package com.kingmushroom.main.data;

// Author: Ali Umar
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.dandannyg.main.data.DataSourceFactory;

import ca.on.senecac.prg556.common.StringHelper;
import ca.senecacollege.prg556.limara.bean.Material;
import ca.senecacollege.prg556.limara.dao.MaterialDAO;

class MaterialData implements MaterialDAO {

	public MaterialData() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Material> getAvailableMaterials(String title, String type)
			throws SQLException {
		List<Material> avMaterials = new ArrayList<>();
		final String tableName = "material";
		String query = String.format(
				"SELECT * FROM Limara.dbo.%s WHERE account_owner_id is null", tableName);

		if (StringHelper.isNotNullOrEmpty(title)) {
			query += " "
					+ String.format("and title like \'%%%s%%\'",
							title.toLowerCase());
		}

		if (StringHelper.isNotNullOrEmpty(type)) {
			query += " "
					+ String.format("and type like \'%%%s%%\'",
							type.toLowerCase());
		}
		try (Connection conn = DataSourceFactory.getDataSource()
				.getConnection()) {
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				try (ResultSet rslt = statement.executeQuery()) {
					while (rslt.next()) {
						String dbMatId = rslt.getString("material_id");
						String dbTitle = rslt.getString("title");
						String dbType = rslt.getString("type");
						avMaterials.add(new Material(dbMatId, dbTitle, dbType));
					}
				}
			}
		}

		return avMaterials;
	}

	@Override
	public Material getMaterial(String materialId) throws SQLException {
		final String tableName = "material";
		final String query = String.format(
				"SELECT * FROM Limara.dbo.%s WHERE material_id = \'%s\'", tableName,
				materialId.toLowerCase());

		try (Connection conn = DataSourceFactory.getDataSource()
				.getConnection()) {
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

}
