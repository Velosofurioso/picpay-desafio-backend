package com.lvb.challenge.picpay.PicpayBackendChallenge.repository;

import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
