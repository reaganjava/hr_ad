<%@page pageEncoding="utf-8"%>
<%@ include file="/jsp/systemManage/top.jsp"%>
<!--以上为头部分-->
<!--中间部分-->
<div class="content">
<jsp:include page="/jsp/systemManage/left.jsp"></jsp:include>
    <div class="content-right">
    	<div class="administrator-title"><strong>增加人才挖掘字段设置</strong></div>
    	<s:form name="sysIncrementTalentsFoundFM" method="post" action="talentsFoundSetting!add.action">
        <div class="inside-tab">
	 	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	                <td width="20%" align="right" valign="middle"><strong>服务名称：</strong></td>
	                <td width="80%" height="40" align="left" class="tab-input">
	                	<s:select list="serviceNames" name="dto.serviceCode" />
	                	<FONT color="red"><s:text name="serviceNameMsg"/></FONT>
	                </td>
	              </tr>
	              <tr>
	                <td width="20%" align="right" valign="middle"><strong>人才搜索的字段：</strong></td>
	                <td width="80%" align="left">
	                	<s:checkboxlist list="fieldsOfSearchs" name="dto.fieldsOfSearch" />
	                	<input type="hidden" id="fieldsOfSearchsid" value="<s:text name="dto.fieldsOfSearch"/>"/>
	                	<FONT color="red"><s:text name="serviceSearchFiledsMsg"/></FONT>
	                </td>
	              </tr>
	              <tr>
	                <td width="20%" align="right" valign="middle"><strong>可搜索的次数：</strong></td>
	                <td width="80%" height="40" align="left" class="tab-input">
	                	<s:textfield name="dto.timesOfSearch" cssStyle="ime-mode:disabled" maxLength="10"
	                	onkeypress="return limitNumberOnkeyPress(this,event)"
	                	onpaste = "return limitNumberOnpaste(this)"
	                	ondrop = "return limitNumberOndrop(this,event)"></s:textfield>
	                	<FONT color="red"><s:text name="serviceTimesOfSearchMsg"/></FONT>
	                </td>
	              </tr>
	              <tr>
	                <td width="10%" align="right" valign="middle"><strong>价格：</strong></td>
	                <td width="80%" height="40" align="left" class="tab-input" nowrap="nowrap">
	                	<input style="ime-mode:disabled" maxLength="10" id="dto.price" value="<s:text name="dto.price"/>"/>
	                	<s:hidden id="priceValue" name="dto.price"/>
	                	<s:checkbox name="dto.priceStatus" id="dto.priceStatus" onclick="priceClick()"/>面议
	                	<FONT color="red"><s:text name="priceMsg"/></FONT>
	                </td>
	              </tr>
	            </table>
			</div>
	       	<div class="system-botton">
	        	<ul>
	            	<li class="botton1"><a href="#" onclick="document.getElementById('priceValue').value=document.getElementById('dto.price').value;document.getElementsByName('sysIncrementTalentsFoundFM')[0].submit()" title="保存">保存</a></li>
	                <li class="botton3"><a href="<%=request.getContextPath()%>/sysIncrement/talentsFoundSetting!cancel.action" title="取消">取消</a></li>
	            </ul>
	        </div>
      	</s:form>
  		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	function init(){
		if(document.getElementById("dto.priceStatus").checked){
			document.getElementById("dto.price").disabled = true;
			document.getElementById("dto.price").value = "0";
		}else{
			document.getElementById("dto.price").disabled = false;
		}
		if(document.getElementById("dto.price").value == "0"){
			document.getElementById("dto.price").disabled = true;
			document.getElementById("dto.priceStatus").checked = "true";
		}else{
			document.getElementById("dto.price").disabled = false;
		}
		//init 人才搜索字段
		for(var i = 0;i < document.getElementsByName("dto.fieldsOfSearch").length;i++){
			var obj = document.getElementsByName("dto.fieldsOfSearch")[i];
			if(checkIsExist(obj.value)){
				obj.checked = "true";
			}else{
				obj.checked = "";
			}
		}
		function checkIsExist(){
			var fieldValue = document.getElementById("fieldsOfSearchsid").value;
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
		$("#sys_increment_ul_talents a").addClass("active");
	}
	function priceClick(){
		if(document.getElementById("dto.priceStatus").checked){
			document.getElementById("dto.price").disabled = true;
			document.getElementById("dto.price").value = "0";
		}else{
			document.getElementById("dto.price").disabled = false;
		}
	}
</script>
