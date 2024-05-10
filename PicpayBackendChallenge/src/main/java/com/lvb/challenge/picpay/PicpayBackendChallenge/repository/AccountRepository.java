package com.lvb.challenge.picpay.PicpayBackendChallenge.repository;

import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.AccountBase;
import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface AccountRepository <T extends AccountBase> extends JpaRepository<T, Long> {

    @Query(value = "SELECT e FROM #{#entityName} e WHERE e.userDocument = :value")
    T existsFieldSaved(String value);

    @Query(value = "SELECT COUNT(e) > 0 FROM #{#entityName} e WHERE e.email = :email")
    boolean existsAccountByEmail(String email);
}
