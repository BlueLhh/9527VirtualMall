package com.mao.vshop.common;
/**
 * ���������ļ��Ĺ�����
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
	 * ��ȡsession
	 * 
	 * @return
	 */
	public Session getSession() {
		return factory.openSession();
	}
}
