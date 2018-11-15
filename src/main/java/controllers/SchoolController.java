package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;

import models.School;
import models.User;

public class SchoolController extends General {
	private School school;

	public SchoolController(School school) {
		super();
		this.school = school;
	}
	
	public SchoolController() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public String schoolsToJSON(School schools[])
	{
		JSONArray json = new JSONArray();
		
		for (School school : schools) {
			json.add(school.toJSONObject());
		}
		
		return json.toString();
	}
	
	public School[] getAll() throws ClassNotFoundException, SQLException
	{
	  String sql = "SELECT * FROM schools WHERE 1 > 5";
	  ArrayList<String[]> rows = this.getBySQL(sql);
	  int rowsCount = rows.size();
	  School schools[] = new School[rowsCount - 1];
	  
	  for(int i = 1; i < rowsCount; i++)
	  {
	    schools[i - 1] = new School(
	        Integer.parseInt(rows.get(i)[0]), 
	        rows.get(i)[1]
	      );
	  }
	  
	  return schools;
	}
	
	public School getById(int id) throws ClassNotFoundException, SQLException
	{
	  String sql = "SELECT * FROM schools WHERE school_id = " + id;
	  
	  ArrayList<String[]> rows = this.getBySQL(sql);
	  int rowsCount = rows.size();
	  if(rowsCount < 2)
	    return null;
	  
	  return new School(
	    Integer.parseInt(rows.get(1)[0]), 
	    rows.get(1)[1]
	  );
	}
}
