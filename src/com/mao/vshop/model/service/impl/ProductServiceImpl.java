package com.mao.vshop.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mao.vshop.model.dao.IProductDao;
import com.mao.vshop.model.pojo.TbProduct;
import com.mao.vshop.model.service.IProductService;
@Service("proService")
public class ProductServiceImpl implements IProductService {
	// ʵ�ֽӿ�
	@Autowired
	private IProductDao proDao;

	/**
	 * �����ѯ��Ʒ
	 */
	@Override
	public List<TbProduct> findByCate(String sql, List<String> params) {
		List<TbProduct> list = proDao.findByCate(sql, params);
		return list;
	}

	/**
	 * ��ҳ��ѯ
	 */
	@Override
	public List<TbProduct> findByPage(String sql, List<String> params, int curPage, int pageSize) {
		List<TbProduct> list = proDao.findByPage(sql, params, curPage, pageSize);
		return list;
	}
}
