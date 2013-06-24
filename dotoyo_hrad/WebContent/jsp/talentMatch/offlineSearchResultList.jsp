<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头--系统管理--企业线下人才需求信息列表</title>
	<s:include value="/jsp/common.jsp"/>
	<%String path = request.getContextPath(); %>
</head>
	<s:include value="/jsp/top.jsp"/>
	<!--以上为头部分-->
	<!--中间部分-->
	<div class="content">
		<s:include value="/jsp/left.jsp"/>
		<div class="content-right">
			<div class="administrator-title"><strong>企业线下人才需求信息</strong></div>
			<s:form id="searchForm" name="searchForm" method="post" action="talentMatchAction!viewOfflineSearchResultList.action">
				<div class="inside-search">
					<ul>
						<li>公司名称：
							<s:textfield name="offlineSearchResultDto.userRealName" size="17"></s:textfield>
						</li>
						<li>职位名称：
							<s:textfield name="offlineSearchResultDto.jobKeyWord" size="17"></s:textfield>
						</li>
						<li>
							<a href="javascript:document.searchForm.submit()">
								<img src="<%=path %>/images/inside-botton.png"width="68" height="25" />
							</a>
						</li>
					</ul>
				</div>
			</s:form>
			<div class="inside-tab">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="4%" align="center">&nbsp;</td>
						<td width="15%" height="40" align="left"><strong>公司名称</strong></td>
						<td width="21%" align="left"><strong>工作地区</strong></td>
						<td width="11%" align="left"><strong>工作性质</strong></td>
						<td width="9%" align="left"><strong>是否顾问</strong></td>
						<td width="26%" align="left"><strong>职位名称</strong></td>
						<td width="14%" align="left"><strong>操作</strong></td>
					</tr>
					<s:iterator value="offlineSearchResultList">
						<tr>
							<td width="4%" align="center">&nbsp;</td>
							<td width="15%" height="40" align="left"><s:property value="userRealName"/></td>
							<td width="21%" align="left"><s:property value="cityName"/><s:property value="areaName"/></td>
							<td width="11%" align="left"><s:property value="jobNatureName"/></td>
							<td width="9%" align="left">
								<s:if test='isAdviser=="0"'>
									否
								</s:if>
								<s:elseif test='isAdviser=="1"'>
									是
								</s:elseif>
							</td>
							<td width="26%" align="left"><s:property value="jobKeyWord"/></td>
							<td width="14%" align="left" class="inside-links">
								<a href="talentMatchAction!matchTalent4Enterprise.action?offlineSearchResultDto.id=<s:property value='id' />">人才匹配</a>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<s:form id="pageFrom" name="pageFrom" method="post" action="talentMatchAction!viewOfflineSearchResultList.action">
				<s:hidden name="offlineSearchResultDto.userRealName"></s:hidden>
				<s:hidden name="offlineSearchResultDto.jobKeyWord"></s:hidden>
				<div class="qx">
					<s:property value="pageInfo.htmlListTableFootInfoNoAllCheck" escapeHtml="false"/>
				</div>
				<div class="num">
					<s:property value="pageInfo.htmlPagingInfo" escapeHtml="false"/>
				</div>
			</s:form>
		</div>
	</div>
</body>
<script type="text/javascript">

	function init(){
		$("#sys_lineofftalentsmath_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_enterpriselinefoffapply a").addClass("active");
	}
</script>
</html>
