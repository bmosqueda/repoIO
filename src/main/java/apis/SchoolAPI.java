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

import controllers.SchoolController;
import models.School;

//Sets the path to base URL + /hello
@Path("/schools")
public class SchoolAPI {
	private SchoolController schoolController = new SchoolController();
	private JSONParser parser = new JSONParser();
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll(@Context HttpServletRequest req, @Context HttpServletResponse res)
			throws ClassNotFoundException {
		try {
			School schools[];
			schools = this.schoolController.getAll();

			return this.schoolController.arrayToJSON(schools);
		} catch (SQLException e) {
			return Response.getJSONError("Error al busca la Escuela", 500, res);
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getById(@PathParam("id") int id, @Context HttpServletRequest req, @Context HttpServletResponse res)
			throws ClassNotFoundException, SQLException {

		School school = this.schoolController.getById(id);

		if (school == null)
			return Response.getJSONError("La escuela con el id " + id + " no encontrado", 404, res);

		return school.toJSON();
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

        if (name == null)
            return Response.getJSONError("Parámetros incompletos para registrar escuela", 400, res);

        School school = new School(name.toString());

        try {
            if (this.schoolController.create(school))
                return school.toJSON();
            else
                return Response.getJSONError("No se pudo crear la escuela", 400, res);
        } catch (SQLException e) {
            return Response.getJSONError(e.getMessage(), 400, res);
        }
    }
}