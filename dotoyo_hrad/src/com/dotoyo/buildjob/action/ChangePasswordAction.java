package com.dotoyo.buildjob.action;

import org.apache.struts2.ServletActionContext;

import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.common.util.Md5;
import com.dotoyo.buildjob.sys.dto.SysUserDto;
import com.dotoyo.buildjob.sys.service.ISysUserRoleAdminService;
/**
 * 修改密码
 * @author wisdy.xiao
 *
 */
public class ChangePasswordAction extends SysAction{
	private ISysUserRoleAdminService sysUserRoleAdminService;
	private SysUserDto dto;
	private String userName;
	private String oldPwd;
	private String newPwd;
	private String newPwdmsg;
	private String oldPwdmsg;
	private String msg;
	private String flag = "0"; //修改成功标志
	/**
	 *
	 */
	private static final long serialVersionUID = 4925992991060543029L;
	/**
	 * 修改密码
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_CHANGE_PASSWORD)
	public String init(){
		flag = "0";
		clear();
		SysUserDto tmp = getCurrentSessionUser();
		dto = sysUserRoleAdminService.getUserByUserNameAndPasswordAdmin(tmp);
		userName = dto.getUserName();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * 修改
	 * @return
	 */
	public String changPassword(){
		clear();
		if(ServletActionContext.getRequest().getSession().getAttribute(Constants.SESSION_USER)== null){
			return "login";
		}
		if(!Md5.encoderByMd5(oldPwd).equals(dto.getPassword())){
			oldPwdmsg = "原密码不正确";
			flag = "0";
			return Constants.RTN_SYS_MAIN;
		}
		//...
		dto.setPassword(Md5.encoderByMd5(newPwd));
		sysUserRoleAdminService.updatePassword(dto);
		msg = "密码修改成功,请重新登录";
		flag = "1";
		ServletActionContext.getRequest().getSession().removeAttribute(Constants.SESSION_USER);
		return Constants.RTN_SYS_MAIN;
	}

	private void clear(){
		newPwdmsg = "";
		oldPwdmsg = "";
		msg = "";
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getNewPwdmsg() {
		return newPwdmsg;
	}
	public void setNewPwdmsg(String newPwdmsg) {
		this.newPwdmsg = newPwdmsg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getOldPwdmsg() {
		return oldPwdmsg;
	}
	public void setOldPwdmsg(String oldPwdmsg) {
		this.oldPwdmsg = oldPwdmsg;
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
