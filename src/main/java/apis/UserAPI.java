package apis;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getById(@PathParam("id") int id, @Context HttpServletRequest req, @Context HttpServletResponse res) {
		try {
			UserController userController = new UserController();
			User user = userController.getAllInfoById(id);
			
			if(user == null) 
				return Response.getJSONError("Usuario con el id " + id + " no encontrado", 404, res);
			 
			return user.toJSON();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.getJSONError("Error al busca el usuario", 500, res);
	}

	@SuppressWarnings("unchecked")
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public String usersJSON(@Context HttpServletRequest req, String body, @Context HttpServletResponse res) {
		HttpSession session = req.getSession(false);
		
		if(session != null) 
			if(session.getAttribute("email") != null)
				return Response.getJSONError("Ya estás loggueado", 400, res);
			else 
				session.invalidate();
		
			//return (String)session.getAttribute("email");
		
		try {
			UserController userController = new UserController();
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(body);
			if(json.get("email") == null || json.get("password") == null)
				return Response.getJSONError("Correo y contraseña necesarios", 400, res);
			
			User user = userController.login(json.get("email").toString(), json.get("password").toString());
			
			if(user == null) 
				return Response.getJSONError("No hay usuario registrado con esa información", 404, res); 
			
			//Start session variables
			session = req.getSession(true);
	        session.setAttribute("user_id", user.getUser_id());
	        session.setAttribute("email", user.getEmail());
	        session.setAttribute("role_id", user.getRole());
	        session.setAttribute("school_id", user.getSchool_id());

			return user.toJSON();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.getJSONError("Error al iniciar sesión", 500, res);
	}

	// This method is called if XML is request
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String insert(@Context HttpServletRequest req, String body, @Context HttpServletResponse res) {
		HttpSession session = req.getSession(false);
		
		if(session != null) 
			if(session.getAttribute("email") != null)
				return Response.getJSONError("Ya estás loggueado", 400, res);
			else 
				session.invalidate();
		try {
			UserController userController = new UserController();
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(body);
			
			if(json.get("email") == null || json.get("password") == null || json.get("school") == null || json.get("account") == null)
				return Response.getJSONError("Correo y contraseña necesarios", 400, res);
			
			User user = new User(json.get("account").toString(),
					json.get("name").toString(),
					json.get("email").toString(),
					json.get("password").toString(),
					Integer.parseInt(json.get("school").toString())
					);
			try {
				//Validar email
				userController.insert(user);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return Response.getJSONError(e.getMessage(), 400, res);
			}
			
			//Start session variables
			session = req.getSession(true);
	        session.setAttribute("user_id", user.getUser_id());
	        session.setAttribute("email", user.getEmail());
	        session.setAttribute("role_id", user.getRole());
	        session.setAttribute("school_id", user.getSchool_id());

			return user.toJSON();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.getJSONError("Error al iniciar sesión", 500, res);
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