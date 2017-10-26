package me.xueyao.crm.dao;

import java.util.List;

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

}
