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
 * 验证注册的用户名是否存在
 * @author Administrator
 * @param <T>
 *
 */
@SuppressWarnings("serial")
public class CheckUserNameAction extends ActionSupport {

	// 判断用户是否存在的字段
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
		// 查询语句
		String selectHQL = "from TbUser where name=?";
		// 查询条件
		Object[] obj = new Object[1];
		obj[0] = username;

		// 载入配置文件，获取bean
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		IBaseService<TbUser> service = ac.getBean("baseService", IBaseService.class);
		// 开始查询
		List<TbUser> list = service.findByHql(selectHQL, obj);

		// 根据结果判断用户是否存在
		if (list == null || list.size() == 0) {
			exist = "yes";
		} else {
			exist = "no";
		}
		return "ajax";
	}
}
