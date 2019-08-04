package com.fatiny.game.util;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fatiny.game.util.log.PlayerLog;

public class BadWordFilter {
	
	public static final Logger logger = LoggerFactory.getLogger(BadWordFilter.class);
	
	public static final int MinMatchType = 1;      //最小匹配规则
	public static final int MaxMatchType = 2;      //最大匹配规则
	
	@SuppressWarnings("rawtypes")
	private static HashMap sensitiveWordMap = new HashMap();
	
	/**
	 * 对名字进行判断,是否存在敏感字
	 * @param txt
	 * @return true:存在敏感字, false:不存在
	 */
	public static boolean isNameInvalid(String txt){
//		LOGGER.info("doNameBooleanFilter  {}", txt);
		int txtLen = txt.length();
		txt = txt.replaceAll(" ", "");
		if(txt.length()!= txtLen)
			return true;
		else if(txt.indexOf("@")>=0 || txt.indexOf("%")>=0 || txt.indexOf("&")>=0
				 || txt.indexOf(",")>=0 || txt.indexOf("_")>=0)
			return true;
		try
		{
			URI.create(txt);
		}
		catch(Exception ex)
		{
			return true;
		}
		boolean sensitive = hasBadWord(txt, MinMatchType);
		return sensitive;
	}
	
