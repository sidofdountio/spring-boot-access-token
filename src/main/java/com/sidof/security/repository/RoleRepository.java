package com.sidof.security.repository;

import com.sidof.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author sidof
 * @Since 25/07/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
}
