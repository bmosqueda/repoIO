<%@ page session="false" %>
<% 
HttpSession nsession = request.getSession(false);
if(nsession != null)
{
	out.println("loggueado");
	out.println(nsession.getAttribute("user_id"));
}

	response.sendRedirect("/repo.io/iniciar-sesion/");
%>