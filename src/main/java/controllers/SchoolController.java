package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.School;

public class SchoolController extends Controller {
	public SchoolController() {
		super();
	}

	public School[] getAll() throws ClassNotFoundException, SQLException {
		this.open();

		String sql = "SELECT * FROM schools";

		PreparedStatement stament = this.connector.prepareStatement(sql);
		ResultSet resultSet = stament.executeQuery();
		ArrayList<School> schools = new ArrayList<School>();

		while (resultSet.next())
			schools.add(new School(resultSet.getInt(1), resultSet.getString(2)));

		resultSet.close();
		this.close();

		return (School[]) schools.toArray(new School[schools.size()]);
	}

	public School getById(int id) throws ClassNotFoundException, SQLException {
		this.open();

		String sql = "SELECT * FROM schools WHERE school_id = " + id;

		PreparedStatement stament = this.connector.prepareStatement(sql);
		ResultSet resultSet = stament.executeQuery();
		School school = null;

		if (resultSet.next())
			school = new School(resultSet.getInt(1), resultSet.getString(2));

		resultSet.close();
		this.close();

		return school;
	}

	public boolean create(School school) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO schools (name) VALUES(?)";

		this.open();

		PreparedStatement stament = this.connector.prepareStatement(sql);
		stament.setString(1, school.getName());

		stament.executeQuery();
		ResultSet generatedKeys = stament.getGeneratedKeys();

		boolean result = false;

		if (generatedKeys.next()) {
			school.setSchool_id(generatedKeys.getInt(1));
			result = true;
		}

		generatedKeys.close();
		this.close();

		return result;
	}

	public boolean update(School school) throws SQLException, ClassNotFoundException {
	    String sql = "UPDATE schools SET name = '"+this.escapeString(school.getName())+"' WHERE school_id = "+school.getSchool_id();

	    this.open();

	    PreparedStatement stament = this.connector.prepareStatement(sql);

	    int rows = stament.executeUpdate(sql);

	    stament.close();
	    this.close();

	    return rows > 0;
	}
	
	public boolean delete(School school) throws SQLException, ClassNotFoundException {
	    String sql = "DELETE FROM schools WHERE school_id = "+school.getSchool_id();
	    this.open();

	    PreparedStatement stament = this.connector.prepareStatement(sql);

	    int rows = stament.executeUpdate(sql);

	    stament.close();
	    this.close();

	    return rows > 0;
	}
	
	public boolean hasChilds(int school_id) throws SQLException, ClassNotFoundException {
	    String sql = "SELECT COUNT(*) AS childs FROM users WHERE school_id = "+school_id;

	    this.open();

	    PreparedStatement stament = this.connector.prepareStatement(sql);
	    ResultSet resultSet = stament.executeQuery();
	    
	    boolean result = false;
	    if (resultSet.next())
	        result = resultSet.getInt("childs") > 0;

	    resultSet.close();
	    this.close();

	    return result;
	}
}
