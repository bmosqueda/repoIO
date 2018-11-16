/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-11-16 01:18:40 UTC
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
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/views/footer.jsp", Long.valueOf(1542330839000L));
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
      out.write("<head>\n");
      out.write("<meta charset=\"utf-8\">\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("\n");
      out.write("<!-- <link rel=\"icon\" href=\"/public/images/Logos/transparent-ico.png\"> -->\n");
      out.write("\n");
      out.write("<!-- Bulma -->\n");
      out.write("<link rel=\"stylesheet\" href=\"/repo.io/public/css/bulma.min.css\">\n");
      out.write("\n");
      out.write("<title>Inicio de sesión</title>\n");
      out.write("</head>\n");
      out.write("<body class=\"is-bold is-light\">\n");
      out.write("\n");
      out.write("\t<link rel=\"stylesheet\" href=\"/repo.io/public/css/login.css\">\n");
      out.write("\t<section class=\"hero is-success is-fullheight\">\n");
      out.write("\t\t<div class=\"hero-body\">\n");
      out.write("\t\t\t<div class=\"container has-text-centered\">\n");
      out.write("\t\t\t\t<div class=\"column is-4 is-offset-4\">\n");
      out.write("\t\t\t\t\t<h3 class=\"title has-text-grey\">Inicio de sesión</h3>\n");
      out.write("\t\t\t\t\t<p class=\"subtitle has-text-grey\">Bienvenido</p>\n");
      out.write("\t\t\t\t\t<div class=\"box\">\n");
      out.write("\t\t\t\t\t\t<figure class=\"avatar\">\n");
      out.write("\t\t\t\t\t\t\t<img src=\"/repo.io/public/images/login.png\">\n");
      out.write("\t\t\t\t\t\t</figure>\n");
      out.write("\t\t\t\t\t\t<form>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"field\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"control\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<input class=\"input is-large\" type=\"email\"\n");
      out.write("\t\t\t\t\t\t\t\t\t\tplaceholder=\"Correo electrónico\" autofocus=\"\">\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t<div class=\"field\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"control\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<input class=\"input is-large\" type=\"password\"\n");
      out.write("\t\t\t\t\t\t\t\t\t\tplaceholder=\"Contraseña\">\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"field is-pulled-right\">\n");
      out.write("\t\t\t\t\t\t\t\t<label class=\"checkbox\"> <input type=\"checkbox\">\n");
      out.write("\t\t\t\t\t\t\t\t\tRecordarme\n");
      out.write("\t\t\t\t\t\t\t\t</label>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<button class=\"button is-block is-info is-large is-fullwidth\">Iniciar\n");
      out.write("\t\t\t\t\t\t\t\tsesión</button>\n");
      out.write("\t\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<p class=\"has-text-grey\">\n");
      out.write("\t\t\t\t\t\tNo tienes cuenta <a href=\"../\">¿Regístrate?</a> &nbsp;·&nbsp;\n");
      out.write("\t\t\t\t\t\t<!-- <a href=\"../\">Forgot Password</a> &nbsp;·&nbsp; -->\n");
      out.write("\t\t\t\t\t\t<!-- <a href=\"../\">Need Help?</a> -->\n");
      out.write("\t\t\t\t\t</p>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</section>\n");
      out.write("\t");
      out.write("<div class=\"has-text-centered has-background-link\">\n");
      out.write("      <p \n");
      out.write("        class=\"footer-title has-text-light\"\n");
      out.write("        style=\"margin: auto;padding: 0px;\">5° D\n");
      out.write("      </p>\n");
      out.write("      <p \n");
      out.write("        class=\"footer-title has-text-light\" \n");
      out.write("        style=\"margin: auto;padding-bottom: : 15px;\">Equipo 3</p>\n");
      out.write("</body>\n");
      out.write("<script>\n");
      out.write("  document.addEventListener('DOMContentLoaded', () => {\n");
      out.write("\n");
      out.write("    // Get all \"navbar-burger\" elements\n");
      out.write("    const $navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0);\n");
      out.write("\n");
      out.write("    // Check if there are any navbar burgers\n");
      out.write("    if ($navbarBurgers.length > 0) {\n");
      out.write("\n");
      out.write("      // Add a click event on each of them\n");
      out.write("      $navbarBurgers.forEach( el => {\n");
      out.write("        el.addEventListener('click', () => {\n");
      out.write("\n");
      out.write("          // Get the target from the \"data-target\" attribute\n");
      out.write("          const target = el.dataset.target;\n");
      out.write("          const $target = document.getElementById(target);\n");
      out.write("\n");
      out.write("          // Toggle the \"is-active\" class on both the \"navbar-burger\" and the \"navbar-menu\"\n");
      out.write("          el.classList.toggle('is-active');\n");
      out.write("          $target.classList.toggle('is-active');\n");
      out.write("\n");
      out.write("        });\n");
      out.write("      });\n");
      out.write("    }\n");
      out.write("  });\n");
      out.write("</script>\n");
      out.write("</html>");
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
