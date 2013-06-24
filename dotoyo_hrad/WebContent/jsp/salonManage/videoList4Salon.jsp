<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.dotoyo.buildjob.common.constant.ApplicationConstant"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头-线下沙龙视频</title>
	<s:include value="/jsp/common.jsp"/>
</head>
<s:include value="/jsp/top.jsp"/>
<!--以上为头部分-->
<!--中间部分-->
<div class="content">
<s:include value="/jsp/left.jsp"/>
 <div class="content-right">
    	<div class="administrator-title"><strong>沙龙视频</strong></div>
    	<div align="left"><s:actionmessage cssStyle="color:blue"/></div>
       <s:form id="deleteForm" name="deleteForm" method="POST" action="salonAdminAction!deleteMedia.action">
       <s:hidden name="salonMediaDto.mediaType" id="salonMediaDto.mediaType"/>
  	   <s:hidden name="salonMediaDto.salonId" id="salonMediaDto.salonId"/>
        <div class="geren-video">
   	    	 <ul>
   	    	 <s:iterator value="mediaList">
                 <li title="<s:property value="subject"/>">
                 	<a onclick="showDiv('<s:property value="mediaName"/>','<s:property value="uploadFilePath"/>/<s:property value="mediaName"/>')" href="#" title="点击播放">
	            		<s:if test="videoCoverPic == null || videoCoverPic == ''">
	            			<img src="<%=request.getContextPath()%>/images/sp2.png" width="120" height="90" />
	            		</s:if>
	            		<s:else>
	            			<img src="<s:property value="uploadFilePath"/>/<s:property value="videoCoverPic"/>" width="120" height="90" />
	            		</s:else>
                 	</a>
                 	<br />
                  	<span class="index_hid_css" style="width:110px;float:left">
                  		<input id="arrayIds" name="arrayIds" type="checkbox" value="<s:property value="id"/>"/>
                  		<a onclick="showDiv('<s:property value="mediaName"/>','<s:property value="uploadFilePath"/>/<s:property value="mediaName"/>')" href="#" title="点击播放">
                  			<s:property value="subject"/>
                  		</a>
                  	</span>
                  	<p>状态:<s:property value="status"/></p>
                  	<p>是否经典:<s:property value="isExcellent"/></p>
                 </li>
             </s:iterator>
             </ul>
		</div>
	</s:form>

	<s:form  id="pageFrom" name="pageFrom" method="POST" action="salonAdminAction!toMediaListBySalonPage.action">
  	 <s:hidden name="salonMediaDto.mediaType" id="salonMediaDto.mediaType"/>
  	 <s:hidden name="salonMediaDto.salonId" id="salonMediaDto.salonId"/>
       <div class="qx">
           <s:property value="pageInfo.htmlListTableFootInfo" escapeHtml="false"/>
       </div>
       	<div class="system-botton">
        	<ul>
                <li class="botton1"><a href="#" onclick="submitFormChooseCheck(deleteForm,'arrayIds')" title="删除">删除</a></li>
                <li class="botton2"><a href="#" onclick="submitForm4ChangeAction(deleteForm,'arrayIds','salonAdminAction!autiMedia.action?audiFlag=<%=ApplicationConstant.STATUS_PASSED%>')" title="审核通过">审核通过</a></li>
                <li class="botton3"><a href="#" onclick="submitForm4ChangeAction(deleteForm,'arrayIds','salonAdminAction!autiMedia.action?audiFlag=<%=ApplicationConstant.STATUS_NOT_PASS%>')" title="审核不通过">审核不通过</a></li>
                <li class="botton3"><a href="#" onclick="submitForm4ChangeAction(deleteForm,'arrayIds','salonAdminAction!setMedia.action')" title="设置为经典">设置为经典</a></li>
                <li class="botton3"><a href="salonAdminAction!toMediaListPage.action?salonMediaDto.mediaType=<%=ApplicationConstant.MEDIA_TYPE_VIDEO%>" title="返回">返回</a></li>
            </ul>
		</div>
        <div class="num">
        	<s:property value="pageInfo.htmlPagingInfo" escapeHtml="false"/>
       	</div>
     </s:form>
  </div>

</div>
<div id="boxBG" style="position:absolute; z-index:1000; filter:alpha(opacity=60);opacity:0.6; background-color:#000; width:100%;  top:0px; left:0px; display:none;"></div>
<div id="box" class="sp-video" style=" position:absolute; left:50%; margin-left:-250px; z-index:2000; display:none;">
	<div class="sping" id="play">
	</div>
    <div class="sping-bar">
    	<ul>
        	<li class="sping-bar2" onclick="addTime()" title="点击延长关闭时间">延长时间</li>
            <li class="sping-bar1"><a title="剩余关闭时间" href="#" onclick="
                if(document.getElementById('statusx').innerHTML=='剩余'){
            		document.getElementById('statusx').innerHTML='暂停中...';this.title='暂停中...';isStop=true;
            		document.getElementById('time').style.display = 'none';
            		document.getElementById('sec').style.display = 'none';
            		this.parentNode.className='sping-bar3';
            	}else{
            		document.getElementById('statusx').innerHTML='剩余';this.title='剩余关闭时间';isStop=false;
            		document.getElementById('time').style.display = '';
            		document.getElementById('sec').style.display = '';
            		this.parentNode.className='sping-bar1';
            	}"><span id="statusx">剩余</span><span id="time"></span><span id="sec">秒</span></a></li>
            <li class="sping-bar3" onclick="mediaStop();" title="点击关闭">关闭</li>
        </ul>
    </div>
</div>
</body>
</html>
<script>

function showDiv(){
	document.getElementById("box").style.display="block";
	document.getElementById("boxBG").style.display="block";
	mediaPlay(arguments[0],arguments[1]);
}
function closeDiv(){
	document.getElementById("box").style.display="none";
	document.getElementById("boxBG").style.display="none";
}
function lod(){
	var Dh=parseInt(document.documentElement.clientHeight);
	var Dw=parseInt(document.documentElement.clientWidth);
	document.getElementById("boxBG").style.height=Dh+'px';
	document.getElementById("boxBG").style.width=Dw+'px';
	document.getElementById("box").style.top=(Dh-300)/2+parseInt(document.documentElement.scrollTop)+'px';
	document.getElementById("boxBG").style.top=parseInt(document.documentElement.scrollTop)+'px';
}
window.onscroll=lod;
	var t;
	var stopFlag = true;
	var isStop = false;
	var stockInterval;
	var startFlag = false;
	function mediaPlay(){
		var html = "<embed id=\"em\" height=\"300\" width=\"450\" alt=\""+arguments[0]+"\" src=\""+arguments[1]+"\">";
		html += "<br/><p style=\"width:100%;\">";
		html += "</p>";
		document.getElementById("play").innerHTML= html;
		t=30;
		document.getElementById("time").innerHTML = t;
		stopFlag = false;
		if(!startFlag){
			stockInterval = setInterval("calTime(1)",1000);
			startFlag = true;
		}
	}
	function calTime(){
		if(!isStop){
			if(t == -1){
				mediaStop();
			}
			if(!stopFlag){
				document.getElementById("time").innerHTML = t;

			}
			t--;
		}
	}
	function addTime(){
		t+=5;
		document.getElementById("time").innerHTML = t;
	}
	function mediaStop(){
		closeDiv();
		document.getElementById("em").stop();
		stopFlag = true;
		//clearInterval(stockInterval);
		document.getElementById("play").innerHTML="";
	}
	function init(){
		$("#salon_video").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		lod();
	}
	document.body.onresize="lod();";
</script>