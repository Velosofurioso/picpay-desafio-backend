package com.lvb.challenge.picpay.PicpayBackendChallenge.repository;

import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountBalanceRepository extends JpaRepository<AccountBalance, Long> {

    @Query(value = "SELECT ab.availableValue >= :transferQuantity FROM AccountBalance ab WHERE ab.account.id = :accountId")
    boolean accountHaveBalance(BigDecimal transferQuantity, Long accountId);
}
