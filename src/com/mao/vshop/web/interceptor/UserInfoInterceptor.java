package com.mao.vshop.web.interceptor;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.mao.vshop.model.pojo.TbOrder;
import com.mao.vshop.model.pojo.TbUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * ������Ϣ��������
 * @author Administrator
 *
 */
public class UserInfoInterceptor implements Interceptor {

	@Override
	public void init() {

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		TbUser user = (TbUser) session.getAttribute("user");
		// ��ö���
		Set<TbOrder> olist = user.getOlist();
		// δȷ�ϵ��ջ�������
		int osum = 0;
		for (TbOrder tbOrder : olist) {
			if (tbOrder.getStatus() == 0) {
				osum++;
			}
		}
		request.setAttribute("osum", osum);
		request.setAttribute("balance", user.getBalance());
		request.setAttribute("level", user.getCreditLevel());
		String invoke = invocation.invoke();
		return invoke;
	}

	@Override
	public void destroy() {
	}
}
