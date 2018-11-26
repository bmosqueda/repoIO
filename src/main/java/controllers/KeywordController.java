package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Keyword;

public class KeywordController extends Controller {
  public KeywordController() {
    super();
  }

  public Keyword[] getAll() throws ClassNotFoundException, SQLException {
    this.open();

    String sql = "SELECT * FROM keywords";

    PreparedStatement stament = this.connector.prepareStatement(sql);
    ResultSet resultSet = stament.executeQuery();
    ArrayList<Keyword> keywords = new ArrayList<Keyword>();

    while (resultSet.next())
      keywords.add(new Keyword(resultSet.getInt(1), resultSet.getString(2)));

    resultSet.close();
    this.close();

    return (Keyword[]) keywords.toArray(new Keyword[keywords.size()]);
  }

  public Keyword getById(int id) throws ClassNotFoundException, SQLException {
    this.open();

    String sql = "SELECT * FROM keywords WHERE keyword_id = " + id;

    PreparedStatement stament = this.connector.prepareStatement(sql);
    ResultSet resultSet = stament.executeQuery();
    Keyword keyword = null;

    if (resultSet.next())
      keyword = new Keyword(resultSet.getInt(1), resultSet.getString(2));

    resultSet.close();
    this.close();

    return keyword;
  }
  
  public int getIdByKey(Keyword key) throws ClassNotFoundException, SQLException {
	    this.open();

	    String sql = "SELECT keyword_id FROM keywords WHERE keyword = ?";
	    String keyword = this.keywordFormat(key.getKeyword().trim());

	    PreparedStatement stament = this.connector.prepareStatement(sql);
	    stament.setString(1, keyword);
	    
	    ResultSet resultSet = stament.executeQuery();
	    int result = -1;
	    		
	    if (resultSet.next())
	     result = resultSet.getInt("keyword_id");
	    
	    resultSet.close();
	    this.close();
	    
	    return result;
	  }

  public boolean create(Keyword keyword) throws SQLException, ClassNotFoundException {
    keyword.setKeyword(keywordFormat(keyword.getKeyword()));

    String sql = "INSERT INTO keywords (keyword) VALUES(?)";

    this.open();

    PreparedStatement stament = this.connector.prepareStatement(sql);
    stament.setString(1, keyword.getKeyword());

    stament.executeQuery();
    ResultSet generatedKeys = stament.getGeneratedKeys();

    boolean result = false;

    if (generatedKeys.next()) {
      keyword.setKeyword_id(generatedKeys.getInt(1));
      result = true;
    }

    generatedKeys.close();
    this.close();

    return result;
  }

  public static String keywordFormat(String key) {

      int length = key.length();
      String formatedKey = "";
      key = key.toLowerCase();

      for(int i = 0; i < length; i++)
      {
        if(!Character.isLetter(key.charAt(i)))
          formatedKey += "_";
        else
          formatedKey += key.charAt(i);
      }

      return formatedKey;
    }
}