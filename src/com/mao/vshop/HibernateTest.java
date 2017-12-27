package com.mao.vshop;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class HibernateTest<T> {
	// 加上Test注解，可以使用单元测试
	// 注意，单元测试的方法没有返回值和参数
	// 选中某个单元测试方法，再右键运行
	@Test
	public void test() {
//		加载配置文件，默认加载src下的hibernate.xml文件
//		也可以通过configure()方法的入参，改变配置文件的路径和名字
		Configuration cfg=new Configuration().configure();
		//解析配置文件
		SchemaExport se=new SchemaExport(cfg);
		//通过配置文件，创建数据库
		//第一个参数，表示是否打印DDL语句
		//第二个参数，表示是否删除已存在的表
		se.create(true,true);
	}
}
