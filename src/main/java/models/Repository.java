package models;

import org.json.simple.JSONObject;

public class Repository extends Model
{	 
	private int repository_id;
	private int creator_id;
	private String name;
	private String url;
	private String tags;

	//Other
	private Resource resources;
	private String creator_name;
	
	public Repository(int repository_id, int creator_id, String name, String url, String tags, String creator_name) {
		super();
		this.repository_id = repository_id;
		this.creator_id = creator_id;
		this.name = name;
		this.url = url;
		this.tags = tags;
		this.creator_name = creator_name;
	}
	
	public Repository(int creator_id, String name, String url, String tags) {
		super();
		this.creator_id = creator_id;
		this.name = name;
		this.url = url;
		this.tags = tags;
	}
	
	@SuppressWarnings("unchecked")
	 @Override
	 public String toJSON() {
	   JSONObject json = new JSONObject();
	   
	   json.put("repository_id", this.repository_id);
	   json.put("creator_id", this.creator_id);
	   json.put("name", this.name);
	   json.put("url", this.url);
	   json.put("tags", this.tags);
	   json.put("creator_name", this.creator_name);
	   
	   return json.toString();
	 }
	 
	 @SuppressWarnings("unchecked")
	 @Override
	 public JSONObject toJSONObject() {
	   JSONObject json = new JSONObject();
	   
	   json.put("repository_id", this.repository_id);
	   json.put("creator_id", this.creator_id);
	   json.put("name", this.name);
	   json.put("url", this.url);
	   json.put("tags", this.tags);
	   json.put("creator_name", this.creator_name);
	   
	   return json;
	 }
	 
	 @Override
	 public String toString()
	 {
	   return "repository_id: "+this.repository_id + "\n "+
	       "creator_id: "+this.creator_id +
	       "name: "+this.name;
	 }
	
	public int getRepository_id() {
		return repository_id;
	}
	public void setRepository_id(int repository_id) {
		this.repository_id = repository_id;
	}
	public int getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Resource getResources() {
		return resources;
	}
	public void setResources(Resource resources) {
		this.resources = resources;
	}
	public String getCreator_name() {
		return creator_name;
	}
	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}
}