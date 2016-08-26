<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="../common/common_css.jsp"%>

</head>
<body class="no-skin">
<input type="hidden" value="${isRank1}" id="isRank1"/>
	<div class="main-content-inner">
		<!-- #section:basics/content.breadcrumbs -->
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
			</script>

			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="javascript:" onclick="lh.mainPageReload();">首页</a></li>
				<c:choose>
					<c:when test="${isRank1}">
						<li><a href="javascript:" onclick="lh.cmsJump('/admin/console/level1users');">调度台信息列表</a></li>
						<li class="active">添加一阶调度台信息</li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:" onclick="lh.cmsJump('/admin/console/level2users');">调度台信息列表</a></li>
						<li class="active">添加二阶调度台信息</li>
					</c:otherwise>
				</c:choose>
			</ul>		
		</div>
		
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<sf:form method="post" modelAttribute="userDto" id="addForm" cssClass="form-horizontal" role="form">
					<!-- #section:elements.form -->
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 登录名(必须是英文): </label>
						<div class="col-sm-9">
							<sf:input path="username" id="username" size="30" cssClass="col-xs-10 col-sm-5" placeholder="用户名"/>
							<sf:errors cssClass="errorContainer" path="username"/>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 显示名称(可以是中文): </label>
						<div class="col-sm-9">
							<sf:input path="nickname" id="nickname" size="30" cssClass="col-xs-10 col-sm-5" placeholder="显示名称"/>
							<sf:errors cssClass="errorContainer" path="nickname"/>
						</div>
					</div>

					<!-- /section:elements.form -->

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-2">用户密码: </label>

						<div class="col-sm-9">
							<sf:input class="col-xs-10 col-sm-5" id="password" type="password" path="password" placeholder="用户密码" />
							<span class="help-inline col-xs-12 col-sm-7">
								<span class="middle"><sf:errors cssClass="errorContainer" path="password"/></span>
							</span>
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-2">确认密码: </label>

						<div class="col-sm-9">
							<input class="col-xs-10 col-sm-5" id="confirmPwd" name="confirmPwd" type="password" placeholder="确认密码" />
							<span class="help-inline col-xs-12 col-sm-7">
								<span class="middle"></span>
							</span>
						</div>
					</div>
					
				
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 手机: </label>
						<div class="col-sm-9">
							<sf:input path="phone" size="30" id="phone" cssClass="col-xs-10 col-sm-5" placeholder="手机号码"/>
							<sf:errors cssClass="errorContainer" path="phone"/>
						</div>
					</div>
				
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">调度台优先级（11-30，30最高）: </label>
						<div class="col-sm-9">
							<sf:input path="priority" id="priority" size="30" cssClass="col-xs-10 col-sm-5"   value="11"/>
							<sf:errors cssClass="errorContainer" path="priority"/>

						</div>

					</div>
			

					<div class="clearfix form-actions">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn btn-info" type="button" onclick="addMainObj();">
								<i class="ace-icon fa fa-check bigger-110"></i>
								提交
							</button>
							&nbsp; &nbsp; &nbsp;
							<button class="btn" type="reset">
								<i class="ace-icon fa fa-undo bigger-110"></i>
								重置
							</button>
						</div>
					</div>
					
					
				</sf:form>
				</div>
			</div><!-- /.row -->
		</div>
	</div>
<%@ include file="../common/common_js.jsp"%>
<script src="<%=request.getContextPath()%>/resources/lh/js/console/add.js" title="v"></script>

</body>
</html>