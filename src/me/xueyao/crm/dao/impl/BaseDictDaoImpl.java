package me.xueyao.crm.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import me.xueyao.crm.dao.BaseDictDao;
import me.xueyao.crm.domain.BaseDict;

@Repository("baseDictDao")
public class BaseDictDaoImpl implements BaseDictDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		List<BaseDict> list = (List<BaseDict>) hibernateTemplate.find("from BaseDict where dict_type_code = ?", dict_type_code);
		return list;
	}

}
