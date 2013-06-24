<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--首页</title>
<link href="css/index-style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="/jsp/common/head.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/javascripts/common/select.js"></script>
<script src="<%=request.getContextPath()%>/javascripts/common/region.js"></script>
<div class="banner-line">
	<s:form id="userLogonForm" name="userLogonForm" method="post" action="logonAction!logon.action" namespace="/common">
		<div class="banner-box">
			<div class="banner"><img src="<%=request.getContextPath()%>/images/banner.png" width="682" height="220" /></div>
			<div class="login">
				<div class="login-title"></div>
				<div class="login-input">
					<input id="account" name="userInfo.userName" type="text" value="用户名/注册邮箱" onfocus="accountOnFocus(this)" onblur="accountOnBlur(this)"/>
				</div>
				<div class="login-input">
					<input id="passwordDisp" name="passwordDisp" type="text" value="密码" onfocus="showSerect(this)"/> 
					<input id="password" name="userInfo.password" type="password"" value="" onfocus="this.select()" onblur="passwordOnBlur(this)" style="display:none;"/>
				</div>
				<div class="yzm">
					<div class="yzm-left">
						<input id="captcha" name="captcha" type="text" value="验证码" onfocus="captchaOnFocus(this)" onblur="captchaOnBlur(this)"/>
					</div>
					<div class="yzm-pic">
						<img src="<%=request.getContextPath()%>/jcaptcha" onclick="changeImage(this)" alt="click to refresh" style="cursor: pointer;" id="kaptchaImage" width="80" height="20" />
					</div>
				</div>
				<div class="login-botton">
					<div class="l-botton">
						<input type="image" src="<%=request.getContextPath()%>/images/login-botton.png" width="101" height="29" />
					</div>
					<div class="l-font"><a href="<%=request.getContextPath()%>/homePage/homePageAction!toUserRegister.action">还没有帐号？</a></div>
				</div>
				<div class="login-wz"><a href="#">忘记密码？</a>|<a href="#">猎头服务</a>|<a href="#"><img src="<%=request.getContextPath()%>/images/bz-icon.jpg" width="17" height="13" />&nbsp;<strong>我的博站</strong></a></div>
			</div>
		</div>
	</s:form>
</div>
<div class="search-box">
	<ul>
    	<li class="search1">
        	<img src="<%=request.getContextPath()%>/images/search-down.png" width="21" height="22" onclick="menuClick('#province', '#searchDown2')" />
        	<input name="input" id="province" type="text" value="选择省份"/>
        	<input name="" id="hiddenProvince" type="hidden" value=""/>
        </li>
        <li class="search1">
        	<img src="<%=request.getContextPath()%>/images/search-down.png" width="21" height="22" onclick="loadData('citySearch')" />
        	<input id="citySearch" name="input" type="text" value="选择城市"/>
        	<input id="hiddenCity" name="" type="text" value=""/>
        </li>
        <li class="search1">
        	<img src="<%=request.getContextPath()%>/images/search-down.png" width="21" height="22" onclick="menuClick('#functionText', '#functionDiv')" />
        	<input id="functionText" name="functionText" type="text" value="选择职能"/>
        	<input id="functionCode" name="" value=""/>
        </li>
        <li class="search1">
	        <img src="<%=request.getContextPath()%>/images/search-down.png" width="21" height="22" onclick="menuClick('#border4', '#searchDown4')" />
	        <input id="border4" name="input" type="text" value="选择日期"/>
        </li>
        <li class="search2">
        	<input name="input" type="text" value="输入关键字" onfocus="this.select()"/>
        </li>
        <li class="search-botton">
        	<a href="#">
        		<img src="<%=request.getContextPath()%>/images/search-botton.png" width="71" height="29" />
        	</a>
        </li>
        <li class="gaoji">
        	<a href="#">高级搜索</a>
        </li>
    </ul>
</div>
<!--搜索下拉菜单~绝对定位~-->

