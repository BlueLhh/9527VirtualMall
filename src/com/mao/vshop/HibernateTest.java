package com.mao.vshop;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class HibernateTest<T> {
	// ����Testע�⣬����ʹ�õ�Ԫ����
	// ע�⣬��Ԫ���Եķ���û�з���ֵ�Ͳ���
	// ѡ��ĳ����Ԫ���Է��������Ҽ�����
	@Test
	public void test() {
//		���������ļ���Ĭ�ϼ���src�µ�hibernate.xml�ļ�
//		Ҳ����ͨ��configure()��������Σ��ı������ļ���·��������
		Configuration cfg=new Configuration().configure();
		//���������ļ�
		SchemaExport se=new SchemaExport(cfg);
		//ͨ�������ļ����������ݿ�
		//��һ����������ʾ�Ƿ��ӡDDL���
		//�ڶ�����������ʾ�Ƿ�ɾ���Ѵ��ڵı�
		se.create(true,true);
	}
}
