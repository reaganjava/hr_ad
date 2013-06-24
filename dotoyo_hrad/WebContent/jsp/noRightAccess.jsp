<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头--系统管理</title>
	<s:include value="/jsp/common.jsp"/>
</head>
<s:include value="/jsp/top.jsp"/>
<body>
<div class="content">
<s:include value="/jsp/left.jsp"/>
   <div class="content-right">
    	<div class="administrator-title"><span>访问权限</span></div>
       <br>
            	<li>你没有访问:[<s:property value="functionName"/>]功能的权限,请与管理员联系</li>
        
  </div>
</div>

</body>
</html>
