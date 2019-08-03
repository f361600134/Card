package com.fatiny.cardloginplus.common.result;

import com.alibaba.fastjson.JSON;

import lombok.Data;

@Data
public class BusinessResult implements IResult{
	
	private Object result;
	
	@Override
	public boolean success() {
		return true;
	}

	@Override
	public String result() {
		return JSON.toJSONString(result);
	}
	
	public BusinessResult(Object object){
		this.result = object;
	}
	
	public static IResult build(Object object) {
		return new BusinessResult(object);
	}
	
}
