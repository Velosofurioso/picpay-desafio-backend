package com.lvb.challenge.picpay.PicpayBackendChallenge.service.transfer;

import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.account.transfer.MakeTransferDTO;
import com.lvb.challenge.picpay.PicpayBackendChallenge.service.account.AccountService;
import com.lvb.challenge.picpay.PicpayBackendChallenge.service.accountBalance.AccountBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountBalanceService accountBalanceService;

    public void doTransfer(final MakeTransferDTO makeTransferDTO) {

        // Decrease balance quantity fo payer
        var payer = accountService.findAccountById(makeTransferDTO.getPayer());
        accountBalanceService.decreaseBalance(payer, makeTransferDTO.getTransferValue());

        // Increase balance quantity fo payee
        var payee = accountService.findAccountById(makeTransferDTO.getPayee());
        accountBalanceService.increaseBalance(payee, makeTransferDTO.getTransferValue());


        //TODO generate receipt from transfer to payer

        //TODO generate receipt from transfer to payee

    }

}
