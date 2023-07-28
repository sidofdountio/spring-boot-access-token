package com.sidof.security.repository;


import com.sidof.security.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author sidof
 * @Since 01/07/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
//    AppUser> findByEmail(String email);
    AppUser findByFirstName(String firstName);
}
