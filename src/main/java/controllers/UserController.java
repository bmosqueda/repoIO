package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;

import models.User;

public class UserController extends General
{
	private User user;
	
	public UserController(User user) throws ClassNotFoundException
	{
		super();
		this.user = user;
	}
	
	public UserController() throws ClassNotFoundException
	{
		super();
	}
	
	@SuppressWarnings("unchecked")
	public String usersToJSON(User users[])
	{
		JSONArray json = new JSONArray();
		
		for (User user : users) {
			json.add(user.toJSONObject());
		}
		
		return json.toString();
	}
	
	public User[] getAll() throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * FROM users";
		ArrayList<String[]> rows = this.getBySQL(sql);
		int rowsCount = rows.size();
		User users[] = new User[rowsCount - 1];
		
		for(int i = 1; i < rowsCount; i++)
		{
			users[i - 1] = new User(
					Integer.parseInt(rows.get(i)[0]), 
					rows.get(i)[1], 
					rows.get(i)[2], 
					rows.get(i)[3],
					Integer.parseInt(rows.get(i)[4]), 
					Integer.parseInt(rows.get(i)[5]) 
				);
		}
		
		return users;
	}
	
	public User getAllInfoById(int id) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT \n" + 
				"  u.account_number,\n" + 
				"  u.email,\n" + 
				"  u.name,\n" + 
				"  u.role,\n" + 
				"  u.school_id,\n" + 
				"  u.user_id,\n" +
				"  s.name AS 'school_name'\n" + 
				"FROM users AS u\n" + 
				"INNER JOIN schools AS s ON u.school_id = s.school_id\n" + 
				"WHERE user_id = "+id;
		
		ArrayList<String[]> rows = this.getBySQL(sql);
		int rowsCount = rows.size();
		if(rowsCount < 2)
			return null;
		
			return new User(
					Integer.parseInt(rows.get(1)[0]), 
					rows.get(1)[1], 
					rows.get(1)[2], 
					rows.get(1)[3],
					Integer.parseInt(rows.get(1)[4]), 
					Integer.parseInt(rows.get(1)[5]),
					rows.get(1)[6]
				);
		
	}
}