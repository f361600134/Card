package com.fatiny.cardloginplus.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fatiny.cardloginplus.common.generator.SnowflakeGenerator;
import com.fatiny.cardloginplus.domain.entity.OrderInfo;
import com.fatiny.cardloginplus.domain.entity.OrderStatusEnum;
import com.fatiny.cardloginplus.module.base.PreOrderParam;
import com.fatiny.cardloginplus.repository.OrderInfoRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderInfoRepository orderRepository;
	
	@Autowired
	private SnowflakeGenerator generator;
	
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 创建订单
	 */
	public OrderInfo createOrder(PreOrderParam param) {
		OrderInfo orderInfo = new OrderInfo();
		try {
			long orderId = generator.nextId();
			orderInfo.setOrderId(orderId);
			orderInfo.setItemId(Integer.valueOf(param.getPayItemId()));
			orderInfo.setServerId(Integer.valueOf(param.getServerId()));
			orderInfo.setActorId(Integer.valueOf(param.getActorId()));
			orderInfo.setUserName(param.getUserName());
			orderInfo.setState(OrderStatusEnum.Order_Created_Paying);
			orderInfo.setAmount(Integer.valueOf(param.getAmount())); //充值金额
			//orderInfo.setFlags();
			orderRepository.save(orderInfo);
			return orderInfo;
		} catch (Exception e) {
			logger.error("创建订单失败, payItemId:{}, playerId:{}, e{}", param.getPayItemId(), param.getUserName(), e);
			orderInfo.setState(OrderStatusEnum.Order_Created_Failure);
		}
		return null;
	}
	
	/**
	 * 更新订单信息
	 *   
	 * @return void  
	 * @date 2019年4月29日下午8:24:59
	 */
	public void updateOrder(OrderInfo orderInfo) {
		orderRepository.save(orderInfo);
	}
	
	/**
	 * 获取到一个订单
	 * @param orderId
	 * @return  
	 * @return OrderInfo  
	 * @date 2019年4月29日下午6:05:13
	 */
	public OrderInfo getPayOrder(long orderId) {
		try {
			Optional<OrderInfo> optional = orderRepository.findById(orderId);
			OrderInfo orderInfo = optional.get();
			//OrderEntity payOrder = OrderEntity.convert(orderInfo);
			if (orderInfo == null) {
				logger.error("订单不存在：" + orderId);
				return null;
			}
			if (orderInfo.getState() == OrderStatusEnum.Order_Exchanged_Success.getStatus()) {
				logger.error("该订单已经处理过了！payOrder:{}" + orderInfo);
				return null;
			}
			return orderInfo;
		} catch (Exception e) {
			logger.error("获取订单时发生异常, 异常信息:", e);
			return null;
		}
	}
	
	@Async("taskAsyncPool")
	public void exchangeGameMoney(OrderInfo orderInfo) {
		// 兑换游戏币
		long startTime = System.currentTimeMillis();
		try {
//			boolean bool = exchargeProxy.doExcharge(orderInfo.getActorId(), orderInfo.getItemId(), orderInfo.getServerId());
//			if (bool) //充值成功
//				orderInfo.setState(OrderStatusEnum.Order_Exchanged_Success);
//			else {
//				orderInfo.setState(OrderStatusEnum.Order_Exchanged_Failed);
//			}
		} catch (Exception e) {
			logger.info("兑换游戏币发生错误, 请检查{}",e);
			orderInfo.setState(OrderStatusEnum.Order_Exchanged_Break);
		}
		orderRepository.save(orderInfo);
		logger.info("订单兑换花费时间:{}", (System.currentTimeMillis() - startTime));
	}
	

}
