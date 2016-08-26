package org.apache.jsp.WEB_002dINF.jsp.corp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class corpList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/WEB-INF/jsp/corp/../common/common_css.jsp");
    _jspx_dependants.add("/WEB-INF/jsp/corp/../common/common_js.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
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
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/third-party/bootstrap-select-1.10.0/css/bootstrap-select.min.css\" />\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write(".btn-white {\r\n");
      out.write("\tcolor: #333;\r\n");
      out.write("\tbackground-color: #fff;\r\n");
      out.write("\tborder-color: #ccc;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"no-skin\">\r\n");
      out.write("\t<div class=\"main-content-inner\">\r\n");
      out.write("\t\t<!-- #section:basics/content.breadcrumbs -->\r\n");
      out.write("\t\t<div class=\"breadcrumbs\" id=\"breadcrumbs\">\r\n");
      out.write("\t\t\t<ul class=\"breadcrumb\">\r\n");
      out.write("\t\t\t\t<li><i class=\"ace-icon fa fa-home home-icon\"></i> <a href=\"javascript:\" onclick=\"lh.mainPageReload();\">首页</a></li>\r\n");
      out.write("\t\t\t\t<li class=\"active\">公司列表</li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t\t<!-- /.breadcrumb -->\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t<div class=\"page-content\">\r\n");
      out.write("\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t<div class=\"col-xs-12\">\r\n");
      out.write("\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t<label style=\"width: 100px;\" class=\"col-sm-3 control-label no-padding-right\" for=\"corpNameLike\">公司名称：</label> <input style=\"width: 140px;\" type=\"text\" id=\"corpNameLike\" placeholder=\"\" class=\"col-xs-10 col-sm-5\">\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t<label style=\"width: 100px;\" class=\"col-sm-3 control-label no-padding-right\" for=\"usernameLike\">登陆名：</label> <input style=\"width: 140px;\" type=\"text\" id=\"usernameLike\" placeholder=\"\" class=\"col-xs-10 col-sm-5\">\r\n");
      out.write("\t\t\t\t\t\t\t<div></div>\r\n");
      out.write("\t\t\t\t\t\t\t<label style=\"width: 100px;\" class=\"col-sm-3 control-label no-padding-right\" for=\"phoneLike\">登陆手机：</label> <input style=\"width: 140px;\" type=\"text\" id=\"phoneLike\" placeholder=\"\" class=\"col-xs-10 col-sm-5\">\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div style=\"display: inline-block; position: relative; bottom: 4px;\">\r\n");
      out.write("\t\t\t\t\t\t\t<label style=\"width: 80px;\" class=\"col-sm-3 control-label no-padding-right\" for=\"zone\">zone：</label> <select id=\"zone\" style=\"width: 80px;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<!--  form-control col-xs-10 col-sm-5 -->\r\n");
      out.write("\t\t\t\t\t\t\t\t<option value=\"\">全部</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div style=\"display: inline-block; position: relative; bottom: 4px;\">\r\n");
      out.write("\t\t\t\t\t\t\t<label style=\"width: 80px;\" class=\"col-sm-3 control-label no-padding-right\" for=\"status\">状态：</label> <select id=\"status\" style=\"width: 160px;\" >\r\n");
      out.write("\t\t\t\t\t\t\t\t<!--  form-control col-xs-10 col-sm-5 -->\r\n");
      out.write("\t\t\t\t\t\t\t\t<option value=\"\">全部</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t<option value=\"1\">启用</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t<option value=\"0\">停用</option>\r\n");
      out.write("\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div style=\"display: inline-block;\">\r\n");
      out.write("\t\t\t\t\t\t\t<button onclick=\"doSearch();\" style=\"margin-left: 10px;\" class=\"btn btn-info\" type=\"button\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-search bigger-110\"></i>查询\r\n");
      out.write("\t\t\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t\t\t\t<button onclick=\"clearSearch();\" class=\"btn\" type=\"reset\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-undo bigger-110\"></i>重置\r\n");
      out.write("\t\t\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t<div class=\"col-xs-12\">\r\n");
      out.write("\t\t\t\t\t<!-- PAGE CONTENT BEGINS -->\r\n");
      out.write("\t\t\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"col-xs-12\">\r\n");
      out.write("\t\t\t\t\t\t\t<table id=\"datagrid\" class=\"table table-striped table-bordered table-hover\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<thead>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<th>ID</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<th>公司名称</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<th>登录名</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<th>状态</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<th>登录手机</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<th>最大用户数</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<th>最大组数</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<th>最大组成员数</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<th>最大调度台数</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<th>用户操作</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t</thead>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t\t<div id=\"optDiv\"></div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"page-header position-relative\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<table style=\"width: 100%;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<tbody>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td style=\"vertical-align: top;\"><a href=\"");
      out.print(request.getContextPath());
      out.write("/admin/corp/add\" target=\"mainFrame\" style=\"color: #FFF; text-decoration: none;\" title=\"添加用户\" class=\"btn btn-info fa\">+</a> <a\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\thref=\"");
      out.print(request.getContextPath());
      out.write("/admin/corp/users\" style=\"color: #FFF; text-decoration: none;\" class=\"btn btn-info fa fa-refresh\" title=\"刷新列表\"></a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t <!-- <a \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tstyle=\"color: #FFF; text-decoration: none;\" class=\"btn btn-info fa fa-refresh\" title=\"\"></a> -->\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t <button onclick=\"exportExcel();\" id=\"exportExcelBtn\" type=\"button\" class=\"btn btn-info\">导出表格</button>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t <!-- <button onclick=\"importExcel();\" id=\"importExcelBtn\" type=\"button\" class=\"btn btn-success\"> -->\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<button id=\"browse\" type=\"button\" class=\"btn btn-success\">导入表格</button>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<!-- <div id=\"upload_outer_div\" style=\"margin-top: 30px;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t上传文件进度展示\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</div>  -->\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"/manager/back/charge.html\" id=\"importExcelBtn\" type=\"button\" class=\"btn btn-warning\">充值</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<!-- <a href=\"/manager/back/charge.html\" id=\"importExcelBtn\" style=\"color: #FFF; text-decoration: none;\" class=\"btn btn-info fa fa-refresh\" title=\"充值\"></a> -->\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<!-- /.span -->\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<!-- /.row -->\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<!-- /.col -->\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- /.row -->\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t");
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
      out.write("\t");
      out.write("\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/third-party/bootstrap-select-1.10.0/js/bootstrap-select.min.js\"></script>\r\n");
      out.write("\t<!-- <script type=\"text/javascript\"> lh.param = ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${paramJson}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" </script> -->\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/js/corp/corp.js\" title=\"v\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/js/common/common_upload.js\" title=\"v\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/resources/lh/\r\n");
      out.write("third-party/plupload/js/plupload.full.min.js\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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

  private boolean _jspx_meth_c_forEach_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent(null);
    _jspx_th_c_forEach_0.setVar("zone");
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${zoneAssign}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t<option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${zone.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write('"');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${zone.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</option>\r\n");
          out.write("\t\t\t\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }
}
