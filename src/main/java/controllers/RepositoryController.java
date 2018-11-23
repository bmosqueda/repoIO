package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Repository;

public class RepositoryController extends Controller {
  public RepositoryController() {
    super();
  }

  public Repository[] getAll() throws ClassNotFoundException, SQLException {
    this.open();

    String sql = "SELECT r.*, u.name FROM repositories AS r INNER JOIN users AS u ON r.creator_id = u.user_id";

    PreparedStatement stament = this.connector.prepareStatement(sql);
    ResultSet resultSet = stament.executeQuery();
    ArrayList<Repository> repositories = new ArrayList<Repository>();

    while (resultSet.next())
      repositories.add(new Repository(
        resultSet.getInt(1), 
        resultSet.getInt(2), 
        resultSet.getString(3),
        resultSet.getString(4),
        resultSet.getString(5),
        resultSet.getString(6)
      ));

    resultSet.close();
    this.close();

    return (Repository[]) repositories.toArray(new Repository[repositories.size()]);
  }
  
  public Repository[] getAllByCreator(int id) throws ClassNotFoundException, SQLException {
	    this.open();

	    String sql = "SELECT r.*, u.name FROM repositories AS r INNER JOIN users AS u ON r.creator_id = u.user_id WHERE r.creator_id = "+id;

	    PreparedStatement stament = this.connector.prepareStatement(sql);
	    ResultSet resultSet = stament.executeQuery();
	    ArrayList<Repository> repositories = new ArrayList<Repository>();

	    while (resultSet.next())
	      repositories.add(new Repository(
	        resultSet.getInt(1), 
	        resultSet.getInt(2), 
	        resultSet.getString(3),
	        resultSet.getString(4),
	        resultSet.getString(5),
	        resultSet.getString(6)
	      ));

	    resultSet.close();
	    this.close();

	    return (Repository[]) repositories.toArray(new Repository[repositories.size()]);
	  }
  
  public Repository[] getAllByCategory(int id) throws ClassNotFoundException, SQLException {
	    this.open();
	    
	    /*
	     	SELECT r.*, u.name 
			FROM categories_repository AS c
			INNER JOIN repositories AS r
  				ON r.repository_id = c.repository_id 
			INNER JOIN users AS u 
  				ON r.creator_id = u.user_id 
			WHERE c.category_id =  2; 
	    */
	    String sql = "SELECT r.*, u.name \n" + 
	    		"FROM categories_repository AS c\n" + 
	    		"INNER JOIN repositories AS r\n" + 
	    		"  ON r.repository_id = c.repository_id \n" + 
	    		"INNER JOIN users AS u \n" + 
	    		"  ON r.creator_id = u.user_id \n" + 
	    		"WHERE c.category_id = "+id;

	    PreparedStatement stament = this.connector.prepareStatement(sql);
	    ResultSet resultSet = stament.executeQuery();
	    ArrayList<Repository> repositories = new ArrayList<Repository>();

	    while (resultSet.next())
	      repositories.add(new Repository(
	        resultSet.getInt(1), 
	        resultSet.getInt(2), 
	        resultSet.getString(3),
	        resultSet.getString(4),
	        resultSet.getString(5),
	        resultSet.getString(6)
	      ));

	    resultSet.close();
	    this.close();

	    return (Repository[]) repositories.toArray(new Repository[repositories.size()]);
	  }
  
  public Repository[] getAllByTitle(String title) throws ClassNotFoundException, SQLException {
	    this.open();	
	    
	    String sql = "SELECT r.*, u.name FROM repositories AS r INNER JOIN users AS u ON r.creator_id = u.user_id WHERE r.name LIKE '%"+this.escapeString(title)+"%'";

	    PreparedStatement stament = this.connector.prepareStatement(sql);
	    ResultSet resultSet = stament.executeQuery();
	    ArrayList<Repository> repositories = new ArrayList<Repository>();

	    while (resultSet.next())
	      repositories.add(new Repository(
	        resultSet.getInt(1), 
	        resultSet.getInt(2), 
	        resultSet.getString(3),
	        resultSet.getString(4),
	        resultSet.getString(5),
	        resultSet.getString(6)
	      ));

	    resultSet.close();
	    this.close();

	    return (Repository[]) repositories.toArray(new Repository[repositories.size()]);
	  }
  
  public Repository[] getAllByArea(int id) throws ClassNotFoundException, SQLException {
	    this.open();
	    
	    /*
            SELECT rep.*, u.name 
            FROM areas_resource AS ar
            INNER JOIN resources AS res
              ON res.resource_id = ar.resource_id 
            INNER JOIN repositories AS rep
              ON res.repository_id = rep.repository_id 
            INNER JOIN users AS u 
              ON rep.creator_id = u.user_id 
            WHERE ar.area_id =  1
            GROUP BY rep.repository_id
	    */
	    String sql = "SELECT rep.*, u.name \n" + 
	    		"FROM areas_resource AS ar\n" + 
	    		"INNER JOIN resources AS res\n" + 
	    		"  ON res.resource_id = ar.resource_id \n" + 
	    		"INNER JOIN repositories AS rep\n" + 
	    		"  ON res.repository_id = rep.repository_id \n" + 
	    		"INNER JOIN users AS u \n" + 
	    		"  ON rep.creator_id = u.user_id \n" + 
	    		"WHERE ar.area_id = "+id + 
	    		" GROUP BY rep.repository_id";

	    PreparedStatement stament = this.connector.prepareStatement(sql);
	    ResultSet resultSet = stament.executeQuery();
	    ArrayList<Repository> repositories = new ArrayList<Repository>();

	    while (resultSet.next())
	      repositories.add(new Repository(
	        resultSet.getInt(1), 
	        resultSet.getInt(2), 
	        resultSet.getString(3),
	        resultSet.getString(4),
	        resultSet.getString(5),
	        resultSet.getString(6)
	      ));

	    resultSet.close();
	    this.close();

	    return (Repository[]) repositories.toArray(new Repository[repositories.size()]);
	  }
  
