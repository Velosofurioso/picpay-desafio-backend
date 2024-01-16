package com.lvb.challenge.picpay.PicpayBackendChallenge.service.seller;

import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.seller.CreateSellerDto;
import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.seller.SellerDto;
import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.Seller;
import com.lvb.challenge.picpay.PicpayBackendChallenge.repository.SellerRepository;
import com.lvb.challenge.picpay.PicpayBackendChallenge.validations.AccountValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {


    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountValidation accountValidation;

    public Long createSeller(final CreateSellerDto createSellerDto) {

        // Business Validation
        accountValidation.newAccountValidation(sellerRepository, createSellerDto.getAccountAttributes().getEmail(), createSellerDto.getCnpj());

        //Seller Creation
        var mappedSeller = modelMapper.map(createSellerDto, Seller.class);
        var seller = sellerRepository.save(mappedSeller);

        return seller.getId();
    }

    public SellerDto getSellerById(final Long id) {

        //TODO verificar se vale a pena colocar um throw para usuario não encontrado

        var seller = findSellerById(id);

        if (seller == null) {
            return null;
        }

        return modelMapper.map(seller, SellerDto.class);
    }

    private Seller findSellerById(final Long id) {

        var seller = sellerRepository.findById(id);


        //TODO verificar se vale a pena colocar um throw para Vendedor não encontrado

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
        newSeller.setPassword(oldSeller.getPassword());
        newSeller.setCreatedAt(oldSeller.getCreatedAt());

        sellerRepository.save(newSeller);
    }

    public void deleteSeller(final Long id) {

        var seller = findSellerById(id);

        if (seller == null) {
            return;
        }

        sellerRepository.delete(seller);
    }
}
