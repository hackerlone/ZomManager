<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/common_css.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/validate/main.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/ace/assets/css/bootstrap-datetimepicker.css" />

</head>
<body>
	<form action="/manager/charge/jumpToPay.html" method="post">
		充值金额:<input type="text" id="money" name="money" / >
		备注:<input type="text" id="note" name="note" / >
		<button type="submit" id="submit">确认充值</button>
	</form>
</body>
</html>