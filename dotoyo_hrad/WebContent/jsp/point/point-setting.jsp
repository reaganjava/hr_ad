<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--积分设置</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<%@ include file="/jsp/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>积分设置</strong></div>
	    <s:form id="pageFrom" name="pageFrom" method="post" action="sysPointSetting!search.action">
	        <div class="inside-search">
	        	<ul>
	            	<li><strong>操作行为</strong>：
	            		<s:textfield id="actionHid" name="searchdto.action" size="17" maxLength="20" cssStyle="display:none"/>
	            		<input id="action" type="text" value="<s:text name="searchdto.action"/>" size="17" maxlength="20"/>
	            	</li>
	                <li><a href="#" onclick="
	                document.getElementById('actionHid').value=document.getElementById('action').value;
	                document.getElementById('pageFrom').submit()"><img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" /></a></li>
	            </ul>
	      	</div>
	        <div class="inside-tab">
	   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	              	<td width="2%">&nbsp;</td>
	                <td width="20%" height="40" align="left" nowrap="true"><strong>积分编码&nbsp;</strong></td>
	                <td width="40%" align="left" nowrap="true"><strong>操作行为&nbsp;</strong></td>
	                <td width="28%" align="left" nowrap="true"><strong>积分分值&nbsp;</strong></td>
	                <td width="10%" align="left"><strong>操作&nbsp;</strong></td>
	              </tr>
	              <s:if test="dtoList!=null">
	              	<s:iterator value="dtoList" var="dto">
		              <tr>
		              	<td width="2%">&nbsp;</td>
		                <td width="20%" height="40" align="left" nowrap="true"><s:text name="code"/>&nbsp;</td>
		                <td width="40%" align="left" nowrap="true"><s:text name="action"/>&nbsp;</td>
		                <td width="28%" align="left" nowrap="true"><s:text name="point"/>&nbsp;</td>
		                <td width="10%" align="left" class="inside-links"><a href="#" onclick="beforeEdit('<s:text name="code"/>')">修改</a>&nbsp;</td>
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
       		<s:form name="sysPointFormEdit" method="post" action="sysPointSetting!beforeEdit.action">
				<s:hidden name="dto.code" id="edit_id"/>
       		</s:form>
       		<s:text name="msg"/>
       	</div>
  </div>
</div>
<script type="text/javascript">
	function beforeEdit(){
		document.getElementById("edit_id").value=arguments[0];
		document.getElementsByName("sysPointFormEdit")[0].submit();
	}
	function init(){
		$("#sys_point_setting_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_point_setting a").addClass("active");
	}
</script>
</body>
</html>
