package com.mao.vshop.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mao.vshop.model.pojo.TbOrder;
import com.mao.vshop.model.pojo.TbProduct;
import com.mao.vshop.model.pojo.TbUser;
import com.mao.vshop.model.service.IBaseService;
import com.mao.vshop.model.service.IProductService;

public class TestUser {

	private ApplicationContext ac;

	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void login() {
		String selectHQL = "from TbUser where name=? and password=?";

		Object[] obj = new Object[2];
		obj[0] = "r";
		obj[1] = "1";

		IBaseService<TbUser> service = ac.getBean("baseService", IBaseService.class);

		List<TbUser> list = service.findByHql(selectHQL, obj);
		// TbUser user = service.findByID(81L, TbUser.class);

		// System.out.println(user.toString());
		System.out.println("user--------" + list.get(0).toString());

		Set<TbOrder> olist = list.get(0).getOlist();
		for (TbOrder tbOrder : olist) {
			System.out.println("tbOrder-----" + tbOrder);
		}

		// д���û�
		TbUser tbUser = new TbUser();
		tbUser.setName("ssssssss");
		tbUser.setAge(12);
		tbUser.setPassword("11111111");
		service.save(tbUser);

		System.out.println("tbUser-------" + tbUser);
	}

	public void findPro() {
		System.out.println("--------------------------------------------------------------------------------");
		IProductService service2 = ac.getBean("proService", IProductService.class);
		String game_name = "���³�����ʿ";
		String keyword = "";
		// ��ȡ�������������
		String proname = "��Ϸ��";
		// ��ѯ���
		String selectSQL = "select * from TB_PRODUCT where 1=1 and status=0 and stock>0";
		// ��̬����
		List<String> params = new ArrayList<String>();
		if (keyword != null && !keyword.equals("")) {
			selectSQL += " and PRODUCT_NAME like ? or DESCRIBE like ?";
			// ��Ҫ������Σ���Ϊ������ռλ��
			params.add("%" + keyword + "%");
			params.add("%" + keyword + "%");
		}
		selectSQL = "select * from (" + selectSQL + ") where PRODUCT_NAME like ?";
		params.add("%" + proname + "%");
		// ������Ϸ����ѯ
		if (game_name != null && !game_name.equals("")) {
			selectSQL += " and GAME_ACCOUNT_ID in (select id from TB_GAME_ACCOUNT where game_name=?)";
			params.add(game_name);
		}

		String sql1 = "select * from TB_PRODUCT";
		List<String> pp = new ArrayList<String>();
		List<TbProduct> cate = service2.findByCate(sql1, pp);
		System.out.println("size---------" + cate.size());
		for (TbProduct tbProduct : cate) {
			System.out.println("tbProduct------" + tbProduct);
		}
	}

	@Test
	public void obj() {
		// ��̬����
		String keyword="";
		Object[] obj = new Object[0];
		
		if (keyword != null && !keyword.equals("")) {
			// ��Ҫ������Σ���Ϊ������ռλ��
			obj = new Object[2];
			obj[0] = "\"%\"" + keyword + "\"%\"";
			obj[1] = "\"%\"" + keyword + "\"%\"";
		}
		
		System.out.println("length------"+obj.length);
		for (Object object : obj) {
			System.out.println(object.toString());
		}
	}

}
