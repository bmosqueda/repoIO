package routes;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import controllers.UserController;
import models.User;

// Extend HttpServlet class to create Http Servlet
public class Test extends HttpServlet {

   private String mymsg;

   public void init() throws ServletException {
      mymsg = "Hello World! SERvlet";
   }

   public void doGet(HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException 
   {
	  // Writing message to the web page
	  PrintWriter out = response.getWriter();
	  out.println("<h1>" + mymsg + "</h1>");
	      
	  try {
		UserController userController = new UserController();
		
		User[] users = userController.getAll();
		int userCount = users.length;
		
		for(int i = 0; i < userCount; i++)
		{
			out.println(users[i].toHTML());
		}
	  } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  

      // Setting up the content type of webpage
      response.setContentType("text/html");
   }

   public void destroy() {
      /* leaving empty for now this can be
       * used when we want to do something at the end
       * of Servlet life cycle
       */
   }
}