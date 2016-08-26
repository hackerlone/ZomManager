package org.apache.jsp.WEB_002dINF.jsp.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class top_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_if_test.release();
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
      response.setContentType("text/html; charset=UTF-8");
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
      out.write("<input type=\"hidden\" id=\"contextPath\" value=\"");
      out.print(request.getContextPath());
      out.write("\"/>\r\n");
      out.write("<div id=\"navbar\" class=\"navbar navbar-default\">\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t/* try{ace.settings.check('navbar' , 'fixed')}catch(e){} */\r\n");
      out.write("\t</script>\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"navbar-container\" id=\"navbar-container\">\r\n");
      out.write("\t\t<!-- #section:basics/sidebar.mobile.toggle -->\r\n");
      out.write("\t\t<button type=\"button\" class=\"navbar-toggle menu-toggler pull-left\" id=\"menu-toggler\" data-target=\"#sidebar\">\r\n");
      out.write("\t\t\t<span class=\"sr-only\">Toggle sidebar</span>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<span class=\"icon-bar\"></span>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<span class=\"icon-bar\"></span>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<span class=\"icon-bar\"></span>\r\n");
      out.write("\t\t</button>\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- /section:basics/sidebar.mobile.toggle -->\r\n");
      out.write("\t\t<div class=\"navbar-header pull-left\">\r\n");
      out.write("\t\t\t<!-- #section:basics/navbar.layout.brand -->\r\n");
      out.write("\t\t\t<a href=\"#\" class=\"navbar-brand\">\r\n");
      out.write("\t\t\t\t<small>\r\n");
      out.write("\t\t\t\t\t<i class=\"fa fa-globe\"></i>\r\n");
      out.write("\t\t\t\t\t零壹众管理平台\r\n");
      out.write("\t\t\t\t</small>\r\n");
      out.write("\t\t\t</a>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<!-- /section:basics/navbar.layout.brand -->\r\n");
      out.write("\r\n");
      out.write("\t\t\t<!-- #section:basics/navbar.toggle -->\r\n");
      out.write("\r\n");
      out.write("\t\t\t<!-- /section:basics/navbar.toggle -->\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- #section:basics/navbar.dropdown -->\r\n");
      out.write("\t\t<div class=\"navbar-buttons navbar-header pull-right\" role=\"navigation\">\r\n");
      out.write("\t\t\t<ul class=\"nav ace-nav\">\r\n");
      out.write("\t\r\n");
      out.write("\t\t\t<!-- #section:basics/navbar.user_menu -->\r\n");
      out.write("\t\t\t\t<li class=\"light-blue\" onclick=\"$('#ul_menu').toggle();\">\r\n");
      out.write("\t\t\t\t\t<a data-toggle=\"dropdown\" href=\"#\" class=\"dropdown-toggle\">\r\n");
      out.write("\t<!-- \t\t\t\t\t<img class=\"nav-user-photo\" src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/avatars/user.jpg\" alt=\"Jason's Photo\" />-->\r\n");
      out.write("\t\t\t\t\t\t<span class=\"user-info\">\r\n");
      out.write("\t\t\t\t\t\t\t<small>欢迎,</small>\r\n");
      out.write("\t\t\t\t\t\t\t");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${loginUser.username }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t<i class=\"ace-icon fa fa-caret-down\"></i>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t<ul id=\"ul_menu\" class=\"user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close\">\r\n");
      out.write("\t\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"javascript:\" target=\"mainFrame\" onclick=\"showPage('/admin/corp/showSelf');\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-user\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t个人设置\r\n");
      out.write("\t\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t<li class=\"divider\"></li>\r\n");
      out.write("\t\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"javascript:exitSystem()\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-power-off\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t退出\r\n");
      out.write("\t\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<!-- /section:basics/navbar.user_menu -->\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div id=\"top_current_corp\" style=\"text-align:center;\">\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- /section:basics/navbar.dropdown -->\r\n");
      out.write("\t</div><!-- /.navbar-container -->\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function exitSystem() {\r\n");
      out.write("\twindow.parent.location.href = $(\"#contextPath\").val()+ \"/admin/logout\";\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<!-- /section:basics/navbar.layout -->");
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

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${isAdmin == true}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t<span id=\"top_current_corp_name\" style=\"line-height:45px;color:white;padding-right:5%\">当前选择公司：");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${corpName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("</span>\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }
}
