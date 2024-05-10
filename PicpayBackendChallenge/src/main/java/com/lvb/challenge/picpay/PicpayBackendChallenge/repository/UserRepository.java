package com.lvb.challenge.picpay.PicpayBackendChallenge.repository;

import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AccountRepository<User> { }
