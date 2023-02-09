package com.example.cubtest.entity.ajax;

import java.io.Serializable;
import java.util.List;

import com.example.cubtest.entity.db.CurrencyVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class QueryDataAjax extends BaseAjax implements Serializable {

	private static final long serialVersionUID = -8577539636077443204L;
	
	private List<CurrencyVO> currencyList;
	
}
