package com.lvb.challenge.picpay.PicpayBackendChallenge.controller;

import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.account.MakeTransferDTO;
import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.account.ValidateCodeDTO;
import com.lvb.challenge.picpay.PicpayBackendChallenge.enums.ValidationType;
import com.lvb.challenge.picpay.PicpayBackendChallenge.service.account.AccountService;
import com.lvb.challenge.picpay.PicpayBackendChallenge.validations.TransfersValidation;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


import java.io.IOException;

@RestController
@RequestMapping("/account")
public class AccountController {

    //TODO criar uma estrutura com  account controller generico

    @Autowired
    private TransfersValidation transfersValidation;

    @Autowired
    private AccountService accountService;

    @PostMapping("/transfer")
    @ResponseBody
    ResponseEntity<MakeTransferDTO> makeTransfers(@Validated @RequestBody MakeTransferDTO makeTransferDTO) {

        // Data Validation
        transfersValidation.validateTransfers(makeTransferDTO);

        //Service
        //var createdSellerId = sellerService.createSeller(createSellerDto);

       // final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdSellerId).toUri();
       // return ResponseEntity.created(uri).body(makeTransferDTO);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{id}/sendEmailCode")
    @ResponseBody
    ResponseEntity<MakeTransferDTO> sendEmailCode(@PathVariable(value = "id") long id) throws MessagingException, IOException {

        //Service
        accountService.sendValidationCode(id, ValidationType.EMAIL);

        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{id}/validateEmail")
    @ResponseBody
    ResponseEntity<MakeTransferDTO> validateEmailCode(@PathVariable(value = "id") long id, @RequestBody ValidateCodeDTO validateCodeDTO) {

        //Service
        accountService.validateCode(id, validateCodeDTO.getCode(), ValidationType.EMAIL);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/validatePhone")
    @ResponseBody
    ResponseEntity<MakeTransferDTO> validatePhone(@PathVariable(value = "id") long id, @RequestBody ValidateCodeDTO validateCodeDTO) {

        //Service
        accountService.validateCode(id, validateCodeDTO.getCode(), ValidationType.PHONE);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/sendPhoneCode")
    @ResponseBody
    ResponseEntity<MakeTransferDTO> sendPhoneCode(@PathVariable(value = "id") long id) throws MessagingException, IOException {

        //Service
        accountService.sendValidationCode(id, ValidationType.PHONE);

        return ResponseEntity.noContent().build();
    }

}
