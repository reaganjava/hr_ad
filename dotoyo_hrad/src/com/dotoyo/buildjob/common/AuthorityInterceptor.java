package com.dotoyo.buildjob.common;

import java.util.Map;

import com.dotoyo.buildjob.common.constant.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 拦截器
 * @author wisdy.xiao
 *
 */
public class AuthorityInterceptor extends  AbstractInterceptor{

	/**
	 *
	 */
	private static final long serialVersionUID = -986035881145084230L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 取得请求相关的ActionContext实例
		ActionContext ctx=invocation.getInvocationContext();
		Map<String,Object> session=ctx.getSession();
		//取出名为user的session属性
		Object user=session.get(Constants.SESSION_USER);
		if(user!=null){
			return invocation.invoke();
		}
		return "login";
	}

}
