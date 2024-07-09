package com.lvb.challenge.picpay.PicpayBackendChallenge.validations;

import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.account.transfer.MakeTransferDTO;
import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.account.transfer.ValidateTransferConfirmationDTO;
import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.enums.UserType;
import com.lvb.challenge.picpay.PicpayBackendChallenge.repository.AccountBalanceRepository;
import com.lvb.challenge.picpay.PicpayBackendChallenge.util.AccountUtils;
import com.lvb.challenge.picpay.PicpayBackendChallenge.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class TransfersValidation {

    @Value("${picpay.transfer_confirmation_mock.url}")
    public String mockUrl;

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;

    @Autowired
    private AccountUtils accountUtils;

    @Autowired
    private RestTemplate restTemplate;

    public void validateTransfers(final MakeTransferDTO makeTransferDTO) {

        // valida se os usuarios existem
        this.validateUsersExist(makeTransferDTO);

        // valida se quem está enviando o dinheiro é do tipo usuario
        this.validateUserTypeTransfer(makeTransferDTO);

        // Valida se quem esta enviando o dinheiro tem o valor disponivel no saldo da conta
        this.validateQuantityTransfer(makeTransferDTO);

        // Valida se a transacao foi aprovada, usando o mock do picpay
        this.validateTransfersConfirmation();
    }

    private void validateTransfersConfirmation() {
        try {
            final ValidateTransferConfirmationDTO confirmationDTO =
                    restTemplate.getForObject(mockUrl, ValidateTransferConfirmationDTO.class);

            if (confirmationDTO == null)
                throw new RuntimeException("An unexpected error occurred, try again later");

            else if (confirmationDTO.getData() == null)
                throw new RuntimeException("Transaction not authorized, try again later");

            else if (!confirmationDTO.getData().isAuthorization())
                throw new RuntimeException("Transaction not authorized, try again later");

        }
        catch (HttpClientErrorException.Forbidden e) {
            throw new RuntimeException("Transaction not authorized, try again later");
        }
        catch (RestClientException e) {
            throw new RuntimeException("An unexpected error occurred, try again later");
        }
    }

    private void validateUsersExist(MakeTransferDTO makeTransferDTO) {
        // Validate if payer exist
        accountUtils.retrieveUserTypeAccount(makeTransferDTO.getPayer());
        // Validate if payee exist
        accountUtils.retrieveUserTypeAccount(makeTransferDTO.getPayee());
    }

    private void validateQuantityTransfer(MakeTransferDTO makeTransferDTO) {
        if (Utils.bigDecimalEquals(BigDecimal.ZERO, makeTransferDTO.getTransferValue()))
            throw new RuntimeException("Cannot transfer value 0");

        else if (!accountBalanceRepository.accountHaveBalance(makeTransferDTO.getTransferValue(), makeTransferDTO.getPayer()))
            // TODO Add Exception to user cannot do transfers because doesn't have balance enough
            throw new RuntimeException("User doesn't have this quantity available to transfer");
    }

    private void validateUserTypeTransfer(MakeTransferDTO makeTransferDTO) {

        //TODO tratar mensagem de usuario não existe para deixar mais claro que é o usuario
        final UserType userType = accountUtils.retrieveUserTypeAccount(makeTransferDTO.getPayer());

        if (!UserType.USER.equals(userType)) {
            // TODO Add Exception to user cannot do transfers because of account type
            throw new RuntimeException("Seller Account can't make transfer");
        }
    }
}
