package models;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public abstract class Model 
{
	public abstract String toJSON();
	
	public abstract JSONObject toJSONObject();
	
	public static JSONArray arrayToJSONArray(String strs[]) {
		ArrayList<String> strings = new ArrayList<String>();
		
		for(String temp : strs) {
			strings.add(temp);
		}
		JSONArray json = new JSONArray();
		json.addAll(strings);
		
		return json;
	}
}