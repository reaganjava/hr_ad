<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--订单管理</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<%@ include file="/jsp/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>订单管理</strong></div>
    	<s:form id="pageFrom" name="pageFrom" method="post" action="sysOrder!search.action">
    	<s:hidden id="isNew" name="searchdto.isNew"></s:hidden>
        <div class="inside-search2">
        	<ul>
           	  <li>支付期限:
               	<s:textfield name="payDueTime" cssStyle="ime-mode:disabled" maxLength="6"
                	onkeypress="return limitNumberOnkeyPress(this,event)"
                	onpaste = "return limitNumberOnpaste(this)"
                	ondrop = "return limitNumberOndrop(this,event)" size="10"></s:textfield>
           	  	&nbsp;天</li>
                <li><a href="#" onclick="genSubmitForm4ChangeAction(document.getElementById('pageFrom'),'sysOrder!setDueTime.action')"><img src="<%=request.getContextPath()%>/images/sz.png" width="68" height="25" /></a></li>
            </ul>
        </div>
	        <div class="inside-search">
	        	<ul>
	                <li>订单期
	                	<s:textfield  id="fromOrderDateHid" name="searchdto.fromOrder"  cssStyle="display:none"/>
	                	<input type="text" size="11" id="fromOrderDate" value="<s:text name="searchdto.fromOrder"/>" class="Wdate2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'toOrderDate\')}'})"/>

	                	<s:textfield  id="toOrderDateHid" name="searchdto.toOrder" cssStyle="display:none"/>
	                	<input type="text" size="11" id="toOrderDate" value="<s:text name="searchdto.toOrder"/>" class="Wdate2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'fromOrderDate\')}'})"/>
	                </li>
	                <li>支付期
	                	<s:textfield id="fromPayDateHid" name="searchdto.fromPay" cssStyle="display:none"/>
	                	<input type="text" size="11" id="fromPayDate" value="<s:text name="searchdto.fromPay"/>" class="Wdate2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'toPayDate\')}'})"/>

	                	<s:textfield id="toPayDateHid" name="searchdto.toPay" cssStyle="display:none"/>
	                	<input type="text" size="11" id="toPayDate" value="<s:text name="searchdto.toPay"/>" class="Wdate2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'fromPayDate\')}'})"/>
	                </li>
	                <li>状态
	      			<s:select id="statusHid" list="statuss" name="searchdto.status" cssStyle="display:none"/>
	      			<s:select id="status" list="statuss" value="searchdto.status" />
	      			</li>
	                <li><a href="#" onclick="
	                document.getElementById('fromOrderDateHid').value=document.getElementById('fromOrderDate').value;
	                document.getElementById('toOrderDateHid').value=document.getElementById('toOrderDate').value;
	                document.getElementById('fromPayDateHid').value=document.getElementById('fromPayDate').value;
	                document.getElementById('toPayDateHid').value=document.getElementById('toPayDate').value;
	                document.getElementById('statusHid').value=document.getElementById('status').value;
	                document.getElementById('pageFrom').submit()">
	                <img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" />
	                </a></li>
	            </ul>
	        </div>
	        <div class="inside-tab">
	   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	              	<td width="2%">&nbsp;</td>
	                <td width="13%" height="40" align="left" nowrap="true"><strong>预定服务&nbsp;</strong></td>
	                <td width="16%" align="left"><strong>下单人&nbsp;</strong></td>
	                <td width="10%" align="left" nowrap="true"><strong>支付状态&nbsp;</strong></td>
	                <td width="12%" align="left" nowrap="true"><strong>支付日期&nbsp;</strong></td>
	                <td width="11%" align="left" nowrap="true"><strong>订单日期&nbsp;</strong></td>
	                <td width="8%"  align="left" nowrap="true"><strong>订单价格&nbsp;</strong></td>
	                <td width="13%"  align="left" nowrap="true"><strong>确认人&nbsp;</strong></td>
	                <td width="5%" align="left" nowrap="true"><strong>操作&nbsp;</strong></td>
	                <td width="8%" align="left">&nbsp;</td>
	                <td width="11%" align="left">&nbsp;</td>
	              </tr>
	              <s:if test="dtoList!=null">
	              	<s:iterator value="dtoList" var="dto">
		              <tr>
		              	<td width="2%">&nbsp;</td>
		                <td width="13%" height="40" align="left" title="<s:text name="serviceName"/>"><span style="width:80px;float:left" class="index_hid_css"><s:text name="serviceName"/></span> </td>
		                <td width="16%" height="40" align="left" title="<s:text name="inOrderName"/>"><span style="width:100px;float:left" class="index_hid_css"><s:text name="inOrderName"/></span> </td>
		                <td width="10%" align="left"><s:text name="statusName"/>&nbsp; </td>
		                <td width="12%" align="left" nowrap="nowrap" title="<s:date name="payDate" format="yyyy-MM-dd HH:mm"/>">
		                	<s:if test="status==1">
		                		<s:date name="payDate" format="yyyy-MM-dd"/>
		                	</s:if>
		                	&nbsp;
		                </td>
		                <td width="11%" align="left" nowrap="nowrap" title="<s:date name="orderDate" format="yyyy-MM-dd HH:mm"/>"><s:date name="orderDate" format="yyyy-MM-dd"/>&nbsp;</td>
		                <td width="8%" align="left">
		                	<s:if test='priceStatus == "0"'>
								面议
		                	</s:if>
		                	<s:else>
		                		<s:text name="price" />
		                	</s:else>
		                	</td>
		                <td width="13%" align="left" title="<s:text name="confirmorName"/>"><span style="width:80px;float:left" class="index_hid_css">&nbsp;<s:text name="confirmorName"/></span>&nbsp;</td>
		                <td width="5%" align="left" class="inside-links"><a href="#" onclick="remove('<s:text name="resId"/>')">删除</a></td>
		                <td width="8%" align="left" nowrap="true">
		                	<s:if test="canEdit == true">
		                		<a href="#" onclick="pay('<s:text name="id"/>')">确认支付&nbsp;</a>
		                	</s:if>
		                </td>
		                <td width="11%" align="left" nowrap="true">
		                	<s:if test="canEdit == true">
		                		<a href="#" onclick="beforeEdit('<s:text name="id"/>')">修改价格</a>
		                	</s:if>
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
       		<s:form name="sysOrderFormEdit" method="post" action="sysOrder!beforeEdit.action">
				<s:hidden name="dto.id" id="edit_id"/>
       		</s:form>
       		<s:form name="sysOrderFormRemove" method="post" action="sysOrder!remove.action">
				<s:hidden name="dto.resId" id="remove_resId"/>
       		</s:form>
       		<s:form name="sysOrderFormPay" method="post" action="sysOrder!beforePay.action">
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
			document.getElementById("remove_resId").value=arguments[0];
			document.getElementsByName("sysOrderFormRemove")[0].submit();
		}
	}
	function pay(){
		//if(confirm("你确定修改这条订单为已支付吗?")){
			document.getElementById("pay_id").value=arguments[0];
			document.getElementsByName("sysOrderFormPay")[0].submit();
		//}
	}
	function init(){
		$("#sys_order_manager_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
	}
</script>
</body>
</html>
