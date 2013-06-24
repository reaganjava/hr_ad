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
      	<s:form id="searchForm" name="searchForm" method="POST" action="salonAdminAction!toMediaListPage.action">
      	<s:hidden name="salonMediaDto.mediaType" id="salonMediaDto.mediaType"/>
    	<div class="administrator-title"><strong>沙龙图片</strong></div>
    	<div align="left"><s:actionmessage cssStyle="color:blue"/></div>
    	<div class="inside-search2">
        	<ul>
            	<li>图片上传审核设置：<s:select name="sysParameterDto.sysValue" list="#{'1':'需要审核','0':'不需要审核'}" listKey="key" listValue="value"/></li>
                <li><a href="#" onclick="genSubmitForm4ChangeAction(searchForm,'salonAdminAction!setMediaAudiFlag.action')"><img src="<%=request.getContextPath()%>/images/sz.png" width="68" height="25" /></a></li>
            </ul>
        </div>
        <div class="inside-search">
        	<ul>
            	<li>沙龙名称： <s:textfield id="salonSearchVo.subject" name="salonSearchVo.subject"/></li>
            	<li>状态： <s:select name="salonSearchVo.status" list="#{null:'所有','1':'通过','0':'待审核','2':'不通过' }" listKey="key" listValue="value"/></li>
                <li><a href="#" onclick="submitForm(searchForm)"><img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" /></a></li>
            </ul>
        </div>
        </s:form>
        <div class="geren-video">
          <s:form id="pictureForm" name="pictureForm" method="POST" action="salonAdminAction!deleteMediaBySalon.action">
            <s:hidden name="salonMediaDto.mediaType" id="salonMediaDto.mediaType"/>
   	    	 <ul>
   	    	 <s:iterator value="salonList" status="status">
                 <li title="<s:property value="subject"/>">
                  <a href="salonAdminAction!toMediaListBySalonPage.action?salonMediaDto.salonId=<s:property value="id"/>&salonMediaDto.mediaType=<%=ApplicationConstant.MEDIA_TYPE_PICTURE%>&salonMediaDto.status=<s:property value="salonSearchVo.status"/>">
                    <img src="<s:property value="uploadFilePath"/>/<s:property value="subjectPic"/>" width="120" height="90" />
                  </a>
                  <br/>
                  <input id="arrayIds" name="arrayIds" type="checkbox" value="<s:property value="id"/>"/>
                  <a href="salonAdminAction!toMediaListBySalonPage.action?salonMediaDto.salonId=<s:property value="id"/>&salonMediaDto.mediaType=<%=ApplicationConstant.MEDIA_TYPE_PICTURE%>&salonMediaDto.status=<s:property value="salonSearchVo.status"/>">
                    <span class="index_hid_css" style="width:110px;float:left"><s:property value="subject"/></span>
                    <p>( 图片个数:<s:property value="mediaCount"/>)</p>
                  </a>
                  </li>
             </s:iterator>
             </ul>
         </s:form>
		</div>

	<s:form  id="pageFrom" name="pageFrom" method="POST" action="salonAdminAction!toMediaListPage.action">
  	 <s:hidden name="salonMediaDto.mediaType" id="salonMediaDto.mediaType"/>
  	 <s:hidden name="salonSearchVo.subject"/>
     <s:hidden name="salonSearchVo.status"/>
       <div class="qx">
           <s:property value="pageInfo.htmlListTableFootInfo" escapeHtml="false"/>
       </div>

       	<div class="system-botton2">
        	<ul>
                <li class="botton1"><a href="#" onclick="submitForm4ChangeAction(pictureForm,'arrayIds','salonAdminAction!autiMediaBySalon.action?audiFlag=<%=ApplicationConstant.STATUS_PASSED%>')" title="审核通过">审核通过</a></li>
                <li class="botton2"><a href="#" onclick="submitForm4ChangeAction(pictureForm,'arrayIds','salonAdminAction!autiMediaBySalon.action?audiFlag=<%=ApplicationConstant.STATUS_NOT_PASS%>')" title="审核不通过">审核不通过</a></li>
                <li class="botton3"><a href="#" onclick="submitFormChooseCheck(pictureForm,'arrayIds')" title="删除">删除</a></li>
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
		$("#salon_picture").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
	}
</script>
</body>
</html>