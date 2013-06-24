<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--增值服务设置--修改套餐名称设置</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<jsp:include page="/jsp/left.jsp"></jsp:include>
    <div class="content-right">
    	<div class="administrator-title"><strong>修改套餐名称设置</strong></div>
    	<s:form id="pageFrom" name="pageFrom" method="post" action="serviceNameMainAction!edit.action">
        <div class="inside-tab">

   	    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tab-input">
              <tr>
                <td width="12%" align="right" valign="middle"><strong>套餐编码：</strong></td>
                <td width="88%" height="40" align="left">
                	<s:label name="dto.serviceCode"/>
                	<s:hidden name="dto.serviceCode"/>
                	<FONT color="red"><s:text name="serviceCodeMsg"/></FONT>
                </td>
              </tr>
              <tr>
                <td width="12%" align="right" valign="middle"><strong>套餐类型：</strong></td>
                <td width="88%" height="40" align="left">
                	<s:if test='dto.serviceType=="0"'>
	                		个人套餐
	                	</s:if>
	                	<s:elseif test='dto.serviceType=="1"'>
	                		企业套餐
	                	</s:elseif>
                	<s:hidden name="dto.serviceType"/>
                </td>
              </tr>
              <tr>
                <td width="12%" align="right" valign="middle"><strong>套餐名称：</strong></td>
                <td width="88%" height="40" align="left">
                	<s:textfield name="dto.serviceName" maxLength="20" cssClass="validate[custom[nullCheckS],custom[filteringSpecialChar]]"></s:textfield>
                	<FONT color="red"><s:text name="serviceNameMsg"/></FONT>
                </td>
              </tr>
              <tr>
                <td width="12%" align="right" valign="middle"><strong>可用天数：</strong></td>
                <td width="88%" height="40" align="left">
                	<s:textfield name="dto.usableDays" maxLength="4" cssClass="validate[required,custom[onlyNumber]]"></s:textfield>
                	<FONT color="red"><s:text name="serviceUseAbleMsg"/></FONT>
                </td>
              </tr>
              <tr>
                <td width="12%" align="right" valign="middle"><strong>价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格：</strong></td>
                <td width="88%" height="40" align="left">
	                	<input style="ime-mode:disabled" maxlength="9" id="priceid" class="validate[custom[onlyDouble]]" value="<s:text name="dto.price"/>"/>
	                	<s:hidden id="priceValue" name="dto.price"/>
	                	<s:checkbox name="dto.priceStatus" id="dto.priceStatus" onclick="priceClick()"/>面议
	                	<input type="hidden" id="priceStatusValue" value="<s:property value='dto.priceStatus'/>"/>
                </td>
              </tr>
              <tr>
                <td width="12%" align="right" valign="middle"><strong>服务简介：</strong></td>
                <td width="88%" height="40" align="left">
                	<s:textarea name="dto.serviceIntro" cssClass="validate[length[0,500]]"></s:textarea>
                	<FONT color="red"><s:text name="serviceInfMsg"/></FONT>
                </td>
              </tr>
            </table>
			</div>
	       	<div class="system-botton">
	        	<ul>
	            	<li class="botton1"><a href="#" onclick="document.getElementById('priceValue').value=document.getElementById('priceid').value;$('#pageFrom').submit();return false;" title="保存">保存</a></li>
	                <li class="botton3"><a href="<%=request.getContextPath()%>/sys/serviceNameMainAction!cancel.action" title="取消">取消</a></li>
	            </ul>
	        </div>
      	</s:form>
  		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	function init(){
		var priceStatusValue = $("#priceStatusValue").val();
		if(priceStatusValue == "0"){
			document.getElementById("priceid").disabled = true;
			document.getElementById("dto.priceStatus").checked = "true";
		}
		if(document.getElementById("dto.priceStatus").checked){
			document.getElementById("priceid").disabled = true;
		}else{
			document.getElementById("priceid").disabled = false;
		}
		$("#sys_increment_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_increment_ul_sysname a").addClass("active");
	}
	function priceClick(){
		if(document.getElementById("dto.priceStatus").checked){
			document.getElementById("priceid").disabled = true;
			if($.trim($("#priceid").val())==""){
				$("#priceid").val("0");
			}
		}else{
			document.getElementById("priceid").disabled = false;
		}
	}
</script>
