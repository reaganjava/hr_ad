<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头-系统管理-添加线下人才</title>
	<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<!--以上为头部分-->
<!--中间部分-->
<div class="content">
	<jsp:include page="/jsp/left.jsp"></jsp:include>
	<s:form id="form" name="form" method="post" action="lineTalentPoolAction!saveAddRecommendation.action">
	<s:hidden id="from" name="from"></s:hidden>
	<input id="formFlag" type="hidden" value="0"/>
    <div class="content-right">
    	<div class="administrator-title"><strong>成为线下推荐对象</strong></div>
        <div class="inside-tab">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="inside-tab">
              <tr>
              	<td width="29%" height="40" align="right">
              		<em>*</em>
              		<strong>用户名：</strong>
              	</td>
              	<td width="71%" align="left" class="tab-input">
              		<s:textfield id="userName" name="recommendationDto.userName" cssClass="validate[custom[email]]" maxlength="40"></s:textfield>
              	</td>
              </tr>
              <tr>
                <td width="29%" height="40" align="right"><em>*</em><strong>职能：</strong></td>
                <td width="71%" align="left" class="tab-input">
                	<select name="recommendationDto.functionCode" id="function" class="validate[required]">
                		<option value="">职能</option>
                		<s:iterator value="competencyList">
                			<option value="<s:property value="code"/>"><s:property value="name"/></option>
                		</s:iterator>
                    </select>
                </td>
              </tr>
              <tr>
                <td width="29%" height="40" align="right"><em>*</em><strong>意向职位：</strong></td>
                <td align="left" class="tab-input">
                	<input id="expectedPosition" name="recommendationDto.expectedPosition" type="text" class="validate[required]" maxlength="20"/>
                </td>
              </tr>
              <tr>
                <td width="29%" height="40" align="right"><em>*</em><strong>意向工作地区：</strong></td>
                <td align="left">
                	<select name="recommendationDto.provinceCode" id="province" style="width:70px;" class="validate[required]">
                		<option value="">省份</option>
                		<s:iterator value="provinceList">
                			<option value="<s:property value="code"/>"><s:property value="name"/></option>
                		</s:iterator>
                    </select>
                	<select name="recommendationDto.cityCode" id="city" style="width:70px;" class="validate[required]">
                    	<option value="">城市</option>
                	</select>
                	<select name="recommendationDto.areaCode" id="area" style="width:70px;">
                		<option value="">城区</option>
                	</select></td>
              </tr>
              <tr>
                <td width="29%" height="40" align="right"><em>*</em><strong>期望月薪：</strong></td>
                <td align="left" class="tab-input">
                	<select name="recommendationDto.expectedSalaryCode" id="expectedSalary" class="validate[required]">
                		<option value="">期望月薪</option>
                		<s:iterator value="salaryList">
                			<option value="<s:property value="code"/>"><s:property value="name"/></option>
                		</s:iterator>
                    </select>
                </td>
              </tr>
              <tr>
                <td width="29%" height="40" align="right"><em>*</em><strong>工作性质：</strong></td>
                <td align="left">
	                <s:iterator value="jobTypeList" status="index">
	                	<input <s:if test="#index.index==0">checked="checked"</s:if> id="<s:property value="code"/>" name="recommendationDto.jobTypeCode" type="radio" value="<s:property value="code"/>" class="validate[required]"/>
	                	<label for="<s:property value="code"/>"><s:property value="name"/>&nbsp;</label>
	                </s:iterator>
                </td>
              </tr>
              <tr>
                <td width="29%" height="40" align="right"><em>*</em><strong>是否顾问：</strong></td>
                <td align="left">
                	<input checked="checked" id="isAdvisor_Y" name="recommendationDto.isAdvisor" type="radio" value="Y" class="validate[required]"/>
                  		<label for="isAdvisor_Y">是&nbsp;</label>
                	<input id="isAdvisor_N" name="recommendationDto.isAdvisor" type="radio" value="N" class="validate[required]"/>
                		<label for="isAdvisor_N">否</label>
                </td>
              </tr>
            </table>
		</div>
       	<div class="system-botton3">
        	<ul>
                <li class="botton1"><a href="#" onclick="return clickSave();" title="保存">保存</a></li>
                <li class="botton3"><a href="javascript:clickReturn()" title="返回">返回</a></li>
            </ul>
		</div>
  </div>
  <s:hidden id="actionMessage" name="actionMessage"></s:hidden>
  </s:form>
</div>
<script type="text/javascript">
function clickSave(){
	$("#form").submit();
	return false;
}
function clickReturn(){
	var from = $("#from").val();
	if(from == "newTalent"){
		window.location.href = "lineTalentPoolAction!viewNewTalent.action";
	}
	else{
		window.location.href = "lineTalentPoolAction!loadLineTalentPool.action";
	}
}
function init(){
	$("#sys_lineofftalents_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
}
function beforeCall(form, options){
    if($("#form").validationEngine('validate')){
 	   alert('----->');
 	   form.submit();
    }
    return true;
 }

 // Called once the server replies to the aja form validation request
 function ajaxValidationCallback(status, form, json, options){
    alert("Callback");
 }
 $(document).ready(function(){
	 var actionMessage = $("#actionMessage").val();
	 if(actionMessage != ""){
		 alert(actionMessage);
	 }
     $("#form").validationEngine({
         ajaxFormValidation: true,
         onAjaxFormComplete: ajaxValidationCallback,
         onBeforeAjaxFormValidation: beforeCall,
         validationEventTriggers:"blur",  //触发的事件  validationEventTriggers:"keyup blur",
         success:false//为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
     });
 });
//个人用户后台省、市、区三级联动（select标签）
 $(document).ready(function(){
 	$("#province").change(function(){
 		var provinceCode = $("#province option:selected").val();
 		var url = "<%=request.getContextPath()%>/common/loadCity.action?provinceCode="+provinceCode;
 		$.getJSON(url,loadCity);
 	});
 	$("#city").change(function(){
 		var cityCode = $("#city option:selected").val();
 		var url = "<%=request.getContextPath()%>/common/loadArea.action?cityCode="+cityCode;
 		$.getJSON(url,loadArea);
 	});
 	loadCity=function(data){
 		var options = "<option value=\"\">城市</option>";
 		var value = "";
 		var text = "";
 		data = eval('('+data+')');
 		$.each(data,function(i,item){
 			value = item.code;
 			text = item.name;
 			options = options + "<option value=\""+value+"\">"+text+"</option>";
 		});
 		$("#city").html(options);
 		var cityCode = $("#city option:selected").val();
 		var url = "<%=request.getContextPath()%>/common/loadArea.action?cityCode="+cityCode;
 		$.getJSON(url,loadArea);
 	};
 	loadArea=function(data){
 		var options = "<option value=\"\">城区</option>";
 		var value = "";
 		var text = "";
 		data = eval('('+data+')');
 		$.each(data,function(i,item){
 			value = item.code;
 			text = item.name;
 			options = options + "<option value=\""+value+"\">"+text+"</option>";
 		});
 			$("#area").html(options);
 	};
 });
</script>
</body>
</html>