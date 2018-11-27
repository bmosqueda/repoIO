package models;

import org.json.simple.JSONObject;

public class Resource extends Model {
	private int resource_id;
	private String title;
	private String description;
	private int size; //On MB
	private int repository_id;
	private int type;
	private String url;


	public Resource(int resource_id, String title, String description, int size, int repository_id, int type,
			String url) {
		super();
		this.resource_id = resource_id;
		this.title = title;
		this.description = description;
		this.size = size;
		this.repository_id = repository_id;
		this.type = type;
		this.url = url;
	}

	public Resource(String title, String description, int size, int repository_id, int type, String url) {
		super();
		this.resource_id = resource_id;
		this.title = title;
		this.description = description;
		this.size = size;
		this.repository_id = repository_id;
		this.setType(type);
		this.url = url;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toJSON() {
		JSONObject json = new JSONObject();

		json.put("resource_id", this.resource_id);
		json.put("title", this.title);
		json.put("description", this.description);
		json.put("size", this.size);
		json.put("repository_id", this.repository_id);
		json.put("type", this.type = type);
		json.put("url", this.url);

		return json.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONObject() {
		JSONObject json = new JSONObject();

		json.put("resource_id", this.resource_id);
		json.put("title", this.title);
		json.put("description", this.description);
		json.put("size", this.size);
		json.put("repository_id", this.repository_id);
		json.put("type", this.type);
		json.put("url", this.url);

		return json;
	}

	@Override
	public String toString()
	{
		return "resource_id: "+this.resource_id + "\n "+
				"Title: "+this.title + "";
	}

	public int getResource_id() {
		return resource_id;
	}

	public void setResource_id(int resource_id) {
		this.resource_id = resource_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getRepository_id() {
		return repository_id;
	}

	public void setRepository_id(int repository_id) {
		this.repository_id = repository_id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


}
