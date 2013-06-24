<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--订单管理--确认支付订单</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<%
	String from = (String) request.getAttribute("from");
%>
<div class="content">
<jsp:include page="/jsp/left.jsp"></jsp:include>
    <div class="content-right">
    	<div class="administrator-title"><strong>确认支付订单</strong></div>
    	<form id="pageFrom" name="pageFrom" method="post" action="sysOrder!pay.action?from=<%=from%>">
	        <div class="inside-tab">
	   	    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="inside-tab">
	              <tr>
	                <td width="29%" height="40" align="right"><strong>预定套餐</strong>：</td>
	                <td width="71%" align="left" class="tab-input">
	                	<s:text name="dto.serviceName"/>
	                	<s:hidden name="dto.id"/>
	                	<s:hidden name="dto.resId"/>
	                	<s:hidden name="dto.status"/>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>下单人</strong>：</td>
	                <td align="left" class="tab-input">
	                	<s:text name="dto.inOrderName"/>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>订单日期</strong>：</td>
	                <td align="left" class="tab-input">
	                	<s:date name="dto.orderDate" format="yyyy-MM-dd"/>
	                </td>
	         	 </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>订单价格</strong>：</td>
	                <td align="left" class="tab-input">
	                	<s:if test="dto.price == 0">
							面议
	                	</s:if>
	                	<s:else>
	                		<s:text name="dto.price" />
	                	</s:else>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>备注</strong>：</td>
	                <td align="left" class="tab-input">
	                	<s:textarea name="dto.remark" cssClass="validate[length[0,255]]" cols="30"/>
	                	<FONT color="red"><s:text name="remarkMsg"/></FONT>
	                </td>
	              </tr>
	            </table>
			</div>
	       	<div class="system-botton3">
	        	<ul>
	            	<li class="botton1"><a href="#" onclick="$('#pageFrom').submit()" title="确认支付">确认支付</a></li>
	                <li class="botton3"><a href="<%=request.getContextPath()%>/sys/sysOrder!cancel.action?from=<%=from%>" title="取消">取消</a></li>
	            </ul>
	        </div>
      	</form>
  		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	function init(){

		$("#sys_order_manager_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
	}
</script>
