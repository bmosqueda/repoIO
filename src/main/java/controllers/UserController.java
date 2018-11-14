package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public User[] getAllInfoById(int id) throws ClassNotFoundException, SQLException
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
}