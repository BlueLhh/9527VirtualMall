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
 * 出售商品的Action
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
	 * 商品出售
	 */
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获取当前用户
		HttpSession session = request.getSession(false);
		TbUser user = (TbUser) session.getAttribute("user");
		// 获取商品名
		String proname = request.getParameter("proname");
		// 获取出售数量（即库存）
		String stock = request.getParameter("count");
		// 获取商品单价
		String price = request.getParameter("price");
		// 获取游戏币金额
		String goldensum = request.getParameter("goldensum");
		// 获取商品描述
		String describe = request.getParameter("describe");
		// 如果商品为游戏币，则把获取的金币数额接到商品描述之后按“#”符号分开
		if ("游戏币".equals(proname)) {
			describe += "#" + goldensum + "W金币";
		}
		// 获取游戏名
		String game_name = request.getParameter("gamename");
		// 获取游戏大区
		String game_region = request.getParameter("gameregion");
		// 获取游戏服务器
		String game_server = request.getParameter("gameserver");
		// 获取游戏账号
		String qq = request.getParameter("qq");
		// 获取游戏账号密码
		String password = request.getParameter("password");
		// 获取二级密码
		String sepassword = request.getParameter("sepassword");
		// 获取角色名
		String rolename = request.getParameter("rolename");

		// 把账号信息写入
		TbGameAccount gameAccount = new TbGameAccount();
		gameAccount.setGame_name(game_name);
		gameAccount.setGame_region(game_region);
		gameAccount.setGame_server(game_server);
		gameAccount.setGame_account(qq);
		gameAccount.setGame_password(password);
		gameAccount.setSecond_password(sepassword);
		gameAccount.setRole_name(rolename);

		// 商品信息写入
		TbProduct product = new TbProduct();
		product.setUser(user);
		product.setAccount(gameAccount);
		product.setProduct_name(proname);
		product.setStock(Integer.valueOf(stock));
		product.setPrice(Integer.valueOf(price));
		product.setUp_date(new java.sql.Timestamp(new Date().getTime()));
		product.setStatus(0);
		product.setDescribe(describe);
		if ("地下城与勇士".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img1.jpg");
		} else if ("征途2".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img2.jpg");
		} else if ("王者农药".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img3.jpg");
		} else if ("英雄联盟".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img4.jpg");
		} else if ("阴阳师".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img6.jpg");
		} else if ("冒险岛2".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img7.jpg");
		} else if ("崩坏3".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img8.jpg");
		} else if ("蜀门".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img9.jpg");
		} else if ("剑侠情缘".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img10.jpg");
		} else if ("穿越火线：枪战王者".equals(gameAccount.getGame_name())) {
			product.setFileName("assets/images/fc-img11.jpg");
		}

		// 写入商品数据
		proService.save(product);

		// 把商品关联到用户
		user.getPlist().add(product);
		userService.update(user);
		return SUCCESS;
	}

	/**
	 * 操作商品的状态，取消上架
	 * @return
	 */
	public String down() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 商品id
		Long proid = Long.valueOf(request.getParameter("proid"));
		String option = request.getParameter("option");

		// 获取商品
		TbProduct product = proService.findByID(proid, TbProduct.class);

		if ("down".equals(option)) {
			// 商品取消上架
			// 改变其交易状态
			product.setStatus(-1);
			proService.update(product);
		}
		return SUCCESS;
	}
}
