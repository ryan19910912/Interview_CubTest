package com.example.cubtest.entity.ajax;

import java.io.Serializable;

import lombok.Data;

@Data
public class BpiAjax implements Serializable {

	private static final long serialVersionUID = -7002820859936697683L;
	
	private CurrencyAjax USD;
	private CurrencyAjax GBP;
	private CurrencyAjax EUR;
}
