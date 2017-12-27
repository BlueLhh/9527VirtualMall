package com.mao.vshop.web.action;

import java.util.Date;

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
import com.opensymphony.xwork2.ActionSupport;

/**
 * ������Action
 * @author Administrator
 * @param <T>
 *
 */
public class OrderAction<T> extends ActionSupport {

	private ApplicationContext ac;
	private IBaseService<TbUser> userService;
	private IBaseService<TbProduct> proService;
	private IBaseService<TbOrder> orderService;

	@Override
	public void validate() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		userService = ac.getBean("baseService", IBaseService.class);
		proService = ac.getBean("baseService", IBaseService.class);
		orderService = ac.getBean("baseService", IBaseService.class);
		super.validate();
	}

	/**
	 * �����µ�
	 */
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// ��ȡsession����
		HttpSession session = request.getSession(false);
		// ��ȡ��ǰ��¼�û���Ϣ
		TbUser user = (TbUser) session.getAttribute("user");
		// ��ȡ��ǰ��Ʒid
		Long proid = Long.valueOf(request.getParameter("proid"));
		// ��ȡ��ϵ�绰
		String phone = request.getParameter("phone");
		// ��ȡQQ
		String qq = request.getParameter("qq");
		// ��ȡ�ջ���ɫ
		String rolename = request.getParameter("rolename");
		// ��ȡ��������
		int account = Integer.valueOf(request.getParameter("account"));

		// ������Ʒid��ȡ��Ʒ����
		TbProduct product = proService.findByID(proid, TbProduct.class);

		// ��ʼ�������Ϣд�붩������
		TbOrder order = new TbOrder();
		order.setUser(user);
		order.setPhone(phone);
		order.setQq(qq);
		order.setShopDate(new java.sql.Timestamp(new Date().getTime()));
		order.setStatus(0);

		// �������Ϣд�붩�������
		TbOrderItem orderItem = new TbOrderItem();
		orderItem.setOrder(order);
		orderItem.setProduct(product);
		orderItem.setRole_name(rolename);
		orderItem.setCount(account);

		// ��������Ѷ����������
		order.getItems().add(orderItem);
		// д�����ݿ�
		orderService.save(order);

		// ������Ʒ�Ŀ���ȥ��������������������Ϊ0������Ҫ�ѽ���״̬�ı�
		product.setStock(product.getStock() - account);
		if (product.getStock() == 0) {
			product.setStatus(2);
		}
		// �Ѷ����������
		product.getItems().add(orderItem);
		// ������Ʒ��Ϣ
		proService.update(product);

		// �����û�����Ϣ����
		// 1.�����ܻ���
		int sum = account * product.getPrice();
		// 2.�û�������
		user.setBalance(user.getBalance() - sum);
		// 3.���״���+1
		user.setTradeCount(user.getTradeCount() + 1);
		// 4.�Ѷ����Ĺ���
		user.getOlist().add(order);
		// 5.�����û���Ϣ
		userService.update(user);
		return SUCCESS;
	}

	/**
	 * �����������������������
	 * @return
	 */
	public String option() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		// ��ȡ��¼�û�
		TbUser user = (TbUser) session.getAttribute("user");
		// ��ȡ����id
		Long orderid = Long.valueOf(request.getParameter("orderid"));
		// ��Ʒid
		Long proid = Long.valueOf(request.getParameter("proid"));
		// ��ȡ��������
		int count = Integer.valueOf(request.getParameter("count"));

		// ������Ʒid��ѯ��Ʒ
		TbProduct product = proService.findByID(proid, TbProduct.class);
		// ������Ʒ��ȡ������
		TbUser sellUser = product.getUser();

		// ����orderid��ѯ����
		TbOrder order = orderService.findByID(orderid, TbOrder.class);

		// optionΪ�û����еĲ���
		String option = request.getParameter("option");
		// �ж��ǳ�����������ȷ���ջ�
		if ("cel".equals(option)) {
			// ��������
			// �����Ʒ״̬Ϊ������ɣ��������״̬��Ϊ������
			if (product.getStatus() == 2) {
				product.setStatus(0);
			}
			// ��Ʒ���+���������
			product.setStock(product.getStock() + count);
			// ͬʱ��¼�û������յ��˿� (��Ʒ����*����)
			user.setBalance(user.getBalance() + (count * product.getPrice()));
			// ͬʱ�Լ��Ľ��״���-1����Ϊ������ʱ���״����Ѿ�����һ�Σ�
			user.setTradeCount(user.getTradeCount() - 1);

			// ������Ʒ���
			proService.update(product);

			// �����û��˺������״���
			userService.update(user);

			// ���Ѷ���״̬��Ϊ���ѳ���
			order.setStatus(-1);
			orderService.update(order);
			return SUCCESS;
		} else {
			// ȷ���ջ�
			// ������Ʒ�������յ�����
			sellUser.setBalance(sellUser.getBalance() + (count * product.getPrice()));
			// ���ҵĽ��״���+1
			sellUser.setTradeCount(sellUser.getTradeCount() + 1);

			// ���³����˵��˺����
			userService.update(sellUser);

			// ���¶���״̬���������
			order.setStatus(2);
			orderService.update(order);
			return SUCCESS;
		}
	}
}
