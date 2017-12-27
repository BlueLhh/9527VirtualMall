package com.mao.vshop.web.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mao.vshop.model.pojo.TbGameAccount;
import com.mao.vshop.model.pojo.TbProduct;
import com.mao.vshop.model.pojo.TbUser;
import com.mao.vshop.model.service.IBaseService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ������Ʒ��Action
 * @author Administrator
 * @param <T>
 *
 */
public class SellProAction<T> extends ActionSupport {
	private ApplicationContext ac;
	private IBaseService<TbUser> userService;
	private IBaseService<TbProduct> proService;

	@Override
	public void validate() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		userService = ac.getBean("baseService", IBaseService.class);
		proService = ac.getBean("baseService", IBaseService.class);
		super.validate();
	}

	/**
	 * ��Ʒ����
	 */
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// ��ȡ��ǰ�û�
		HttpSession session = request.getSession(false);
		TbUser user = (TbUser) session.getAttribute("user");
		// ��ȡ��Ʒ��
		String proname = request.getParameter("proname");
		// ��ȡ��������������棩
		String stock = request.getParameter("count");
		// ��ȡ��Ʒ����
		String price = request.getParameter("price");
		// ��ȡ��Ϸ�ҽ��
		String goldensum = request.getParameter("goldensum");
		// ��ȡ��Ʒ����
		String describe = request.getParameter("describe");
		// �����ƷΪ��Ϸ�ң���ѻ�ȡ�Ľ������ӵ���Ʒ����֮�󰴡�#�����ŷֿ�
		if ("��Ϸ��".equals(proname)) {
			describe += "#" + goldensum + "W���";
		}
		// ��ȡ��Ϸ��
		String game_name = request.getParameter("gamename");
		// ��ȡ��Ϸ����
		String game_region = request.getParameter("gameregion");
		// ��ȡ��Ϸ������
		String game_server = request.getParameter("gameserver");
		// ��ȡ��Ϸ�˺�
		String qq = request.getParameter("qq");
		// ��ȡ��Ϸ�˺�����
		String password = request.getParameter("password");
		// ��ȡ��������
		String sepassword = request.getParameter("sepassword");
		// ��ȡ��ɫ��
		String rolename = request.getParameter("rolename");

		// ���˺���Ϣд��
		TbGameAccount gameAccount = new TbGameAccount();
		gameAccount.setGame_name(game_name);
		gameAccount.setGame_region(game_region);
		gameAccount.setGame_server(game_server);
		gameAccount.setGame_account(qq);
		gameAccount.setGame_password(password);
		gameAccount.setSecond_password(sepassword);
		gameAccount.setRole_name(rolename);

		// ��Ʒ��Ϣд��
		TbProduct product = new TbProduct();
		product.setUser(user);
		product.setAccount(gameAccount);
		product.setProduct_name(proname);
		product.setStock(Integer.valueOf(stock));
		product.setPrice(Integer.valueOf(price));
		product.setUp_date(new java.sql.Timestamp(new Date().getTime()));
		product.setStatus(0);
		product.setDescribe(describe);
		if ("���³�����ʿ".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img1.jpg");
		} else if ("��;2".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img2.jpg");
		} else if ("����ũҩ".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img3.jpg");
		} else if ("Ӣ������".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img4.jpg");
		} else if ("����ʦ".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img6.jpg");
		} else if ("ð�յ�2".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img7.jpg");
		} else if ("����3".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img8.jpg");
		} else if ("����".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img9.jpg");
		} else if ("������Ե".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img10.jpg");
		} else if ("��Խ���ߣ�ǹս����".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img11.jpg");
		}

		// д����Ʒ����
		proService.save(product);

		// ����Ʒ�������û�
		user.getPlist().add(product);
		userService.update(user);
		return SUCCESS;
	}

	/**
	 * ������Ʒ��״̬��ȡ���ϼ�
	 * @return
	 */
	public String down() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// ��Ʒid
		Long proid = Long.valueOf(request.getParameter("proid"));
		String option = request.getParameter("option");

		// ��ȡ��Ʒ
		TbProduct product = proService.findByID(proid, TbProduct.class);

		if ("down".equals(option)) {
			// ��Ʒȡ���ϼ�
			// �ı��佻��״̬
			product.setStatus(-1);
			proService.update(product);
		}
		return SUCCESS;
	}
}
