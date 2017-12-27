package com.mao.vshop.model.service;
/**
 * 商品特有的服务层
 * @author Administrator
 *
 */

import java.util.List;

import com.mao.vshop.model.pojo.TbProduct;

public interface IProductService {
	/**
	 * 分类查询商品
	 * @param hql
	 * @param params
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
}
