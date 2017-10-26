package me.xueyao.crm.dao;

import me.xueyao.crm.domain.Customer;

public interface CustomerDao {

	/**
	 * 保存客户
	 * @param customer
	 */
	public void save(Customer customer);

}
