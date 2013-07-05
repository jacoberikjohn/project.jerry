package com.jerry.BaseFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseFileConnection {

    private static Connection con;

    public static void createConnection(String baseFile){
        try {
        	Class.forName("org.hsqldb.jdbcDriver");
        	con = DriverManager.getConnection("jdbc:hsqldb:file:" + baseFile, "SA","");
        } catch (Exception ex) {
            ex.printStackTrace();
        }    
    }

    public static Connection getConnection(){
        return con;
    }

    public static void closeConnection(){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException ex) {
                 ex.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
    	BaseFileConnection bf = new BaseFileConnection();
    	
    	//bf.createConnection("/home/lost/Desktop/project.jerry/userFiles/testFile");
    	bf.createConnection("/home/lost/Desktop/project.jerry/src/com/jerry/resources/applicationSettings.odb");
    	
    	Statement statement;
		try {
			statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery("SELECT * FROM usersettings");
			//print the result set
            while (rs.next())
            {
                System.out.println("create version: " + rs.getString("create_version"));
                System.out.println("project name: " + rs.getString("project_name"));
                System.out.println("project description: " + rs.getString("project description"));
                System.out.println("project author: " + rs.getString("project_author"));
            }

            statement.close();
            con.close();

			
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //look at " for table name
        
    	
    }

}