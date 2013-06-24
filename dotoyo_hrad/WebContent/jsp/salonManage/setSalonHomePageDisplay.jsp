<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.dotoyo.buildjob.common.constant.ApplicationConstant"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头-沙龙首页显示设置</title>
	<s:include value="/jsp/common.jsp"/>
</head>
<s:include value="/jsp/top.jsp"/>
<!--以上为头部分-->
<!--中间部分-->
<div class="content">
<s:include value="/jsp/left.jsp"/>
 <div class="content-right">
      	<s:form id="searchForm" name="searchForm" method="POST" action="salonAdminAction!searchSalon.action">
      	 <s:hidden id="goFrom" name="goFrom" value="1"/>
         <s:hidden id="salonSearchVo.status" name="salonSearchVo.status"/>
    	<div class="administrator-title"><strong>沙龙首页显示设置</strong></div>
        <div class="inside-search">
        	<ul>
            	<li>名称： <s:textfield id="salonSearchVo.subject" name="salonSearchVo.subject" size="20"/></li>
                <li>起止日期：<input id="strStartDate" name="strStartDate" value="<s:property value="strStartDate"/>" size="12" class="Wdate2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',errDealMode:1,maxDate:'#F{$dp.$D(\'strEndDate\')}'})"/></li>
                <li><input id="strEndDate" name="strEndDate" value="<s:property value="strEndDate"/>" size="12" class="Wdate2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'strStartDate\')}'})"/></li>
                <li><a href="#" onclick="javascript:submitForm(searchForm)"><img src="<%=request.getContextPath()%>/images/results-list-botton.png" width="101" height="25" /></a></li>
            </ul>
        </div>
       </s:form>

        <div class="inside-tab">
        <s:form id="confirmForm" name="confirmForm" method="POST" action="salonAdminAction!toSetConfirmSalonHomePage.action">
   	    <table width="100%" border="0" cellspacing="0" cellpadding="0" >
              <tr>
                <td width="5%"><input id="selected" name="selected" type="checkbox" value=""/></td>
                <td width="13%" align="left" nowrap="nowrap"><strong>沙龙名称</strong>&nbsp;</td>
                <td width="12%" height="40" align="left" nowrap="nowrap"><strong>沙龙类型</strong>&nbsp;</td>
                <td width="6%" align="left" nowrap="nowrap"><strong>组织者</strong>&nbsp;</td>
                <td width="6%" align="left" nowrap="nowrap"><strong>状态</strong>&nbsp;</td>
                <td width="10%" align="left" nowrap="nowrap"><strong>开始日期</strong>&nbsp;</td>
                <td width="10%"  align="left" nowrap="nowrap"><strong>截止日期</strong>&nbsp;</td>
                <td width="15%" align="left" nowrap="nowrap"><strong>地点</strong>&nbsp;</td>
              </tr>
             <s:iterator value="salonList">
              <tr>
                <td width="5%"><input id="arrayIds" name="arrayIds" type="checkbox" value="<s:property value="id"/>"/></td>
                <td width="13%" align="left" title="<s:property value="subject"/>"><span style="width:100px;float:left" class="index_hid_css"><s:property value="subject"/></span>&nbsp;</td>
                <td width="12%" height="40" align="left"><s:property value="salonTypeText"/>&nbsp;</td>
                <td width="6%" align="center"><s:property value="userName"/>&nbsp;</td>
                <td width="6%" align="center"><s:property value="audiFlag"/>&nbsp;</td>
                <td width="10%" align="left"><s:property value="getText('formatDate_yyyy-MM-dd',{startDate})"/>&nbsp;</td>
                <td width="10%" align="left"><s:property value="getText('formatDate_yyyy-MM-dd',{endDate})"/>&nbsp;</td>
                <td width="15%" align="left" title="<s:property value="location"/>"><span style="width:120px;float:left" class="index_hid_css"><s:property value="location"/></span>&nbsp;</td>
              </tr>
            </s:iterator>
            </table>
          </s:form>
</div>
<s:form  id="pageFrom" name="pageFrom" method="POST" action="salonAdminAction!searchSalon.action">
    <s:hidden name="goFrom" value="1"/>
    <s:hidden name="salonSearchVo.subject"/>
    <s:hidden name="strStartDate"/>
    <s:hidden name="strEndDate"/>
    <s:hidden id="salonSearchVo.status" name="salonSearchVo.status"/>
    <div class="qx">
         <s:property value="pageInfo.htmlListTableFootInfo" escapeHtml="false"/>
     </div>
       	<div class="system-botton3">
        	<ul>
                <li class="botton1"><a href="#" onclick="submitFormChooseCheck(confirmForm,'arrayIds')" title="显示设置">显示设置</a></li>
                <li class="botton2"><a href="salonAdminAction!toAudiSalonPage.action" title="返回">返回</a></li>
            </ul>
		</div>
        <div class="num">
    	<s:property value="pageInfo.htmlPagingInfo" escapeHtml="false"/>
     </div>

</s:form>
  </div>


</div>

</body>
</html>
<script type="text/javascript">
	function init(){
		$("#salon_manager").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#salon_manager_1 a").addClass("active");
	}
</script>