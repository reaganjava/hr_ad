<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--管理员角色管理</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<%@ include file="/jsp/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>管理员角色管理</strong></div>
	    <s:form id="pageFrom" name="pageFrom" method="post" action="sysRoleAdmin!search.action">
	        <div class="inside-search">
	        	<ul>
	            	<li><strong>角色名称</strong>：
	            		<s:textfield id="nameHid" name="searchdto.name" size="17" maxLength="20" cssStyle="display:none"/>
	            		<input type="text" id="name" value="<s:text name="searchdto.name"/>" size="17" maxlength="20"/>&nbsp;
	            	</li>
	                <li><a href="#" onclick="
	                document.getElementById('nameHid').value=document.getElementById('name').value;
	                document.getElementById('pageFrom').submit()"><img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" /></a></li>
	            </ul>
	      	</div>
	        <div class="inside-tab">
	   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	              	<td width="5%"><input id="selected" name="selected" type="checkbox" value=""/></td>
	                <td width="30%" height="40" align="left" nowrap="true"><strong>角色编码&nbsp;</strong></td>
	                <td width="40%" align="left" nowrap="true"><strong>角色名称&nbsp;</strong></td>
	                <td width="8%" align="left" ><strong>操作&nbsp;</strong></td>
	                <td width="17%" align="left" >&nbsp;</td>
	              </tr>
	              <s:if test="dtoList!=null">
	              	<s:iterator value="dtoList" var="dto">
		              <tr>
		              	<td width="5%">
		              		<s:if test="assignCounts==0">
		              			<input name="removeBox" type="checkbox" value="<s:property value="id"/>"/>
		              		</s:if>
		              		<s:else>
		              			<input name="removeBox" type="checkbox" disabled="disabled" value="<s:property value="id"/>"/>
		              		</s:else>
		              	</td>
		                <td width="30%" height="40" align="left" nowrap="true"><s:text name="code"/>&nbsp;</td>
		                <td width="40%" align="left" nowrap="true"><s:text name="name"/>&nbsp;</td>
		                <td width="8%" align="left" class="inside-links">
		                	<a href="#" onclick="beforeEdit('<s:text name="id"/>')">修改</a>&nbsp;
		                </td>
		                <td width="17%" align="left">
		                	<a href="#" onclick="beforeAssign('<s:text name="id"/>')">分配角色成员</a>&nbsp;
		                </td>
		              </tr>
	              </s:iterator>
	              </s:if>
	            </table>
			</div>

	        <div class="qx" >
	           <s:property value="pageInfo.htmlListTableFootInfo" escapeHtml="false"/>
	        </div>
	       	<div class="system-botton3">
	        	<ul>
	            	<li class="botton1"><a href="<%=request.getContextPath()%>/sys/sysRoleAdmin!beforeAdd.action">增加</a></li>
	                <li class="botton3"><a href="#" onclick="submitForm4ChangeAction(pageFrom,'removeBox','sysRoleAdmin!remove.action')">删除</a></li>
	            </ul>
	        </div>
			<div class="num">
			   <s:property value="pageInfo.htmlPagingInfo" escapeHtml="false"/>
			</div>
		</s:form>
       	<div style="display:none">
       		<s:form id="sysRoleFormEdit" method="post" action="sysRoleAdmin!beforeEdit.action">
				<s:hidden name="dto.id" id="edit_id"/>
       		</s:form>
       		<s:form id="sysRoleFormAssign" method="post" action="sysRoleAdmin!beforeAssign.action">
				<s:hidden name="dto.id" id="assign_id"/>
       		</s:form>
       		<s:text name="msg"/>
       	</div>
  </div>
</div>
<script type="text/javascript">
	function beforeEdit(){
		document.getElementById("edit_id").value=arguments[0];
		document.getElementsByName("sysRoleFormEdit")[0].submit();
	}
	function beforeAssign(){
		document.getElementById("assign_id").value=arguments[0];
		document.getElementsByName("sysRoleFormAssign")[0].submit();
	}
	function init(){
		$("#sys_admin_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_admin_role a").addClass("active");
	}
</script>
</body>
</html>
