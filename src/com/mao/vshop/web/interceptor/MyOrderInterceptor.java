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
 * 获取我购买商品的拦截器
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
		// 通过session获取当前登录用户
		HttpSession session = request.getSession(false);
		TbUser user = (TbUser) session.getAttribute("user");
		user = userService.findByID(user.getId(), TbUser.class);
		session.setAttribute("user", user);
		// 获得订单  TODO
		Set<TbOrder> olist = user.getOlist();
		
		// 保存订单子项的集合
		List<TbOrderItem> oilist = new ArrayList<TbOrderItem>();
		// 遍历订单获取其订单子项
		for (TbOrder tbOrder : olist) {
			Set<TbOrderItem> items = tbOrder.getItems();
			for (TbOrderItem tbOrderItem : items) {
				oilist.add(tbOrderItem);
			}
		}

		// 保存商品的集合
		List<TbProduct> plist = new ArrayList<TbProduct>();
		// 遍历子订单，获取对应的商品
		for (TbOrderItem tbOrderItem : oilist) {
			TbProduct product = tbOrderItem.getProduct();
			plist.add(product);
		}

		// 遍历商品
		for (TbProduct product : plist) {
			// 因为存在金币，所以把商品描述信息进行重组
			String describe = product.getDescribe();
			// 如果是商品类型是游戏币则把商品描述按“#”分开
			// 因为在上架商品时，就把金币数额接在商品描述后面
			if (product.getProduct_name().equals("游戏币")) {
				String[] split = describe.split("#");
				// 前面是描述，后面是金币数额
				product.setProduct_name(split[1]);
				product.setDescribe(split[0]);
			}
		}
		/**
		 * 最后req传入订单子项对象
		 */
		request.setAttribute("oilist", oilist);
		String invoke = invocation.invoke();
		return invoke;
	}

	@Override
	public void destroy() {

	}
}
