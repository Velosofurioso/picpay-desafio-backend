package com.lvb.challenge.picpay.PicpayBackendChallenge.controller;

import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.seller.CreateSellerDto;
import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.seller.SellerDto;
import com.lvb.challenge.picpay.PicpayBackendChallenge.service.seller.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/seller")
public class SellerController {

    //TODO criar uma estrutura com  account controller generico

    @Autowired
    private SellerService sellerService;

    @PostMapping
    @ResponseBody
    ResponseEntity<SellerDto> createSeller(@Validated @RequestBody CreateSellerDto createSellerDto) {

        // Data Validation
        //TODO add validation for data received

        // Password Validation

        //Service
        var createdSellerId = sellerService.createSeller(createSellerDto);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdSellerId).toUri();
        return ResponseEntity.created(uri).body(createSellerDto);
    }

    @PutMapping("/{id}")
    @ResponseBody
    ResponseEntity<SellerDto> updateSeller(@PathVariable(value = "id") long id, @Validated @RequestBody final SellerDto sellerDto) {

        // Data Validation
        //TODO add validation for data received

        //Service
        sellerService.updateSeller(sellerDto, id);

        return ResponseEntity.ok(sellerDto);
    }

    @GetMapping("/{id}")
    ResponseEntity<SellerDto> getSeller(@PathVariable(value = "id") long id) {
        var seller = sellerService.getSellerById(id);

        if (seller == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(seller);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> removeSeller(@PathVariable(value = "id") long id) {

        // Data Validation
        //TODO add validation for data received

        //Service
        sellerService.deleteSeller(id);

        return ResponseEntity.ok().build();
    }
}
