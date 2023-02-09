package com.example.cubtest.entity.ajax;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class DataChangeAjax extends BaseAjax implements Serializable {

	private static final long serialVersionUID = -7830687286742287479L;
	
	private String updateTime;
	private BpiAjax bpi;

}
