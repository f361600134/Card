package com.fatiny.cardloginplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatiny.cardloginplus.domain.entity.ActorInfo;

@Repository
public interface ActorInfoRepository extends JpaRepository<ActorInfo, String> {

}
