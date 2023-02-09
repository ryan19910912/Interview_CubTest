package com.example.cubtest.entity.api;

import java.io.Serializable;

import lombok.Data;

@Data
public class CurrencyVO implements Serializable {

	private static final long serialVersionUID = 172773109265269560L;
	
	private String code;
	private String symbol;
	private String rate;
	private String description;
	private Double rate_float;
	
}
