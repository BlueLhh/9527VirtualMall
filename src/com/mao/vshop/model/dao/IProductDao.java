package com.mao.vshop.model.dao;

import java.util.List;

import com.mao.vshop.model.pojo.TbProduct;

/**
 * 商品特有的Dao方法
 * @author Administrator
 * @param <T>
 *
 */
public interface IProductDao {

	/**
	 * 分类查询商品
	 * @param name
	 * @param password
	 * @return
	 */
	public List<TbProduct> findByCate(String sql, List<String> params);

	/**
	 * 分页查询商品
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<TbProduct> findByPage(String sql, List<String> params, int curPage, int pageSize);

	/**
	 *  获取成交笔数，需要连表查询，连接item和order表
	 * @param id
	 * @return
	 */
	public int findSellCountById(Long id);
}
