<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/common_css.jsp"%>
</head>
<body class="no-skin">
	<input type="hidden" value="${isRank1}" id="isRank1" />
	<input type="hidden" value="${userId}" id="groupId" />
	<input type="hidden" value="${userId}" id="userId" />
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
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="javascript:" onclick="lh.mainPageReload();">首页</a></li>
				<c:choose>
					<c:when test="${isRank1}">
						<li><a href="javascript:" onclick="lh.cmsJump('/admin/console/level1users');">调度台信息列表</a></li>
						<li class="active">修改一阶调度台信息</li>
						
					</c:when>
					<c:otherwise>
						<li><a href="javascript:" onclick="lh.cmsJump('/admin/console/level2users');">调度台信息列表</a></li>
						<li class="active">修改二阶调度台信息</li>
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

						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 用户优先级（11-30，11最高）: </label>
							<div class="col-sm-9">
								<sf:input path="priority" id="priority" size="30" cssClass="col-xs-10 col-sm-5" />
								<sf:errors cssClass="errorContainer" path="priority" />

							</div>

						</div>
						<c:choose>
							<c:when test="${isRank1 }">
								<div class="space-4"></div>
								<div class="form-group">
							<div class="space-4"></div>
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1">选择被管理员成员: </label>
									<div class="col-sm-6" style="z-index: 200;">
										<div class="row">
											<div class="col-xs-12">
												<!-- PAGE CONTENT BEGINS -->
												<div class="row">
													<div class="col-xs-12">
														<button type="button" onclick="addAllGroupUser(${userId});">批量加入</button>
														<button type="button" onclick="delAllGroupUser(${userId});">批量退出</button>
														<table id="datagrid1" class="table table-striped table-bordered table-hover black">
															<thead>
																<tr>
																	<th><input id="groupIds" type="checkbox" onclick="checkAllInput();"></th>
																	<th>用户名</th>
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
				</div>

				<div class="form-group">
				<input type="hidden" value="${logonName}" id="logonName" />
					<label class="col-sm-3 control-label no-padding-right" for="form-field-1">管理我的二阶管理台: </label>
					<div class="col-sm-6" style="z-index: 200;">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="row">
									<div class="col-xs-12">
										<button type="button" id="checkAllBtn" onclick="addAllGroupUser1(${userName});">批量加入</button>
										<button type="button" id="cleanAllBtn" onclick="delAllGroupUser1(${userName});">批量退出</button>
										<table id="consol2Table" class="table table-striped table-bordered table-hover black">
											<thead>
												<tr>
													<th><input id="groupIds1" type="checkbox" onclick="checkAllInput1();"></th>
													<th>用户名</th>
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
				</c:when>
				<c:otherwise>
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 选择被管理调度台: </label>
							<div class="col-sm-6">
							<div class="row">
									<div class="col-xs-12">
										<!-- PAGE CONTENT BEGINS -->
										<div class="row">
											<div class="col-xs-12">
												<button type="button" id="checkAllBtn" onclick="addAllGroupUser2(${userId});">批量加入</button>
												<button type="button" id="cleanAllBtn" onclick="delAllGroupUser2(${userId});">批量退出</button>
												<table id="consol1Table" class="table table-striped table-bordered table-hover black">
													<thead>
														<tr>
															<th><input id="groupIds2" type="checkbox" onclick="checkAllInput2();"></th>
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
				</c:otherwise>
				</c:choose>

				<div class="clearfix form-actions">
					<div class="col-md-offset-3 col-md-9">
						<button class="btn btn-info" type="button" onclick="updateMainObj(${userId});">
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
	<script src="<%=request.getContextPath()%>/resources/lh/js/console/update.js" title="v"></script>

</body>
</html>