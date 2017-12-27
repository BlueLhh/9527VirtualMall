package com.mao.vshop.web.action;

import java.util.List;

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
import com.opensymphony.xwork2.ActionSupport;

public class SellProStatusAction<T> extends ActionSupport {

	private ApplicationContext ac;
	private IBaseService<TbProduct> proService;
	@Autowired
	private IProductDao proDao;

	@Override
	public void validate() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		proService = ac.getBean("baseService", IBaseService.class);
		super.validate();
	}

	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	/**
	 * 根据菜单状态显示商品
	 * @return
	 */
	public String option() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获取当前登录用户
		HttpSession session = request.getSession(false);
		TbUser user = (TbUser) session.getAttribute("user");
		// 获取菜单栏的选择项
		String status = request.getParameter("status");

		// 查询语句
		String selectHQL = "from TbProduct where user_id=?";
		Object[] obj = new Object[1];
		obj[0] = user.getId() + "";
		if (status != null && !"".equals(status)) {
			selectHQL += " and status=" + status + "";
		}
		// 开始查询
		List<TbProduct> plist = proService.findByHql(selectHQL, obj);

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
		request.setAttribute("status", status);
		return SUCCESS;
	}
}
