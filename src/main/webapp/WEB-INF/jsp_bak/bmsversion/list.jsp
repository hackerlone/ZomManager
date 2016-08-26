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
					<a href="#">客户端管理</a>
				</li>
				<li class="active">客户端版本管理</li>
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
										<th>ID</th>
										<th>服务器版本</th>
										<th>客户端系统</th>		
										<th>客户端版本</th>												
							
										<th>是否兼容老版本</th>
										<th>最新客户端版本</th>
										<th>描述 </th>
										<th>用户操作</th>
										
									</tr>
								</thead>

								<tbody>
								<c:forEach items="${datas.datas }" var="bmsVer" >
									<tr>
										
										<td><a href="<%=request.getContextPath() %>/admin/version/${bmsVer.id }" class="list_link">${bmsVer.id }</a></td>
											
									
										<td class="hidden-480" >
											<class="list_link" ><span style="color: blue">${bmsVer.serverVersion }</span>
										</td>
										
										<td class="hidden-480" >
											<class="list_link" ><span style="color: blue">${bmsVer.clientOsType }</span>
										</td>
		
										<td  >
											<class="list_link"><span style="color: blue">${bmsVer.clientVersion }</span>
										</td>

										<td  >
										
											<c:if test="${bmsVer.latestServer eq 0 }">
												<class="list_link"><span style="color: blue">不兼容</span>		</class>
										
											</c:if>
											<c:if test="${bmsVer.latestServer eq 1 }">
												<class="list_link"><span style="color: blue">兼容</span>		
											</c:if>
											
										</td>
										
										<td  class="hidden-480">
										<c:if test="${bmsVer.latestClient eq 0 }">
												<class="list_link"><span style="color: blue">no</span>		</class>
										
											</c:if>
											<c:if test="${bmsVer.latestClient eq 1 }">
												<class="list_link"><span style="color: blue">yes</span>		
											</c:if>
										</td>
 
 										<td  class="hidden-480">
											<class="list_link"><span style="color: blue">${bmsVer.clientDescription }</span>
										</td>
										                         				 
									
			                           

									
										<td>
									
												

												<a class="btn btn-xs btn-info" href="update/${bmsVer.id }" title="编辑">
													<i class="ace-icon fa fa-pencil bigger-120"></i>
												</a>

												<a class="btn btn-xs btn-danger" href="delete/${bmsVer.id }" title="删除">
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
												<a href="<%=request.getContextPath() %>/admin/version/add" target="mainFrame" style="color:#FFF;text-decoration:none;" title="添加用户" class="btn btn-info fa">+</a>
												<a href="<%=request.getContextPath() %>/admin/version/list" style="color:#FFF;text-decoration:none;" class="btn btn-info fa fa-refresh" title="刷新列表"></a>
												<a href="<%=request.getContextPath() %>/admin/file/upload" target="mainFrame" style="color:#FFF;text-decoration:none;" title="发布新版本" class="btn btn-info fa">发布新版本</a>
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