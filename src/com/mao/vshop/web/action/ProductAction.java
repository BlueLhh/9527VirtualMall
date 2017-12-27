package com.mao.vshop.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.mao.vshop.model.pojo.TbProduct;
import com.mao.vshop.model.service.IProductService;
import com.opensymphony.xwork2.ActionSupport;

public class ProductAction extends ActionSupport {

	@Autowired
	private IProductService service;

	@Override
	public void validate() {
		super.validate();
	}

	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	/**
	 * 分类显示商品
	 * @return
	 */
	public String cate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String game_name = request.getParameter("gamename");
		String keyword = request.getParameter("keyword");
		// 获取道具名（即类别）
		String proname = request.getParameter("cate");

		// 查询语句
		String selectSQL = "select * from TB_PRODUCT where 1=1 and status=0 and stock>0";
		// 动态条件
		List<String> params = new ArrayList<String>();
		if (keyword != null && !keyword.equals("")) {
			selectSQL += " and PRODUCT_NAME like ? or DESCRIBE like ?";
			// 需要添加两次，因为有两个占位符
			params.add("%" + keyword + "%");
			params.add("%" + keyword + "%");
		}
		selectSQL = "select * from (" + selectSQL + ") where PRODUCT_NAME like ?";
		params.add("%" + proname + "%");
		// 根据游戏名查询
		if (game_name != null && !game_name.equals("")) {
			selectSQL += " and GAME_ACCOUNT_ID in (select id from TB_GAME_ACCOUNT where game_name=?)";
			params.add(game_name);
		}
		// 查询商品
		List<TbProduct> selectPro = service.findByCate(selectSQL, params);

		// 遍历筛选出来的商品集合,筛选该商品是否为金币
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
		request.setAttribute("proname", proname);
		return "cate";
	}

	/**
	 * 分页显示商品
	 * @return
	 */
	public String page() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String game_name = request.getParameter("gamename");
		String keyword = request.getParameter("keyword");
		// 获取道具名（即类别）
		String proname = request.getParameter("cate");
		// 获取页码
		int page = Integer.valueOf(request.getParameter("page"));

		// 查询语句
		String selectSQL = "select * from TB_PRODUCT where 1=1 and status=0 and stock>0";
		// 动态条件
		List<String> params = new ArrayList<String>();
		if (keyword != null && !keyword.equals("")) {
			selectSQL += " and (PRODUCT_NAME like ? or DESCRIBE like ?)";
			// 需要添加两次，因为有两个占位符
			params.add("%" + keyword + "%");
			params.add("%" + keyword + "%");
		}
		// 根据类别使用子查询
		if (proname != null && !proname.equals("")) {
			selectSQL = "select * from (" + selectSQL + ") where PRODUCT_NAME like ?";
			params.add("%" + proname + "%");
		}
		// 根据游戏名查询
		if (game_name != null && !game_name.equals("")) {
			selectSQL += " and GAME_ACCOUNT_ID in (select id from TB_GAME_ACCOUNT where game_name=?)";
			params.add(game_name);
		}
		// 当前分页需要显示的商品
		List<TbProduct> selectPro = service.findByPage(selectSQL, params, page, 10);
		// 符合条件的所有商品，最后根据数量来分页码
		List<TbProduct> count = service.findByCate(selectSQL, params);

		// 遍历筛选出来的商品集合,筛选该商品是否为金币
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

		// 判断查询到的总数据需要分几页
		int pageCount = count.size() % 10 == 0 ? count.size() / 10 : count.size() / 10 + 1;
		// 把筛选好的商品集合传到request对象
		request.setAttribute("selectPro", selectPro);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("proname", proname);
		return "page";
	}
}
