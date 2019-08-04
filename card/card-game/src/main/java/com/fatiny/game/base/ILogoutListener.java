package com.fatiny.game.base;

import com.fatiny.game.game.module.player.domain.PlayerContext;

/**
 * 离线监听
 */
public interface ILogoutListener {
	
	void logout(PlayerContext playerContext);
	
}
