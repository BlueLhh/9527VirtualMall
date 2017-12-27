package com.mao.vshop.web.interceptor;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mao.vshop.model.dao.IProductDao;
import com.mao.vshop.model.pojo.TbProduct;
import com.mao.vshop.model.pojo.TbUser;
import com.mao.vshop.model.service.IBaseService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * ��ȡ�ҳ�����Ʒ��������
 * @author Administrator
 *
 */
public class MySellProInterceptor implements Interceptor {

	@Autowired
	private IProductDao proDao;
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
		// ��ȡ��ǰ��¼�û�
		HttpSession session = request.getSession(false);
		TbUser user = (TbUser) session.getAttribute("user");
		user = userService.findByID(user.getId(), TbUser.class);
		session.setAttribute("user", user);
		// ��ȡ��Ʒ  TODO �޷��������󣿶�������Ʒ���޷�����
		Set<TbProduct> plist = user.getPlist();

		// ������Ʒ����
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
			// ר�Ų�ѯ��Ʒ�ɽ�����������ֱ��ʹ����ƷDao��
			int sellCount = proDao.findSellCountById(product.getId());
			product.setSellCount(sellCount);
		}

		// ����Ʒ���ϴ���req����
		request.setAttribute("plist", plist);
		String invoke = invocation.invoke();
		return invoke;
	}

	@Override
	public void destroy() {

	}
}
