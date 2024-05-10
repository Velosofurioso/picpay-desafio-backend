package com.lvb.challenge.picpay.PicpayBackendChallenge.validations;

import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.account.AccountDto;
import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.enums.UserType;
import com.lvb.challenge.picpay.PicpayBackendChallenge.repository.AccountRepository;
import com.lvb.challenge.picpay.PicpayBackendChallenge.util.Constants;
import org.springframework.stereotype.Component;

@Component
public class AccountValidation {


    //TODO add documentation for this function
    public void newAccountValidation(final AccountDto userDto, final AccountRepository accountRepository) {
        emailAlreadyUsed(userDto.getEmail(), accountRepository);
        existsFieldSaved(userDto.getUserDocument(), accountRepository);
    }

    //TODO add documentation for this function
    private void emailAlreadyUsed(final String email, final AccountRepository accountRepository) {
        var account = accountRepository.existsAccountByEmail(email);
        if (account) {
            throw new RuntimeException("Email já existe");
        }
    }

    //TODO add documentation for this function
    private void existsFieldSaved(final String accountUniqueCode, final AccountRepository accountRepository) {

        var account = accountRepository.existsFieldSaved(accountUniqueCode);

        if (account != null) {
            var uniqueCode = defineValidationField(account.getUserType());
            //TODO criar uma classe de excecao para esse erro
            throw new RuntimeException(uniqueCode.toUpperCase().concat(" já existe"));
        }
    }


    //TODO change logic to define if is CPF or CNPJ
    private String defineValidationField(final UserType userType) {

        // If userType is User means that is a creation of new user
        if (UserType.USER.equals(userType)) {
            return Constants.CPF;
        }
        // Otherwise means that is a creation of new seller
        return Constants.CNPJ;
    }

}
