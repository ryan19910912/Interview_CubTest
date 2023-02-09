package com.example.cubtest.service;

import java.util.List;

import com.example.cubtest.entity.db.CurrencyVO;

public interface ApiService {

	CurrencyVO queryByCode(String code);
	
	List<CurrencyVO> queryAll();
	
	List<CurrencyVO> insert(String code, String name);
	
	List<CurrencyVO> update(String code, String name);
	
	List<CurrencyVO> delete(String code);
}
