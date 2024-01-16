package com.lvb.challenge.picpay.PicpayBackendChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AccountRepository <T, R> extends JpaRepository<T, R> {

    @Query(value = "SELECT COUNT(e) > 0 FROM #{#entityName} e WHERE e.accountAttributes.email = :email")
    boolean existsAccountByEmail(String email);

    boolean existsFieldSaved(String value);
}
