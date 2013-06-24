<%@page pageEncoding="utf-8"%>
<%@ include file="/jsp/systemManage/top.jsp"%>
<div class="content">
<%@ include file="/jsp/systemManage/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>订单管理<strong></div>
        <div class="inside-search2">
        	<ul>
           	  <li>支付期限:&nbsp;<input name="" type="text" size="6"/>&nbsp;天</li>
                <li><a href="#"><img src="<%=request.getContextPath()%>/images/sz.png" width="68" height="25" /></a></li>
            </ul>
        </div>
	    <s:form id="pageFrom" name="pageFrom" method="post" action="sysOrder!search.action">
	        <div class="inside-search">
	        	<ul>

	                <li>订单日期:&nbsp;
	                	<s:textfield type="text" id="fromOrderDate" name="searchdto.fromOrder" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\\'toOrderDate\\')}'})" size="7" cssStyle="height:15px;"/>
	                		&nbsp;至&nbsp;
	                	<s:textfield type="text" id="toOrderDate" name="searchdto.toOrder" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\\'fromOrderDate\\')}'})" size="7" cssStyle="height:15px;"/>
	                </li>
	                <li>支付确认日期:&nbsp;
	                	<s:textfield id="fromPayDate" name="searchdto.fromPay" cssClass="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\\'toPayDate\\')}'})" size="7" cssStyle="height:15px;"/>
	                	&nbsp;至&nbsp;
	                	<s:textfield id="toPayDate" name="searchdto.toPay" cssClass="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\\'fromPayDate\\')}'})" size="7" cssStyle="height:15px;"/>
	                </li>
	                <li>状态:&nbsp;
	      			<s:select list="statuss" name="searchdto.status" />
	      			</li>
	                <li><a href="#" onclick="document.getElementById('pageFrom').submit()"><img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" /></a></li>
	            </ul>
	        </div>
	        <div class="inside-tab">
	   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	                <td width="12%" height="40" align="left" nowrap="true"><strong>预定服务&nbsp;</strong></td>
	                <td width="16%" align="left" nowrap="true"><strong>下单人&nbsp;</strong></td>
	                <td width="10%" align="left" nowrap="true"><strong>支付状态&nbsp;</strong></td>
	                <td width="12%" align="left" nowrap="true"><strong>确认支付日期&nbsp;</strong></td>
	                <td width="11%" align="left" nowrap="true"><strong>订单日期&nbsp;</strong></td>
	                <td width="8%"  align="left" nowrap="true"><strong>订单价格&nbsp;</strong></td>
	                <td width="16%"  align="left" nowrap="true"><strong>确认人&nbsp;</strong></td>
	                <td width="5%" align="left" nowrap="true"><strong>操作&nbsp;</strong></td>
	                <td width="8%" align="left">&nbsp;</td>
	                <td width="11%" align="left">&nbsp;</td>
	              </tr>
	              <s:if test="dtoList!=null">
	              	<s:iterator value="dtoList" var="dto">
		              <tr>
		                <td width="12%" height="40" align="left" title="<s:text name="serviceName"/>"><span style="width:80px;float:left" class="index_hid_css"><s:text name="serviceName"/></span>&nbsp; </td>
		                <td width="16%" align="left" title="<s:text name="inOrderName"/>"><span style="width:80px;float:left" class="index_hid_css"><s:text name="inOrderName"/></span>&nbsp;</td>
		                <td width="10%" align="left"><s:text name="statusName"/>&nbsp; </td>
		                <td width="12%" align="left"><s:date name="payDate" format="yyyy-MM-dd"/>&nbsp; </td>
		                <td width="11%" align="left"><s:date name="orderDate" format="yyyy-MM-dd"/>&nbsp;</td>
		                <td width="8%" align="left"><s:text name="price"/>&nbsp;</td>
		                <td width="7%" align="left" title="<s:text name="confirmorName"/>"><span style="width:80px;float:left" class="index_hid_css"><s:text name="confirmorName"/></span>&nbsp;</td>
		                <td width="5%" align="left" class="inside-links"><a href="#" onclick="remove('<s:text name="id"/>')">删除</a></td>
		                <td width="8%" align="left" nowrap="true"><a href="#" onclick="pay('<s:text name="id"/>')">确认支付&nbsp;</a></td>
		                <td width="11%" align="left" nowrap="true"><a href="#" onclick="beforeEdit('<s:text name="id"/>')">修改订单价格</a></td>
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
       		<s:form name="sysOrderFormEdit" method="post" action="sysOrder!beforeEdit.action">
				<s:hidden name="dto.id" id="edit_id"/>
       		</s:form>
       		<s:form name="sysOrderFormRemove" method="post" action="sysOrder!remove.action">
				<s:hidden name="dto.id" id="remove_id"/>
       		</s:form>
       		<s:form name="sysOrderFormPay" method="post" action="sysOrder!pay.action">
				<s:hidden name="dto.id" id="pay_id"/>
       		</s:form>
       		<s:text name="msg"/>
       	</div>
  </div>
</div>
<script type="text/javascript">
	function beforeEdit(){
		document.getElementById("edit_id").value=arguments[0];
		document.getElementsByName("sysOrderFormEdit")[0].submit();
	}
	function remove(){
		if(confirm("你确定要删除这条订单吗?")){
			document.getElementById("remove_id").value=arguments[0];
			document.getElementsByName("sysOrderFormRemove")[0].submit();
		}
	}
	function pay(){
		if(confirm("你确定修改这条订单为已支付吗?")){
			document.getElementById("pay_id").value=arguments[0];
			document.getElementsByName("sysOrderFormPay")[0].submit();
		}
	}
	function init(){
		$("#sys_order_manager_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_order_manager a").addClass("active");
	}
</script>
</body>
</html>
