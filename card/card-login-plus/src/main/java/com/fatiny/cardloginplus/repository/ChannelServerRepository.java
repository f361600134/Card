package com.fatiny.cardloginplus.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fatiny.cardloginplus.domain.entity.ChannelServer;

@Repository
@Qualifier(value = "channelServerRepository")
public interface ChannelServerRepository extends JpaRepository<ChannelServer, Integer> {
	
	@Query(value="select * from channel_server order by channel, server_id", nativeQuery=true)
	List<ChannelServer> selectByOrder();
	
}
