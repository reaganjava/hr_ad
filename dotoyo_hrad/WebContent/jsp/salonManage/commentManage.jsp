<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.dotoyo.buildjob.common.constant.ApplicationConstant"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头-评论管理</title>
	<s:include value="/jsp/common.jsp"/>
</head>
<s:include value="/jsp/top.jsp"/>
<!--以上为头部分-->
<!--中间部分-->
<div class="content">
<s:include value="/jsp/left.jsp"/>
    <div class="content-right">
    	<div class="administrator-title"><strong>评论管理</strong></div>
	   <s:form id="searchForm" name="searchForm" method="POST" action="salonAdminAction!searchComment.action">
   	    <div class="inside-search2">
        	<ul>
            	<li>评论内容： <s:textfield id="salonSearchVo.content" size="16" name="salonSearchVo.content" /></li>
                <li>发表时间：<input id="strStartDate" name="strStartDate" value="<s:property value="strStartDate"/>" size="12" class="Wdate2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',errDealMode:1,maxDate:'#F{$dp.$D(\'strEndDate\')}'})"/>&nbsp;&nbsp;
                             <input id="strEndDate" name="strEndDate" value="<s:property value="strEndDate"/>" size="12" class="Wdate2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'strStartDate\')}'})"/></li>
                <li>状态：<s:select name="salonSearchVo.status" list="#{null:'所有','1':'通过','0':'待审核','2':'不通过' }" listKey="key" listValue="value"/></li>
                <li><a href="#" onclick="javascript:submitForm(searchForm)"><img src="<%=request.getContextPath()%>/images/inside-botton2.png" width="68" height="25" /></a></li>
            </ul>
        </div>
       </s:form>
        <div class="inside-tab">
        <s:form id="commentForm" name="commentForm" method="POST" action="salonAdminAction!deleteComment.action">
   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="2%"><input id="selected" name="selected" type="checkbox" value=""/></td>
                <td width="15%" align="left"><strong>沙龙名称</strong></td>
                <td width="10%" align="left"><strong>评论类型</strong></td>
                <td width="15%" height="40" align="left"><strong>评论类型标题</strong></td>
                <td width="20%" height="40" align="left"><strong>发表者</strong></td>
                <td width="20%" align="left"><strong>评论内容</strong></td>
                <td width="12%" align="left"><strong>发表时间</strong></td>
                <td width="6%" align="left"><strong>状态</strong></td>
              </tr>
              <s:iterator value="commentList">
              <tr>
                <td width="2%"><input id="arrayIds" name="arrayIds" type="checkbox" value="<s:property value="id"/>"/></td>
                <td width="15%" align="left" title="<s:property value="salonName"/>"><span  class="index_hid_css" style="width:115px;float:left"><s:property value="salonName"/></span></td>
                <td width="10%" align="left"><s:property value="subjectType"/></td>
                <td width="15%" height="40" align="left" title="<s:property value="subjectName"/>"><span  class="index_hid_css" style="width:100px;float:left"><s:property value="subjectName"/></span></td>
                <td width="20%" height="40" align="left" title="<s:property value="userName"/>"><span  class="index_hid_css" style="width:140px;float:left"><s:property value="userName"/></span></td>
                <td width="20%" align="left" title="<s:property value="content"/>"><span  class="index_hid_css" style="width:140px;float:left"><s:property value="content"/></span></td>
                <td width="12%" align="left"><s:property value="getText('formatDate_yyyy-MM-dd',{submitDate})"/></td>
                <td width="6%" align="left"><s:property value="status"/></td>

              </tr>
              </s:iterator>
            </table>
            </s:form>
		</div>

	  <s:form  id="pageFrom" name="pageFrom" method="POST" action="salonAdminAction!searchComment.action">
	    <s:hidden name="salonSearchVo.subject"/>
        <s:hidden name="strStartDate"/>
        <s:hidden name="strEndDate"/>
        <s:hidden name="salonSearchVo.status"/>
        <div class="qx">
           <s:property value="pageInfo.htmlListTableFootInfo" escapeHtml="false"/>
       </div>
         <div class="system-botton5">
           <ul>
             <li class="botton1"><a href="#" onclick="submitFormChooseCheck(commentForm,'arrayIds')"  title="删除">删除</a></li>
             <li class="botton2"><a  href="#" onclick="submitForm4ChangeAction(commentForm,'arrayIds','salonAdminAction!autiComment.action?audiFlag=<%=ApplicationConstant.STATUS_PASSED%>')"  title="审核通过">审核通过</a></li>
             <li class="botton3"><a  href="#" onclick="submitForm4ChangeAction(commentForm,'arrayIds','salonAdminAction!autiComment.action?audiFlag=<%=ApplicationConstant.STATUS_NOT_PASS%>')"  title="审核不通过">审核不通过</a></li>
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
		$("#salon_comment").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
	}
</script>
</body>
</html>
