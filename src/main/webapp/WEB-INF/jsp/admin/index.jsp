<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%@ include file="../common/common_css.jsp"%>
<title>欢迎使用管理平台</title>
</head>
<body class="no-skin">
	<!-- header -->
  <jsp:include page="../common/top.jsp"></jsp:include>  
	<div class="main-container" id="main-container">
		<jsp:include page="../common/left.jsp"></jsp:include>
		<div class="main-content">
		    <iframe name="mainFrame" id="mainFrame" frameborder="0" style="margin:0 auto;width:100%;height:100%;"></iframe>
		</div>
	</div>

	<jsp:include page="../common/foot.jsp"></jsp:include>
	
	<%@ include file="../lh/common/common_back_js.jsp"%>
	<%@ include file="../common/common_js.jsp"%>
	<script src="<%=request.getContextPath()%>/resources/lh/js/main.js" title="v"></script>

</body>
</html>
