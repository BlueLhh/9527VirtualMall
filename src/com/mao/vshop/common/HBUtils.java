package com.mao.vshop.common;
/**
 * 加载配置文件的工具类
 * @author Administrator
 *
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("hbUtils")
public class HBUtils {

	@Autowired
	private SessionFactory factory = null;

	/**
	 * 获取session
	 * 
	 * @return
	 */
	public Session getSession() {
		return factory.openSession();
	}
}
