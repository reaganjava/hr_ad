<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头-系统管理-招聘职位管理</title>
	<%@ include file="/jsp/common.jsp"%>
	<script src="<%=request.getContextPath()%>/javascripts/My97DatePicker/WdatePicker.js"></script>
</head>
<%@ include file="/jsp/top.jsp"%>

<div class="content">
<jsp:include page="/jsp/left.jsp"></jsp:include>
<div class="content-right">
    	<div class="administrator-title"><strong>招聘职位管理</strong></div>
    	<s:form  id="pageFrom" name="pageFrom" method="POST" action="jobSideManagementAction!companiesJobManage.action">
    	<s:hidden id="isNew" name="jobSearchVo.isNew"></s:hidden>
        <div class="inside-search">
        	<ul>
            	<li>职位名称:
            		<s:textfield id="jobNameHid" name="jobSearchVo.jobName" size="17" cssStyle="display:none"/>
            		<input id="jobName" type="text" value="<s:text name="jobSearchVo.jobName"/>" size="17"/>
            	</li>
                <li>有效期:
	                <input  id="jobIssuetDateHid" name="jobSearchVo.jobIssuetDate" size="15" type="text" value="<s:property value="@com.dotoyo.buildjob.common.util.DateUtil@getFormatDateByFormat(jobSearchVo.jobIssuetDate,'yyyy-MM-dd')"/>" style="display:none"/>
	                <input  id="jobIssuetDate" size="12" type="text" value="<s:property value="@com.dotoyo.buildjob.common.util.DateUtil@getFormatDateByFormat(jobSearchVo.jobIssuetDate,'yyyy-MM-dd')"/>" class="Wdate2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',errDealMode:1})"/>
	                &nbsp;至&nbsp;
	                <input id="jobExpiryDateHid" class='Wdate2' size="15" name="jobSearchVo.jobExpiryDate" value="<s:property value="@com.dotoyo.buildjob.common.util.DateUtil@getFormatDateByFormat(jobSearchVo.jobExpiryDate,'yyyy-MM-dd')"/>" type="text" style="display:none"/>
	                <input id="jobExpiryDate" class='Wdate2' size="12" value="<s:property value="@com.dotoyo.buildjob.common.util.DateUtil@getFormatDateByFormat(jobSearchVo.jobExpiryDate,'yyyy-MM-dd')"/>" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'jobIssuetDate\')}'})"/>
                </li>

                <li>状态:
                	<s:select id="statusHid" list="jobStatusMap" name="jobSearchVo.status" value="jobSearchVo.status" cssStyle="display:none">
                			  </s:select>
                	<s:select id="status" list="jobStatusMap" value="jobSearchVo.status">
                			  </s:select>
                </li>
                <li><a href="#" onclick="
                	document.getElementById('jobNameHid').value=document.getElementById('jobName').value;
                	document.getElementById('jobIssuetDateHid').value=document.getElementById('jobIssuetDate').value;
                	document.getElementById('jobExpiryDateHid').value=document.getElementById('jobExpiryDate').value;
                	document.getElementById('statusHid').value=document.getElementById('status').value;
               		document.getElementById('pageFrom').submit()"><img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" /></a>
                </li>
            </ul>
        </div>
        <div id="content" class="inside-tab">
   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="5%"><input id="selected" name="selected" type="checkbox" /></td>
                <td width="26%" height="40" align="left"><strong>职位名称</strong></td>
                <td width="15%" align="left"><strong>开始时间</strong></td>
                <td width="14%" align="left"><strong>结束时间</strong></td>
                <td width="7%" align="left"><strong>状态</strong></td>
                <td width="17%" align="left"><strong>最后修改时间</strong></td>
                <td width="5%" align="left"><strong>操作</strong></td>
                <td width="7%" align="left">&nbsp;</td>
              </tr>
              <s:iterator value="sysJobInforList">
	              <tr>
	                <td width="5%"><input name="publishedJobs" type="checkbox" value="<s:property value="id"/>" /></td>
	                <td width="26%" height="40" align="left"><s:property value="jobName"/></td>
	                <td width="15%" align="left"><s:property value="getText('format.date',{jobIssuetDate})"/></td>
	                <td width="14%" align="left"><s:property value="getText('format.date',{jobExpiryDate})"/></td>
	                <td width="12%" align="left">
		              	<s:if test="status==1">正常</s:if><s:elseif test="status==0">暂停</s:elseif><s:elseif test="status==2">已过期</s:elseif>
		            </td>
					<td width="17%" align="left"><s:property value="getText('format.date',{lastUpdateTime})"/></td>
	                <td width="7%" align="left" class="inside-links">
	                	<a href="<s:url action="jobSideManagementAction!jobInfoDetail.action">
		                    <s:param name="jobSearchVo.id"><s:property value="id"/></s:param>
		                    <s:param name="jobSearchVo.userId"><s:property value="userId"/></s:param>
		                </s:url>" target="_blank">查看</a></td>
	              </tr>
              </s:iterator>
        </table>
	    </div>

		 <div class="qx">
	         <s:property value="pageInfo.htmlListTableFootInfo" escapeHtml="false"/>
	     </div>
		 <div class="system-botton3">
	        	<ul>
	                <li class="botton1"><a href="javascript:void(0)" title="删除" onclick="delJobs('publishedJobs');return false;">删除</a></li>
	                <li class="botton3"><a href="javascript:void(0)" title="暂停" onclick="suspendJobs('publishedJobs');return false;">暂停</a></li>
	            </ul>
		  </div>
	     <div class="num">
	    	<s:property value="pageInfo.htmlPagingInfo" escapeHtml="false"/>
	     </div>
		 </s:form>
  </div>
</div>
<script type="text/javascript">
function delJobs(checkboxName){
	if(checkStatus(checkboxName)==false) {
		alert('请至少选中一个职位');
	}else{
		if(window.confirm("你确认要进行此操作吗？")){
			document.pageFrom.action='jobSideManagementAction!delJob.action';
			document.pageFrom.method='post';
			document.pageFrom.submit();
		}
	}
}

function suspendJobs(checkboxName){
	if(checkStatus(checkboxName)==false) {
		alert('请至少选中一个职位');
	}else{
		if(window.confirm("你确认要进行此操作吗？")){
		document.pageFrom.action='jobSideManagementAction!suspendJobs.action';
		document.pageFrom.method='post';
		document.pageFrom.submit();
		}
		//$("#content").load(location.href + ' #content');
	}
}
function init(){
	$("#sys_recruit_manage_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
	$("#sys_recruit_position a").addClass("active");
}
</script>
</body>
</html>