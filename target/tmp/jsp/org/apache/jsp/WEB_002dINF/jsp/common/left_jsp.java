package org.apache.jsp.WEB_002dINF.jsp.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class left_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!-- #section:basics/sidebar -->\r\n");
      out.write("<div id=\"sidebar\" class=\"sidebar responsive\">\r\n");
      out.write("\t<ul class=\"nav nav-list\">\r\n");
      out.write("\t\t<li class=\"active\">\r\n");
      out.write("\t\t\t<a href=\"javascript:\"> <i class=\"menu-icon fa fa-desktop\"></i> <span class=\"menu-text\"> 查看首页 </span></a> <b class=\"arrow\"></b>\r\n");
      out.write("\t\t</li>\r\n");
      out.write("\t\t");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t<li class=\"\">\r\n");
      out.write("\t\t\t<a href=\"javascript:\" onclick=\"showPage('/admin/user/users');\" target=\"mainFrame\"> \r\n");
      out.write("\t\t\t\t<i class=\"menu-icon fa glyphicon-user fa-user\"></i> <span class=\"menu-text\"> 用户管理 </span> <!--<b class=\"arrow fa fa-angle-down\"></b>-->\r\n");
      out.write("\t\t\t</a> \r\n");
      out.write("\t\t\t<!-- \r\n");
      out.write("\t\t\t<b class=\"arrow\"></b>\r\n");
      out.write("\t\t\t<ul class=\"submenu\">\r\n");
      out.write("\t\t\t\t<li class=\"\">\r\n");
      out.write("\t\t\t\t\t<a href=\"/admin/user/users\" target=\"mainFrame\">\r\n");
      out.write("\t\t\t\t\t\t<i class=\"menu-icon fa fa-caret-right\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t用户1\r\n");
      out.write("\t\t\t\t\t\t<b class=\"arrow fa fa-angle-down\"></b>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t<li class=\"\">\r\n");
      out.write("\t\t\t\t\t<a href=\"/admin/group/groups\" target=\"mainFrame\">\r\n");
      out.write("\t\t\t\t\t\t<i class=\"menu-icon fa fa-caret-right\"></i>\r\n");
      out.write("\t\t\t\t\t\t用户2\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t<b class=\"arrow\"></b>\r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t<li class=\"\">\r\n");
      out.write("\t\t\t\t\t<a href=\"/admin/role/roles\" target=\"mainFrame\">\r\n");
      out.write("\t\t\t\t\t\t<i class=\"menu-icon fa fa-caret-right\"></i>\r\n");
      out.write("\t\t\t\t\t\t用户3\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t<b class=\"arrow\"></b>\r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t\t-->\r\n");
      out.write("\t\t</li>\r\n");
      out.write("\t\t<li class=\"\">\r\n");
      out.write("\t\t\t<a href=\"#\" class=\"dropdown-toggle\"> \r\n");
      out.write("\t\t\t\t<i class=\"menu-icon fa fa-group\"></i> <span class=\"menu-text\"> 组管理 </span> <b class=\"arrow fa fa-angle-down\"></b>\r\n");
      out.write("\t\t\t</a> \r\n");
      out.write("\t\t\t<b class=\"arrow\"></b>\r\n");
      out.write("\t\t\t<ul class=\"submenu\">\r\n");
      out.write("\t\t\t\t<li class=\"\">\r\n");
      out.write("\t\t\t\t\t<a href=\"javascript:\" onclick=\"showPage('/admin/group/groups');\" target=\"mainFrame\"> \r\n");
      out.write("\t\t\t\t\t\t<i class=\"menu-icon fa fa-group\"></i> 用户组管理\r\n");
      out.write("\t\t\t\t\t</a> \r\n");
      out.write("\t\t\t\t\t<b class=\"arrow\"></b>\r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t<!--  \r\n");
      out.write("\t\t\t\t<li class=\"\">\r\n");
      out.write("\t\t\t\t\t<a href=\"/admin/group/consolegrps\" target=\"mainFrame\">\r\n");
      out.write("\t\t\t\t\t\t<i class=\"menu-icon fa fa-desktop\"></i>\r\n");
      out.write("\t\t\t\t\t\t调度台组管理\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t<b class=\"arrow\"></b>\r\n");
      out.write("\t\t\t\t</li>-->\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</li>\r\n");
      out.write("\r\n");
      out.write("\t\t<li class=\"\">\r\n");
      out.write("\t\t\t<!--<a href=\"/admin/console/users\" target=\"mainFrame\">--> \r\n");
      out.write("\t\t\t<a href=\"#\" class=\"dropdown-toggle\"> \r\n");
      out.write("\t\t\t\t<i class=\"menu-icon fa fa-desktop\"></i> <span class=\"menu-text\"> 调度台管理 </span> <b class=\"arrow fa fa-angle-down\"></b>\r\n");
      out.write("\t\t\t</a> \r\n");
      out.write("\t\t\t<b class=\"arrow\"></b>\r\n");
      out.write("\t\t\t<ul class=\"submenu\">\r\n");
      out.write("\t\t\t\t<li class=\"\">\r\n");
      out.write("\t\t\t\t\t<a href=\"javascript:\" onclick=\"showPage('/admin/console/level1users');\" target=\"mainFrame\"> \r\n");
      out.write("\t\t\t\t\t\t<i class=\"menu-icon fa fa-caret-right\"></i> 一阶管理台\r\n");
      out.write("\t\t\t\t\t</a> \r\n");
      out.write("\t\t\t\t\t<b class=\"arrow\"></b>\r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t<li class=\"\">\r\n");
      out.write("\t\t\t\t\t<a href=\"javascript:\" onclick=\"showPage('/admin/console/level2users');\" target=\"mainFrame\"> \r\n");
      out.write("\t\t\t\t\t\t<i class=\"menu-icon fa fa-caret-right\"></i> 二阶管理台\r\n");
      out.write("\t\t\t\t\t</a> \r\n");
      out.write("\t\t\t\t\t<b class=\"arrow\"></b>\r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</li>\r\n");
      out.write("\t\r\n");
      out.write("\t\t");
      out.write("\r\n");
      out.write("\t</ul>\r\n");
      out.write("\t<!-- /.nav-list -->\r\n");
      out.write("\t<!-- #section:basics/sidebar.layout.minimize -->\r\n");
      out.write("\t<div class=\"sidebar-toggle sidebar-collapse\" id=\"sidebar-collapse\">\r\n");
      out.write("\t\t<i class=\"ace-icon fa fa-angle-double-left\" data-icon1=\"ace-icon fa fa-angle-double-left\" data-icon2=\"ace-icon fa fa-angle-double-right\"></i>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<!-- /section:basics/sidebar.layout.minimize -->\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\ttry {\r\n");
      out.write("\t\t\tace.settings.check('sidebar', 'collapsed')\r\n");
      out.write("\t\t} catch (e) {\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("</div>");
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
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${isAdmin }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t<li class=\"\">\r\n");
        out.write("\t\t\t\t<a href=\"javascript:\" target=\"mainFrame\" class=\"dropdown-toggle\"> \r\n");
        out.write("\t\t\t\t\t<i class=\"menu-icon fa glyphicon-user  fa-building\"></i> <span class=\"menu-text\"> 公司管理 </span> <b class=\"arrow fa fa-angle-down\"></b>\r\n");
        out.write("\t\t\t\t</a> \r\n");
        out.write("\t\t\t\t<b class=\"arrow\"></b>\r\n");
        out.write("\t\t\t\t<ul class=\"submenu\">\r\n");
        out.write("\t\t\t\t\t<li class=\"\">\r\n");
        out.write("\t\t\t\t\t\t<a href=\"javascript:\" onclick=\"showPage('/admin/corp/users');\" target=\"mainFrame\"> \r\n");
        out.write("\t\t\t\t\t\t\t<i class=\"menu-icon fa fa-caret-right\"></i> 公司列表 <b class=\"arrow fa fa-angle-down\"></b>\r\n");
        out.write("\t\t\t\t\t\t</a>\r\n");
        out.write("\t\t\t\t\t</li>\r\n");
        out.write("\t\t\t\t\t<!--  \r\n");
        out.write("\t\t\t\t\t<li class=\"\">\r\n");
        out.write("\t\t\t\t\t\t<a href=\"/admin/corp/groups\" target=\"mainFrame\">\r\n");
        out.write("\t\t\t\t\t\t\t<i class=\"menu-icon fa fa-caret-right\"></i>\r\n");
        out.write("\t\t\t\t\t\t\t公司信息统计\r\n");
        out.write("\t\t\t\t\t\t</a>\r\n");
        out.write("\t\t\t\t\t\t<b class=\"arrow\"></b>\r\n");
        out.write("\t\t\t\t\t</li>-->\r\n");
        out.write("\t\t\t\t</ul>\r\n");
        out.write("\t\t\t</li>\r\n");
        out.write("\t\t\t<li class=\"\">\r\n");
        out.write("\t\t\t\t<a href=\"javascript:\" onclick=\"showPage('/admin/version/list');\" target=\"mainFrame\"> \r\n");
        out.write("\t\t\t\t\t<i class=\"menu-icon fa glyphicon-user fa-mobile\"></i> <span class=\"menu-text\"> 客户端版本管理 </span>\r\n");
        out.write("\t\t\t\t</a>\r\n");
        out.write("\t\t\t</li>\r\n");
        out.write("\t\t");
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
