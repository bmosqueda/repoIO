package apis;

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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import controllers.UserController;
import models.User;

@Path("/users")
public class UserAPI {
	private UserController userController = new UserController();
	private JSONParser parser = new JSONParser();

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getById(@PathParam("id") int id, @Context HttpServletRequest req, @Context HttpServletResponse res)
			throws ClassNotFoundException, SQLException {

		User user = this.userController.getById(id);

		if (user == null)
			return Response.getJSONError("Usuario con el id " + id + " no encontrado", 404, res);

		return user.toJSON();
	}

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@Context HttpServletRequest req, String body, @Context HttpServletResponse res)
			throws ClassNotFoundException {
		HttpSession session = req.getSession(false);

		// Validaciones de la sesión
		if (session != null)
			if (session.getAttribute("email") != null)
				return Response.getJSONError("Ya estás loggueado", 400, res);
			else
				session.invalidate();

		JSONObject json;
		try {
			json = (JSONObject) this.parser.parse(body);
		} catch (ParseException e) {
			return Response.getJSONError("Los parámetros deben de venir en formato JSON", 400, res);
		}
		Object emailOrAccount = json.get("emailOrAccount");
		Object password = json.get("password");

		if (emailOrAccount == null || password == null)
			return Response.getJSONError("Correo o el número de cuenta y la contraseña son necesarios", 400, res);

		User user = null;
		try {
			user = userController.login(emailOrAccount.toString(), password.toString());
		} catch (SQLException e) {
			return Response.getJSONError(e.getMessage(), 400, res);
		}

		if (user == null)
			return Response.getJSONError("No hay usuario registrado con esa información", 404, res);

		// Start session variables
		session = req.getSession(true);

		session.setAttribute("user_id", user.getUser_id());
		session.setAttribute("email", user.getEmail());
		session.setAttribute("role_id", user.getRole());
		session.setAttribute("school_id", user.getSchool_id());

		return user.toJSON();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String create(@Context HttpServletRequest req, String body, @Context HttpServletResponse res)
			throws ClassNotFoundException {
		HttpSession session = req.getSession(false);

		if (session != null)
			if (session.getAttribute("email") != null)
				return Response.getJSONError("Ya estás loggueado", 400, res);
			else
				session.invalidate();

		JSONObject json;
		try {
			json = (JSONObject) this.parser.parse(body);
		} catch (ParseException e) {
			return Response.getJSONError("Los parámetros deben de venir en formato JSON", 400, res);
		}

		Object account_number = json.get("account_number");
		Object name = json.get("name");
		Object email = json.get("email");
		Object password = json.get("password");
		Object role = json.get("role");
		Object school_id = json.get("school_id");

		if (email == null || name == null || password == null || role == null || school_id == null
				|| account_number == null)
			return Response.getJSONError("Parámetros incompletos para registrar usuario", 400, res);

		if (!(UserController.isInt(role.toString()) || UserController.isInt(school_id.toString())))
			return Response.getJSONError("Parámetros incorrectos para registrar usuario", 400, res);

		User user = new User(account_number.toString(), name.toString(), email.toString(), password.toString(),
				Integer.parseInt(role.toString()), Integer.parseInt(school_id.toString()));

		try {
			if (this.userController.create(user)) {
				// Start session variables
				session = req.getSession(true);
				session.setAttribute("user_id", user.getUser_id());
				session.setAttribute("email", user.getEmail());
				session.setAttribute("role_id", user.getRole());
				session.setAttribute("school_id", user.getSchool_id());

				return user.toJSON();
			} else
				return Response.getJSONError("No se pudo crear el usuario", 400, res);
		} catch (SQLException e) {
			return Response.getJSONError(e.getMessage(), 400, res);
		}
	}
}