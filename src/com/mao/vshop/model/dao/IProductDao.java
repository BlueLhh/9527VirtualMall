package com.mao.vshop.model.dao;

import java.util.List;

import com.mao.vshop.model.pojo.TbProduct;

/**
 * ��Ʒ���е�Dao����
 * @author Administrator
 * @param <T>
 *
 */
public interface IProductDao {

	/**
	 * �����ѯ��Ʒ
	 * @param name
	 * @param password
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

	/**
	 *  ��ȡ�ɽ���������Ҫ�����ѯ������item��order��
	 * @param id
	 * @return
	 */
	public int findSellCountById(Long id);
}
