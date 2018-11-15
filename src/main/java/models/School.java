package models;

import org.json.simple.JSONObject;

public class School {
	private int school_id;
	private String name;
	
	public School(String name) {
		super();
		this.name = name;
	}
	
	public School(int id, String name) {
		super();
		this.school_id = id;
		this.name = name;
	}
	
	@SuppressWarnings("unchecked")
	public String toJSON() {
		JSONObject json = new JSONObject();
		json.put("school_id", this.school_id);
		json.put("name", this.name);
		
		return json.toString();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject() {
		JSONObject json = new JSONObject();
		json.put("school_id", this.school_id);
		json.put("name", this.name);
		
		return json;
	}
	
	@Override
	public String toString()
	{
		return "School_id: "+this.school_id + "\n "+
				"Name: "+this.name + "";
	}
	
	public int getSchool_id() {
		return school_id;
	}
	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
