<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/common_css.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/validate/main.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/ace/assets/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/lh/third-party/bootstrap-select-1.10.0/css/bootstrap-select.min.css" />
<style type="text/css">
.btn-white {
	color: #333;
	background-color: #fff;
	border-color: #ccc;
}
</style>

</head>
<body class="no-skin">
	<div class="main-content-inner">
		<!-- #section:basics/content.breadcrumbs -->
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>

			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="javascript:" onclick="lh.back();">公司列表</a></li>
				<li class="active">添加公司</li>
			</ul>
			<!-- /.breadcrumb -->
		</div>

		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<sf:form method="post" modelAttribute="corp" id="addForm" cssClass="form-horizontal" role="form"  onsubmit="return false;">
						<!-- #section:elements.form -->
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right red" for="form-field-1"> 用户名(必须是英文): *</i></label>
							<div class="col-sm-9">
								<sf:input path="username" id="username" size="30" cssClass="col-xs-10 col-sm-5" placeholder="用户名" />
								<sf:errors cssClass="errorContainer" path="username" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right red" for="form-field-1"> 公司名称(可以是中文): *</label>

							<div class="col-sm-9">
								<sf:input path="corpName" id="corpName" size="30" cssClass="col-xs-10 col-sm-5" placeholder="公司名称" />
								<sf:errors cssClass="errorContainer" path="corpName" />
							</div>
						</div>

						<!-- /section:elements.form -->
						<!-- <script type="text/javascript" src="base64.js"></script> -->

						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right red" for="form-field-2">用户密码: *</label>

							<div class="col-sm-9">
								<sf:input class="col-xs-10 col-sm-5" id="corpPassword" type="password" path="corpPassword" placeholder="用户密码" />
								<span class="help-inline col-xs-12 col-sm-7"> <span class="middle"><sf:errors cssClass="errorContainer" path="corpPassword" /></span>
								</span>
							</div>
						</div>

						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right red" for="form-field-2">确认密码: *</label>

							<div class="col-sm-9">
								<input class="col-xs-10 col-sm-5" id="confirmCorpPwd" name="confirmCorpPwd" type="password" placeholder="确认密码" /> <span class="help-inline col-xs-12 col-sm-7"> <span class="middle"></span>
								</span>
							</div>
						</div>

						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right red" for="form-field-1"> 手机号码: *</label>
							<div class="col-sm-9">
								<sf:input path="phone" id="phone" size="30" cssClass="col-xs-10 col-sm-5" placeholder="手机号码" />
								<sf:errors cssClass="errorContainer" path="phone" />
							</div>
						</div>

						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right red" for="form-field-1"> 电子邮箱: *</label>
							<div class="col-sm-9">
								<sf:input path="email" id="email" size="30" cssClass="col-xs-10 col-sm-5" placeholder="电子邮箱" />
								<sf:errors cssClass="errorContainer" path="email" />
							</div>
						</div>



						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right red" for="form-field-1"> 使用期限: *</label>
							<div class="col-sm-9" id="dataTimes" style="width: 256.86px;">
								<input id="date-timepicker1" type="text" class="form-control" /> <span class="input-group-addon"> <i class="fa fa-clock-o bigger-110"></i>
								</span>
							</div>
						</div>



						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right " for="form-field-1"> 最大用户数: </label>
							<div class="col-sm-9">
								<sf:input type="number" path="maxUser" id="maxUser" size="30" color="red" cssClass="col-xs-10 col-sm-5" placeholder="100" />
								<sf:errors cssClass="errorContainer" path="maxUser" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right " for="form-field-1"> 最大用组数: </label>
							<div class="col-sm-9">
								<sf:input type="number" path="maxGroup" id="maxGroup" size="30" color="red" cssClass="col-xs-10 col-sm-5" placeholder="100" />
								<sf:errors cssClass="errorContainer" path="maxGroup" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right " for="form-field-1"> 最大用调度台数: </label>
							<div class="col-sm-9">
								<sf:input type="number" path="maxConsole" id="maxConsole" size="30" color="red" cssClass="col-xs-10 col-sm-5" placeholder="10" />
								<sf:errors cssClass="errorContainer" path="maxConsole" />
							</div>
						</div>
						<div class="space-4"></div>

						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right " for="permissionLevel"> 权限: </label>

							<%-- <sf:select path="permissionLevel" tbindex="1">
								<sf:option value="0">普通</sf:option>
								<sf:option value="1">管理员</sf:option>
							</sf:select> --%>
							<div class="col-sm-9">
								<select class="col-xs-10 col-sm-5" id="permissionLevel" style="height: 34px;">
									<option value="0">普通</option>
									<option value="1">管理员</option>
								</select>
							</div>


						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right " for="priorityLevel"> 优先级: </label>

							<%-- <sf:select path="priorityLevel" tbindex="2">
								<sf:option value="0">普通</sf:option>
								<sf:option value="1">一级</sf:option>
								<sf:option value="1">二级</sf:option>
								<sf:option value="2">三级</sf:option>
							</sf:select> --%>
							<div class="col-sm-9">
								<select class="col-xs-10 col-sm-5" id="priorityLevel" style="height: 34px;">
									<option value="0">普通</option>
									<option value="1">一级</option>
									<option value="1">二级</option>
									<option value="2">三级</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">要加入的zone: </label>
							<div class="col-sm-9">

								<div style="display: inline-block; position: relative; bottom: 4px;">
									 <select id="zone" class="selectpicker"  multiple data-noneSelectedText="请选择要加入的zone">
										<!--  form-control col-xs-10 col-sm-5 -->
										<c:forEach var="zone" items="${zoneAssign}">
											<option value="${zone.id}">${zone.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>

						<div class="space-4"></div>
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="button" onclick="addMainObj();">
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
	<script src="<%=request.getContextPath()%>/resources/ace/assets/js/date-time/bootstrap-datetimepicker.js" title="v"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lh/third-party/bootstrap-select-1.10.0/js/bootstrap-select.min.js"></script>
	<script type="text/javascript">
		$("#dataTimes").datetimepicker({
			format : 'YYYY-MM-DD hh:mm:ss'
		});
	</script>

	<script src="<%=request.getContextPath()%>/resources/lh/js/corp/add.js" title="v"></script>



</body>
</html>