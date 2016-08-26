<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/common_css.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/validate/main.css" />
</head>
<body class="no-skin">
	<div class="main-content-inner">
		<!-- #section:basics/content.breadcrumbs -->
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="javascript:" onclick="lh.mainPageReload();">首页</a></li>
				<li><a href="javascript:" onclick="lh.cmsJump('/admin/corp/users');">公司列表</a></li>
				<c:if test="${isAdd != 'add'}">
					<li class="active">修改公司信息--> ${corporation.username}</li>
				</c:if>
				<c:if test="${isAdd == 'add'}">
					<li class="active">添加公司信息</li>
				</c:if>
			</ul>
			<!-- /.breadcrumb -->
		</div>

		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<sf:form method="post" modelAttribute="corporation" id="addForm" cssClass="form-horizontal" role="form" novalidate="novalidate" onsubmit="return false;">
						<!-- #section:elements.form -->
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="corpName"> 公司名称: </label>
							<div class="col-sm-9">
								<sf:input id="corpName" name="corpName" path="corpName" size="30" cssClass="col-xs-10 col-sm-5" />
								<sf:errors cssClass="errorContainer" path="corpName" />
							</div>
						</div>

						<!-- /section:elements.form -->
						<%-- <div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-2">用户密码: </label>

						<div class="col-sm-9">
							<sf:input class="col-xs-10 col-sm-5" id="password" type="password" placeholder="Password" path="password"/>
							<span class="help-inline col-xs-12 col-sm-7">
								<span class="middle"><sf:errors cssClass="errorContainer" path="password"/></span>
							</span>
						</div>
					</div>
					
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-2">确认密码: </label>

						<div class="col-sm-9">
							<input class="col-xs-10 col-sm-5" id="confirmPwd" name="confirmPwd" type="password"/>
							<span class="help-inline col-xs-12 col-sm-7">
								<span class="middle"></span>
							</span>
						</div>
					</div>  --%>

						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="phone"> 手机: </label>
							<div class="col-sm-9">
								<sf:input id="phone" path="phone" size="30" cssClass="col-xs-10 col-sm-5" />
								<sf:errors cssClass="errorContainer" path="phone" />
							</div>
						</div>

						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="email"> 电子邮箱: </label>
							<div class="col-sm-9">
								<sf:input id="email" path="email" size="30" cssClass="col-xs-10 col-sm-5" />
								<sf:errors cssClass="errorContainer" path="email" />
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="maxUser"> 最大用户数: </label>
							<div class="col-sm-9">
								<sf:input id="maxUser" path="maxUser" size="30" cssClass="col-xs-10 col-sm-5" />
								<sf:errors cssClass="errorContainer" path="maxUser" />
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="maxGroup"> 最大组数: </label>
							<div class="col-sm-9">
								<sf:input id="maxGroup" path="maxGroup" size="30" cssClass="col-xs-10 col-sm-5" />
								<sf:errors cssClass="errorContainer" path="maxGroup" />
							</div>
						</div>
						
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right red" for="form-field-1"> 使用期限: *</label>
							<div class="col-sm-9" id="dataTimes" style="width: 256.86px;">
								<input id="date-timepicker1" type="text" class="form-control" /> 
								<span class="input-group-addon"> 
									<i class="fa fa-clock-o bigger-110"></i>
								</span>
							</div>
						</div>
						
						
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="maxConsole"> 最大调度台数: </label>
							<div class="col-sm-9">
								<sf:input id="maxConsole" path="maxConsole" size="30" cssClass="col-xs-10 col-sm-5" />
								<sf:errors cssClass="errorContainer" path="maxConsole" />
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="maxUserGroup"> 最大组成员数: </label>
							<div class="col-sm-9">
								<sf:input id="maxUserGroup" path="maxUserGroup" size="30" cssClass="col-xs-10 col-sm-5" />
								<sf:errors cssClass="errorContainer" path="maxUserGroup" />
							</div>
						</div>

						<%-- <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">要加入的zone: </label>
							<div class="col-sm-9">
								<select id="zone" style="width: 160px;">
									<!--  form-control col-xs-10 col-sm-5 -->
									<option value="${corporation.zoneId }">${corporation.zoneName }</option>
									<c:forEach var="zone" items="${zoneAssign}">
										<option value="${zone.id}">${zone.name}</option>
									</c:forEach>
								</select>
							</div>
						</div> --%>

						<div class="space-4"></div>
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="button" onclick="updateManiObj(${corporation.id});">
									<i class="ace-icon fa fa-check bigger-110"></i> 保存
								</button>
								<button class="btn" type="reset" style="margin-left: 40px;" onclick="lh.back();">
									<i class="ace-icon fa fa-undo bigger-110"></i> 返回
								</button>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
			<!-- /.row -->
		</div>
	</div>
	<%@ include file="../common/common_js.jsp"%>
	<!-- <script type="text/javascript"> lh.param = ${paramJson} </script> -->
	<script src="<%=request.getContextPath()%>/resources/lh/js/corp/update.js" title="v"></script>
</body>
</html>