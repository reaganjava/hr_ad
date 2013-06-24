<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.dotoyo.buildjob.common.constant.ApplicationConstant"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头-主办单位管理</title>
	<s:include value="/jsp/common.jsp"/>
</head>
<s:include value="/jsp/top.jsp"/>
<!--以上为头部分-->
<!--中间部分-->
<div class="content">
<s:include value="/jsp/left.jsp"/>
    <div class="content-right">
    	<div class="administrator-title"><strong>主办单位管理</strong></div>
        <div class="inside-tab">
        <s:form id="hostUnitForm" name="hostUnitForm" method="POST" action="salonAdminAction!deleteHostUnit.action">
   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="5%"><input id="selected" name="selected" type="checkbox" value=""/></td>
                <td width="15%" height="40" align="left" nowrap="nowrap"><strong>图片&nbsp;</strong></td>
                <td width="20%" align="left" nowrap="nowrap"><strong>单位名称&nbsp;</strong></td>
                <td width="20%" align="left" nowrap="nowrap"><strong>单位简介&nbsp;</strong></td>
                <td width="10%" align="left" nowrap="nowrap"><strong>顺序号&nbsp;</strong></td>
                <td width="20%" align="left" nowrap="nowrap"><strong>链接&nbsp;</strong></td>
                <td width="12%" align="left" nowrap="nowrap"><strong>操作</strong></td>
              </tr>
              <s:iterator value="hostUnitList">
              <tr>
                <td width="5%"><input id="arrayIds" name="arrayIds" type="checkbox" value="<s:property value="id"/>"/></td>
                <td width="15%" height="40" align="left"><img src="<s:property value="uploadFilePath"/>/<s:property value="pictureName"/>" width="120" height="90" />&nbsp;</td>
                <td width="20%" align="left"><s:property value="unitName"/></td>
                <td width="20%" align="left" title="<s:property value="introduction"/>"><span class="index_hid_css" style="width:150px;float:left"><s:property value="introduction"/></span></td>
                <td width="10%" align="center"><s:property value="intOrder"/></td>
                <td width="20%" align="left" title="<s:property value="linkURL"/>"><span class="index_hid_css" style="width:150px;float:left"><s:property value="linkURL"/></td>
                 <td width="12%" align="left" class="inside-links"><a href="salonAdminAction!toModifyHostUnitPage.action?hostUnitDto.id=<s:property value="id"/>">修改</a></td>
              </tr>
              </s:iterator>
            </table>
            </s:form>
		</div>

	  <s:form  id="pageFrom" name="pageFrom" method="POST" action="salonAdminAction!toHostUnitManagePage.action">
	    <s:hidden id="goFrom" name="goFrom" value="1"/>
        <div class="qx">
           <s:property value="pageInfo.htmlListTableFootInfo" escapeHtml="false"/>
       </div>
          <div class="system-botton3">
           <ul>
             <li class="botton1"><a href="#" onclick="submitFormChooseCheck(hostUnitForm,'arrayIds')"  title="删除">删除</a></li>
              <li class="botton3"><a href="salonAdminAction!toAddHostUnitPage.action" title="增加">增加</a></li>
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
		$("#salon_manager").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#salon_manager_4 a").addClass("active");
	}
</script>
</body>
</html>
