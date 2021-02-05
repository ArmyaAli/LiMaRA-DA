package com.dandannyg.main.data;
//Daniel Guo
import javax.sql.DataSource;

public class DataSourceFactory {
	private static DataSource dSource;
	
	public DataSourceFactory() {
		// TODO Auto-generated constructor stub
	}
	public static DataSource getDataSource() {
		return dSource;
	}
	public static void setDataSource(DataSource dataSource) {
		DataSourceFactory.dSource = dataSource;
	}

}
