<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="../common/common_css.jsp"%>

</head>
<body class="no-skin">
	<div class="main-content-inner">
		<!-- #section:basics/content.breadcrumbs -->
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
			</script>

			<ul class="breadcrumb">
				<li>
					<i class="ace-icon fa fa-home home-icon"></i>
					<a href="/admin/group/groups">组管理</a>
				</li>
				<li class="active">添加组</li>
			</ul><!-- /.breadcrumb -->			
		</div>
		
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<sf:form method="post" modelAttribute="groupDto" id="addForm" cssClass="form-horizontal" role="form" >
					<!-- #section:elements.form -->
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 组名称: </label>
						<div class="col-sm-9">
							<sf:input path="groupName" size="30" cssClass="col-xs-10 col-sm-5"/>
							<sf:errors cssClass="errorContainer" path="groupName"/>
						</div>
					</div>
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 选择组管理员: </label>
					<sf:select id="ownerId" path="ownerId" >
						        <option value="">-请选择-</option>
								<sf:options items="${users}" path="user"   itemLabel="displayName" itemValue="id" ></sf:options>
							</sf:select>
			
					
					</div>

					
					<div class="space-4"></div>
					

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
<script src="<%=request.getContextPath()%>/resources/lh/js/group/add.js" title="v"></script>

</body>
</html>