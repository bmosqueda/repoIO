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

import controllers.SchoolController;
import models.School;

//Sets the path to base URL + /hello
@Path("/schools")
public class SchoolAPI {
  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public String getAll(@Context HttpServletRequest req, @Context HttpServletResponse res) {
    try {
      SchoolController schoolController = new SchoolController();
      School schools[] = schoolController.getAll();
      
      return schoolController.schoolsToJSON(schools);

    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return Response.getJSONError("Error al busca la Escuela", 500, res);
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getById(@PathParam("id") int id, @Context HttpServletRequest req, @Context HttpServletResponse res) {
    try {
      SchoolController schoolController = new SchoolController();
      School school = schoolController.getById(id);
      
      if(school == null) 
        return Response.getJSONError("Escuela con el id " + id + " no encontrado", 404, res);
      
      return school.toJSON();

    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return Response.getJSONError("Error al busca la Escuela", 500, res);
  }
}