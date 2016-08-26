
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lh/third-party/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lh/third-party/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lh/third-party/lodash/lodash.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lh/third-party/json2/json2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lh/third-party/clipboard/clipboard.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lh/third-party/mustache/mustache.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lh/third-party/is/is.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lh/third-party/moment/moment.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lh/third-party/moment/locale/zh-cn.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/ace/dist/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/ace/dist/js/additional-methods.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lh/js/common/common_base.js" title="v"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lh/js/common/common_back.js" title="v"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lh/js/common/common_front_ui_bootstrap.js" title="v"></script>

<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='<%=request.getContextPath() %>/resources/ace/dist/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>

<!-- page specific plugin scripts -->
<!--[if lte IE 8]>
  <script src="<%=request.getContextPath() %>/resources/ace/dist/js/excanvas.min.js"></script>
<![endif]-->
<%-- <script src="<%=request.getContextPath() %>/resources/ace/dist/js/jquery-ui.custom.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/dist/js/jquery.ui.touch-punch.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/dist/js/jquery.easypiechart.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/dist/js/jquery.sparkline.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/dist/js/flot/jquery.flot.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/dist/js/flot/jquery.flot.pie.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/dist/js/flot/jquery.flot.resize.min.js"></script> --%>

<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/core/jquery.cms.validate.min.js"></script> --%>

<!-- ace scripts -->
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/elements.scroller.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/elements.colorpicker.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/elements.fileinput.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/elements.typeahead.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/elements.wysiwyg.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/elements.spinner.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/elements.treeview.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/elements.wizard.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/elements.aside.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/ace.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/ace.ajax-content.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/ace.touch-drag.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/ace.sidebar.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/ace.sidebar-scroll-1.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/ace.submenu-hover.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/ace.widget-box.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/ace.settings.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/ace.settings-rtl.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/ace.settings-skin.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/ace.widget-on-reload.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/ace/ace.searchbox-autocomplete.js"></script>

<script src="<%=request.getContextPath()%>/resources/ace/dist/js/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/ace/dist/js/jquery.dataTables.bootstrap.min.js"></script>

<!-- the following scripts are used in demo only for onpage help and you don't need them -->
<%-- <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/ace/dist/css/ace.onpage-help.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/ace/docs/dist/js/themes/sunburst.css" />

<script type="text/javascript"> ace.vars['base'] = '<%=request.getContextPath() %>/resources/ace'; </script>
<script src="<%=request.getContextPath() %>/resources/ace/dist/js/ace/elements.onpage-help.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/dist/js/ace/ace.onpage-help.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/docs/dist/js/rainbow.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/docs/dist/js/language/generic.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/docs/dist/js/language/html.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/docs/dist/js/language/css.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/docs/dist/js/language/javascript.min.js"></script> --%>

