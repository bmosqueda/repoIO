package models;

import org.json.simple.JSONObject;

public class Keyword extends Model
{
  private int keyword_id;
  private String keyword;
  
  public Keyword(String keyword) {
    super();
    this.keyword = keyword;
  }
  
  public Keyword(int id, String keyword) {
    super();
    this.keyword_id = id;
    this.keyword = keyword;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public String toJSON() {
    JSONObject json = new JSONObject();
    
    json.put("keyword_id", this.keyword_id);
    json.put("keyword", this.keyword);
    
    return json.toString();
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public JSONObject toJSONObject() {
    JSONObject json = new JSONObject();
    
    json.put("keyword_id", this.keyword_id);
    json.put("keyword", this.keyword);
    
    return json;
  }
  
  @Override
  public String toString()
  {
    return "keyword_id: "+this.keyword_id + "\n "+
        "keyword: "+this.keyword + "";
  }
  
  public int getKeyword_id() {
    return keyword_id;
  }
  public void setKeyword_id(int keyword_id) {
    this.keyword_id = keyword_id;
  }
  public String getKeyword() {
    return keyword;
  }
  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }
}