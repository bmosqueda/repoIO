package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.User;

public class UserController extends Controller
{		
	public UserController() {
		super();
	}
	
	public User[] getAll() throws ClassNotFoundException, SQLException
	{
		this.open();
		
		String sql = "SELECT "+User.fields_without_password
				+ ", s.name FROM users AS u "
				+ "INNER JOIN schools AS s ON u.school_id = s.school_id";
		
		PreparedStatement stament = this.connector.prepareStatement(sql);
        ResultSet resultSet = stament.executeQuery();
        ArrayList<User> users = new ArrayList<User>();
        
        while (resultSet.next()) 
        	users.add(new User(
        				resultSet.getInt(1),
        				resultSet.getString(2),
        				resultSet.getString(3),
        				resultSet.getString(4),
        				resultSet.getInt(5),
        				resultSet.getInt(6),
        				resultSet.getString(7),
        				resultSet.getString(8)
        			));
        
        resultSet.close();
        this.close();
        
		return (User[]) users.toArray();
	}
	
	public User getById(int id) throws ClassNotFoundException, SQLException
	{
		this.open();
		
		String sql = "SELECT " + User.fields_without_password
				+ ", s.name FROM users AS u "
				+ "INNER JOIN schools AS s ON u.school_id = s.school_id WHERE u.user_id = "+id;
		
		PreparedStatement stament = this.connector.prepareStatement(sql);
        ResultSet resultSet = stament.executeQuery();
        User user = null;
        
        if(resultSet.next()) 
        	user = new User(
        				resultSet.getInt(1),
        				resultSet.getString(2),
        				resultSet.getString(3),
        				resultSet.getString(4),
        				resultSet.getInt(5),
        				resultSet.getInt(6),
        				resultSet.getString(7),
        				resultSet.getString(8)
        			);
        
        resultSet.close();
        this.close();
        
		return user;
	}
	
	public boolean existsByAccount(String account) throws ClassNotFoundException, SQLException
	{
		this.open();
		
		String sql = "SELECT COUNT(*) AS exists FROM users WHERE account_number = ?";
		
		PreparedStatement stament = this.connector.prepareStatement(sql);
        stament.setString(1, account);
        ResultSet resultSet = stament.executeQuery(sql);
        boolean exists = false;

        if(resultSet.next()) 
        	exists = resultSet.getInt("exists") > 0;

        resultSet.close();
        this.close();

        return exists;
	}
	
	public boolean existsByEmail(String email) throws ClassNotFoundException, SQLException
	{
		this.open();
		
		String sql = "SELECT COUNT(*) AS exists FROM users WHERE email = ?";
		
		PreparedStatement stament = this.connector.prepareStatement(sql);
        stament.setString(1, email);
        ResultSet resultSet = stament.executeQuery(sql);
        boolean exists = false;

        if(resultSet.next()) 
        	exists = resultSet.getInt("exists") > 0;

        resultSet.close();
        this.close();

        return exists;
	}
	
	public boolean create(User user) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO users (account_number, name, email, password, role, school_id, image) VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		this.open();
	       
        PreparedStatement stament = this.connector.prepareStatement(sql);
        stament.setString(1, user.getAccount_number());
        stament.setString(2, user.getName());
        stament.setString(3, user.getEmail());
        stament.setString(4, user.getPassword());
        stament.setInt(5, user.getRole());
        stament.setInt(6, user.getSchool_id());
        stament.setString(7, user.getImage());
        
        stament.executeQuery();
        ResultSet generatedKeys = stament.getGeneratedKeys();
        
        boolean result = false;
        
        if(generatedKeys.next()) {
        	user.setUser_id(generatedKeys.getInt(1));
        	result = true;
        }
        
        generatedKeys.close();
        this.close();
        
        return result;
	}
	
	public User login(String emailOrAccount, String password) throws ClassNotFoundException, SQLException
	{
		this.open();
		
		boolean isAccount = this.isInt(emailOrAccount);
		
		String sql = "SELECT "+User.fields_without_password
				+ ", s.name FROM users AS u "
				+ "INNER JOIN schools AS s ON u.school_id = s.school_id WHERE "+ (isAccount ? "u.account_number = ?" : "u.email = ?")
				+ " AND password = ?";
		
		PreparedStatement stament = this.connector.prepareStatement(sql);
        stament.setString(1, emailOrAccount);
        stament.setString(2, password);
        
        ResultSet resultSet = stament.executeQuery();
        User user = null;
        
        if(resultSet.next()) 
        	user = new User(
        				resultSet.getInt(1),
        				resultSet.getString(2),
        				resultSet.getString(3),
        				resultSet.getString(4),
        				resultSet.getInt(5),
        				resultSet.getInt(6),
        				resultSet.getString(7),
        				resultSet.getString(8)
        			);
        
        resultSet.close();
        this.close();
        
		return user;
	}
}