package apis;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

import controllers.UserController;
import models.User;

//Sets the path to base URL + /hello
@Path("/users")
public class UserAPI {
	private final String nullJSON = "{\"message\": \"null\"}";

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getById(@PathParam("id") int id, @Context HttpServletRequest req) {
		try {
			UserController userController = new UserController();

			return userController.getAllInfoById(id).toJSON();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.nullJSON;
	}

	@SuppressWarnings("unchecked")
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public String usersJSON(@Context HttpServletRequest req, String body) {
		try {
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(body);
			json.put("Hola", "mundo");
			
			return json.toString();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Hola mundo";
	}

	// This method is called if XML is request
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String sayXMLHello(@Context HttpServletRequest req) {
		HttpSession session= req.getSession();
		System.out.println(session.getAttribute("hola"));
		if(session == null)
			return "{\"err\":\"NADA\"}";
		return "{\"name\":\""+session.getAttribute("hola")+"\"}";
	}

	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello(@Context HttpServletRequest req) {
		HttpSession session= req.getSession();
		System.out.println(session.getAttribute("hola"));
		if(session == null)
			return "{\"err\":\"NADA\"}";
		return "{\"name\":\""+session.getAttribute("hola")+"\"}";
	}
}