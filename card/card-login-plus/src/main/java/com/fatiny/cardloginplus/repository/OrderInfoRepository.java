package com.fatiny.cardloginplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatiny.cardloginplus.domain.entity.OrderInfo;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {

}
