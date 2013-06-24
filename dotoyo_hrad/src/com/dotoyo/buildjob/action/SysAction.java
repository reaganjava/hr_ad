package com.dotoyo.buildjob.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dotoyo.buildjob.common.action.BuildJobAction;
import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.sys.dto.SysUserDto;

public class SysAction extends BuildJobAction{
	private String commonMsg;
	/**
	 *
	 */
	private static final long serialVersionUID = -4586994311692999652L;
	public String execute(){
		try {
			commonMsg = "";
			return super.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	protected HttpSession getHttpSession(){
		return ServletActionContext.getRequest().getSession();
	}
	protected HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	/**
	 * 获得当前用户
	 * @return
	 */
	protected SysUserDto getCurrentSessionUser(){
		Object obj = ServletActionContext.getRequest().getSession().getAttribute(Constants.SESSION_USER);
		if(obj != null && obj instanceof SysUserDto){
			SysUserDto tmp = (SysUserDto)obj;
			return tmp;
		}
		return null;
	}

	public String getCommonMsg() {
		return commonMsg;
	}
	public void setCommonMsg(String commonMsg) {
		this.commonMsg = commonMsg;
	}
	public void addActionError(String anErrorMessage){
		commonMsg = anErrorMessage;
		//super.addActionError(anErrorMessage);

	}

	public void addActionMessage(String aMessage){
		super.addActionMessage(aMessage);
	}
	public void addFieldError(String fieldName, String errorMessage){
		super.addFieldError(fieldName, errorMessage);
	}

}
