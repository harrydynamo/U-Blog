/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-08-10 19:01:32 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.ublog;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Create_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");

    /*If user tries to click on browser back button then he/ she should not be able to access this page*/
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!--\r\n");
      out.write("\tTODO: 4.15. Check if user is logged in or not. If not then redirect user to the default page i.e index.jsp.\r\n");
      out.write("\t(Hint: You need to handle NullPointerException.)\r\n");
      out.write("\t(Hint: Make use of the email id stored in the session object to check if user is logged in or not.)\r\n");
      out.write("-->\r\n");
      out.write("\r\n");
      out.write("<!--\r\n");
      out.write("\tTODO: 4.16. Design the \"Create Post\" page with the following properties.\r\n");
      out.write("\t    1. Title of the page should be \"Create Post\"\r\n");
      out.write("\t    2. Provide method and action attributes of the form tag such that when user submit\r\n");
      out.write("\t        the form, the doPost() method of PostServlet get invoked.\r\n");
      out.write("\t    3. Provide \"User Email:\" label along with the prepopulate emailId of user as shown on the learn platform.\r\n");
      out.write("\t        (Hint: You need to use emailId stored in the session object along with JSP expression tag.)\r\n");
      out.write("\t    4. Provide \"Blog Title:\" label along with the text field as shown on the learn platform.\r\n");
      out.write("\t    5. Also, for the \"Blog Title:\" text field, provide max length as 200, placeholder as \"Title\" and\r\n");
      out.write("\t        make this field required.\r\n");
      out.write("\t    6. Provide \"Blog Tag:\" label along with the text field as shown on the learn platform.\r\n");
      out.write("        7. Also, for the \"Blog Tag:\" text field, provide max length as 10, placeholder as \"java\" and\r\n");
      out.write("            make this field required.\r\n");
      out.write("\t    8. Provide \"Blog Description:\" label along with the text area as shown on the learn platform.\r\n");
      out.write("        9. Also, for the \"Blog Description:\" text area, provide max length as 1000, placeholder as\r\n");
      out.write("            \"Post Description\", rows as 15, cols as 75, and make this field required.\r\n");
      out.write("        10. Use the table tag to align the labels and text fields.\r\n");
      out.write("        11. Provide \"Post\" submit button.\r\n");
      out.write("        12. Provide a link to the \"Home Page\".\r\n");
      out.write("-->\r\n");
      out.write("\r\n");
      out.write("<!--\r\n");
      out.write("    TODO: 4.17. If the user is logged in then display the string before @ in the user email id\r\n");
      out.write("    on this web page. For example, if email id is example@gmail.com, then display \"Logged In as example\"\r\n");
      out.write("    in the top left corner of the web page as shown on the learn platform.\r\n");
      out.write("-->\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>Create Post</title>\r\n");
      out.write("    </head>\r\n");
      out.write("\r\n");
      out.write("    <body>\r\n");
      out.write("        ");

            if (session.getAttribute("email") == null) {
                response.sendRedirect("index.jsp");
                return;
            }
        
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <p>Logged In as ");
      out.print( session.getAttribute("email").toString().substring(0, session.getAttribute("email").toString().indexOf("@")) );
      out.write(" </p>\r\n");
      out.write("\r\n");
      out.write("        <form method=\"post\" action=\"/ublog/post\" >\r\n");
      out.write("            <table>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td><p>User Email:</p></td>\r\n");
      out.write("                    <td>");
      out.print( session.getAttribute("email") );
      out.write("</td>\r\n");
      out.write("                </tr>\r\n");
      out.write("\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td><p>Blog Title:</p></td>\r\n");
      out.write("                    <td><input type=\"text\" name=\"title\" placeholder=\"Title\" required></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td><p>Blog Tag:</p></td>\r\n");
      out.write("                    <td><input type=\"text\" name=\"tag\" placeholder=\"java\" required></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td><p>Blog Description:</p></td>\r\n");
      out.write("                    <td><textarea type=\"text\" name=\"description\" placeholder=\"Post Description\" rows=\"15\" cols=\"75\" required></textarea></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("\r\n");
      out.write("            </table>\r\n");
      out.write("            <p>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${message}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</p>\r\n");
      out.write("            <button type=\"submit\">Post</button>\r\n");
      out.write("            <a href=\"#\">Home Page</a>\r\n");
      out.write("        </form>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
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
