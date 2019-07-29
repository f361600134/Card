package com.fatiny.cardlogin.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatiny.cardlogin.domain.entity.OrderInfo;

@Repository
@Qualifier(value = "orderRepository")
public interface OrderRepository extends JpaRepository<OrderInfo, Long> {

}
