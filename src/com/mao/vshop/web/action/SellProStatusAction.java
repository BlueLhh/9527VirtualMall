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
	 * ���ݲ˵�״̬��ʾ��Ʒ
	 * @return
	 */
	public String option() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// ��ȡ��ǰ��¼�û�
		HttpSession session = request.getSession(false);
		TbUser user = (TbUser) session.getAttribute("user");
		// ��ȡ�˵�����ѡ����
		String status = request.getParameter("status");

		// ��ѯ���
		String selectHQL = "from TbProduct where user_id=?";
		Object[] obj = new Object[1];
		obj[0] = user.getId() + "";
		if (status != null && !"".equals(status)) {
			selectHQL += " and status=" + status + "";
		}
		// ��ʼ��ѯ
		List<TbProduct> plist = proService.findByHql(selectHQL, obj);

		// ������Ʒ����
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
			// ר�Ų�ѯ��Ʒ�ɽ�����������ֱ��ʹ����ƷDao��
			int sellCount = proDao.findSellCountById(product.getId());
			product.setSellCount(sellCount);
		}
		// ����Ʒ���ϴ���req����
		request.setAttribute("plist", plist);
		request.setAttribute("status", status);
		return SUCCESS;
	}
}
