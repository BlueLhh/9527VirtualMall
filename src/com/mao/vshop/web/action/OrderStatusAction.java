package com.mao.vshop.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.mao.vshop.model.dao.IProductDao;
import com.mao.vshop.model.dao.impl.ProductDaoImpl;
import com.mao.vshop.model.pojo.TbOrder;
import com.mao.vshop.model.pojo.TbOrderItem;
import com.mao.vshop.model.pojo.TbProduct;
import com.mao.vshop.model.pojo.TbUser;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 根据订单状态显示商品
 * @author Administrator
 *
 */
public class OrderStatusAction extends ActionSupport {

	@Override
	public void validate() {
		super.validate();
	}

	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	/**
	 * 根据菜单状态显示订单
	 * @return
	 */
	public String option() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 通过session获取当前登录用户
		HttpSession session = request.getSession(false);
		TbUser user = (TbUser) session.getAttribute("user");
		// 获取选中的订单状态菜单项
		String status = request.getParameter("status");

		Set<TbOrder> list = user.getOlist();
		// 保存符合订单状态的集合
		List<TbOrder> olist = new ArrayList<TbOrder>();
		if (status != null && !"".equals(status)) {
			for (TbOrder tbOrder : list) {
				if (tbOrder.getStatus() == Integer.valueOf(status)) {
					olist.add(tbOrder);
				}
			}
		} else {
			for (TbOrder tbOrder : list) {
				olist.add(tbOrder);
			}
		}

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
		request.setAttribute("status", Integer.valueOf(status));
		return SUCCESS;
	}
}
