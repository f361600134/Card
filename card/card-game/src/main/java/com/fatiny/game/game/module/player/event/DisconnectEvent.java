package com.fatiny.game.game.module.player.event;

import com.fatiny.game.game.module.player.domain.PlayerContext;

/**
 * 连接断开事件
 */
public class DisconnectEvent {
	
	private PlayerContext playerCtx;
	
	public DisconnectEvent(PlayerContext playerCtx) {
		this.playerCtx = playerCtx;
	}
	
	public PlayerContext getPlayerCtx() {
		return playerCtx;
	}
	
}