<!--搜索下拉菜单~绝对定位~-->
<div class="search-down" id="showMenuID">
    <div class="search-down1" id="searchDown2" onmouseleave="menuHide(this)" style="display:none;">
    	<ul>
    		<s:iterator id="provinceList" value="provinceList">
                <li><a href="#" id="<s:property value="code" />" onclick="setValue(this,'province','hiddenProvince')"><s:property value="name"/></a></li>
            </s:iterator>
        </ul>
    </div>
    <div class="search-down1" id="searchDown3" onmouseleave="menuHide(this)" style="display:none;">
    	<ul id="cityId">
        </ul>
    </div>
     <div class="search-down2" id="functionDiv" onmouseleave="menuHide(this)" style="display:none;">
        <ul>
        	<s:iterator id="functionList" value="functionList">
                <li><a href="#" id="<s:property value="code" />" onclick="setValue(this,'functionText','functionCode')"><s:property value="name"/></a></li>
            </s:iterator>
        </ul>
    </div>
    <div class="search-down3" id="searchDown4" onmouseleave="menuHide(this)" style="display:none;">
        <ul>
            <li><a href="#">三天内</a></li>
            <li><a href="#">两周内</a></li>
            <li><a href="#">一个月</a></li>
            <li><a href="#">三个月</a></li>
            <li><a href="#">一年</a></li>
            <li><a href="#">一年至三年</a></li>
            <li><a href="#">十年以上</a></li>
        </ul>
    </div>
</div>
<!---->
<div class="mid">
	<div class="diqu">
    	<h3></h3>
        <ul>
            <li><a href="#">北京</a></li>
            <li><a href="#">上海</a></li>
            <li><a href="#">广州</a></li>
            <li><a href="#">深圳</a></li>
            <li><a href="#">武汉</a></li>
            <li><a href="#">西安</a></li>
            <li><a href="#">杭州</a></li>
            <li><a href="#">南京</a></li>
            <li><a href="#">成都</a></li>
            <li><a href="#">重庆</a></li>
            <li><a href="#">东莞</a></li>
            <li><a href="#">大连</a></li>
            <li><a href="#">沈阳</a></li>
            <li><a href="#">苏州</a></li>
            <li><a href="#">昆明</a></li>
            <li><a href="#">长沙</a></li>
            <li><a href="#">合肥</a></li>
            <li><a href="#">宁波</a></li>
            <li><a href="#">郑州</a></li>
            <li><a href="#">哈尔滨</a></li>
            <li><a href="#">天津</a></li>
            <li><a href="#">青岛</a></li>
            <li><a href="#">济南</a></li>
            <li><a href="#">长春</a></li>
            <li><a href="#">福州</a></li>
            <li><a href="#">石家庄</a></li>
            <li><a href="#">无锡</a></li>
            <li><a href="#">常州</a></li>
            <li><a href="#">佛山</a></li>
            <li><a href="#">中山</a></li>
            <li><a href="#">珠海</a></li>
        </ul>
    </div>
    <div class="hot-job">
    	<h3></h3>
        <ul>              
            <li><a href="#">监理工程师</a></li>
            <li><a href="#">土木结构</a></li>
            <li><a href="#">一级建造师</a></li>
            <li><a href="#">造价工程师</a></li>
            <li><a href="#">一级建筑师</a></li>
            <li><a href="#">规划师</a></li>
            <li><a href="#">一级结构师证</a></li>
            <li><a href="#">土木结构</a></li>
            <li><a href="#">一级建造师</a></li>
            <li><a href="#">造价工程师</a></li>
            <li><a href="#">监理工程师</a></li>
            <li><a href="#">一级建筑师</a></li>
            <li><a href="#">造价师</a></li>
            <li><a href="#">土木结构</a></li>
            <li><a href="#">监理工程师</a></li>
            <li><a href="#">一级结构师</a></li>
            <li><a href="#">土木结构</a></li>
            <li><a href="#">规划师</a></li>
            <li><a href="#">造价师</a></li>
        </ul>
  </div>
</div>
<jsp:include page="/jsp/common/bottom.jsp"></jsp:include>
</body>
<script type="text/javascript">
	changeMenu(document.getElementById('menu01'));
	lastIndex = document.getElementById('menu01');
	function changeImage(obj){
		obj.src="<%=request.getContextPath()%>"+"/jcaptcha?"+Math.random();
	}
	function showSerect(obj){
		$(obj).hide();
		$("#password").show();
		$("#password").focus();
	}
	function accountOnFocus(obj){
		var text = obj.value;
		if("用户名/注册邮箱"==text){
			obj.value="";
		}
	}
	function accountOnBlur(obj){
		var text = obj.value;
		if(""==text){
			obj.value="用户名/注册邮箱";
		}
	}
	function captchaOnFocus(obj){
		var text = obj.value;
		if("验证码"==text){
			obj.value="";
		}
	}
	function captchaOnBlur(obj){
		var text = obj.value;
		if(""==text){
			obj.value="验证码";
		}
	}
	function passwordOnBlur(obj){
		var text = obj.value;
		if(""==text){
			$(obj).hide();
			$("#passwordDisp").show();
		}
	}
</script>
</html>