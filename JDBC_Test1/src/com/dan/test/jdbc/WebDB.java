package com.dan.test.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.dan.test.webobjects.WebTable1;

public class WebDB {
	private Connection connection;
	private Logger logger;
	
	public WebDB() throws WebDBException { 
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("jdbc/web");
			this.connection = dataSource.getConnection();
		}
		catch (NamingException ex) {
			throw new WebDBException("Couldn't locate database.",ex);
		}
		catch (SQLException ex) {
			throw new WebDBException("Couldn't connect to database.",ex);
		}
		
		logger = Logger.getLogger(WebDB.class.getName());
	}
	
	public List<WebTable1> getWebTable1s() throws WebDBException {
		List<WebTable1> webTable1s = null;
		
		PreparedStatement ps = null;
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT `id`, `set`, `key`, `value`");
		buffer.append("  FROM web_table_1 " );
		
		try {
			ps = this.connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				webTable1s = new ArrayList<WebTable1>();
				while (rs.next()) {
					WebTable1 webTable1 = new WebTable1();
					webTable1.setSet(rs.getString("set"));
					webTable1.setKey(rs.getString("key"));
					webTable1.setValue(rs.getString("value"));
					webTable1s.add(webTable1);
				}
			}
		}
		catch (SQLException ex) {
			logger.severe("SQL Exception: " + ex.toString());
			throw new WebDBException("Failed to select data.",ex);
		}
		finally {
			try {
				if (ps != null) ps.close();
			}
			catch (SQLException ex) { }
		}
		
		return webTable1s;
	}
}