	/**
	 * 获取名字中的敏感词
	 * @param txt 文字
	 * @param matchType 匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
	 * @return
	 */
	private static boolean hasBadWord(String txt , int matchType){
		for(int i = 0 ; i < txt.length(); i++){
			int length = checkName(txt, i, matchType);    //判断是否包含敏感字符
			if(length > 0){    //存在,加入list中
				logger.info("hasBadWord  {}", txt.substring(i, i+length));
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 用于检测名字是否存在敏感字
	 * @param txt
	 * @param beginIndex
	 * @param matchType
	 * @return 如果存在，则返回敏感词字符的长度，不存在返回0
	 */
	@SuppressWarnings({ "rawtypes"})
	private static int checkName(String txt, int beginIndex, int matchType){
		boolean  flag = false;    //敏感词结束标识位：用于敏感词只有1位的情况
		int matchFlag = 0;     //匹配标识数默认为0
		char word = 0;
		Map nowMap = sensitiveWordMap;
		for(int i = beginIndex; i < txt.length() ; i++){
			word = txt.charAt(i);
			if (!nowMap.containsKey(word)) {
				//如果有字符.则找不到map,跳过这次执行
				matchFlag++;
				continue;
			}
			nowMap = (Map) nowMap.get(word);     //获取指定key
			if(nowMap != null){     //存在，则判断是否为最后一个
				matchFlag++;     //找到相应key，匹配标识+1 
				if("1".equals(nowMap.get("isEnd"))){       //如果为最后一个匹配规则,结束循环，返回匹配标识数
					flag = true;       //结束标志位为true   
					if(MinMatchType == matchType){    //最小规则，直接返回,最大规则还需继续查找
						break;
					}
				}
			}
		}
		if(matchFlag < 2 || !flag){        //长度必须大于等于1，为词 
			matchFlag = 0;
		}
		return matchFlag;
	}
	
	/**
	 * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：<br>
	 * 大 = {
	 *      isEnd = 0
	 *      蠢= {<br>
	 *      	 isEnd = 1
	 *           货 = {
	 *           	isEnd = 1
	 *           	}
	 *           }
	 *      二  = {
	 *           isEnd = 0
	 *           货 = {
	 *           	isEnd = 1
	 *           	}
	 *           }
	 *           
	 *      }
	 * @param keyWordSet  敏感词库
	 * @version 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void addSensitiveWord(Set<String> keyWordSet) {
		sensitiveWordMap = new HashMap(keyWordSet.size());     //初始化敏感词容器，减少扩容操作
		String key = null;  
		Map nowMap = null;
		Map<String, String> newWorMap = null;
		//迭代keyWordSet
		Iterator<String> iterator = keyWordSet.iterator();
		while(iterator.hasNext()){
			key = iterator.next();    //关键字
			nowMap = sensitiveWordMap;
			for(int i = 0 ; i < key.length() ; i++){
				char keyChar = key.charAt(i);       //转换成char型
				Object wordMap = nowMap.get(keyChar);       //获取
				
				if(wordMap != null){        //如果存在该key，直接赋值
					nowMap = (Map) wordMap;
				}
				else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
					newWorMap = new HashMap<String,String>();
					newWorMap.put("isEnd", "0");     //不是最后一个
					nowMap.put(keyChar, newWorMap);
					nowMap = newWorMap;
				}
				
				if(i == key.length() - 1){
					nowMap.put("isEnd", "1");    //最后一个
				}
			}
		}
	}
	
	
	/////////////////////////// 以下是聊天相关 //////////////
	/**
	 * 获取替换字符串
	 * @param replaceChar
	 * @param length
	 * @return 获取到的敏感字符
	 */
	private static String getReplaceChars(String replaceChar,int length){
		String resultReplace = replaceChar;
		for(int i = 1 ; i < length ; i++){
			resultReplace += replaceChar;
		}
		return resultReplace;
	}
	
	/**
	 * 替换敏感字字符
	 * @param txt
	 * @param matchType
	 * @param replaceChar 替换字符，默认*
	 */
	public static String doChatFilter(String txt){
		String resultTxt = doChatFilter(txt, 1, "*");
		return resultTxt;
	}
	
	/**
	 * 替换敏感字字符
	 * @param txt
	 * @param matchType
	 * @param replaceChar 替换字符，默认*
	 */
	public static boolean doChatBooleanFilter(String txt){
		Set<String> set = getBadChatWord(txt, 1);     //获取所有的敏感词
		if (set.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 替换敏感字字符
	 * @param txt
	 * @param matchType
	 * @param replaceChar 替换字符，默认*
	 */
	public static String doChatFilter(String txt, int matchType, String replaceChar){
		String resultTxt = txt;
		Set<String> set = getBadChatWord(txt, matchType);     //获取所有的敏感词
		Iterator<String> iterator = set.iterator();
		String word = null;
		String replaceString = null;
		while (iterator.hasNext()) {
			word = iterator.next();
			replaceString = getReplaceChars(replaceChar, word.length());
			resultTxt = resultTxt.replaceAll(word, replaceString);
		}
		return resultTxt;
	}
	
	/**
	 * 获取名字中的敏感词
	 * @param txt 文字
	 * @param matchType 匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
	 * @return
	 */
	private static Set<String> getBadChatWord(String txt , int matchType){
		Set<String> sensitiveWordSet = new HashSet<String>();
		for(int i = 0 ; i < txt.length() ; i++){
			int length = checkChat(txt, i, matchType);    //判断是否包含敏感字符
			if(length > 0){    //存在,加入list中
				sensitiveWordSet.add(txt.substring(i, i+length));
				i = i + length - 1;    //减1的原因，是因为for会自增
			}
		}
		return sensitiveWordSet;
	}
	
	/**
	 * 检查聊天中是否存在敏感字
	 * @param txt
	 * @param beginIndex
	 * @param matchType
	 * @return 如果存在，则返回敏感词字符的长度，不存在返回0
	 */
	@SuppressWarnings({ "rawtypes"})
	private static int checkChat(String txt,int beginIndex,int matchType){
		boolean  flag = false;    //敏感词结束标识位：用于敏感词只有1位的情况
		int matchFlag = 0;     //匹配标识数默认为0
		char word = 0;
		Map nowMap = sensitiveWordMap;
		for(int i = beginIndex; i < txt.length() ; i++){
			word = txt.charAt(i);
			nowMap = (Map) nowMap.get(word);     //获取指定key
			if(nowMap != null){     //存在，则判断是否为最后一个
				matchFlag++;     //找到相应key，匹配标识+1 
				if("1".equals(nowMap.get("isEnd"))){       //如果为最后一个匹配规则,结束循环，返回匹配标识数
					flag = true;       //结束标志位为true   
					if(MinMatchType == matchType){    //最小规则，直接返回,最大规则还需继续查找
						break;
					}
				}
			}
			else{     //不存在，直接返回
				break;
			}
		}
		if(matchFlag < 2 || !flag){        //长度必须大于等于1，为词 
			matchFlag = 0;
		}
		return matchFlag;
	}

}
