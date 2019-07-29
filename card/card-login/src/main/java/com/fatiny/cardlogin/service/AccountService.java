package com.fatiny.cardlogin.service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatiny.cardlogin.domain.entity.Account;
import com.fatiny.cardlogin.module.common.impl.AbstractLoginParam;
import com.fatiny.cardlogin.repository.AccountRepository;
import com.fatiny.cardlogin.util.MD5;

@Service
public class AccountService {

	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccountRepository accountRepository;
	
	/**
	 * 创建全平台唯一的账号
	 * 生成规则:Md5(ch+sdkUserId+sdkUserName)
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
	
}
