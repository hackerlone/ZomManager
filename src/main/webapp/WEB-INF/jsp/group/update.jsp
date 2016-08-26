<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/common_css.jsp"%>
</head>
<body class="no-skin">
<input type="hidden" value="${groupDto.id}" id="groupId"/>
	<div class="main-content-inner">
		<!-- #section:basics/content.breadcrumbs -->
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
			</script>

		<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="javascript:" onclick="lh.mainPageReload();">首页</a></li>
					<li><a href="javascript:" onclick="lh.cmsJump('/admin/group/groups');">组信息列表</a></li>
				<li class="active">修改组信息</li>
			</ul><!-- /.breadcrumb -->	
		</div>
		
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<sf:form method="post" modelAttribute="groupDto" id="addForm" cssClass="form-horizontal" role="form">
					<!-- #section:elements.form -->
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 组名称: </label>
						<div class="col-sm-9">
							<sf:input path="groupName" id="groupName" size="30" cssClass="col-xs-10 col-sm-5"/>
							<sf:errors cssClass="errorContainer" path="groupName"/>
						</div>
					</div>
					<div class="space-4"></div>
			
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">组状态: </label>
						<div class="col-sm-9">
							<sf:select path="status" id="status" tbindex="1">
								<sf:option value="0">停用</sf:option>
								<sf:option value="1">启用</sf:option>
							</sf:select>
						</div>
					</div>
					
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 选择组成员: </label>
						<div class="col-sm-6" style="z-index: 200;">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="row">
									<div class="col-xs-12">
										<button type="button" id="checkAllBtn" onclick="addAllGroupUser(${groupDto.id});">批量加入</button>
										<button type="button" id="cleanAllBtn" onclick="delAllGroupUser(${groupDto.id});">批量退出</button>
										<table id="datagrid" class="table table-striped table-bordered table-hover black">
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
				
					<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="button" onclick="updateMainObj(${groupDto.id});">
									<i class="ace-icon fa fa-check bigger-110"></i> 保存
								</button>
								<button class="btn" type="reset" style="margin-left:40px;" onclick="lh.back();">
									<i class="ace-icon fa fa-undo bigger-110"></i> 返回
								</button>
							</div>
					</div>
				</sf:form>
				</div>
			</div><!-- /.row -->
		</div>
	</div>
<%@ include file="../common/common_js.jsp"%>
<script src="<%=request.getContextPath()%>/resources/lh/js/group/update.js" title="v"></script>
</script>

</body>
</html>