package com.sidof.security.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @Author sidof
 * @Since 12/07/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class ConfirmationToken {
    @SequenceGenerator(
            name = "sequence_confirmation_token",
            sequenceName = "sequence_confirmation_token",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "sequence_confirmation_token",
            strategy = SEQUENCE
    )
    @Id
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name="app_user_id",referencedColumnName = "id",nullable = false)
    private AppUser appUser;
}
