package com.fatiny.cardlogin.common.result.support;

import com.fatiny.cardlogin.common.result.ErrorCodeEnum;
import com.fatiny.cardlogin.common.result.ErrorResult;
import com.fatiny.cardlogin.common.result.IResult;

/**
 * 用于用户验证创建订单, 订单校验返回结果集
 * 
 * @auth Jeremy
 * @date 2018年9月21日下午2:01:26
 */
public class OrderResult extends ErrorResult {

	private long orderId;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public OrderResult(ErrorCodeEnum codeEnum) {
		super(codeEnum);
	}

	public OrderResult(ErrorCodeEnum codeEnum, long orderId) {
		super(codeEnum);
		this.orderId = orderId;
	}

	public static IResult success(ErrorCodeEnum processEnum, long orderId) {
		OrderResult result = new OrderResult(processEnum, orderId);
		return result;
	}

	public static IResult build(ErrorCodeEnum processEnum) {
		OrderResult result = new OrderResult(processEnum);
		return result;
	}

}
