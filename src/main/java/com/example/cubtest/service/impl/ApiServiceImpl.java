package com.example.cubtest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cubtest.dao.CurrencyRepository;
import com.example.cubtest.entity.db.CurrencyVO;
import com.example.cubtest.service.ApiService;

@Service
public class ApiServiceImpl implements ApiService {

	@Autowired
	private CurrencyRepository currencyRepository;
	
	@Override
	public CurrencyVO queryByCode(String code) {
		return currencyRepository.findByCode(code);
	}
	
	@Override
	public List<CurrencyVO> queryAll() {
		return currencyRepository.findAll();
	}
	
	@Override
	public List<CurrencyVO> insert(String code, String name) {
		
		CurrencyVO vo = new CurrencyVO();
		vo.setCode(code);
		vo.setName(name);
		
		currencyRepository.save(vo);
		
		return this.queryAll();
	}

	@Override
	public List<CurrencyVO> update(String code, String name) {
		
		currencyRepository.updateByCode(code, name);
		
		return this.queryAll();
	}

	@Override
	public List<CurrencyVO> delete(String code) {
		
		currencyRepository.deleteByCode(code);
		
		return this.queryAll();
	}

}
