package me.xueyao.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import me.xueyao.crm.domain.Customer;

public interface CustomerDao {

	/**
	 * 保存客户
	 * @param customer
	 */
	public void save(Customer customer);

	/**
	 * 查询所有客户
	 * @return
	 */
	public List<Customer> findAll();

	/**
	 * 查询客户表总记录数
	 * @param dc 
	 * @return
	 */
	public int findCount(DetachedCriteria dc);

	/**
	 * 分页查询客户表
	 * @param dc 
	 * @param i
	 * @param rows
	 * @return
	 */
	public List<Customer> findByPage(DetachedCriteria dc, int i, int rows);

}
