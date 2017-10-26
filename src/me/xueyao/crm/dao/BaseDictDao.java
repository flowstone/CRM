package me.xueyao.crm.dao;

import java.util.List;

import me.xueyao.crm.domain.BaseDict;

public interface BaseDictDao {

	/**
	 * 根据类别编号查询数据字典信息
	 * @param dict_type_code
	 * @return
	 */
	public List<BaseDict> findByTypeCode(String dict_type_code);

}
