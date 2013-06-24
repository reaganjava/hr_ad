package com.dotoyo.buildjob.common;

import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.util.ParseAuthNameUtil;
import com.dotoyo.buildjob.common.util.PropertiesValue;
import com.dotoyo.buildjob.sys.dto.SysUserDto;
import com.dotoyo.buildjob.sys.service.ISysFunctionRoleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 验证方法的访问权限 2011-03-04
 * 
 * @author Bill Xu
 * 
 */
public class AuthFunction4AdminInterceptor extends MethodFilterInterceptor {
	private static final long serialVersionUID = 1L;
	public SysUserDto sysUserDto;
	public ISysFunctionRoleService sysFunctionRoleService;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {

		ActionContext ctx = ActionContext.getContext();
		// 空返回登录
		if (ctx.getSession().get(Constants.SESSION_USER) == null) {
			return "login";
		}
		this.sysUserDto = (SysUserDto) ctx.getSession().get(
				Constants.SESSION_USER);
		// 如果是超级管理员,默认访问所有功能
		String userName = PropertiesValue
				.getPropertyValue(Constants.SYSTEM_ADMINISTRATORS_DEFAULTNAME);
		if (userName != null && userName.equals(sysUserDto.getUserName())) {
			return invocation.invoke();
		}
		String methodName = invocation.getProxy().getMethod();
		Object actionName = invocation.getProxy().getAction();
		String functionCode = ParseAuthNameUtil.parseAuthentication(
				actionName.getClass(), methodName);
		Long userId = this.sysUserDto.getId();

		// 如果functioncode为空，则表名该方法为加入权限控制
		if (functionCode == null || "".equalsIgnoreCase(functionCode)) {
			return invocation.invoke();
		}

		// 根据Code和用户名去权限表查找(调用service)，是否存在访问这个方法的权限
		if (functionCode != null && userId != null) {
			if (sysFunctionRoleService.existFunctionRight4Admin(userId,
					functionCode)) {// 有权限
				return invocation.invoke();
			} else {// 无权限
				String sessionValue = sysFunctionRoleService
						.getFunctionDtoByCode(functionCode).getName();
				ctx.getSession().put(Constants.SESSION_FUNCTION_NAME,
						sessionValue);
			}
		}
		return "noRightPage";
	}

	public ISysFunctionRoleService getSysFunctionRoleService() {
		return sysFunctionRoleService;
	}

	public void setSysFunctionRoleService(
			ISysFunctionRoleService sysFunctionRoleService) {
		this.sysFunctionRoleService = sysFunctionRoleService;
	}

}