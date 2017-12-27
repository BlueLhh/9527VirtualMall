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
	 * ������ʾ��Ʒ
	 * @return
	 */
	public String cate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String game_name = request.getParameter("gamename");
		String keyword = request.getParameter("keyword");
		// ��ȡ�������������
		String proname = request.getParameter("cate");

		// ��ѯ���
		String selectSQL = "select * from TB_PRODUCT where 1=1 and status=0 and stock>0";
		// ��̬����
		List<String> params = new ArrayList<String>();
		if (keyword != null && !keyword.equals("")) {
			selectSQL += " and PRODUCT_NAME like ? or DESCRIBE like ?";
			// ��Ҫ������Σ���Ϊ������ռλ��
			params.add("%" + keyword + "%");
			params.add("%" + keyword + "%");
		}
		selectSQL = "select * from (" + selectSQL + ") where PRODUCT_NAME like ?";
		params.add("%" + proname + "%");
		// ������Ϸ����ѯ
		if (game_name != null && !game_name.equals("")) {
			selectSQL += " and GAME_ACCOUNT_ID in (select id from TB_GAME_ACCOUNT where game_name=?)";
			params.add(game_name);
		}
		// ��ѯ��Ʒ
		List<TbProduct> selectPro = service.findByCate(selectSQL, params);

		// ����ɸѡ��������Ʒ����,ɸѡ����Ʒ�Ƿ�Ϊ���
		for (TbProduct product : selectPro) {
			// ��Ϊ���ڽ�����԰���Ʒ������Ϣ��������
			String describe = product.getDescribe();
			// �������Ʒ��������Ϸ�������Ʒ��������#���ֿ�
			// ��Ϊ���ϼ���Ʒʱ���Ͱѽ�����������Ʒ��������
			if (product.getProduct_name().equals("��Ϸ��")) {
				String[] split = describe.split("#");
				// ǰ���������������ǽ������
				product.setDescribe(split[0]);
				product.setProduct_name(split[1]);
			}
		}

		// �жϲ�ѯ����������Ҫ�ּ�ҳ
		int pageCount = selectPro.size() % 10 == 0 ? selectPro.size() / 10 : selectPro.size() / 10 + 1;
		// ��ɸѡ�õ���Ʒ���ϴ���request����
		request.setAttribute("selectPro", selectPro);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("proname", proname);
		return "cate";
	}

	/**
	 * ��ҳ��ʾ��Ʒ
	 * @return
	 */
	public String page() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String game_name = request.getParameter("gamename");
		String keyword = request.getParameter("keyword");
		// ��ȡ�������������
		String proname = request.getParameter("cate");
		// ��ȡҳ��
		int page = Integer.valueOf(request.getParameter("page"));

		// ��ѯ���
		String selectSQL = "select * from TB_PRODUCT where 1=1 and status=0 and stock>0";
		// ��̬����
		List<String> params = new ArrayList<String>();
		if (keyword != null && !keyword.equals("")) {
			selectSQL += " and (PRODUCT_NAME like ? or DESCRIBE like ?)";
			// ��Ҫ������Σ���Ϊ������ռλ��
			params.add("%" + keyword + "%");
			params.add("%" + keyword + "%");
		}
		// �������ʹ���Ӳ�ѯ
		if (proname != null && !proname.equals("")) {
			selectSQL = "select * from (" + selectSQL + ") where PRODUCT_NAME like ?";
			params.add("%" + proname + "%");
		}
		// ������Ϸ����ѯ
		if (game_name != null && !game_name.equals("")) {
			selectSQL += " and GAME_ACCOUNT_ID in (select id from TB_GAME_ACCOUNT where game_name=?)";
			params.add(game_name);
		}
		// ��ǰ��ҳ��Ҫ��ʾ����Ʒ
		List<TbProduct> selectPro = service.findByPage(selectSQL, params, page, 10);
		// ����������������Ʒ����������������ҳ��
		List<TbProduct> count = service.findByCate(selectSQL, params);

		// ����ɸѡ��������Ʒ����,ɸѡ����Ʒ�Ƿ�Ϊ���
		for (TbProduct product : selectPro) {
			// ��Ϊ���ڽ�����԰���Ʒ������Ϣ��������
			String describe = product.getDescribe();
			// �������Ʒ��������Ϸ�������Ʒ��������#���ֿ�
			// ��Ϊ���ϼ���Ʒʱ���Ͱѽ�����������Ʒ��������
			if (product.getProduct_name().equals("��Ϸ��")) {
				String[] split = describe.split("#");
				// ǰ���������������ǽ������
				product.setDescribe(split[0]);
				product.setProduct_name(split[1]);
			}
		}

		// �жϲ�ѯ������������Ҫ�ּ�ҳ
		int pageCount = count.size() % 10 == 0 ? count.size() / 10 : count.size() / 10 + 1;
		// ��ɸѡ�õ���Ʒ���ϴ���request����
		request.setAttribute("selectPro", selectPro);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("proname", proname);
		return "page";
	}
}
