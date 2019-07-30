package com.fatiny.cardloginplus.module.core.support;

import com.fatiny.cardloginplus.common.result.AbstractResult;

class ErrorResult extends AbstractResult{

	public ErrorResult(ErrorCodeEnum eEnum) {
		super(eEnum.getStatus(), eEnum.getDesc());
	}
	
	@Override
	public boolean success() {
		return getCode() == ErrorCodeEnum.SUCCESS.getStatus();
	}

}
