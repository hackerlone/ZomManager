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
			<script type="text/javascript">
				try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
			</script>

			<ul class="breadcrumb">
				<li>
					<i class="ace-icon fa fa-home home-icon"></i>
					<a href="#">版本管理</a>
				</li>
				<li class="active">手机客户端版本管理</li>
			</ul><!-- /.breadcrumb -->			
		</div>
		
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="row">
						<div class="col-xs-12 col-sm-9">
							<!-- #section:pages/profile.info -->
							<div class="profile-user-info profile-user-info-striped">
								<div class="profile-info-row">
									<div class="profile-info-name"> 服务器版本</div>

									<div class="profile-info-value">
										<!--<span class="editable editable-click" style="display: inline;">${group.groupName }</span>  -->
										${bmsVer.serverVersion }
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name"> 客户端操作系统 </div>
                                    
									<div class="profile-info-value">
										${bmsVer.clientOsType }
										</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name"> 客户端版本 </div>
                                    
									<div class="profile-info-value">
										${bmsVer.clientVersion }
										</div>
								</div>
								
								<div class="profile-info-row">
									<div class="profile-info-name"> 客户端程序链接 </div>
                                    
									<div class="profile-info-value">
										${bmsVer.clientUrl }
										</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name"> 是否兼容老版本 </div>
                                    
                                    <div class="profile-info-value">
											<c:if test="${bmsVer.latestServer eq 0 }">
												<class="list_link">不兼容</span>		</class>
										
											</c:if>
											<c:if test="${bmsVer.latestServer eq 1 }">
												<class="list_link">兼容</span>		
											</c:if>
										</div>
								</div>								

								<div class="profile-info-row">
									<div class="profile-info-name"> 最新客户端版本</div>
                                     <div class="profile-info-value">
										<c:if test="${bmsVer.latestClient eq 0 }">
												<class="list_link">no</span>		</class>
										
											</c:if>
											<c:if test="${bmsVer.latestClient eq 1 }">
												<class="list_link">yes</span>		
											</c:if>
											</div>
										</td>
										
										
								</div>	
								
								<div class="profile-info-row">
									<div class="profile-info-name"> 版本描述</div>
                                    
									<div class="profile-info-value">
										${bmsVer.clientDescription }
										</div>
								</div>									
								<div class="profile-info-row">
									<div class="profile-info-name"> </div>

									<div class="profile-info-value">
										<span class="editable editable-click" style="display: inline;">
											<a class="btn btn-primary no-radius" href="update/${bmsVer.id }">
												<i class="ace-icon fa fa-pencil-square-o"></i>
												修改版本信息
											</a>
										</span>
									</div>
								</div>
							</div>
						</div>
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