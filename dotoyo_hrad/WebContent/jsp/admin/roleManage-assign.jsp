<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--管理员角色管理--分配角色成员</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<%@ include file="/jsp/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>分配管理员角色成员</strong></div>
	    <s:form id="pageFrom" name="pageFrom" method="post" action="sysRoleAdmin!userSearch.action">
	        <div class="inside-search">
	        	<ul>
	            	<li><strong>管理员名</strong>：
	            		<s:textfield id="userNameHid" name="userSearchdto.userName" size="17" maxLength="20" cssStyle="display:none"/>
	            		<input type="text" id="userName" value="<s:text name="userSearchdto.userName"/>" size="17" maxlength="20"/>
	            	&nbsp;</li>
	            	<li><strong>状态</strong>：
	            		<s:select id="statusHid" list="statuss" name="userSearchdto.status" cssStyle="display:none"/>
	            		<s:select id="status" list="statuss" value="userSearchdto.status"/>
	            	</li>
	                <li><a href="#" onclick="
					document.getElementById('userNameHid').value=document.getElementById('userName').value;
					document.getElementById('statusHid').value=document.getElementById('status').value;
	                document.getElementById('pageFrom').submit()"><img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" /></a></li>
	            </ul>
	      	</div>
	        <div class="inside-tab">
	   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	              	<td width="5%"><input id="selected" name="selected" type="checkbox" value=""/></td>
	                <td width="75%" height="40" align="left" nowrap="true"><strong>管理员名&nbsp;</strong></td>
	                <td width="20%" align="left" ><strong>状态&nbsp;</strong></td>
	              </tr>
	              <s:if test="userDtoList!=null">
	              	<s:iterator value="userDtoList" var="dto">
		              <tr>
		              	<td width="5%">
		              		<s:if test="roleid==null">
		              			<input type="checkbox" name="assignBox" value="<s:text name="id"/>"/>
		              		</s:if>
		              		<s:else>
		              			<input type="checkbox" checked="checked" name="assignBox" value="<s:text name="id"/>"/>
		              		</s:else>
		              	</td>
		                <td width="75%" height="40" align="left" nowrap="true"><s:text name="userName"/>&nbsp;</td>
		                <td width="20%" align="left"><s:text name="status"/>&nbsp;</td>
		              </tr>
	              </s:iterator>
	              </s:if>
	            </table>
			</div>
	        <div class="qx" >
	           <s:property value="userPageInfo.htmlListTableFootInfo" escapeHtml="false"/>
	        </div>
	       	<div class="system-botton3">
	        	<ul>
	            	<li class="botton1"><a href="#" title="确定" onclick="submitForm4ChangeActionNull(pageFrom,'assignBox','sysRoleAdmin!assign.action')">确定</a></li>
	                <li class="botton3"><a href="<%=request.getContextPath()%>/sys/sysRoleAdmin!cancel.action" title="取消">取消</a></li>
	            </ul>
	        </div>
			<div class="num">
			   <s:property value="userPageInfo.htmlPagingInfo" escapeHtml="false"/>
			</div>
			<div style="display:none">
				<input type="checkbox" id="hidden_true_box" name="assignBox" value="hidden_true_box"/>
			</div>
		</s:form>
       	<div style="display:none">
       		<s:text name="msg"/>
       	</div>
  </div>
</div>
<script type="text/javascript">
	function init(){
		$("#sys_admin_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_admin_role a").addClass("active");
	}
</script>
</body>
</html>
