package apis;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

public class Response 
{
	public static String getJSONError(String message, int status, HttpServletResponse res) {
		JSONObject json = new JSONObject();
		json.put("error", message);
		
		res.setStatus(status);
	    try {
	        res.flushBuffer();
	    }catch(Exception e){
	    	System.out.println("Erroal setter el status en Response class");
	    }
		
		return json.toString();
	}
}