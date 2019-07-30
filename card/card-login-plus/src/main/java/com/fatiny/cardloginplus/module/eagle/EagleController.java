package com.fatiny.cardloginplus.module.eagle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fatiny.cardloginplus.domain.entity.OrderInfo;
import com.fatiny.cardloginplus.module.core.AbstractController;
import com.fatiny.cardloginplus.module.core.annotation.ParameterMapping;
import com.fatiny.cardloginplus.module.core.base.IExchargeParam;
import com.fatiny.cardloginplus.module.core.result.ErrorCodeEnum;
import com.fatiny.cardloginplus.module.core.result.IResult;
import com.fatiny.cardloginplus.module.core.result.ResultCode;
import com.fatiny.cardloginplus.module.core.support.AuthResult;
import com.fatiny.cardloginplus.module.core.support.ExchargeResult;
import com.fatiny.cardloginplus.util.HttpClientUtil;
import com.google.common.collect.Maps;

/**
 * 老鹰EagleSdk
 * @auth Jeremy
 * @date 2019年5月8日下午4:27:00
 * @test 登录:  http://192.168.31.227:8998/eagle/login?channelUId=1&serverId=1&channelUname=1&os=ios&gameId=1&ch=99&token=1
 * @test 创建订单: http://192.168.31.227:8998/eagle/createOrder?ch=99&userId=1&userName=1&payItemId=1&amount=6&serverId=1&serverIp=192.168.31.227&port=8998
 * 
 */
@RestController
@RequestMapping("/eagle")
@ParameterMapping(loginParam = EagleLoginParam.class, exchargeParam = EagleExchargeParam.class)
public class EagleController extends AbstractController{
	
	private static final Logger LOGGER = LogManager.getLogger(EagleController.class);
	
	/**
	 * 1.使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串A，
	 * 2.在A最后拼接上&gameSecret（游戏服务端签名密钥，直接拼接不需要键值对格式）得到字符串B
	 * 3.并对B进行MD5运算，再将得到的字符串转换为小写，得到签名
	 * 
	 */
	@Override
	public IResult sdkLogin(Object object) {
		EagleLoginParam param = (EagleLoginParam) object;
		String currentTime = String.valueOf(System.currentTimeMillis());
//		Map<String, String> objMap = Maps.newHashMap();
//		objMap.put("gameId", param.getGameId());
//		objMap.put("userId", param.getChannelUId());
//		objMap.put("token", param.getToken());
//		objMap.put("ts", currentTime);
//		String localSign1 = EagleConfig.sign(objMap, null, EagleConfig.EagleAppsecret);
		
		List<BasicNameValuePair> list = new ArrayList<>();
		list.add(new BasicNameValuePair("gameId", param.getGameId()));//游戏ID，sdk方提供
		list.add(new BasicNameValuePair("userId", param.getChannelUId()));//用户ID，SDK登录时返回
		list.add(new BasicNameValuePair("token", param.getToken())); //用户令牌，SDK登录时返回
		list.add(new BasicNameValuePair("ts", currentTime)); //当前时间戳(13位，毫秒级)
		String localSign = localSign(list);
		list.add(new BasicNameValuePair("sign", localSign)); //签名
		try {
			String ret = HttpClientUtil.doPost(EagleConfig.requestUrl, list);
			if (StringUtils.isBlank(ret)) {
				LOGGER.info("login error, ret is null or empty");
				return AuthResult.build(ErrorCodeEnum.ERROR_SIGN_NULL);
			}
			@SuppressWarnings("unchecked")
			Map<String, String> retMap = JSONObject.parseObject(ret, Map.class);
			String state = String.valueOf(retMap.get("state"));
			//状态码，1:成功，其余失败
			if (state != null && state.equals(ResultCode.CODE_1))
				return AuthResult.build(ErrorCodeEnum.SUCCESS);
			else {
				LOGGER.info("login error, ret:{}, localSign:{}, localSign1:{}", ret, localSign);
				return AuthResult.build(ErrorCodeEnum.ERROR_SIGN_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("login error, localSign:{}", localSign);
			return AuthResult.build(ErrorCodeEnum.ERROR_SIGN_NULL);
		}
	}
	
	private String localSign(List<BasicNameValuePair> list) {
		Map<String, String> objMap = Maps.newTreeMap();
		for (NameValuePair nameValuePair : list) {
			objMap.put(nameValuePair.getName(), nameValuePair.getValue());
		}
		String localSign = EagleConfig.getLocalSignByMap(objMap, EagleConfig.EagleAppsecret);
		return localSign;
	}
	
	@Override
	public IResult sdkExcharge(IExchargeParam param, OrderInfo orderInfo) {
		IResult result = null;
		EagleExchargeParam exchargeParam = (EagleExchargeParam) param;
		Map<String, String> objMap = EagleConfig.objToTreeMap(exchargeParam, "sign");
		String localSign = EagleConfig.getLocalSignByMap(objMap, EagleConfig.EagleAppsecret);
		// 验签
		if (StringUtils.equals(localSign, exchargeParam.getSign())) {
			result = ExchargeResult.build(ErrorCodeEnum.SUCCESS, ResultCode.CODE_SUCCESS);
		} else {
			result = ExchargeResult.build(ErrorCodeEnum.IllEGAL_PARAMS, ResultCode.CODE_FAILD);
			LOGGER.info("验签失败, exchargeParam:{}, localSign:{}", exchargeParam, localSign);
		}
		return result;
	}
	
}
