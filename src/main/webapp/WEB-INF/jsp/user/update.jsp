<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/common_css.jsp"%>
</head>
<body class="no-skin">
	<input type="hidden" value="${userDto.id}" id="userId" />
	<div class="main-content-inner">
		<!-- #section:basics/content.breadcrumbs -->
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
			</script>

			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="javascript:" onclick="lh.mainPageReload();">首页</a></li>
				<li><a href="javascript:" onclick="lh.cmsJump('/admin/user/users');">用户信息列表</a></li>
				<li class="active">修改用户信息</li>
			</ul>
			<!-- /.breadcrumb -->
		</div>

		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<sf:form method="post" modelAttribute="userDto" id="addForm" cssClass="form-horizontal" role="form">
						<!-- #section:elements.form -->
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 显示名(可以是中文): </label>
							<div class="col-sm-9">
								<sf:input path="nickname" id="nickname" size="30" cssClass="col-xs-10 col-sm-5" />
								<sf:errors cssClass="errorContainer" path="nickname" />
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 手机号码: </label>
							<div class="col-sm-9">
								<sf:input path="phone" id="phone" size="30" cssClass="col-xs-10 col-sm-5" />
								<sf:errors cssClass="errorContainer" path="phone" />
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
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 用户优先级（1-10，10最高优先级）: </label>
							<div class="col-sm-9">
								<sf:input path="userPriority" id="userPriority" size="30" cssClass="col-xs-10 col-sm-5" />
								<sf:errors cssClass="errorContainer" path="userPriority" />
							</div>
						</div>

						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 欲加入用户组: </label>
							<div class="col-sm-6" style="z-index: 200;">
								<div class="row">
									<div class="col-xs-12">
										<!-- PAGE CONTENT BEGINS -->
										<div class="row">
											<div class="col-xs-12">
												<button type="button" id="checkAllBtn" onclick="addAllUserGroup(${userDto.id});">批量加入</button>
												<button type="button" id="cleanAllBtn" onclick="delAllUserGroup(${userDto.id});">批量退出</button>
												<table id="datagrid" class="table table-striped table-bordered table-hover black">
													<thead>
														<tr>
															<th><input id="groupIds" type="checkbox" onclick="checkAllInput();"></th>
															<th>组名称</th>
															<th>状态/操作</th>
														</tr>
													</thead>

												</table>
											</div>
											<!-- /.span -->
										</div>
										<!-- /.row -->
									</div>
									<!-- /.col -->
								</div>
								<!-- /.row -->
							</div>
						</div>

						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 管理台: </label>
							<div class="col-sm-6"  style="z-index: 200;">
								<div class="row">
									<div class="col-xs-12">
										<!-- PAGE CONTENT BEGINS -->
										<div class="row">
											<div class="col-xs-12">
												<button type="button" id="checkAllBtn" onclick="addAllUserGroup1(${userDto.id});">批量加入</button>
												<button type="button" id="cleanAllBtn" onclick="delAllUserGroup1(${userDto.id});">批量退出</button>
												<table id="datagridConsoler" class="table table-striped table-bordered table-hover black">
													<thead>
														<tr>
															<th><input id="groupIds1" type="checkbox" onclick="checkAllInput1();"></th>
															<th>管理台名称</th>
															<th>状态/操作</th>
														</tr>
													</thead>
												</table>
											</div>
											<!-- /.span -->
										</div>
										<!-- /.row -->
									</div>
									<!-- /.col -->
								</div>
								<!-- /.row -->
							</div>
						</div>

						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="button" onclick="updateMainObj(${userDto.id});">
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
		</div>
		<!-- /.row -->
	</div>
	<%@ include file="../common/common_js.jsp"%>
	<%@ include file="../lh/common/common_back_js.jsp"%>
	<script src="<%=request.getContextPath()%>/resources/ace/assets/js/jquery.dataTables.js"></script>
	<script src="<%=request.getContextPath()%>/resources/ace/assets/js/jquery.dataTables.bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lh/third-party/bootstrap-select-1.10.0/js/bootstrap-select.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/lh/js/user/update.js" title="v"></script>
</body>
</html>