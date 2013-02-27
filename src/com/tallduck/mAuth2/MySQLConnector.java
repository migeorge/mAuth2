package com.tallduck.mAuth2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLConnector {
	private String host = null;
	private String port = null;
	private String dbName = null;
	private String tableName = null;
	private String username = null;
	private String password = null;
	private String secret = null;
	
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	public MySQLConnector(String host, String port, String dbName, String tableName, String username, String password, String secret){
		this.host = host;
		this.port = port;
		this.dbName = dbName;
		this.tableName = tableName;
		this.username = username;
		this.password = password;
		this.secret = secret;
	}
	
	public String getPass(String player){
		String pass = null;
		
		try{
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbName, username, password);
		
		statement = connect.createStatement();
		resultSet = statement.executeQuery("SELECT pass FROM " + tableName + " WHERE user = '" + player + "'");
		
		pass = resultSet.getString("pass");
		}
		catch(Exception e){
			e.printStackTrace();
		} finally {
			close();
		}
		
		return pass;
	}
	
	private void close(){
		try{
			if(resultSet != null){
				resultSet.close();
			}
			if(statement != null){
				statement.close();
			}
			if(connect != null){
				connect.close();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
