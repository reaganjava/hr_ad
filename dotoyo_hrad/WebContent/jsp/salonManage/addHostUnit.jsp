<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头-增加主办单位</title>
	<s:include value="/jsp/common.jsp"/>
</head>
<s:include value="/jsp/top.jsp"/>
<!--以上为头部分-->
<!--中间部分-->
<div class="content">
<s:include value="/jsp/left.jsp"/>
	<s:form id="pageFrom" name="pageFrom" method="post" action="salonAdminAction!addHostUnit.action" enctype ="multipart/form-data">
   <div class="content-right">
    	<div class="administrator-title"><strong>增加主办单位</strong></div>
    	<s:actionmessage cssStyle="color:red"/>
        <div class="inside-tab">
   	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="inside-tab">
              <tr>
                <td width="29%" height="40" align="right"><em>*</em><strong>主办单位名称</strong>：</td>
                <td align="left" class="tab-input"><s:textfield id="unitName" name="hostUnitDto.unitName" cssClass="validate[custom[nullCheckS],custom[filteringSpecialChar]]" maxlength="50"/></td>
              </tr>

              <tr>
                <td width="29%" height="40" align="right"><em>*</em><strong>图片</strong>：</td>
                <td align="left" class="tab-input"><s:file name="uploadPicFile" cssClass="validate[custom[nullCheckS],custom[isImg]]"/></td>
              </tr>
              <tr>
                <td width="29%" height="40" align="right"><em>*</em><strong>顺序号</strong>：</td>
                <td align="left" class="tab-input"><s:textfield id="intOrder" name="hostUnitDto.intOrder" cssClass="validate[required,custom[onlyNumber]]" size="10" maxlength="4"/></td>
              </tr>
              <tr>
                <td width="29%" height="40" align="right"><em>*</em><strong>链接</strong>：</td>
                <td align="left" class="tab-input"><s:textfield id="linkURL" name="hostUnitDto.linkURL" cssClass="validate[custom[url]]" size="40" maxlength="50"/></td>
              </tr>
              <tr>
                <td width="29%" height="40" align="right"><em>*</em><strong>单位介绍</strong>：</td>
                <td align="left" class="tab-input"><s:textarea name="hostUnitDto.introduction" cssClass="validate[custom[nullCheckS],length[1,200]]"/></td>
              </tr>
            </table>
  </div>

       		<div class="system-botton">
        	<ul>
	            	<li class="botton1" style="margin-left:200px"><a href="#" onclick="$('#pageFrom').submit()" title="提交">提交</a></li>
	                <li class="botton3"><a href="<%=request.getContextPath()%>/sys/salonAdminAction!cancel.action" title="返回">返回</a></li>
            </ul>
		</div>

  </div>
</s:form>
</div>

</body>
</html>
<script type="text/javascript">
	function init(){
		$("#salon_manager").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#salon_manager_4 a").addClass("active");
	}
</script>
