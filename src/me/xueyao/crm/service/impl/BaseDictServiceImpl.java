package me.xueyao.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.xueyao.crm.dao.BaseDictDao;
import me.xueyao.crm.domain.BaseDict;
import me.xueyao.crm.service.BaseDictService;

@Service("baseDictService")
@Transactional
public class BaseDictServiceImpl implements BaseDictService {

	@Autowired
	private BaseDictDao baseDictDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return baseDictDao.findByTypeCode(dict_type_code);
	}

}
