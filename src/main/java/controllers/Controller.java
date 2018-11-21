package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.json.simple.JSONArray;

import models.Model;

public class Controller 
{
	protected Connection connector;
	private final String host = "localhost";
	private final String port = "3306";
	private final String database = "repoIo";
	private final String user = "repoIo";
	private final String password = "repoIoPass";
	
	public Controller() {
		super();
	}
	
	public void open() throws ClassNotFoundException, SQLException
    {
		Class.forName("org.mariadb.jdbc.Driver");
        this.connector = DriverManager.getConnection(
                "jdbc:mariadb://" + this.host + ":" + this.port + "/" + this.database,
                this.user,
                this.password
        );
    }
	
	public void close() throws SQLException
    {
        this.connector.close();
    }
	
	public static String arrayToJSON(Model objs[]) 
	{
		JSONArray json = new JSONArray();

		for (Model obj : objs) 
			json.add(obj.toJSONObject());

		return json.toString();
	}
	
	public static boolean isInt(String str)
	{
	    try
	    {
	        Integer.parseInt(str);
	        return true;
	    } catch (NumberFormatException ex)
	    {
	        return false;
	    }
	}
}