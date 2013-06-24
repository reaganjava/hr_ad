<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头--系统管理--公司匹配线下人才的人才匹配结果</title>
	<s:include value="/jsp/common.jsp"/>
	<%String path = request.getContextPath(); %>
</head>
	<s:include value="/jsp/top.jsp"/>
	<!--以上为头部分-->
	<!--中间部分-->
	<div class="content">
		<s:include value="/jsp/left.jsp"/>
		<div class="content-right">
			<div class="administrator-title"><strong>匹配的条件</strong></div>
			<div class="inside-tab">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="18%" height="40" align="right"><strong>工作地区</strong>：</td>
						<td width="22%" align="left" class="tab-input">
							<s:property value="offlineSearchResultDto.cityName" /><s:property value="offlineSearchResultDto.areaName" />
						</td>
						<td width="9%">&nbsp;</td>
						<td width="12%" align="right"><strong>工作性质</strong>：</td>
						<td width="39%" align="left"><s:property value="offlineSearchResultDto.jobNatureName" /></td>
					</tr>
					<tr>
						<td height="40" align="right"><strong>是否顾问</strong>：</td>
						<td align="left" class="tab-input">
							<s:if test='offlineSearchResultDto.isAdviser=="1"'>
								是
							</s:if>
							<s:elseif test='offlineSearchResultDto.isAdviser=="0"'>
								否
							</s:elseif>
						</td>
						<td>&nbsp;</td>
						<td align="right"><strong>意向职位</strong>：</td>
						<td align="left"><s:property value="offlineSearchResultDto.jobKeyWord" /></td>
					</tr>
				</table>
			</div>
			<div class="administrator-title2"><strong>匹配结果</strong></div>
			<div class="inside-tab">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="4%" align="center">&nbsp;</td>
						<td width="18%" height="40" align="left"><strong>工作地区</strong></td>
						<td width="10%" align="left"><strong>工作性质</strong></td>
						<td width="9%" align="left"><strong>是否顾问</strong></td>
						<td width="29%" align="left"><strong>申请人</strong></td>
						<td width="20%" align="left"><strong>职位</strong></td>
						<td width="10%" align="left"><strong>更新日期</strong></td>
					</tr>
					<s:iterator value="excavateResultList">
						<tr>
							<td width="4%" align="center">&nbsp;</td>
							<td width="18%" height="40" align="left"><s:property value="cityCode" /><s:property value="areaCode" /></td>
							<td width="10%" align="left"><s:property value="jobNature" /></td>
							<td width="9%" align="left">
								<s:if test="isAdviser==1">
									是
								</s:if>
								<s:else>
									否
								</s:else>
							</td>
							<td width="29%" align="left"><s:property value="companyName" /></td>
							<td width="20%" align="left"><s:property value="jobName" /></td>
							<td width="10%" align="left"><s:date name="userInfoUpdateDate" format="yyyy-MM-dd"/></td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<s:form id="hiddenForm" name="hiddenForm" target="_blank" method="post" action="talentMatchAction!printTalent4Enterprise.action">
				<s:hidden name="offlineSearchResultDto.id"></s:hidden>
			</s:form>
			<s:form  id="pageFrom" name="pageFrom" method="POST" action="talentMatchAction!matchTalent4Enterprise.action">
				<s:hidden name="offlineSearchResultDto.id"></s:hidden>
				<div class="qx">
					<s:property value="pageInfo.htmlListTableFootInfoNoAllCheck" escapeHtml="false"/>
					<input id="SSS" name="" type="checkbox" value="" />
					<label for="SSS">&nbsp;&nbsp;全选</label>
				</div>
				<div class="system-botton3">
					<ul>
						<li class="botton1">
							<a href="javascript:document.hiddenForm.submit()" title="打印">打印</a>
						</li>
						<li class="botton3"><a href="javascript:goBack()" title="返回">返回</a></li>
					</ul>
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
