<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- #section:basics/sidebar -->
			<div id="sidebar" class="sidebar responsive">
				<ul class="nav nav-list">
					<li class="active">
						<a href="javascript:">
							<i class="menu-icon fa fa-desktop"></i>
							<span class="menu-text"> 查看首页 </span>
						</a>

						<b class="arrow"></b>
					</li>
						<c:if test="${isAdmin }">
					<li class="">
						<a href="javascript:" target="mainFrame" class="dropdown-toggle">
							<i class="menu-icon fa glyphicon-user  fa-building"></i>
							<span class="menu-text">
								公司管理
							</span>

							<b class="arrow fa fa-angle-down"></b>

						</a>

						<b class="arrow"></b>

						<ul class="submenu">
							<li class="">
								<a href="javascript:" onclick="showPage('/admin/corp/users');" target="mainFrame">
									<i class="menu-icon fa fa-caret-right"></i>
										公司列表
									<b class="arrow fa fa-angle-down"></b>
								</a>
							</li>
<!--  
							<li class="">
								<a href="<%=request.getContextPath() %>/admin/corp/groups" target="mainFrame">
									<i class="menu-icon fa fa-caret-right"></i>
									公司信息统计
								</a>
								<b class="arrow"></b>
							</li>
-->
						</ul>
					</li>
					
										<li class="">
						<a href="javascript:" onclick="showPage('/admin/version/list');" target="mainFrame">
							<i class="menu-icon fa glyphicon-user fa-mobile"></i>
							<span class="menu-text">
								客户端版本管理
							</span>

			
						</a>

					</li>
					</c:if>
					<li class="">
						<a href="javascript:" onclick="showPage('/admin/user/users');" target="mainFrame">
							<i class="menu-icon fa glyphicon-user fa-user"></i>
							<span class="menu-text">
								用户管理
							</span>

				<!-- 			<b class="arrow fa fa-angle-down"></b>  -->
						</a>
<!-- 
						<b class="arrow"></b>

						<ul class="submenu">
							<li class="">
								<a href="<%=request.getContextPath() %>/admin/user/users" target="mainFrame">
									<i class="menu-icon fa fa-caret-right"></i>
										用户1
									<b class="arrow fa fa-angle-down"></b>
								</a>
							</li>

							<li class="">
								<a href="<%=request.getContextPath() %>/admin/group/groups" target="mainFrame">
									<i class="menu-icon fa fa-caret-right"></i>
									用户2
								</a>
								<b class="arrow"></b>
							</li>

							<li class="">
								<a href="<%=request.getContextPath() %>/admin/role/roles" target="mainFrame">
									<i class="menu-icon fa fa-caret-right"></i>
									用户3
								</a>
								<b class="arrow"></b>
							</li>
						</ul>
						-->
					</li>
					
					
					
					<li class="">
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-group"></i>
							<span class="menu-text"> 组管理 </span>

							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
							<li class="">
								<a href="javascript:" onclick="showPage('/admin/group/groups');" target="mainFrame">
									<i class="menu-icon fa fa-group"></i>
									用户组管理
									
								</a>                             
                               
								<b class="arrow"></b>
							</li>
<!--  
							<li class="">
							
								<a href="<%=request.getContextPath() %>/admin/group/consolegrps" target="mainFrame">
									<i class="menu-icon fa fa-desktop"></i>
									调度台组管理
								</a>
-->
								<b class="arrow"></b>
							</li>
						</ul>
					</li>
					
					
					
					<li class="">
					<!--  
						<a href="<%=request.getContextPath() %>/admin/console/users" target="mainFrame">
				-->
	
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-desktop"></i>
							<span class="menu-text"> 调度台管理 </span>

							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
							<li class="">
								<a href="javascript:" onclick="showPage('/admin/console/level1users');" target="mainFrame">
									<i class="menu-icon fa fa-caret-right"></i>
									一阶管理台
								</a>

								<b class="arrow"></b>
							</li>
							
							<li class="">
								<a href="javascript:" onclick="showPage('/admin/console/level2users');" target="mainFrame">
									<i class="menu-icon fa fa-caret-right"></i>
									二阶管理台
								</a>

								<b class="arrow"></b>
							</li>

						</ul>
					</li>
					
<%-- 
					<li class="">
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa glyphicon-user fa-book"></i>
							<span class="menu-text">
								日志管理
							</span>

							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
							<li class="">
								<a href="<%=request.getContextPath() %>/admin/log/system/list" target="mainFrame">
									<i class="menu-icon fa fa-caret-right"></i>
										系统日志
									<b class="arrow fa fa-angle-down"></b>
								</a>
							</li>
						</ul>
					</li>
	--%>				
					
				</ul><!-- /.nav-list -->

				<!-- #section:basics/sidebar.layout.minimize -->
				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>

				<!-- /section:basics/sidebar.layout.minimize -->
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
				</script>
			</div>