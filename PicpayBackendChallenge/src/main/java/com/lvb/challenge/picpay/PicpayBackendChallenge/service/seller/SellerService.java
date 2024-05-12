package com.lvb.challenge.picpay.PicpayBackendChallenge.service.seller;

import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.seller.CreateSellerDto;
import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.seller.SellerDto;
import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.Seller;
import com.lvb.challenge.picpay.PicpayBackendChallenge.repository.SellerRepository;
import com.lvb.challenge.picpay.PicpayBackendChallenge.service.accountBalance.AccountBalanceService;
import com.lvb.challenge.picpay.PicpayBackendChallenge.service.keycloak.KeyCloakService;
import com.lvb.challenge.picpay.PicpayBackendChallenge.validations.AccountValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private KeyCloakService keyCloakService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountValidation accountValidation;

    @Autowired
    private AccountBalanceService accountBalanceService;

    public Long createSeller(final CreateSellerDto createSellerDto) {

        // Business Validation
        accountValidation.newAccountValidation(createSellerDto, sellerRepository);

        // Create a new Seller in Keycloak
        final String keycloakSellerId = keyCloakService.createAccount(createSellerDto, createSellerDto.getPassword());

        //Create a new User in DB
        var mappedSeller = modelMapper.map(createSellerDto, Seller.class);
        mappedSeller.setUserIdKeycloak(keycloakSellerId);
        var seller = sellerRepository.save(mappedSeller);
        accountBalanceService.initBalance(mappedSeller);

        return seller.getId();
    }

    public SellerDto getSellerById(final Long id) {

        //TODO verificar se vale a pena colocar um throw para Seller não encontrado

        var seller = findSellerById(id);

        if (seller == null) {
            return null;
        }

        return modelMapper.map(seller, SellerDto.class);
    }

    private Seller findSellerById(final Long id) {

        var seller = sellerRepository.findById(id);


        //TODO verificar se vale a pena colocar um throw para seller não encontrado

        return seller.orElse(null);
    }

    public void updateSeller(final SellerDto sellerDto, final Long id) {

        var oldSeller = findSellerById(id);

        if (oldSeller == null) {
            return;
        }

        //TODO validar se vale a pena separar as o codigo abaixo para outra funcao
        var newSeller = modelMapper.map(sellerDto, Seller.class);
        newSeller.setId(oldSeller.getId());
        newSeller.setUserIdKeycloak(oldSeller.getUserIdKeycloak());
        newSeller.setCreatedAt(oldSeller.getCreatedAt());

        sellerRepository.save(newSeller);

        keyCloakService.updateAccount(sellerDto, newSeller.getUserIdKeycloak());
    }

    public void deleteSeller(final Long id) {

        var oldSeller = findSellerById(id);

        if (oldSeller == null) {
            return;
        }

        accountBalanceService.removeBalance(oldSeller);
        sellerRepository.delete(oldSeller);
        keyCloakService.removeAccount(oldSeller.getUserIdKeycloak());
    }
}
