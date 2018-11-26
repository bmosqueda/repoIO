<% 
//Ya está loggueado
response.sendRedirect("/repo.io/iniciar-sesion/");
// if(session.getAttribute("email") != null)
%>
<%! String title = "Repo.io"; %>
<%@ include file="/views/header-no-session.jsp"%>
<h2>Repo.Io</h2>
<h2>El equipo chido :v</h2>
<%@ include file="/views/footer.jsp"%>