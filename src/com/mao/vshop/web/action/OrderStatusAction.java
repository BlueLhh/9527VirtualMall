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
 * ���ݶ���״̬��ʾ��Ʒ
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
	 * ���ݲ˵�״̬��ʾ����
	 * @return
	 */
	public String option() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// ͨ��session��ȡ��ǰ��¼�û�
		HttpSession session = request.getSession(false);
		TbUser user = (TbUser) session.getAttribute("user");
		// ��ȡѡ�еĶ���״̬�˵���
		String status = request.getParameter("status");

		Set<TbOrder> list = user.getOlist();
		// ������϶���״̬�ļ���
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

		// ���涩������ļ���
		List<TbOrderItem> oilist = new ArrayList<TbOrderItem>();
		// ����������ȡ�䶩������
		for (TbOrder tbOrder : olist) {
			Set<TbOrderItem> items = tbOrder.getItems();
			for (TbOrderItem tbOrderItem : items) {
				oilist.add(tbOrderItem);
			}
		}

		// ������Ʒ�ļ���
		List<TbProduct> plist = new ArrayList<TbProduct>();
		// �����Ӷ�������ȡ��Ӧ����Ʒ
		for (TbOrderItem tbOrderItem : oilist) {
			TbProduct product = tbOrderItem.getProduct();
			plist.add(product);
		}

		// ������Ʒ
		for (TbProduct product : plist) {
			// ��Ϊ���ڽ�ң����԰���Ʒ������Ϣ��������
			String describe = product.getDescribe();
			// �������Ʒ��������Ϸ�������Ʒ��������#���ֿ�
			// ��Ϊ���ϼ���Ʒʱ���Ͱѽ�����������Ʒ��������
			if (product.getProduct_name().equals("��Ϸ��")) {
				String[] split = describe.split("#");
				// ǰ���������������ǽ������
				product.setProduct_name(split[1]);
				product.setDescribe(split[0]);
			}
		}

		/**
		 * ���req���붩���������
		 */
		request.setAttribute("oilist", oilist);
		request.setAttribute("status", Integer.valueOf(status));
		return SUCCESS;
	}
}
