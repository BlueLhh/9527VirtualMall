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
		// 获取跳转的地址栏
		String header = request.getHeader("referer");
		if (header.contains("proid")) {
			String[] split = header.split("proid=");
			// 根据proid=分割地址栏，下标为“1”的字符就为当时需要购买的商品id
			proid = split[1];
		}
	}

	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	/**
	 * 用户登录
	 * @return
	 */
	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession hSession = request.getSession();
		// 用户名
		String username = request.getParameter("username");
		// 密码
		String password = request.getParameter("password");
		String selectHQL = "from TbUser where name=? and password=?";

		// 查询条件
		Object[] obj = new Object[2];
		obj[0] = username;
		obj[1] = password;
		// 开始查询
		List<TbUser> list = userService.findByHql(selectHQL, obj);

		// 判断是否存在
		if (list == null || list.size() == 0) {
			// 用户不存在，登录失败
			request.setAttribute("error", "用户名或密码不正确请重新输入");
			return "error";
		} else {
			// 用户存在，登录成功
			user = list.get(0);
			hSession.setAttribute("user", user);
			// 判断用户是否由点击购买按钮跳转到登录界面的
			if (proid != null && !"".equals(proid)) {
				// 根据商品id查询商品
				IBaseService<TbProduct> proService = ac.getBean("baseService", IBaseService.class);
				TbProduct product = proService.findByID(Long.valueOf(proid), TbProduct.class);

				// 如果是商品类型是游戏币则把商品描述按“#”分开
				// 因为在上架商品时，就把金币数额接在商品描述后面
				if (product.getProduct_name().equals("游戏币")) {
					// 因为存在金币；所以把商品描述信息进行重组
					String describe = product.getDescribe();
					String[] split = describe.split("#");
					// 前面是描述，后面是金币数额
					product.setProduct_name(split[1]);
					product.setDescribe(split[0]);
				}
				// 把获取到商品对象传到request
				request.setAttribute("product", product);
				// 把出售商品用户的信息等级传到request
				request.setAttribute("level", product.getUser().getCreditLevel());
				// 把游戏账户对象传到request
				request.setAttribute("gameAccount", product.getAccount());
				return "shop";
			} else {
				// 用户只是普通的登录，直接跳到主界面
				return "login";
			}
		}
	}

	/**
	 * 用户注册
	 * @return
	 */
	public String register() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 注册，先获取数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String gender = request.getParameter("sex");
		String year = request.getParameter("year");
		String idCard = request.getParameter("identity");
		String phone = request.getParameter("mobile");
		String qq = request.getParameter("qq");

		// 用日历类获取当前年份
		Calendar calendar = Calendar.getInstance();
		int nowYear = calendar.get(Calendar.YEAR);

		// 新建用户，将数据插入
		TbUser user = new TbUser();
		user.setName(username);
		user.setPassword(password);
		if (gender.equals("0")) {
			user.setGender("男");
		} else {
			user.setGender("女");
		}
		user.setAge(nowYear - Integer.valueOf(year));
		user.setIdCard(idCard);
		user.setPhone(phone);
		user.setQq(qq);
		user.setTradeCount(0); // 交易次数
		user.setCreditLevel(0); // 信誉等级
		user.setBalance(0); // 账户余额
		// 将用户写入数据库
		userService.save(user);

		// 获取session，用户登录
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		return "register";
	}

	/**
	 * 用户退出
	 * @return
	 */
	public String logout() {
		// 退出
		HttpServletRequest request = ServletActionContext.getRequest();
		// 1.获取当前登录用户保存的session对象
		HttpSession session = request.getSession(false);
		// 2.销毁
		session.invalidate();
		return "logout";
	}

	/**
	 * 用户充值
	 * @return
	 */
	public String money() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		TbUser user = (TbUser) session.getAttribute("user");
		String sum = request.getParameter("sum");
		// 根据id获取用户
		user = userService.findByID(user.getId(), TbUser.class);

		if (!"".equals(sum)) {
			user.setBalance(user.getBalance() + Integer.valueOf(sum));
			userService.update(user);
		}
		// 充值金额后，需要把金额更新；所以把user对象重新写入session
		session.setAttribute("user", user);
		return "money";
	}
}
