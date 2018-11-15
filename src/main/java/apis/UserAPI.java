package apis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import controllers.UserController;
import models.User;

//Sets the path to base URL + /hello
@Path("/users")
public class UserAPI {

  // This method is called if TEXT_PLAIN is request
  @GET
  @Path("/mundo/{varX}")
  @Produces(MediaType.TEXT_PLAIN)
  public String sayPlainTextHello(@PathParam("varX") String varX, @Context HttpServletRequest req) {
    System.out.println(req.getParameter("param"));
    System.out.println("varX: ");
    System.out.println(varX);
    return "Hello Jersey mundo";
  }
  
  @GET
  @Path("/users")
  @Produces(MediaType.APPLICATION_JSON)
  public String usersJSON(@Context HttpServletRequest req) {
    return "Hola mundo";
  }

  // This method is called if XML is request
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public String sayXMLHello(@Context HttpServletRequest req) {
    HttpSession session= req.getSession();
      System.out.println(session.getAttribute("hola"));
      if(session == null)
        return "{\"err\":\"NADA\"}";
    return "{\"name\":\""+session.getAttribute("hola")+"\"}";
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