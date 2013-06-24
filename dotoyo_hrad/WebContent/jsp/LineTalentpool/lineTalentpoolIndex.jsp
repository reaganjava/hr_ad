<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头--系统管理--线下人才库</title>
	<%@ include file="/jsp/common.jsp"%>
	<script src="<%=request.getContextPath()%>/javascripts/My97DatePicker/WdatePicker.js"></script>
	<style>
		.inside-search a{color:#fff; text-decoration:underline;}
		.inside-search a:hover{color:#fff; text-decoration:none;}
		.rcss-p{width:100%;padding:0px 15px 0px 10px; font-size:14px;}
		.rcss-p-left{float:left;width:700px;font-size:12px;color:#666; line-height:30px;height:96px;padding-left:10px;text-align:left;}
		.rcss-p-left p strong{font-weight:normal;color:#000;}
	</style>
	<script type="text/javascript">
		// 标题与摘要
		function showPatternzy(){
			$("#showAbstract").val("1");
			$("#showAbstract1").val("1");
			$(".abstractInfo").show();
			return false;
		}
		function showPatternbt(){
			$("#showAbstract").val("0");
			$("#showAbstract1").val("0");
			$(".abstractInfo").hide();
			return false;
		}
		function showSingle(obj){
			
			if($(obj).parent().next().attr("style").indexOf("none")>0){
				$(obj).parent().next().show();				
			}
			else{
				$(obj).parent().next().hide();
			}
		}
		$(document).ready(function(){
			var showAbstract = $("#showAbstract").val();
			if(showAbstract == "1"){
				$(".abstractInfo").show();
			}
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
</head>


<%@ include file="/jsp/top.jsp"%>
<!--中间部分-->
<div class="content">
	<jsp:include page="/jsp/left.jsp"></jsp:include>
    <div class="content-right">
    	<div class="administrator-title"><strong>线下人才库</strong></div>
    	<s:form  id="searchForm" name="searchForm" method="POST" action="lineTalentPoolAction!loadLineTalentPool.action">
    	<s:hidden id="showAbstract" name="showAbstract"></s:hidden>
    	<s:hidden name="isNew"></s:hidden>
    	<div class="inside-search2">
        	<ul>
            	<li>职位名称：<input type="text" id="expectedPosition" name="expectedPosition" size="12" value="<s:property value='expectedPosition'/>"/></li>
            	<li>专长：<input type="text" id="metier" name="metier" size="12" value="<s:property value='metier'/>"/></li>
            	<li>工作地点：
					<s:select id="province" name="provinceCode" cssStyle="width:80px;" list="provinceList" listKey="code" listValue="name" headerKey="" headerValue="省份"></s:select>
					<s:select id="city" name="cityCode" cssStyle="width:80px;" list="cityList" listKey="code" listValue="name" headerKey="" headerValue="城市"></s:select>
					<s:select id="area" name="areaCode" cssStyle="width:80px;" list="areaList" listKey="code" listValue="name" headerKey="" headerValue="城区"></s:select>
				</li>
            </ul>
      	</div>
      	<div class="inside-search2">
        	<ul>
            	<li>面试情况：
            		<s:select id="interviewStatus" name="interviewStatus" list="#{'':'所有','0':'未面试','1':'面试通过','2':'面试不通过' }">
            		</s:select>
            	</li>
                <li id="inside-search-input">
                	<input type="image" src="<%=request.getContextPath()%>/images/inside-botton2.png" />
                </li>
            </ul>
      	</div>
      	</s:form>
      	<s:form  id="pageFrom" name="pageFrom" method="POST" action="lineTalentPoolAction!loadLineTalentPool.action">
      	<s:hidden id="showAbstract1" name="showAbstract"></s:hidden>
      	<s:hidden name="expectedPosition"></s:hidden>
      	<s:hidden name="metier"></s:hidden>
      	<s:hidden name="isNew"></s:hidden>
        <div class="inside-search" style="line-height:30px;">
        	<ul>
            	<li>显示：</li>
            	<li><a id="showPatternbt" href="javascript:void(0)" onclick="showPatternbt();">标题显示</a></li>
            	<li><a id="showPatternzy" href="javascript:void(0)" onclick="showPatternzy();">摘要显示</a></li>
            	<li>&nbsp;</li>
            </ul>
        	<ul>
            	<li>排序：</li>
            	<li><a href="javascript:void(0)" onclick="orderDisplay('expectedPosition');return false;">意向职位</a></li>
            	<li><a href="javascript:void(0)" onclick="orderDisplay('categoryIndex');return false;">星级指数</a></li>
            	<li><a href="javascript:void(0)" onclick="orderDisplay('lastEditDate');return false;">更新时间</a></li>
            </ul>
        </div>
        <div class="inside-tab">
   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="2%" align="left"><input id="selected" name="selected" type="checkbox" /></td>
                <td width="12%" align="left"><strong>意向职位</strong></td>
                <td width="12%" align="left"><strong>专业</strong></td>
                <td width="12%" align="left"><strong>专长</strong></td>
                <td width="8%" align="left"><strong>星级指数</strong></td>
                <td width="9%" align="left"><strong>产生方式</strong></td>
                <td width="8%" align="left"><strong>审核状态</strong></td>
                <td width="8%" align="left"><strong>面试情况</strong></td>
                <td width="8%" align="left"><strong>发布求职</strong></td>
                <td width="10%" align="left"><strong>更新时间</strong></td>
              </tr>
              <s:iterator value="lineTalentPoolList">
	              <tr>
	              	<td width="2%" align="left"><input name="lineTalents" type="checkbox" value="<s:property value="id"/>" /></td>
	                <td width="12%" align="left">
		                <span style="width:95px;float:left" class="index_hid_css" title="<s:property value='expectedPosition'/>">
		                	<s:property value="expectedPosition"/>
		                </span>
	                </td>
	                <td width="12%" align="left">
	                	<span style="width:95px;float:left" class="index_hid_css" title="<s:property value='specializedType'/>">
		                	<s:property value="specializedType"/>
		                </span>
	                </td>
	                <td width="12%" align="left">
	                	<span style="width:95px;float:left" class="index_hid_css" title="<s:property value='metier'/>">
		                	<s:property value="metier"/>
		                </span>
	                </td>
	                <td width="8%" height="40" align="center""><s:property value="categoryIndex"/></td>
	                <td width="9%" align="left">
	                	<s:if test="recommendationType==0">
	                		线上申请
	                	</s:if>
	                	<s:elseif test="recommendationType==1">
	                		后台录入
	                	</s:elseif>
	                </td>
	                <td width="8%" align="left">
	                	<s:if test="verifyStatus==0">
	                		待审核
	                	</s:if>
	                	<s:elseif test="verifyStatus==2">
	                		审核通过
	                	</s:elseif>
	                	<s:elseif test="verifyStatus==3">
	                		审核不通过
	                	</s:elseif>
	                </td>
	                <td width="8%" align="left">
	                	<s:if test='interviewStatus==0'>
	                		未面试
	                	</s:if>
	                	<s:if test='interviewStatus==1'>
	                		面试通过
	                	</s:if>
	                	<s:if test='interviewStatus==2'>
	                		面试不通过
	                	</s:if>
	                </td>
	                <td width="8%" align="center"">
	                	<s:if test="havePublished>0">
	                		是
	                	</s:if>
	                	<s:else>
	                		否
	                	</s:else>
	                </td>
	                <td width="10%" align="left" onclick="showSingle(this);" class="showSingle">
	                	<s:property value="getText('formatDate_yyyy-MM-dd',{lastEditDate})"/>
	                	<img  src="<%=request.getContextPath()%>/images/abstract-text-icon.gif" width="7" height="12" />
	                </td>
	              </tr>
	              <tr class="abstractInfo" style="display:none;">
	              	<td colspan="10">
	<div class="rcss-p" >
    	<div class="rcss-p-left">
        	<p>
            	<strong>姓名：</strong><s:property value="userName"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <strong>意向工作地点：</strong><s:property value="provinceCode"/> <s:property value="cityCode"/> <s:property value="areaCode"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <strong>学历：</strong><s:property value="education"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <br/>
                <strong>人才编号：</strong><s:property value="id"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <strong>工作年限：</strong><s:property value="workingLife"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <strong>职能：</strong><s:property value="competency"/>
                <strong>行业：</strong><s:property value="industryType"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <strong>年龄：</strong><s:property value="age"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <strong>性别：</strong><s:if test='sex=="1"'>男</s:if><s:elseif test='sex=="0"'>女</s:elseif><s:else>不限</s:else>&nbsp;&nbsp;&nbsp;&nbsp;
                <br/>
                <strong>语言能力：</strong><s:property value="langCapa"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <strong>掌握程度：</strong><s:property value="mastery"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <strong>计算机能力：</strong><s:property value="computerGrade"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <strong>顾问：</strong><s:if test='isAdviser=="Y"'>是</s:if><s:else>否</s:else>&nbsp;&nbsp;&nbsp;&nbsp;
                <strong>工作性质：</strong><s:property value="jobNature"/>
		  </p>
        </div>
    </div>
	              	</td>
	              </tr>
              </s:iterator>
        </table>
		</div>

        <div class="qx">
	         <s:property value="pageInfo.htmlListTableFootInfo" escapeHtml="false"/>
	    </div>
		<div class="system-botton2">
        	<ul>
                <li class="botton1">
                	<a href="lineTalentPoolAction!toAddRecommendation.action" title="增加">增加</a>
                </li>
                <li class="botton2"><a href="javascript:delTalent('lineTalents')" title="删除">删除</a></li>
                <li class="botton3"><a href="javascript:examineAndVerify('lineTalents',2)" title="审核通过">审核通过</a></li>
                <li class="botton3"><a href="javascript:examineAndVerify('lineTalents',3)" title="审核不通过">审核不通过</a></li>
                <li class="botton1"><a href="javascript:editInterviewStatus('1')" title="面试通过" >面试通过</a></li>
                <li class="botton2"><a href="javascript:editInterviewStatus('2')" title="面试不通过">面试不通过</a></li>
            </ul>
		</div>
	    <div class="num">
	    	<s:property value="pageInfo.htmlPagingInfo" escapeHtml="false"/>
	    </div>
	    </s:form>
	</div>
</div>
<script type="text/javascript">
function delTalent(checkboxName){
	if(checkStatus(checkboxName)==false) {
		alert('请至少选中一条记录');
	}else{
		if(window.confirm("你确认要进行此操作吗？")){
		document.pageFrom.action='lineTalentPoolAction!delLineTalent.action';
		document.pageFrom.method='post';
		document.pageFrom.submit();
		}
	}
}

function examineAndVerify(checkboxName,status){
	if(checkStatus(checkboxName)==false) {
		alert('请至少选中一条记录');
	}else{
		if(window.confirm("你确认要进行此操作吗？")){
		document.pageFrom.action='lineTalentPoolAction!examineAndVerify.action?verifyStatus='+status;
		document.pageFrom.method='post';
		document.pageFrom.submit();
		}
	}
}
function editInterviewStatus(interviewStatus){
	if(checkStatus("lineTalents")) {
		if(window.confirm("你确认要进行此操作吗？")){
		document.pageFrom.action='lineTalentPoolAction!editInterviewStatus.action?interviewStatus='+interviewStatus;
		document.pageFrom.method='post';
		document.pageFrom.submit();
		}
	}else{
		alert('请至少选中一条记录');
	}
}
function orderDisplay(columnName){
	document.pageFrom.action='lineTalentPoolAction!loadLineTalentPool.action?columnName='+columnName;
	document.pageFrom.method='post';
	document.pageFrom.submit();
}
function init(){
	$("#sys_lineofftalents_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
}

</script>
</body>
</html>