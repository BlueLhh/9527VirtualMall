package com.mao.vshop.model.dao;

import java.util.List;

/**
 * ʵ��ͨ�ý���CRUD��Dao��
 * @author Administrator
 *
 */
public interface IBaseDao<T> {

	/**
	 * ��������
	 * @param <T>
	 * @param entity
	 */
	public void save(T entity);

	/**
	 * ������������
	 * @param entity
	 */
	public void saveOrUpdate(T entity);

	/**
	 * ��������
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * ɾ������
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 * ����id��ѯ����
	 * @param id
	 * @return
	 */
	public T findByID(Long id,Class<T> clazz);

	/**
	 * ���������hql����params���������ѯ����
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> findByHql(String hql, Object[] obj);
}
