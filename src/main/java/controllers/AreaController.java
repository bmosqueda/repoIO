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
}