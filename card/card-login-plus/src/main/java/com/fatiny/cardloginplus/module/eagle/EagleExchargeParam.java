package com.fatiny.cardloginplus.module.eagle;

import com.fatiny.cardloginplus.module.core.base.IExchargeParam;

/**
 * @auth Jeremy
 * @date 2019年4月29日下午6:32:04
 */
public class EagleExchargeParam implements IExchargeParam {
	
	/**
	 *  
	   	人民币CNY	分	1元 = 100分
		美元	USD	美分	1美元 = 100美分
		港币	HKD	仙	1元 = 100仙
		澳门币MOP	仙	1圆 = 100仙
		新台币TWD	分	1圆 = 100分
		日元	JPY	钱	1日元 = 100钱
		韩元	KRW	钱	1韩元 = 100钱
		欧元	EUR	欧分	1欧元 = 100欧分
	 */
	private String gameOrderId; //游戏订单号
	private String extension;	//游戏下单扩展参数
	private String money;		//充值金额,1元 = 100分
	private String currency;	//货币类型,CNY
	private String realPay;		//是否真实付费，1:是，其余否
	private String payTime;		//支付时间(yyyy-MM-dd HH:mm:ss)
	private String orderId;		//订单号
	private String gameId;		//游戏ID
	private String userId;		//用户ID
	private String serverId;	//区服ID
	private String roleId;		//角色ID
	private String productId;	//商品ID
	private String ts;			//当前时间戳(13位，毫秒级)
	private String sign;		//签名

	public long getGameOrderId() {
		return Long.parseLong(gameOrderId);
	}

	public void setGameOrderId(String gameOrderId) {
		this.gameOrderId = gameOrderId;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRealPay() {
		return realPay;
	}

	public void setRealPay(String realPay) {
		this.realPay = realPay;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	@Override
	public String getChOrderId() {
		return orderId;
	}
	
}
