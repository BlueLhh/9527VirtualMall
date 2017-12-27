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
 * 商品下订单时的拦截器
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
		// 获取商品id
		Long proid = Long.valueOf(request.getParameter("proid"));

		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		IBaseService<TbProduct> service = ac.getBean("baseService", IBaseService.class);
		// 根据商品id查询商品
		TbProduct product = service.findByID(proid, TbProduct.class);

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

		// 获取该商品的出售用户
		TbUser sellUser = product.getUser();
		// 查询商品所属的游戏
		TbGameAccount gameAccount = product.getAccount();
		// 把获取到商品对象传到request
		request.setAttribute("product", product);
		// 把出售商品用户的信息等级传到request
		request.setAttribute("level", sellUser.getCreditLevel());
		// 把游戏账户对象传到request
		request.setAttribute("gameAccount", gameAccount);
		String invoke = invocation.invoke();
		return invoke;
	}

	@Override
	public void destroy() {

	}
}
