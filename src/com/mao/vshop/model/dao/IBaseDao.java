package com.mao.vshop.model.dao;

import java.util.List;

/**
 * 实体通用进行CRUD的Dao层
 * @author Administrator
 *
 */
public interface IBaseDao<T> {

	/**
	 * 保存数据
	 * @param <T>
	 * @param entity
	 */
	public void save(T entity);

	/**
	 * 保存或更新数据
	 * @param entity
	 */
	public void saveOrUpdate(T entity);

	/**
	 * 更新数据
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * 删除数据
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 */
	public T findByID(Long id,Class<T> clazz);

	/**
	 * 根据输入的hql语句和params条件数组查询数据
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> findByHql(String hql, Object[] obj);
}
