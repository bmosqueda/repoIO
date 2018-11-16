<% 
//HttpSession session = request.getSession(false);
if(session.getAttribute("email") == null)
	response.sendRedirect("/repo.io/iniciar-sesion/?beforePage="+currentPage);
%>