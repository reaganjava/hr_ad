<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--会员管理</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<body>
<div class="content">
<%@ include file="/jsp/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>会员管理</strong></div>
	    <s:form id="pageFrom" name="pageFrom" method="post" action="sysMember!userSearch.action">
	        <div class="inside-search">
	        	<ul>
	            	<li><strong>用户名</strong>：
	            		<s:textfield id="userNameHid" name="userSearchdto.userName" size="17" maxLength="20" cssStyle="display:none"/>
	            		<input type="text" id="userName" value="<s:text name="userSearchdto.userName"/>" size="12" maxlength="20"/>
	            		&nbsp;</li>
	            	<li><strong>分配角色</strong>：
	            		<s:textfield id="rolenamesHid" name="userSearchdto.rolenames" size="17" maxLength="20" cssStyle="display:none"/>
	            		<input id="rolenames" type="text" id="userName" value="<s:text name="userSearchdto.rolenames"/>" size="12" maxlength="20"/>
	            	&nbsp;</li>
	            	<li><strong>用户类型</strong>:
	            		<s:select id="userTypeHid" list="userType" name="userSearchdto.userType" cssStyle="display:none"/>
	            		<s:select id="userType" list="userType" value="userSearchdto.userType"/>
	            	</li>
	            	<li><strong>状态</strong>:
	            		<s:select id="statusHid" list="statuss" name="userSearchdto.status" cssStyle="display:none"/>
	            		<s:select id="status" list="statuss" value="userSearchdto.status"/>
	            	</li>
	            	<s:hidden id="registerDate" name="userSearchdto.registerDate"></s:hidden>
	                <li><a href="javascript:void(0)" onclick="
	                document.getElementById('userNameHid').value=document.getElementById('userName').value;
	                document.getElementById('rolenamesHid').value=document.getElementById('rolenames').value;
	                document.getElementById('userTypeHid').value=document.getElementById('userType').value;
	                document.getElementById('statusHid').value=document.getElementById('status').value;
	                document.getElementById('pageFrom').submit();
	                return false;"><img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" /></a></li>
	            </ul>
	      	</div>
	        <div class="inside-tab">
	   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	                <td width="16%" height="40" align="left" ><strong>用户名</strong></td>
	                <td width="10%" align="left"><strong>用户类型</strong></td>
	                <td width="6%" align="left"><strong>积分</strong></td>
	                <td width="9%" align="left"><strong>分配角色</strong></td>
	                <td width="5%" align="left"><strong>状态</strong></td>
	                <td width="14%" align="left"><strong>注册日期</strong></td>
	                <td width="15%" align="left"><strong>最后登录时间</strong></td>
	                <td width="6%" align="left"><strong>操作</strong></td>
	                <td width="9%" align="left">&nbsp;</td>
	                <td width="11%" align="left">&nbsp;</td>
	              </tr>
	              <s:if test="userDtoList!=null">
	              	<s:iterator value="userDtoList" var="dto">
		              <tr>
		                <td width="16%" height="40" align="left" nowrap="true" title="<s:text name="userName"/>"><span style="width:130px;float:left" class="index_hid_css"><s:text name="userName"/></span></td>
		                <td width="10%" align="left">
			                <s:if test="userType==0">个人</s:if>
			                <s:elseif test="userType==1">企业</s:elseif>
			            </td>
		                <td width="6%" align="left" nowrap="true"><s:text name="point"/>&nbsp;</td>
		                <td width="9%" align="left" title="<s:text name="rolenames"/>"> <span style="width:130px;float:left" class="index_hid_css"><s:text name="rolenames"/></span></td>
		                <td width="5%" align="left"><s:text name="status"/></td>
		                <td width="14%" align="left" nowrap="true"><s:date name="registerDate" format="yyyy-MM-dd"/></td>
		                <td width="15%" align="left" nowrap="true"><s:date name="lastLoginDate" format="yyyy-MM-dd"/></td>
		                <td width="6%" align="left" nowrap="true" class="inside-links"><a href="#" onclick="beforeEdit('<s:text name="id"/>')">修改</a></td>
		                <td width="9%" align="left"><a href="#" onclick="beforeAssign('<s:text name="id"/>')">分配角色</a></td>
	                	<td width="11%" align="left"><a href="#" onclick="beforeAssignFun('<s:text name="id"/>')">分配功能点</a></td>
		              </tr>
	              </s:iterator>
	              </s:if>
	            </table>
			</div>
	        <div class="qx" >
	           <s:property value="userPageInfo.htmlListTableFootInfoNoAllCheck" escapeHtml="false"/>
	        </div>
			<div class="num">
			   <s:property value="userPageInfo.htmlPagingInfo" escapeHtml="false"/>
			</div>
		</s:form>
       	<div style="display:none">
       		<s:form id="sysUserFormEdit" method="post" action="sysMember!beforeEdit.action">
				<s:hidden name="userdto.id" id="edit_id"/>
       		</s:form>
       		<s:form id="sysUserFormAssign" method="post" action="sysMember!beforeAssign.action">
				<s:hidden name="userdto.id" id="assign_id"/>
       		</s:form>
       		<s:form id="sysUserFormAssignFun" method="post" action="sysMember!beforeAssignFunctions.action">
				<s:hidden name="funSearchdto.userid" id="assignfun_id"/>
       		</s:form>
       		<s:text name="msg"/>
       	</div>
  </div>
</div>
<script type="text/javascript">
	function beforeAssign(){
		document.getElementById("assign_id").value=arguments[0];
		document.getElementById("sysUserFormAssign").submit();
	}

	function beforeEdit(){
		document.getElementById("edit_id").value=arguments[0];
		document.getElementsByName("sysUserFormEdit")[0].submit();
	}
	function beforeAssignFun(){
		document.getElementById("assignfun_id").value=arguments[0];
		document.getElementById("sysUserFormAssignFun").submit();
	}
	function init(){
		$("#sys_member_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_member_manage a").addClass("active");
	}
</script>
</body>
</html>
