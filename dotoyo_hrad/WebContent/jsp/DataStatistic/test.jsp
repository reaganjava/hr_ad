<%@ page contentType="application/msexcel; charset=gbk" pageEncoding="gbk" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
   response.setHeader("Content-disposition","attachment; filename=test1.xls");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Excel档案呈现方式</title>
</head>
<body>
  <table border="1" width="100%">
    <tr>
      <td>姓名</td><td>身份证字号</td><td>生日</td>
    </tr>
    <tr>
      <td>李玟</td><td>N111111111</td><td>1900/11/12</td>
    </tr>
    <tr>
      <td>梁静如</td><td>N222222222</td><td>1923/10/1</td>
    </tr>
    <tr>
      <td>张惠妹</td><td>N333333333</td><td>1934/12/18</td>
    </tr>
  </table>
</body>
</html>





