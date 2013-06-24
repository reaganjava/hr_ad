<%@ page contentType="application/msexcel; charset=gbk" pageEncoding="gbk" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
   response.setHeader("Content-disposition","attachment; filename=test1.xls");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Save as Excel</title>
</head>
<body>
  <table border="1" width="100%" style="display:none;">
    <tr>
      <td>统计项</td>
      <td>数量</td>
    </tr>
    <tr>
      <td>个人提交证书数量</td>
      <td><s:property value="personalCertAmount"/></td>
    </tr>
    <tr>
      <td>企业证书需求数量</td>
      <td><s:property value="certNeedsAmount"/></td>
    </tr>
    <tr>
      <td>总计</td>
      <td><s:property value="certNeedsAmount+personalCertAmount"/></td>
    </tr>
  </table>
</body>
</html>