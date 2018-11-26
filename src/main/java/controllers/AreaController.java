package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Area;

public class AreaController extends Controller {
  public AreaController() {
    super();
  }

  public Area[] getAll() throws ClassNotFoundException, SQLException {
    this.open();

    String sql = "SELECT * FROM areas";

    PreparedStatement stament = this.connector.prepareStatement(sql);
    ResultSet resultSet = stament.executeQuery();
    ArrayList<Area> areas = new ArrayList<Area>();

    while (resultSet.next())
      areas.add(new Area(resultSet.getInt(1), resultSet.getString(2)));

    resultSet.close();
    this.close();

    return (Area[]) areas.toArray(new Area[areas.size()]);
  }
  
  public Area[] getByResourceId(int id) throws ClassNotFoundException, SQLException {
	    this.open();

	    String sql = "SELECT a.* FROM areas_resource AS ar INNER JOIN areas AS a ON ar.area_id = a.area_id WHERE ar.resource_id = "+ id;

	    PreparedStatement stament = this.connector.prepareStatement(sql);
	    ResultSet resultSet = stament.executeQuery();
	    ArrayList<Area> areas = new ArrayList<Area>();

	    while (resultSet.next())
	      areas.add(new Area(resultSet.getInt("area_id"), resultSet.getString("name")));

	    resultSet.close();
	    this.close();

	    return (Area[]) areas.toArray(new Area[areas.size()]);
	  }

  public Area getById(int id) throws ClassNotFoundException, SQLException {
    this.open();

    String sql = "SELECT * FROM areas WHERE area_id = " + id;

    PreparedStatement stament = this.connector.prepareStatement(sql);
    ResultSet resultSet = stament.executeQuery();
    Area area = null;

    if (resultSet.next())
      area = new Area(resultSet.getInt(1), resultSet.getString(2));

    resultSet.close();
    this.close();

    return area;
  }

  public boolean create(Area area) throws SQLException, ClassNotFoundException {
    String sql = "INSERT INTO areas (name) VALUES(?)";

    this.open();

    PreparedStatement stament = this.connector.prepareStatement(sql);
    stament.setString(1, area.getName());

    stament.executeQuery();
    ResultSet generatedKeys = stament.getGeneratedKeys();

    boolean result = false;

    if (generatedKeys.next()) {
      area.setArea_id(generatedKeys.getInt(1));
      result = true;
    }

    generatedKeys.close();
    this.close();

    return result;
  }

  public boolean update(Area area) throws SQLException, ClassNotFoundException {
	  String sql = "UPDATE areas SET name = '"+this.escapeString(area.getName())+"' WHERE area_id = "+area.getArea_id();

	  this.open();

	  PreparedStatement stament = this.connector.prepareStatement(sql);
	  //stament.setString(1, area.getName());
	  //stament.setInt(2, area.getArea_id());

	  int rows = stament.executeUpdate(sql);

	  stament.close();
	  this.close();

	  return rows > 0;
  }
  
  public boolean delete(Area area) throws SQLException, ClassNotFoundException {
	  String sql = "DELETE FROM areas WHERE area_id = "+area.getArea_id();

	  this.open();

	  PreparedStatement stament = this.connector.prepareStatement(sql);
	  //stament.setString(1, area.getName());
	  //stament.setInt(2, area.getArea_id());

	  int rows = stament.executeUpdate(sql);

	  stament.close();
	  this.close();

	  return rows > 0;
  }
  
  public boolean hasChilds(Area area) throws SQLException, ClassNotFoundException {
	  String sql = "SELECT COUNT(*) AS childs FROM areas_resource WHERE area_id = "+area.getArea_id();

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