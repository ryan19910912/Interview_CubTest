package com.example.cubtest.entity.api;

import java.io.Serializable;

import lombok.Data;

@Data
public class ApiVO implements Serializable {

	private static final long serialVersionUID = -69225560012370932L;

	private TimeVO time;
	private String disclaimer;
	private String chartName;
	private BpiVO bpi;
	
}
