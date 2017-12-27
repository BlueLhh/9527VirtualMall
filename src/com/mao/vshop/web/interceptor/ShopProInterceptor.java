package com.mao.vshop.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mao.vshop.model.pojo.TbGameAccount;
import com.mao.vshop.model.pojo.TbProduct;
import com.mao.vshop.model.pojo.TbUser;
import com.mao.vshop.model.service.IBaseService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * ��Ʒ�¶���ʱ��������
 * @author Administrator
 * @param <T>
 *
 */
public class ShopProInterceptor<T> implements Interceptor {

	@Override
	public void init() {

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// ��ȡ��Ʒid
		Long proid = Long.valueOf(request.getParameter("proid"));

		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		IBaseService<TbProduct> service = ac.getBean("baseService", IBaseService.class);
		// ������Ʒid��ѯ��Ʒ
		TbProduct product = service.findByID(proid, TbProduct.class);

		// �������Ʒ��������Ϸ�������Ʒ��������#���ֿ�
		// ��Ϊ���ϼ���Ʒʱ���Ͱѽ�����������Ʒ��������
		if (product.getProduct_name().equals("��Ϸ��")) {
			// ��Ϊ���ڽ�ң����԰���Ʒ������Ϣ��������
			String describe = product.getDescribe();
			String[] split = describe.split("#");
			// ǰ���������������ǽ������
			product.setProduct_name(split[1]);
			product.setDescribe(split[0]);
		}

		// ��ȡ����Ʒ�ĳ����û�
		TbUser sellUser = product.getUser();
		// ��ѯ��Ʒ��������Ϸ
		TbGameAccount gameAccount = product.getAccount();
		// �ѻ�ȡ����Ʒ���󴫵�request
		request.setAttribute("product", product);
		// �ѳ�����Ʒ�û�����Ϣ�ȼ�����request
		request.setAttribute("level", sellUser.getCreditLevel());
		// ����Ϸ�˻����󴫵�request
		request.setAttribute("gameAccount", gameAccount);
		String invoke = invocation.invoke();
		return invoke;
	}

	@Override
	public void destroy() {

	}
}
