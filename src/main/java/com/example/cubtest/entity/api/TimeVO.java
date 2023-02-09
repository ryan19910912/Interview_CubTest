package com.example.cubtest.entity.api;

import java.io.Serializable;

import lombok.Data;

@Data
public class TimeVO implements Serializable {

	private static final long serialVersionUID = 7142461839978934203L;

	private String updated;
	private String updatedISO;
	private String updateduk;
	
	
}
