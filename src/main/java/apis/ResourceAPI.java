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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import controllers.ResourceController;
import models.Resource;

//Sets the path to base URL + /hello
@Path("/resources")
public class ResourceAPI {
  private ResourceController resourceController = new ResourceController();
  private JSONParser parser = new JSONParser();
  
  @GET
  @Path("/all")
  @Produces(MediaType.APPLICATION_JSON)
  public String getAll(@Context HttpServletRequest req, @Context HttpServletResponse res)
      throws ClassNotFoundException {
    try {
      Resource resources[];
      resources = this.resourceController.getAll();

      return this.resourceController.arrayToJSON(resources);
    } catch (SQLException e) {
      return Response.getJSONError("Error al busca el recurso", 500, res);
    }
  }
  
  @GET
  @Path("/repository/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getAllByRepository(@PathParam("id") int id, @Context HttpServletRequest req, @Context HttpServletResponse res)
      throws ClassNotFoundException {
	  HttpSession session = req.getSession(false);

      if (session != null)
          if (session.getAttribute("email") == null)
              return Response.getJSONError("Necesitas iniciar sesión", 400, res);
      
    try {
      
      JSONArray resources = this.resourceController.getAllByRepositoryIdJSON(id);

      return resources.toString();
    } catch (SQLException e) {
      return Response.getJSONError("Error al busca el recurso", 500, res);
    }
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getById(@PathParam("id") int id, @Context HttpServletRequest req, @Context HttpServletResponse res)
      throws ClassNotFoundException, SQLException {

    Resource resource = this.resourceController.getById(id);

    if (resource == null)
      return Response.getJSONError("El recurso con el id " + id + " no encontrado", 404, res);

    return resource.toJSON();
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

        Object title = json.get("title");
        Object description = json.get("description");
        Object size = json.get("size");
        Object repository_id = json.get("repository_id");
        Object type = json.get("type");
        Object url = json.get("url");

        if (title == null || description == null || size == null ||
            repository_id == null || type == null || url == null
          )
            return Response.getJSONError("Parámetros incompletos para registrar el recurso", 400, res);

        if(!(this.resourceController.isInt(size.toString()) || this.resourceController.isInt(repository_id.toString()) ||
            this.resourceController.isInt(type.toString())))
            return Response.getJSONError("Parámetros incorrectos para registrar el recurso", 400, res);

        Resource resource = new Resource(
              title.toString(),
              description.toString(),
              Integer.parseInt(size.toString()),
              Integer.parseInt(repository_id.toString()),
              Integer.parseInt(type.toString()),
              url.toString()
            );

        try {
            if (this.resourceController.create(resource))
                return resource.toJSON();
            else
                return Response.getJSONError("No se pudo crear la categoría", 400, res);
        } catch (SQLException e) {
            return Response.getJSONError(e.getMessage(), 400, res);
        }
    }
}