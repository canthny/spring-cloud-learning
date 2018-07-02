package com.hundsun.network.hspay.common.xmldomain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class WithdrawMessageDtailDO implements java.io.Serializable{
	
	
	
	@XStreamAsAttribute
	private String id;
	/**
	 * 版本号
	 */
	@XStreamAlias("version")
	private String version;
	/**
	 * 机构标识
	 */
	@XStreamAlias("instId")
	private String instId;
	/**
	 * 数字证书标识
	 */
	@XStreamAlias("certId")
	private String certId;
	/**
	 * 商户交易流水号
	 */
	@XStreamAlias("channelSeq")
	private String channelSeq;
	/**
	 * 渠道日期
	 */
	@XStreamAlias("channelDate")
	private String channelDate;
	/**
	 * 渠道时间
	 */
	@XStreamAlias("channelTime")
	private String channelTime;
	
	/**
	 * 账户类型
	 */
	@XStreamAlias("accountType")
	private String accountType;
	
	/**
	 * 他行收款账户
	 */
	@XStreamAlias("recAccount")
	private String recAccount;
	/**
	 * 提取现金额
	 */
	@XStreamAlias("payAmount")
	private String payAmount;
	/**
	 * 付款账户
	 */
	@XStreamAlias("payAccount")
	private String payAccount;
	/**
	 * 到账类型
	 */
	@XStreamAlias("settleType")
	private String settleType;
	
	/**
	 * 币种
	 */
	@XStreamAlias("currencyType")
	private String currencyType;
	
	/**
	 * 附言
	 */
	@XStreamAlias("explain")
	private String explain;
	/**
	 * 扩展字段
	 */
	@XStreamAlias("extension")
	private String extension;
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
	public String getChannelSeq() {
		return channelSeq;
	}
	public void setChannelSeq(String channelSeq) {
		this.channelSeq = channelSeq;
	}
	public String getChannelDate() {
		return channelDate;
	}
	public void setChannelDate(String channelDate) {
		this.channelDate = channelDate;
	}
	public String getChannelTime() {
		return channelTime;
	}
	public void setChannelTime(String channelTime) {
		this.channelTime = channelTime;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getRecAccount() {
		return recAccount;
	}
	public void setRecAccount(String recAccount) {
		this.recAccount = recAccount;
	}
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public String getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getSettleType() {
		return settleType;
	}
	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}
	
	

}
