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
				<li class="active">用户信息管理</li>
			</ul><!-- /.breadcrumb -->			
		</div>
		
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="row">
						<div class="col-xs-12">
							<table id="sample-table-1" class="table table-striped table-bordered table-hover black">
								<thead>
									<tr>
										<th>用户ID</th>
										<th>用户昵称</th>		
										<th>用户状态</th>																	
										<th>用户手机</th>
										<th>用户优先级</th>
										<th>管理我的调度台</th>
										<th>用户已加入组  </th>
										<th>用户操作</th>
									</tr>
								</thead>

								<tbody>
								<c:forEach items="${datas.datas }" var="userDto" >
									<tr>
										<td>
											${userDto.id }
										</td>
										<td><class="list_link" >${userDto.nickname }</td>
										<td class="hidden-480">
											<c:if test="${userDto.status eq 0 }">
												<span class="emp">停用</span>
												<a href="<%=request.getContextPath() %>/admin/user/updateStatus/${userDto.id }" class="list_op">启用</a>
											</c:if>
											<c:if test="${userDto.status eq 1 }">
												<span>启用</span>
												<a href="<%=request.getContextPath() %>/admin/user/updateStatus/${userDto.id }" class="list_op">停用</a>
											</c:if>
										</td>
										
										<td class="hidden-480" >
											<class="list_link" ><span style="color: blue">${userDto.phone }</span>
										</td>
		
										<td  class="hidden-480">
											<class="list_link"><span style="color: blue">${userDto.userPriority }</span>
										</td>
										<td class="hidden-480" >

										<c:forEach items="${userDto.adminNames}" var="adminNames" varStatus="status" >
									           <!-- display joined groups -->
						     
						                         ${adminNames }  
						                                         							
                           				<c:if test="${status.count%3==0}">
                           					<p></p>
                          				 </c:if>  
                          				 </c:forEach>
                          				 
										<td class="hidden-480">
										
										<c:forEach items="${userDto.groupNames}" var="groupNames" varStatus="status" >
									           <!-- display joined groups -->
						     
						                         ${groupNames }  
						                                         							
                           				<c:if test="${status.count%4==0}">
                           					<p></p>
                          				 </c:if> 
				                        
 										</c:forEach>
			                           

										</td>
										
										<td>
									
												

												<a class="btn btn-xs btn-info" href="update/${userDto.id }" title="编辑">
													<i class="ace-icon fa fa-pencil bigger-120"></i>
												</a>

												<a class="btn btn-xs btn-danger" href="delete/${userDto.id }" title="删除">
													<i class="ace-icon fa fa-trash-o bigger-120"></i>
												</a>
												
									            <a class="btn btn-xs btn-info" href="updatePwd/${userDto.id }" title="修改密码">
													<i class="ace-icon fa fa-lock bigger-120"></i>
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
												<a href="<%=request.getContextPath() %>/admin/user/add" target="mainFrame" style="color:#FFF;text-decoration:none;" title="添加用户" class="btn btn-info fa">+</a>
												<a href="<%=request.getContextPath() %>/admin/user/users" style="color:#FFF;text-decoration:none;" class="btn btn-info fa fa-refresh" title="刷新列表"></a>
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