<%! String currentPage = "repositorio/crear/"; %>
<%@ include file="../require-session.jsp"%>
<% 
  if(Integer.parseInt(session.getAttribute("role_id").toString()) != 1)
    response.sendRedirect("/repo.io/buscar/");
%>
<%! String title = "Repo.io"; %>
<%@ include file="../header.jsp"%>

<%@ include file="form-create.jsp"%>
<%@ include file="../footer.jsp"%>
<script src="/repo.io/public/js/validator.js"></script>
<script src="/repo.io/public/js/repository/create.js"></script>
</body>
</html>