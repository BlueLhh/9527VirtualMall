package com.mao.vshop.web.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mao.vshop.model.pojo.TbGameAccount;
import com.mao.vshop.model.pojo.TbProduct;
import com.mao.vshop.model.service.IBaseService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * list页面的拦截器，当进入list页面前根据搜索框的条件 
 * 在list页面搜出对应的商品
 * @author Administrator
 * @param <T>
 */
public class ListProInterceptor<T> implements Interceptor {

	@Override
	public void init() {

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String game_name = request.getParameter("gamename");
		String keyword = request.getParameter("keyword");

		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		IBaseService<TbProduct> service = ac.getBean("baseService", IBaseService.class);

		// 查询语句
		String seletHQL = "from TbProduct where 1=1 and status=0 and stock>0";
		// 动态条件
		Object[] obj = new Object[0];
		if (keyword != null && !keyword.equals("")) {
			seletHQL += " and PRODUCT_NAME like ? or DESCRIBE like ?";
			// 需要添加两次，因为有两个占位符
			obj = new Object[2];
			obj[0] = "%" + keyword + "%";
			obj[1] = "%" + keyword + "%";
		}
		// 查询商品
		List<TbProduct> plist = service.findByHql(seletHQL, obj);

		// 保存筛选出的符合游戏名的商品
		List<TbProduct> selectPro = new ArrayList<TbProduct>();
		// 如果输入的游戏名不为空
		if (game_name != null && !game_name.equals("")) {
			for (TbProduct tbProduct : plist) {
				TbGameAccount account = tbProduct.getAccount();
				if (account.getGame_name().equals(game_name)) {
					selectPro.add(tbProduct);
				}
			}
		} else {
			// 游戏名为空，则原封不动的把其赋值给selectPro
			selectPro.addAll(plist);
		}

		// 遍历筛选出来的商品集合
		for (TbProduct product : selectPro) {
			// 因为存在金币所以把商品描述信息进行重组
			String describe = product.getDescribe();
			// 如果是商品类型是游戏币则把商品描述按“#”分开
			// 因为在上架商品时，就把金币数额接在商品描述后面
			if (product.getProduct_name().equals("游戏币")) {
				String[] split = describe.split("#");
				// 前面是描述，后面是金币数额
				product.setDescribe(split[0]);
				product.setProduct_name(split[1]);
			}
		}

		// 判断查询到的数据需要分几页
		int pageCount = selectPro.size() % 10 == 0 ? selectPro.size() / 10 : selectPro.size() / 10 + 1;
		// 把筛选好的商品集合传到request对象
		request.setAttribute("selectPro", selectPro);
		request.setAttribute("pageCount", pageCount);
		String invoke = invocation.invoke();
		return invoke;
	}

	@Override
	public void destroy() {

	}
}
