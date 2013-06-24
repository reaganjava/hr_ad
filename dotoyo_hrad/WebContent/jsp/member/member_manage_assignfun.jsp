<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--会员管理--分配功能点</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<%
	String from =(String) request.getAttribute("from");
%>
<div class="content">
<%@ include file="/jsp/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>分配功能点</strong></div>
	    <s:form id="pageFrom" name="pageFrom" method="post" action="sysMember!searchFun.action">
	        <div class="inside-tab">
	   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	              	<td width="5%"><input id="selected" name="selected" type="checkbox" value=""/></td>
	                <td width="40%" height="40" align="left" nowrap="true"><strong>功能点编码&nbsp;</strong></td>
	                <td width="55%" align="left" nowrap="true"><strong>功能点名称&nbsp;</strong></td>
	              </tr>
	              <s:if test="functionDtoList!=null">
	              	<s:iterator value="functionDtoList" var="dto">
		              <tr>
		              	<td width="5%">
		              		<s:if test="userid==null">
		              			<input type="checkbox" name="assignBox" value="<s:text name="id"/>" />
		              		</s:if>
		              		<s:else>
		              			<input type="checkbox" checked="checked" name="assignBox" value="<s:text name="id"/>"/>
		              		</s:else>
		              	</td>
		                <td width="40%" height="40" align="left" nowrap="true"><s:text name="code"/>&nbsp;</td>
		                <td width="55%" align="left" nowrap="true"><s:text name="name"/>&nbsp;</td>
		              </tr>
	              </s:iterator>
	              </s:if>
	            </table>
			</div>
	        <div class="qx" >
	        </div>
	       	<div class="system-botton3">
	        	<ul>
	            	<li class="botton1"><a href="#" onclick="submitForm4ChangeActionNull(pageFrom,'assignBox','sysMember!assignFun.action?from=<%=from%>')">确定</a></li>
	                <li class="botton3"><a href="<%=request.getContextPath()%>/sys/sysMember!cancel.action?from=<%=from%>" title="取消">取消</a></li>
	            </ul>
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
		$("#sys_member_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_member_manage a").addClass("active");
	}
</script>
</body>
</html>
