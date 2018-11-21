package models;

import org.json.simple.JSONObject;

public class Category extends Model
{
  private int category_id;
  private String name;
  
  public Category(String name) {
    super();
    this.name = name;
  }
  
  public Category(int id, String name) {
    super();
    this.category_id = id;
    this.name = name;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public String toJSON() {
    JSONObject json = new JSONObject();
    
    json.put("category_id", this.category_id);
    json.put("name", this.name);
    
    return json.toString();
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public JSONObject toJSONObject() {
    JSONObject json = new JSONObject();
    
    json.put("category_id", this.category_id);
    json.put("name", this.name);
    
    return json;
  }
  
  @Override
  public String toString()
  {
    return "category_id: "+this.category_id + "\n "+
        "Name: "+this.name + "";
  }
  
  public int getCategory_id() {
    return category_id;
  }
  public void setCategory_id(int category_id) {
    this.category_id = category_id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
}