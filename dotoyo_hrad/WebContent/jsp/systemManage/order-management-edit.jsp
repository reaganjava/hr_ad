<%@page pageEncoding="utf-8"%>
<%@ include file="/jsp/systemManage/top.jsp"%>
<!--以上为头部分-->
<!--中间部分-->
<div class="content">
<jsp:include page="/jsp/systemManage/left.jsp"></jsp:include>
    <div class="content-right">
    	<div class="administrator-title"><strong>修改挂证服务设置</strong></div>
    	<s:form name="sysOrderFM" method="post" action="sysOrder!edit.action">
	        <div class="inside-tab">
	   	    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="inside-tab">
	              <tr>
	                <td width="29%" height="40" align="right"><strong>预定服务</strong>：</td>
	                <td width="71%" align="left" class="tab-input">
	                	<s:text name="dto.serviceName"/>
	                	<s:hidden name="dto.id"/>
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
	                	<s:textfield name="dto.price" />
	                	<FONT color="red"><s:text name="priceMsg"/></FONT>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>备注</strong>：</td>
	                <td align="left">
	                	<s:text name="dto.remark"/>
	                </td>
	              </tr>
	            </table>
			</div>
	       	<div class="system-botton">
	        	<ul>
	            	<li class="botton1"><a href="#" onclick="document.getElementsByName('sysOrderFM')[0].submit()" title="修改价格">修改价格</a></li>
	                <li class="botton3"><a href="<%=request.getContextPath()%>/sysOrder/sysOrder!cancel.action" title="取消">取消</a></li>
	            </ul>
	        </div>
      	</s:form>
  		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	function init(){

		$("#sys_order_manager_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_order_manager a").addClass("active");
	}
</script>
