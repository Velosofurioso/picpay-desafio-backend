package com.lvb.challenge.picpay.PicpayBackendChallenge.repository;

import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends AccountRepository<Seller, Long> {

    @Override
    @Query(value = "SELECT COUNT(e) > 0 FROM #{#entityName} e WHERE e.cnpj = :value")
    boolean existsFieldSaved(String value);
}
