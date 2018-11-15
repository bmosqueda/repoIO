/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-11-15 15:32:42 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/views/header.jsp", Long.valueOf(1542295864000L));
    _jspx_dependants.put("/views/footer.jsp", Long.valueOf(1542295715000L));
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<html>\n");
      out.write("  <head> \n");
      out.write("    <meta charset=\"utf-8\">\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("    //<link rel=\"icon\" href=\"/public/images/Logos/transparent-ico.png\">\n");
      out.write("\n");
      out.write("    <!-- Bulma -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"/repo.io/public/css/bulma.min.css\">\n");
      out.write("  \t<title>Repo.IO</title>\n");
      out.write("  </head>\n");
      out.write("  <body class=\"is-bold is-light\">\n");
      out.write("    <!-- NavBar -->\n");
      out.write("      <nav class=\"navbar is-white\">\n");
      out.write("        <div class=\"navbar-brand\">\n");
      out.write("          <a class=\"navbar-item\" href=\"/\">\n");
      out.write("            <img class=\"logo-no-mobile\" src=\"/public/images/Logos/logo-10.jpeg\" alt=\"Patrimonio Social 2018 ©\"  width=\"300\" height=\"2000\">\n");
      out.write("            <figure class=\"logo-mobile image is-32x32 is-pulled-right text-mobile\">\n");
      out.write("              <img src=\"/public/images/Logos/transparent-ico.png\" alt=\"Sociedad Patrimonial 2018 ©\">\n");
      out.write("          </figure>\n");
      out.write("          </a>\n");
      out.write("          <div class=\"navbar-burger burger\" data-target=\"navbar\">\n");
      out.write("            <span></span>\n");
      out.write("            <span></span>\n");
      out.write("            <span></span>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div id=\"navbar\" class=\"navbar-menu\">\n");
      out.write("          <div class=\"navbar-start\">\n");
      out.write("          </div>\n");
      out.write("          <div class=\"navbar-end\">\n");
      out.write("            <a class=\"navbar-item  is-purple has-text-weight-semibold\" href=\"/\">\n");
      out.write("              Inicio\n");
      out.write("            </a>\n");
      out.write("            <a class=\"navbar-item is-purple has-text-weight-semibold\" href=\"/nosotros/\">\n");
      out.write("              Nosotros\n");
      out.write("            </a>\n");
      out.write("            <a class=\"navbar-item is-purple has-text-weight-semibold\" href=\"/servicios/\">\n");
      out.write("              Servicios\n");
      out.write("            </a>\n");
      out.write("            <a class=\"navbar-item is-purple has-text-weight-semibold\" href=\"/privacidad/\">\n");
      out.write("              Aviso de privacidad\n");
      out.write("            </a>\n");
      out.write("            <a class=\"navbar-item is-purple has-text-weight-semibold\" href=\"/requisitos/\">\n");
      out.write("              Requisitos\n");
      out.write("            </a>\n");
      out.write("            <a class=\"navbar-item is-purple has-text-weight-semibold\" href=\"/preguntas-frecuentes/\">\n");
      out.write("              Preguntas frecuentes\n");
      out.write("            </a>\n");
      out.write("            <a class=\"navbar-item is-purple has-text-weight-semibold\" href=\"/contacto/\">\n");
      out.write("              Contacto\n");
      out.write("            </a>\n");
      out.write("            <a class=\"navbar-item is-purple has-text-weight-semibold\" href=\"/acceso/\">\n");
      out.write("              Acceso\n");
      out.write("            </a>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("      </nav>\n");
      out.write("    <!-- NavBar Fin -->");
      out.write("\n");
      out.write("<h1>Hola mundo</h1>\n");
      out.write("<footer class=\"footer has-background-info\" style=\"padding-bottom: 4rem;\">\n");
      out.write("\t<div class=\"content has-text-centered has-text-light\">\n");
      out.write("\t\t<p>\n");
      out.write("\t\t\t<strong>Equipo 3</strong>\n");
      out.write("\t\t</p>\n");
      out.write("\t\t<span>Brandon Alejandro Mosqueda González</span><br> <span>Daniel\n");
      out.write("\t\t\tSalomón Marmolejo Contreras</span><br> <span>Rebeca Noelia\n");
      out.write("\t\t\tFlores Urtiz</span><br> <span>Isaac Ramírez Martínez </span><br>\n");
      out.write("\t</div>\n");
      out.write("</footer>\n");
      out.write("</body>\n");
      out.write("<script src=\"/repo.io/public/js/vue.min.js\"></script>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
