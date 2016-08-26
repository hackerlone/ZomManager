<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/common_css.jsp"%>
</head>
<body class="no-skin">
	<div class="main-content-inner">
		<!-- #section:basics/content.breadcrumbs -->
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li>
					<i class="ace-icon fa fa-home home-icon"></i>
					<a href="#">用户管理</a>
				</li>
				<li class="active">公司信息管理</li>
			</ul><!-- /.breadcrumb -->			
		</div>
		
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="row">
						<div class="col-xs-12">
							<table id="sample-table-1" class="table table-striped table-bordered table-hover">
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

								<tbody>
								<c:forEach items="${datas.datas }" var="corporation">
									<tr>
										<td>  <!-- id -->
											${corporation.id }</a>
										</td>
										<td>  <!-- id -->
											${corporation.corpName }</a>
										</td>
										<td>  <!-- id -->
											${corporation.username }</a>
										</td>
																				<td class="hidden-480">
											<c:if test="${corporation.status eq 0 }">
												<span class="emp">停用</span>
												<a href="<%=request.getContextPath() %>/admin/corp/updateStatus/${corporation.id }" class="list_op">启用</a>
											</c:if>
											<c:if test="${corporation.status eq 1 }">
												<span>启用</span>
												<a href="<%=request.getContextPath() %>/admin/corp/updateStatus/${corporation.id }" class="list_op">停用</a>
											</c:if>
										</td>
										<td>  <!-- id -->
											${corporation.phone }
										</td>
										<!-- 
																				<td>  
											${corporation.expireDate }
										</td>
										 -->

										<td>  <!-- id -->
											${corporation.maxUser }
										</td>
										<td>  <!-- id -->
											${corporation.maxGroup }
										</td>
																				<td> 
											${corporation.maxUserGroup }
										</td>
																				<td>  
											${corporation.maxConsole }

</td>
<td>

												<a class="btn btn-xs btn-info" href="${corporation.id }" title="编辑">
													<i class="ace-icon fa fa-pencil bigger-120"></i>
												</a>

												<a class="btn btn-xs btn-danger" href="delete/${corporation.id }" title="删除">
													<i class="ace-icon fa fa-trash-o bigger-120"></i>
												</a>


									
										</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							<div class="page-header position-relative">
								<table style="width: 100%;">
									<tbody>
										<tr>
											<td style="vertical-align: top;">
												<a href="<%=request.getContextPath() %>/admin/corp/add" target="mainFrame" style="color:#FFF;text-decoration:none;" title="添加用户" class="btn btn-info fa">+</a>
												<a href="<%=request.getContextPath() %>/admin/corp/users" style="color:#FFF;text-decoration:none;" class="btn btn-info fa fa-refresh" title="刷新列表"></a>
											</td>
											<td style="vertical-align: top;">
												<c:if test="${datas.total > 0}">
													<jsp:include page="/jsp/pager.jsp">
														<jsp:param value="${datas.total }" name="totalRecord"/>
														<jsp:param value="users" name="url"/>
													</jsp:include>
												</c:if>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div><!-- /.span -->
					</div><!-- /.row -->
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div>
	</div>
<%@ include file="../common/common_js.jsp"%>

<script src="<%=request.getContextPath() %>/resources/ace/assets/js/jquery.dataTables.js"></script>
<script src="<%=request.getContextPath() %>/resources/ace/assets/js/jquery.dataTables.bootstrap.js"></script>

</body>
</html>