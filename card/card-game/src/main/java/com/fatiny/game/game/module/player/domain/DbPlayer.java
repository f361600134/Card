package com.fatiny.game.game.module.player.domain;

import com.fatiny.core.data.PO;
import com.fatiny.game.po.TbDbPlayerPo;

/**
 * this class file is auto output by net.good321.frame.db.tool.POPrinter
 * @author chao
 * @see net.good321.frame.db.tool.PoPrinter
 */
@PO("db_player")
public class DbPlayer extends TbDbPlayerPo {

	public DbPlayer() {

	}
	
	
	public boolean enoughGold(int needGold) {
		return getGold()>=needGold;
	}
	
	/**
	 * 修改钱币,负数表示扣除
	 */
	public void addGold(int addVal, String action)
	{
		int value = getGold() + addVal;
		if(value < 0 && addVal < 0) {
			value = 0;
		} else if (value < 0 && addVal > 0) {
			value = Integer.MAX_VALUE;
		}
		setGold(value);
//		if (value < 0) 
//			PlayerLog.obtainReource(this, action, SourceNature.consumeGold, Config.IdGoodGold, gold, g);
//		else 
//			PlayerLog.obtainReource(this, action, SourceNature.obtainGold, Config.IdGoodGold, gold, g);
	}
	
	public boolean enoughDiamond(int needDiamond) {
		return getDiamond()>=needDiamond;
	}
	
	/**
	 * 修改元宝,负数表示扣除
	 */
	public void addDiamond(int addVal, String action)
	{
		int value = getDiamond() + addVal;
		if(value < 0 && addVal < 0) {
			value = 0;
		} else if (value < 0 && addVal > 0) {
			value = Integer.MAX_VALUE;
		}
		setDiamond(value);
//		if (gold < 0) 
//			PlayerLog.obtainReource(this, action, SourceNature.consumeGold, Config.IdGoodGold, gold, g);
//		else 
//			PlayerLog.obtainReource(this, action, SourceNature.obtainGold, Config.IdGoodGold, gold, g);
	}
	
	public static void main(String[] args) {
		//int num = Integer.MAX_VALUE;
		int num = 1;
		int addnum =  -10;
		int g = num + addnum;
		if(g < 0 && addnum < 0) {
			g = 0;
		} else if (g < 0 && addnum > 0) {
			g = Integer.MAX_VALUE;
		}
		System.out.println(g);
	}
	
	@Override
	public long key() {
		//第一主键的数据值, 例如playerId
		return getId();
	}

	@Override
	public String cacheId() {
		//缓存的二级ID, 如果不是一对多关系的return null即可
		return String.valueOf(getId());
	}

	@Override
	public String keyColumn() {
		//第一主键的数据库列名
		return ids()[0];
	}

}
