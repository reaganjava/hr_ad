<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--密码重置</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<%@ include file="/jsp/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>密码重置</strong></div>
	    <s:form id="pageFrom" name="pageFrom" method="post" action="sysMemberReset!userSearch.action">
	        <div class="inside-search">
	        	<ul>
	            	<li><strong>用户名</strong>：
	            		<s:textfield id="userNameHid" name="userSearchdto.userName" size="17" maxLength="20" cssStyle="display:none"/>
	            		<input type="text" id="userName" value="<s:text name="userSearchdto.userName"/>" size="12" maxlength="20"/>
	            		&nbsp;</li>
	            	<li><strong>分配角色</strong>：
	            		<s:textfield id="rolenamesHid" name="userSearchdto.rolenames" size="17" maxLength="20" cssStyle="display:none"/>
	            		<input id="rolenames" type="text" id="userName" value="<s:text name="userSearchdto.rolenames"/>" size="12" maxlength="20"/>
	            	&nbsp;</li>
	            	<li><strong>用户类型</strong>:
	            		<s:select id="userTypeHid" list="userType" name="userSearchdto.userType" cssStyle="display:none"/>
	            		<s:select id="userType" list="userType" value="userSearchdto.userType"/>
	            	</li>
	            	<li><strong>状态</strong>:
	            		<s:select id="statusHid" list="statuss" name="userSearchdto.status" cssStyle="display:none"/>
	            		<s:select id="status" list="statuss" value="userSearchdto.status"/>
	            	</li>
	                <li><a href="javascript:void(0)" onclick="
	                document.getElementById('userNameHid').value=document.getElementById('userName').value;
	                document.getElementById('rolenamesHid').value=document.getElementById('rolenames').value;
	                document.getElementById('userTypeHid').value=document.getElementById('userType').value;
	                document.getElementById('statusHid').value=document.getElementById('status').value;
	                document.getElementById('pageFrom').submit();return false;"><img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" /></a></li>
	            </ul>
	      	</div>
	        <div class="inside-tab">
	   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	                <td width="20%" height="40" align="left" nowrap="true"><strong>用户名&nbsp;</strong></td>
	                <td width="5%" align="left" nowrap="true"><strong>积分&nbsp;</strong></td>
	                <td width="30%" align="left"><strong>分配角色&nbsp;</strong></td>
	                <td width="10%" align="left" ><strong>状态&nbsp;</strong></td>
	                <td width="20%" align="left" ><strong>最后登录时间&nbsp;</strong></td>
	                <td width="15%" align="left" nowrap="true"><strong>操作&nbsp;</strong></td>
	              </tr>
	              <s:if test="userDtoList!=null">
	              	<s:iterator value="userDtoList" var="dto">
		              <tr>
		                <td width="20%" height="40" align="left" nowrap="true" title="<s:text name="userName"/>"><span style="width:130px;float:left" class="index_hid_css"><s:text name="userName"/></span>&nbsp;</td>
		                <td width="5%" align="left" nowrap="true"><s:text name="point"/>&nbsp;</td>
		                <td width="30%" align="left" title="<s:text name="rolenames"/>"><span style="width:200px;float:left" class="index_hid_css"><s:text name="rolenames"/></span>&nbsp;</td>
		                <td width="10%" align="left"><s:text name="status"/>&nbsp;</td>
		                <td width="20%" align="left" nowrap="true"><s:date name="lastLoginDate" format="yyyy-MM-dd HH:mm:ss"/>&nbsp;</td>
		                <td width="15%" align="left" class="inside-links" nowrap="true"><a href="#" onclick="beforeReset('<s:text name="id"/>')">密码重置</a>&nbsp;</td>
		              </tr>
	              </s:iterator>
	              </s:if>
	            </table>
			</div>
	        <div class="qx" >
	           <s:property value="userPageInfo.htmlListTableFootInfoNoAllCheck" escapeHtml="false"/>
	        </div>
			<div class="num">
			   <s:property value="userPageInfo.htmlPagingInfo" escapeHtml="false"/>
			</div>
		</s:form>
       	<div style="display:none">
       		<s:form id="sysUserFormReset" method="post" action="sysMemberReset!beforeReset.action">
				<s:hidden name="userdto.id" id="reset_id"/>
       		</s:form>
       		<s:text name="msg"/>
       	</div>
  </div>
</div>
<script type="text/javascript">
	function beforeReset(){
		document.getElementById("reset_id").value=arguments[0];
		document.getElementsByName("sysUserFormReset")[0].submit();
	}
	function init(){
		$("#sys_member_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_member_reset a").addClass("active");
	}
</script>
</body>
</html>
