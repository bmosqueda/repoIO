package apis;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import controllers.CategoryController;
import models.Category;

//Sets the path to base URL + /hello
@Path("/categories")
public class CategoryAPI {
  private CategoryController categoryController = new CategoryController();
  private JSONParser parser = new JSONParser();
  
  @GET
  @Path("/all")
  @Produces(MediaType.APPLICATION_JSON)
  public String getAll(@Context HttpServletRequest req, @Context HttpServletResponse res)
      throws ClassNotFoundException {
    try {
      Category categories[];
      categories = this.categoryController.getAll();

      return this.categoryController.arrayToJSON(categories);
    } catch (SQLException e) {
      return Response.getJSONError(e.getMessage(), 500, res);
    }
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getById(@PathParam("id") int id, @Context HttpServletRequest req, @Context HttpServletResponse res)
      throws ClassNotFoundException, SQLException {

    Category category = this.categoryController.getById(id);

    if (category == null)
      return Response.getJSONError("La categoría con el id " + id + " no encontrado", 404, res);

    return category.toJSON();
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

      Category category = new Category(name.toString());

      try {
        if (this.categoryController.create(category))
          return category.toJSON();
        else
          return Response.getJSONError("No se pudo crear la categoría", 400, res);
      } catch (SQLException e) {
        return Response.getJSONError(e.getMessage(), 400, res);
      }
    }

  @PUT
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public String update(@PathParam("id") int id, @Context HttpServletRequest req, String body, @Context HttpServletResponse res)
      throws ClassNotFoundException {
    HttpSession session = req.getSession(false);

    if (session != null)
    {
      if (session.getAttribute("email") == null)
        return Response.getJSONError("Necesitas iniciar sesión", 400, res);
    }
    else
      return Response.getJSONError("Necesitas iniciar sesión", 400, res);
    
    if(Integer.parseInt(session.getAttribute("role_id").toString()) != 1)
      return Response.getJSONError("Sólo administradores", 403, res);
    
    Category category = null;
    try {
      category = this.categoryController.getById(id);
    } catch (SQLException e1) {
      System.out.println(e1.getMessage());
      return Response.getJSONError("Problema al buscar la categoría", 404, res);
    }

    if (category == null)
      return Response.getJSONError("La categoría con el id " + id + " no encontrado", 404, res);

    JSONObject json;

    try {
      json = (JSONObject) this.parser.parse(body);
    } catch (org.json.simple.parser.ParseException e) {
      return Response.getJSONError("Los parámetros deben de venir en formato JSON", 400, res);
    }

    Object name = json.get("name");

    if (name == null)
      return Response.getJSONError("Parámetros incompletos para registrar el área", 400, res);

    category.setName(name.toString());

    try {
      if (this.categoryController.update(category))
        return category.toJSON();
      else
        return Response.getJSONError("No se pudo crear la categoría", 400, res);
    } catch (SQLException e) {
      return Response.getJSONError(e.getMessage(), 400, res);
    }
  }

  @DELETE
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public String delete(@PathParam("id") int id, @Context HttpServletRequest req, @Context HttpServletResponse res)
      throws ClassNotFoundException, SQLException {
    HttpSession session = req.getSession(false);
    
    if (session != null)
    {
      if (session.getAttribute("email") == null)
        return Response.getJSONError("Necesitas iniciar sesión", 400, res);
    }
    else
      return Response.getJSONError("Necesitas iniciar sesión", 400, res);
    
    if(Integer.parseInt(session.getAttribute("role_id").toString()) != 1)
      return Response.getJSONError("Sólo administradores", 403, res);

    Category category = this.categoryController.getById(id);

    if (category == null)
      return Response.getJSONError("La categoría con el id " + id + " no encontrado", 404, res);
    
    try {
        if(this.categoryController.hasChilds(category))
          return Response.getJSONError("No se puede eliminar el área porque hay que recursos registrados que usan este elemento", 400, res);
        
      if (this.categoryController.delete(category))
        return category.toJSON();
      else
        return Response.getJSONError("No se pudo eliminar el área", 400, res);
    } catch (SQLException e) {
      return Response.getJSONError(e.getMessage(), 400, res);
    }
  }
}