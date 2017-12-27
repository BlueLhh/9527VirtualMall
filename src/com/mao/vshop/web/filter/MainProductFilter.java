package com.mao.vshop.web.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mao.vshop.model.pojo.TbProduct;
import com.mao.vshop.model.service.IBaseService;

/**
 * ������ҳ�棬����ҳ������ʾ������Ʒ
 * 
 * @author Administrator
 * @param <T>
 *
 */
@WebFilter("/main.jsp")
public class MainProductFilter<T> implements Filter {

	public MainProductFilter() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		IBaseService<TbProduct> service = ac.getBean("baseService", IBaseService.class);

		String findHQL = "from TbProduct where stock>0 and status=0 and rownum<10";
		// ��ҳ��ʾ����Ʒ��������Ϊ�գ������������һ��������
		Object[] obj = new Object[0];
		List<TbProduct> plist = (List<TbProduct>) service.findByHql(findHQL, obj);

		request.setAttribute("plist", plist);
		chain.doFilter(request, response);
	}

	public void destroy() {
	}
}
