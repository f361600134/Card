package com.fatiny.game.base.actorserver;

import com.fatiny.core.server.main.manager.SessionGroup;
import com.fatiny.game.actor.remote.IGameActor;

import lombok.extern.slf4j.Slf4j;


/**
 * 游戏Actor
 */
@Slf4j
public class GameActor implements IGameActor {

	@Override
	public int getOnlineCount() {
		return SessionGroup.onlineMembers();
	}

//	@Override
//	public int billConfirm(String orderId, String accountId, double money, int chargeType, int payType, String extraData, String thirdParam) {
//		IBillConfirm billConfirm = BillFactory.CreateBillConfirm(payType);
//		return billConfirm.shipments(orderId, accountId, money, chargeType, payType, extraData, thirdParam);
//	}
//	
//	@Override
//	public int scrollNotice(String contents, int interval, int frequency) {
//		ChatHelper.pushScrollNotice(contents, 100, 50, 12, interval, frequency);
//		return 1;
//	}
//	
//	@Override
//	public int sendNotice(int noticeId, String title, String contents, String picture, String page, String indexes, int type, long endTime) {
//		Notice notice = null;
//		if (noticeId <= 0) {
//			notice = Notice.create(title, contents, indexes, picture, page, type, endTime);
//			NoticeManager.instance().putNotice(notice);
//			notice.save();
//		} else {
//			notice = Notice.create(noticeId, title, contents, indexes, picture, page, type, endTime);
//			NoticeManager.instance().putNotice(notice);
//			notice.udpate();
//		}
//		
//		S2CNotice.Builder s2cNotice = S2CNotice.newBuilder();
//    	NoticeInfo.Builder noticeInfo = NoticeInfo.newBuilder();
//		
//		noticeInfo.setNoticeId(notice.getNoticeId()); 
//		noticeInfo.setTitle(title);
//		noticeInfo.setContents(contents);
//		noticeInfo.setPage(page);
//		
//		if (!StringUtils.isBlank(indexes)) {
//			String[] indexArray = indexes.split(",");
//			List<Integer> indexList = new ArrayList<>();
//			for (String element : indexArray) {
//				indexList.add(Integer.parseInt(element));
//			}
//			noticeInfo.addAllIndexes(indexList);
//		}
//		noticeInfo.setEndTime((int) (endTime / 1000));
//		noticeInfo.setPicture(picture);
//		noticeInfo.setType(type);
//		s2cNotice.addNoticeInfo(noticeInfo);
//		
//		List<PlayerContext> onlineList = PlayerManager.instance().onlinePlayers();
//		SimpleProtocol proto = SimpleProtocol.create(ProtocolCode.NOTICE_S2C_NOTICE_LIST, s2cNotice.build());
//		for (PlayerContext element : onlineList) {
//			element.push(proto);
//		}
//		return 1;
//	}
//
//	
//	@Override
//	public List<String> allNotices() {
//		Collection<Notice> notices = NoticeManager.instance().allNotices();
//		List<String> noticeStrList = new ArrayList<>();
//		for (Notice notice : notices) {
//			noticeStrList.add(notice.toJsonString());
//		}
//		return noticeStrList;
//	}
//
//	
//	@Override
//	public int delNotice(int noticeId) {
//		Notice notice = NoticeManager.instance().getNotice(noticeId);
//		if (notice != null) {
//			NoticeManager.instance().delNotice(noticeId);
//			notice.delete();
//		}
//		return 0;
//	}
//
//	@Override
//	public int sendSystemMail(long playerId, String title, String content, int rewardId, long endTime,String items) {
//		if (playerId > 0L) {
//			log.debug("发送个人邮件");
//			List<Material> materials = new ArrayList<Material>();
//			if(items != null && !items.isEmpty())
//			{
//				//物品ID发送
////				String item [] = items.split("\\|");
////				for (int i = 0; i < item.length; i++) {
////					String data [] = item[i].split(",");
////					int type = Integer.parseInt(data[0]);
////					int itemId = Integer.parseInt(data[1]);
////					int itemCount = Integer.parseInt(data[2]);
////					materials.add(Material.create(itemId, type, itemCount));
////				}
//				materials = Material.analyze(items);
//			}else
//			{
//				//掉落ID发送
//				materials = DropHelper.processDrop(playerId, rewardId);
//			}
//
//			PlayerMail.sendMail(playerId, MailType.GM, title, content, materials);
//			return 0;
//		}
//		Gmmail mail = new Gmmail();
//		mail.setId(UIDAllocator.get().nextId());
//		mail.setType(MailType.GM); // 运营
//		mail.setTitle(title);
//		mail.setContent(content);
//		mail.setRewardId(rewardId);
//		mail.setCreateDate(new Date());
//		mail.setEffectDate(new Date(endTime));
//		
//		MailManager manager = MailManager.instance();
//		manager.insert(mail);
//		
//		List<PlayerContext> onlineList = PlayerManager.instance().onlinePlayers();
//		Date now = new Date();
//		log.debug("发送全服邮件");
//		for (PlayerContext onlinePlayerCtx : onlineList) {
//			if (!onlinePlayerCtx.isOnline()) { 
//				continue;
//			}
//			List<Material> materials = DropHelper.processDrop(onlinePlayerCtx.getId(), mail.getRewardId());
//			PlayerMail.sendMail(onlinePlayerCtx.getId(), mail.getType(), mail.getTitle(), mail.getContent(), materials);
//			
//			onlinePlayerCtx.getPlayerRecord().setReceiveMailTime(now);
//			onlinePlayerCtx.getPlayerRecord().update();
//		}
//		return 0;
//	}
//
//	@Override
//	public List<String> allSystemMails() {
//		MailManager mailManager = MailManager.instance();
//		Map<Long, Gmmail> mails = mailManager.getAllGmmail();
//		List<String> mailStrList = new ArrayList<>();
//		for (Gmmail element : mails.values()) {
//			mailStrList.add(element.toJsonString());
//		}
//		return mailStrList;
//	}
//
//	@Override
//	public int banPlayerOperation(BannedPlayer bannedPlayer) {
//		BannedManager bannedManager = BannedManager.instance();
//		bannedManager.addPlayerToBannedList(bannedPlayer);
//		return 0;
//	}
//
//	@Override
//	public int unbanPlayer(long playerId, int type) {
//		BannedManager bannedManager = BannedManager.instance();
//		bannedManager.removePlayerFromBannedList(playerId, type);
//		return 0;
//	}
//
//	@Override
//	public int updateBaseResources(String host, int port, String url, String local) {
//		try {
//			final String resourcesPath = "resources/" + local;
//			HttpDownloadClient client = new HttpDownloadClient();
//			DownloadDoneListener doneListener = new DownloadDoneListener() {
//				@Override
//				public void downloadComplete() {
//					try {
//						SevenZipUtil.extractArchiveFile(resourcesPath);
//						BaseDataManager manager = BaseDataManager.instance();
//						manager.loadFiles(); // 重载基础数据
//					} catch (Exception e) {
//						log.error("解压基础数据文件出错", e);
//					}
//				}
//			};
//			client.connect(host, port, url, resourcesPath, doneListener);
//			return 0;
//		} catch (Exception e) {
//			log.error("", e);
//			return -1;
//		}
//	}
//	
	@Override
	public int kickOnlinePlayers() {
		log.info("GM踢所有玩家下线");
//		PlayerManager.instance().kickAllPlayers();
//		JobManager.instance().clearAllEvents();
		return 0;
	}
//	
//	@Override
//	public int delSystemMail(long mailId){
//		MailManager manager = MailManager.instance();
//		Gmmail mail = manager.getAllGmmail().get(mailId);
//		manager.delete(mail);
//		return 0;
//	}
//	
//	@Override
//	public List<String> allPlayerMails(long playerId) {
//
//		GameContext gameCtx = GameContext.instance();
//
//		List<Mail> data = gameCtx.gameData().getData(Mail.class, playerId);
//
//		List<String> mailStrList = new ArrayList<>();
//		for (Mail element : data) {
//			mailStrList.add(element.toJsonString());
//		}
//		return mailStrList;
//	}
//	
//	
//	public static void main(String[] args) throws Exception {
//		log.init();
//		new GameActor().updateBaseResources("shootcdn.good321.net", 80, "https://shootcdn.good321.net/res/server/serverResPC.7z", "serverResPC.7z");
//	}

	

}
