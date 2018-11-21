package models;

import org.json.simple.JSONObject;

public class Area extends Model
{
  private int area_id;
  private String name;
  
  public Area(String name) {
    super();
    this.name = name;
  }
  
  public Area(int id, String name) {
    super();
    this.area_id = id;
    this.name = name;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public String toJSON() {
    JSONObject json = new JSONObject();
    
    json.put("area_id", this.area_id);
    json.put("name", this.name);
    
    return json.toString();
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public JSONObject toJSONObject() {
    JSONObject json = new JSONObject();
    
    json.put("area_id", this.area_id);
    json.put("name", this.name);
    
    return json;
  }
  
  @Override
  public String toString()
  {
    return "area_id: "+this.area_id + "\n "+
        "Name: "+this.name + "";
  }
  
  public int getArea_id() {
    return area_id;
  }
  public void setArea_id(int area_id) {
    this.area_id = area_id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
}