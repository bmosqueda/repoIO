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

import controllers.AuthorController;
import models.Author;

//Sets the path to base URL + /hello
@Path("/authors")
public class AuthorAPI {
  private AuthorController authorController = new AuthorController();
  private JSONParser parser = new JSONParser();
  
  @GET
  @Path("/all")
  @Produces(MediaType.APPLICATION_JSON)
  public String getAll(@Context HttpServletRequest req, @Context HttpServletResponse res)
      throws ClassNotFoundException {
    try {
      Author authors[];
      authors = this.authorController.getAll();

      return this.authorController.arrayToJSON(authors);
    } catch (SQLException e) {
      return Response.getJSONError(e.getMessage(), 500, res);
    }
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getById(@PathParam("id") int id, @Context HttpServletRequest req, @Context HttpServletResponse res)
      throws ClassNotFoundException, SQLException {

    Author author = this.authorController.getById(id);

    if (author == null)
      return Response.getJSONError("Autor con el id " + id + " no encontrado", 404, res);

    return author.toJSON();
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
        Object alias = json.get("alias");
        Object country_of_birth = json.get("country_of_birth");

        if (name == null || alias == null || country_of_birth == null)
            return Response.getJSONError("Parámetros incompletos para registrar el autor", 400, res);

        Author author = new Author(name.toString(), alias.toString(), country_of_birth.toString());

        try {
            if (this.authorController.create(author))
                return author.toJSON();
            else
                return Response.getJSONError("No se pudo crear el autor", 400, res);
        } catch (SQLException e) {
            return Response.getJSONError(e.getMessage(), 400, res);
        }
    }
}