<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.dotoyo.buildjob.common.constant.Constants"%>
<%@ page
	import="com.dotoyo.buildjob.common.constant.ApplicationConstant"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头-沙龙审核</title>
<s:include value="/jsp/common.jsp" />
	<%
	Object obj = request.getSession().getAttribute(Constants.SESSION_USER);
	String rootPath = String.valueOf(application.getAttribute(ApplicationConstant.FRONT_HTTP_PATH));
%>
</head>
<s:include value="/jsp/top.jsp" />
<!--以上为头部分-->
<!--中间部分-->
<div class="content">
<s:include value="/jsp/left.jsp"/>
 <div class="content-right">
      	<s:form id="searchForm" name="searchForm" method="POST" action="salonAdminAction!searchSalon.action">
      	<s:hidden id="goFrom" name="goFrom" value="0"/>
    	<div class="administrator-title"><strong>沙龙审核</strong></div>
        <div class="inside-search">
        	<ul>
            	<li>名称:&nbsp;<s:textfield id="salonSearchVo.subject" name="salonSearchVo.subject" size="15"/></li>
                <li>起止日期:&nbsp;<input id="strStartDate" name="strStartDate" value="<s:property value="strStartDate"/>" size="12" class="Wdate2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',errDealMode:1,maxDate:'#F{$dp.$D(\'strEndDate\')}'})"/></li>
                <li><input id="strEndDate" name="strEndDate" value="<s:property value="strEndDate"/>" size="12" class="Wdate2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'strStartDate\')}'})"/></li>
                <li>状态:&nbsp;<s:select name="salonSearchVo.status" list="#{null:'所有','1':'通过','0':'待审核','2':'不通过' }" listKey="key" listValue="value"/></li>
                <li><a href="#" onclick="javascript:submitForm(searchForm)"><img src="<%=request.getContextPath()%>/images/results-list-botton.png" width="101" height="25" /></a></li>
            </ul>
        </div>
       </s:form>
<div class="inside-tab"><s:form id="deleteForm" name="deleteForm"
	method="POST" action="salonAdminAction!deleteSalon.action">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="5%"><input id="selected" name="selected"
				type="checkbox" value="" /></td>
			<td width="10%" align="left"><strong>沙龙名称</strong></td>
			<td width="14%" height="40" align="left"><strong>沙龙类型</strong></td>
			<td width="6%" align="left"><strong>组织者</strong></td>
			<td width="6%" align="left"><strong>主办方</strong></td>
			<td width="10%" align="left"><strong>状态</strong></td>
			<td width="12%" align="left"><strong>开始日期</strong></td>
			<td width="12%" align="left"><strong>截止日期</strong></td>
			<td width="10%" align="left"><strong>沙龙地点</strong></td>
			<td width="10%" align="left"><strong>参与人数</strong></td>
		</tr>
		<s:iterator value="salonList">
			<tr>
				<td width="5%"><input id="arrayIds" name="arrayIds"
					type="checkbox" value="<s:property value="id"/>" /></td>
				<td width="10%" align="left" title="<s:property value="subject"/>"><span
					style="width: 100px; float: left" class="index_hid_css">
					<s:if test="salonType==1">
					<a href="<%=rootPath%>/innovationSalon/salonAction!toSalonDetail.action?salonInfoDto.id=<s:property value="id"/>" target="_blank"><s:property value="subject" /></a>
					</s:if>
					<s:if test="salonType==0">
					<a href="<%=rootPath%>/innovationSalon/salonAction!toOffLineSalonDetail.action?salonInfoDto.id=<s:property value="id"/>" target="_blank"><s:property value="subject" /></a>
					</s:if>
					&nbsp;</span></td>
				<td width="14%" height="40" align="left"><s:property
					value="salonTypeText" />&nbsp;</td>
				<td width="6%" align="left" title="<s:property value="userName"/>"><span
					style="width: 100px; float: left" class="index_hid_css"><s:property
					value="userName" /></span>&nbsp;</td>
				<td width="6%" align="left" title="<s:property value="sponsor"/>"><span
					style="width: 100px; float: left" class="index_hid_css"><s:property
					value="sponsor" /></span>&nbsp;</td>
				<td width="8%" align="left"><s:property value="audiFlag" />&nbsp;</td>
				<td width="12%" align="left"><s:property
					value="getText('formatDate_yyyy-MM-dd',{startDate})" />&nbsp;</td>
				<td width="12%" align="left"><s:property
					value="getText('formatDate_yyyy-MM-dd',{endDate})" />&nbsp;</td>
				<td width="10%" align="left" class="inside-links"
					title="<s:property value="location"/>"><span
					style="width: 150px; float: left" class="index_hid_css"><s:property
					value="location" /></span></td>
				<td width="10%" align="left"><s:property
					value="salonAttendCount" />&nbsp;</td>
			</tr>
		</s:iterator>
	</table>
</s:form></div>
<s:form id="pageFrom" name="pageFrom" method="POST"
	action="salonAdminAction!searchSalon.action">
	<s:hidden name="salonSearchVo.subject" />
	<s:hidden name="strStartDate" />
	<s:hidden name="strEndDate" />
	<s:hidden name="salonSearchVo.status" />
	<div class="qx"><s:property
		value="pageInfo.htmlListTableFootInfo" escapeHtml="false" /></div>
	<div class="system-botton2">
	<ul>
		<li class="botton1"><a href="#"
			onclick="submitForm4ChangeAction(deleteForm,'arrayIds','salonAdminAction!autiSalon.action?audiFlag=<%=ApplicationConstant.STATUS_PASSED%>')"
			title="审核通过">审核通过</a></li>
		<li class="botton2"><a href="#"
			onclick="submitForm4ChangeAction(deleteForm,'arrayIds','salonAdminAction!autiSalon.action?audiFlag=<%=ApplicationConstant.STATUS_NOT_PASS%>')"
			title="审核不通过">审核不通过</a></li>
		<li class="botton3"><a
			href="salonAdminAction!toSetConfirmSalonHomePage.action" title="沙龙首页显示设置">沙龙首页显示设置</a></li>
		<li class="botton3"><a href="#"
			onclick="submitFormChooseCheck(deleteForm,'arrayIds')" title="删除">删除</a></li>
	</ul>
	</div>
	<div class="num"><s:property value="pageInfo.htmlPagingInfo"
		escapeHtml="false" /></div>

</s:form></div>
</div>
<script type="text/javascript">
	function init(){
		$("#salon_manager").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#salon_manager_1 a").addClass("active");
	}
</script>
</body>
</html>