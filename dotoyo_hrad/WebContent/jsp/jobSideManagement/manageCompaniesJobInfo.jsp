<%@page pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--系统管理--招聘方管理</title>
<%@ include file="/jsp/common.jsp"%>
<script src="<%=request.getContextPath()%>/javascripts/My97DatePicker/WdatePicker.js"></script>
</head>
<%@ include file="/jsp/top.jsp"%>

<!--中间部分-->
<div class="content">
	<jsp:include page="/jsp/left.jsp"></jsp:include>
    <div class="content-right">
    	<div class="administrator-title"><strong>招聘方管理</strong></div>
    	<s:form id="pageFrom" name="pageFrom" action="jobSideManagementAction!managementCompaniesJobInformation.action" method="post">
	        <div class="inside-search">
	        	<ul>
	            	<li>用户名：
	            		<s:textfield id="userNameHid" name="blogUserInfo.userName" size="17" cssStyle="display:none"/>
	            		<input type="text" id="userName" value="<s:text name="blogUserInfo.userName"/>" size="17"/>
	            	</li>
	                <li>公司名称：
	                	<s:textfield id="companyNameHid" name="blogUserInfo.companyName" size="17" cssStyle="display:none"/>
	                	<input type="text" id="companyName" value="<s:text name="blogUserInfo.companyName"/>" size="17" />
	                </li>
	                <li><a href="#" onclick="
	                	document.getElementById('userNameHid').value=document.getElementById('userName').value;
	                	document.getElementById('companyNameHid').value=document.getElementById('companyName').value;
	               		document.getElementById('pageFrom').submit()"><img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" /></a>
	                </li>
	            </ul>
	        </div>
	        <div class="inside-tab">
	   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	                <td width="2%" align="center">&nbsp;</td>
	                <td width="18%" height="40" align="left"><strong>用户名</strong></td>
	                <td width="21%" align="left"><strong>公司名称</strong></td>
	                <td width="13%" align="left"><strong>最后上网时间</strong></td>
	                <td width="28%" align="left"><strong>已购买了的服务</strong></td>
	                <td width="6%" align="left"><strong>操作</strong></td>
	                <td width="12%" align="left"><strong>&nbsp;</strong></td>
	              </tr>
	              <s:iterator value="recruiterInformationList">
		              <tr>
		                <td width="2%" align="center">&nbsp;</td>
		                <td width="18%" height="40" align="left"><s:property value="userName"/></td>
		                <td width="21%" align="left" title="<s:property value="companyName"/>">
		                	<span style="width:150px;float:left" class="index_hid_css"><s:property value="companyName"/></span>
		                </td>
		                <td width="13%" align="left"><s:property value="getText('format.date',{lastLoginDate})"/></td>
		                <td width="28%" align="left" title="<s:property value="servicesHavePurchased"/>">
		                	<span style="width:200px;float:left" class="index_hid_css"><s:property value="servicesHavePurchased"/></span>
		                </td>
		                <td width="12%" align="left" class="inside-links"><a href="<s:url action="jobSideManagementAction!viewJobs.action">
		                	 <s:param name="jobSearchVo.userId"><s:property value="id"/></s:param>
		                </s:url>">查看招聘职位</a></td>
		              </tr>
	              </s:iterator>
	        </table>
		    </div>
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
		$("#sys_recruit_manage_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_recruit_this a").addClass("active");
	}
</script>
</html>
