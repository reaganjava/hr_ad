function setCity(hiddenProvince,cityId,hiddenCity,hiddenCityV,areaId,hiddenArea){
	$.getJSON("../common/loadCity.action?provinceCode="+$(hiddenProvince).val()+"&randomNum="+Math.random() , function(data) {
		data = eval('('+data+')');
		$.each(data, function(i, item) {				
			value = item.code;
			text = item.name;
			document.getElementById(cityId).value=text;
			document.getElementById(hiddenCity).value=value;
		
			if(i==data.length-1){
				setArea(hiddenCityV,areaId,hiddenArea);
			}
		});
	});
}

function setArea(hiddenCity,areaId,hiddenArea){
	$.getJSON("../common/loadArea.action?cityCode="+$(hiddenCity).val()+"&randomNum="+Math.random() , function(data) {
		data = eval('('+data+')');
		$.each(data, function(i, item) {	
			value = item.code;
			text = item.name;
			document.getElementById(areaId).value=text;
			document.getElementById(hiddenArea).value=value;
		});
	});
}

function loadData(dataType,searchDown){
	var citySearch='citySearch';
	var areaSearch='areaSearch';
	if(dataType==citySearch){
		$.getJSON("../common/loadCity.action?provinceCode="+$("#hiddenProvince").val()+"&randomNum="+Math.random() , function(data) {
			var options="";
			data = eval('('+data+')');
			$.each(data, function(i, item) {				
				value = item.code;
				text = item.name;
				options = options + "<li><a href='#' id=\""+value+"\""+" onclick=\"setValue(this,'citySearch','hiddenCity');setArea('#hiddenCity','areaSearch','hiddenArea')\">"+text+"</a></li>";
			});
			
			$('#cityId').html(options);
			menuClick('#citySearch', searchDown, -74, 24);

		});
	}else{
		$.getJSON("../common/loadArea.action?cityCode="+$("#hiddenCity").val()+"&randomNum="+Math.random() , function(data) {
			var options="";
			data = eval('('+data+')');
			$.each(data, function(i, item) {				
				value = item.code;
				text = item.name;
				options = options + "<li><a href='#' id=\""+value+"\""+" onclick=\"setValue(this,'areaSearch','hiddenArea')\">"+text+"</a></li>";
			});
			
			$('#areaId').html(options);
			menuClick('#areaSearch', searchDown, -74, 24);
		});
	}
}

function setBaseData(iId,hId,dId,dataType){
	
	$.getJSON("../common/loadEssentialData.action?dataType="+dataType+"&"+"randomNum="+Math.random() , function(data) {
		var options="";
		data = eval('('+data+')');
		options = options + "<ul>";
		$.each(data, function(i, item) {				
			value = item.code;
			text = item.name;
			options = options + "<li><a href='javascript:void(0)' id=\""+value+"\""+" onclick=\"setValue(this,'"+iId+"','"+hId+"')\">"+text+"</a></li>";
		});
		options = options + "</ul>";
		$(dId).html(options);
	});
}


function setBaseData11(iId,hId,dId,dataType){
	$.getJSON("../common/loadEssentialData.action?dataType="+$(dataType).val()+"&"+"randomNum="+Math.random() , function(data) {
		var options="";
		data = eval('('+data+')');
		options = options + "<ul>";
		$.each(data, function(i, item) {				
			value = item.code;
			text = item.name;
			options = options + "<li><a href='#' id=\""+value+"\""+" onclick=\"setValue(this,'"+iId+"','"+hId+"')\">"+text+"</a></li>";
		});
		options = options + "</ul>";
		$(dId).html(options);
	});
}


function setValue(searchDown,inputValue,hiddenValue){
	// alert($(searchDown).text()+'----'+$(searchDown).attr("id"));
	document.getElementById(inputValue).value=$(searchDown).text();
	document.getElementById(hiddenValue).value=$(searchDown).attr("id");
};

$(document).ready(function(){
	$("#province").change(function(){
		var provinceCode = $("#province option:selected").val();
		var url = "../common/loadCity.action?provinceCode="+provinceCode;
		$.getJSON(url,loadCity);
	});
	$("#city").change(function(){
		var cityCode = $("#city option:selected").val();
		var url = "../common/loadArea.action?cityCode="+cityCode;
		$.getJSON(url,loadArea);
	});
});
loadCity=function(data){
	var options = "";
	var defaultCityOption = "<option value=\"\">市</option>";
	var defaultAreaOption = "<option value=\"\">区</option>";
	var value = "";
	var text = "";
	data = eval('('+data+')');
	$.each(data,function(i,item){
		value = item.code;
		text = item.name;
		options = options + "<option value=\""+value+"\">"+text+"</option>";
	});
	$("#city").html(options);
	
	var defaultName = $("#province").val();
	if(defaultName == ""){
		$("#city").html(defaultCityOption);
		$("#area").html(defaultAreaOption);
	}
	
	var cityCode = $("#city option:selected").val();
	var url = "../common/loadArea.action?cityCode="+cityCode;
	$.getJSON(url,loadArea);
};
loadArea=function(data){
	var options = "";
	var defaultAreaOption = "<option value=\"\">区</option>";
	var value = "";
	var text = "";
	data = eval('('+data+')');
	$.each(data,function(i,item){
		value = item.code;
		text = item.name;
		options = options + "<option value=\""+value+"\">"+text+"</option>";
	});
	if(options != ""){
		$("#area").html(options);
	}
	else{
		$("#area").html(defaultAreaOption);
	}
};