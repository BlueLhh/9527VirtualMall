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
	 * 由于需要执行sql语句所有这里注入的是session
	 */
	@Autowired
	private Session session;

	/**
	 * 根据分类查询商品，使用查询缓存，使效率更高
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbProduct> findByCate(String sql, List<String> params) {
		SQLQuery query = session.createSQLQuery(sql);
		// 清除缓存
		session.clear();
		// 开启查询缓存
		// query.setCacheable(true);
		if (params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setString(i, params.get(i));
			}
		}
		// 与HQL语句不同，SQL还需要映射实体类
		query.addEntity(TbProduct.class);
		List<TbProduct> list = query.list();
		return list;
	}

	/**
	 * 分页查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbProduct> findByPage(String sql, List<String> params, int curPage, int pageSize) {
		SQLQuery query = session.createSQLQuery(sql);
		// 清除缓存
		session.clear();
		// 开启查询缓存
		// query.setCacheable(true);
		if (params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setString(i, params.get(i));
			}
		}
		// 与HQL语句不同，SQL还需要映射实体类
		query.addEntity(TbProduct.class);
		// 从第几条记录开始查
		query.setFirstResult((curPage - 1) * pageSize);
		// 每页查询多少条记录
		query.setMaxResults(pageSize);
		List<TbProduct> list = query.list();
		return list;
	}

	/** 
	 * 获取成交笔数，需要连表查询，连接item和order表
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
