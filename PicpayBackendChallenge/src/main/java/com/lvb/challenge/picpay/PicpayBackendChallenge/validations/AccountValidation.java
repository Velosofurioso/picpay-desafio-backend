package com.lvb.challenge.picpay.PicpayBackendChallenge.validations;

import com.lvb.challenge.picpay.PicpayBackendChallenge.repository.AccountRepository;
import com.lvb.challenge.picpay.PicpayBackendChallenge.repository.UserRepository;
import com.lvb.challenge.picpay.PicpayBackendChallenge.util.Constants;
import org.springframework.stereotype.Component;

@Component
public class AccountValidation {

    //TODO add documentation for this function
    public void newAccountValidation(final AccountRepository<?, ?> jpaRepository, final String email, final String accountUniqueCode) {
        emailAlreadyUsed(jpaRepository, email);
        existsFieldSaved(jpaRepository, accountUniqueCode);
    }

    //TODO add documentation for this function
    private void emailAlreadyUsed(final AccountRepository<?, ?> jpaRepository, final String email) {
        var account = jpaRepository.existsAccountByEmail(email);
        if (account) {
            throw new RuntimeException("Email já existe");
        }
    }

    //TODO add documentation for this function
    private void existsFieldSaved(final AccountRepository<?, ?> jpaRepository, final String accountUniqueCode) {

        var account = jpaRepository.existsFieldSaved(accountUniqueCode);

        if (account) {
            var uniqueCode = defineValidationField(jpaRepository);
            //TODO criar uma classe de excecao para esse erro
            throw new RuntimeException(uniqueCode.toUpperCase().concat(" já existe"));
        }
    }

    private String defineValidationField(final AccountRepository<?, ?> jpaRepository) {

        // If Repository is UserRepository means that is a creation of new user
        if (jpaRepository instanceof UserRepository) {
            return Constants.CPF;
        }
        // Otherwise means that is a creation of new seller
        return Constants.CNPJ;
    }
}
