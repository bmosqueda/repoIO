package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import models.Area;
import models.Author;
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
  
  public JSONArray getAllByRepositoryIdJSON(int repository_id) throws ClassNotFoundException, SQLException {
	    this.open();

	    String sql = "SELECT * FROM resources WHERE repository_id = "+repository_id;

	    PreparedStatement stament = this.connector.prepareStatement(sql);
	    ResultSet resultSet = stament.executeQuery();
	    
	    AreaController areaController = new AreaController();
	    AuthorController authorController = new AuthorController();
	    
	    JSONArray json = new JSONArray();
	    
	    while (resultSet.next())
	    {
	    	Resource resource = new Resource(
	  	          resultSet.getInt(1),
		          resultSet.getString(2),
		          resultSet.getString(3),
		          resultSet.getInt(4),
		          resultSet.getInt(5),
		          resultSet.getInt(6),
		          resultSet.getString(7)
		        );
	    	
	    	JSONObject resourceJSON = resource.toJSONObject();
	    	
	    	Area areas[] = areaController.getByResourceId(resultSet.getInt("resource_id"));
	    	resourceJSON.put("areas", this.arrayToJSONArray(areas));
	    	
	    	Author authors[] = authorController.getByResourceId(resultSet.getInt("resource_id"));
	    	resourceJSON.put("authors", this.arrayToJSONArray(authors));
	    	
	    	json.add(resourceJSON);
	    }

	    resultSet.close();
	    this.close();

	    return json;
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
  
  public boolean insertAreaResource(int resource_id, int area_id) throws SQLException, ClassNotFoundException {
	    String sql = "INSERT INTO areas_resource (resource_id, area_id) VALUES (?, ?)";

	    this.open();

	    PreparedStatement stament = this.connector.prepareStatement(sql);
	    stament.setInt(1, resource_id);
	    stament.setInt(2, area_id);
	    
	    stament.executeQuery();
	    ResultSet generatedKeys = stament.getGeneratedKeys();

	    boolean result = false;

	    if (generatedKeys.next()) 
	      result = true;
	    

	    generatedKeys.close();
	    this.close();

	    return result;
	  }
  
  public boolean insertAuthorResource(int resource_id, int author_id) throws SQLException, ClassNotFoundException {
	    String sql = "INSERT INTO authors_resource (resource_id, author_id) VALUES (?, ?)";

	    this.open();

	    PreparedStatement stament = this.connector.prepareStatement(sql);
	    stament.setInt(1, resource_id);
	    stament.setInt(2, author_id);
	    
	    stament.executeQuery();
	    ResultSet generatedKeys = stament.getGeneratedKeys();

	    boolean result = false;

	    if (generatedKeys.next()) 
	      result = true;
	    

	    generatedKeys.close();
	    this.close();

	    return result;
	  }
}