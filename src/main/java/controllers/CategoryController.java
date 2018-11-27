package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Category;

public class CategoryController extends Controller {
  public CategoryController() {
    super();
  }

  public Category[] getAll() throws ClassNotFoundException, SQLException {
    this.open();

    String sql = "SELECT * FROM categories";

    PreparedStatement stament = this.connector.prepareStatement(sql);
    ResultSet resultSet = stament.executeQuery();
    ArrayList<Category> categories = new ArrayList<Category>();

    while (resultSet.next())
      categories.add(new Category(resultSet.getInt(1), resultSet.getString(2)));

    resultSet.close();
    this.close();

    return (Category[]) categories.toArray(new Category[categories.size()]);
  }

  public Category getById(int id) throws ClassNotFoundException, SQLException {
    this.open();

    String sql = "SELECT * FROM categories WHERE category_id = " + id;

    PreparedStatement stament = this.connector.prepareStatement(sql);
    ResultSet resultSet = stament.executeQuery();
    Category Category = null;

    if (resultSet.next())
      Category = new Category(resultSet.getInt(1), resultSet.getString(2));

    resultSet.close();
    this.close();

    return Category;
  }

  public boolean create(Category Category) throws SQLException, ClassNotFoundException {
    String sql = "INSERT INTO categories (name) VALUES(?)";

    this.open();

    PreparedStatement stament = this.connector.prepareStatement(sql);
    stament.setString(1, Category.getName());

    stament.executeQuery();
    ResultSet generatedKeys = stament.getGeneratedKeys();

    boolean result = false;

    if (generatedKeys.next()) {
      Category.setCategory_id(generatedKeys.getInt(1));
      result = true;
    }

    generatedKeys.close();
    this.close();

    return result;
  }

  public Category[] getAllByRepositoryId(int repository_id) throws ClassNotFoundException, SQLException {
      this.open();

      String sql = "SELECT c.* FROM categories_repository AS cr "+
                    "INNER JOIN categories AS c ON cr.category_id = c.category_id "+
                    "WHERE cr.repository_id = "+repository_id;

      PreparedStatement stament = this.connector.prepareStatement(sql);
      ResultSet resultSet = stament.executeQuery();
      ArrayList<Category> categories = new ArrayList<Category>();

      while (resultSet.next())
        categories.add(new Category(
            resultSet.getInt(1),
            resultSet.getString(2)
          ));

      resultSet.close();
      this.close();

      return (Category[]) categories.toArray(new Category[categories.size()]);
    }

  public boolean update(Category category) throws SQLException, ClassNotFoundException {
      String sql = "UPDATE categories SET name = '"+this.escapeString(category.getName())+"' WHERE category_id = "+category.getCategory_id();

      this.open();

      PreparedStatement stament = this.connector.prepareStatement(sql);

      int rows = stament.executeUpdate(sql);

      stament.close();
      this.close();

      return rows > 0;
  }
  
  public boolean delete(Category category) throws SQLException, ClassNotFoundException {
      String sql = "DELETE FROM categories WHERE category_id = "+category.getCategory_id();
      this.open();

      PreparedStatement stament = this.connector.prepareStatement(sql);

      int rows = stament.executeUpdate(sql);

      stament.close();
      this.close();

      return rows > 0;
  }
  
  public boolean hasChilds(Category category) throws SQLException, ClassNotFoundException {
      String sql = "SELECT COUNT(*) AS childs FROM categories_repository WHERE category_id = "+category.getCategory_id();

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