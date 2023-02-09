package com.example.cubtest.entity.ajax;

import com.example.cubtest.constant.AjaxResultEnum;

import lombok.Data;

@Data
public class BaseAjax {

	private String resultCode;
	private String resultDesc;
	
	public void genResult(AjaxResultEnum e) {
		this.resultCode = e.getResultCode();
		this.resultDesc = e.getResultMsg();
	}
	
}
