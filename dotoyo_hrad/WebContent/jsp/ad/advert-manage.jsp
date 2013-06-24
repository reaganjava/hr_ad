<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--广告管理</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<%@ include file="/jsp/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>广告管理</strong></div>
	    <s:form id="pageFrom" name="pageFrom" method="post" action="sysAd!search.action">
	        <div class="inside-search">
	        	<ul>
	                <li>广告公司:&nbsp;
	                	<s:textfield id="companyHid" name="searchdto.company" maxlength="20" size="12" cssStyle="display:none"/>
	                	<input id="company" type="text" value="<s:text name="searchdto.company"/>" size="12" maxlength="20"/>
	                </li>
	                <li>位置:&nbsp;
	                	<s:select id="positionHid" list="all_postions" name="searchdto.position" cssStyle="display:none"/>
	                	<s:select id="position" list="all_postions" value="searchdto.position" cssStyle="width:100px" onchange="COM_setSelectTitle(this,1);"/>
	                </li>
	                <li>时间:&nbsp;
	                	<s:textfield id="startDateHid" name="searchdto.start" cssStyle="display:none"/>
	                	<input id="startDate" type="text" id="startDate" value="<s:text name="searchdto.start"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'endDate\')}'})" size="12" style="height:15px;"/>
	                		&nbsp;至&nbsp;
	                	<s:textfield id="endDateHid" name="searchdto.end" cssStyle="display:none"/>
	                	<input type="text" id="endDate" value="<s:text name="searchdto.end"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'startDate\')}'})" size="12" style="height:15px;"/>
	                </li>
	                <li><a href="#" onclick="
	                	document.getElementById('companyHid').value=document.getElementById('company').value;
	                	document.getElementById('positionHid').value=document.getElementById('position').value;
	                	document.getElementById('startDateHid').value=document.getElementById('startDate').value;
	                	document.getElementById('endDateHid').value=document.getElementById('endDate').value;
	                document.getElementById('pageFrom').submit()"><img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" /></a>
	                </li>
	            </ul>
	        </div>

	        <div class="inside-tab">
	   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	              	<td width="5%"><input id="selected" name="selected" type="checkbox" value=""/></td>
	                <td width="20%" height="40" align="left" nowrap="true"><strong>广告标题&nbsp;</strong></td>
	                <td width="20%" align="left" nowrap="true"><strong>广告公司&nbsp;</strong></td>
	                <td width="5%" align="left" nowrap="true"><strong>索引&nbsp;</strong></td>
	                <td width="20%" align="left" nowrap="true"><strong>位置&nbsp;</strong></td>
	                <td width="20%" align="left" nowrap="true"><strong>广告时段&nbsp;</strong></td>
	                <td width="10%" align="left" nowrap="true"><strong>状态&nbsp;</strong></td>
	                <td width="5%" align="left" nowrap="true"><strong>操作&nbsp;</strong></td>
	              </tr>
	              <s:if test="dtoList!=null">
	              	<s:iterator value="dtoList" var="dto">
		              <tr>
		              	<td width="5%">
		              		<input type="checkbox" name="removeBox" value="<s:text name="id"/>"/>
		              			<input type="hidden"  value="<s:text name="position"/>" name="positionID"  id="positionID"/>
		              	</td>
		                <td width="20%" height="40" align="left" nowrap="true" title="<s:text name="title"/>"><span style="width:70px;float:left" class="index_hid_css"><s:text name="title"/></span>&nbsp;</td>
		                <td width="20%" align="left" nowrap="true" title="<s:text name="company"/>"><span style="width:70px;float:left" class="index_hid_css"><s:text name="company"/></span>&nbsp;</td>
		                <td width="5%" align="left" nowrap="true"><s:text name="adindex"/>&nbsp;</td>
		                <td width="20%" align="left" nowrap="true"><s:text name="positionName"/>&nbsp;</td>
		                <td width="20%" align="left" nowrap="true"><s:date name="startDate" format="yyyy-MM-dd HH:mm"/>&nbsp;-&nbsp;<s:date name="endDate" format="yyyy-MM-dd HH:mm"/>&nbsp;</td>
		                <td width="10%" align="left" nowrap="true">
		                	<s:if test="status == 0">
		                		暂停
		                	</s:if>
		                	<s:else>
		                		打开
		                	</s:else>
		                	&nbsp;
		                	<s:if test="status == 0">
		                		<a href="#" onclick="beforeOpen('<s:text name="id"/>')">打开</a>
		                	</s:if>
		                	&nbsp;
		                </td>
		                <td width="5%" align="left" nowrap="nowrap" class="inside-links">
							<a href="#" onclick="beforeEdit('<s:text name="id"/>')">修改</a>
		                </td>
		              </tr>
	              </s:iterator>
	              </s:if>
	            </table>
			</div>
	        <div class="qx" >
	           <s:property value="pageInfo.htmlListTableFootInfo" escapeHtml="false"/>
	        </div>
	       	<div class="system-botton5">
	        	<ul>
	            	<li class="botton1"><a href="<%=request.getContextPath()%>/sys/sysAd!beforeAdd.action">增加</a></li>
	            	<li class="botton2"><a href="#" onclick="submitForm4ChangeAction(pageFrom,'removeBox','sysAd!stop.action')">暂停</a></li>
	                <li class="botton3"><a href="#" onclick="submitForm4ChangeAction(pageFrom,'removeBox','sysAd!remove.action')">删除</a></li>
	            </ul>
	        </div>
			<div class="num">
			   <s:property value="pageInfo.htmlPagingInfo" escapeHtml="false"/>
			</div>

		</s:form>
       	<div style="display:none">
       		<s:form id="sysAdFormEdit" method="post" action="sysAd!beforeEdit.action">
				<s:hidden name="dto.id" id="edit_id"/>
       		</s:form>
       		<s:form id="sysAdFormOpen" method="post" action="sysAd!open.action">
				<s:hidden name="dto.id" id="open_id"/>
       		</s:form>
       		<s:text name="msg"/>
       	</div>
  </div>
</div>
<script type="text/javascript">
	function beforeEdit(){
		document.getElementById("edit_id").value=arguments[0];
		document.getElementById("sysAdFormEdit").submit();
	}
	function beforeOpen(){
		document.getElementById("open_id").value=arguments[0];
		document.getElementById("sysAdFormOpen").submit();
	}
	function init(){
		$("#sys_advert_manager_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		COM_SetOptionTitle();
		COM_setSelectTitle(document.getElementById("position"),1);
	}

</script>
</body>
</html>
