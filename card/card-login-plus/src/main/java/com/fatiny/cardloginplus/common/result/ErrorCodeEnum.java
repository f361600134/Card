package com.fatiny.cardloginplus.common.result;

/**
 * 验证错误码
 * @auth Jeremy
 * @date 2019年4月28日下午2:57:59
 */
public enum ErrorCodeEnum {

	SUCCESS(1, "success"),
	ERROR_USERNAME_LEN(2, "用户名必须是5位以上的字母或数字"),
	ERROR_MAINTENANCE(3, "服务器维护中,请30分钟后重试"),
	ERROR_ACCOUNT(4, "创建账号出错,请检查用户名"),
	IllEGAL_PARAMS(5, "参数错误"),
	ERROR_TIMEOUT(6, "超时错误"),
	ERROR_SIGN_NULL(7, "请求签名遇到异常,签名为空"),
	ERROR_SIGN_ERROR(8, "签名验证失败,签名不一致"),
	ERROR_FORBIDDEN_IP(9, "很抱歉，您的IP已被禁止登陆"),
	ERROR_RUNNING(10, "服务器异常"),
	ERROR_BAN(11, "很抱歉，您的账户已被封印"), //
	ERROR_ORDER_CREATE(12, "订单创建失败"), //
	;

	private int status;
	private String desc;

	private ErrorCodeEnum(int status, String desc) {
		this.status = status;
		this.desc = desc;
	}

	public int getStatus() {
		return status;
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
	public static ErrorCodeEnum getStateEnum(int status) {
		for (ErrorCodeEnum statusEnum : values()) {
			if (statusEnum.status == status) {
				return statusEnum;
			}
		}
		return null;
	}

}
