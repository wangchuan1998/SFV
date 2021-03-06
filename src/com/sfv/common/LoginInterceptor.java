package com.sfv.common;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 用户拦截器
 * @author dbq
 *
 */
public class LoginInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		if(arg0.getAction() instanceof com.sfv.action.LoginAction
				|| arg0.getAction() instanceof com.sfv.action.cms.LoginAction)
		{
			return arg0.invoke();
		}
		
		Map session = arg0.getInvocationContext().getSession();
		if(null != session.get("USERINFO"))
		{
			return arg0.invoke();
		}
		return "login";
	}

}
