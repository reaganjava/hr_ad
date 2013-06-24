<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.dotoyo.buildjob.common.constant.ApplicationConstant"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头-线下沙龙图片</title>
	<s:include value="/jsp/common.jsp"/>
</head>
<s:include value="/jsp/top.jsp"/>
<!--以上为头部分-->
<!--中间部分-->
<div class="content">
<s:include value="/jsp/left.jsp"/>
 <div class="content-right">
    	<div class="administrator-title"><strong>沙龙图片</strong></div>
    	<div align="left"><s:actionmessage cssStyle="color:blue"/></div>
       <s:form id="deleteForm" name="deleteForm" method="POST" action="salonAdminAction!deleteMedia.action">
       <s:hidden name="salonMediaDto.mediaType" id="salonMediaDto.mediaType"/>
  	   <s:hidden name="salonMediaDto.salonId" id="salonMediaDto.salonId"/>
  	   <s:hidden name="salonMediaDto.status" id="salonMediaDto.status"/>
        <div class="geren-video">
   	    	 <ul>
   	    	 <s:iterator value="mediaList">
                 <li title="<s:property value="subject"/>"><img src="<s:property value="uploadFilePath"/>/<s:property value="MediaName"/>" width="120" height="90" /><br />
                  <span class="index_hid_css" style="width:110px;float:left"><input id="arrayIds" name="arrayIds" type="checkbox" value="<s:property value="id"/>"/><s:property value="subject"/></span><p>状态:<s:property value="status"/></p><p>是否经典:<s:property value="isExcellent"/></p>
                 </li>
             </s:iterator>
             </ul>
		</div>
	</s:form>
	<s:form  id="pageFrom" name="pageFrom" method="POST" action="salonAdminAction!toMediaListBySalonPage.action">
  	 <s:hidden name="salonMediaDto.mediaType" id="salonMediaDto.mediaType"/>
  	 <s:hidden name="salonMediaDto.salonId" id="salonMediaDto.salonId"/>
  	 <s:hidden name="salonMediaDto.status" id="salonMediaDto.status"/>
       <div class="qx">
           <s:property value="pageInfo.htmlListTableFootInfo" escapeHtml="false"/>
       </div>
       	<div class="system-botton2">
        	<ul>
                <li class="botton1"><a href="#" onclick="submitFormChooseCheck(deleteForm,'arrayIds')" title="删除">删除</a></li>
                <li class="botton2"><a href="#" onclick="submitForm4ChangeAction(deleteForm,'arrayIds','salonAdminAction!autiMedia.action?audiFlag=<%=ApplicationConstant.STATUS_PASSED%>&status=<s:property value="salonMediaDto.status"/>')" title="审核通过">审核通过</a></li>
                <li class="botton3"><a href="#" onclick="submitForm4ChangeAction(deleteForm,'arrayIds','salonAdminAction!autiMedia.action?audiFlag=<%=ApplicationConstant.STATUS_NOT_PASS%>&status=<s:property value="salonMediaDto.status"/>')" title="审核不通过">审核不通过</a></li>
                <li class="botton3"><a href="#" onclick="submitForm4ChangeAction(deleteForm,'arrayIds','salonAdminAction!setMedia.action')" title="设置为经典">设置为经典</a></li>
                <li class="botton1"><a href="salonAdminAction!toMediaListPage.action?salonMediaDto.mediaType=<%=ApplicationConstant.MEDIA_TYPE_PICTURE%>" title="返回">返回</a></li>
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
		$("#salon_picture").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
	}
</script>
</html>