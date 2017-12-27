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
 * 订单的Action
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
	 * 订单下单
	 */
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获取session对象
		HttpSession session = request.getSession(false);
		// 获取当前登录用户信息
		TbUser user = (TbUser) session.getAttribute("user");
		// 获取当前商品id
		Long proid = Long.valueOf(request.getParameter("proid"));
		// 获取联系电话
		String phone = request.getParameter("phone");
		// 获取QQ
		String qq = request.getParameter("qq");
		// 获取收货角色
		String rolename = request.getParameter("rolename");
		// 获取购买数量
		int account = Integer.valueOf(request.getParameter("account"));

		// 根据商品id获取商品对象
		TbProduct product = proService.findByID(proid, TbProduct.class);

		// 开始把相关信息写入订单对象
		TbOrder order = new TbOrder();
		order.setUser(user);
		order.setPhone(phone);
		order.setQq(qq);
		order.setShopDate(new java.sql.Timestamp(new Date().getTime()));
		order.setStatus(0);

		// 把相关信息写入订单子项表
		TbOrderItem orderItem = new TbOrderItem();
		orderItem.setOrder(order);
		orderItem.setProduct(product);
		orderItem.setRole_name(rolename);
		orderItem.setCount(account);

		// 订单对象把订单子项关联
		order.getItems().add(orderItem);
		// 写入数据库
		orderService.save(order);

		// 最后把商品的库存减去购买数量；如果库存数量为0，还需要把交易状态改变
		product.setStock(product.getStock() - account);
		if (product.getStock() == 0) {
			product.setStatus(2);
		}
		// 把订单子项关联
		product.getItems().add(orderItem);
		// 更新商品信息
		proService.update(product);

		// 最后把用户的信息更新
		// 1.计算总花费
		int sum = account * product.getPrice();
		// 2.用户余额减少
		user.setBalance(user.getBalance() - sum);
		// 3.交易次数+1
		user.setTradeCount(user.getTradeCount() + 1);
		// 4.把订单的关联
		user.getOlist().add(order);
		// 5.更新用户信息
		userService.update(user);
		return SUCCESS;
	}

	/**
	 * 操作订单，撤销，交易完成
	 * @return
	 */
	public String option() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		// 获取登录用户
		TbUser user = (TbUser) session.getAttribute("user");
		// 获取订单id
		Long orderid = Long.valueOf(request.getParameter("orderid"));
		// 商品id
		Long proid = Long.valueOf(request.getParameter("proid"));
		// 获取购买数量
		int count = Integer.valueOf(request.getParameter("count"));

		// 根据商品id查询商品
		TbProduct product = proService.findByID(proid, TbProduct.class);
		// 根据商品获取出售人
		TbUser sellUser = product.getUser();

		// 根据orderid查询订单
		TbOrder order = orderService.findByID(orderid, TbOrder.class);

		// option为用户进行的操作
		String option = request.getParameter("option");
		// 判断是撤销订单还是确认收货
		if ("cel".equals(option)) {
			// 撤销订单
			// 如果商品状态为交易完成，则还需把其状态改为拍卖中
			if (product.getStatus() == 2) {
				product.setStatus(0);
			}
			// 商品库存+购买的数量
			product.setStock(product.getStock() + count);
			// 同时登录用户将会收到退款 (商品数量*单价)
			user.setBalance(user.getBalance() + (count * product.getPrice()));
			// 同时自己的交易次数-1（因为在拍卖时交易次数已经加了一次）
			user.setTradeCount(user.getTradeCount() - 1);

			// 更新商品库存
			proService.update(product);

			// 更新用户账号余额，交易次数
			userService.update(user);

			// 最后把订单状态改为，已撤销
			order.setStatus(-1);
			orderService.update(order);
			return SUCCESS;
		} else {
			// 确认收货
			// 出售商品的卖家收到货款
			sellUser.setBalance(sellUser.getBalance() + (count * product.getPrice()));
			// 卖家的交易次数+1
			sellUser.setTradeCount(sellUser.getTradeCount() + 1);

			// 更新出售人的账号余额
			userService.update(sellUser);

			// 更新订单状态，交易完成
			order.setStatus(2);
			orderService.update(order);
			return SUCCESS;
		}
	}
}
