<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--积分管理--积分调整</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<%@ include file="/jsp/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>积分调整</strong></div>
	    <s:form id="pageFrom" name="pageFrom" method="post" action="sysPointAdjust!search.action">
	        <div class="inside-search">
	        	<ul>
	            	<li><strong>用户名</strong>：
	            		<s:textfield id="userNameHid" name="searchdto.userName" size="17" maxLength="20" cssStyle="display:none"/>
	            		<input id="userName" type="text" value="<s:text name="searchdto.userName"/>" size="17" maxlength="20"/>
	            		&nbsp;</li>
	            	<li><strong>状态</strong>：
	            		<s:select id="statusHid" list="statuss" name="searchdto.status" cssStyle="display:none"/>
	            		<s:select id="status" list="statuss" value="searchdto.status"/>
	            	</li>
	                <li><a href="#" onclick="
	                document.getElementById('userNameHid').value=document.getElementById('userName').value;
	                document.getElementById('statusHid').value=document.getElementById('status').value;
	                document.getElementById('pageFrom').submit()"><img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" /></a></li>
	            </ul>
	      	</div>
	        <div class="inside-tab">
	   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	              	<td width="5%"><input id="selected" name="selected" type="checkbox" value=""/></td>
	                <td width="30%" height="40" align="left" nowrap="true"><strong>用户名&nbsp;</strong></td>
	                <td width="30%" align="left" nowrap="true"><strong>积分&nbsp;</strong></td>
	                <td width="15%" align="left" ><strong>状态&nbsp;</strong></td>
	                <td width="20%" align="left" ><strong>最后登录时间&nbsp;</strong></td>
	              </tr>
	              <s:if test="dtoList!=null">
	              	<s:iterator value="dtoList" var="dto">
		              <tr>
		              	<td width="5%">
		              		<input type="checkbox" name="assignBox" value="<s:text name="id"/>"/>
		              	</td>
		                <td width="30%" height="40" align="left" nowrap="true"><s:text name="userName"/>&nbsp;</td>
		                <td width="30%" align="left" nowrap="true"><s:text name="point"/>&nbsp;</td>
		                <td width="15%" align="left"><s:text name="status"/>&nbsp;</td>
		                <td width="20%" align="left"><s:date name="lastLoginDate" format="yyyy-MM-dd HH:mm:ss"/>&nbsp;</td>
		              </tr>
	              </s:iterator>
	              </s:if>
	            </table>
			</div>
	        <div class="qx" >
	           <s:property value="pageInfo.htmlListTableFootInfo" escapeHtml="false"/>
	        </div>
	       	<div class="shanchu"><a href="#" title="积分调整" onclick="openWin()">积分调整</a></div>
			<div class="num">
			   <s:property value="pageInfo.htmlPagingInfo" escapeHtml="false"/>
			</div>
			<s:hidden id="increase_point" name="increasePoint"/>
		</s:form>
       	<div style="display:none">
       		<s:text name="msg"/>
       	</div>
  </div>
</div>
<script type="text/javascript">
	function init(){
		$("#sys_point_setting_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_point_setting_adjust a").addClass("active");
	}
	function openWin()
	{
		var isSelect = 0;
		for(var i = 0; i < document.getElementsByName("assignBox").length;i++){
			if(document.getElementsByName("assignBox")[i].checked){
				isSelect = 1;
				break;
			}
		}
		if(isSelect == 0){
			alert("至少选择一条数据");
			return;
		}
		var returnS = window.showModalDialog("<%=request.getContextPath()%>/jsp/point/point-adjust-model.jsp","contactInfo","dialogHeight: 200px; dialogWidth: 400px;center=yes;help=no;location=no;");
		if(returnS != "" && returnS != null){
			document.getElementById("increase_point").value = returnS;
			submitForm4ChangeAction(document.getElementById("pageFrom"),"assignBox","sysPointAdjust!assign.action");
		}
	}
</script>
</body>
</html>
