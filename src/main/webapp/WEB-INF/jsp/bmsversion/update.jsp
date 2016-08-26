<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="javascript:" onclick="lh.mainPageReload();">首页</a></li>
				<li><a href="javascript:" onclick="lh.cmsJump('/admin/version/list');">客户端版本列表</a></li>

				<li class="active">修改版本信息</li>


			</ul>
			<!-- /.breadcrumb -->
		</div>

		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<sf:form method="post" modelAttribute="bmsVer" id="addForm" cssClass="form-horizontal" role="form">
						<!-- #section:elements.form -->
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 服务器版本: </label>
							<div class="col-sm-9">
								<sf:input id="serverVersion" path="serverVersion" size="30" cssClass="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 客户端操作系统: </label>
							<div class="col-sm-9">
								<sf:select path="clientOsType" id="clientOsType" tbindex="1">
									<sf:option value="android">android</sf:option>
									<sf:option value="ios">ios</sf:option>
									<sf:option value="windows">windows</sf:option>
									<sf:option value="console">console</sf:option>
								</sf:select>
							</div>
						</div>



						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 客户端版本: </label>
							<div class="col-sm-9">
								<sf:input path="clientVersion" id="clientVersion" size="30" cssClass="col-xs-10 col-sm-5" />


							</div>

						</div>

						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 客户端程序链接: </label>
							<div class="col-sm-9">
								<sf:input path="clientUrl" id="clientUrl" size="30" cssClass="col-xs-10 col-sm-5" />


							</div>

						</div>

						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 是否兼容老版本: </label>
							<div class="col-sm-9">
								<sf:select path="latestServer" id="latestServer" tbindex="1">
									<sf:option value="1">yes</sf:option>
									<sf:option value="0">no</sf:option>

								</sf:select>
							</div>

						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 最新客户端版本: </label>
							<div class="col-sm-9">
								<sf:select path="latestClient" id="newClient" tbindex="1">
									<sf:option value="1">yes</sf:option>
									<sf:option value="0">no</sf:option>

								</sf:select>
							</div>

						</div>

						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 版本描述: </label>
							<div class="col-sm-9">
								<sf:input path="clientDescription" id="clientDescription" size="30" cssClass="col-xs-10 col-sm-5" />


							</div>

						</div>


						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="button" onclick="updateMainObj(${bmsVer.id});">
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
	<script src="<%=request.getContextPath()%>/resources/lh/js/bmsVer/update.js" title="v"></script>
</body>
</html>