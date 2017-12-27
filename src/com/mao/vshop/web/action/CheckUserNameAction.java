package com.mao.vshop.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mao.vshop.model.pojo.TbUser;
import com.mao.vshop.model.service.IBaseService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ��֤ע����û����Ƿ����
 * @author Administrator
 * @param <T>
 *
 */
@SuppressWarnings("serial")
public class CheckUserNameAction extends ActionSupport {

	// �ж��û��Ƿ���ڵ��ֶ�
	private String exist;

	public String getExist() {
		return exist;
	}

	@Override
	public void validate() {
		super.validate();
	}

	@SuppressWarnings({ "resource", "unchecked" })
	public <T> String check() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		// ��ѯ���
		String selectHQL = "from TbUser where name=?";
		// ��ѯ����
		Object[] obj = new Object[1];
		obj[0] = username;

		// ���������ļ�����ȡbean
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		IBaseService<TbUser> service = ac.getBean("baseService", IBaseService.class);
		// ��ʼ��ѯ
		List<TbUser> list = service.findByHql(selectHQL, obj);

		// ���ݽ���ж��û��Ƿ����
		if (list == null || list.size() == 0) {
			exist = "yes";
		} else {
			exist = "no";
		}
		return "ajax";
	}
}
