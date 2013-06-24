<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--增值服务设置--套餐名称设置</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<%@ include file="/jsp/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>套餐名称设置</strong></div>
    	<s:form id="pageFrom" name="pageFrom" method="post" action="serviceNameMainAction!search.action">
        <div class="inside-search">
        	<s:hidden name="searchdto.serviceName" id="serviceNameHid"/>
        	<ul>
            	<li><strong>套餐名称</strong>：<input id="serviceName" type="text" size="17" maxlength="20" value="<s:text name="searchdto.serviceName"/>"/> </li>
                <li><a href="#" onclick="document.getElementById('serviceNameHid').value=document.getElementById('serviceName').value;document.getElementsByName('pageFrom')[0].submit()"><img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" /></a></li>
            </ul>
      	</div>

        <div class="inside-tab">
   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="15%" height="40" align="left" class="tab-padding"><strong>编码</strong></td>
                <td width="20%" align="left"><strong>套餐名称    </strong></td>
                <td width="15%" align="left"><strong>套餐类型    </strong></td>
                <td width="15%" align="left"><strong>可使用天数</strong></td>
                <td width="10%" align="left"><strong>价格</strong></td>
                <td width="15%"  align="left"><strong>操作人</strong></td>
                <td width="5%" align="left"><strong>操作</strong></td>
                <td width="5%">&nbsp;</td>
              </tr>
              <s:if test="dtoList!=null">
              	<s:iterator value="dtoList" var="dto">
	              <tr>
	                <td width="15%" height="40" align="left" class="tab-padding" nowrap="nowrap"><s:text name="serviceCode"/>&nbsp;</td>
	                <td width="20%" align="left"><s:text name="serviceName"/>&nbsp;</td>
	                <td width="15%" align="left">
	                	<s:if test='serviceType=="0"'>
	                		个人套餐
	                	</s:if>
	                	<s:elseif test='serviceType=="1"'>
	                		企业套餐
	                	</s:elseif>
	                </td>
	                <td width="15%" align="left"><s:text name="usableDays"/>&nbsp;</td>
	                <td width="10%" align="left">
	                	<s:if test='priceStatus=="0"'>
	                		面议
	                	</s:if>
	                	<s:else>
	                		<s:text name="price"/>&nbsp;
	                	</s:else>
	                </td>
	                <td width="15%" align="left"><s:text name="operatorName"/>&nbsp;</td>
	                <td width="5%" align="left" class="inside-links">
	                	<a href="#" onclick="beforeEdit('<s:text name="serviceCode"/>')">修改</a>

	                </td>
	                <td width="5%"><a href="#" onclick="remove('<s:text name="serviceCode"/>')">删除</a></td>
	              </tr>
              	</s:iterator>
              </s:if>
            </table>
    	</div>
        <div class="qx" >
         <s:property value="pageInfo.htmlListTableFootInfoNoAllCheck" escapeHtml="false"/>
        </div>
        <div class="shanchu"><a href="<%=request.getContextPath()%>/sys/serviceNameMainAction!beforeAdd.action">增加</a></div>
		<div class="num">
		   <s:property value="pageInfo.htmlPagingInfo" escapeHtml="false"/>
		</div>
		</s:form>
       	<div style="display:none">
       		<s:form name="sysIncrementServiceNameFormEdit" method="post" action="serviceNameMainAction!beforeEdit.action">
				<s:hidden name="dto.serviceCode"/>
       		</s:form>
       		<s:form name="sysIncrementServiceNameFormRemove" method="post" action="serviceNameMainAction!remove.action">
				<s:hidden name="dto.serviceCode"/>
       		</s:form>
       	</div>
  </div>
</div>
<script type="text/javascript">
	function beforeEdit(){
		document.getElementsByName("dto.serviceCode")[0].value=arguments[0];
		document.getElementsByName("sysIncrementServiceNameFormEdit")[0].submit();
	}
	function remove(){
		if(confirm("你确定要删除这条服务名称吗?")){
			document.getElementsByName("dto.serviceCode")[1].value=arguments[0];
			document.getElementsByName("sysIncrementServiceNameFormRemove")[0].submit();
		}

	}
	function init(){
		$("#sys_increment_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_increment_ul_sysname a").addClass("active");
		alertMsg('<s:text name="msg"/>');
	}

</script>
</body>
</html>
