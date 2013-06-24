<%@page pageEncoding="utf-8"%>
<%@ include file="/jsp/systemManage/top.jsp"%>
<div class="content">
<%@ include file="/jsp/systemManage/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>服务名称设置</strong></div>
    	<s:form id="pageFrom" name="pageFrom" method="post" action="serviceNameMainAction!search.action">
        <div class="inside-search">
        	<ul>
            	<li><strong>服务名称</strong>：<s:textfield name="searchdto.serviceName" size="17" maxLength="20"/></li>
                <li><a href="#" onclick="document.getElementsByName('pageFrom')[0].submit()"><img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" /></a></li>
            </ul>
      	</div>

        <div class="inside-tab">
   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="8%" height="40" align="left" class="tab-padding"><strong>编码</strong></td>
                <td width="17%" align="left"><strong>服务名称    </strong></td>
                <td width="14%" align="left"><strong>可使用天数</strong></td>
                <td width="33%" align="left"><strong>服务简介</strong></td>
                <td width="13%"  align="left"><strong>操作人</strong></td>
                <td width="15%" align="left"><strong>操作</strong></td>
              </tr>
              <s:if test="dtoList!=null">
              	<s:iterator value="dtoList" var="dto">
	              <tr>
	                <td width="8%" height="40" align="left" class="tab-padding" nowrap="nowrap"><s:text name="serviceCode"/>&nbsp;</td>
	                <td width="17%" align="left"><s:text name="serviceName"/>&nbsp;</td>
	                <td width="14%" align="left"><s:text name="usableDays"/>&nbsp;</td>
	                <td width="33%" align="left" title="<s:text name="serviceIntro"/>"><span style="width:200px;float:left" class="index_hid_css"><s:text name="serviceIntro"/></span>&nbsp;</td>
	                <td width="13%" align="left"><s:text name="operatorName"/>&nbsp;</td>
	                <td width="15%" align="left" class="inside-links">
	                	<a href="#" onclick="beforeEdit('<s:text name="serviceCode"/>','<s:text name="serviceName"/>','<s:text name="usableDays"/>','<s:text name="serviceIntro"/>')">修改</a>
	                	&nbsp;&nbsp;
	                	<a href="#" onclick="remove('<s:text name="serviceCode"/>')">删除</a>
	                </td>
	              </tr>
              	</s:iterator>
              </s:if>
            </table>
    	</div>
        <div class="qx" >
         <s:property value="pageInfo.htmlListTableFootInfoNoAllCheck" escapeHtml="false"/>
        </div>
       	<div class="shanchu"><a href="<%=request.getContextPath()%>/sysIncrement/serviceNameMainAction!beforeAdd.action">增加</a></div>
		<div class="num">
		   <s:property value="pageInfo.htmlPagingInfo" escapeHtml="false"/>
		</div>
		</s:form>
       	<div style="display:none">
       		<s:form name="sysIncrementServiceNameFormEdit" method="post" action="serviceNameMainAction!beforeEdit.action">
				<s:hidden name="dto.serviceCode"/>
				<s:hidden name="dto.serviceName"/>
				<s:hidden name="dto.usableDays"/>
				<s:hidden name="dto.serviceIntro"/>
       		</s:form>
       		<s:form name="sysIncrementServiceNameFormRemove" method="post" action="serviceNameMainAction!remove.action">
				<s:hidden name="dto.serviceCode"/>
       		</s:form>
       		<s:text name="msg"/>
       	</div>
  </div>
</div>
<script type="text/javascript">
	function beforeEdit(){
		document.getElementsByName("dto.serviceCode")[0].value=arguments[0];
		document.getElementsByName("dto.serviceName")[0].value=arguments[1];
		document.getElementsByName("dto.usableDays")[0].value=arguments[2];
		document.getElementsByName("dto.serviceIntro")[0].value=arguments[3];
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
	}
</script>
</body>
</html>
