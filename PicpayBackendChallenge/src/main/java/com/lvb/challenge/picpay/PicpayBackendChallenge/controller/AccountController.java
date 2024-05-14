package com.lvb.challenge.picpay.PicpayBackendChallenge.controller;

import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.account.MakeTransferDTO;
import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.seller.CreateSellerDto;
import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.seller.SellerDto;
import com.lvb.challenge.picpay.PicpayBackendChallenge.service.seller.SellerService;
import com.lvb.challenge.picpay.PicpayBackendChallenge.validations.TransfersValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/account")
public class AccountController {

    //TODO criar uma estrutura com  account controller generico

    @Autowired
    private TransfersValidation transfersValidation;

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

}
