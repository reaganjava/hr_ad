<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--管理员管理</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<body>
<div class="content">
<%@ include file="/jsp/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>管理员管理</strong></div>
	    <s:form id="pageFrom" name="pageFrom" method="post" action="sysMemberAdmin!userSearch.action">
	        <div class="inside-search">
	        	<ul>
	            	<li><strong>管理员ID</strong>：
	            		<s:textfield id="userNameHid" name="userSearchdto.userName" size="17" maxLength="20" cssStyle="display:none"/>
	            		<input type="text" id="userName" value="<s:text name="userSearchdto.userName"/>" size="17" maxlength="20"/>&nbsp;
	            		</li>
	            	<li><strong>分配角色</strong>：
	            		<s:textfield id="rolenamesHid" name="userSearchdto.rolenames" size="17" maxLength="20" cssStyle="display:none"/>
	            		<input type="text" id="rolenames" value="<s:text name="userSearchdto.rolenames"/>" size="17" maxlength="20"/>
	            		&nbsp;
	            		</li>
	            	<li><strong>状态</strong>：
	            		<s:select id="statusHid" list="statuss" name="userSearchdto.status" cssStyle="display:none"/>
	            		<s:select id="status" list="statuss" value="userSearchdto.status"/>
	            	</li>
	                <li><a href="javascript:void(0)" onclick="
	                document.getElementById('userNameHid').value=document.getElementById('userName').value;
	                document.getElementById('rolenamesHid').value=document.getElementById('rolenames').value;
	                document.getElementById('statusHid').value=document.getElementById('status').value;
	                document.getElementById('pageFrom').submit();return false;"><img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" /></a></li>
	            </ul>
	      	</div>
	        <div class="inside-tab">
	   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	              	<td width="5%"><input id="selected" name="selected" type="checkbox" value=""/></td>
	                <td width="20%" height="40" align="left" nowrap="true"><strong>管理员ID&nbsp;</strong></td>
	                <td width="45%" align="left"><strong>分配角色&nbsp;</strong></td>
	                <td width="10%" align="left" ><strong>状态&nbsp;</strong></td>
	                <td width="1%" align="left" nowrap="true"><strong>操作&nbsp;</strong></td>
	                <td width="9%" >&nbsp;</td>
	                <td width="10%" >&nbsp;</td>
	              </tr>
	              <s:if test="userDtoList!=null">
	              	<s:iterator value="userDtoList" var="dto">
		              <tr>
		              	<td width="5%"><input name="removeBox" type="checkbox" value="<s:property value="id"/>"/></td>
		                <td width="20%" height="40" align="left" nowrap="true" title="<s:text name="userName"/>"><span style="width:130px;float:left" class="index_hid_css"><s:text name="userName"/></span>&nbsp;</td>
		                <td width="45%" align="left" title="<s:text name="rolenames"/>"><span style="width:300px;float:left" class="index_hid_css"><s:text name="rolenames"/></span>&nbsp;</td>
		                <td width="10%" align="left"><s:text name="status"/>&nbsp;</td>
		                <td width="1%" align="left" class="inside-links" nowrap="true"><a href="#" onclick="beforeEdit('<s:text name="id"/>')">修改</a></td>
		                <td width="9%" ><a href="#" onclick="beforeAssign('<s:text name="id"/>')">分配角色</a></td>
	                	<td width="10%" ><a href="#" onclick="beforeAssignFun('<s:text name="id"/>')">分配功能点</a></td>
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
	            	<li class="botton1"><a href="<%=request.getContextPath()%>/sys/sysMemberAdmin!beforeAdd.action">增加</a></li>
	                <li class="botton3"><a href="javascript:void(0)" onclick="submitForm4ChangeAction(pageFrom,'removeBox','sysMemberAdmin!remove.action');return false;">删除</a></li>
	            </ul>
	        </div>
			<div class="num">
			   <s:property value="userPageInfo.htmlPagingInfo" escapeHtml="false"/>
			</div>
		</s:form>
       	<div style="display:none">
       		<s:form id="sysUserFormEdit" method="post" action="sysMemberAdmin!beforeEdit.action">
				<s:hidden name="userdto.id" id="edit_id"/>
       		</s:form>
       		<s:form id="sysUserFormAssign" method="post" action="sysMemberAdmin!beforeAssign.action">
				<s:hidden name="userdto.id" id="assign_id"/>
       		</s:form>
       		<s:form id="sysUserFormAssignFun" method="post" action="sysMemberAdmin!beforeAssignFunctions.action">
				<s:hidden name="funSearchdto.userid" id="assignfun_id"/>
       		</s:form>
       		<s:text name="msg"/>
       	</div>
  </div>
</div>
<script type="text/javascript">
	function beforeAssign(){
		document.getElementById("assign_id").value=arguments[0];
		document.getElementById("sysUserFormAssign").submit();
	}

	function beforeEdit(){
		document.getElementById("edit_id").value=arguments[0];
		document.getElementsByName("sysUserFormEdit")[0].submit();
	}
	function beforeAssignFun(){
		document.getElementById("assignfun_id").value=arguments[0];
		document.getElementById("sysUserFormAssignFun").submit();
	}
	function init(){
		$("#sys_admin_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_admin_manage a").addClass("active");
	}
</script>
</body>
</html>
