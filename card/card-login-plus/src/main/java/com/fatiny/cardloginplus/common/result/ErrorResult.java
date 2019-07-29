package com.fatiny.cardloginplus.common.result;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;

/**
 * 结果集父类
 * @auth Jeremy
 * @date 2019年4月28日下午3:06:32
 */
public class ErrorResult implements IResult {

	// 错误码
	private int code;
	// 错误描述
	private String tips;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public ErrorResult(int code, String tips) {
		super();
		this.code = code;
		this.tips = tips;
	}

	/**
	 * 是否正确
	 * 
	 * @return
	 * @return boolean
	 */
	@Override
	public boolean success() {
		return code == ErrorCodeEnum.SUCCESS.getStatus();
	}

	public ErrorResult(ErrorCodeEnum eEnum) {
		// 错误提示
		Preconditions.checkNotNull(eEnum);
		//Preconditions.checkArgument(!(eEnum == ErrorCodeEnum.SUCCESS), "错误码有误,codeEnum:" + eEnum.getStatus());
		this.code = eEnum.getStatus();
		this.tips = eEnum.getDesc();
	}
	
	/**
	 * 错误码的错误状态发生改变
	 * 
	 * @return void
	 */
	public void change(ErrorCodeEnum eEnum) {
		// 错误提示
		Preconditions.checkNotNull(eEnum);
		//Preconditions.checkArgument(!(eEnum == ErrorCodeEnum.SUCCESS), "错误码有误,codeEnum:" + eEnum.getStatus());
		this.code = eEnum.getStatus();
		this.tips = eEnum.getDesc();
	}

	@Override
	public String toString() {
		return "ErrorResult [code=" + code + ", tips=" + tips + "]";
	}

	@Override
	public String result() {
		String result = JSONObject.toJSONString(this);
		return result;
	}
	
	/**
	 * 构造消息
	 * @return
	 */
	public static IResult build(ErrorCodeEnum codeEnum) {
		return new ErrorResult(codeEnum);
	}
	
}
