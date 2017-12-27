package com.mao.vshop.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mao.vshop.model.dao.IBaseDao;
import com.mao.vshop.model.service.IBaseService;

@Service("baseService")
public class BaseServiceImpl<T> implements IBaseService<T> {

	@Autowired
	private IBaseDao<T> dao;

	@Override
	public void save(T entity) {
		dao.save(entity);
	}

	@Override
	public void update(T entity) {
		dao.update(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		dao.saveOrUpdate(entity);
	}

	@Override
	public void delete(T entity) {
		dao.delete(entity);
	}

	@Override
	public T findByID(Long id, Class<T> clazz) {
		T entity = dao.findByID(id, clazz);
		return entity;
	}

	@Override
	public List<T> findByHql(String hql, Object[] obj) {
		List<T> list = dao.findByHql(hql, obj);
		return list;
	}
}
