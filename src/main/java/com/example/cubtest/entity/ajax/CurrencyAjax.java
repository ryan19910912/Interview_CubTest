package com.example.cubtest.entity.ajax;

import java.io.Serializable;

import lombok.Data;

@Data
public class CurrencyAjax implements Serializable {

	private static final long serialVersionUID = 172773109265269560L;
	
	private String code;
	private String name;
	private String rate;
	
}
