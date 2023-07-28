package com.sidof.security.repository;

import com.sidof.security.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author sidof
 * @Since 12/07/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {
    ConfirmationToken findByToken(String token);
}
