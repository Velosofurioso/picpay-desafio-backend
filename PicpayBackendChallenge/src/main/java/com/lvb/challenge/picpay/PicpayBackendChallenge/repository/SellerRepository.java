package com.lvb.challenge.picpay.PicpayBackendChallenge.repository;

import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {}
