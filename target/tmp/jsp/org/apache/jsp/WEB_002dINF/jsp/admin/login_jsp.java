package org.apache.jsp.WEB_002dINF.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../lh/common/common_back_css.jsp", out, false);
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/third-party/reset/reset.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/css/login.css\" title=\"v\" />\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write(".cd-close-form {\r\n");
      out.write("  /* form X button on top right */\r\n");
      out.write("  display: block;\r\n");
      out.write("  position: absolute;\r\n");
      out.write("  width: 40px;\r\n");
      out.write("  height: 40px;\r\n");
      out.write("  right: 0;\r\n");
      out.write("  top: -40px;\r\n");
      out.write("  background: url(\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/images/back/login/cd-icon-close.svg\") no-repeat center center;\r\n");
      out.write("  text-indent: 100%;\r\n");
      out.write("  white-space: nowrap;\r\n");
      out.write("  overflow: hidden;\r\n");
      out.write("}\r\n");
      out.write(".cd-form label.cd-username {\r\n");
      out.write("  background-image: url(\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/images/login/cd-icon-username.svg\");\r\n");
      out.write("}\r\n");
      out.write(".cd-form label.cd-email {\r\n");
      out.write("  background-image: url(\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/images/login/cd-icon-email.svg\");\r\n");
      out.write("}\r\n");
      out.write(".cd-form label.cd-password {\r\n");
      out.write("  background-image: url(\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/images/login/cd-icon-password.svg\");\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<header role=\"banner\">\r\n");
      out.write("\t\t<!-- <div id=\"cd-logo\"><a href=\"#0\"><img src=\"/images/back/login/cd-logo.svg\" alt=\"Logo\"></a></div> -->\r\n");
      out.write("\t\t<div id=\"cd-logo\" style=\"margin: 5px 50px;\">\r\n");
      out.write("\t\t\t<a href=\"#0\">\r\n");
      out.write("\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t</a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<nav class=\"main-nav\">\r\n");
      out.write("\t\t\t<ul>\r\n");
      out.write("\t\t\t\t<!-- inser more links here -->\r\n");
      out.write("\t\t\t\t<!-- <li><a class=\"cd-signin\" href=\"#0\">登 陆</a></li> -->\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</nav>\r\n");
      out.write("\t</header>\r\n");
      out.write("\t\r\n");
      out.write("\t<div style=\"position:absolute;top:25px;width:100%;text-align:center;\">\r\n");
      out.write("\t\t\t<h1 style=\"color:white;font-size:23px;\">\r\n");
      out.write("\t\t\t\t<span class=\"blue\" style=\"color:#1B9AF7\">零壹众</span> <span class=\"black\" id=\"id-text2\">集群管理台</span>\r\n");
      out.write("\t\t\t</h1>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"cd-user-modal is-visible\" style=\"background: none;\">\r\n");
      out.write("\t\t<!-- this is the entire modal form, including the background -->\r\n");
      out.write("\t\t<div class=\"cd-user-modal-container\" style=\"background: #EAE9E9; top: 10%;\">\r\n");
      out.write("\t\t\t<button style=\"margin-left: 230px;\" onclick=\"quickLogin();return false;\" class=\"button button-primary button-rounded button-small\">快捷登陆</button>\r\n");
      out.write("\t\t\t<div id=\"cd-login\" class=\"is-selected\" style=\"background: none;\">\r\n");
      out.write("\t\t\t\t<!-- log in form -->\r\n");
      out.write("\t\t\t\t<form class=\"cd-form\">\r\n");
      out.write("\t\t\t\t\t<p class=\"fieldset\">\r\n");
      out.write("\t\t\t\t\t\t<label class=\"image-replace cd-username\" for=\"signin-username\">账号</label> <input class=\"full-width has-padding has-border\" id=\"signin-username\" type=\"text\" placeholder=\"账号\"> <span class=\"cd-error-message\">请输入您的账号</span>\r\n");
      out.write("\t\t\t\t\t</p>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<p class=\"fieldset\">\r\n");
      out.write("\t\t\t\t\t\t<label class=\"image-replace cd-password\" for=\"signin-password\">密码</label> <input class=\"full-width has-padding has-border\" id=\"signin-password\" type=\"password\" placeholder=\"密码\"> <span\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"cd-error-message\">请输入您的密码</span>\r\n");
      out.write("\t\t\t\t\t\t<!-- <a href=\"#0\" class=\"hide-password\">隐藏</a> -->\r\n");
      out.write("\t\t\t\t\t</p>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<p class=\"fieldset\" style=\"position: relative; top: -19px;\">\r\n");
      out.write("\t\t\t\t\t\t<label class=\"image-replace cd-close\" for=\"signin-verificationCode\">验证码</label> \r\n");
      out.write("\t\t\t\t\t\t<input class=\"full-width has-padding has-border\" id=\"signin-verificationCode\" type=\"text\" placeholder=\"验证码\" style=\"min-width: 200px; width: 55%\"> \r\n");
      out.write("\t\t\t\t\t\t<span class=\"cd-error-message\">请输入验证码</span> \r\n");
      out.write("\t\t\t\t\t\t<a href='javascript:loadRandomCode();'> \r\n");
      out.write("\t\t\t\t\t\t\t<img src='drawCheckCode' id='imgcode' style=\"width:160px;height: 50px; position: relative; top: 19px;\" />\r\n");
      out.write("\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"javascript:loadRandomCode();\">重新加载</a>\r\n");
      out.write("\t\t\t\t\t</p>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<p class=\"fieldset\" style=\"position: relative; top: -19px;\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"checkbox\" id=\"remember\" checked /> <label for=\"remember-me\">记住我</label>\r\n");
      out.write("\t\t\t\t\t</p>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<p class=\"fieldset\" style=\"position: relative; top: -19px;\">\r\n");
      out.write("\t\t\t\t\t\t<input id=\"login_submit\" class=\"full-width\" type=\"submit\" value=\"登 陆\" />\r\n");
      out.write("\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<p class=\"cd-form-bottom-message\" onclick=\"forgetPassword();\">\r\n");
      out.write("\t\t\t\t\t<a style=\"cursor: pointer; color: #2f889a;\">忘记密码?</a>\r\n");
      out.write("\t\t\t\t</p>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- cd-login -->\r\n");
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- cd-user-modal-container -->\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<footer class=\"login_foot\" style=\"\">&copy; 零壹众科技</footer>\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../common/common_js.jsp", out, false);
      out.write("\r\n");
      out.write("\t<script type=\"text/javascript\"> lh.param = ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${paramJson}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" </script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/third-party/compatible/modernizr.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/js/login/main.js\" title=\"v\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
