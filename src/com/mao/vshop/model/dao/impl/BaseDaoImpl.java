package com.mao.vshop.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.mao.vshop.model.dao.IBaseDao;

/**
 * 实现具体CRUD方法
 * 
 * @author Administrator
 *
 * @param <T>
 */
@Repository("baseDao")
public class BaseDaoImpl<T> implements IBaseDao<T> {
	// 注入spring配置文件中的封装好的HibernateTemplate模板
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void save(T entity) {
		hibernateTemplate.save(entity);
	}

	@Override
	public void update(T entity) {
		hibernateTemplate.update(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		hibernateTemplate.saveOrUpdate(entity);
	}

	@Override
	public void delete(T entity) {
		hibernateTemplate.delete(entity);
	}

	@Override
	public T findByID(Long id, Class<T> clazz) {
		T entity = hibernateTemplate.get(clazz, id);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByHql(String hql, Object[] obj) {
		// 判断obj长度
		List<T> list;
		if (obj.length > 0) {
			list = (List<T>) hibernateTemplate.find(hql, obj);
		} else {
			list = (List<T>) hibernateTemplate.find(hql);
		}
		return list;
	}
}
