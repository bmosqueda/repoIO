package models;

import org.json.simple.JSONObject;

public abstract class Model 
{
	public abstract String toJSON();
	
	public abstract JSONObject toJSONObject();
}