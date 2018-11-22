package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Resource;

public class ResourceController extends Controller {
  public ResourceController() {
    super();
  }

  public Resource[] getAll() throws ClassNotFoundException, SQLException {
    this.open();

    String sql = "SELECT * FROM resources";

    PreparedStatement stament = this.connector.prepareStatement(sql);
    ResultSet resultSet = stament.executeQuery();
    ArrayList<Resource> resources = new ArrayList<Resource>();

    while (resultSet.next())
      resources.add(new Resource(
          resultSet.getInt(1),
          resultSet.getString(2),
          resultSet.getString(3),
          resultSet.getInt(4),
          resultSet.getInt(5),
          resultSet.getInt(6),
          resultSet.getString(7)
        ));

    resultSet.close();
    this.close();

    return (Resource[]) resources.toArray(new Resource[resources.size()]);
  }
  
  public Resource[] getAllByRepositoryId(int repository_id) throws ClassNotFoundException, SQLException {
	    this.open();

	    String sql = "SELECT * FROM resources WHERE repository_id = "+repository_id;

	    PreparedStatement stament = this.connector.prepareStatement(sql);
	    ResultSet resultSet = stament.executeQuery();
	    ArrayList<Resource> resources = new ArrayList<Resource>();

	    while (resultSet.next())
	      resources.add(new Resource(
	          resultSet.getInt(1),
	          resultSet.getString(2),
	          resultSet.getString(3),
	          resultSet.getInt(4),
	          resultSet.getInt(5),
	          resultSet.getInt(6),
	          resultSet.getString(7)
	        ));

	    resultSet.close();
	    this.close();

	    return (Resource[]) resources.toArray(new Resource[resources.size()]);
	  }

  public Resource getById(int id) throws ClassNotFoundException, SQLException {
    this.open();

    String sql = "SELECT * FROM resources WHERE resource_id = " + id;

    PreparedStatement stament = this.connector.prepareStatement(sql);
    ResultSet resultSet = stament.executeQuery();
    Resource resource = null;

    if (resultSet.next())
      resource = new Resource(
          resultSet.getInt(1),
          resultSet.getString(2),
          resultSet.getString(3),
          resultSet.getInt(4),
          resultSet.getInt(5),
          resultSet.getInt(6),
          resultSet.getString(7)
        );

    resultSet.close();
    this.close();

    return resource;
  }

  public boolean create(Resource resource) throws SQLException, ClassNotFoundException {
    String sql = "INSERT INTO resources (title, description, size, repository_id, type, url) VALUES (?, ?, ?, ?, ?, ?)";

    this.open();

    PreparedStatement stament = this.connector.prepareStatement(sql);
    stament.setString(1, resource.getTitle());
    stament.setString(2, resource.getDescription());
    stament.setInt(3, resource.getSize());
    stament.setInt(4, resource.getRepository_id());
    stament.setInt(5, resource.getType().getIndex());
    stament.setString(6, resource.getUrl());

    stament.executeQuery();
    ResultSet generatedKeys = stament.getGeneratedKeys();

    boolean result = false;

    if (generatedKeys.next()) {
      resource.setResource_id(generatedKeys.getInt(1));
      result = true;
    }

    generatedKeys.close();
    this.close();

    return result;
  }
}