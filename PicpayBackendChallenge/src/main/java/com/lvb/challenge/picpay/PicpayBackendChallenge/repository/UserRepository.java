package com.lvb.challenge.picpay.PicpayBackendChallenge.repository;

import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT e  FROM User e WHERE e.userDocument = :value")
    User existsFieldSaved(String value);

    @Query(value = "SELECT COUNT(e) > 0 FROM #{#entityName} e WHERE e.email = :email")
    boolean existsAccountByEmail(String email);
}
