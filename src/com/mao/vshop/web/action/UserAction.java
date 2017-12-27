package com.mao.vshop.web.action;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mao.vshop.model.pojo.TbProduct;
import com.mao.vshop.model.pojo.TbUser;
import com.mao.vshop.model.service.IBaseService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction<T> extends ActionSupport {

	private TbUser user = new TbUser();
	private String proid = "";
	private ApplicationContext ac;
	private IBaseService<TbUser> userService;

	public UserAction() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		userService = ac.getBean("baseService", IBaseService.class);
	}

	@Override
	public void validate() {
		super.validate();
		HttpServletRequest request = ServletActionContext.getRequest();
		// ��ȡ��ת�ĵ�ַ��
		String header = request.getHeader("referer");
		if (header.contains("proid")) {
			String[] split = header.split("proid=");
			// ����proid=�ָ��ַ�����±�Ϊ��1�����ַ���Ϊ��ʱ��Ҫ�������Ʒid
			proid = split[1];
		}
	}

	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	/**
	 * �û���¼
	 * @return
	 */
	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession hSession = request.getSession();
		// �û���
		String username = request.getParameter("username");
		// ����
		String password = request.getParameter("password");
		String selectHQL = "from TbUser where name=? and password=?";

		// ��ѯ����
		Object[] obj = new Object[2];
		obj[0] = username;
		obj[1] = password;
		// ��ʼ��ѯ
		List<TbUser> list = userService.findByHql(selectHQL, obj);

		// �ж��Ƿ����
		if (list == null || list.size() == 0) {
			// �û������ڣ���¼ʧ��
			request.setAttribute("error", "�û��������벻��ȷ����������");
			return "error";
		} else {
			// �û����ڣ���¼�ɹ�
			user = list.get(0);
			hSession.setAttribute("user", user);
			// �ж��û��Ƿ��ɵ������ť��ת����¼�����
			if (proid != null && !"".equals(proid)) {
				// ������Ʒid��ѯ��Ʒ
				IBaseService<TbProduct> proService = ac.getBean("baseService", IBaseService.class);
				TbProduct product = proService.findByID(Long.valueOf(proid), TbProduct.class);

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
				// �ѻ�ȡ����Ʒ���󴫵�request
				request.setAttribute("product", product);
				// �ѳ�����Ʒ�û�����Ϣ�ȼ�����request
				request.setAttribute("level", product.getUser().getCreditLevel());
				// ����Ϸ�˻����󴫵�request
				request.setAttribute("gameAccount", product.getAccount());
				return "shop";
			} else {
				// �û�ֻ����ͨ�ĵ�¼��ֱ������������
				return "login";
			}
		}
	}

	/**
	 * �û�ע��
	 * @return
	 */
	public String register() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// ע�ᣬ�Ȼ�ȡ����
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String gender = request.getParameter("sex");
		String year = request.getParameter("year");
		String idCard = request.getParameter("identity");
		String phone = request.getParameter("mobile");
		String qq = request.getParameter("qq");

		// ���������ȡ��ǰ���
		Calendar calendar = Calendar.getInstance();
		int nowYear = calendar.get(Calendar.YEAR);

		// �½��û��������ݲ���
		TbUser user = new TbUser();
		user.setName(username);
		user.setPassword(password);
		if (gender.equals("0")) {
			user.setGender("��");
		} else {
			user.setGender("Ů");
		}
		user.setAge(nowYear - Integer.valueOf(year));
		user.setIdCard(idCard);
		user.setPhone(phone);
		user.setQq(qq);
		user.setTradeCount(0); // ���״���
		user.setCreditLevel(0); // �����ȼ�
		user.setBalance(0); // �˻����
		// ���û�д�����ݿ�
		userService.save(user);

		// ��ȡsession���û���¼
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		return "register";
	}

	/**
	 * �û��˳�
	 * @return
	 */
	public String logout() {
		// �˳�
		HttpServletRequest request = ServletActionContext.getRequest();
		// 1.��ȡ��ǰ��¼�û������session����
		HttpSession session = request.getSession(false);
		// 2.����
		session.invalidate();
		return "logout";
	}

	/**
	 * �û���ֵ
	 * @return
	 */
	public String money() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		TbUser user = (TbUser) session.getAttribute("user");
		String sum = request.getParameter("sum");
		// ����id��ȡ�û�
		user = userService.findByID(user.getId(), TbUser.class);

		if (!"".equals(sum)) {
			user.setBalance(user.getBalance() + Integer.valueOf(sum));
			userService.update(user);
		}
		// ��ֵ������Ҫ�ѽ����£����԰�user��������д��session
		session.setAttribute("user", user);
		return "money";
	}
}
