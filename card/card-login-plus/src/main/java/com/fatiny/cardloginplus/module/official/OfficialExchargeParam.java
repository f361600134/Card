package com.fatiny.cardloginplus.module.official;

import com.fatiny.cardloginplus.module.core.base.IExchargeParam;

public class OfficialExchargeParam implements IExchargeParam{
	
	private long gameOrderId;
	
	public void setGameOrderId(long gameOrderId) {
		this.gameOrderId = gameOrderId;
	}

	@Override
	public long getGameOrderId() {
		return gameOrderId;
	}

	@Override
	public String getChOrderId() {
		return String.valueOf(System.currentTimeMillis());
	}

}
