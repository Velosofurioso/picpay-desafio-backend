package com.lvb.challenge.picpay.PicpayBackendChallenge.validations;

import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.account.MakeTransferDTO;
import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.enums.UserType;
import com.lvb.challenge.picpay.PicpayBackendChallenge.repository.AccountBalanceRepository;
import com.lvb.challenge.picpay.PicpayBackendChallenge.util.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransfersValidation {

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;

    @Autowired
    private AccountUtils accountUtils;

    public void validateTransfers(final MakeTransferDTO makeTransferDTO) {

        //valida se os usuarios existem
        this.validateUsersExist(makeTransferDTO);

        //valida se quem está enviando o dinheiro é do tipo usuario
        this.validateUserTypeTransfer(makeTransferDTO);

        //Valida se quem esta enviando o dinheiro tem o valor disponivel no saldo da conta
        this.validateQuantityTransfer(makeTransferDTO);
    }

    private void validateUsersExist(MakeTransferDTO makeTransferDTO) {
        accountUtils.retrieveUserTypeAccount(makeTransferDTO.getPayer());
        accountUtils.retrieveUserTypeAccount(makeTransferDTO.getPayee());
    }

    private void validateQuantityTransfer(MakeTransferDTO makeTransferDTO) {
        if (!accountBalanceRepository.accountHaveBalance(makeTransferDTO.getTransferValue(), makeTransferDTO.getPayer())) {
            // TODO Add Exception to user cannot do transfers because doesn't have balance enough
        }
    }

    private void validateUserTypeTransfer(MakeTransferDTO makeTransferDTO) {

        //TODO tratar mensagem de usuario não existe para deixar mais claro que é o usuario
        final UserType userType = accountUtils.retrieveUserTypeAccount(makeTransferDTO.getPayer());

        if (!UserType.USER.equals(userType)) {
            // TODO Add Exception to user cannot do transfers because of account type
        }
    }
}
