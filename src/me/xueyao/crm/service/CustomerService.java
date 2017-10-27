package me.xueyao.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import me.xueyao.crm.domain.Customer;

public interface CustomerService {

	/**
	 * 保存客户
	 * @param customer
	 */
	public void saveCustomer(Customer customer);

	/**
	 * 查询所有客户
	 * @return
	 */
	public List<Customer> findAllCustomer();

	/**
	 * 查询客户表总记录数
	 * @param dc 
	 * @return
	 */
	public int findCount(DetachedCriteria dc);

	/**
	 * 分页查询客户表
	 * @param dc 
	 * @param i  开始查询的位置索引
	 * @param rows 每页查询的最大记录数
	 * @return
	 */
	public List<Customer> findByPage(DetachedCriteria dc, int i, int rows);

	/**
	 *根据id 删除客户
	 * @param cust_id
	 */
	public void delete(Integer cust_id);


}
