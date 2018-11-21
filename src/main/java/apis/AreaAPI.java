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

import controllers.AreaController;
import models.Area;

//Sets the path to base URL + /hello
@Path("/areas")
public class AreaAPI {
  private AreaController areaController = new AreaController();
  private JSONParser parser = new JSONParser();
  
  @GET
  @Path("/all")
  @Produces(MediaType.APPLICATION_JSON)
  public String getAll(@Context HttpServletRequest req, @Context HttpServletResponse res)
      throws ClassNotFoundException {
    try {
      Area areas[];
      areas = this.areaController.getAll();

      return this.areaController.arrayToJSON(areas);
    } catch (SQLException e) {
      return Response.getJSONError("Error al busca el área", 500, res);
    }
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getById(@PathParam("id") int id, @Context HttpServletRequest req, @Context HttpServletResponse res)
      throws ClassNotFoundException, SQLException {

    Area area = this.areaController.getById(id);

    if (area == null)
      return Response.getJSONError("El área con el id " + id + " no encontrado", 404, res);

    return area.toJSON();
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
            return Response.getJSONError("Parámetros incompletos para registrar el área", 400, res);

        Area area = new Area(name.toString());

        try {
            if (this.areaController.create(area))
                return area.toJSON();
            else
                return Response.getJSONError("No se pudo crear la categoría", 400, res);
        } catch (SQLException e) {
            return Response.getJSONError(e.getMessage(), 400, res);
        }
    }
}