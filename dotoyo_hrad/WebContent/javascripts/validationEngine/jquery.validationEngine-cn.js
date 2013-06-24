(function($) {
	$.fn.validationEngineLanguage = function() {
	};
	$.validationEngineLanguage = {
		newLang : function() {
			$.validationEngineLanguage.allRules = {
				"required" : { // Add your regex rules here, you can take telephone as an example
					"regex" : "none",
					"alertText" : "* 此项不能为空.",
					"alertTextCheckboxMultiple" : "* 至少选择一个复选框.",
					"alertTextCheckboxe" : "* 至少选择一个复选框.",
					"alertTextRadio" : "* 请选择一项."
				},

				"length" : {
					"regex" : "none",
					"alertText" : "* 长度必须在 ",
					"alertText2" : " 至 ",
					"alertText3" : " 之间."
				},
				// bluespring添加 区间效验
				"region" : {
					"regex" : "none",
					"alertText" : "* 取值必须在 ",
					"alertText2" : " 至 ",
					"alertText3" : " 之间."
				},
				"maxCheckbox" : {
					"regex" : "none",
					"alertText" : "* 最多选择 ",
					"alertText2" : " 项."
				},
				"minCurrentDay" : {
					"regex" : "none",
					"alertText" : "* 不能选择今天以前的日期 "
				},
				"minCheckbox" : {
					"regex" : "none",
					"alertText" : "* 至少选择 ",
					"alertText2" : " 项."
				},
				"confirm" : {
					"regex" : "none",
					"alertText" : "* 两次输入不一致,请重新输入."
				},
				"notconfirm" : {
					"regex" : "none",
					"alertText" : "* 新密码不能与原密码相同."
				},
				"telephone" : {
					"regex" : "/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/",
					"alertText" : "* 请输入有效的电话号码,如:010-29292929."
				},
				"nullCheckS" : {
					"regex" : "/[^ ]/g",
					"alertText" : "* 此项不能为空."
				},
				"isImg" : {
					"regex" : "/^(.+\.(JPEG|jpeg|JPG|jpg|GIF|gif|BMP|bmp|PNG|png))$/",
					"alertText" : "* 文件格式限制,只能是图片."
				},
				"isFlash" : {
					"regex" : "/^(.+\.(swf))$/",
					"alertText" : "* 文件格式限制,只能是flash."
				},
				"isImgFlash" : {
					"regex" : "/^(.+\.(JPEG|jpeg|JPG|jpg|GIF|gif|BMP|bmp|PNG|png|swf))$/",
					"alertText" : "* 文件格式限制,只能是flash或者图片."
				},
				"positionMoreThan8" : {
					"regex" : "/^[a-zA-Z0-9\-]{9,20}$/",
					"alertText" : "* 请选择具体位置."
				},
				"mobilephone" : {
					"regex" : "/(^0?[1][358][0-9]{9}$)/",
					"alertText" : "* 请输入有效的手机号码."
				},
				"email" : {
					"regex" : "/^[a-zA-Z0-9_\.\-]+\@([a-zA-Z0-9\-]+\.)+[a-zA-Z0-9]{2,4}$/",
					"alertText" : "* 请输入有效的邮件地址."
				},
				"date" : {
					"regex" : "/^(19[0-9]{2}|2[0-9]{3})-(0[1-9]|1[012])-([123]0|[012][1-9]|31)$/",
					"alertText" : "* 请输入有效的日期,如:2010-01-01."
				},
				"datetime" : {
					"regex" : "/^(19[0-9]{2}|2[0-9]{3})-(0[1-9]|1[012])-([123]0|[012][1-9]|31) ([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$/",
					"alertText" : "* 请输入有效的日期时间,如:2010-01-01 12:12:01."
				},
				"ip" : {
					"regex" : "/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/",
					"alertText" : "* 请输入有效的IP."
				},
				"chinese" : {
					"regex" : "/^[\u4e00-\u9fa5]+$/",
					"alertText" : "* 请输入中文."
				},
				"url" : {
					"regex" : "/^(http(s)?|ftp|telnet|news|rtsp|mms)(:)[/\/\a-zA-Z0-9_!~*'().&=+$%-:&?#]+$/",
					"alertText" : "* 请输入有效的网址."
				},
				"zipcode" : {
					"regex" : "/^[1-9]\d{5}$/",
					"alertText" : "* 请输入有效的邮政编码."
				},
				"onlyNumber" : {
					"regex" : "/^[0-9]+$/",
					"alertText" : "* 请输入正确的数字.<br/>* 例 123."
				},
				"onlyDouble" : {
					"regex" : "/^[0-9]+(.[0-9]{1,2})?$/",
					"alertText" : "* 请输入正确的金额.<br/>* 例 88.88."
				},
				"onlyLetter" : {
					"regex" : "/^[a-zA-Z]+$/",
					"alertText" : "* 请输入英文字母."
				},
				"noSpecialCaracters" : {
					"regex" : "/^[0-9a-zA-Z]+$/",
					"alertText" : "* 请输入英文字母和数字."
				},
				"ajaxUser" : {
					"file" : "validateAjaxUser",
					"alertTextOk" : "* 帐号可以使用.",
					"alertTextLoad" : "* 检查中, 请稍后...",
					"alertText" : "* 帐号不能使用."
				},
				"ajaxName" : {
					"file" : "validateUser.php",
					"extraData" : "eric", //其他的参数
					"alertTextOk" : "* This name is available",
					"alertTextLoad" : "* Loading, please wait",
					"alertText" : "* This name is already taken"
				},
				"ajaxServiceCode" : {
					"file" : "sys/serviceNameMainAction!checkIsExisted.action",
					"extraData" : "err", //其他的参数
					"alertTextOk" : "* This name is available",
					"alertTextLoad" : "* Loading, please wait",
					"alertText" : "* This name is already taken"
				},
				"validate2fields" : {
					"nname" : "validate2fields",
					"alertText" : "* You must have a firstname and a lastname"
				},
                "filteringSpecialChar":{//只含有汉字、数字、字母
                	"regex": /^(?!_)(?!.*?_$)[a-zA-Z0-9\u4e00-\u9fa5]+$/,
                    "alertText": "* 输入限制[汉字、数字、字母]."
                },
                "filteringSpecialChar1":{//只含有汉字、数字、字母、下划线不能以下划线开头和结尾
                	"regex": /^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/,
                    "alertText": "* 输入有误."
                },

                "filteringSpecialChar2":{//只含有数字、字母、-、_
                	"regex": /^[a-zA-Z0-9_-]+$/,
                    "alertText": "* 输入限制[数字、字母、横杠、下划线]."
                },
                "filteringSpecialChar3":{//只含有数字、字母、-、_、@、.
                	"regex": /^[a-zA-Z0-9@._-]+$/,
                    "alertText": "* 输入限制[数字、字母、横杠、下划线、@、点]."
                },
				 // DOTOYO rules
                "requiredSelected": {
                	"regex": /^(?!请选择)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]|[\/\/]+$/,
                    "alertText": "* 请选择.",
                    "alertTextCheckboxMultiple": "* Please select an option",
                    "alertTextCheckboxe": "* This checkbox is required"
                },
                "region": {
                	"regex": /^(?![省份城市城区])(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/,
                    "alertText": "* This field is required",
                    "alertTextCheckboxMultiple": "* Please select an option",
                    "alertTextCheckboxe": "* This checkbox is required"
                }

			};
		}
	};
	$.validationEngineLanguage.newLang();
})(jQuery);
