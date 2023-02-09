package com.example.cubtest.constant;

public enum AjaxResultEnum {

	SUCCESS("0000", "成功！"),
	PARAM_FAIL("0001", "參數異常！"),
	SYSTEM_ERROR("9999", "程式異常！");

	private String resultCode;
	private String resultMsg;

	AjaxResultEnum(String resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	public String getResultCode() {
		return resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

}
