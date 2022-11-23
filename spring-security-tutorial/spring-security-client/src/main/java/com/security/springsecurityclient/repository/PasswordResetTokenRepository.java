package com.security.springsecurityclient.repository;


import com.security.springsecurityclient.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends     JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);
}
