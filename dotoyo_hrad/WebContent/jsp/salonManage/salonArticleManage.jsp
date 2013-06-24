<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.dotoyo.buildjob.common.constant.Constants"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.dotoyo.buildjob.common.constant.ApplicationConstant"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头-沙龙文章管理</title>
	<s:include value="/jsp/common.jsp"/>
	<%
	Object obj = request.getSession().getAttribute(Constants.SESSION_USER);
	String rootPath = String.valueOf(application.getAttribute(ApplicationConstant.FRONT_HTTP_PATH));
%>
</head>
<s:include value="/jsp/top.jsp"/>
<!--以上为头部分-->
<!--中间部分-->
<div class="content">
<s:include value="/jsp/left.jsp"/>
    <div class="content-right">
    	<div class="administrator-title"><strong>沙龙文章管理</strong></div>
    	<div align="left"><s:actionmessage cssStyle="color:blue"/></div>
	   <s:form id="searchForm" name="searchForm" method="POST" action="salonAdminAction!searchArticle.action">
	    <s:hidden id="goFrom" name="goFrom" value="1"/>
	    <s:hidden name="isNew"></s:hidden>
   	    <div class="inside-search">
        	<ul>
            	<li>标题:&nbsp;<s:textfield id="salonSearchVo.subject" name="salonSearchVo.subject" /></li>
                <li>上传时间:&nbsp;<input id="strStartDate" name="strStartDate" value="<s:property value="strStartDate"/>" size="12" class="Wdate2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',errDealMode:1,maxDate:'#F{$dp.$D(\'strEndDate\')}'})"/>&nbsp;&nbsp;
                             <input id="strEndDate" name="strEndDate" value="<s:property value="strEndDate"/>" size="12" class="Wdate2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'strStartDate\')}'})"/></li>
                <li>状态:&nbsp;
                	<s:select id="statusHid" name="salonSearchVo.status" list="#{null:'所有','1':'通过','0':'待审核','2':'不通过' }" listKey="key" listValue="value" cssStyle="display:none"/>
                	<s:select id="status" value="salonSearchVo.status" list="#{null:'所有','1':'通过','0':'待审核','2':'不通过' }" listKey="key" listValue="value"/>
                </li>
                <li><a href="#" onclick="
                document.getElementById('statusHid').value=document.getElementById('status').value;
                ;$('#searchForm').submit();"><img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" /></a></li>
            </ul>
        </div>
       </s:form>
        <div class="inside-tab">
        <s:form id="articleForm" name="articleForm" method="POST" action="salonAdminAction!deleteSalonArticle.action">
        <s:hidden name="isNew"></s:hidden>
   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="5%"><input id="selected" name="selected" type="checkbox" value=""/></td>
                <td width="18%" height="40" align="left"><strong>标题</strong></td>
                <td width="15%" align="left"><strong>作者</strong></td>
                <td width="12%" align="left"><strong>沙龙名称</strong></td>
                <td width="10%" align="left"><strong>上传时间</strong></td>
                <td width="6%" align="left"><strong>状态</strong></td>
                <td width="6%" align="left"><strong>经典</strong></td>
                <td width="6%" align="left"><strong>推荐</strong></td>
                <td width="6%" align="left"><strong>精华</strong></td>
                <td width="6%" align="left"><strong>浏览</strong></td>
              </tr>
              <s:iterator value="articleList">
              <tr>
                <td width="5%"><input id="arrayIds" name="arrayIds" type="checkbox" value="<s:property value="id"/>"/></td>
                <td width="18%" height="40" align="left" title="<s:property value="subject"/>"><span style="width:140px;float:left" class="index_hid_css"><a href="<%=rootPath%>/innovationSalon/salonAction!toArticleDetail.action?salonArticleDto.id=<s:property value="id"/>" target="_blank"><s:property value="subject"/></a></span></td>
                <td width="15%" align="left" title="<s:property value="userName"/>"><span style="width:100px;float:left" class="index_hid_css"><s:property value="userName"/></span></td>
                <td width="12%" align="left" title="<s:property value="salonSubject"/>"><span style="width:100px;float:left" class="index_hid_css"><s:property value="salonSubject"/></span></td>
                <td width="10%" align="left"><s:property value="getText('formatDate_yyyy-MM-dd',{submitDate})"/></td>
                <td width="6%" align="left"><s:property value="articleStatus"/></td>
                <td width="6%" align="left"><s:property value="isExcellent"/></td>
                <td width="6%" align="left"><s:property value="isRecomment"/></td>
                <td width="6%" align="left"><s:property value="iscream"/></td>
                <td width="6%" align="left"><s:property value="browseCount"/></td>
              </tr>
              </s:iterator>
            </table>
            </s:form>
		</div>

	  <s:form  id="pageFrom" name="pageFrom" method="POST" action="salonAdminAction!searchArticle.action">
	  	<s:hidden name="isNew"></s:hidden>
	    <s:hidden id="goFrom" name="goFrom" value="1"/>
	    <s:hidden name="salonSearchVo.subject"/>
        <s:hidden name="strStartDate"/>
        <s:hidden name="strEndDate"/>
        <s:hidden name="salonSearchVo.status"/>
        <div class="qx">
           <s:property value="pageInfo.htmlListTableFootInfo" escapeHtml="false"/>
       </div>
          <div class="system-botton2">
           <ul>
             <li class="botton1"><a href="#" onclick="submitFormChooseCheck(articleForm,'arrayIds')"  title="删除">删除</a></li>
             <li class="botton2"><a  href="#" onclick="submitForm4ChangeAction(articleForm,'arrayIds','salonAdminAction!autiSalonArticle.action?audiFlag=<%=ApplicationConstant.STATUS_PASSED%>')"  title="审核通过">审核通过</a></li>
             <li class="botton3"><a  href="#" onclick="submitForm4ChangeAction(articleForm,'arrayIds','salonAdminAction!autiSalonArticle.action?audiFlag=<%=ApplicationConstant.STATUS_NOT_PASS%>')"  title="审核不通过">审核不通过</a></li>
             <li class="botton3"><a href="#" onclick="submitForm4ChangeAction(articleForm,'arrayIds','salonAdminAction!setRecommentSalonArticle.action')"  title="推荐">推荐</a></li>
             <li class="botton1"><a href="#" onclick="submitForm4ChangeAction(articleForm,'arrayIds','salonAdminAction!setCreamSalonArticle.action')"  title="精华">精华</a></li>
             <li class="botton2"><a href="#" onclick="submitForm4ChangeAction(articleForm,'arrayIds','salonAdminAction!setClassicSalonArticle.action')"  title="经典">经典</a></li>
             </ul>
       	</div>
        <div class="num">
        		<s:property value="pageInfo.htmlPagingInfo" escapeHtml="false"/>
       	</div>
       	</s:form>
  </div>
</div>
<script type="text/javascript">
	function init(){
		$("#salon_article").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
	}
</script>
</body>
</html>
