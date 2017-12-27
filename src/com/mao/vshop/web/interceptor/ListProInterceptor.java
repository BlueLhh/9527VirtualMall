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
 * listҳ�����������������listҳ��ǰ��������������� 
 * ��listҳ���ѳ���Ӧ����Ʒ
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

		// ��ѯ���
		String seletHQL = "from TbProduct where 1=1 and status=0 and stock>0";
		// ��̬����
		Object[] obj = new Object[0];
		if (keyword != null && !keyword.equals("")) {
			seletHQL += " and PRODUCT_NAME like ? or DESCRIBE like ?";
			// ��Ҫ������Σ���Ϊ������ռλ��
			obj = new Object[2];
			obj[0] = "%" + keyword + "%";
			obj[1] = "%" + keyword + "%";
		}
		// ��ѯ��Ʒ
		List<TbProduct> plist = service.findByHql(seletHQL, obj);

		// ����ɸѡ���ķ�����Ϸ������Ʒ
		List<TbProduct> selectPro = new ArrayList<TbProduct>();
		// ����������Ϸ����Ϊ��
		if (game_name != null && !game_name.equals("")) {
			for (TbProduct tbProduct : plist) {
				TbGameAccount account = tbProduct.getAccount();
				if (account.getGame_name().equals(game_name)) {
					selectPro.add(tbProduct);
				}
			}
		} else {
			// ��Ϸ��Ϊ�գ���ԭ�ⲻ���İ��丳ֵ��selectPro
			selectPro.addAll(plist);
		}

		// ����ɸѡ��������Ʒ����
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
		String invoke = invocation.invoke();
		return invoke;
	}

	@Override
	public void destroy() {

	}
}
