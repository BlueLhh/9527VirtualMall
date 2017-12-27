package com.mao.vshop.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mao.vshop.model.pojo.TbProduct;
import com.mao.vshop.model.service.IBaseService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * ��������Ʒ��������
 * @author Administrator
 * @param <T>
 *
 */
public class MainProInterceptor<T> implements Interceptor {

	@Override
	public void init() {

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		IBaseService<TbProduct> service = ac.getBean("baseService", IBaseService.class);

		String findHQL = "from TbProduct where stock>0 and status=0 and rownum<10";
		// ��ҳ��ʾ����Ʒ��������Ϊ�գ������������һ��������
		Object[] obj = new Object[0];
		List<TbProduct> plist = (List<TbProduct>) service.findByHql(findHQL, obj);

		request.setAttribute("plist", plist);
		String invoke = invocation.invoke();
		return invoke;
	}

	@Override
	public void destroy() {

	}
}
