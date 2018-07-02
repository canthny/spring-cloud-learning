package com.hundsun.network.dto;

import java.io.Serializable;


public class ResponesWithdrawDeposit implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2925692246712461684L;
	private String id;
	/**
	 * 版本
	 */
	
	private String version;
	/**
	 * 机构标识
	 */
	
	private String instId;
	/**
	 * 数字证书标识
	 */
	
	private String certId;
	/**
	 * 错误代码
	 */
	
	private String errorCode;
	/**
	 * 错误信息
	 */

	private String errorMessage;
	
	/**
	 * 商户交易流水号
	 */

	private String channelSeq;
	/**
	 * 处理结果
	 */

	private String DealRes;
	/**
	 * 交易时间
	 */
	private String transTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getInstId() {
		return instId;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	public String getCertId() {
		return certId;
	}
	public void setCertId(String certId) {
		this.certId = certId;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getChannelSeq() {
		return channelSeq;
	}
	public void setChannelSeq(String channelSeq) {
		this.channelSeq = channelSeq;
	}
	public String getDealRes() {
		return DealRes;
	}
	public void setDealRes(String dealRes) {
		DealRes = dealRes;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}

}
