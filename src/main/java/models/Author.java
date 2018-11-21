package models;

import org.json.simple.JSONObject;

public class Author extends Model {
	private int author_id;
	private String name;
	private String alias;
	private String country_of_birth;
	
	//Other
	public static final String fields = "a.author_id, a.name, a.alias, a.country_of_birth";
	
	public Author(String name, String alias, String country_of_birth) {
		super();
		this.name = name;
		this.alias = alias;
		this.country_of_birth = country_of_birth;
	}
	
	public Author(int author_id, String name, String alias, String country_of_birth) {
		super();
		this.author_id = author_id;
		this.name = name;
		this.alias = alias;
		this.country_of_birth = country_of_birth;
	}
	
	@SuppressWarnings("unchecked")
	  @Override
	  public String toJSON() {
	    JSONObject json = new JSONObject();
	    
	    json.put("author_id", this.author_id);
	    json.put("name", this.name);
	    json.put("alias", this.alias);
	    json.put("country_of_birth", this.country_of_birth);
	    
	    return json.toString();
	  }
	  
	  @SuppressWarnings("unchecked")
	  @Override
	  public JSONObject toJSONObject() {
	    JSONObject json = new JSONObject();
	    
	    json.put("author_id", this.author_id);
	    json.put("name", this.name);
	    json.put("alias", this.alias);
	    json.put("country_of_birth", this.country_of_birth);
	    
	    return json;
	  }
	  
	  @Override
	  public String toString()
	  {
	    return "author_id: "+this.author_id + "\n "+
	        "Name: "+this.name + "";
	  }
	
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getCountry_of_birth() {
		return country_of_birth;
	}
	public void setCountry_of_birth(String country_of_birth) {
		this.country_of_birth = country_of_birth;
	}
}
