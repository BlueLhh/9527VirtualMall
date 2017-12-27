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
 * 获取我出售商品的拦截器
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
		// 获取当前登录用户
		HttpSession session = request.getSession(false);
		TbUser user = (TbUser) session.getAttribute("user");
		user = userService.findByID(user.getId(), TbUser.class);
		session.setAttribute("user", user);
		// 获取商品  TODO 无法关联对象？订单与商品都无法更新
		Set<TbProduct> plist = user.getPlist();

		// 遍历商品集合
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
			// 专门查询商品成交笔数，所有直接使用商品Dao类
			int sellCount = proDao.findSellCountById(product.getId());
			product.setSellCount(sellCount);
		}

		// 把商品集合传到req对象
		request.setAttribute("plist", plist);
		String invoke = invocation.invoke();
		return invoke;
	}

	@Override
	public void destroy() {

	}
}
