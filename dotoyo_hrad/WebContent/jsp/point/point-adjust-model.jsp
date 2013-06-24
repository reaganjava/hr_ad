<%@page pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/jsp/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>积分调整</title>

<style type="text/css">
body{font:12px/20px Arial, Helvetica, sans-serif; color:#000; margin:auto; padding:0px; text-align:center;background:#FFF; }
.top{width:960px;height:194px;margin:10px auto;}
.bottom{width:960px;height:64px;margin-bottom:10px; background:url(../images/bottom.png) bottom center no-repeat;}

.line{border-top:1px solid #ccc;border-left:1px solid #ccc;}
.line td{border-right:1px solid #ccc;border-bottom:1px solid #ccc;margin:0px;padding:0px;}

.point{font-size:14px;overflow:hidden; width:100%;margin-top:20px;}
.point p{ width:80%;margin-bottom:8px; text-align:left;font-size:14px;font-weight:bold;}
p img{cursor:pointer;}
.point div{ width:80%; text-align:center;color:#fff;background:#f60;font-size:14px;padding:5px 0px;}
.point div input{border:1px solid #fff;padding:0px 0px;font-size:14px;padding:2px 0px;font-weight:bold;color:#000;}
.point input{color:#000;}
</style>
</head>

<body style="background:#eee;">

<div class="point">
<p >积分调整值<br/>选中的会员积分增加输入的值</p>
<div>
		  <strong>积分调整值：</strong>
          <label></label>
          <label>
               	<s:textfield id="point" maxlength="10" size="16"/>
          </label>
        </div>
</div>
<p style="height:20px;color:#f00;margin:10px auto 5px auto;" id="msg"></p>
<p><img src="/dotoyoBuildJobManage/images/tz.png" onclick="publishRec()"></img></p>

</body>
</html>
<script>
	function publishRec(){
		if(document.getElementById("point").value == ""){
			document.getElementById("msg").innerHTML = "积分调整值不能为空";
			return;
		}
		var reg = /^-?\d+$/;
		if(!reg.test(document.getElementById("point").value)){
			document.getElementById("msg").innerHTML = "积分调整值必须是整数";
			return;
		}
		document.getElementById("msg").innerHTML = "";
		window.returnValue = document.getElementById("point").value;
		window.close();
	}
</script>
