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

import controllers.RepositoryController;
import models.Repository;

//Sets the path to base URL + /hello
@Path("/repositories")
public class RepositoryAPI {
	private RepositoryController repositoryController = new RepositoryController();
	private JSONParser parser = new JSONParser();

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll(@Context HttpServletRequest req, @Context HttpServletResponse res)
			throws ClassNotFoundException {
		try {
			Repository repositories[];
			repositories = this.repositoryController.getAll();

			return this.repositoryController.arrayToJSON(repositories);
		} catch (SQLException e) {
			return Response.getJSONError(e.getMessage(), 500, res);
		}
	}

	@GET
	@Path("/creator/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getByCreator(@PathParam("id") int id, @Context HttpServletRequest req,
			@Context HttpServletResponse res) throws ClassNotFoundException {
		HttpSession session = req.getSession(false);

		if (session != null)
			if (session.getAttribute("email") == null)
				return Response.getJSONError("Necesitas iniciar sesión", 400, res);

		try {
			Repository repositories[];
			repositories = this.repositoryController.getAllByCreator(Integer.parseInt(session.getAttribute("user_id").toString()));

			return this.repositoryController.arrayToJSON(repositories);
		} catch (SQLException e) {
			return Response.getJSONError(e.getMessage(), 500, res);
		}
	}

	@GET
	@Path("/category/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getByCategory(@PathParam("id") int id, @Context HttpServletRequest req,
			@Context HttpServletResponse res) throws ClassNotFoundException {
		HttpSession session = req.getSession(false);

		if (session != null)
			if (session.getAttribute("email") == null)
				return Response.getJSONError("Necesitas iniciar sesión", 400, res);
		try {
			Repository repositories[];
			repositories = this.repositoryController.getAllByCategory(id);

			return this.repositoryController.arrayToJSON(repositories);
		} catch (SQLException e) {
			return Response.getJSONError(e.getMessage(), 500, res);
		}
	}

	@GET
	@Path("/area/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getByArea(@PathParam("id") int id, @Context HttpServletRequest req, @Context HttpServletResponse res)
			throws ClassNotFoundException {
		HttpSession session = req.getSession(false);

		if (session != null)
			if (session.getAttribute("email") == null)
				return Response.getJSONError("Necesitas iniciar sesión", 400, res);
		try {
			Repository repositories[];
			repositories = this.repositoryController.getAllByArea(id);

			return this.repositoryController.arrayToJSON(repositories);
		} catch (SQLException e) {
			return Response.getJSONError(e.getMessage(), 500, res);
		}
	}

	@POST
	@Path("/keyword")
	@Produces(MediaType.APPLICATION_JSON)
	public String getByCreator(@Context HttpServletRequest req, String body, @Context HttpServletResponse res)
			throws ClassNotFoundException {
		HttpSession session = req.getSession(false);

		if (session != null)
			if (session.getAttribute("email") == null)
				return Response.getJSONError("Necesitas iniciar sesión", 400, res);

		JSONObject json;

		try {
			json = (JSONObject) this.parser.parse(body);
		} catch (org.json.simple.parser.ParseException e) {
			return Response.getJSONError("Los parámetros deben de venir en formato JSON", 400, res);
		}

		Object keywords = json.get("keywords");

		if (keywords == null)
			return Response.getJSONError("Parámetros incompletos para buscar por tags", 400, res);

		try {
			Repository repositories[];
			repositories = this.repositoryController.getAllByKeywords(keywords.toString());

			return this.repositoryController.arrayToJSON(repositories);
		} catch (SQLException e) {
			return Response.getJSONError(e.getMessage(), 500, res);
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getById(@PathParam("id") int id, @Context HttpServletRequest req, @Context HttpServletResponse res)
			throws ClassNotFoundException, SQLException {

		Repository repository = this.repositoryController.getById(id);

		if (repository == null)
			return Response.getJSONError("Repositorio con el id " + id + " no encontrado", 404, res);

		return repository.toJSON();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String create(@Context HttpServletRequest req, String body, @Context HttpServletResponse res)
			throws ClassNotFoundException {
		HttpSession session = req.getSession(false);

		if (session != null)
			if (session.getAttribute("email") == null)
				return Response.getJSONError("Necesitas iniciar sesión", 400, res);

		JSONObject json;

		try {
			json = (JSONObject) this.parser.parse(body);
		} catch (org.json.simple.parser.ParseException e) {
			return Response.getJSONError("Los parámetros deben de venir en formato JSON", 400, res);
		}

		Object name = json.get("name");
		Object url = json.get("url");
		Object tags = json.get("tags");

		if (url == null || name == null || tags == null)
			return Response.getJSONError("Parámetros incompletos para registrar escuela", 400, res);

		Object creator_id = json.get("creator_id");
		Repository repository = new Repository(Integer.parseInt(session.getAttribute("user_id").toString()),
				name.toString(), url.toString(), tags.toString());

		try {
			if (this.repositoryController.create(repository))
				return repository.toJSON();
			else
				return Response.getJSONError("No se pudo crear la categoría", 400, res);
		} catch (SQLException e) {
			return Response.getJSONError(e.getMessage(), 400, res);
		}
	}
}