  public Repository[] getAllByKeywords(String keywords) throws ClassNotFoundException, SQLException {
	    this.open();
	    
	    String keys[] = keywords.split(",");
	    String where = "";
	    for (String str : keys) 
			where += "keyword = '"+KeywordController.keywordFormat(str) +"' OR ";
		
	    where = where.substring(0, where.length() - 4);
	    /*
		      SELECT r.*, u.name 
              FROM (
                SELECT keyword_id
                FROM keywords 
                WHERE keyword = 'mundo'
              ) AS k
              INNER JOIN repositories_with_keyword AS rk
                  ON rk.keyword_id = k.keyword_id 
              INNER JOIN repositories AS r
                  ON rk.repository_id = r.repository_id 
              INNER JOIN users AS u
                  ON r.creator_id = u.user_id
              GROUP BY r.repository_id
	    */
	    String sql = "SELECT r.*, u.name \n" + 
	    		"FROM (\n" + 
	    		"  SELECT keyword_id\n" + 
	    		"  FROM keywords \n" + 
	    		"  WHERE " + where + 
	    		") AS k\n" + 
	    		"INNER JOIN repositories_with_keyword AS rk\n" + 
	    		"    ON rk.keyword_id = k.keyword_id \n" + 
	    		"INNER JOIN repositories AS r\n" + 
	    		"    ON rk.repository_id = r.repository_id \n" + 
	    		"INNER JOIN users AS u\n" + 
	    		"    ON r.creator_id = u.user_id\n" + 
	    		"GROUP BY r.repository_id";
	    System.out.println(sql);
	    
	    PreparedStatement stament = this.connector.prepareStatement(sql);
	    ResultSet resultSet = stament.executeQuery();
	    ArrayList<Repository> repositories = new ArrayList<Repository>();

	    while (resultSet.next())
	      repositories.add(new Repository(
	        resultSet.getInt(1), 
	        resultSet.getInt(2), 
	        resultSet.getString(3),
	        resultSet.getString(4),
	        resultSet.getString(5),
	        resultSet.getString(6)
	      ));

	    resultSet.close();
	    this.close();

	    return (Repository[]) repositories.toArray(new Repository[repositories.size()]);
	  }
  
  public Repository getById(int id) throws ClassNotFoundException, SQLException {
    this.open();
    String sql = "SELECT r.*, u.name FROM repositories AS r INNER JOIN users AS u ON r.creator_id = u.user_id WHERE r.repository_id = "+id;

    PreparedStatement stament = this.connector.prepareStatement(sql);
    ResultSet resultSet = stament.executeQuery();
    Repository repository = null;

    if (resultSet.next())
      repository = new Repository(
          resultSet.getInt(1), 
          resultSet.getInt(2), 
          resultSet.getString(3),
          resultSet.getString(4),
          resultSet.getString(5),
          resultSet.getString(6)
        );

    resultSet.close();
    this.close();

    return repository;
  }

  public boolean create(Repository repository) throws SQLException, ClassNotFoundException {
    String sql = "INSERT INTO repositories (creator_id, name, url, tags) VALUES (?, ?, ?, ?)";

    this.open();

    PreparedStatement stament = this.connector.prepareStatement(sql);
    stament.setInt(1, repository.getCreator_id());
    stament.setString(2, repository.getName());
    stament.setString(3, repository.getUrl());
    stament.setString(4, repository.getTags());

    stament.executeQuery();
    ResultSet generatedKeys = stament.getGeneratedKeys();

    boolean result = false;

    if (generatedKeys.next()) {
      repository.setRepository_id(generatedKeys.getInt(1));
      result = true;
    }

    generatedKeys.close();
    this.close();

    return result;
  }

  public boolean insertCategoryRepository(int repository_id, int category_id) throws SQLException, ClassNotFoundException {
	  String sql = "INSERT INTO categories_repository (repository_id, category_id) VALUES (?, ?)";

	    this.open();

	    PreparedStatement stament = this.connector.prepareStatement(sql);
	    stament.setInt(1, repository_id);
	    stament.setInt(2, category_id);

	    stament.executeQuery();
	    ResultSet generatedKeys = stament.getGeneratedKeys();

	    boolean result = false;

	    if (generatedKeys.next()) 
	      result = true;

	    generatedKeys.close();
	    this.close();

	    return result;
	  }
  
  public boolean insertKeywordRepository(int repository_id, int keyword_id) throws SQLException, ClassNotFoundException {
	  String sql = "INSERT INTO repositories_with_keyword (repository_id, keyword_id) VALUES (?, ?)";

	    this.open();

	    PreparedStatement stament = this.connector.prepareStatement(sql);
	    stament.setInt(1, repository_id);
	    stament.setInt(2, keyword_id);

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