<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--功能点维护</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<%@ include file="/jsp/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>功能点维护</strong></div>
	    <s:form id="pageFrom" name="pageFrom" method="post" action="sysFunction!search.action">
	        <div class="inside-search">
	        	<ul>
	            	<li><strong>功能点名称</strong>：
	            		<s:textfield name="searchdto.name" size="17" maxLength="20" id="nameHid" cssStyle="display:none"/>
	            		<input type="text" value="<s:text name="searchdto.name"/>" id="name" size="17" maxlength="20"/>
	            		</li>
	                <li><a href="#" onclick="
	                document.getElementById('nameHid').value=document.getElementById('name').value;
	                document.getElementById('pageFrom').submit()"><img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" /></a></li>
	            </ul>
	      	</div>
	        <div class="inside-tab">
	   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	              	<td width="2%" >&nbsp;</td>
	                <td width="30%" height="40" align="left" nowrap="true"><strong>功能点编码&nbsp;</strong></td>
	                <td width="60%" align="left" nowrap="true"><strong>功能点名称&nbsp;</strong></td>
	                <td width="8%" align="left"><strong>操作&nbsp;</strong></td>
	              </tr>
	              <s:if test="dtoList!=null">
	              	<s:iterator value="dtoList" var="dto">
		              <tr>
		              	<td width="2%" >&nbsp;</td>
		                <td width="30%" height="40" align="left" nowrap="true"><s:text name="code"/>&nbsp;</td>
		                <td width="60%" align="left" nowrap="true"><s:text name="name"/>&nbsp;</td>
		                <td width="8%" align="left" class="inside-links"><a href="#" onclick="beforeEdit('<s:text name="id"/>')">修改</a>&nbsp;</td>
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
       		<s:form name="sysFunctionFormEdit" method="post" action="sysFunction!beforeEdit.action">
				<s:hidden name="dto.id" id="edit_id"/>
       		</s:form>
       		<s:text name="msg"/>
       	</div>
  </div>
</div>
<script type="text/javascript">
	function beforeEdit(){
		document.getElementById("edit_id").value=arguments[0];
		document.getElementsByName("sysFunctionFormEdit")[0].submit();
	}
	function init(){
		$("#sys_function_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
	}
</script>
</body>
</html>
