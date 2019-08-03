package com.fatiny.cardloginplus.service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatiny.cardloginplus.domain.entity.Account;
import com.fatiny.cardloginplus.module.core.base.AbstractLoginParam;
import com.fatiny.cardloginplus.repository.AccountRepository;
import com.fatiny.cardloginplus.util.MD5;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@Service
public class AccountService {

	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccountRepository accountRepository;
	
	/**
	 * 缓存数据, 定时清除
	 * username 为key, 不通阶段更新成不同的信息
	 * 登陆: username-> seesionKey
	 * 验证: username-> Account
	 * 此时, 如果再次获取username 则会报错
	 */
	Cache<String, String> cache = CacheBuilder.newBuilder()
			.expireAfterAccess(5, TimeUnit.SECONDS)// 在给定时间内没有被读/写访问,则清除
			.maximumSize(100)// 最大容量
			.concurrencyLevel(5) //并发等级
			.initialCapacity(20)//初始容量
			.build();
	
	/**
	 * 创建全平台唯一的账号
	 * 生成规则:Md5(ch+ChannelUId+ChannelUname)
	 * @return  
	 * @return String  
	 * @date 2019年5月14日下午5:58:47
	 */
	public String createUserName(AbstractLoginParam loginParam) {
		StringBuilder builder = new StringBuilder();
		builder.append(loginParam.getCh());
		builder.append(loginParam.getChannelUId());
		builder.append(loginParam.getChannelUname());
		String username = MD5.digest(builder.toString(), true);
		return username;
	}
	
	/**
	 * 获取账号
	 */
	public Account getAccount(String username)
	{
		if(StringUtils.isBlank(username))
			return null;
		Optional<Account> optional = accountRepository.findById(username);
		return optional.get();
	}
	
	/**
	 * 创建账号
	 * @param loginParam 登陆参数
	 * @param userName 游戏账号
	 * @return  
	 * @return Account  
	 * @date 2019年5月14日下午6:17:20
	 */
	public Account createAccount(AbstractLoginParam loginParam, String userName)
	{
		if(StringUtils.isBlank(userName))
			return null;
		
		Account account = new Account();
		account.setUserName(userName);
		account.setPassword("123456");
		account.setUserKey("asdfajioej");	//预留
		account.setChannel(loginParam.getCh());
		account.setChannelUid(loginParam.getChannelUId());
		account.setChannelUname(loginParam.getChannelUname());
		account.setInputUname(loginParam.getInputUname());
		account.setSubChannel(0);
		account.setRegTime(new Date());
		account.setLastTime(new Date());
		account.setOs(loginParam.getOs().equalsIgnoreCase("ios")?0:1);
		account.setIsSuper(false);
		account.setIsBan(false);
		account.setLastServer(0);
		account = accountRepository.save(account);
		return account;
	}
	
	/**
	 * 创建账号
	 * @param username
	 * @param os ios or android
	 * @param channel
	 * @return
	 */
	public Account createAccount(String username, String os, int channel)
	{//username=&platform=android&channel=1
		if(StringUtils.isBlank(username))
			return null;
		
		Account account = new Account();
		account.setUserName(username);
		account.setPassword("123456");
		account.setUserKey("asdfajioej");	//预留
		account.setChannel(channel);
		account.setSubChannel(0);
		account.setRegTime(new Date());
		account.setLastTime(new Date());
		account.setOs(os.equalsIgnoreCase("ios")?0:1);
		account.setIsSuper(false);
		account.setIsBan(false);
		account.setLastServer(0);
		account = accountRepository.save(account);
		return account;
	}
	
	/**
	 * 更新账号
	 */
	public boolean updateAccount(final Account account)
	{
		Account ret = this.accountRepository.save(account);
		//cache.put(account.getUserName(), account);
		return ret != null;
	}
	
	/**
	 * 删除账号
	 */
	public boolean deleteAccount(String username)
	{
		if(StringUtils.isBlank(username))
			return false;
		this.accountRepository.deleteById(username);
		return true;
	}
		
	/**
	 * 创建sessionKey
	 * @return  
	 * @return String  
	 * @date 2019年5月14日下午5:57:14
	 */
	public String createSessionKey(String username)
	{
		String sessionKey = createSessionKey();
		cache.put(username, sessionKey);
		return sessionKey;
	}
	
	/**
	 * 创建sessionKey
	 * @return  
	 * @return String  
	 * @date 2019年5月14日下午5:57:14
	 */
	private String createSessionKey()
	{
        //32位UUID
		 String uuid = UUID.randomUUID().toString().replace("-", "");
		 return uuid.substring(22);
	}
	
	/**
	 * 创建sessionKey
	 * @return  
	 * @return String  
	 * @date 2019年5月14日下午5:57:14
	 */
	public String getSessionKey(String username){
		return cache.getIfPresent(username);
	}
	
}
