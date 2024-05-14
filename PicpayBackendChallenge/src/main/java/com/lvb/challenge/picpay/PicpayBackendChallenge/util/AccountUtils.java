package com.lvb.challenge.picpay.PicpayBackendChallenge.util;


import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.AccountBase;
import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.enums.UserType;
import com.lvb.challenge.picpay.PicpayBackendChallenge.repository.AccountBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountUtils {

    @Autowired
    private AccountBaseRepository accountBaseRepository;

    public UserType retrieveUserTypeAccount(final Long accountId) {
        final Optional<AccountBase> accountBase = accountBaseRepository.findById(accountId);
        if (accountBase.isEmpty()) {
            //TODO refactor this exception
            throw new RuntimeException("User with id: " + accountId  + " Not Exist");
        }
        return accountBase.get().getUserType();
    }

    public UserType retrieveUserTypeAccountOrNull(final Long accountId) {
        final Optional<AccountBase> accountBase = accountBaseRepository.findById(accountId);
        return accountBase.map(AccountBase::getUserType).orElse(null);
    }

    public void accountExists(final Long accountId) {

    }
}
