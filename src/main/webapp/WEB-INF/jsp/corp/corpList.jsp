<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/common_css.jsp"%>
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
			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="javascript:" onclick="lh.mainPageReload();">首页</a></li>
				<li class="active">公司列表</li>
			</ul>
			<!-- /.breadcrumb -->
		</div>

		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<div class="form-group">
						<div>
							<label style="width: 100px;" class="col-sm-3 control-label no-padding-right" for="corpNameLike">公司名称：</label> <input style="width: 140px;" type="text" id="corpNameLike" placeholder="" class="col-xs-10 col-sm-5">
						</div>
						<div>
							<label style="width: 100px;" class="col-sm-3 control-label no-padding-right" for="usernameLike">登陆名：</label> <input style="width: 140px;" type="text" id="usernameLike" placeholder="" class="col-xs-10 col-sm-5">
							<div></div>
							<label style="width: 100px;" class="col-sm-3 control-label no-padding-right" for="phoneLike">登陆手机：</label> <input style="width: 140px;" type="text" id="phoneLike" placeholder="" class="col-xs-10 col-sm-5">
						</div>
						<div style="display: inline-block; position: relative; bottom: 4px;">
							<label style="width: 80px;" class="col-sm-3 control-label no-padding-right" for="zone">zone：</label> <select id="zone" style="width: 80px;">
								<!--  form-control col-xs-10 col-sm-5 -->
								<option value="">全部</option>
								<c:forEach var="zone" items="${zoneAssign}">
									<option value="${zone.id}">${zone.name}</option>
								</c:forEach>
							</select>
						</div>
						<div style="display: inline-block; position: relative; bottom: 4px;">
							<label style="width: 80px;" class="col-sm-3 control-label no-padding-right" for="status">状态：</label> <select id="status" style="width: 160px;" >
								<!--  form-control col-xs-10 col-sm-5 -->
								<option value="">全部</option>
								<option value="1">启用</option>
								<option value="0">停用</option>
							</select>
						</div>
						<div style="display: inline-block;">
							<button onclick="doSearch();" style="margin-left: 10px;" class="btn btn-info" type="button">
								<i class="ace-icon fa fa-search bigger-110"></i>查询
							</button>
							<button onclick="clearSearch();" class="btn" type="reset">
								<i class="ace-icon fa fa-undo bigger-110"></i>重置
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="row">
						<div class="col-xs-12">
							<table id="datagrid" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>ID</th>
										<th>公司名称</th>
										<th>登录名</th>
										<th>状态</th>
										<th>登录手机</th>
										<th>最大用户数</th>
										<th>最大组数</th>
										<th>最大组成员数</th>
										<th>最大调度台数</th>
										<th>用户操作</th>
									</tr>
								</thead>

								<%-- <tbody>
									<c:forEach items="${datas.datas }" var="corporation">
										<tr>
											<td>
												<!-- id --> ${corporation.id }</a>
											</td>
											<td>
												<!-- id --> ${corporation.corpName }</a>
											</td>
											<td>
												<!-- id --> ${corporation.username }</a>
											</td>
											<td class="hidden-480"><c:if test="${corporation.status eq 0 }">
													<span class="emp">停用</span>
													<a href="<%=request.getContextPath() %>/admin/corp/updateStatus/${corporation.id }" class="list_op">启用</a>
												</c:if> <c:if test="${corporation.status eq 1 }">
													<span>启用</span>
													<a href="<%=request.getContextPath() %>/admin/corp/updateStatus/${corporation.id }" class="list_op">停用</a>
												</c:if></td>
											<td>
												<!-- id --> ${corporation.phone }
											</td>
											<!-- 
																				<td>  
											${corporation.expireDate }
										</td>
										 -->

											<td>
												<!-- id --> ${corporation.maxUser }
											</td>
											<td>
												<!-- id --> ${corporation.maxGroup }
											</td>
											<td>${corporation.maxUserGroup }</td>
											<td>${corporation.maxConsole }</td>
											<td><a class="btn btn-xs btn-info" href="${corporation.id }" title="编辑"> <i class="ace-icon fa fa-pencil bigger-120"></i>
											</a> <a class="btn btn-xs btn-danger" href="delete/${corporation.id }" title="删除"> <i class="ace-icon fa fa-trash-o bigger-120"></i>
											</a></td>
										</tr>
									</c:forEach>
								</tbody> --%>
							</table>
							<div id="optDiv"></div>
							<div class="page-header position-relative">
								<table style="width: 100%;">
									<tbody>
										<tr>
											<td style="vertical-align: top;"><a href="<%=request.getContextPath()%>/admin/corp/add" target="mainFrame" style="color: #FFF; text-decoration: none;" title="添加用户" class="btn btn-info fa">+</a> <a
												href="<%=request.getContextPath()%>/admin/corp/users" style="color: #FFF; text-decoration: none;" class="btn btn-info fa fa-refresh" title="刷新列表"></a>
												
												 <!-- <a 
												style="color: #FFF; text-decoration: none;" class="btn btn-info fa fa-refresh" title=""></a> -->
												 <button onclick="exportExcel();" id="exportExcelBtn" type="button" class="btn btn-info">导出表格</button>
												 <!-- <button onclick="importExcel();" id="importExcelBtn" type="button" class="btn btn-success"> -->

												<button id="browse" type="button" class="btn btn-success">导入表格</button>
												
												<%-- <div style="display: inline-block; float: left;">
													<img id="pic" class="picurl" src="${pic.picPath}" style="height: 60px; max-width: 100px; overflow: hidden; padding: 5px;" /> 
													<input type="hidden" name="filePaths" id="filePaths" value="${filePath}" /> <input
														type="hidden" name="fileDBIds" id="fileDBIds" />
												</div> --%>
												<!-- <div id="upload_outer_div" style="margin-top: 30px;">
													上传文件进度展示
												</div>  -->
											
												<a href="/manager/back/charge.html" id="importExcelBtn" type="button" class="btn btn-warning">充值</a>
												<!-- <a href="/manager/back/charge.html" id="importExcelBtn" style="color: #FFF; text-decoration: none;" class="btn btn-info fa fa-refresh" title="充值"></a> -->
												</td>
											
											
											
											<%-- 
											<td style="vertical-align: top;">
												<c:if test="${datas.total > 0}">
													<jsp:include page="/jsp/pager.jsp">
														<jsp:param value="${datas.total }" name="totalRecord" />
														<jsp:param value="users" name="url" />
													</jsp:include>
												</c:if>
											</td> --%>
										</tr>
									</tbody>
								</table>
							</div>
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
	<%@ include file="../common/common_js.jsp"%>
	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/lh/third-party/bootstrap/js/bootstrap.min.js"></script> --%>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lh/third-party/bootstrap-select-1.10.0/js/bootstrap-select.min.js"></script>
	<!-- <script type="text/javascript"> lh.param = ${paramJson} </script> -->
	<script src="<%=request.getContextPath()%>/resources/lh/js/corp/corp.js" title="v"></script>
	<script src="<%=request.getContextPath()%>/resources/lh/js/common/common_upload.js" title="v"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lh/
third-party/plupload/js/plupload.full.min.js"></script>
</body>
</html>