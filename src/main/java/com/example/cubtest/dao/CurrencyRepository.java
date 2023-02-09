package com.example.cubtest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.cubtest.entity.db.CurrencyVO;


public interface CurrencyRepository extends JpaRepository<CurrencyVO, Integer> {
	
	CurrencyVO findByCode(String code);
	
	@Transactional
	@Modifying
	@Query(value = "update currency c set c.name = :name where c.code = :code")
	void updateByCode(@Param("code") String code, @Param("name") String name);
	
	@Transactional
	@Modifying
	@Query(value = "delete from currency c where c.code = :code")
	void deleteByCode(@Param("code") String code);
}
