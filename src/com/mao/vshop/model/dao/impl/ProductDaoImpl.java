package com.mao.vshop.model.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mao.vshop.model.dao.IProductDao;
import com.mao.vshop.model.pojo.TbProduct;

@Repository("proDao")
public class ProductDaoImpl implements IProductDao {

	/**
	 * ������Ҫִ��sql�����������ע�����session
	 */
	@Autowired
	private Session session;

	/**
	 * ���ݷ����ѯ��Ʒ��ʹ�ò�ѯ���棬ʹЧ�ʸ���
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbProduct> findByCate(String sql, List<String> params) {
		SQLQuery query = session.createSQLQuery(sql);
		// �������
		session.clear();
		// ������ѯ����
		// query.setCacheable(true);
		if (params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setString(i, params.get(i));
			}
		}
		// ��HQL��䲻ͬ��SQL����Ҫӳ��ʵ����
		query.addEntity(TbProduct.class);
		List<TbProduct> list = query.list();
		return list;
	}

	/**
	 * ��ҳ��ѯ
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbProduct> findByPage(String sql, List<String> params, int curPage, int pageSize) {
		SQLQuery query = session.createSQLQuery(sql);
		// �������
		session.clear();
		// ������ѯ����
		// query.setCacheable(true);
		if (params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setString(i, params.get(i));
			}
		}
		// ��HQL��䲻ͬ��SQL����Ҫӳ��ʵ����
		query.addEntity(TbProduct.class);
		// �ӵڼ�����¼��ʼ��
		query.setFirstResult((curPage - 1) * pageSize);
		// ÿҳ��ѯ��������¼
		query.setMaxResults(pageSize);
		List<TbProduct> list = query.list();
		return list;
	}

	/** 
	 * ��ȡ�ɽ���������Ҫ�����ѯ������item��order��
	 */
	@Override
	public int findSellCountById(Long id) {
		String selectSQL = "select count(*) from TB_PRODUCT p,TB_ORDER_ITEM i,TB_ORDER o where "
				+ "o.id=i.order_id and i.product_id=p.id and p.id=? and o.status=2";
		SQLQuery query = session.createSQLQuery(selectSQL);
		query.setLong(0, id);
		Object rs = query.uniqueResult();
		int sellCount = Integer.parseInt(rs.toString());
		return sellCount;
	}
}
