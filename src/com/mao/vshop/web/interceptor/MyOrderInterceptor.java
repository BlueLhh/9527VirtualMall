package com.mao.vshop.web.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mao.vshop.model.pojo.TbOrder;
import com.mao.vshop.model.pojo.TbOrderItem;
import com.mao.vshop.model.pojo.TbProduct;
import com.mao.vshop.model.pojo.TbUser;
import com.mao.vshop.model.service.IBaseService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
/**
 * ��ȡ�ҹ�����Ʒ��������
 * @author Administrator
 *
 */
public class MyOrderInterceptor implements Interceptor {

	private ApplicationContext ac;
	private IBaseService<TbUser> userService;
	
	@Override
	public void init() {
		ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		userService=ac.getBean("baseService",IBaseService.class);
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// ͨ��session��ȡ��ǰ��¼�û�
		HttpSession session = request.getSession(false);
		TbUser user = (TbUser) session.getAttribute("user");
		user = userService.findByID(user.getId(), TbUser.class);
		session.setAttribute("user", user);
		// ��ö���  TODO
		Set<TbOrder> olist = user.getOlist();
		
		// ���涩������ļ���
		List<TbOrderItem> oilist = new ArrayList<TbOrderItem>();
		// ����������ȡ�䶩������
		for (TbOrder tbOrder : olist) {
			Set<TbOrderItem> items = tbOrder.getItems();
			for (TbOrderItem tbOrderItem : items) {
				oilist.add(tbOrderItem);
			}
		}

		// ������Ʒ�ļ���
		List<TbProduct> plist = new ArrayList<TbProduct>();
		// �����Ӷ�������ȡ��Ӧ����Ʒ
		for (TbOrderItem tbOrderItem : oilist) {
			TbProduct product = tbOrderItem.getProduct();
			plist.add(product);
		}

		// ������Ʒ
		for (TbProduct product : plist) {
			// ��Ϊ���ڽ�ң����԰���Ʒ������Ϣ��������
			String describe = product.getDescribe();
			// �������Ʒ��������Ϸ�������Ʒ��������#���ֿ�
			// ��Ϊ���ϼ���Ʒʱ���Ͱѽ�����������Ʒ��������
			if (product.getProduct_name().equals("��Ϸ��")) {
				String[] split = describe.split("#");
				// ǰ���������������ǽ������
				product.setProduct_name(split[1]);
				product.setDescribe(split[0]);
			}
		}
		/**
		 * ���req���붩���������
		 */
		request.setAttribute("oilist", oilist);
		String invoke = invocation.invoke();
		return invoke;
	}

	@Override
	public void destroy() {

	}
}
