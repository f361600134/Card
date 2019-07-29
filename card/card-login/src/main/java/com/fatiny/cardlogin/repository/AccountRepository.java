package com.fatiny.cardlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatiny.cardlogin.domain.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

}
