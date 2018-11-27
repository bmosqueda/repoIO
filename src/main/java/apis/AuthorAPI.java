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
    
    Author author = null;
    try {
      author = this.authorController.getById(id);
    } catch (SQLException e1) {
      System.out.println(e1.getMessage());
      return Response.getJSONError("Problema al buscar el autor", 404, res);
    }

    if (author == null)
      return Response.getJSONError("El autor a con el id " + id + " no encontrado", 404, res);

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

    author.setName(name.toString());
    author.setAlias(alias.toString());
    author.setCountry_of_birth(country_of_birth.toString());

    try {
      if (this.authorController.update(author))
        return author.toJSON();
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

    Author author = this.authorController.getById(id);

    if (author == null)
      return Response.getJSONError("Autor con el id " + id + " no encontrado", 404, res);
    
    try {
        if(this.authorController.hasChilds(author))
          return Response.getJSONError("No se puede eliminar el autor porque hay que recursos registrados que usan este elemento", 400, res);
        
      if (this.authorController.delete(author))
        return author.toJSON();
      else
        return Response.getJSONError("No se pudo eliminar el área", 400, res);
    } catch (SQLException e) {
      return Response.getJSONError(e.getMessage(), 400, res);
    }
  }
}