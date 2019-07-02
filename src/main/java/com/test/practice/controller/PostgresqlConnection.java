package com.test.practice.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Service;

@Service
public class PostgresqlConnection {

	private final String url = "jdbc:postgresql://172.26.29.165:5432/things";
	//mainflux:mainflux@localhost:5432/things 172.26.29.162 

    private final String user = "mainflux";
    private final String password = "mainflux";
    

	public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
 
        return conn;
    }
	
    public void getAllThings() {
    	
    	Connection conn = null;
    	Statement stmt = null;
        try {
            conn = connect();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM things" );
            while(rs.next()) {
            	
            	System.out.println("Device Name ----> "+rs.getString("name"));
            	System.out.println("Device Owner ----> "+rs.getString("owner"));
            	System.out.println("Device Id ----> "+rs.getString("id"));
            	System.out.println("Device Key ----> "+rs.getString("key"));
            	System.out.println();


            	
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	
    	
    }
    
    public String getThingIdByName(String deviceName) {
    	
    	Connection conn = null;
    	Statement stmt = null;
    	String deviceId = null;
        try {
            conn = connect();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM things where \"name\" = '"+deviceName+"'" );
            while(rs.next()) {
            	
            	System.out.println("Device Id ----> "+rs.getString("id"));
            	
            	System.out.println();
            	
            	deviceId = rs.getString("id");
            	
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	return deviceId;
    	
    }
    
	/*
	 * public static void main(String[] args) { PostgresqlConnection app = new
	 * PostgresqlConnection(); app.getAllThings(); }
	 */
}
