package com.sidof.security.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @Author sidof
 * @Since 24/07/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Data
@AllArgsConstructor @NoArgsConstructor @Entity
public class Role {
    @SequenceGenerator(
            name = "sequence_role_id",
            sequenceName = "sequence_role_id",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "sequence_role_id",
            strategy = SEQUENCE
    )
    @Id
    private Long id;
    private String name;
}
