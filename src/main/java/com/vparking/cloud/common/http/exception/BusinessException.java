package com.vparking.cloud.common.http.exception;

import com.vparking.cloud.common.http.response.ReturnStatus;

import java.text.MessageFormat;

@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {
	private int code;
	private String description;
	private ReturnStatus returnStatus;

	public BusinessException(ReturnStatus returnStatus) {
		this.returnStatus = returnStatus;
		this.code = returnStatus.getValue();
		this.description = returnStatus.getDescription();
	}
	
	public BusinessException(ReturnStatus returnStatus, Object... str) {
		this.code = returnStatus.getValue();
		String returnStr = returnStatus.getDescription();
		this.description = MessageFormat.format(returnStr,str);
		returnStatus.setDescription(this.description);
		this.returnStatus = returnStatus;
	}


	public BusinessException(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ReturnStatus getReturnStatus() {
		return returnStatus;
	}

}
