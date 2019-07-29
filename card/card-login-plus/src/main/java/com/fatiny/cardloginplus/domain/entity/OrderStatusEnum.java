package com.fatiny.cardloginplus.domain.entity;

public enum OrderStatusEnum {

	Order_Delete_By_Manual(-2, "非法充值,作废订单"), //
	Order_Params_Error(-1, "订单请求参数非法,作废订单"), //
	Order_Created_Paying(1, "订单创建完成,等待支付"), //
	Order_Created_Failure(2, "订单创建失败, 异常中断"), //
	Order_Paid_Exchanging(3, "订单支付成功,等待发货"), //
	Order_Paid_Failure(4, "订单支付成功, 校验失败无法发货"), //
	Order_Exchanged_Break(5, "订单兑换游戏币失败,异常中断"), //
	Order_Exchanged_Failed(6, "订单兑换游戏币失败,游戏服兑换失败"), //
	Order_Exchanged_Success(7, "订单兑换游戏币成功,发货完成"), //
	;

	private int status;
	private String desc;

	private OrderStatusEnum(int status, String desc) {
		this.status = status;
		this.desc = desc;
	}

	public short getStatus() {
		return (short)status;
	}

	public String getDesc() {
		return desc;
	}

	/**
	 * 获取到订单枚举
	 * 
	 * @param status
	 * @return
	 * @return PayOrderStatusEnum
	 */
	public static OrderStatusEnum getStateEnum(int status) {
		for (OrderStatusEnum statusEnum : values()) {
			if (statusEnum.status == status) {
				return statusEnum;
			}
		}
		return null;
	}

	/**
	 * 通过状态获取描述
	 * 
	 * @param status
	 * @return
	 * @return String
	 */
	public static String getDesc(int status) {
		OrderStatusEnum statusEnum = getStateEnum(status);
		return statusEnum.getDesc();
	}
}
