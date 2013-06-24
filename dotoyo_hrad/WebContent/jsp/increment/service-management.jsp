<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--增值服务管理</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<%@ include file="/jsp/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>增值服务管理</strong></div>
	    <s:form id="pageFrom" name="pageFrom" method="post" action="sysIncrementManage!search.action">
	        <div class="inside-search">
	        	<ul>

	                <li>用户名:&nbsp;
						<s:textfield name="searchdto.inOrderName" cssStyle="display:none" id="inOrderNameHid"/>
						<input type="text" value="<s:text name="searchdto.inOrderName"/>" id="inOrderName"/>
	                </li>
	                <li>套餐名称:&nbsp;
	      			<s:select list="serviceNames" name="searchdto.serviceCode" cssStyle="display:none" id="serviceCodeHid"/>
	      			<s:select list="serviceNames" value="searchdto.serviceCode" id="serviceCode" onchange="COM_setSelectTitle(this,0)"/>
	      			</li>
	                <li>支付日期:&nbsp;
	                	<s:textfield name="searchdto.pay" cssStyle="display:none" id="payHid"/>
	                	<input type="text" value="<s:text name="searchdto.pay"/>" class="Wdate2" id="pay" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',errDealMode:1})" size="9" style="height:15px;"/>
	                </li>

	                <li><a href="#" onclick="document.getElementById('serviceCodeHid').value=document.getElementById('serviceCode').value;
	                document.getElementById('inOrderNameHid').value=document.getElementById('inOrderName').value;
	                document.getElementById('payHid').value=document.getElementById('pay').value;
	                document.getElementById('pageFrom').submit();">
	                	<img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" />
	                	</a>
	                </li>
	            </ul>
	        </div>
	        <div class="inside-tab">
	   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	              	<td width="2%">&nbsp;</td>
	                <td width="30%" height="40" align="left" nowrap="true"><strong>用户名&nbsp;</strong></td>
	                <td width="30%" align="left" nowrap="true"><strong>购买的套餐&nbsp;</strong></td>
	                <td width="28%" align="left" nowrap="true"><strong>支付日期&nbsp;</strong></td>
	                <td width="20%" align="left" nowrap="true"><strong>操作&nbsp;</strong></td>
	              </tr>
	              <s:if test="dtoList!=null">
	              	<s:iterator value="dtoList" var="dto">
		              <tr>
		              	<td width="2%">&nbsp;</td>
		                <td width="30%" height="40" align="left"><s:text name="inOrderName"/>&nbsp; </td>
		                <td width="30%" align="left"><s:text name="serviceName"/>&nbsp; </td>
		                <td width="28%" align="left"><s:date name="payDate" format="yyyy-MM-dd"/>&nbsp; </td>
		                <td width="20%" align="left" nowrap="true" class="inside-links"><a href="#" onclick="beforeView('<s:text name="serviceCode"/>','<s:text name="itemCode"/>')">查看详情</a></td>
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
       		<s:form name="sysIncrementFormView" method="post" action="sysIncrementManage!beforeView.action">
				<s:hidden name="dto.serviceCode" id="view_id"/>
				<s:hidden name="dto.itemCode" id="view_itemid"/>
       		</s:form>
       	</div>
  </div>
</div>
<script type="text/javascript">
	function beforeView(){
		document.getElementById("view_id").value=arguments[0];
		document.getElementById("view_itemid").value=arguments[1];
		document.getElementsByName("sysIncrementFormView")[0].submit();
	}
	function init(){
		$("#sys_increment_manager_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		COM_SetOptionTitle();
		alertMsg('<s:text name="msg"/>');
	}
</script>
</body>
</html>
