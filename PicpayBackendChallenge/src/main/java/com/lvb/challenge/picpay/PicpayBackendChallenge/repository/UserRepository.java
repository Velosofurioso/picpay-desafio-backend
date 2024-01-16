package com.lvb.challenge.picpay.PicpayBackendChallenge.repository;

import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AccountRepository<User, Long> {

    @Override
    @Query(value = "SELECT COUNT(e) > 0 FROM #{#entityName} e WHERE e.cpf = :value")
    boolean existsFieldSaved(String value);
}
