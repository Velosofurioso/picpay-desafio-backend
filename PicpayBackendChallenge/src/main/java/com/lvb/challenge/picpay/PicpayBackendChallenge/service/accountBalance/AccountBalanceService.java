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

    public void increaseBalance(final AccountBase accountBase, final BigDecimal increaseQty) {
        if (accountBase == null) return;
        else if (accountBase.getAccountBalance() == null)  return;

        final BigDecimal newBalanceQty = accountBase.getAccountBalance().getAvailableValue().add(increaseQty);
        accountBase.getAccountBalance().setAvailableValue(newBalanceQty);

        accountBalanceRepository.save(accountBase.getAccountBalance());

        //TODO save the balance movements in another table
    }

    public void decreaseBalance(final AccountBase accountBase, final BigDecimal increaseQty) {
        if (accountBase == null) return;
        else if (accountBase.getAccountBalance() == null)  return;

        final BigDecimal newBalanceQty = accountBase.getAccountBalance().getAvailableValue().subtract(increaseQty);
        accountBase.getAccountBalance().setAvailableValue(newBalanceQty);

        accountBalanceRepository.save(accountBase.getAccountBalance());

        //TODO save the balance movements in another table
    }
}
