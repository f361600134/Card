package com.fatiny.cardlogin.module.eagle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fatiny.cardlogin.util.MD5;

/**
 * 现在暂定一个游戏只有一个配置, 暂时不会存在一个渠道多配置的情况
 * 所以, 暂时硬编码起来, 功能做完后可以做优化, 从配置读取渠道参数
 * @auth Jeremy
 * @date 2019年4月28日下午2:24:18
 */
public class EagleConfig {
	
//	public static String EagAppId = "466";
//	public static String EagleAppKey = "3c6b70303cd44782acb3a463054b0218";
	public static String EagleAppsecret = "dc65585ffedc490eaad285145e24e669";
//	public static String EagleChannelid = "310402444";
//	public static String EagleBuglyAppid = "1b4ebb361b";
	
	/**
	 * Eagle Sdk
	 * 
	 * @request: POST, application/x-www-form-urlencoded
	 * @param: gameId, userId, token, ts, sign
	 * @result: state(string) 状态码，1:成功，其余失败<br>
	 * 			msg(string) 状态说明<br>
	 * 			data(string) 成功时返回<br>
	 * 			userId(string) 用户ID<br>
	 */
	public static String requestUrl = "http://eagle.hoho666.com/user/loginVerify";
	
//	 /**
//     * 将请求参数转化为Map
//     * 
//     * @param request
//     * @return
//     */
//    public static Map<String, String> getParameterMap(HttpServletRequest request) {
//        Map<String, String> param = new HashMap<>();
//        try {
//            Enumeration<String> em = request.getParameterNames();
//            while (em.hasMoreElements()) {
//                String key = em.nextElement();
//                param.put(key, request.getParameter(key));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return param;
//    }

    /**
     * 参数名按ASCII码从小到大排序，使用URL键值对的格式拼接成字符串A，在A最后拼接上&gameSecret得到字符串B，并对B进行MD5运算，再将得到的字符串转换为小写，得到签名
     * 
     * @param param
     *            签名参数
     * @param paramExcluded
     *            排除签名参数
     * @param gameSecret
     *            签名密钥
     * @return
     */
    public static String sign(Map<String, String> param, List<String> paramExcluded, String gameSecret) {
        String signStr = generateSortedParamString(param, "=", "&", paramExcluded, false, false);
        signStr += "&" + (gameSecret == null ? "" : gameSecret);
        return MD5.digest(signStr, true);
    }

    /**
     * 生成升序参数字符串
     * 
     * @param param
     *            参数
     * @param keyValConnectChar
     *            键值对连接符
     * @param paramSplitChar
     *            参数分隔符
     * @param paramExcluded
     *            参数排除
     * @param emptyExcluded
     *            空排除
     * @param zeroExcluded
     *            零排除
     * @return
     */
    public static String generateSortedParamString(Map<String, String> param, String keyValConnectChar,
            String paramSplitChar, List<String> paramExcluded, Boolean emptyExcluded, Boolean zeroExcluded) {
        if (param == null || param.isEmpty()) {
            return "";
        }
        if (keyValConnectChar == null) {
            keyValConnectChar = "";
        }
        if (paramSplitChar == null) {
            paramSplitChar = "";
        }
        if (paramExcluded == null) {
            paramExcluded = new ArrayList<>();
        }
        StringBuilder content = new StringBuilder();
        List<String> keys = new ArrayList<>(param.keySet());
        Collections.sort(keys);
        for (String key : keys) {

            if (StringUtils.isEmpty(key) || paramExcluded.contains(key)) {
                continue;
            }
            String value = param.get(key) == null ? "" : param.get(key);
            if (emptyExcluded && StringUtils.isEmpty(value)) {
                continue;
            }
            if (zeroExcluded && StringUtils.equals(value, "0")) {
                continue;
            }
            content.append(paramSplitChar).append(key).append(keyValConnectChar).append(value);
        }
        if (!StringUtils.isEmpty(paramSplitChar) && content.length() >= paramSplitChar.length()) {
            content.delete(0, paramSplitChar.length());
        }
        return content.toString();

    }
	
	/**
	 * 把对象转成Map
	 * @param obj
	 * @param extract
	 *            除去需要解析的字段
	 * @return
	 * @return Map<String,String>
	 * @date 2018年9月25日下午4:39:10
	 */
	public static Map<String, String> objToTreeMap(Object obj, String... extract) {
		Map<String, String> sortMap = new TreeMap<String, String>(new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				return str1.compareTo(str2);
			}
		});
		JSONObject jsonObject = (JSONObject) JSON.toJSON(obj);
		Set<Entry<String, Object>> entrySet = jsonObject.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			String key = entry.getKey();
			String value = String.valueOf(entry.getValue());
			if (StringUtils.containsAny(key, extract)) {
				continue;
			}
			sortMap.put(key, value);
		}
		return sortMap;
	}
    
    /**
	 * map中的数据组装后生成md5 签名
	 * 
	 * @style key1=value1&key2=value2
	 * @param map
	 * @return
	 */
	public static String getLocalSignByMap(Map<String, String> map, String... extras) {
		StringBuilder url = new StringBuilder();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			url.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		for (String extra : extras) {
			url.append(extra).append("&");
		}
		url.deleteCharAt(url.length() - 1);
		// md5加密
		String mySign = MD5.digest(url.toString(), true);
		return mySign;
	}

}
