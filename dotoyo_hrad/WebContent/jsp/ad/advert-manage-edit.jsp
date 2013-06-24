<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--广告管理--修改广告</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<jsp:include page="/jsp/left.jsp"></jsp:include>
    <div class="content-right">
    	<div class="administrator-title"><strong>修改广告</strong></div>
    	<s:form id="pageFrom" name="pageFrom" method="post" action="sysAd!edit.action" enctype="multipart/form-data">
	        <div class="inside-tab">
	   	    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="inside-tab">
	              <tr>
	                <td width="29%" height="40" align="right"><strong>广告标题</strong>：</td>
	                <td width="71%" align="left" class="tab-input">
	                	<s:textfield name="dto.title" maxLength="20" cssClass="validate[custom[nullCheckS],custom[filteringSpecialChar]]"/>
	                	<FONT color="red" id="titleMsg"><s:text name="titleMsg"/></FONT>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>广告公司</strong>：</td>
	                <td align="left" class="tab-input">
	                	<s:textfield name="dto.company" maxLength="20" cssClass="validate[custom[nullCheckS],custom[filteringSpecialChar]]"/>
	                	<FONT color="red" id="companyMsg"><s:text name="companyMsg"/></FONT>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>公司所在城市</strong>：</td>
	                <td align="left" class="tab-input">
						<s:select id="province" onchange="COM_setSelectTitle(this,0)" name="dto.province" list="provinces" listKey="code" listValue="name" headerKey="" headerValue="省" cssStyle="width:70px;" cssClass="validate[custom[requiredSelected]]"/>
						<s:select id="city" onchange="COM_setSelectTitle(this,1)" name="dto.city" list="citys" listKey="code" listValue="name" headerKey="" headerValue="市" cssStyle="width:70px;" cssClass="validate[custom[requiredSelected]]"/>
	                	<FONT color="red" id="cityMsg"><s:text name="cityMsg"/></FONT>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>广告时段</strong>：</td>
	                <td align="left" class="tab-input">
	                	<s:textfield type="text" id="startDate" name="dto.start" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\\'endDate\\')}'})" cssStyle="" cssClass="validate[custom[nullCheckS]]"/>
	                		&nbsp;至&nbsp;
	                	<s:textfield type="text" id="endDate" name="dto.end" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\\'startDate\\')}'})" cssStyle="" cssClass="validate[custom[nullCheckS]]"/>
	                	<FONT color="red" id="startDateMsg"><s:text name="startDateMsg"/></FONT>
	                	<FONT color="red" id="endDateMsg"><s:text name="endDateMsg"/></FONT>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>广告位置</strong>：</td>
	                <td align="left" class="tab-input">
	                	<input type="hidden"  value="<s:text name="dto.oldPosition"/>" name="dto.oldPosition"  id="dto.oldPosition"/>
	                	<s:select id="all_postions" list="all_postions" name="dto.position" onchange="COM_setSelectTitle(this,2);selectTypeChange()" cssClass="validate[custom[positionMoreThan8]]"/>
	                	<FONT color="red" id="positionMsg"><s:text name="positionMsg"/></FONT>
	                </td>
	              </tr>
	               <tr>
	                <td width="29%" height="40" align="right"><strong>广告索引</strong>：</td>
	                <td align="left" class="tab-input">
	                	<s:textfield  name="dto.adindex" maxLength="3" cssClass="validate[custom[nullCheckS],custom[onlyNumber]]"/>
	                	<FONT color="red" id="adindexMsg"><s:text name="adindexMsg"/></FONT>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>广告类型</strong>：</td>
	                <td align="left" class="tab-input">
	                	<s:radio list="types" name="dto.type" id="type" onclick="typeClick(this)" cssClass="validate[required]"/>
	                	<FONT color="red" id="typeMsg"><s:text name="typeMsg"/></FONT>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>广告类型信息</strong>：</td>
	                <td align="left" class="tab-input">
	                	<s:textarea name="dto.typeContent" cssClass="validate[custom[nullCheckS],length[0,200]]"/>
	                	<FONT color="red" id="typeContentMsg"><s:text name="typeContentMsg"/></FONT>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>Url</strong>：</td>
	                <td align="left" class="tab-input">
	                	<s:textfield name="dto.url" maxlength="200" cssClass="validate[custom[url]]"/>
	                </td>
	              </tr>
	              <tr style="display:none">
	                <td width="29%" height="40" align="right"><strong>广告大小</strong>：</td>
	                <td align="left" class="tab-input">
	                	长:
	                	<s:textfield name="dto.width"  maxlength="10" value="0"/>
	                	宽:
	                	<s:textfield name="dto.height"  maxlength="10" value="0"/>
	                	<FONT color="red" id="widthMsg"><s:text name="widthMsg"/></FONT>
	                	<FONT color="red" id="heightMsg"><s:text name="heightMsg"/></FONT>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>广告图片/flash</strong>：</td>
	                <td align="left" class="tab-input">
	                	<s:file id="adMg" name="dto.file"  onchange="fileOnclick(this)"/>
	                	<s:if test="dto.imgName != null && dto.imgName != ''">
	                		&nbsp;&nbsp;&nbsp;<a target="_bank" href="<s:text name="dto.serverUrl"/>/<s:text name="dto.imgName"/>" title="<s:text name="dto.serverUrl"/>/<s:text name="dto.imgName"/>">查看</a>
	                	</s:if>
	                	<FONT color="red" id="advertImgMsg"><s:text name="advertImgMsg"/></FONT>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>介绍</strong>：</td>
	                <td align="left" class="tab-input">
	                	<s:textarea name="dto.description" cssClass="validate[length[0,500]]"/>
	                	<FONT color="red" id="descriptionMsg"></FONT>
	                </td>
	              </tr>
	            </table>
			</div>
	       	<div class="system-botton3">
	        	<ul>
	            	<li class="botton1"><a href="#" onclick="$('#pageFrom').submit()" title="保存">保存</a></li>
	                <li class="botton3"><a href="<%=request.getContextPath()%>/sys/sysAd!cancel.action" title="取消">取消</a></li>
	            </ul>
	        </div>
      	</s:form>
  		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	var imgBtnValue = "";
	function init(){
		$("#sys_advert_manager_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		var rado = document.getElementsByName("dto.type");
		var k = 0;
		for(var i = 0;i < rado.length;i++){
			if(rado[i].checked && rado[i].value == "1"){
				k++;
			}
		}
		if(k > 0){
			document.getElementById("adMg").disabled = true;
		}else{
			document.getElementById("adMg").disabled = false;
		}
		imgBtnValue = '<s:text name="dto.type" />';
		//初始化下拉列表的title
		COM_SetOptionTitle();
		//设定默认选中的title
		COM_setSelectTitle(document.getElementById("province"),0);
		COM_setSelectTitle(document.getElementById("city"),1);
		COM_setSelectTitle(document.getElementById("all_postions"),2);
	}
		/**
	** 显示控制
	**/
	function selectTypeChange(){
		 document.getElementById("pageFrom").action="sysAd!changeTypeEdit.action";
     	 document.getElementById("pageFrom").submit();
	}
	function typeClick(obj){
		imgBtnValue = obj.value;
		if(obj.value == "1"){
			document.getElementById("adMg").disabled = true;
			document.getElementById("adMg").className="";
			document.getElementById("adMg").value="";
		}else{
			document.getElementById("adMg").disabled = false;
		}
		fileOnclick(document.getElementById("adMg"));
	}
	function fileOnclick(obj){
		if(obj.value == "") return;
		if(imgBtnValue == "0"){
			document.getElementById("adMg").className="validate[custom[isImg]]";
		}else if(imgBtnValue == "2"){
			document.getElementById("adMg").className="validate[custom[isFlash]]";
		}else if(imgBtnValue == "1"){
			document.getElementById("adMg").className="";
		}else{
			document.getElementById("adMg").className="validate[custom[isImgFlash]]";
		}
	}
function getFullPath(obj)
{
if(obj)
{
//ie
if (window.navigator.userAgent.indexOf("MSIE")>=1)
{
obj.select();
return document.selection.createRange().text;
}
//firefox
else if(window.navigator.userAgent.indexOf("Firefox")>=1)
{
if(obj.files)
{
return obj.files.item(0).getAsDataURL();
}
return obj.value;
}
return obj.value;
}
}
//个人用户后台省、市、区三级联动（select标签）
$(document).ready(function(){
	$("#province").change(function(){
		var provinceCode = $("#province option:selected").val();
		var url = "<%=request.getContextPath()%>/common/loadCity.action?provinceCode="+provinceCode;
		$.getJSON(url,loadCity);
	});
	$("#city").change(function(){
		var cityCode = $("#city option:selected").val();
	});
	loadCity=function(data){
		var options = "";
		var defaultCityOption = "<option value=\"\">市</option>";
		var value = "";
		var text = "";
		data = eval('('+data+')');
		$.each(data,function(i,item){
			value = item.code;
			text = item.name;
			options = options + "<option value=\""+value+"\">"+text+"</option>";
		});
		$("#city").html(options);

		var defaultName = $("#province").val();
		if(defaultName == ""){
			$("#city").html(defaultCityOption);
		}
		COM_setSelectTitle(document.getElementById("city"),1);
	};

});
</script>
