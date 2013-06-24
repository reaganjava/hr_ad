<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--系统日志</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<%@ include file="/jsp/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>系统日志</strong></div>
	    <s:form id="pageFrom" name="pageFrom" method="post" action="sysLog!search.action">
	        <div class="inside-search">
	        	<ul>
	            	<li><strong>操作人</strong>：
	            		<s:textfield id="operatorNameHid" name="searchdto.operatorName" size="17" maxLength="20" cssStyle="display:none"/>
	            		<input id="operatorName" type="text" value="<s:text name="searchdto.operatorName"/>" size="17" maxlength="20"/>
	            	</li>
	                <li>操作时间:&nbsp;
	                	<s:textfield id="operateTimeHid" name="searchdto.operateTime" cssClass="Wdate"  cssStyle="display:none"/>
	                	<input id="operateTime" type="text" value="<s:text name="searchdto.operateTime"/>" class="Wdate2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',errDealMode:1})" size="13" style="height:15px;"/>
	                </li>
 					<li><a href="#" onclick="
 					document.getElementById('operatorNameHid').value=document.getElementById('operatorName').value;
 					document.getElementById('operateTimeHid').value=document.getElementById('operateTime').value;
 					document.getElementsByName('pageFrom')[0].submit()"><img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" /></a></li>
	            </ul>
	      	</div>
	        <div class="inside-tab">
	   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	              	<td width="5%"><input id="selected" name="selected" type="checkbox" value=""/></td>
	                <td width="30%" height="40" align="left" nowrap="true"><strong>操作人&nbsp;</strong></td>
	                <td width="20%" align="left" nowrap="true"><strong>操作动作&nbsp;</strong></td>
	                <td width="20%" align="left" ><strong>访问IP&nbsp;</strong></td>
	                <td width="25%" align="left" ><strong>操作时间&nbsp;</strong></td>
	              </tr>
	              <s:if test="dtoList!=null">
	              	<s:iterator value="dtoList" var="dto">
		              <tr>
		              	<td width="5%">
		              		<input type="checkbox" name="removeBox" value="<s:text name="id"/>"/>
		              	</td>
		                <td width="30%" height="40" align="left" nowrap="true"><s:text name="operatorName"/>&nbsp;</td>
		                <td width="20%" align="left" nowrap="true"><s:text name="action"/>&nbsp;</td>
						<td width="20%" align="left" nowrap="true"><s:text name="ip"/>&nbsp;</td>
						<td width="25%" align="left"><s:date name="operate_time" format="yyyy-MM-dd HH:mm:ss"/>&nbsp; </td>
		              </tr>
	              </s:iterator>
	              </s:if>
	            </table>
			</div>
	        <div class="qx" >
	           <s:property value="pageInfo.htmlListTableFootInfo" escapeHtml="false"/>
	        </div>
	       	<div class="shanchu"><a href="#" onclick="submitForm4ChangeAction(pageFrom,'removeBox','sysLog!remove.action')">删除</a></div>
			<div class="num">
			   <s:property value="pageInfo.htmlPagingInfo" escapeHtml="false"/>
			</div>

		</s:form>
       	<div style="display:none">
       		<s:text name="msg"/>
       	</div>
  </div>
</div>
<script type="text/javascript">
	function init(){
		$("#sys_log_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
	}
</script>
</body>
</html>
