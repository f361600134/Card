package com.fatiny.cardloginplus.module.official;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatiny.cardloginplus.common.result.IResult;
import com.fatiny.cardloginplus.domain.entity.OrderInfo;
import com.fatiny.cardloginplus.module.core.AbstractController;
import com.fatiny.cardloginplus.module.core.annotation.ParameterMapping;
import com.fatiny.cardloginplus.module.core.base.IExchargeParam;
import com.fatiny.cardloginplus.module.core.support.AuthResult;
import com.fatiny.cardloginplus.module.core.support.ErrorCodeEnum;
import com.fatiny.cardloginplus.module.core.support.ExchargeResult;
import com.fatiny.cardloginplus.module.core.support.ResultCode;

/**
 * 官方登陆入口
 * @auth Jeremy
 * @date 2019年5月8日上午11:59:55
 * 
 * @test login
 * http://192.168.31.158:8998/official/login?userName=12321a&serverId=1&os=1&ch=99
 * http://192.168.31.158:8998/official/login?userName={}&serverId={}&os={}&ch={}
 * http://192.168.31.158:8998/official/command=1?userName=12321a&serverId=1&os=1&ch=99
 * 
 * @test createOrder http://192.168.31.159:8998/official/createOrder?serverIp=192.168.31.159&userName=aaa&userId=1&serverId=903&payItemId=1&amount=6&ch=98&port=8998
 * 
 * @test excharge http://192.168.31.159:8998/official/excharge?gameOrderId=96157182214430720
 */
@RestController
@RequestMapping(name="/official")
@ParameterMapping(loginParam = OfficialLoginParam.class, exchargeParam = OfficialExchargeParam.class)
public class OfficialController extends AbstractController{
	
	private static final Logger LOGGER = LogManager.getLogger(OfficialController.class);
	
	/**
	 * 新方式登录
	 * 官方没有sdk, 只对账户进行校验
	 */
	@Override
	public IResult sdkLogin(Object object) {
		OfficialLoginParam param = (OfficialLoginParam) object;
		String username = param.getInputUname();
		if(username.length()<5 || username.length()>60)
			return AuthResult.build(ErrorCodeEnum.ERROR_USERNAME_LEN);
//		else if(!StringUtils.is.isValidate(username))
//			return AuthResult.build(ErrorCodeEnum.ERROR_USERNAME_LEN);
		return AuthResult.build(ErrorCodeEnum.SUCCESS);
	}

	/**
	 * 官方无sdk,默认返回true用于测试
	 */
	@Override
	public IResult sdkExcharge(IExchargeParam object, OrderInfo orderInfo) {
		return ExchargeResult.build(ErrorCodeEnum.SUCCESS, ResultCode.CODE_SUCCESS);
	}

}
