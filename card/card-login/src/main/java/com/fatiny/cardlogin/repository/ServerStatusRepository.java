package com.fatiny.cardlogin.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatiny.cardlogin.domain.entity.ServerStatus;

@Repository
@Qualifier(value = "serverStatusRepository")
public interface ServerStatusRepository extends JpaRepository<ServerStatus, Integer> {
	
}
