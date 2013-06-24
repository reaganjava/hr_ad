<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--增值服务设置--修改广告位服务设置</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<jsp:include page="/jsp/left.jsp"></jsp:include>
    <div class="content-right">
    	<div class="administrator-title"><strong>修改广告位服务设置</strong></div>
    	<s:form id="pageFrom" name="pageFrom" method="post" action="advertSetting!edit.action">
        <div class="inside-tab">
	 	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	                <td width="20%" align="right" valign="middle"><strong>套餐名称：</strong></td>
	                <td width="80%" height="40" align="left" class="tab-input">
	                	<s:hidden name="dto.serviceCode"/>
	                	<s:text name="dto.serviceName"/>
	                	<FONT color="red"><s:text name="serviceNameMsg"/></FONT>
	                </td>
	              </tr>
	              <tr>
	                <td width="20%" align="right" valign="top"><strong>广告位：</strong></td>
	                <td width="80%" align="left">
	                	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		                	<s:iterator id="filed" value="advertisings" status="index">
		                		<tr>
		                			<td>
		                				<s:checkboxlist list="filed" name="dto_advertising" listKey="key" listValue="value"/>
		                			</td>
		                		</tr>
		                	</s:iterator>
	                	</table>
	                	<input type="hidden" id="advertisingid" value="<s:text name="dto.advertising"/>"/>
	                	<FONT color="red"><s:text name="serviceAdvertisingMsg"/></FONT>
	                </td>
	              </tr>

	            </table>
			</div>
	       	<div class="system-botton">
	        	<ul>
	            	<li class="botton1"><a href="#" onclick="$('#pageFrom').submit()" title="保存">保存</a></li>
	                <li class="botton3"><a href="<%=request.getContextPath()%>/sys/advertSetting!cancel.action" title="取消">取消</a></li>
	            </ul>
	        </div>
      	</s:form>
  		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	function init(){
		//init 人才搜索字段
		for(var i = 0;i < document.getElementsByName("dto_advertising").length;i++){
			var obj = document.getElementsByName("dto_advertising")[i];
			obj.className="validate[required]";
			if(checkIsExist(obj.value)){
				obj.checked = "true";
			}else{
				obj.checked = "";
			}
		}
		function checkIsExist(){
			var fieldValue = document.getElementById("advertisingid").value;
			var fields = "";
			if(fieldValue != ""){
				fields = fieldValue.split(",");
			}
			for(var i = 0;i < fields.length;i++){
				if(arguments[0] == fields[i]){
					return true;
				}
			}
			return false;
		}
		$("#sys_increment_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_increment_ul_advertising a").addClass("active");
	}
</script>
