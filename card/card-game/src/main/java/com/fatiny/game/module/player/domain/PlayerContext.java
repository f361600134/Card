package com.fatiny.game.module.player.domain;

import com.fatiny.core.server.main.GameSession;
import com.fatiny.game.actor.local.IPlayerActor;

/**
 * 玩家上下文
 * 
 * @author huachp
 */
public class PlayerContext {
	
	/** 玩家ID */
	private long playerId;
	/** 玩家数据对象 */
//	private Player player;
	/** 玩家Actor */
	private IPlayerActor playerActor;
	/** 玩家会话 */
	private GameSession session;
	/** 玩家战斗属性 */
//	private PlayerBattle playerBattle;
	/** 玩家额外数据 */
//	private PlayerRecord playerRecord;
	/** 当前操作协议 */
	private short operatingCmd;
	/** 未下发协议 */
//	private LinkedList<IProtocol> protoNotSent = new LinkedList<>();
	/** 头像标识 */
	private String avatar;
	/** 正在战斗服战斗的标识 */
	private int fightingStage;
	/** 正在战斗服战斗的标识 */
	private int fightingCity;
	/** 缓存玩家战力*/
	private int firePower ;
	/** 游戏客户端数据 */
//	private ClientParams clientParams;
	
	
//	public PlayerContext() {
//		
//	}
//	
//	public PlayerContext(Player player) {
//		this.player = player;
//		this.playerId = player.getPlayerId();
//		this.playerBattle = new PlayerBattle(player.getLevel(), player.getMilitaryRank());
//	}
//	
//	public PlayerContext(long playerId) {
//		this.playerId = playerId;
//	}
//	
//	
//	public Player create(long playerId, String account, String name) {
//		this.player = new Player(playerId, account, name);
//		this.playerId = playerId;
//		born();
//		this.playerBattle = new PlayerBattle(player.getLevel(), player.getMilitaryRank());
//		return this.player;
//	}
//	
//	
//	public void init(GameSession session) {
//		initSession(session);
//	}
//	
//	
//	public void initSession(GameSession session) {
//		session.init(getId(), this);
//		this.session = session;
//		SessionGroup.saveSession(session);
//	}
//	
//	
//	private void born() {
//		player.setLevel(1); // 初始等级1级
//		player.setMilitaryRank(0);//初始化军衔等级
//		player.setGolden(CommonConfig.getRes(CommonRes.BORN_GOLDEN)); 
//		player.setEnergy(CommonConfig.getRes(CommonRes.BORN_ENERGY));
//		player.setPower(CommonConfig.getRes(CommonRes.BORN_POWER));
//		player.setCreateTime(new Date());
//	}
//	
//	
//	public void sex(int sex) {
//		player.setSex(sex);
//	}
//	
//	public long getId() {
//		return playerId;
//	}
//	
//	public String getPlayerName() {
//		return player.getName();
//	}
//	
//	public long getPlayerId() {
//		return player.getPlayerId();
//	}
//	
//	public String getAccount() {
//		return player.getAccount();
//	}
//	
//	public int getLevel() {
//		return player.getLevel();
//	}
//	
//	public int getExp() {
//		return player.getExp();
//	}
//	
//	public int getEnergy() {
//		return player.getEnergy();
//	}
//	
//	public String getAvatar() {
//		if (avatar == null) {
//			avatar = getSexInfo().getAvatar();
//		}
//		return avatar;
//	}
//	
//	public Player getPlayer() {
//		return player;
//	}
//
//	public IPlayerActor getPlayerActor() {
//		if (playerActor == null) {
//			playerActor = getAndInitActor();
//		}
//		return playerActor;
//	}
//	
//	public void initActor(IPlayerActor playerActor) {
//		this.playerActor = playerActor;
//		this.playerActor.initContext(this);
//	}
//	
//	private IPlayerActor getAndInitActor() {
//		IPlayerActor actor = PlayerManager.instance().getActor(playerId);
//		actor.initContext(this);
//		return actor;
//	}
//	
//	public GameSession getSession() {
//		return session;
//	}
//	
//	
//	public void push(IProtocol protocol) {
//		short cmd = protocol.protocol();
//		if (isOnline()) {
//			Object data = Packet.encode(protocol);
//			session.send(data);
//			int len = ((DataCarrier) data).getStructure().length;
//			GameLog.debug("推送协议[{}], pid={}, data={}, size={}B", cmd, playerId, protocol, len);
//		} else {
//			if (Protocols.isMustResponse(cmd)) {
//				protoNotSent.add(protocol);
//			}
//		}
//	}
//	
//	public PlayerRecord getPlayerRecord() {
//		if (playerRecord == null) {
//			playerRecord = PlayerManager.instance().getPlayerRecord(getId());
//		}
//		return playerRecord;
//	}
//	
//	
//	public boolean checkGolden(int consume) {
//		return player.checkGolden(consume);
//	}
//	
//	public boolean checkEnergy(int consume) {
//		return player.checkEnergy(consume);
//	}
//	
//	public boolean checkPower(int consume) {
//		return player.checkPower(consume);
//	}
//	
//	public boolean checkHonor(int consume) {
//		return player.checkHonor(consume);
//	}
//	
//	public boolean checkMerit(int consume) {
//		return player.checkMerit(consume);
//	}
//    public boolean checkPotence(int potence) {
//        return player.checkPotence(potence);
//    }
//    
//    public boolean checkContribution(int contrib) {
//        return player.checkContribution(contrib);
//    }
//    
//    public boolean checkPrestige(int prestige) {
//        return player.checkPrestige(prestige);
//    }
//		
//	private boolean addGolden(int increment) {
//		return player.addGolden(increment);
//	}
//
//    private boolean addEnergy(int increment) {
//		return player.addEnergy(increment);
//	}
//
//    private boolean addPower(int increment) {
//		return player.addPower(increment);
//	}
//
//    private boolean addHonor(int increment) {
//		return player.addHonor(increment);
//	}
//	
//	//清除每天限制
//	public boolean clearEveryDayLimit(){
//		CommonConfig cfg = CommonConfig.get(CommonRes.COMMON_RESET_TIME);
//		Date reshTime = playerRecord.getLimitTime();
//		if(DateUtil.isInDigitReshTime(cfg.getFactor(), reshTime)){
//			playerRecord.setBuyPowerCount(0);
//			playerRecord.setBuyGoldCount(0);
//			playerRecord.setGetMeritCount(0);
//			playerRecord.setLimitTime(new Date());
//			playerRecord.setLegionApplyNum(0);
//	    	playerRecord.update();
//			return true;
//		}
//		//每天5点，推送最新的体力信息
//		pushPowerInfoProto();
//		return false;
//	}
//	
//	/**
//	 * 可获得的功勋
//	 * @param increment
//	 * @return
//	 */
//	public int getAddMerit(int increment){
//		CommonConfig cfg = CommonConfig.get(CommonRes.MERIT_LIMIT);
//		int limitNum = Integer.valueOf(cfg.getFactor());
//		int merit = playerRecord.getGetMeritCount();
//		if(merit >= limitNum){
//			return 0;
//		}
//		int num = 0;
//		if((merit+increment) > limitNum){
//			num = limitNum - merit;
//		}else{
//			num = increment;
//		}
//		return num;
//	}
//
//    private boolean addMerit(int increment) {
//		return player.addMerit(increment);
//	}
//
//    private boolean addPotence(int potence) {
//        return player.addPotence(potence);
//    }
//
//    private boolean addContribution(int potence) {
//        return player.addContribution(potence);
//    }
//    
//    private boolean addPrestige(int prestige) {
//        return player.addPrestige(prestige);
//    }
//	
//	private boolean deductGolden(int decrement) {
//		return player.deductGolden(decrement);
//	}
//
//    private boolean deductEnergy(int decrement) {
//		return player.deductEnergy(decrement);
//	}
//
//    private boolean deductPotence(int potence) {
//        return player.deductPotence(potence);
//    }
//
//    private boolean deductContribution(int contrib) {
//        return player.deductContribution(contrib);
//    }
//    
//    private boolean deductPrestige(int prestige) {
//        return player.deductPrestige(prestige);
//    }
//    
//    private boolean deductHonor(int honor) {
//    	return player.deductHonor(honor);
//    }
//    
//    private boolean deductPower(int decrement) {
//		int oldPower = player.getPower();
//		if(player.deductPower(decrement)){
//			checkRecoverPowerStatus(oldPower);
//			return true;
//		}
//		return false;
//	}
//	
//	
//	public boolean isLvEnough(int needLv) {
//		return player.getLevel() >= needLv;
//	}
//	
//
//	
//	public boolean checkResEnough(int resType, int count) {
//		switch (resType) {
//			case ResType.GOLD:  	return checkGolden(count);
//			case ResType.ENERGY: 	return checkEnergy(count);
//			case ResType.POWER: 	return checkPower(count); 
//			case ResType.HONOR:		return checkHonor(count);
//			case ResType.MERIT:		return checkMerit(count);
//			case ResType.POTENCE: 	return checkPotence(count);
//			case ResType.CONTRIBUTION: return checkContribution(count);
//			case ResType.PRESTIGE: return checkPrestige(count);
//			default: 				return false;
//		}
//	}
//	
//	public boolean checkResFull(int resType, int count) {
//		switch (resType) {
//			case ResType.POWER: 	return player.getPower() >= StaminaCommonBase.get().getPossessMaxSta();
//			default: 				return false;
//		}
//	}
//	
//	public boolean addResource(int resType, int count, String source) {
//		//功勋有上限需要检查
//		if(resType == ResType.MERIT){
//			count = getAddMerit(count);
//			if(count > 0){
//				playerRecord.setGetMeritCount(playerRecord.getGetMeritCount() + count);
//				playerRecord.update();
//				//推送获得资源信息
//				push(PromptsProto.create(PromptCode.ALERT_10000030, count, resType));
//			}else{
//				push(PromptsProto.create(PromptCode.ALERT_10000031, resType));
//				return true;
//			}
//		}
//						
//	    boolean flag = addRes(resType, count, source);
//	    //player.update();
//	    
//        //添加荣誉和功勋，这对象也需要更新
//        switch (resType){
//            case ResType.HONOR:
//            case ResType.MERIT:
//                getPlayerRecord().update();
//        }
//        //获取资源事件
//        GameEventBus.instance().post(new PlayerReceiveResEvent(this,resType,count));
//
//        return flag;
//    }
//	
//	private boolean addRes(int resType, int count, String source) {
//		boolean added;
//		switch (resType) {
//			case ResType.GOLD:  
//				added = addGolden(count);
//				try {
//					PlayerLog.obtainNotRechargeRes(this, count, source, resType);
//				} catch (Exception e) {
//					GameLog.error("金币记录日志错误：{}", e);
//				}
//				return added;
//			case ResType.ENERGY: 	
//				added = addEnergy(count);
//				try {
//					PlayerLog.obtainEnergy(this, count, source);
//				} catch (Exception e) {
//					GameLog.error("能源记录日志错误：{}", e);
//				}
//				return added;
//			case ResType.POWER:
//				added = addPower(count);
//			    return added;
//			case ResType.HONOR:
//				added = addHonor(count);
//				getPlayerRecord().addHonor(count);
//			    try {
//				    PlayerLog.obtainNotRechargeRes(this, count, source, resType);
//				} catch (Exception e) {
//					GameLog.error("荣誉记录日志错误：{}", e);
//				}
//                return added;
//			case ResType.MERIT:
//				added = addMerit(count);
//			    getPlayerRecord().addMerit(count);
//			    try {
//				    PlayerLog.obtainNotRechargeRes(this, count, source, resType);
//				} catch (Exception e) {
//					GameLog.error("功勋记录日志错误：{}", e);
//				}
//                return added;
//			case ResType.PLAYER_EXP:	
//				int oldLevel = getLevel();
//				addExp(count);
//				int newLevel = getLevel();
//				try {
//					PlayerLog.obtainExp(this, oldLevel, newLevel, count, source);
//				} catch (Exception e) {
//					GameLog.error("添加经验记录日志错误：{}", e);
//				}
//				return true;
//            case ResType.POTENCE:
//            	added = addPotence(count);
//            	try {
//                	PlayerLog.obtainNotRechargeRes(this, count, source, resType);
//				} catch (Exception e) {
//					GameLog.error("添加潜能点记录日志错误：{}", e);
//				}
//                return added;
//            case ResType.CONTRIBUTION:
//            	added = addContribution(count);
//            	try {
//                	PlayerLog.obtainNotRechargeRes(this, count, source, resType);
//				} catch (Exception e) {
//					GameLog.error("个人贡献点记录日志错误：{}", e);
//				}
//                return added;
//            case ResType.PRESTIGE:
//            	added = addPrestige(count);
//            	try {
//                	PlayerLog.obtainNotRechargeRes(this, count, source, resType);
//				} catch (Exception e) {
//					GameLog.error("声望点记录日志错误：{}", e);
//				}
//                return added;
//            case ResType.VIPEXP:
//            	VipContext vipContext = VipManager.insance.getVipContext(this.getPlayerId());
//        		if(vipContext != null) {
//        			vipContext.addVipExp(this, count);
//        			vipContext.pushVipInfo(this);
//        		}
//            	try {
//                	PlayerLog.obtainNotRechargeRes(this, count, source, resType);
//				} catch (Exception e) {
//					GameLog.error("vip经验点记录日志错误：{}", e);
//				}
//                return count>0;
////            case ResType.INTEGRAL:
////            	added = addPrestige(count);
////            	
////            	;
//			default:
//			    return false;
//		}
//
//	}
//
//	public boolean deductResource(int resType, int count, String source) {
//		boolean deducted;
//		switch (resType) {
//			case ResType.GOLD:  	
//				deducted = deductGolden(count);
//				if (deducted) {
//					PlayerLog.consumeGolden(this, count, source, 0, 1, resType);
//				}
//				return deducted;
//			case ResType.ENERGY: 	
//				deducted = deductEnergy(count);
//				if (deducted) {
//					PlayerLog.consumeEnergy(this, count, source, 0, 1);
//					GameEventBus.instance().post(new PlayerConsumeEvent(this, resType, count));
//				}
//				return deducted;
//			case ResType.POWER: 	
//				deducted = deductPower(count);
//				if (deducted) {
//					PlayerLog.consumeGolden(this, count, source, 0, 1, resType);
//				}
//				return deducted;
//			case ResType.HONOR:			
//				deducted = deductHonor(count);
//				if (deducted) {
//					PlayerLog.consumeGolden(this, count, source, 0, 1, resType);
//				}
//				return deducted;
//            case ResType.POTENCE:   
//            	deducted = deductPotence(count);
//				if (deducted) {
//					PlayerLog.consumeGolden(this, count, source, 0, 1, resType);
//				}
//            	return deducted;
//            case ResType.CONTRIBUTION: 	
//            	deducted = deductContribution(count);
//				if (deducted) {
//					PlayerLog.consumeGolden(this, count, source, 0, 1, resType);
//				}
//            	return deducted;
//            case ResType.PRESTIGE: 	
//            	deducted = deductPrestige(count);
//				if (deducted) {
//					PlayerLog.consumeGolden(this, count, source, 0, 1, resType);
//				}
//            	return deducted;
//			default: 		
//				return false;
//		}
//	}
//	
//	
//	public void addExp(int exp) {
//		int preLevel = getLevel();
//		int upLevel = player.addExp(exp);
//		if (upLevel > preLevel) {
//			levelUp(preLevel, upLevel);
//		} else {
//			pushExpInfo();
//		}
//		player.update(); // 异步
//	}
//	
//	private void levelUp(int preLevel, int upLevel) {
//		addPowerFromLevelUp(preLevel, upLevel);
//		pushLevelUp(preLevel, upLevel);
//		
//		GameEventBus.instance().post(new LevelUpEvent(this, preLevel, upLevel)); // 派发升级事件
//		playerBattle.levelUp(upLevel);
//		calcAttributes();
//        pushAttrs();
//        
//        PlayerLog.playerLevelUp(this, preLevel, upLevel); // 升级日志
//        
//	} 
//	
//	public void militaryRanklevelUp(int newRank) {
//		player.setMilitaryRank(newRank);
//		player.update();
//		playerBattle.levelUpMiliRank(newRank);
//		calcAttributes();
//		push(playerBattle.toAttrsProto());
//		pushMilitaryRank();
//	}
//	
//	private void addPowerFromLevelUp(int preLevel, int upLevel){
//		int i = upLevel - preLevel;
//		if(i <= 0){
//			return;
//		}
//		//升级成功后，处理体力的赠送
//		for (int j = 0; j < i; j++) {
//			StaminaMaxPerLevelBase base = StaminaMaxPerLevelBase.get(preLevel + j);
//			addPower(base.getGotStaminaPerLevel());
//		}
//	}
//	
//	
//	private void pushLevelUp(int preLevel, int upLevel) {
//		PlayerResProto protocol = new PlayerResProto();
//		protocol.addAttr(CommonRes.LEVEL_ID, upLevel);
//		protocol.addAttr(CommonRes.EXP_ID, getExp());
//		protocol.addAttr(CommonRes.POWER_ID, player.getPower());
//		push(protocol);
//		
//		S2CLevelUp levelUpMsg = S2CLevelUp.newBuilder()
//				.setOldLevel(preLevel).setUpLevel(upLevel).build();
//		short cmd = ProtocolCode.PLAYER_S2C_LEVEL_UP;
//		push(SimpleProtocol.create(cmd, levelUpMsg));
//	}
//	
//	
//	public void pushPowerRes() {
//		PlayerResProto protocol = new PlayerResProto();
//		protocol.addAttr(CommonRes.POWER_ID, player.getPower());
//		push(protocol);
//	}
//	
//	public void pushExpInfo() {
//		PlayerResProto protocol = new PlayerResProto();
//		protocol.addAttr(CommonRes.EXP_ID, player.getExp());
//		push(protocol);
//	}
//	
//	public void pushProtosBeforeOffline() {
//		int protoSize = protoNotSent.size();
//		for (int i = 0; i < protoSize; i++) {
//			push(protoNotSent.poll());
//		}
//	}
//	
//	public void pushMilitaryRank() {
//		PlayerResProto protocol = new PlayerResProto();
//		protocol.addAttr(CommonRes.MILITARY_RANK_ID, player.getMilitaryRank());
//		push(protocol);
//	}
//	
//	public void pushMeritInfo() {
//		PlayerResProto protocol = new PlayerResProto();
//		protocol.addAttr(CommonRes.MERIT_ID, player.getMerit());
//		push(protocol);
//	}
//	
//	
//	/**
//	 * 注意: 不能传null
//	 * 
//	 * @param protocols
//	 */
//	public void push(IProtocol... protocols) {
//		for (IProtocol protocol : protocols) {
//			push(protocol);
//		}
//	}
//	
//	public void calcAttributes() {
//		Attributes total = new Attributes();
//        //基础属性
//		Attributes baseAttrs = playerBattle.getBaseAttrs();
//		total.addAttrs(baseAttrs);
//		//军衔属性
//        Attributes miliRankAttrs = getMiliRankAttrs();
//        total.addAttrs(miliRankAttrs);
//
//        //装备
//		PlayerEquip pEquip = EquipManager.instance().getPlayerEquip(getId());
//		total.addAttrs(pEquip.attrs(this));
//        //时装
//        PlayerDress playerDress = DressManager.instance().getPlayerDress(this);
//        total.addAttrs(playerDress.attrs());
//        //基因
//        PlayerGene playerGene = GeneManager.instance().getPlayerGene(this);
//        Attributes geneAttr = playerGene.attrs(1,baseAttrs);
//        total.addAttrs(geneAttr);
//
//        //投掷武器
//        PlayerMissile playerMissile = MissileManager.instance().getPlayerMissile(this);
//        Attributes missileAttrs = playerMissile.attrsToPlayer();
//        total.addAttrs(missileAttrs);
//        //辅助道具
//        PlayerAssistanceItem playerAssistanceItem = AssistanceItemManager.instance().getPlayerAssistanceItem(this);
//        Attributes assistanceItemAttrs = playerAssistanceItem.attrsToPlayer();
//        total.addAttrs(assistanceItemAttrs);
//        //飞行器
//        Fly fly = FlyManager.instance().getFly(getPlayerId());
//        Attributes fightArrrs = fly.getFightArrrs(this);
//        total.addAttrs(fightArrrs);
//        
//        //科技,假如找不到军团,则不能增加属性
//        //改成了即使找不到军团,也可以增加属性2017.08.31
//        LegionTechnology legionTechCtx = LegionTechManager.instance().getLegionTechContext(getId());
//        Attributes techAttr = legionTechCtx.attrs(1, baseAttrs);
//        total.addAttrs(techAttr);
//        /*
//        LegionContext legionCtx = LegionManager.instance().getLegionContextByPlayerId(this.getId());
//        if (legionCtx!=null) {
//        	Legion legion = legionCtx.getLegion();
//        	if (legion!=null) {
//        		LegionMember legionMember = legion.getMember(this.getPlayerId());
//        		if (legionMember!=null) {
//        			LegionMemberRecord legionMemberRecord = legionMember.getLegionMemberRecord();
//        			Attributes techAttr = legionMemberRecord.attrs(1, baseAttrs);
//        	        total.addAttrs(techAttr);
//				}
//			}
//		}*/
//        
//        //芯片增益属性
//        WaferContext waferContext = WaferManager.instance().getWaferContext(this);
//        Attributes waferAttrs = waferContext.attrs();
//        
//        total.addAttrs(waferAttrs);
//        
//		playerBattle.refreshAttrs(total);
//	}
//	
//	
//	public int getMiliRank() {
//		return player.getMilitaryRank();
//	}
//	
//	/**
//	 * 得到军衔属性
//	 * 
//	 * @return {@link Attributes}
//	 */
//	public Attributes getMiliRankAttrs() {
//        Attributes miliRankAttrs = playerBattle.getMiliRankAttrs();
//        return miliRankAttrs;
//	}
//	
//	public Attributes getBaseAttrs() {
//		return playerBattle.getBaseAttrs();
//	}
//	
//	public Attributes getTotalAttrs() {
//		return playerBattle.getTotalAttrs();
//	}
//	
//	
//	public void onLogin() {
//		calcAttributes(); // 计算总属性
//		GameEventBus.instance().post(new LoginEvent(this)); // 派发登录事件
//	}
//	
//	private void pushLoginBaseMessage() {
//		loginPowerEvent();
//		pushBaseInfo();
//		pushRecordMsg();
//		pushMilitaryRankMark();
//		push(playerRecord.toGameRecordProto());
//		pushFightPower();
//	}
//	
//	public void onCreate() {
//		GameEventBus.instance().post(new RoleBornEvent(this)); // 派发出生事件
//		onLogin();
//	}
//
//	public void pushAttrs() {
//		push(playerBattle.toAttrsProto());
//        pushFightPower();
//	}
//	
//	private void pushRecordMsg() {
//		CommonMsg.S2CRecordMsg.Builder builder = CommonMsg.S2CRecordMsg.newBuilder();
//        ClientData clientBytes = getPlayerRecord().getClientData();
//        byte[] bytes = clientBytes.getRecordMsg();
//        if (bytes.length > 0) {
//            builder.setData(ByteString.copyFrom(bytes));
//        }else {
//            builder.setData(ByteString.EMPTY);
//        }
//
//		push(SimpleProtocol.create(ProtocolCode.COMMON_S2C_RECORD_MSG, builder.build()));
//	}
//	
//	public void pushEnterScence() {
//		if (isOnline()) {
//			short cmdId = ProtocolCode.COMMON_S2C_ENTER_MAIN_SCENCE;
//			push(SimpleProtocol.create(cmdId));
//		}
//	}
//	
//	public void pushReturnEarth(int stageId) {
//		if (isOnline()) {
//			S2CReturnStage.Builder builder = S2CReturnStage.newBuilder();
//			builder.setStageId(stageId);
//			push(SimpleProtocol.create(ProtocolCode.STAGE_S2C_RETURN, builder.build()));
//		}
//	}
//	
//	private void pushBaseInfo() {
//		PlayerAttrsProto attrs = playerBattle.toAttrsProto();
//		PlayerResProto resouces = player.toResProto();
//		push(attrs, resouces);
//	}
//	
//	public void pushFightPower() {
//		int firePower = FightCapForDisBase.calcFirePowerCap(this);
//		this.firePower = firePower;
//		PlayerResProto resProto = new PlayerResProto();
//		resProto.addAttr(CommonRes.FIREPOWER, firePower);
//		push(resProto);
//	}
//	
//	public void pushResourceUpdate(Integer... resTypes) {
//		push(player.toResProto(resTypes));
//	}
//	
//	/**
//	 * 得到玩家战力
//	 * @return
//	 */
//	public int getFirePower(){
//		if(this.firePower <= 0){
//			int firePower = FightCapForDisBase.calcFirePowerCap(this);
//			this.firePower = firePower;
//		}
//		return this.firePower;
//	}
//	
//	private void loginPowerEvent() {
//		checkPowerRefresh();  //刷新恢复体力数据
//		pushPowerInfoProto(); //推送恢复体力相关信息
//	}
//	
//	
//	public void operateCmd(short cmd) {
//		this.operatingCmd = cmd;
//	}
//	
//	
////	public void responseSuc() {
////		S2CCommonReply.Builder builder = S2CCommonReply.newBuilder();
////		builder.setProtoCode(this.operatingCmd);
////		builder.setSuccess(1);
////		push(SimpleProtocol.create(ProtocolCode.COMMON_S2C_REPLY, builder.build()));
////	}
//	
//	
////	public void responseSuc(int attachment) {
////		S2CCommonReply.Builder builder = S2CCommonReply.newBuilder();
////		builder.setProtoCode(this.operatingCmd);
////		builder.setSuccess(1);
////		builder.setAttachment(attachment);
////		push(SimpleProtocol.create(ProtocolCode.COMMON_S2C_REPLY, builder.build()));
////	}
//	
//	
//	public void responseFail() {
//		S2CCommonReply.Builder builder = S2CCommonReply.newBuilder();
//		builder.setProtoCode(this.operatingCmd);
//		builder.setSuccess(0);
//		push(SimpleProtocol.create(ProtocolCode.COMMON_S2C_REPLY, builder.build()));
//	}
//	
//	
//	public boolean isOnline() {
//		return session != null && session.isConnect();
//	}
//
//	
//	public long getOfflineTime() {
//		return player.getOfflineTime().getTime();
//	}
//	
//	
//	public long getOnlineTime() {
//		long offlineTime = getOfflineTime();
//		long loginTime = player.getLastLoginTime().getTime();
//		return offlineTime - loginTime;
//	}
//	
//	
//	public void disconnect() {
//		sessionClose();
//		logoutRecord();
//		GameEventBus.instance().post(new DisconnectEvent(this)); // 广播离线事件
//	}
//	
//	
//	public void logoutRecord() {
//		player.setOfflineTime(new Date());
//		player.update();
//	}
//	
//	
//	public void sessionClose() {
//		SessionGroup.closeSession(getId());
//	}
//	
//	
//	public void refreshDaily() {
//		LinkedList<AbstractJobEvent> events = JobManager.instance().getEvent(getId());
//        for (AbstractJobEvent event : events) {
//            event.dailyEvent();
//        }
//        //请求客户端刷新数据
//        pushRefeshData();
//	}
//	
//	public void refreshMinute() {
//		LinkedList<AbstractJobEvent> events = JobManager.instance().getEvent(getId());
//        for (AbstractJobEvent event : events) {
//            event.minuteEvent();
//        }
//	}
//
//    /**
//     * 生成serverToken
//     * @return
//     */
//	private String genServerToken() {
//        try {
//            Map<String,String> dataMap = new HashMap<>();
//            dataMap.put("playerId",String.valueOf(getPlayerId()));
//            dataMap.put("account",getAccount());
//            dataMap.put("time",String.valueOf(System.currentTimeMillis()));
//
//            String data = JSON.toJSONString(dataMap);
//            byte[] cipherData = RSAEncrypt.get().encryptByRSAPrivateKey(data.getBytes());
//            return Base64.encode(cipherData);
//        } catch (Exception e) {
//            GameLog.error("genServerToken err:",e);
//        }
//        return "";
//    }
//
//	public void processLogin(GameSession session, boolean isCreate) {
//		try {
//			init(session);
//			
//			PlayerManager.instance().cache(this);  // 缓存玩家数据
//			
//			LoginProtocol protocol = new LoginProtocol(LoginConstant.SUCCESS);
//			protocol.set(getId(), getPlayerName());
//			protocol.setServerToken(genServerToken());
//			push(protocol);
//			
//			if (isCreate) {
//				LoadDataManager.instance().createAll(this);
//				onCreate();
//			} else {
//				LoadDataManager.instance().loadAll(this);
//				onLogin();
//			}
//			
//			pushLoginBaseMessage();
//			
//			//TODO 这里的事件会产生不可预估的问题
//			GameEventBus.instance().post(new LoginEndEvent(this)); // 派发登录结束事件
//			pushEnterScence();
//			loginFightEnd(); 
//			
//			initJobEvent(); // 初始化定时器
//			player.login(getClientParams());
//			push(SimpleProtocol.create(ProtocolCode.LOGIN_S2C_PUSH_DATA_END)); // 数据下发结束
//			
//			obtainGMPurview(); // 检测GM权限
//		} catch (Exception e) {
//			GameLog.error("登录过程出错", e);
//			PlayerManager.instance().endLogin(player.getAccount());
//		}
//	}
//
//    /**
//     * 初始化定时任务
//     */
//    private void initJobEvent() {
//        // 先清除以前的任务
//        JobManager.instance().removeJobEvent(getId());
//        // 初始化
//        JobManager.instance().initJobEvents(getId());
//    }
//	
//	
//	public void destroy() {
//		if (playerActor != null) {
//			AkkaContext.stopTypedActor(playerActor); // 停止TypedActor
//			playerActor = null; 
//		}
//		if (session != null) {
//			session.destroy(); // 销毁userData
//			session = null;
//		}
//	}
//	
//	
//	/**
//	 * 获得GM权限
//	 */
//	public void obtainGMPurview() {
//		if (!player.isGM() && PrivilegeUserBase.contains(getAccount())) {
//			player.setGmPurview(1); // 1-GM
//		}
//	}
//	
//	/**
//	 * 检查恢复体力的状态，如果小于自然恢复上限，就运行恢复体力
//	 */
//	private void checkRecoverPowerStatus(int oldPower) {
//		StaminaMaxPerLevelBase staminaMaxPerLevelBase = StaminaMaxPerLevelBase.get(getLevel());
//		//每级体力恢复上限
//		int maxPower = staminaMaxPerLevelBase.getStaminaMaxPerLevel();
//		if(maxPower > oldPower)
//			return;
//		if(maxPower <= getPlayer().getPower())
//			return;
//		playerRecord.setRecoverPowerTime(DateUtil.truncateSecond());
//		playerRecord.update();
//		pushPowerInfoProto();
//	}
//	
//	
//	/**
//	 * 登录时，刷新恢复体力数据
//	 */
//	private void checkPowerRefresh() {
//		StaminaMaxPerLevelBase staminaMaxPerLevelBase = StaminaMaxPerLevelBase.get(getLevel());
//		//每级体力恢复上限
//		int maxPower = staminaMaxPerLevelBase.getStaminaMaxPerLevel();
//		if(maxPower <= getPlayer().getPower())
//			return;
//		//上一次恢复体力时间
//		Date recoverPowerTime = playerRecord.getRecoverPowerTime();
//		boolean isUpdate = false;
//		if(recoverPowerTime == null){
//			addPower(maxPower - getPlayer().getPower());
//			isUpdate=true;
//			//已经恢复满了，设置当前时间
//			playerRecord.setRecoverPowerTime(DateUtil.truncateSecond());
//		}else{
//			StaminaCommonBase staminaCommonBase = StaminaCommonBase.get();
//			//间隔多少分钟恢复一点
//			int intervalMinute = staminaCommonBase.getTimeInterval();
//			//以分钟计算
//			long lastRecoverTime = DateUtil.truncateSecond(recoverPowerTime).getTime();
//			long curTime = DateUtil.truncateSecond().getTime();
//			long minute = (curTime - lastRecoverTime)/(1000 * 60);
//			int num = (int)(minute/intervalMinute);
//			if(num > 0){
//				if((getPlayer().getPower() + num) > maxPower){
//					addPower(maxPower - getPlayer().getPower());
//					//已经恢复满了，设置当前时间
//					playerRecord.setRecoverPowerTime(DateUtil.truncateSecond());
//				}else {
//					addPower(num);
//					//恢复了num点体力，在原来恢复时间上加intervalMinute*num分钟
//					playerRecord.setRecoverPowerTime(DateUtil.addMinute(recoverPowerTime, intervalMinute*num));
//				}
//				isUpdate=true;
//			}
//		}
//		if (isUpdate) {
//			playerRecord.update();
//			player.update();
//		}
//	}
//	
//	/**
//	 * 恢复体力
//	 */
//	public void recoverPower() {
//		StaminaMaxPerLevelBase staminaMaxPerLevelBase = StaminaMaxPerLevelBase.get(getLevel());
//		if(staminaMaxPerLevelBase.getStaminaMaxPerLevel() <= getPlayer().getPower())
//			return;
//		Date recoverPowerTime = playerRecord.getRecoverPowerTime();
//		boolean isUpdate = false;
//		if(recoverPowerTime == null){
//			recoverIncPower();
//			isUpdate=true;
//		}else{
//			StaminaCommonBase staminaCommonBase = StaminaCommonBase.get();
//			//加间隔时间
//			recoverPowerTime = DateUtil.addMinute(recoverPowerTime, staminaCommonBase.getTimeInterval());
//			if(System.currentTimeMillis() >= recoverPowerTime.getTime() || DateUtil.isMinute(new Date(), recoverPowerTime)){
//				recoverIncPower();
//				isUpdate=true;
//			}
//		}
//		if (isUpdate) {
//			playerRecord.setRecoverPowerTime(DateUtil.truncateSecond());
//			playerRecord.update();
//			player.update();
//			pushPowerInfoProto();
//			push(player.toResProto(ResType.POWER));
//		}
//	}
//	
//	/**
//	 * 恢复体力+1
//	 */
//	private void recoverIncPower() {
//		addPower(1);
//	}
//	
//	public void pushPowerInfoProto() {
//		S2CPushPowerInfo.Builder builder = S2CPushPowerInfo.newBuilder();
//		builder.setPowerInfo(playerRecord.toPowerInfoProto());
//		push(SimpleProtocol.create(ProtocolCode.PLAYER_S2C_PUSH_POWER_INFO, builder.build()));
//	}
//	
//	public void pushRefeshData() {
//		S2CEmptyMsg.Builder builder = S2CEmptyMsg.newBuilder();
//		push(SimpleProtocol.create(ProtocolCode.PLAYER_S2C_REFRESH_DATA, builder.build()));
//	}
//	
//	/**
//	 * 体力不足弹框购买
//	 */
//	public void pushPowerLack() {
//		int buyCount = playerRecord.getTodayBuyPowerCount();
//		StaminaBuyBase maxBuyBase = StaminaBuyBase.getMaxBuyTime();
//		StaminaBuyBase dataBase = StaminaBuyBase.get(buyCount+1);
//		if(dataBase == null){
//			dataBase = maxBuyBase;
//		}
//		CommonConfig common = CommonConfig.get(CommonRes.POWER_BUY_NUM);
//		int baseNum = common.getInt();
//		int limit = VipHelper.getLimit(this, VipType.buy_power_num);
//		int canBuy = baseNum + limit ;
//		
//		short protoCode = ProtocolCode.PLAYER_C2S_BUY_POWER;
//		push(PromptsProto.alert(PromptCode.ALERT_10000001,protoCode,dataBase.getCostGold(), dataBase.getGetStamina(), buyCount, canBuy));
//	}
//	
//	/**
//	 * 推送军衔升级标志
//	 */
//	public void pushMilitaryRankMark() { 
//		MilitaryRankBase dataBase = MilitaryRankBase.get(player.getMilitaryRank());
//		if(dataBase == null || dataBase.getTotalHonor() < 1){
//			return;
//		}
//		//功勋不足
//		if(!checkResEnough(ResType.MERIT, dataBase.getTotalHonor())){
//			return;
//		}
//		//推送军衔升级标志
//		S2CMilitaryRankLevelUpMark.Builder builder = S2CMilitaryRankLevelUpMark.newBuilder();
//		push(SimpleProtocol.create(ProtocolCode.PLAYER_S2C_CLEAR_MILITARY_RANK_MARK, builder.build()));
//	}
//	
//	public Sex getSexInfo() {
//		return Sex.values()[player.getSex()];
//	}
//
//    /**
//     * 获取总战斗力
//     * @return
//     */
//	public int getFightingCapacity(int stageId) {
//	    SystemFightCapForActityBase actityBase = SystemFightCapForActityBase.get(stageId);
//        if (actityBase == null)
//            return 0;
//        //主角
//        float role = calcFightingCapacity() * actityBase.getRole();
//        //装备
//        float equ = EquipManager.instance().getPlayerEquip(getPlayerId()).calcFightingCapacity() * actityBase.getEqu();
//        //机甲
//        float meach = MechaManager.instance().getMecha(this).calcFightingCapacity() * actityBase.getMeach();
//        //炮台
//        float turret = TurretManager.instance().getTurret(getPlayerId()).calcFightingCapacity() * actityBase.getTurret();
//        //武器
//        WeaponWarehouse weaponWarehouse = WeaponManager.instance().getWeaponWarehouse(this);
//        float mainWea = weaponWarehouse.inUseWeapon(Weapon.TYPE_FIRST).calcFightingCapacity() * actityBase.getMainWea();
//        Weapon weaAss = weaponWarehouse.inUseWeapon(Weapon.TYPE_SECOND);
//        float assWea = 0;
//        if (weaAss != null){
//            assWea = weaAss.calcFightingCapacity() * actityBase.getAssWea();
//        }
//        //辅助道具
//        float assis = AssistanceItemManager.instance().getPlayerAssistanceItem(this).calcFightingCapacity() * actityBase.getAssis();
//        //投掷武器
//        float missile = MissileManager.instance().getPlayerMissile(this).calcFightingCapacity() * actityBase.getMissile();
//        float v = role + equ + meach + turret + mainWea + assWea + assis + missile;
//
//        GameLog.info("calcFightingCapacity,stageId:{}, role:{}, equ:{}, meach:{}, turret:{}, mainWea:{}, assWea:{}, assis:{}, missile:{}",
//                stageId,role , equ , meach , turret , mainWea , assWea , assis , missile);
//
//        return (int)v;
//    }
//
//    /**
//     * 计算玩家部分，战斗力
//     * @return
//     */
//    private float calcFightingCapacity() {
//        //等级*0.15
//        SystemFightCapBase base = SystemFightCapBase.get();
//        return getLevel() * base.getRoleLevel();
//    }
//
//    /**
//     * 连胜败次数
//     * @return
//     */
//    public int getVictoryOrdefeatCount() {
//        return getPlayerRecord().getVictoryOrdefeatCount();
//    }
//    
//    /**
//     * 发放军衔奖励
//     */
//    public void distributionMilitaryRankReward() {
//    	//已经领取的不发放
//    	if(!clearEveryDayLimit()){
//    		return;
//    	}
//    	
//    	//功能没有开启不发邮件
//    	if(!FunctionHelper.checkOpenByCache(this, FunctionType.MILITARY_RANK)){
//    		return;
//    	}
//    	sendMilitaryRankReward();
//    }
//    
//    public void sendMilitaryRankReward() {
//    	MilitaryRankBase dataBase = MilitaryRankBase.get(player.getMilitaryRank());
//    	if (dataBase == null) {
//    		GameLog.error("邮件奖励出错, 军阶奖励找不到配置:{}", player.getMilitaryRank());
//    		return;
//		}
//    	//检测奖励是否有误
//    	List<Material> rewards = DropHelper.processDrop(getId(), dataBase.getDailyReward());
//    	if (rewards == null) {
//    		GameLog.error("邮件奖励出错, playerId:{}, rewardId(军衔奖励id):{}", getId(), dataBase.getDailyReward());
//    		return;
//		}
//    	String title = MailConfig.THEME_3230001;
//		MailBase mailBase = MailBase.get(Integer.valueOf(title));
//    	PlayerMail.sendMail(playerId, MailType.SYSTEM, title, mailBase.getText()+"", new ArrayList<>(rewards));
//    }
//	
//	
//	public void fighting(int stageId) {
//		this.fightingStage = stageId;
//	}
//	
//	public void fightEnd() {
//		this.fightingStage = 0;
//		this.fightingCity = 0;
//	}
//	
//	public int getFightingStage() {
//		return fightingStage;
//	}
//	
//	public void setFightingCity(int cityId) {
//		this.fightingCity = cityId;
//	}
//	
//	public int getFightingCity(){
//		return this.fightingCity;
//	}
//	
//	public void loginFightEnd() {
//		if (fightingStage > 0) {
//			fightEnd();
//			FightRemoteManager manager = FightRemoteManager.instance();
//			ActorInvoker invoker = manager.getActor(playerId);
//			if (invoker == null) {
//				return;
//			}
//			invoker.invoke(IFightRemoteActor.exitFightRoom, playerId).remoteCall();
//			push(PromptsProto.create(PromptCode.DUANXIAN_1000091));
//		}
//	}
//
//	public Dress getDress() {
//	    PlayerDress playerDress = DressManager.instance().getPlayerDress(this);
//        return playerDress.getPutOnDress();
//    }
//	/***
//	 * 推送玩家首冲状态
//	 */
////	public void pushFirstChargeState(){
////		FirstCharge.Builder builder=FirstCharge.newBuilder();
////		builder.setChargeState(player.getFirstCharge());
////		this.push(SimpleProtocol.create(ProtocolCode.PLAYER_S2C_FIRST_CHAEGE_INFO, builder.build()));
////	}
//	/***
//	 * 玩家充值,设置首冲状态
//	 */
////	public void charge(){
////		Integer firstCharge = player.getFirstCharge();
////		if(firstCharge>0){
////			return ;
////		}
////		player.setFirstCharge(1);
////		pushFirstChargeState();
////	}
//	/***
//	 * 领取首冲奖励
//	 */
//	public void rewardFirstCharge(){
//		Integer firstCharge = player.getFirstCharge();
//		if(firstCharge != 1){
//			this.push(PromptsProto.create(PromptCode.ERROR_10000034));
//			return;
//		}
//		CommonConfig common = CommonConfig.get(CommonRes.FIRST_CHARGE_DROP);
//		List<Material> rewars = DropHelper.processDrop(playerId, common.getInt());
//		RewardContext rewardCtx = new RewardContext(this);
//        rewardCtx.source(SourceNature.FIRST_CHARGE);
//		
//		//推送特定弹框
//		LotteryRewardProto proto = new LotteryRewardProto(0);
//		for (Material reward : rewars) {
//			if (reward.getType() == MaterialType.WEAPON && rewardCtx.checkWeaponHasDecomposition(reward)) {
//				List<Material> maters = rewardCtx.getWeaponDecomposition(reward);
//				proto.build(maters.get(0), reward.getGoodsId(), reward.getType());
//			} else
//				proto.build(reward);
//		}
//		
//		rewardCtx.reward(rewars);
//		
//		player.setFirstCharge(2);
//		player.update();
//
//		this.push(proto);
//	}
//	
//	/**
//	 * 玩家不在线，默认客服端信息
//	 * @return
//	 */
//	public ClientParams getClientParams() {
//		if(clientParams == null)
//		{
//			ClientParams defaultClientParams = new ClientParams(this);
//			return defaultClientParams;
//		}
//		return clientParams;
//	}
//
//	public void setClientParams(ClientParams clientParams) {
//		this.clientParams = clientParams;
//	}
//	

}
