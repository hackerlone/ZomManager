package org.apache.jsp.WEB_002dINF.jsp.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/WEB-INF/jsp/common/../common/common_css.jsp");
    _jspx_dependants.add("/WEB-INF/jsp/common/../common/common_js.jsp");
  }

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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />\r\n");
      out.write("<meta charset=\"utf-8\" />\r\n");
      out.write("<title>ZeroMore Management System</title>\r\n");
      out.write("\r\n");
      out.write("<meta name=\"description\" content=\"overview &amp; stats\" />\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\" />\r\n");
      out.write("\r\n");
      out.write("<!-- bootstrap & fontawesome -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/css/bootstrap.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/css/font-awesome.css\" />\r\n");
      out.write("\r\n");
      out.write("<!-- page specific plugin styles -->\r\n");
      out.write("\r\n");
      out.write("<!-- text fonts -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/css/ace-fonts.css\" />\r\n");
      out.write("\r\n");
      out.write("<!-- ace styles -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/css/ace.css\" class=\"ace-main-stylesheet\" id=\"main-ace-style\" />\r\n");
      out.write("\r\n");
      out.write("<!--[if lte IE 9]>\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/css/ace-part2.css\" class=\"ace-main-stylesheet\" />\r\n");
      out.write("<![endif]-->\r\n");
      out.write("\r\n");
      out.write("<!--[if lte IE 9]>\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/css/ace-ie.css\" />\r\n");
      out.write("<![endif]-->\r\n");
      out.write("\r\n");
      out.write("<!-- inline styles related to this page -->\r\n");
      out.write("\r\n");
      out.write("<!-- ace settings handler -->\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace-extra.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->\r\n");
      out.write("\r\n");
      out.write("<!--[if lte IE 8]>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/html5shiv.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/respond.js\"></script>\r\n");
      out.write("<![endif]-->");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"no-skin\">\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"main-content-inner\">\r\n");
      out.write("\t\t<!-- #section:basics/content.breadcrumbs -->\r\n");
      out.write("\t\t<div class=\"breadcrumbs\" id=\"breadcrumbs\">\r\n");
      out.write("\t\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\t\ttry{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}\r\n");
      out.write("\t\t\t</script>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<ul class=\"breadcrumb\">\r\n");
      out.write("\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t<i class=\"ace-icon fa fa-home home-icon\"></i>\r\n");
      out.write("\t\t\t\t\t<a href=\"#\">首页</a>\r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t<li class=\"active\">平台管理</li>\r\n");
      out.write("\t\t\t</ul><!-- /.breadcrumb -->\r\n");
      out.write("\r\n");
      out.write("\t\t\t<!-- #section:basics/content.searchbox -->\r\n");
      out.write("\t\t\t<!-- \r\n");
      out.write("\t\t\t<div class=\"nav-search\" id=\"nav-search\">\r\n");
      out.write("\t\t\t\t<form class=\"form-search\">\r\n");
      out.write("\t\t\t\t\t<span class=\"input-icon\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" placeholder=\"Search ...\" class=\"nav-search-input\" id=\"nav-search-input\" autocomplete=\"off\" />\r\n");
      out.write("\t\t\t\t\t\t<i class=\"ace-icon fa fa-search nav-search-icon\"></i>\r\n");
      out.write("\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("            -->\r\n");
      out.write("            \r\n");
      out.write("\t\t\t<!-- /section:basics/content.searchbox -->\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- /section:basics/content.breadcrumbs -->\r\n");
      out.write("\t\t<div class=\"page-content\">\r\n");
      out.write("\t\t\t<!-- /section:settings.box -->\r\n");
      out.write("\t\t\t<div class=\"page-header\">\r\n");
      out.write("\t\t\t\t<h1>\r\n");
      out.write("\t\t\t\t\t欢迎使用零壹众集群管理平台\r\n");
      out.write("\t\t\t\t</h1>\r\n");
      out.write("\t\t\t</div><!-- /.page-header -->\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t<div class=\"col-xs-12\">\r\n");
      out.write("\t\t\t\t\t<!-- PAGE CONTENT BEGINS -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"space-6\"></div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-sm-9 infobox-container\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<!-- #section:pages/dashboard.infobox -->\r\n");
      out.write("\t\t\t\t\t\t\t<div style = \"width: 280px\" class=\"infobox infobox-green\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"infobox-icon\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-user\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"infobox-data\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<span  style=\"float:left\" class=\"infobox-data-number\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.maxUser}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span>\r\n");
      out.write("\t\t\t\t\t\t\t        \r\n");
      out.write("\t\t\t\t\t\t\t        <span style = \"padding-left:110px\" class=\"infobox-data-number\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${uCount}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div style=\"float:left\" class=\"infobox-content\">总用户</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div style=\"float:left;padding-left:50px\"  class=\"infobox-content\">已注册用户</div>\t\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<div style = \"width: 280px\" class=\"infobox infobox-blue\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"infobox-icon\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-group\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"infobox-data\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<span  style=\"float:left\" class=\"infobox-data-number\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.maxGroup}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span>\r\n");
      out.write("\t\t\t\t\t\t\t        \r\n");
      out.write("\t\t\t\t\t\t\t        <span style = \"padding-left:110px\" class=\"infobox-data-number\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${gCount}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div style=\"float:left\" class=\"infobox-content\">总用户组</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div style=\"float:left;padding-left:50px\"  class=\"infobox-content\">已注册用户组</div>\t\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div style = \"width: 280px\" class=\"infobox infobox-red\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"infobox-icon\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-desktop\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"infobox-data\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<span  style=\"float:left\" class=\"infobox-data-number\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.maxConsole}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<span style=\"padding-left:110px\"   class=\"infobox-data-number\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${cCount}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div  style=\"float:left\"  class=\"infobox-content\">总调度台</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div  style=\"float:left;padding-left:50px\"  class=\"infobox-content\">已注册调度台</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div style = \"width: 280px\" class=\"infobox infobox-pink\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"infobox-icon\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-group\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"infobox-data\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<span style=\"float:left\" class=\"infobox-data-number\">11</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<span style=\"padding-left:110px\" class=\"infobox-data-number\">11</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div style=\"float:left\" class=\"infobox-content\">总调度台组</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div  style=\"float:left;padding-left:45px\"  class=\"infobox-content\">已注册调度台组</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\t\t<!-- PAGE CONTENT ENDS -->\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t</div><!-- /.row -->\r\n");
      out.write("\t\t</div><!-- /.page-content -->\r\n");
      out.write("\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("<!-- /.main-content -->\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/third-party/jquery/jquery-2.1.4.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/third-party/bootstrap/js/bootstrap.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/third-party/lodash/lodash.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/third-party/json2/json2.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/third-party/clipboard/clipboard.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/third-party/mustache/mustache.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/third-party/is/is.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/third-party/moment/moment.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/third-party/moment/locale/zh-cn.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/resources/ace/dist/js/jquery.validate.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/resources/ace/dist/js/additional-methods.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/js/common/common_base.js\" title=\"v\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/js/common/common_back.js\" title=\"v\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/js/common/common_front_ui_bootstrap.js\" title=\"v\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tif('ontouchstart' in document.documentElement) document.write(\"<script src='");
      out.print(request.getContextPath() );
      out.write("/resources/ace/dist/js/jquery.mobile.custom.min.js'>\"+\"<\"+\"/script>\");\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<!-- page specific plugin scripts -->\r\n");
      out.write("<!--[if lte IE 8]>\r\n");
      out.write("  <script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/dist/js/excanvas.min.js\"></script>\r\n");
      out.write("<![endif]-->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- ace scripts -->\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/elements.scroller.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/elements.colorpicker.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/elements.fileinput.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/elements.typeahead.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/elements.wysiwyg.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/elements.spinner.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/elements.treeview.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/elements.wizard.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/elements.aside.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/ace.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/ace.ajax-content.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/ace.touch-drag.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/ace.sidebar.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/ace.sidebar-scroll-1.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/ace.submenu-hover.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/ace.widget-box.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/ace.settings.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/ace.settings-rtl.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/ace.settings-skin.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/ace.widget-on-reload.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/resources/ace/assets/js/ace/ace.searchbox-autocomplete.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/resources/ace/dist/js/jquery.dataTables.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/resources/ace/dist/js/jquery.dataTables.bootstrap.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<!-- the following scripts are used in demo only for onpage help and you don't need them -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
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
