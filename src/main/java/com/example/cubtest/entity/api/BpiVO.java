package com.example.cubtest.entity.api;

import java.io.Serializable;

import lombok.Data;

@Data
public class BpiVO implements Serializable {

	private static final long serialVersionUID = -7002820859936697683L;
	
	private CurrencyVO USD;
	private CurrencyVO GBP;
	private CurrencyVO EUR;
}
