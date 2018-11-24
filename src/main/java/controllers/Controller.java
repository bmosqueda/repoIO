package controllers;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public static JSONArray arrayToJSONArray(Model objs[]) 
	{
		JSONArray json = new JSONArray();

		for (Model obj : objs) 
			json.add(obj.toJSONObject());

		return json;
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
	
	public static String escapeString(String x) {
        StringBuilder sBuilder = new StringBuilder(x.length() * 11/10);

        int stringLength = x.length();

        for (int i = 0; i < stringLength; ++i) {
            char c = x.charAt(i);

            switch (c) {
            case 0: /* Must be escaped for 'mysql' */
                sBuilder.append('\\');
                sBuilder.append('0');

                break;

            case '\n': /* Must be escaped for logs */
                sBuilder.append('\\');
                sBuilder.append('n');

                break;

            case '\r':
                sBuilder.append('\\');
                sBuilder.append('r');

                break;

            case '\\':
                sBuilder.append('\\');
                sBuilder.append('\\');

                break;

            case '\'':
                sBuilder.append('\\');
                sBuilder.append('\'');

                break;

            /*case '"': 
                if (escapeDoubleQuotes) {
                    sBuilder.append('\\');
                }

                sBuilder.append('"');

                break;*/

            case '\032': /* This gives problems on Win32 */
                sBuilder.append('\\');
                sBuilder.append('Z');

                break;

            case '\u00a5':
            case '\u20a9':
                // escape characters interpreted as backslash by mysql
                // fall through

            default:
                sBuilder.append(c);
            }
        }

        return sBuilder.toString();
    }
}