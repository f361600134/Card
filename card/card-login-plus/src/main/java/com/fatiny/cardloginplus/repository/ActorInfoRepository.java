package com.fatiny.cardloginplus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fatiny.cardloginplus.domain.entity.ActorInfo;
import com.fatiny.cardloginplus.domain.entity.ChannelServer;

@Repository
public interface ActorInfoRepository extends JpaRepository<ActorInfo, String> {
	
	//@Query(value="select * from actor_info where user_name = :username", nativeQuery=true)
	//List<ActorInfo> selectAllById(@Param("username") String username);
	@Query(value="select * from actor_info where user_name = ?", nativeQuery=true)
	List<ActorInfo> selectAllById(String username);
	
}
