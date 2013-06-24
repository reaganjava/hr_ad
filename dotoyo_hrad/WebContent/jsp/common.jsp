<%@page pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<script src="<%=request.getContextPath()%>/javascripts/common/jquery.js"></script>
<script src="<%=request.getContextPath()%>/javascripts/common/jquery.query.js"></script>
<script src="<%=request.getContextPath()%>/javascripts/common/validation.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/javascripts/common/jquery-ui-1.7.2.custom.min.js"></script>
<script src="<%=request.getContextPath()%>/javascripts/common/common.js"></script>
<script src="<%=request.getContextPath()%>/javascripts/My97DatePicker/WdatePicker.js"></script>
<script src="<%=request.getContextPath()%>/javascripts/common/accordion.js"></script>
<script src="<%=request.getContextPath()%>/javascripts/common/jquery.cookie.js"></script>
<script src="<%=request.getContextPath()%>/javascripts/common/jquery.validate.js"></script>
<script src="<%=request.getContextPath()%>/javascripts/common/businessValidate.js"></script>
<script src="<%=request.getContextPath()%>/javascripts/validationEngine/jquery.validationEngine-cn.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/javascripts/validationEngine/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<link href="<%=request.getContextPath()%>/css/system-manage.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/validateMessage.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/javascripts/validationEngine/css/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/javascripts/validationEngine/css/template.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.index_hid_css {
   overflow:hidden;
   word-break:keep-all;
   white-space:nowrap;
   text-overflow:ellipsis;
   -o-text-overflow: ellipsis;    /* Opera */

}
</style>

<script type="text/javascript">
	//options 处理
	var com_arrs;
	function COM_SetOptionTitle()
	{
	    var selects = document.getElementsByTagName("select");
	    if (selects.length > 0)
	    {
	    	com_arrs = new Array(selects.length);
	        for (var i = 0; i < selects.length; i++)
	        {
	        	var subArrs = new Array(selects[i].options.length);
	            var options = selects[i].options;
	            if (selects[i].options.length > 0)
	            {
	                for (var j = 0; j < options.length; j++)
	                {
	                	var pos = { x: 0, y: 0 };
	                    if (options[j].title == "")
	                        options[j].title = options[j].text;
	                    pos.x = options[j].value;
	                    pos.y = options[j].text;
	                    subArrs[j] = pos;
	                }
	            }
	            com_arrs[i] = subArrs;
	        }
	    }
	}
	function COM_setSelectTitle(obj,k){
		COM_SetOptionTitle();
		if(com_arrs.length > k){
			var tmps = com_arrs[k];
			if(tmps.length > 0){
				for(var i = 0;i < tmps.length;i++){
					if(tmps[i].x == obj.value){
						obj.title = tmps[i].y;
						break;
					}
				}
			}
		}
	}
	function COM_getElementsByClassName(cn){

		var hasClass=function(w,Name){
			var hasClass = false;
			w.className.split(' ').each(function(s){
				if (s == Name) hasClass = true;
			});
			return hasClass;
		};

		var elems =document.getElementsByTagName("*")||document.all;
		var elemList = [];
		$c(elems).each(function(e){
			if(hasClass(e,cn)){elemList.push(e);}
		})
		return $c(elemList);
	}
(function($) {
	var msg = "<s:text name="commonMsg"/>";
	if(msg != "" && msg != "commonMsg"){
		alert(msg);
		msg = "";
	}
})(jQuery);
//submit form
function genSubmitForm4ChangeAction(formName,actionName){
	 formName.action=actionName;
     formName.submit();

}

//submit form
function submitForm(formName){

	if(formName == null){
	  document.form.submit();
	}else{
      formName.submit();
	}
}
//go back
function goBack(){
	window.history.go(-1);
}

//submit batch choose action form
function submitFormChooseCheck(formName,checkBoxName){
	if(checkStatus(checkBoxName)){
	if(confirm("你确认要进行此操作吗?")){
	if(formName == null){
	  document.form.submit();
	}else{
      formName.submit();
	}
  }
 }else{
   alert("请选择最少一条记录");
 }
}

//submit batch choose action form
function submitForm4ChangeAction(formName,checkBoxName,actionName){
	if(checkStatus(checkBoxName)){
	  if(confirm("你确认要进行此操作吗?")){
		if(formName == null){
		  document.form.submit();
		}else{
		  formName.action=actionName;
	      formName.submit();
		}
	}
 }else{
   alert("请选择最少一条记录");
 }
}
//submit batch choose action form
function submitForm4ChangeActionNull(formName,checkBoxName,actionName){
	document.getElementById("hidden_true_box").checked = true;
	if(checkStatus(checkBoxName)){
	  if(confirm("你确认要进行此操作吗?")){
		if(formName == null){
		  document.form.submit();
		}else{
		  formName.action=actionName;
	      formName.submit();
		}
	   }
	 }else{
	   alert("请选择最少一条记录");
	 }
}
function alertMsg(msg){
	if(msg != ""){
		alert(msg);
	}
}
var flag = false;
function beforeCall(form, options){
   flag = $("#pageFrom").validationEngine('validate');
   if(flag){
   	 	form.submit();
   }
   return true;
}
function beforeClickCall(){

}
// Called once the server replies to the aja form validation request
function ajaxValidationCallback(status, form, json, options){
   alert("Callback");
}
function callFail(){
	alert("callFailFunction");
}
$(document).ready(function(){
    $("#pageFrom").validationEngine({
        ajaxFormValidation: true,
        onAjaxFormComplete: ajaxValidationCallback,
        onBeforeAjaxFormValidation: beforeCall,
		validationEventTriggers:"blur",  //触发的事件  validationEventTriggers:"keyup blur",
		inlineValidation: true,//是否即时验证，false为提交表单时验证,默认true
		success :  false//为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
    });
});　
</script>