package com.mao.vshop.model.service;
/**
 * ��Ʒ���еķ����
 * @author Administrator
 *
 */

import java.util.List;

import com.mao.vshop.model.pojo.TbProduct;

public interface IProductService {
	/**
	 * �����ѯ��Ʒ
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<TbProduct> findByCate(String sql, List<String> params);
	
	/**
	 * ��ҳ��ѯ��Ʒ
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<TbProduct> findByPage(String sql, List<String> params, int curPage, int pageSize);
}
