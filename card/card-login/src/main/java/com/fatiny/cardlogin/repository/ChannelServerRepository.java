package com.fatiny.cardlogin.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fatiny.cardlogin.domain.entity.ChannelServer;

@Repository
@Qualifier(value = "channelServerRepository")
public interface ChannelServerRepository extends JpaRepository<ChannelServer, Integer> {
	
	@Query("select * from server_status order by channel, serverId")
	List<ChannelServer> selectByOrder();
	
}
