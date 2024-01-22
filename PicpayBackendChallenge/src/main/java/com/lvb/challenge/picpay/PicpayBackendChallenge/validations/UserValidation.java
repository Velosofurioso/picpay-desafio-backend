package com.lvb.challenge.picpay.PicpayBackendChallenge.validations;

import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.user.UserDto;
import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.User;
import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.enums.UserType;
import com.lvb.challenge.picpay.PicpayBackendChallenge.repository.UserRepository;
import com.lvb.challenge.picpay.PicpayBackendChallenge.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidation {

    @Autowired
    private UserRepository userRepository;


    //TODO add documentation for this function
    public void newAccountValidation(final UserDto userDto) {
        emailAlreadyUsed(userDto.getEmail());
        existsFieldSaved(userDto.getUserDocument());
    }

    //TODO add documentation for this function
    private void emailAlreadyUsed(final String email) {
        var account = userRepository.existsAccountByEmail(email);
        if (account) {
            throw new RuntimeException("Email já existe");
        }
    }

    //TODO add documentation for this function
    private void existsFieldSaved(final String accountUniqueCode) {

        var account = userRepository.existsFieldSaved(accountUniqueCode);

        if (account != null) {
            var uniqueCode = defineValidationField(account);
            //TODO criar uma classe de excecao para esse erro
            throw new RuntimeException(uniqueCode.toUpperCase().concat(" já existe"));
        }
    }

    //TODO change logic to define if is CPF or CNPJ
    private String defineValidationField(final User user) {

        // If userType is User means that is a creation of new user
        if (UserType.USER.equals(user.getUserType())) {
            return Constants.CPF;
        }
        // Otherwise means that is a creation of new seller
        return Constants.CNPJ;
    }
}
