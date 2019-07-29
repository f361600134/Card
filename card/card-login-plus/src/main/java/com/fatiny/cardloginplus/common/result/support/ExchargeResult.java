package com.fatiny.cardloginplus.common.result.support;

import com.fatiny.cardloginplus.common.result.ErrorCodeEnum;
import com.fatiny.cardloginplus.common.result.ErrorResult;
import com.fatiny.cardloginplus.common.result.IResult;

/**
 * 支付的的结果集对象, 用于支付的结果返回集.
 * 
 * @auth Jeremy
 * @date 2019年1月23日下午5:16:58
 */
public class ExchargeResult extends ErrorResult {

	private String result;

	@Override
	public String result() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public ExchargeResult(ErrorCodeEnum codeEnum) {
		super(codeEnum);
	}

	public ExchargeResult(ErrorCodeEnum codeEnum, String result) {
		super(codeEnum);
		this.result = result;
	}

	public static IResult build(ErrorCodeEnum codeEnum) {
		return new ExchargeResult(codeEnum);
	}

	/**
	 * 
	 * @param codeEnum
	 * @param result 支持自定义结果
	 * @return  
	 * @return IResult  
	 * @date 2019年5月12日上午12:40:58
	 */
	public static IResult build(ErrorCodeEnum codeEnum, String result) {
		return new ExchargeResult(codeEnum, result);
	}

}
