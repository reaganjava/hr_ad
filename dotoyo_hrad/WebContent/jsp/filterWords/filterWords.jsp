<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--过滤词管理</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<%@ include file="/jsp/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>过滤词管理</strong></div>
	    <s:form id="pageFrom" name="pageFrom" method="post" action="sysFilterWord!search.action">
	        <div class="inside-search">
	        	<ul>
	            	<li><strong>过滤词</strong>：
	            		<s:hidden id="wordsHid" name="searchdto.words"/>
	            		<input type="text" id="words" value="<s:text name="searchdto.words"/>" size="17" maxlength="20"/>
	            	</li>
	                <li><a href="#" onclick="
	                document.getElementById('wordsHid').value=document.getElementById('words').value;
	                document.getElementById('pageFrom').submit()"><img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" /></a></li>
	            </ul>
	      	</div>
	        <div class="inside-tab">
	   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	              	<td width="5%"><input id="selected" name="selected" type="checkbox" value=""/></td>
	                <td width="30%" height="40" align="left" nowrap="true"><strong>过滤词&nbsp;</strong></td>
	                <td width="30%" align="left" nowrap="true"><strong>增加人&nbsp;</strong></td>
	                <td width="20%" align="left" nowrap="true"><strong>最后更新日期&nbsp;</strong></td>
	                <td width="15%" align="left" ><strong>操作&nbsp;</strong></td>
	              </tr>
	              <s:if test="dtoList!=null">
	              	<s:iterator value="dtoList" var="dto">
		              <tr>
		              	<td width="5%">
		              		<input type="checkbox" name="removeBox" value="<s:text name="id"/>"/>
		              	</td>
		                <td width="30%" height="40" align="left" nowrap="true"><s:text name="words"/>&nbsp;</td>
		                <td width="30%" align="left" nowrap="true"><s:text name="operatorName"/>&nbsp;</td>
		                <td width="20%" align="left" nowrap="true"><s:date name="last_edit_date" format="yyyy-MM-dd HH:mm:ss"/>&nbsp;</td>
		                <td width="15%" align="left" class="inside-links">
		                	<a href="#" onclick="beforeEdit('<s:text name="id"/>')">修改</a>&nbsp;
		                </td>
		              </tr>
	              </s:iterator>
	              </s:if>
	            </table>
			</div>
	        <div class="qx" >
	           <s:property value="pageInfo.htmlListTableFootInfo" escapeHtml="false"/>
	        </div>
	       	<div class="system-botton3">
	        	<ul>
	            	<li class="botton1"><a href="<%=request.getContextPath()%>/sys/sysFilterWord!beforeAdd.action">增加</a></li>
	                <li class="botton3"><a href="#" onclick="submitForm4ChangeAction(pageFrom,'removeBox','sysFilterWord!remove.action')">删除</a></li>
	            </ul>
	        </div>
			<div class="num">
			   <s:property value="pageInfo.htmlPagingInfo" escapeHtml="false"/>
			</div>
		</s:form>
       	<div style="display:none">
       		<s:form id="sysFilterWordFormEdit" method="post" action="sysFilterWord!beforeEdit.action">
				<s:hidden name="dto.id" id="edit_id"/>
       		</s:form>
       		<s:text name="msg"/>
       	</div>
  </div>
</div>
<script type="text/javascript">
	function beforeEdit(){
		document.getElementById("edit_id").value=arguments[0];
		document.getElementsByName("sysFilterWordFormEdit")[0].submit();
	}
	function init(){
		$("#sys_filter_words_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
	}
</script>
</body>
</html>
