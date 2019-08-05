package com.fatiny.game.actor.remote;

/**
 * 游戏服Actor
 * 
 */
public interface IGameActor {
	
	/**
	 * 获取在线玩家数量
	 * 
	 * @return {@link Integer}
	 */
	int getOnlineCount();
	
	
//	/**
//	 * 确认充值订单
//	 * 
//	 * @param orderId
//	 * @param accountId
//	 * @param money
//	 * @param chargeType
//	 * @param payType
//	 * @param extraData
//	 * @return {@link Integer}
//	 */
//	int billConfirm(String orderId, String accountId, double money, int chargeType, int payType, String extraData, String thirdParam);
//	
//	
//	/**
//	 * 后台跑马灯
//	 * 
//	 * @param contents  内容
//	 * @param interval  循环间隔
//	 * @param frequency 循环次数
//	 * @return {@link Integer}
//	 */
//	int scrollNotice(String contents, int interval, int frequency);
//	
//	
//	/**
//	 * 发公告
//	 * 
//	 * @param title
//	 * @param contents
//	 * @param picture
//	 * @param page
//	 * @param indexes
//	 * @param type
//	 * @param endTime
//	 * @return
//	 */
//	public int sendNotice(int noticeId, String title, String contents, String picture, String page, String indexes, int type, long endTime);
//
//	
//	/**
//	 * 所有公告
//	 * 
//	 * @return {@link List}
//	 */
//	public List<String> allNotices();
//	
//	
//	/**
//	 * 删除公告
//	 * 
//	 * @return
//	 */
//	public int delNotice(int noticeId);
//	
//	
//	/**
//	 * 删除运营邮件
//	 * 
//	 * @return
//	 */
//	public int delSystemMail(long mailId);
//	
//	
//	/**
//	 * 发运营邮件
//	 * 
//	 * @return
//	 */
//	public int sendSystemMail(long playerId, String title, String content, int rewardId, long endTime,String items);
//	
//	
//	/**
//	 * 所有后台发的邮件
//	 * 
//	 * @return {@link List}
//	 */
//	public List<String> allSystemMails();
//	
//	
//	/**
//	 * 玩家邮件历史
//	 * 
//	 * @return {@link List}
//	 */
//	public List<String> allPlayerMails(long playerId);
//	
//	
//	/**
//	 * 禁止玩家权限 
//	 * 
//	 * @param bannedPlayer
//	 * @return {@link Integer}
//	 */
//	public int banPlayerOperation(BannedPlayer bannedPlayer);
//	
//	
//	/**
//	 * 解禁
//	 * 
//	 * @param playerId
//	 * @param type
//	 * @return {@link Integer}
//	 */
//	public int unbanPlayer(long playerId, int type);
//	
//	
//	/**
//	 * 更新基础数据资源
//	 * 
//	 * @param host
//	 * @param port
//	 * @param url
//	 * @param local
//	 */
//	public int updateBaseResources(String host, int port, String url, String local);
//	
	
	/**
	 * 全服T人
	 * 
	 * @return {@link Integer}
	 */
	public int kickOnlinePlayers();
	
}
