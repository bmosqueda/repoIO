package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;

import models.School;
import models.User;

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

}
