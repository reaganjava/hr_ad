<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--管理员权限设置</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<%@ include file="/jsp/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>管理员角色权限分配列表</strong></div>
	    <s:form id="pageFrom" name="pageFrom" method="post" action="sysFunctionRoleAdmin!search.action">
	        <div class="inside-search">
	        	<ul>
	            	<li><strong>角色名称</strong>：
	            	<s:textfield name="searchdto.name" id="nameHid" size="17" maxLength="20" cssStyle="display:none"/>
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
	              	<td width="2%">&nbsp;</td>
	                <td width="30%" height="40" align="left" nowrap="true"><strong>角色编码&nbsp;</strong></td>
	                <td width="50%" align="left" nowrap="true"><strong>角色名称&nbsp;</strong></td>
	                <td width="18%" align="left" ><strong>操作</strong></td>
	              </tr>
	              <s:if test="dtoList!=null">
	              	<s:iterator value="dtoList" var="dto">
		              <tr>
		                <td width="2%">&nbsp;</td>
		                <td width="30%" height="40" align="left" nowrap="true"><s:text name="code"/>&nbsp;</td>
		                <td width="50%" align="left" nowrap="true"><s:text name="name"/>&nbsp;</td>
		                <td width="18%" align="left" class="inside-links" >
		                	<a href="#" onclick="beforeAssign('<s:text name="id"/>')">分配功能点</a>&nbsp;
		                </td>
		              </tr>
	              </s:iterator>
	              </s:if>
	            </table>
			</div>
	        <div class="qx" >
	           <s:property value="pageInfo.htmlListTableFootInfoNoAllCheck" escapeHtml="false"/>
	        </div>
			<div class="num">
			   <s:property value="pageInfo.htmlPagingInfo" escapeHtml="false"/>
			</div>
		</s:form>
       	<div style="display:none">
       		<s:form id="sysFunctionRoleFormAssign" method="post" action="sysFunctionRoleAdmin!beforeAssign.action">
				<s:hidden name="dto.id" id="assign_id"/>
       		</s:form>
       		<s:text name="msg"/>
       	</div>
  </div>
</div>
<script type="text/javascript">
	function beforeAssign(){
		document.getElementById("assign_id").value=arguments[0];
		document.getElementsByName("sysFunctionRoleFormAssign")[0].submit();
	}

	function init(){
		$("#sys_admin_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_admin_setting a").addClass("active");
	}
</script>
</body>
</html>
