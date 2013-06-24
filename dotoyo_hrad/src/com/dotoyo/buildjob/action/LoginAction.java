package com.dotoyo.buildjob.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.dao.DataAccessResourceFailureException;

import com.dotoyo.buildjob.common.action.BuildJobAction;
import com.dotoyo.buildjob.common.constant.ApplicationConstant;
import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.util.Md5;
import com.dotoyo.buildjob.common.util.PropertiesValue;
import com.dotoyo.buildjob.sys.dto.SysUserDto;
import com.dotoyo.buildjob.sys.service.ISysUserRoleAdminService;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;
import com.opensymphony.xwork2.ActionContext;
/**
 * 后台管理员登陆
 * @author wisdy.xiao
 *
 */
public class LoginAction extends BuildJobAction{
	private static Logger logger = Logger.getLogger(LoginAction.class);
	private ISysUserRoleAdminService sysUserRoleAdminService;
	private SysUserDto dto;
	private String password;
	private String msg;
	private GenericManageableCaptchaService captchaService;
	private String functionName;
	private static final long serialVersionUID = 4925992991060543029L;
	/**
	 * 验证码验证
	 *
	 * @return
	 */
	public Boolean validateCaptcha() {
		HttpServletRequest req = ServletActionContext.getRequest();
		String captchaId = getHttpSession().getId();
		String userCaptchaResponse = req
				.getParameter(ApplicationConstant.REQUEST_PARAMETER_CAPTCHA);
		ImageCaptchaService imageCaptchaService = (ImageCaptchaService) captchaService;
		boolean flag = false;
		try{
			flag = imageCaptchaService.validateResponseForID(captchaId,
					userCaptchaResponse);
		}catch(Exception e){
			logger.error(e);
		}
		return flag;
	}
	/**
	 * initialize
	 * @return
	 */
	public String init(){
		clear();
		try{
			if(!getApplication().containsKey(ApplicationConstant.FRONT_HTTP_PATH))
				getApplication().put(ApplicationConstant.FRONT_HTTP_PATH,PropertiesValue.getPropertyValue(ApplicationConstant.FRONT_HTTP_PATH));
		}catch(Exception e){
			logger.error(e);
		}
		return Constants.RTN_SYS_MAIN;
	}


	/**
	 * 管理员登录
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String login(){
		clear();
		try{
			if(!validateCaptcha()){
				msg = "验证码错误";
				return Constants.RTN_SYS_MAIN;
			}
			SysUserDto tmp = checkUser();
			if(tmp == null){
				msg = "请确认你的用户名或者密码是正确的";
				return Constants.RTN_SYS_MAIN;
			}else if(tmp.getStatus().equals(ApplicationConstant.STATUS_USER_UNABLED)){
				msg = "抱歉，你输入的用户已停用";
				return Constants.RTN_SYS_MAIN;
			}
	//		/** 写入日志 **/
	//		SysLogDto logDto = new SysLogDto();
	//		logDto.setAction(Constants.SYS_LOG_STATUS_LOGIN);
	//		logDto.setOperate_time(new Date());
	//		logDto.setOperator(tmp.getId());
	//		logDto.setIp(getIp());
	//		sysLogService.addSysLog(logDto);
			getHttpSession().setAttribute(Constants.SESSION_USER, tmp);
		}catch(DataAccessResourceFailureException drfe){
			msg = "连接超时,请重试";
			logger.error(drfe);
			return Constants.RTN_SYS_MAIN;
		}catch(Exception e){
			msg = "登陆失败,请重试";
			logger.error(e);
			return Constants.RTN_SYS_MAIN;
		}
		return super.SUCCESS;
	}

	public String loginout(){
		/** 写入日志 **/
//		SysUserDto tmp = (SysUserDto)getSession().getAttribute(Constants.SESSION_USER);
//		SysLogDto logDto = new SysLogDto();
//		logDto.setAction(Constants.SYS_LOG_STATUS_LOGINOUT);
//		logDto.setOperate_time(new Date());
//		logDto.setOperator(tmp.getId());
//		logDto.setIp(getIp());
//		sysLogService.addSysLog(logDto);
		getHttpSession().removeAttribute(Constants.SESSION_USER);
		dto = new SysUserDto();
		return Constants.RTN_SYS_MAIN;
	}
	private HttpSession getHttpSession(){
		return ServletActionContext.getRequest().getSession();
	}
	private Map<String,Object> getApplication(){
		return ServletActionContext.getContext().getApplication();
	}
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	public String getIp(){
		String ip  =  getRequest().getHeader("x-forwarded-for");
		 if (ip  ==   null   ||  ip.length()  ==   0   ||   "unknown" .equalsIgnoreCase(ip))  {
		    ip  =  getRequest().getHeader( " Proxy-Client-IP " );
		}
		 if (ip  ==   null   ||  ip.length()  ==   0   ||   "unknown" .equalsIgnoreCase(ip))  {
		    ip  =  getRequest().getHeader( "WL-Proxy-Client-IP" );
		}
		 if (ip  ==   null   ||  ip.length()  ==   0   ||   "unknown" .equalsIgnoreCase(ip))  {
		    ip  =  getRequest().getRemoteAddr();
		}
		 return ip;
	}
	/**
	 * Check user
	 * @return
	 */
	private SysUserDto checkUser(){
		dto.setPassword(Md5.encoderByMd5(password));
		SysUserDto tmpdto = sysUserRoleAdminService.getUserByUserNameAndPasswordAdmin(dto);
		return tmpdto;
	}
	private void clear(){
		msg = "";
	}


	public String noRightAccess(){
	ActionContext ctx = ActionContext.getContext();
	this.functionName=(String) ctx.getSession().get(Constants.SESSION_FUNCTION_NAME);
	return "noRightPage";
	}
	public ISysUserRoleAdminService getSysUserRoleAdminService() {
		return sysUserRoleAdminService;
	}
	public void setSysUserRoleAdminService(
			ISysUserRoleAdminService sysUserRoleAdminService) {
		this.sysUserRoleAdminService = sysUserRoleAdminService;
	}
	public SysUserDto getDto() {
		return dto;
	}
	public void setDto(SysUserDto dto) {
		this.dto = dto;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public GenericManageableCaptchaService getCaptchaService() {
		return captchaService;
	}
	public void setCaptchaService(GenericManageableCaptchaService captchaService) {
		this.captchaService = captchaService;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}


}
