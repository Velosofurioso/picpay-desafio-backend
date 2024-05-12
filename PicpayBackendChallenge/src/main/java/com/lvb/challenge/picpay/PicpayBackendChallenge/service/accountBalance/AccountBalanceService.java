package com.lvb.challenge.picpay.PicpayBackendChallenge.service.accountBalance;

import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.AccountBalance;
import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.AccountBase;
import com.lvb.challenge.picpay.PicpayBackendChallenge.repository.AccountBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountBalanceService {

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;


    public void initBalance(final AccountBase accountBase) {

        if (accountBase == null) return;
        else if (accountBase.getId() == null)  return;

        final AccountBalance balance = AccountBalance.builder()
                .account(accountBase)
                .availableValue(BigDecimal.ZERO)
                .build();

        accountBalanceRepository.save(balance);
    }

    public void removeBalance(final AccountBase accountBase) {
        if (accountBase == null) return;
        else if (accountBase.getAccountBalance() == null)  return;

        accountBalanceRepository.delete(accountBase.getAccountBalance());
    }

    //public void
}
